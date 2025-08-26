package fr.Eidolyth.agriculture;

import fr.Eidolyth.EidoPlants;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.block.CropGrowEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = EidoPlants.MODID)
public class GrowthTweaks {

    @SubscribeEvent
    public static void onCropGrowPre(CropGrowEvent.Pre event) {
        if (!(event.getState().getBlock() instanceof CropBlock)) return;

        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();

        // 🔹 Vérifie si la plante voit le ciel
        if (!level.canSeeSky(pos.above())) {
            return;
        }

        // 🔹 Chance de pousser (25% de chance)
        if (level.random.nextFloat() > 0.25f) {
            return;
        }

        // 🔹 Lumière minimale requise
        int light = level.getMaxLocalRawBrightness(pos);
        if (light < 11) {
            return;
        }

        // 🔹 Eau proche (rayon 4 blocs, facultatif)
        boolean hasWaterNear = BlockPos.betweenClosedStream(pos.offset(-4, -1, -4), pos.offset(4, 1, 4))
                .anyMatch(p -> level.getBlockState(p).getFluidState().isSource());
        if (!hasWaterNear) {
            return;
        }
    }
}