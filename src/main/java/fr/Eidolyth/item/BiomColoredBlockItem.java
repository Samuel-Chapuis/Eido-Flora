package fr.Eidolyth.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class BiomColoredBlockItem extends BlockItem implements net.minecraft.client.color.item.ItemColor {

    public BiomColoredBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public int getColor(net.minecraft.world.item.ItemStack stack, int tintIndex) {
        // Return the default foliage color for items in inventory
        // This provides a consistent green color when not in a specific biome context
        return 0x48B518;
    }
}
