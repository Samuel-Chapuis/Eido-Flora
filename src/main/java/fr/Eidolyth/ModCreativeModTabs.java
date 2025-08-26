package fr.Eidolyth;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, EidoPlants.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PLANTS_TAB = CREATIVE_MODE_TABS.register("plants_eidoplants", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.JUNGLE_GRASS.get().asItem()))
            .title(Component.translatable("creativetab.plants_eidoplants"))
            .displayItems((pParameters, pOutput) -> {
                // Add blocks to creative tab
                pOutput.accept(ModBlocks.JUNGLE_GRASS.get());
                pOutput.accept(ModBlocks.JUNGLE_GRASS_LIGHT.get());
                pOutput.accept(ModBlocks.GRAPE_VINE.get());
                pOutput.accept(ModBlocks.GRAPY_GRAPE_VINE.get());
                pOutput.accept(ModBlocks.BIG_LILY_PAD.get());
                pOutput.accept(ModBlocks.BIG_LILY_PAD_PINK.get());
                pOutput.accept(ModBlocks.BIG_LILY_PAD_WHITE.get());
                pOutput.accept(ModBlocks.BIG_LILY_PAD_RED.get());
                pOutput.accept(ModBlocks.BIG_LILY_PAD_BLUE.get());
                pOutput.accept(ModBlocks.ALGAE0.get());
                pOutput.accept(ModBlocks.ALGAE1.get());
                pOutput.accept(ModBlocks.BIG_DEAD_BUSH.get());
                pOutput.accept(ModBlocks.BIG_DEAD_TREE.get());
                pOutput.accept(ModBlocks.CATTAILS1.get());
                pOutput.accept(ModBlocks.CATTAILS2.get());
                pOutput.accept(ModBlocks.SWAMP_CATTAILS1.get());
                pOutput.accept(ModBlocks.SWAMP_CATTAILS2.get());
                pOutput.accept(ModBlocks.CACTUS_FLOWER.get());
                pOutput.accept(ModBlocks.LEAF_LITTER.get());
                pOutput.accept(ModBlocks.SPRING_LEAF_LITTER.get());
                pOutput.accept(ModBlocks.WILD_FLOWER.get());
                pOutput.accept(ModBlocks.BLUET.get());
                pOutput.accept(ModBlocks.HIBISCUS.get());
                pOutput.accept(ModBlocks.CUSTOM_ACCACIA_SAPLING.get());
                pOutput.accept(ModBlocks.CUSTOM_BIRCH_SAPLING.get());
                pOutput.accept(ModBlocks.CUSTOM_BUSHY_BIRCH_SAPLING.get());
                pOutput.accept(ModBlocks.CUSTOM_DARK_OAK_SAPLING.get());
                pOutput.accept(ModBlocks.CUSTOM_JUNGLE_SAPLING.get());
                pOutput.accept(ModBlocks.CUSTOM_MANGROVE_SAPLING.get());
                pOutput.accept(ModBlocks.CUSTOM_OAK_SAPLING.get());
                pOutput.accept(ModBlocks.CUSTOM_PALM_SAPLING.get());
                pOutput.accept(ModBlocks.CUSTOM_PLUME_SAPLING.get());
                pOutput.accept(ModBlocks.CUSTOM_SAKURA_SAPLING.get());
                pOutput.accept(ModBlocks.CUSTOM_SEQUOIA_SAPLING.get());
                pOutput.accept(ModBlocks.CUSTOM_SPRUCE_SAPLING.get());
                pOutput.accept(ModBlocks.WATERING_POT.get());
            })
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .build());

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}
