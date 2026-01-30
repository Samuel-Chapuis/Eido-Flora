package fr.Eidolyth.block.plants;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.KelpBlock;

import java.util.function.Supplier;

public class AlgaeHead extends KelpBlock {
    private final Supplier<? extends Block> bodyBlock;

    public AlgaeHead(Properties properties, Supplier<? extends Block> bodyBlock) {
        super(properties);
        this.bodyBlock = bodyBlock;
    }

    @Override
    protected Block getBodyBlock() {
        return bodyBlock.get();
    }

    public Block getBodyBlockPublic() {
        return getBodyBlock();
    }
}
