package teamcerberus.cerberuscore.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityCommandBlock;
import teamcerberus.cerberuscore.util.NetworkUtil;
import teamcerberus.cerberuscore.util.ServerUtil;

public abstract class CerberusCommand extends CommandBase {
	public ArrayList<String>	aliasList	= new ArrayList<String>();

	@Override
	public List<String> getCommandAliases() {
		return aliasList;
	}

	public void addAliase(String s) {
		aliasList.add(s);
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if (var1 instanceof EntityPlayer) {
			processCommandPlayer((EntityPlayer) var1, var2);
		} else if (var1 instanceof TileEntityCommandBlock) {
			processCommandBlock((TileEntityCommandBlock) var1, var2);
		} else {
			processCommandConsole(var1, var2);
		}
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		String usage;
		try {
			usage = "/" + getCommandName() + " " + getSyntax(sender) + " "
					+ getInfo(sender);
		} catch (NullPointerException e) {
			usage = "Not usable by player";
		}
		return usage;

	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		if (sender instanceof EntityPlayer) return canPlayerUseCommand((EntityPlayer) sender);
		else if (sender instanceof TileEntityCommandBlock) return canCommandBlockUseCommand((TileEntityCommandBlock) sender);
		else return canConsoleUseCommand();
	}

	public List<?> addTabCompletionOptions(ICommandSender sender, String[] args) {
		if (args.length == 0) {
			return getListOfStringsFromIterableMatchingLastWord(args,
					ServerUtil.getServerInstance().getCommandManager()
							.getPossibleCommands(sender));
		} else {
			return getListOfStringsMatchingLastWord(args, ServerUtil
					.getServerInstance().getAllUsernames());
		}
	}

	public abstract String getSyntax(ICommandSender sender);

	public abstract String getInfo(ICommandSender sender);

	public abstract boolean canPlayerUseCommand(EntityPlayer player);

	public abstract boolean canCommandBlockUseCommand(
			TileEntityCommandBlock block);

	public abstract boolean canConsoleUseCommand();

	public abstract void processCommandPlayer(EntityPlayer sender, String[] args);

	public abstract void processCommandBlock(TileEntityCommandBlock block,
			String[] args);

	public abstract void processCommandConsole(ICommandSender sender,
			String[] args);
}