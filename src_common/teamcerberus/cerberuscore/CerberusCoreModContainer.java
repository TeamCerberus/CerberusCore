package teamcerberus.cerberuscore;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class CerberusCoreModContainer extends DummyModContainer {
	public CerberusCoreModContainer() {
		super(new ModMetadata());
		ModMetadata myMeta = super.getMetadata();
		myMeta.authorList = Arrays.asList(new String[] { "newmangamers",
				"cazzar" });
		myMeta.description = "A universal core.";
		myMeta.modId = CerberusCore.id;
		myMeta.version = CerberusCore.version;
		myMeta.name = CerberusCore.id;
	}

	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}

	@Subscribe
	public void init(FMLInitializationEvent ev) {
		CerberusCore.init();
	}
}