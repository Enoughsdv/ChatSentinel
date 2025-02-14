package dev._2lstudios.chatsentinel.bukkit.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dev._2lstudios.chatsentinel.bukkit.modules.ModuleManager;
import dev._2lstudios.chatsentinel.shared.chat.ChatPlayer;
import dev._2lstudios.chatsentinel.shared.chat.ChatPlayerManager;
import dev._2lstudios.chatsentinel.shared.modules.WhitelistModule;

public class PlayerJoinListener implements Listener {
	final private WhitelistModule whitelistModule;
	final private ChatPlayerManager chatPlayerManager;

	public PlayerJoinListener(final ModuleManager moduleManager, final ChatPlayerManager chatPlayerManager) {
		this.whitelistModule = moduleManager.getWhitelistModule();
		this.chatPlayerManager = chatPlayerManager;
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerJoin(final PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		final ChatPlayer chatPlayer = chatPlayerManager.getPlayer(player.getUniqueId());

		chatPlayerManager.setOnline(chatPlayer);

		if (whitelistModule.isEnabled())
			whitelistModule.addName(player.getName());
	}
}
