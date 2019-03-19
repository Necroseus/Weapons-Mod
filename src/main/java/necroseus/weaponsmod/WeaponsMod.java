package necroseus.weaponsmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import necroseus.weaponsmod.items.MyItemSword;
import necroseus.weaponsmod.lists.ItemList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("wepmechanics")
public class WeaponsMod {
	public static WeaponsMod instance;	
	public static final String modid = "wepmechanics";
	private static final Logger logger = LogManager.getLogger(modid);
	
	public WeaponsMod() {
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		logger.info("Setup Method Registered.");
	}
	
	private void clientRegistries(final FMLClientSetupEvent event) {
		
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistyEvents {
		@SubscribeEvent
		public static void RegisterItems(final RegistryEvent.Register<Item> event) {
			event.getRegistry().registerAll(
					ItemList.diamond_lance = new Item(new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(location("diamond_lance")),
					ItemList.diamond_pollaxe = new Item(new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(location("diamond_pollaxe")),
					ItemList.diamond_spear = new Item(new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(location("diamond_spear")),
					ItemList.diamond_dagger = new MyItemSword(null, 10, 2, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(location("diamond_dagger")),
					ItemList.tutorial_item = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("tutorial_item"))
			);
			
			logger.info("Items Registered.");
		}
		
		private static ResourceLocation location(String name) {
			return new ResourceLocation(modid, name);
		}
	}
}