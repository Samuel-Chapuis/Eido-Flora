package fr.Eidolyth.block;

import fr.Eidolyth.EidoMod;
import fr.Eidolyth.block.plants.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

import fr.Eidolyth.item.ModItems;

/**
 * Handles the registration of custom plant blocks for the EidoMod vegetation mod.
 * Blocks are registered using Forge's {@link DeferredRegister} system.
 */
public class ModBlocks {
	// DeferredRegister to hold all custom blocks
	public static final DeferredRegister<Block> BLOCKS =
			DeferredRegister.create(ForgeRegistries.BLOCKS, EidoMod.MODID); // Registers blocks under the mod's namespace

	/* --------------------- */
	/* --- Block Entries --- */
	/* --------------------- */

	/*  Plants Blocks */
	public static final RegistryObject<Block> JUNGLE_GRASS = registerBlock("junglegrass",
			() -> new VoxelBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)));

	public static final RegistryObject<Block> JUNGLE_GRASS_LIGHT = registerBlock("junglegrasslight",
			() -> new VoxelBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)));

	public static final RegistryObject<Block> GRAPE_VINE = registerBlock("grapevine",
			() -> new BiomColoredBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

	public static final RegistryObject<Block> GRAPY_GRAPE_VINE = registerBlock("grapygrapevine",
			() -> new BiomColoredBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

	public static final RegistryObject<Block> BIG_LILY_PAD = registerBlock("biglilypad",
			() -> new WaterPlant(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)));

	public static final RegistryObject<Block> BIG_LILY_PAD_PINK = registerBlock("biglilypad_pink",
			() -> new WaterPlant(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)));

	public static final RegistryObject<Block> BIG_LILY_PAD_WHITE = registerBlock("biglilypad_white",
			() -> new WaterPlant(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)));

	public static final RegistryObject<Block> BIG_LILY_PAD_RED = registerBlock("biglilypad_red",
			() -> new WaterPlant(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)));

	public static final RegistryObject<Block> BIG_LILY_PAD_BLUE = registerBlock("biglilypad_blue",
			() -> new WaterPlant(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)));

	public static final RegistryObject<Block> ALGAE0 = registerBlock("algae0",
			() -> new WaterPlant(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)));

	public static final RegistryObject<Block> ALGAE1 = registerBlock("algae1",
			() -> new WaterPlant(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)));

	public static final RegistryObject<Block> BIG_DEAD_BUSH = registerBlock("big_dead_bush",
			() -> new VoxelBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BUSH)));

	public static final RegistryObject<Block> BIG_DEAD_TREE = registerBlock("big_dead_tree",
			() -> new VoxelBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BUSH)));

	public static final RegistryObject<Block> CATTAILS1 = registerBlock("cattails1",
			() -> new CattailBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).noOcclusion()));

	public static final RegistryObject<Block> CATTAILS2 = registerBlock("cattails2",
			() -> new CattailBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).noOcclusion()));

	public static final RegistryObject<Block> SWAMP_CATTAILS1 = registerBlock("swamp_cattails1",
			() -> new CattailBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).noOcclusion()));

	public static final RegistryObject<Block> SWAMP_CATTAILS2 = registerBlock("swamp_cattails2",
			() -> new CattailBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).noOcclusion()));

	public static final RegistryObject<Block> CACTUS_FLOWER = registerBlock("cactus_flower",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).noOcclusion()));

	public static final RegistryObject<Block> LEAF_LITTER = registerBlock("leaf_litter",
			 () -> new LeafLitterBlock(BlockBehaviour.Properties.copy(Blocks.PINK_PETALS)));


	public static final RegistryObject<Block> SPRING_LEAF_LITTER = registerBlock("spring_leaf_litter",
			() -> new LeafLitterBlock(BlockBehaviour.Properties.copy(Blocks.PINK_PETALS)));

	public static final RegistryObject<Block> WILD_FLOWER = registerBlock("wildflower",
			() -> new PinkPetalsBlock(BlockBehaviour.Properties.copy(Blocks.PINK_PETALS).noOcclusion()));

	public static final RegistryObject<Block> BLUET = registerBlock("bluet",
			() -> new PinkPetalsBlock(BlockBehaviour.Properties.copy(Blocks.PINK_PETALS).noOcclusion()));

	public static final RegistryObject<Block> HIBISCUS = registerBlock("hibiscus",
			() -> new FlowerBlock(() -> MobEffects.HEAL, 12, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));


	public static RegistryObject<Block> CUSTOM_ACCACIA_SAPLING = registerBlock("custom_acacia_sapling",
		() -> new CustomSaplingBlock(
			BlockBehaviour.Properties.copy(Blocks.ACACIA_SAPLING),
			List.of(
				new ResourceLocation("eidomod", "acacia1"),
				new ResourceLocation("eidomod", "acacia2"),
				new ResourceLocation("eidomod", "acacia3"),
				new ResourceLocation("eidomod", "acacia4")
			)
		)
	);

	public static RegistryObject<Block> CUSTOM_BIRCH_SAPLING = registerBlock("custom_birch_sapling",
			() -> new CustomSaplingBlock(
					BlockBehaviour.Properties.copy(Blocks.BIRCH_SAPLING),
					List.of(
							new ResourceLocation("eidomod", "birch1"),
							new ResourceLocation("eidomod", "birch2"),
							new ResourceLocation("eidomod", "birch3"),
							new ResourceLocation("eidomod", "birch4")
					)
			)
	);

	public static RegistryObject<Block> CUSTOM_BUSHY_BIRCH_SAPLING = registerBlock("custom_bushy_birch_sapling",
			() -> new CustomSaplingBlock(
					BlockBehaviour.Properties.copy(Blocks.BIRCH_SAPLING),
					List.of(
							new ResourceLocation("eidomod", "bushy_birch1"),
							new ResourceLocation("eidomod", "bushy_birch2"),
							new ResourceLocation("eidomod", "bushy_birch3"),
							new ResourceLocation("eidomod", "bushy_birch4"),
							new ResourceLocation("eidomod", "bushy_birch5")
					)
			)
	);

	public static RegistryObject<Block> CUSTOM_DARK_OAK_SAPLING = registerBlock("custom_dark_oak_sapling",
		() -> new CustomSaplingBlock(
			BlockBehaviour.Properties.copy(Blocks.DARK_OAK_SAPLING),
			List.of(
				new ResourceLocation("eidomod", "dark_oak1"),
				new ResourceLocation("eidomod", "dark_oak2"),
				new ResourceLocation("eidomod", "dark_oak3")
			)
		)
	);

	public static RegistryObject <Block> CUSTOM_JUNGLE_SAPLING = registerBlock("custom_jungle_sapling",
		() -> new CustomSaplingBlock(
			BlockBehaviour.Properties.copy(Blocks.JUNGLE_SAPLING),
			List.of(
				new ResourceLocation("eidomod", "jungle1"),
				new ResourceLocation("eidomod", "jungle2"),
				new ResourceLocation("eidomod", "jungle3")
			)
		)
	);

	public static RegistryObject <Block> CUSTOM_MANGROVE_SAPLING = registerBlock("custom_mangrove_sapling",
		() -> new MangroveCustomSapling(
			BlockBehaviour.Properties.copy(Blocks.MANGROVE_PROPAGULE),
			List.of(
				new ResourceLocation("eidomod", "mangrove1"),
				new ResourceLocation("eidomod", "mangrove2"),
				new ResourceLocation("eidomod", "mangrove3")
			)
		)
	);

	public static final RegistryObject<Block> CUSTOM_OAK_SAPLING = registerBlock("custom_oak_sapling",
		() -> new CustomSaplingBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING),
			List.of(
				new ResourceLocation("eidomod", "oak1"),
				new ResourceLocation("eidomod", "oak2"),
				new ResourceLocation("eidomod", "oak3")
			)
		)
	);

	public static final RegistryObject<Block> CUSTOM_PALM_SAPLING = registerBlock("custom_palm_sapling",
		() -> new CustomSaplingBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING),
			List.of(
				new ResourceLocation("eidomod", "palm1"),
				new ResourceLocation("eidomod", "palm2"),
				new ResourceLocation("eidomod", "palm3"),
				new ResourceLocation("eidomod", "palm4"),
				new ResourceLocation("eidomod", "palm5")
			)
		)
	);

	public static final RegistryObject<Block> CUSTOM_PLUME_SAPLING = registerBlock("custom_plume_sapling",
		() -> new CustomSaplingBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING),
			List.of(
				new ResourceLocation("eidomod", "plume1"),
				new ResourceLocation("eidomod", "plume2"),
				new ResourceLocation("eidomod", "plume3")
			)
		)
	);

	public static final RegistryObject<Block> CUSTOM_SAKURA_SAPLING = registerBlock("custom_sakura_sapling",
		() -> new CustomSaplingBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING),
			List.of(
				new ResourceLocation("eidomod", "sakura1"),
				new ResourceLocation("eidomod", "sakura2"),
				new ResourceLocation("eidomod", "sakura3"),
				new ResourceLocation("eidomod", "sakura4")
			)
		)
	);

	public static final RegistryObject<Block> CUSTOM_SEQUOIA_SAPLING = registerBlock("custom_sequoia_sapling",
		() -> new CustomSaplingBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING),
			List.of(
				new ResourceLocation("eidomod", "sequoia1"),
				new ResourceLocation("eidomod", "sequoia2"),
				new ResourceLocation("eidomod", "sequoia3"),
				new ResourceLocation("eidomod", "sequoia4")
			)
		)
	);

	public static final RegistryObject<Block> CUSTOM_SPRUCE_SAPLING = registerBlock("custom_spruce_sapling",
		() -> new CustomSaplingBlock(
			BlockBehaviour.Properties.copy(Blocks.SPRUCE_SAPLING),
			List.of(
				new ResourceLocation("eidomod", "spruce1"),
				new ResourceLocation("eidomod", "spruce2"),
				new ResourceLocation("eidomod", "spruce3"),
				new ResourceLocation("eidomod", "spruce4"),
				new ResourceLocation("eidomod", "spruce5"),
				new ResourceLocation("eidomod", "spruce6")
			)
		)
	);

	/* --------------------- */
	/* --- Helper Methods --- */
	/* --------------------- */

	/**
	 * Registers a block and its corresponding BlockItem.
	 *
	 * @param name  The registry name of the block.
	 * @param block A supplier that provides the block instance.
	 * @param <T>   The type of the block.
	 * @return A {@link RegistryObject} representing the registered block.
	 */
	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
		// Register the block
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		// Register the block as an item
		registerBlockItem(name, toReturn);
		return toReturn;
	}

	/**
	 * Registers a BlockItem for a given block.
	 *
	 * @param name  The registry name of the block item.
	 * @param block The {@link RegistryObject} representing the block.
	 * @param <T>   The type of the block.
	 * @return A {@link RegistryObject} representing the registered BlockItem.
	 */
	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
		// 1. On retourne directement le RegistryObject<Item> créé
		//    en passant à Forge une Supplier qui sera appelée plus tard.
		return ModItems.ITEMS.register(name, () -> {
			// Ce code n'est exécuté QUE lors de l’enregistrement effectif des items,
			// donc à ce moment-là, block.get() n’est plus null.
			Block b = block.get();
			if (b instanceof WaterlilyBlock) {
				return new PlaceOnWaterBlockItem(b, new Item.Properties());
			}
			return new BlockItem(b, new Item.Properties());
		});
	}

	/**
	 * Registers all blocks in this class with the provided {@link IEventBus}.
	 *
	 * @param eventBus The event bus to register the blocks with.
	 */
	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}
