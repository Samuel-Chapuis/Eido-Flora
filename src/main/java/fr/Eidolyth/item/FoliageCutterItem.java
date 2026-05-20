package fr.Eidolyth.item;

import net.minecraft.world.item.ShearsItem;

public class FoliageCutterItem extends ShearsItem {

    public FoliageCutterItem(Properties properties) {
        super(properties);
        FoliageCutterItemLogic.ensureRegistered();
    }
}
