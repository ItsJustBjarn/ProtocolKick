package net.bjarn.bungee.protocolkick.listeners;

import net.bjarn.bungee.protocolkick.core.Core;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LoginListener implements Listener {

    Core plugin;

    public LoginListener(Core instance)
    {
        this.plugin = instance;
    }

    @EventHandler
    public void onLoginEvent(PreLoginEvent event) {

        if (event.getConnection().getVersion() < plugin.protocol) {

            if(plugin.kickBoolean) {
                event.setCancelReason(ChatColor.translateAlternateColorCodes('&', plugin.kickMessage).replace("#newline", "\n"));
                event.setCancelled(true);
            }

        }

    }

}
