package com.almightyalpaca.discord.bot.plugin.permission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.almightyalpaca.discord.bot.system.command.AbstractCommand;
import com.almightyalpaca.discord.bot.system.config.Config;
import com.almightyalpaca.discord.bot.system.events.PermissionEvent;
import com.almightyalpaca.discord.bot.system.events.manager.EventHandler;
import com.almightyalpaca.discord.bot.system.exception.PluginLoadingException;
import com.almightyalpaca.discord.bot.system.exception.PluginUnloadingException;
import com.almightyalpaca.discord.bot.system.plugins.Plugin;
import com.almightyalpaca.discord.bot.system.plugins.PluginInfo;

public class PermissionPlugin extends Plugin {

	public class PermissionCommand extends AbstractCommand {

		public PermissionCommand() {
			super("perms", "Change Permissions", "[global/guild] [command] [allow/deny] [...]");
		}

//		@Command(dm = true, guild = true, priority = 1, async = true)
//		public void onCommand(final CommandEvent event, Guild guild, String command, String scope, User user) {
//			if (scope.equalsIgnoreCase("allow")) {
//
//			} else if (scope.equalsIgnoreCase("deny")) {
//
//			}
//		}

	}

	private static final PluginInfo		INFO	= new PluginInfo("com.almightyalpaca.discord.bot.plugin.permission", "1.0.0", "Almighty Alpaca", "Permission Plugin", "Permissions everywhere!");
	private static final List<String>	ops;
	static {
		final List<String> temp = new ArrayList<>();

		temp.add("107490111414882304");// Almighty Alpaca
		temp.add("107562988810027008");// DV8FromTheWorld
		temp.add("122758889815932930");// Kantenkugel

		ops = Collections.unmodifiableList(temp);
	}

	private Config	perms;

	private Config	globalPerms;

	private Config	guildsPerms;

	public PermissionPlugin() {
		super(PermissionPlugin.INFO);
	}

	@Override
	public void load() throws PluginLoadingException {
		this.perms = this.getPluginConfig().getOrCreateConfig("permissions");
		this.globalPerms = this.perms.getOrCreateConfig("global");
		this.guildsPerms = this.perms.getOrCreateConfig("guilds");

		this.registerEventHandler(this);
		this.registerCommand(new PermissionCommand());
	}

	@EventHandler
	public void onPermissionEvent(final PermissionEvent event) {
		if (!PermissionPlugin.ops.contains(event.getAuthor().getId())) {
			if (event.getCommandName().equalsIgnoreCase("plugins")) {
				event.getChannel().sendMessage("Access denied!");
				event.setResult(false);
			} else if (event.getCommandName().equalsIgnoreCase("eval")) {
				event.getChannel().sendMessage("Access denied!");
				event.setResult(false);
			} else if (event.getCommandName().equalsIgnoreCase("shutdown")) {
				event.getChannel().sendMessage("Access denied!");
				event.setResult(false);
			} else if (event.getCommandName().equalsIgnoreCase("clear")) {
				event.getChannel().sendMessage("Access denied!");
				event.setResult(false);
			}
		}
	}

//	public void GuildAllowUser(Guild guild, String command, User user) {
//
//	}

//	public enum Scope {
//		ALLOW, DENY;
//	}

	@Override
	public void unload() throws PluginUnloadingException {}

}
