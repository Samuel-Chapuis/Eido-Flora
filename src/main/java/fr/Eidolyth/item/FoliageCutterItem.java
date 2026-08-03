package fr.Eidolyth.item;

import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class FoliageCutterItem extends AxeItem {
    private final int maxDistance;

    public FoliageCutterItem(Tier tier, Properties properties, int maxDistance) {
        super(tier, properties);
        this.maxDistance = Math.max(0, maxDistance);
        FoliageCutterItemLogic.ensureRegistered();
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents,
                                TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.eidoplants.foliage_cutter.description")
                .withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.translatable("item.eidoplants.foliage_cutter.warning")
                .withStyle(ChatFormatting.RED));
        tooltipComponents.add(Component.translatable("item.eidoplants.foliage_cutter.range", maxDistance)
                .withStyle(ChatFormatting.GRAY));
    }
}
