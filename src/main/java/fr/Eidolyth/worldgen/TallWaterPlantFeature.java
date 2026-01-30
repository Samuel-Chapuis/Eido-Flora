package fr.Eidolyth.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class TallWaterPlantFeature extends Feature<SimpleBlockConfiguration> {
    public TallWaterPlantFeature(Codec<SimpleBlockConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        BlockState state = context.config().toPlace().getState(random, pos);
        if (!state.hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF)) {
            return false;
        }

        if (!level.getBlockState(pos).is(Blocks.WATER) || !level.getBlockState(pos.above()).is(Blocks.WATER)) {
            return false;
        }

        BlockState lower = state.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER);
        if (!lower.canSurvive(level, pos)) {
            return false;
        }

        level.setBlock(pos, lower, 2);
        BlockState upper = state.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER);
        if (!upper.canSurvive(level, pos.above())) {
            level.removeBlock(pos, false);
            return false;
        }
        level.setBlock(pos.above(), upper, 2);
        return true;
    }
}
