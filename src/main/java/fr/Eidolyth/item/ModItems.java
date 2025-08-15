package fr.Eidolyth.item;

import fr.Eidolyth.EidoPlant;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


/**
 * Registry class for all custom items in the 4Vegetation mod.
 * Utilizes DeferredRegister for thread-safe item registration with Forge.
 */
public class ModItems {
	/**
	 * DeferredRegister instance for registering items.
	 * Associates the items with the mod's unique identifier.
	 */
	public static final DeferredRegister<Item> ITEMS =
		DeferredRegister.create(ForgeRegistries.ITEMS, EidoPlant.MODID);

	// Plant-related items can be added here in the future if needed

	/**
	 * Registers all items with the provided event bus.
	 * Must be called during the mod's initialization phase.
	 *
	 * @param eventBus The mod's event bus to which items are registered.
	 */
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}

}
