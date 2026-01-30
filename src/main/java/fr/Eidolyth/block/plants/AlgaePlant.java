package fr.Eidolyth.block.plants;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.KelpPlantBlock;

public class AlgaePlant extends KelpPlantBlock {
    private final ResourceLocation headId;

    public AlgaePlant(Properties properties, ResourceLocation headId) {
        super(properties);
        this.headId = headId;
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) BuiltInRegistries.BLOCK.get(headId);
    }
}
