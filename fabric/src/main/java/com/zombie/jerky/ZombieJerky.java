package com.zombie.jerky;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZombieJerky implements ModInitializer {
	public static final String MOD_ID = "zombie_jerky";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Item ZOMBIE_JERKY_ITEM;

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Zombie Jerky");

		Identifier itemId = Identifier.fromNamespaceAndPath(MOD_ID, "zombie_jerky");
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, itemId);

		Item.Properties properties = new Item.Properties()
				.setId(itemKey)
				.food(new FoodProperties.Builder()
						.nutrition(1)
						.saturationModifier(0.5f)
						.alwaysEdible()
						.build());

		ZOMBIE_JERKY_ITEM = Registry.register(
				BuiltInRegistries.ITEM,
				itemId,
				new Item(properties)
		);

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
				.register(output -> output.accept(new ItemStack(ZOMBIE_JERKY_ITEM)));
	}
}
