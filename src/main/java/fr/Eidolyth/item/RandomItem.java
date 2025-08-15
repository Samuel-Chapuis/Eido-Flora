package fr.Eidolyth.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Random;

/**
 * Item qui pose aléatoirement l’un des blocs passés en paramètre,
 * en respectant la logique de placement native de chaque bloc
 * (DoublePlant, WaterPlant, etc.).
 */
public class RandomItem extends Item {
    private final List<Block> choices;
    private final Random random = new Random();

    public RandomItem(List<Block> choices, Properties props) {
        super(props);
        this.choices = choices;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();

        // 1) On ne fait rien côté client, on renvoie SUCCESS pour que le serveur prenne la main
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        // 2) Construire un BlockPlaceContext à partir du UseOnContext
        BlockPlaceContext bpc = new BlockPlaceContext(context);

        // 3) Déterminer la position finale où poser le bloc :
        //    -> c’est la case “face du clic” à partir de bpc.getClickedPos()
        BlockPos placePos = bpc.getClickedPos().relative(bpc.getClickedFace());

        // 4) Vérifier l’éventuel bloc déjà présent à placePos
        if (!world.getBlockState(placePos).canBeReplaced()) {
            return InteractionResult.FAIL;
        }

        // 5) Choisir aléatoirement l’un des blocs de la liste
        Block toPlace = choices.get(random.nextInt(choices.size()));

        // 6) Demander à ce bloc son état “prêt à poser” :
        //    Pour DoublePlantBlock (CattailBlock), getStateForPlacement() posera la moitié haute.
        //    Pour WaterPlant, getStateForPlacement() fournira l’orientation, mais canSurvive()
        //       fera échouer si ce n’est pas sur de l’eau.
        BlockState state = toPlace.getStateForPlacement(bpc);
        if (state == null) {
            return InteractionResult.FAIL;
        }

        // 7) Vérifier que ce bloc peut survivre à cet emplacement
        if (!state.canSurvive(world, placePos)) {
            return InteractionResult.FAIL;
        }

        // 8) Placer le bloc (la moitié haute, si nécessaire, a déjà été posée par getStateForPlacement)
        world.setBlock(placePos, state, 3);

        // 9) Décrémenter l’item dans la main du joueur (sauf en créatif)
        Player player = context.getPlayer();
        if (player != null && !player.getAbilities().instabuild) {
            context.getItemInHand().shrink(1);
        }

        return InteractionResult.CONSUME;
    }
}
