package fr.Eidolyth.item;

import fr.Eidolyth.EidoMod;
import fr.Eidolyth.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


/**
 * Registers custom creative mode tabs for the EidoMod vegetation mod.
 * Organizes mod-specific plant items and blocks within the Minecraft creative inventory.
 */
public class ModCreativeModTabs {

    /**
     * DeferredRegister instance for registering creative mode tabs.
     * Associates the tabs with the mod's unique identifier.
     */
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EidoMod.MODID);


    public static final RegistryObject<CreativeModeTab> PLANTS_TAB = CREATIVE_MODE_TABS.register("plants_eidomod",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.JUNGLE_GRASS.get()))
                    .title(Component.translatable("creativetab.plants_eidomod"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.CUSTOM_ACCACIA_SAPLING.get());
                        pOutput.accept(ModBlocks.CUSTOM_BIRCH_SAPLING.get());
                        pOutput.accept(ModBlocks.CUSTOM_BUSHY_BIRCH_SAPLING.get());
                        pOutput.accept(ModBlocks.CUSTOM_DARK_OAK_SAPLING.get());
                        pOutput.accept(ModBlocks.CUSTOM_JUNGLE_SAPLING.get());
                        pOutput.accept(ModBlocks.CUSTOM_MANGROVE_SAPLING.get());
                        pOutput.accept(ModBlocks.CUSTOM_OAK_SAPLING.get());
                        pOutput.accept(ModBlocks.CUSTOM_PALM_SAPLING.get());
                        pOutput.accept(ModBlocks.CUSTOM_SPRUCE_SAPLING.get());
                        pOutput.accept(ModBlocks.CUSTOM_PLUME_SAPLING.get());
                        pOutput.accept(ModBlocks.CUSTOM_SAKURA_SAPLING.get());
                        pOutput.accept(ModBlocks.CUSTOM_SEQUOIA_SAPLING.get());
                        pOutput.accept(ModBlocks.CUSTOM_SPRUCE_SAPLING.get());
                        pOutput.accept(ModBlocks.JUNGLE_GRASS.get());
                        pOutput.accept(ModBlocks.JUNGLE_GRASS_LIGHT.get());
                        pOutput.accept(ModBlocks.CACTUS_FLOWER.get());
                        pOutput.accept(ModBlocks.LEAF_LITTER.get());
                        pOutput.accept(ModBlocks.SPRING_LEAF_LITTER.get());
                        pOutput.accept(ModBlocks.WILD_FLOWER.get());
                        pOutput.accept(ModBlocks.BLUET.get());
                        pOutput.accept(ModBlocks.HIBISCUS.get());
                        pOutput.accept(ModBlocks.BIG_LILY_PAD.get());
                        pOutput.accept(ModBlocks.BIG_LILY_PAD_PINK.get());
                        pOutput.accept(ModBlocks.BIG_LILY_PAD_BLUE.get());
                        pOutput.accept(ModBlocks.BIG_LILY_PAD_RED.get());
                        pOutput.accept(ModBlocks.BIG_LILY_PAD_WHITE.get());
                        pOutput.accept(ModBlocks.GRAPE_VINE.get());
                        pOutput.accept(ModBlocks.GRAPY_GRAPE_VINE.get());
                        pOutput.accept(ModBlocks.BIG_DEAD_BUSH.get());
                        pOutput.accept(ModBlocks.BIG_DEAD_TREE.get());
                        pOutput.accept(ModBlocks.ALGAE0.get());
                        pOutput.accept(ModBlocks.ALGAE1.get());
                        pOutput.accept(ModBlocks.CATTAILS1.get());
                        pOutput.accept(ModBlocks.CATTAILS2.get());
                        pOutput.accept(ModBlocks.SWAMP_CATTAILS1.get());
                        pOutput.accept(ModBlocks.SWAMP_CATTAILS2.get());
                    })
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .build());

    /**
     * Registers all creative mode tabs with the provided event bus.
     * This method should be called during the mod's initialization phase to ensure
     * that the creative tabs are registered at the appropriate time.
     *
     * @param eventBus The mod's event bus used for registering creative tabs.
     */
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}