package teamcerberus.cerberuscore;

import java.util.Arrays;

import net.minecraftforge.common.MinecraftForge;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CerberusCoreModContainer extends DummyModContainer {
	public CerberusCoreModContainer() {
		super(new ModMetadata());
		ModMetadata myMeta = super.getMetadata();
		myMeta.authorList = Arrays.asList(new String[] { "csnewman",
				"cazzar" });
		myMeta.description = "A universal core.";
		myMeta.modId = CerberusCore.id;
		myMeta.version = CerberusCore.version;
		myMeta.name = CerberusCore.id;
		MinecraftForge.EVENT_BUS.register(new CerberusCoreSubcriber());
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}

	@Subscribe
	public void init(FMLInitializationEvent ev) {
		CerberusCore.init();
	}
	
	@Subscribe
	public void preInit(FMLPreInitializationEvent ev) {
		CerberusCore.preInit(ev);
	}
}