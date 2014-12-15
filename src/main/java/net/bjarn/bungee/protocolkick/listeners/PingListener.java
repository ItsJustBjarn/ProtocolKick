package net.bjarn.bungee.protocolkick.listeners;

import net.bjarn.bungee.protocolkick.core.Core;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PingListener implements Listener {

    Core plugin;

    public PingListener(Core instance)
    {
        this.plugin = instance;
    }

    @EventHandler
    public void onPing(ProxyPingEvent event) {

        ServerPing sp = event.getResponse();
        System.out.println(sp.getVersion() + " - " + plugin.protocol);
        if(event.getConnection().getVersion() < plugin.protocol) {
            if(plugin.protocolBoolean) {
                sp.setVersion(new ServerPing.Protocol(ChatColor.translateAlternateColorCodes('&', plugin.protocolText), plugin.protocol));
            }
            if(plugin.motdBoolean) {
                sp.setDescription(ChatColor.translateAlternateColorCodes('&', plugin.motd).replace("#newline", "\n"));
            }

            return;
        }

    }

}
