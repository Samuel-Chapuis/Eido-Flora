package fr.Eidolyth.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VoxelBlock extends Block {
    public static VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public VoxelBlock(BlockBehaviour.Properties properties) {
    super(properties
        .strength(1.0f)
        .noOcclusion()
        .instabreak()
        .isRedstoneConductor((a, b, c) -> false)
        .isSuffocating((a, b, c) -> false));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
        // Cache la face si le bloc voisin est le même
        if (adjacentState.is(state.getBlock())) return true;
        return super.skipRendering(state, adjacentState, side);
    }

    public void setshape(VoxelShape shape) {
        SHAPE = shape;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(net.minecraft.world.item.context.BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter plevel, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
