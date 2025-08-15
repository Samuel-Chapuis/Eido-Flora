package fr.Eidolyth.block;

import fr.Eidolyth.EidoPlant;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.GrassColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = EidoPlant.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientBlockEventSubscriber {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {

    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {

        // Biom Colored Blocks
        event.register(
                (state, level, pos, tintIndex) -> {
                    if (level != null && pos != null) {
                        return BiomeColors.getAverageFoliageColor(level, pos);
                    }
                    return GrassColor.get(0.5D, 1.0D);
                },
                ModBlocks.GRAPE_VINE.get(),
                ModBlocks.GRAPY_GRAPE_VINE.get(),
                ModBlocks.SPRING_LEAF_LITTER.get(),
                ModBlocks.BIG_LILY_PAD.get(),
                ModBlocks.BIG_LILY_PAD_PINK.get(),
                ModBlocks.BIG_LILY_PAD_WHITE.get(),
                ModBlocks.BIG_LILY_PAD_RED.get(),
                ModBlocks.BIG_LILY_PAD_BLUE.get()
        );

        // Orange Biom Colored Block
        event.register(
                (state, level, pos, tintIndex) -> {
                    if (level != null && pos != null) {
                        int biomeColor = BiomeColors.getAverageFoliageColor(level, pos);
                        int r = (biomeColor >> 16) & 0xFF;
                        int g = (biomeColor >>  8) & 0xFF;
                        int b = (biomeColor      ) & 0xFF;
                        float[] hsb = java.awt.Color.RGBtoHSB(r, g, b, null);
                        float orangeHue = 30f / 360f;

                        float newHue = (hsb[0] + orangeHue) * 0.5f;
                        float newSat = hsb[1];
                        float newBright = hsb[2];

                        int newRgb = java.awt.Color.HSBtoRGB(newHue, newSat, newBright);
                        return newRgb;
                    }
                    return 0xFFA500;
                },
                ModBlocks.LEAF_LITTER.get()
        );
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> 0x48B518, 
            ModBlocks.GRAPE_VINE.get().asItem(),
            ModBlocks.GRAPY_GRAPE_VINE.get().asItem(),
            ModBlocks.SPRING_LEAF_LITTER.get().asItem(),
            ModBlocks.BIG_LILY_PAD.get().asItem(),
            ModBlocks.BIG_LILY_PAD_PINK.get().asItem(),
            ModBlocks.BIG_LILY_PAD_WHITE.get().asItem(),
            ModBlocks.BIG_LILY_PAD_RED.get().asItem(),
            ModBlocks.BIG_LILY_PAD_BLUE.get().asItem()
        );
        event.register((stack, tintIndex) -> 0xFFA500,
            ModBlocks.LEAF_LITTER.get().asItem()
        );
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Dans le cas ou la mention "render_type": "cutout" ne suffit pas ( exemple heritage )
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUET.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.HIBISCUS.get(), RenderType.cutout());
        });
    }
}
