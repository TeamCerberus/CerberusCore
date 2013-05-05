package teamcerberus.cerberuscore;

import java.util.Map;

import teamcerberus.cerberuscore.asm.CerberusCoreAccessTransformer;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class CerberusCoreLoadingPlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getLibraryRequestClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		CerberusCoreAccessTransformer.addTransformerMap("cerberuscore_at.cfg");
		return new String[] {"teamcerberus.cerberuscore.CerberusCoreAccessTransformer"};
	}

	@Override
	public String getModContainerClass() {
		return "teamcerberus.cerberuscore.CerberusCoreModContainer";
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {

	}

}
