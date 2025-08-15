package fr.Eidolyth.block.plants;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class LeafLitterBlock extends PinkPetalsBlock implements BlockColor {

    public LeafLitterBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    /**
     * Forge/Vanilla appellera cette méthode (via BlockColor)
     * dès qu’un modèle utilisant tintindex sera rendu.
     */
    @Override
    public int getColor(BlockState state, @Nullable BlockAndTintGetter world, @Nullable BlockPos pos, int tintIndex) {
        if (world != null && pos != null) {
            return BiomeColors.getAverageFoliageColor(world, pos);
        }
        // Couleur de secours si hors monde (ex. inventaire)
        return 0x48B518;
    }
}