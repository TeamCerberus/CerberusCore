package teamcerberus.cerberuscore;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLCallHook;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

//@IFMLLoadingPlugin.MCVersion("1.5.2")
public class CerberusCoreLoadingPlugin implements IFMLLoadingPlugin,
		IFMLCallHook {

	@Override
	public String[] getLibraryRequestClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		// CerberusCoreAccessTransformer.addTransformerMap("cerberuscore_at.cfg");
		return new String[] { "teamcerberus.cerberuscore.CerberusCoreAccessTransformer" };
	}

	@Override
	public String getModContainerClass() {
		return "teamcerberus.cerberuscore.CerberusCoreModContainer";
	}

	@Override
	public String getSetupClass() {
		return "teamcerberus.cerberuscore.CerberusCoreLoadingPlugin";
	}

	@Override
	public void injectData(Map<String, Object> data) {

	}

	@Override
	public Void call() throws Exception {
		CerberusCore.preMCInit();
		return null;
	}

}