package com.zombie.jerky;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ZombieJerky.MOD_ID)
public class ZombieJerky {
    public static final String MOD_ID = "zombie_jerky";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MOD_ID);

    // Helper function for resource keys
    private static ResourceKey<Item> zombieJerkyItemId(String name) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, name));
    }

    // NeoForge item registration pattern (following candy-dispenser)
    private static DeferredHolder<Item, Item> registerItem(String name, Function<Item.Properties, Item> constructor, Item.Properties props) {
        var propWithId = props.setId(zombieJerkyItemId(name));  // CRITICAL: Set ID before item creation!
        return ITEMS.register(name, () -> constructor.apply(propWithId));
    }

    public static final DeferredHolder<Item, Item> ZOMBIE_JERKY_ITEM = registerItem("zombie_jerky",
            Item::new,
            new Item.Properties()
                    .stacksTo(64)
                    .food(new FoodProperties.Builder()
                            .nutrition(1)        // nutrition
                            .saturationModifier(0.5f)     // saturation
                            .alwaysEdible()      // always edible
                            .build())
    );

    public ZombieJerky(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Initializing Zombie Jerky (NeoForge)");
        
        ITEMS.register(modEventBus);
        
        modEventBus.addListener(this::addCreative);
    }

    @SubscribeEvent
    public void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ZOMBIE_JERKY_ITEM.get());
        }
    }
}