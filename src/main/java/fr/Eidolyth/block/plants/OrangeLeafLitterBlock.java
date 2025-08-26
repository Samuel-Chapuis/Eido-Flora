package fr.Eidolyth.block.plants;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import javax.annotation.Nullable;

public class OrangeLeafLitterBlock extends PinkPetalsBlock implements net.minecraft.client.color.block.BlockColor {

    // Fixed collision box for all leaf litter amounts - full block width, thin height
    private static final VoxelShape FIXED_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public OrangeLeafLitterBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public int getColor(BlockState state, @Nullable BlockAndTintGetter world, @Nullable BlockPos pos, int tintIndex) {
        if (world != null && pos != null) {
            int foliageColor = BiomeColors.getAverageFoliageColor(world, pos);
            // Blend foliage color with orange (70% orange, 30% foliage)
            return blendColors(foliageColor, 0xD2691E, 0.7f);
        }
        // Orange fallback color for inventory
        return 0xD2691E;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return FIXED_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return FIXED_SHAPE;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return FIXED_SHAPE;
    }
    
    private int blendColors(int color1, int color2, float ratio) {
        int r1 = (color1 >> 16) & 0xFF;
        int g1 = (color1 >> 8) & 0xFF;
        int b1 = color1 & 0xFF;
        
        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;
        
        int r = (int) (r1 * (1 - ratio) + r2 * ratio);
        int g = (int) (g1 * (1 - ratio) + g2 * ratio);
        int b = (int) (b1 * (1 - ratio) + b2 * ratio);
        
        return (r << 16) | (g << 8) | b;
    }
}
