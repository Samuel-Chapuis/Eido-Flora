package fr.Eidolyth.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import fr.Eidolyth.block.plants.AlgaeHead;

public class TallKelpFeature extends Feature<SimpleBlockConfiguration> {
    private final int minHeight;
    private final int maxHeight;

    public TallKelpFeature(Codec<SimpleBlockConfiguration> codec, int minHeight, int maxHeight) {
        super(codec);
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    @Override
    public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        BlockState headState = context.config().toPlace().getState(random, origin);
        if (!(headState.getBlock() instanceof GrowingPlantHeadBlock headBlock)) {
            return false;
        }

        if (!level.getBlockState(origin).is(Blocks.WATER)) {
            return false;
        }

        BlockPos below = origin.below();
        BlockState belowState = level.getBlockState(below);
        if (belowState.is(Blocks.WATER) || belowState.isAir()) {
            return false;
        }

        if (!(headBlock instanceof AlgaeHead algaeHead)) {
            return false;
        }
        BlockState bodyState = algaeHead.getBodyBlockPublic().defaultBlockState();

        int height = minHeight + random.nextInt(maxHeight - minHeight + 1);
        int actual = 0;
        BlockPos.MutableBlockPos cursor = origin.mutable();
        while (actual < height && level.getBlockState(cursor).is(Blocks.WATER)) {
            actual++;
            cursor.move(Direction.UP);
        }

        if (actual == 0) {
            return false;
        }

        for (int i = 0; i < actual - 1; i++) {
            level.setBlock(origin.above(i), bodyState, 2);
        }

        BlockState placedHead = headState;
        if (placedHead.hasProperty(BlockStateProperties.AGE_25)) {
            placedHead = placedHead.setValue(BlockStateProperties.AGE_25, random.nextInt(26));
        }
        level.setBlock(origin.above(actual - 1), placedHead, 2);
        return true;
    }
}
