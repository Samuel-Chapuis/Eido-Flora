package fr.Eidolyth.item;

import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.level.block.Block;

public class BiomColoredPlaceOnWaterBlockItem extends PlaceOnWaterBlockItem implements net.minecraft.client.color.item.ItemColor {

    public BiomColoredPlaceOnWaterBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public int getColor(net.minecraft.world.item.ItemStack stack, int tintIndex) {
        // Return the default foliage color for items in inventory
        // This provides a consistent green color when not in a specific biome context
        return 0x48B518;
    }
}
