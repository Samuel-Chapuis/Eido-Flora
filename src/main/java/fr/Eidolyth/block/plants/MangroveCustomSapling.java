package fr.Eidolyth.block.plants;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class MangroveCustomSapling extends CustomSaplingBlock {

    public MangroveCustomSapling(Properties properties, List<ResourceLocation> structures) {
        super(properties, structures);
        yoffset = -7;
    }
}
