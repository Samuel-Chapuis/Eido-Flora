package fr.Eidolyth;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;
import java.util.function.Supplier;
import fr.Eidolyth.block.VoxelBlock;
import fr.Eidolyth.block.plants.FloatingWaterPlant;
import fr.Eidolyth.block.plants.FloatingAlgaeBlock;
import fr.Eidolyth.block.plants.CattailBlock;
import fr.Eidolyth.block.plants.LeafLitterBlock;
import fr.Eidolyth.block.plants.OrangeLeafLitterBlock;
import fr.Eidolyth.block.plants.BiomColoredBlock;
import fr.Eidolyth.block.plants.CustomSaplingBlock;
import fr.Eidolyth.block.plants.MangroveCustomSapling;
import fr.Eidolyth.block.plants.CutoutFlowerBlock;
import fr.Eidolyth.block.plants.Algae;
import fr.Eidolyth.block.plants.TallAlgae;
import fr.Eidolyth.block.plants.GrowingAlgae;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, EidoPlants.MODID);

    public static final DeferredHolder<Block, Block> JUNGLE_GRASS = registerBlock("junglegrass", () -> new VoxelBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY).noOcclusion()));
    public static final DeferredHolder<Block, Block> JUNGLE_GRASS_LIGHT = registerBlock("junglegrasslight", () -> new VoxelBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY).noOcclusion()));

    public static final DeferredHolder<Block, Block> GRAPE_VINE = registerBiomColoredBlock("grapevine", () -> new BiomColoredBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noOcclusion().strength(0.2F).sound(SoundType.GRASS)));
    public static final DeferredHolder<Block, Block> GRAPY_GRAPE_VINE = registerBiomColoredBlock("grapygrapevine", () -> new BiomColoredBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noOcclusion().strength(0.2F).sound(SoundType.GRASS)));

    public static final DeferredHolder<Block, Block> BIG_LILY_PAD = registerBiomColoredWaterBlock("biglilypad", () -> new FloatingWaterPlant(BlockBehaviour.Properties.of().mapColor(MapColor.WATER).instabreak().sound(SoundType.LILY_PAD)));
    public static final DeferredHolder<Block, Block> BIG_LILY_PAD_PINK = registerBiomColoredWaterBlock("biglilypad_pink", () -> new FloatingWaterPlant(BlockBehaviour.Properties.of().mapColor(MapColor.WATER).instabreak().sound(SoundType.LILY_PAD)));
    public static final DeferredHolder<Block, Block> BIG_LILY_PAD_WHITE = registerBiomColoredWaterBlock("biglilypad_white", () -> new FloatingWaterPlant(BlockBehaviour.Properties.of().mapColor(MapColor.WATER).instabreak().sound(SoundType.LILY_PAD)));
    public static final DeferredHolder<Block, Block> BIG_LILY_PAD_RED = registerBiomColoredWaterBlock("biglilypad_red", () -> new FloatingWaterPlant(BlockBehaviour.Properties.of().mapColor(MapColor.WATER).instabreak().sound(SoundType.LILY_PAD)));
    public static final DeferredHolder<Block, Block> BIG_LILY_PAD_BLUE = registerBiomColoredWaterBlock("biglilypad_blue", () -> new FloatingWaterPlant(BlockBehaviour.Properties.of().mapColor(MapColor.WATER).instabreak().sound(SoundType.LILY_PAD)));

    public static final DeferredHolder<Block, Block> ALGAE0 = registerBiomColoredWaterBlock("algae0", () -> new FloatingAlgaeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WATER).instabreak().sound(SoundType.LILY_PAD)));
    public static final DeferredHolder<Block, Block> ALGAE1 = registerBiomColoredWaterBlock("algae1", () -> new FloatingAlgaeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WATER).instabreak().sound(SoundType.LILY_PAD)));

    public static final DeferredHolder<Block, Block> BROWN_SEAWEED = registerBlock("brown_seaweed", () -> new Algae(
        BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().instabreak().sound(SoundType.WET_GRASS)
    ));
    public static final DeferredHolder<Block, Block> RED_ALGAE = registerBlock("red_algae", () -> new TallAlgae(
        BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().instabreak().sound(SoundType.WET_GRASS)
    ));
    public static final DeferredHolder<Block, Block> BULL_KELP = registerBlock("bull_kelp", () -> new GrowingAlgae(
        BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().instabreak().sound(SoundType.WET_GRASS)
    ));
    public static final DeferredHolder<Block, Block> SARGASSUM = registerBlock("sargassum", () -> new GrowingAlgae(
        BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().instabreak().sound(SoundType.WET_GRASS)
    ));

    public static final DeferredHolder<Block, Block> BIG_DEAD_BUSH = registerBlock("big_dead_bush", () -> new VoxelBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final DeferredHolder<Block, Block> BIG_DEAD_TREE = registerBlock("big_dead_tree", () -> new VoxelBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));

    public static final DeferredHolder<Block, Block> CATTAILS1 = registerBlock("cattails1", () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
    public static final DeferredHolder<Block, Block> CATTAILS2 = registerBlock("cattails2", () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
    public static final DeferredHolder<Block, Block> SWAMP_CATTAILS1 = registerBlock("swamp_cattails1", () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
    public static final DeferredHolder<Block, Block> SWAMP_CATTAILS2 = registerBlock("swamp_cattails2", () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));

    public static final DeferredHolder<Block, Block> CACTUS_FLOWER = registerBlock("cactus_flower", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));

    public static final DeferredHolder<Block, Block> LEAF_LITTER = registerBiomColoredBlock("leaf_litter", () -> new OrangeLeafLitterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noOcclusion().instabreak().sound(SoundType.GRASS))); // Orange leaf litter
    public static final DeferredHolder<Block, Block> SPRING_LEAF_LITTER = registerBiomColoredBlock("spring_leaf_litter", () -> new LeafLitterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noOcclusion().instabreak().sound(SoundType.GRASS))); // Default green

    public static final DeferredHolder<Block, Block> WILD_FLOWER = registerBlock("wildflower", () -> new CutoutFlowerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noOcclusion().instabreak().sound(SoundType.GRASS)));
    public static final DeferredHolder<Block, Block> BLUET = registerBlock("bluet", () -> new CutoutFlowerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noOcclusion().instabreak().sound(SoundType.GRASS)));

    public static final DeferredHolder<Block, Block> HIBISCUS = registerBlock("hibiscus", () -> new FlowerBlock(
        MobEffects.HEAL,
        12,
        BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)
    ));

    public static final DeferredHolder<Block, Block> CUSTOM_ACCACIA_SAPLING = registerBlock("custom_acacia_sapling", () -> new CustomSaplingBlock(
    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS),
        List.of(
            ResourceLocation.fromNamespaceAndPath("eidoplants", "acacia1"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "acacia2"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "acacia3"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "acacia4")
        )
    ));

    public static final DeferredHolder<Block, Block> CUSTOM_BIRCH_SAPLING = registerBlock("custom_birch_sapling", () -> new CustomSaplingBlock(
    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS),
        List.of(
            ResourceLocation.fromNamespaceAndPath("eidoplants", "birch1"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "birch2"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "birch3"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "birch4")
        )
    ));

    public static final DeferredHolder<Block, Block> CUSTOM_BUSHY_BIRCH_SAPLING = registerBlock("custom_bushy_birch_sapling", () -> new CustomSaplingBlock(
    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS),
        List.of(
            ResourceLocation.fromNamespaceAndPath("eidoplants", "bushy_birch1"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "bushy_birch2"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "bushy_birch3"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "bushy_birch4"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "bushy_birch5")
        )
    ));

    public static final DeferredHolder<Block, Block> CUSTOM_DARK_OAK_SAPLING = registerBlock("custom_dark_oak_sapling", () -> new CustomSaplingBlock(
    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS),
        List.of(
            ResourceLocation.fromNamespaceAndPath("eidoplants", "dark_oak1"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "dark_oak2"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "dark_oak3")
        )
    ));

    public static final DeferredHolder<Block, Block> CUSTOM_JUNGLE_SAPLING = registerBlock("custom_jungle_sapling", () -> new CustomSaplingBlock(
    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS),
        List.of(
            ResourceLocation.fromNamespaceAndPath("eidoplants", "jungle1"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "jungle2"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "jungle3")
        )
    ));

    public static final DeferredHolder<Block, Block> CUSTOM_MANGROVE_SAPLING = registerBlock("custom_mangrove_sapling", () -> new MangroveCustomSapling(
    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS),
        List.of(
            ResourceLocation.fromNamespaceAndPath("eidoplants", "mangrove1"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "mangrove2"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "mangrove3")
        )
    ));

    public static final DeferredHolder<Block, Block> CUSTOM_OAK_SAPLING = registerBlock("custom_oak_sapling", () -> new CustomSaplingBlock(
    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS),
        List.of(
            ResourceLocation.fromNamespaceAndPath("eidoplants", "oak1"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "oak2"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "oak3")
        )
    ));

    public static final DeferredHolder<Block, Block> CUSTOM_PALM_SAPLING = registerBlock("custom_palm_sapling", () -> new CustomSaplingBlock(
    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS),
        List.of(
            ResourceLocation.fromNamespaceAndPath("eidoplants", "palm1"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "palm2"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "palm3"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "palm4"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "palm5")
        )
    ));

    public static final DeferredHolder<Block, Block> CUSTOM_PLUME_SAPLING = registerBlock("custom_plume_sapling", () -> new CustomSaplingBlock(
    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS),
        List.of(
            ResourceLocation.fromNamespaceAndPath("eidoplants", "plume1"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "plume2"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "plume3")
        )
    ));

    public static final DeferredHolder<Block, Block> CUSTOM_SAKURA_SAPLING = registerBlock("custom_sakura_sapling", () -> new CustomSaplingBlock(
    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS),
        List.of(
            ResourceLocation.fromNamespaceAndPath("eidoplants", "sakura1"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "sakura2"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "sakura3"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "sakura4")
        )
    ));

    public static final DeferredHolder<Block, Block> CUSTOM_SEQUOIA_SAPLING = registerBlock("custom_sequoia_sapling", () -> new CustomSaplingBlock(
    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS),
        List.of(
            ResourceLocation.fromNamespaceAndPath("eidoplants", "sequoia1"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "sequoia2"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "sequoia3"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "sequoia4")
        )
    ));

    public static final DeferredHolder<Block, Block> CUSTOM_SPRUCE_SAPLING = registerBlock("custom_spruce_sapling", () -> new CustomSaplingBlock(
    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS),
        List.of(
            ResourceLocation.fromNamespaceAndPath("eidoplants", "spruce1"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "spruce2"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "spruce3"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "spruce4"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "spruce5"),
            ResourceLocation.fromNamespaceAndPath("eidoplants", "spruce6")
        )
    ));

    public static final DeferredHolder<Block, Block> WATERING_POT = registerWateringPotBlock("watering_pot", () -> new VoxelBlock(
    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)
    ));

    // Helper to register a block and corresponding item
    private static <T extends Block> DeferredHolder<Block, T> registerBlock(String name, Supplier<T> blockSupplier) {
        DeferredHolder<Block, T> toReturn = BLOCKS.register(name, blockSupplier);
        // register BlockItem
        ModItems.registerBlockItem(name, toReturn);
        return toReturn;
    }

    // Helper to register the watering pot block with its special item
    private static <T extends Block> DeferredHolder<Block, T> registerWateringPotBlock(String name, Supplier<T> blockSupplier) {
        DeferredHolder<Block, T> toReturn = BLOCKS.register(name, blockSupplier);
        // register WateringPotItem
        ModItems.registerWateringPotItem(name, toReturn);
        return toReturn;
    }

    // Helper to register a BiomColoredBlock with biome-colored item
    private static <T extends Block> DeferredHolder<Block, T> registerBiomColoredBlock(String name, Supplier<T> blockSupplier) {
        DeferredHolder<Block, T> toReturn = BLOCKS.register(name, blockSupplier);
        // register BiomColoredBlockItem
        ModItems.registerBiomColoredBlockItem(name, toReturn);
        return toReturn;
    }

    // Helper to register a biome-colored water-placeable block
    private static <T extends Block> DeferredHolder<Block, T> registerBiomColoredWaterBlock(String name, Supplier<T> blockSupplier) {
        DeferredHolder<Block, T> toReturn = BLOCKS.register(name, blockSupplier);
        // register BiomColoredPlaceOnWaterBlockItem
        ModItems.registerBiomColoredWaterBlockItem(name, toReturn);
        return toReturn;
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
