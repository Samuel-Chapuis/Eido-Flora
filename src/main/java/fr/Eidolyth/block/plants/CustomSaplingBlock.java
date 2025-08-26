package fr.Eidolyth.block.plants;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nonnull;

public class CustomSaplingBlock extends BushBlock implements BonemealableBlock {
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
    protected int yoffset = 0;
    private final List<ResourceLocation> structureList;

    public CustomSaplingBlock(BlockBehaviour.Properties properties, List<ResourceLocation> structures) {
        super(properties);
        this.structureList = structures;
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, 0));
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(@Nonnull Level level, @Nonnull RandomSource random, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(@Nonnull ServerLevel level, @Nonnull RandomSource random, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        advanceTree(level, pos, state, random);
    }

    public void advanceTree(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        if (state.getValue(STAGE) == 0) {
            level.setBlock(pos, state.cycle(STAGE), 4);
            return;
        }

        // Remove the sapling
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        
        // Select a random structure from the list
        ResourceLocation structureId = structureList.get(random.nextInt(structureList.size()));
        
        System.out.println("=== MANUAL STRUCTURE LOADING ===");
        System.out.println("Attempting to load structure: " + structureId);
        
        // Try to load the structure manually using the resource manager
        StructureTemplate template = new StructureTemplate();
        String resourcePath = "/data/" + structureId.getNamespace() + "/structures/" + structureId.getPath() + ".nbt";
        
        try {
            // Get the resource as an input stream
            InputStream inputStream = CustomSaplingBlock.class.getResourceAsStream(resourcePath);
            
            if (inputStream == null) {
                System.err.println("Resource not found: " + resourcePath);
                System.err.println("Trying alternative path...");
                
                // Try alternative path without leading slash
                String altPath = "data/" + structureId.getNamespace() + "/structures/" + structureId.getPath() + ".nbt";
                inputStream = CustomSaplingBlock.class.getClassLoader().getResourceAsStream(altPath);
                
                if (inputStream == null) {
                    System.err.println("Alternative resource not found: " + altPath);
                    level.setBlock(pos, state, 3);
                    return;
                }
                System.out.println("Found with alternative path: " + altPath);
            } else {
                System.out.println("Found resource: " + resourcePath);
            }
            
            // Read the NBT data
            CompoundTag nbtData = NbtIo.readCompressed(inputStream, NbtAccounter.unlimitedHeap());
            inputStream.close();
            
            // Load the template from NBT
            template.load(level.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.BLOCK), nbtData);
            
            System.out.println("Structure loaded successfully from NBT!");
            
        } catch (IOException e) {
            System.err.println("Failed to load structure NBT: " + e.getMessage());
            e.printStackTrace();
            level.setBlock(pos, state, 3);
            return;
        } catch (Exception e) {
            System.err.println("Unexpected error loading structure: " + e.getMessage());
            e.printStackTrace();
            level.setBlock(pos, state, 3);
            return;
        }
        
        // Get structure size for proper centering
        Vec3i size = template.getSize();
        System.out.println("Structure size: " + size.getX() + "x" + size.getY() + "x" + size.getZ());
        
        if (size.equals(Vec3i.ZERO)) {
            System.err.println("Structure has zero size, something went wrong");
            level.setBlock(pos, state, 3);
            return;
        }
        
        // Calculate the center offset (place structure centered on the sapling position)
        int halfX = size.getX() / 2;
        int halfZ = size.getZ() / 2;
        
        // Calculate the origin position (bottom-center of structure should be at sapling position)
        BlockPos origin = pos.offset(-halfX, yoffset, -halfZ);
        
        // Create placement settings
        StructurePlaceSettings settings = new StructurePlaceSettings()
                .setRotation(net.minecraft.world.level.block.Rotation.NONE)
                .setMirror(net.minecraft.world.level.block.Mirror.NONE)
                .addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR)
                .setIgnoreEntities(false)
                .setFinalizeEntities(true);
        
        System.out.println("Placing structure at origin: " + origin + " (sapling was at: " + pos + ")");
        
        // Place the structure
        try {
            boolean success = template.placeInWorld(level, origin, origin, settings, random, 2);
            System.out.println("Structure placement result: " + success);
            System.out.println("=== END MANUAL STRUCTURE LOADING ===");
            
            if (!success) {
                System.err.println("Failed to place structure, placing sapling back");
                level.setBlock(pos, state, 3);
            }
        } catch (Exception e) {
            System.err.println("Exception while placing structure: " + e.getMessage());
            e.printStackTrace();
            level.setBlock(pos, state, 3);
        }
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STAGE);
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return null;
    }
}
