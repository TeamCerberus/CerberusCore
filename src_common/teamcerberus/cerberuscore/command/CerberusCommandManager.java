package teamcerberus.cerberuscore.command;

import java.util.HashMap;

import net.minecraft.command.CommandHandler;
import teamcerberus.cerberuscore.util.ServerUtil;

public class CerberusCommandManager {
	private static CerberusCommandManager		instance;
	private HashMap<String, CerberusCommand>	commands;

	public CerberusCommandManager() {
		commands = new HashMap<String, CerberusCommand>();
	}

	public void registerCommand(CerberusCommand command) {
		((CommandHandler) ServerUtil.getServerInstance().getCommandManager())
				.registerCommand(command);
		commands.put(command.getCommandName(), command);
	}

	public static void init() {
		instance = new CerberusCommandManager();
	}

	public static CerberusCommandManager getInstance() {
		if (instance == null) init();
		return instance;
	}
}
