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
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import javax.annotation.Nullable;

public class CutoutFlowerBlock extends PinkPetalsBlock implements net.minecraft.client.color.block.BlockColor {

    // Fixed collision box for flowers - full block width, thin height
    private static final VoxelShape FIXED_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public CutoutFlowerBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public int getColor(BlockState state, @Nullable BlockAndTintGetter world, @Nullable BlockPos pos, int tintIndex) {
        if (world != null && pos != null) {
            return BiomeColors.getAverageFoliageColor(world, pos);
        }
        // Green fallback color for inventory
        return 0x48B518;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return FIXED_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // No collision so entities (players, items) can pass through like vanilla grass
        return Shapes.empty();
    }
}
