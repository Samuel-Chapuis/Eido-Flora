package fr.Eidolyth.block.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.List;
import java.util.Optional;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import javax.annotation.Nonnull;

import static fr.Eidolyth.block.ModBlocks.BLOCKS;
import net.minecraft.world.level.block.Rotation;

public class CustomSaplingBlock extends BushBlock implements BonemealableBlock {
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
    protected int yoffset = 0;
    private final List<ResourceLocation> structureList;

    public CustomSaplingBlock(BlockBehaviour.Properties properties, List<ResourceLocation> structures) {
        super(properties);
        this.structureList = structures;
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, 0));
    }

    // ...getShape, randomTick comme SaplingBlock...

    @Override
    public boolean isValidBonemealTarget(@Nonnull LevelReader level, @Nonnull BlockPos pos, @Nonnull BlockState state, boolean isClient) {
        // Autorise la bone meal à tous les stades
        return true;
    }

    @Override
    public boolean isBonemealSuccess(@Nonnull Level level, @Nonnull RandomSource random, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(@Nonnull ServerLevel level, @Nonnull RandomSource random, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        // Fait avancer la pousse à chaque clic
        advanceTree(level, pos, state, random);
    }

    public void advanceTree(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        if (state.getValue(STAGE) == 0) {
            level.setBlock(pos, state.cycle(STAGE), 4);
            return;
        }

        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        Optional<StructureTemplate> opt = level
                .getStructureManager()
                .get(structureList.get(random.nextInt(structureList.size())));
        if (!opt.isPresent()) return;
        StructureTemplate template = opt.get();

        // Rotation aléatoire
        Rotation rotation = Rotation.getRandom(random);

        // Taille brute du template
        Vec3i size = template.getSize();
        int sx = size.getX();
        int sz = size.getZ();
        // demi-taille
        int hx = sx / 2;
        int hz = sz / 2;

        // Calcul de l'offset tourné
        BlockPos offset;
        switch (rotation) {
            case CLOCKWISE_90:
                offset = new BlockPos(-hz, yoffset, hx);
                break;
            case COUNTERCLOCKWISE_90:
                offset = new BlockPos(hz, yoffset, -hx);
                break;
            case CLOCKWISE_180:
                offset = new BlockPos(-hx, yoffset, -hz);
                break;
            default: // NONE
                offset = new BlockPos(hx, yoffset, hz);
                break;
        }

        // Origine pour centrer la structure
        BlockPos origin = pos.subtract(offset);

        // Settings sans pivot (on gère nous-mêmes l'offset)
        StructurePlaceSettings settings = new StructurePlaceSettings()
                .setRotation(rotation)
                .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK)          // ignore les structure blocks
                .addProcessor(BlockIgnoreProcessor.AIR);

        // Placement
        template.placeInWorld(level, origin, origin, settings, random, 2);
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STAGE);
    }
}