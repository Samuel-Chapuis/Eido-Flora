package fr.Eidolyth.item;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;

public class FoliageCutterItem extends AxeItem {
    private final int radius;

    public FoliageCutterItem(Tier tier, Properties properties, int radius) {
        super(tier, properties);
        this.radius = radius;
        FoliageCutterItemLogic.ensureRegistered();
    }

    public int getRadius() {
        return radius;
    }
}