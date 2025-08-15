package fr.Eidolyth.event;

import com.mojang.blaze3d.platform.InputConstants;
import fr.Eidolyth.Globals;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

/**
 * Handles custom key input events for the ForPlanes mod.
 * This class defines custom key bindings and processes key press and release events
 * to modify global variables used in the mod's rendering logic.
 */
@Mod.EventBusSubscriber(modid = "eidomod", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyInputHandler {

    /* --------------------- */
    /* --- Key Bindings --- */
    /* --------------------- */

    /**
     * Defines the key binding for the 'J' key.
     * This key is used to modify the first global variable (Globals.global1).
     */
    public static final KeyMapping J_KEY_BINDING = new KeyMapping(
            "key.eidomod.j_key", // Translation key for localization
            InputConstants.Type.KEYSYM, // Type of input (keyboard key)
            GLFW.GLFW_KEY_J, // The GLFW key code for 'J'
            "category.eidomod.custom_keys" // Category in the controls menu
    );

    /**
     * Defines the key binding for the 'K' key.
     * This key is used to modify the second global variable (Globals.global2).
     */
    public static final KeyMapping K_KEY_BINDING = new KeyMapping(
            "key.eidomod.k_key", // Translation key for localization
            InputConstants.Type.KEYSYM, // Type of input (keyboard key)
            GLFW.GLFW_KEY_K, // The GLFW key code for 'K'
            "category.eidomod.custom_keys" // Category in the controls menu
    );

    public static final KeyMapping L_KEY_BINDING = new KeyMapping(
            "key.eidomod.l_key", // Translation key for localization
            InputConstants.Type.KEYSYM, // Type of input (keyboard key)
            GLFW.GLFW_KEY_L, // The GLFW key code for 'L'
            "category.eidomod.custom_keys" // Category in the controls menu
    );

    public static final KeyMapping U_KEY_BINDING = new KeyMapping(
            "key.eidomod.u_key", // Translation key for localization
            InputConstants.Type.KEYSYM, // Type of input (keyboard key)
            GLFW.GLFW_KEY_U, // The GLFW key code for 'U'
            "category.eidomod.custom_keys" // Category in the controls menu
    );

    public static final KeyMapping I_KEY_BINDING = new KeyMapping(
            "key.eidomod.i_key", // Translation key for localization
            InputConstants.Type.KEYSYM, // Type of input (keyboard key)
            GLFW.GLFW_KEY_I, // The GLFW key code for 'I'
            "category.eidomod.custom_keys" // Category in the controls menu
    );

    public static final KeyMapping O_KEY_BINDING = new KeyMapping(
            "key.eidomod.o_key", // Translation key for localization
            InputConstants.Type.KEYSYM, // Type of input (keyboard key)
            GLFW.GLFW_KEY_O, // The GLFW key code for 'O'
            "category.eidomod.custom_keys" // Category in the controls menu
    );

    /* ----------------------------- */
    /* --- Key Binding Registration --- */
    /* ----------------------------- */

    /**
     * Registers all custom key bindings with Minecraft's key binding system.
     * This method is called during the key mapping registration phase.
     *
     * @param event The RegisterKeyMappingsEvent used to register key bindings.
     */
    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(J_KEY_BINDING);
        event.register(K_KEY_BINDING);
        event.register(U_KEY_BINDING);
        event.register(I_KEY_BINDING);
    }

    /* -------------------------------- */
    /* --- Key Input Event Handling --- */
    /* -------------------------------- */

    /**
     * Handles key input events to detect when custom keys are pressed or released.
     * This inner class listens to key events and updates global variables accordingly.
     */
    @Mod.EventBusSubscriber(modid = "eidomod", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ClientEvents {

        /**
         * Called whenever a key input event occurs.
         * Checks if the 'J', 'K', or 'L' keys are pressed or released and updates global variables.
         *
         * @param event The InputEvent.Key event containing key input information.
         */
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            // Handle 'J' key events
            if (J_KEY_BINDING.matches(event.getKey(), event.getScanCode())) {
                if (event.getAction() == GLFW.GLFW_PRESS) {
                    Globals.global1 -= 0.2f; // Increment global1 by 0.2
                    System.out.println("J key pressed: " + Globals.global1);
                }
                if (event.getAction() == GLFW.GLFW_RELEASE) {
                    System.out.println("J key released");
                }
            }

            // Handle 'K' key events
            if (K_KEY_BINDING.matches(event.getKey(), event.getScanCode())) {
                if (event.getAction() == GLFW.GLFW_PRESS) {
                    Globals.global2 -= 0.2f; // Increment global2 by 0.2
                    System.out.println("K key pressed: " + Globals.global2);
                }
                if (event.getAction() == GLFW.GLFW_RELEASE) {
                    System.out.println("K key released");
                }
            } // 3.6000004

            if (L_KEY_BINDING.matches(event.getKey(), event.getScanCode())) {
                if (event.getAction() == GLFW.GLFW_PRESS) {
                    Globals.global3 -= 0.2f; // Increment global2 by 0.2
                    System.out.println("L key pressed: " + Globals.global3);
                }
                if (event.getAction() == GLFW.GLFW_RELEASE) {
                    System.out.println("L key released");
                }
            }

            if(U_KEY_BINDING.matches(event.getKey(), event.getScanCode())){
                if(event.getAction() == GLFW.GLFW_PRESS){
                    Globals.global1 += 0.2f;
                    System.out.println("U key pressed: " + Globals.global1);
                }
                if(event.getAction() == GLFW.GLFW_RELEASE){
                    System.out.println("U key released");
                }
            }


            if(I_KEY_BINDING.matches(event.getKey(), event.getScanCode())){
                if(event.getAction() == GLFW.GLFW_PRESS){
                    Globals.global2 += 0.2f;
                    System.out.println("I key pressed: " + Globals.global2);
                }
                if(event.getAction() == GLFW.GLFW_RELEASE){
                    System.out.println("I key released");
                }
            }

            if (O_KEY_BINDING.matches(event.getKey(), event.getScanCode())) {
                if (event.getAction() == GLFW.GLFW_PRESS) {
                    Globals.global3 += 0.2f; // Increment global2 by 0.2
                    System.out.println("O key pressed: " + Globals.global3);
                }
                if (event.getAction() == GLFW.GLFW_RELEASE) {
                    System.out.println("O key released");
                }
            }

        }
    }
}
