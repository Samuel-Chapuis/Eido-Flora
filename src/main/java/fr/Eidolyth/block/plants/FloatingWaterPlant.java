package fr.Eidolyth.block.plants;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class FloatingWaterPlant extends WaterlilyBlock implements net.minecraft.client.color.block.BlockColor {
    public static VoxelShape SHAPE = Block.box(0, 0, 0, 16, 1.5, 16);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public FloatingWaterPlant(Properties properties) {
        super(properties
                .strength(0.0F)  // Same as vanilla lily pad
                .noOcclusion()
                .instabreak()
                .sound(SoundType.LILY_PAD));  // Use lily pad sounds
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    public void setshape(VoxelShape shape) {
        SHAPE = shape;
    }
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // Allow placement on water blocks
        BlockPos pos = context.getClickedPos();
        BlockState stateBelow = context.getLevel().getBlockState(pos.below());
        
        if (mayPlaceOn(stateBelow, context.getLevel(), pos.below())) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        }
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter plevel, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        FluidState fluidState = level.getFluidState(pos);
        FluidState fluidStateAbove = level.getFluidState(pos.above());
        // Allow placement on water blocks or ice, with empty fluid above
        return (fluidState.getType() == Fluids.WATER || state.getBlock() instanceof IceBlock) &&
                fluidStateAbove.getType() == Fluids.EMPTY;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockBelow = pos.below();
        BlockState stateBelow = level.getBlockState(blockBelow);
        FluidState fluidStateBelow = level.getFluidState(blockBelow);
        // Can survive on water blocks or if water is present
        return (fluidStateBelow.getType() == Fluids.WATER || stateBelow.getBlock() instanceof IceBlock) ||
               this.mayPlaceOn(stateBelow, level, blockBelow);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, facingState, level, currentPos, facingPos);
    }

    @Override
    public int getColor(BlockState state, @Nullable BlockAndTintGetter world, @Nullable BlockPos pos, int tintIndex) {
        if (world != null && pos != null) {
            return BiomeColors.getAverageFoliageColor(world, pos);
        }
        // Fallback color for out-of-world rendering (e.g., inventory)
        return 0x48B518;
    }
}
