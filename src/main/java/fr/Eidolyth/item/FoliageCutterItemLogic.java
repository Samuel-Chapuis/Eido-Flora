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
            int radius = cutter.getRadius();
            try {
                for (int dx = -radius; dx <= radius; dx++) {
                    for (int dy = -radius; dy <= radius; dy++) {
                        for (int dz = -radius; dz <= radius; dz++) {
                            if (dx == 0 && dy == 0 && dz == 0) {
                                continue;
                            }

                            checked++;
                            BlockPos targetPos = origin.offset(dx, dy, dz);

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

                            boolean ok = level.destroyBlock(targetPos, drop, player);
                            if (ok) {
                                destroyed++;
                            } else {
                                failed++;
                                if (verbose) {
                                    EidoPlants.LOGGER.info("[FoliageCutter] Failed to destroy {} ({})", targetPos, targetState);
                                }
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
    }
}
