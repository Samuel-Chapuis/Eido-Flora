package fr.Eidolyth.item;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;

public class FoliageCutterItem extends AxeItem {
    public FoliageCutterItem(Tier tier, Properties properties) {
        super(tier, properties);
        FoliageCutterItemLogic.ensureRegistered();
    }
}
