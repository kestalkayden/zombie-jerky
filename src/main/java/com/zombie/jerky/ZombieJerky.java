package com.zombie.jerky;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;

public class ZombieJerky implements ModInitializer {
	public static final String MOD_ID = "zombie-jerky";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Item ZOMBIE_JERKY_ITEM = new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(1).saturationModifier(0.5f).meat().snack().build()));
			
	@Override
	public void onInitialize() {
		LOGGER.info("Zombie Jerky Mod Initialized");

		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "zombie_jerky"), ZOMBIE_JERKY_ITEM);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> entries.add(ZOMBIE_JERKY_ITEM));
		
	}
}
