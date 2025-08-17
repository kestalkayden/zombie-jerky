package com.zombie.jerky;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.consume.UseAction;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZombieJerky implements ModInitializer {
	public static final String MOD_ID = "zombie-jerky";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Item ZOMBIE_JERKY_ITEM;

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Zombie Jerky");

		// Create the registry key and settings
		Identifier itemId = Identifier.of(MOD_ID, "zombie_jerky");
		RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, itemId);
		
		Item.Settings settings = new Item.Settings()
				.registryKey(itemKey)
				.component(DataComponentTypes.FOOD, new FoodComponent(
						1,        // nutrition
						0.5f,     // saturation
						true      // always edible
				))
				.component(DataComponentTypes.CONSUMABLE,
						new ConsumableComponent(
								0.8f,                      // consumeSeconds: fast
								UseAction.EAT,              // animation
								SoundEvents.ENTITY_GENERIC_EAT, // RegistryEntry<SoundEvent>
								true,                       // has consume particles
								List.of()                   // no effects
						)
				);

		ZOMBIE_JERKY_ITEM = Registry.register(
				Registries.ITEM,
				itemId,
				new Item(settings)
		);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
				.register(entries -> entries.add(ZOMBIE_JERKY_ITEM));
	}
}