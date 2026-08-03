package fr.Eidolyth.item;

import fr.Eidolyth.EidoPlants;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public final class FoliageCutterItemLogic {

    private static volatile boolean REGISTERED = false;
    private static final ThreadLocal<Boolean> AOE_BREAKING = ThreadLocal.withInitial(() -> Boolean.FALSE);

    private FoliageCutterItemLogic() {
    }

    private static final TagKey<Block> FOLIAGE_BREAKABLE = BlockTags.create(
        ResourceLocation.fromNamespaceAndPath("eidoplants", "foliage_breakable")
    );

    public static void ensureRegistered() {
        if (REGISTERED) {
            return;
        }
        REGISTERED = true;
        NeoForge.EVENT_BUS.register(new Handler());
        EidoPlants.LOGGER.info("[FoliageCutter] Logic registered");
    }

    private static final class Handler {
        @SubscribeEvent
        public void onBlockBroken(BlockEvent.BreakEvent event) {
            if (AOE_BREAKING.get()) {
                return;
            }

            if (!(event.getLevel() instanceof ServerLevel level)) {
                return;
            }

            if (!(event.getPlayer() instanceof ServerPlayer player)) {
                return;
            }

            ItemStack tool = player.getMainHandItem();
            if (!(tool.getItem() instanceof FoliageCutterItem cutter)) {
                return;
            }

            BlockState originState = event.getState();
            if (!originState.is(FOLIAGE_BREAKABLE)) {
                // Expected behavior: if you hit wood (or anything else), do not AOE.
                return;
            }

            BlockPos origin = event.getPos();
            boolean verbose = player.isShiftKeyDown();
            if (verbose) {
                EidoPlants.LOGGER.info("[FoliageCutter] Trigger @ {} state={}", origin, originState);
            }

            int checked = 0;
            int destroyed = 0;
            int failed = 0;
            int skippedAir = 0;
            int skippedNotLeaves = 0;
            int skippedOutOfBounds = 0;

            boolean drop = !player.getAbilities().instabuild;

            AOE_BREAKING.set(Boolean.TRUE);
            try {
                ArrayDeque<SearchNode> queue = new ArrayDeque<>();
                Set<BlockPos> visited = new HashSet<>();
                queue.add(new SearchNode(origin, 0));
                visited.add(origin);

                while (!queue.isEmpty()) {
                    SearchNode current = queue.removeFirst();

                    // The original block is destroyed by the BreakEvent itself.
                    if (current.distance() > 0) {
                        BlockState currentState = level.getBlockState(current.pos());
                        boolean ok = level.destroyBlock(current.pos(), drop, player);
                        if (ok) {
                            destroyed++;
                        } else {
                            failed++;
                            if (verbose) {
                                EidoPlants.LOGGER.info("[FoliageCutter] Failed to destroy {} ({})", current.pos(), currentState);
                            }
                        }
                    }

                    if (current.distance() >= cutter.getMaxDistance()) {
                        continue;
                    }

                    // Check all 26 neighboring positions: faces, edges, and corners.
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            for (int dz = -1; dz <= 1; dz++) {
                                if (dx == 0 && dy == 0 && dz == 0) {
                                    continue;
                                }

                                BlockPos targetPos = current.pos().offset(dx, dy, dz);
                                if (!visited.add(targetPos)) {
                                    continue;
                                }

                                checked++;
                                if (targetPos.getY() < level.getMinBuildHeight() || targetPos.getY() >= level.getMaxBuildHeight()
                                        || !level.getWorldBorder().isWithinBounds(targetPos)) {
                                    skippedOutOfBounds++;
                                    continue;
                                }

                                BlockState targetState = level.getBlockState(targetPos);
                                if (targetState.isAir()) {
                                    skippedAir++;
                                    continue;
                                }

                                if (!targetState.is(FOLIAGE_BREAKABLE)) {
                                    skippedNotLeaves++;
                                    continue;
                                }

                                queue.addLast(new SearchNode(targetPos, current.distance() + 1));
                            }
                        }
                    }
                }
            } finally {
                AOE_BREAKING.set(Boolean.FALSE);
            }

            if (verbose) {
                EidoPlants.LOGGER.info(
                        "[FoliageCutter] AOE done @ {} | checked={} destroyed={} failed={} skipped(air={},notLeaves={},oob={})",
                        origin, checked, destroyed, failed, skippedAir, skippedNotLeaves, skippedOutOfBounds
                );
            } else {
                EidoPlants.LOGGER.debug(
                        "[FoliageCutter] AOE done @ {} | checked={} destroyed={} failed={} skipped(air={},notLeaves={},oob={})",
                        origin, checked, destroyed, failed, skippedAir, skippedNotLeaves, skippedOutOfBounds
                );
            }
        }

        private record SearchNode(BlockPos pos, int distance) {
        }
    }
}
