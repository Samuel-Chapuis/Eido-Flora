// package fr.Eidolyth.agriculture;

// import fr.Eidolyth.EidoPlants;
// import net.minecraft.core.BlockPos;
// import net.minecraft.server.level.ServerLevel;
// import net.minecraft.world.InteractionHand;
// import net.minecraft.world.item.Items;
// import net.minecraft.world.level.block.CropBlock;
// import net.minecraft.world.level.block.state.BlockState;
// import net.neoforged.bus.api.SubscribeEvent;
// import net.neoforged.fml.common.EventBusSubscriber;
// import net.neoforged.neoforge.event.entity.player.BonemealEvent;

// @EventBusSubscriber(modid = EidoPlants.MODID)
// public class BonemealNerf {

//     private static final float CHANCE = 0.1f;

//     @SubscribeEvent
//     public static void onBonemeal(BonemealEvent event) {
//         if (event.getLevel() == null || event.getPlayer() == null) return;

//         BlockPos pos = event.getPos();
//         if (pos == null) return;

//         BlockState state = event.getLevel().getBlockState(pos);
//         if (!(state.getBlock() instanceof CropBlock)) return;

//         // On ne s'applique qu'à la bone meal
//         boolean isBoneMeal = event.getPlayer().getMainHandItem().is(Items.BONE_MEAL)
//                 || event.getPlayer().getOffhandItem().is(Items.BONE_MEAL);
//         if (!isBoneMeal) return;

//         // 🔹 Bloque si la plante ne voit pas le ciel
//         if (!event.getLevel().canSeeSky(pos.above())) {
//             event.setCanceled(true);
//             consumeBoneMeal(event); // consomme même si ça échoue
//             playFailFeedback(event, pos);
//             return;
//         }

//         // 🔹 30% de chance de réussir
//         boolean success = event.getLevel().getRandom().nextFloat() <= CHANCE;
//         if (!success) {
//             event.setCanceled(true);
//             consumeBoneMeal(event); // consomme à l’échec
//             playFailFeedback(event, pos);
//         }
//         // Sinon (success == true) → vanilla gère normalement : croissance, anim, particules, consommation
//     }

//     private static void consumeBoneMeal(BonemealEvent event) {
//         if (event.getPlayer().getMainHandItem().is(Items.BONE_MEAL)) {
//             event.getPlayer().getMainHandItem().shrink(1);
//         } else if (event.getPlayer().getOffhandItem().is(Items.BONE_MEAL)) {
//             event.getPlayer().getOffhandItem().shrink(1);
//         }
//     }

//     private static void playFailFeedback(BonemealEvent event, BlockPos pos) {
//         // anim de bras
//         event.getPlayer().swing(InteractionHand.MAIN_HAND, true);
//         // particules bone meal
//         if (event.getLevel() instanceof ServerLevel server) {
//             server.levelEvent(2005, pos, 0);
//         }
//     }
// }
