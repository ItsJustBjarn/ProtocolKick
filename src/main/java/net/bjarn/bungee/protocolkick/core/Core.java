package net.bjarn.bungee.protocolkick.core;

import com.google.common.io.ByteStreams;

import net.bjarn.bungee.protocolkick.listeners.LoginListener;

import net.bjarn.bungee.protocolkick.listeners.PingListener;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;

public class Core extends Plugin implements Listener {

    public Configuration config;

    public String kickMessage = "";
    public String protocolText = "";
    public String motd = "";
    public Boolean protocolBoolean = true;
    public Boolean motdBoolean = true;
    public Boolean kickBoolean = true;
    public int protocol = 47;

    public void onEnable() {

        saveDefaultConfig();

        getProxy().getPluginManager().registerListener(this, new LoginListener(this));
        getProxy().getPluginManager().registerListener(this, new PingListener(this));

        try {

            this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.getConfig());

            this.protocol = config.getInt("protocolnumber", 0);
            this.kickBoolean = config.getBoolean("settings.toggle.kick");
            this.motdBoolean = config.getBoolean("settings.toggle.motd");
            this.protocolBoolean = config.getBoolean("settings.toggle.protocoltext");
            this.motd = config.getString("settings.messages.motd");
            this.protocolText = config.getString("settings.messages.protocoltext");
            this.kickMessage = config.getString("settings.messages.kick");

        }catch(IOException e){

            e.printStackTrace();

        }
    }

    private File getConfig(){

        return new File(this.getDataFolder() + File.separator + "config.properties");

    }

    private void saveDefaultConfig(){

        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }

        File configFile = new File(this.getDataFolder(), "config.properties");
        if (!configFile.exists()) {
            try {
                if(configFile.createNewFile()) {

                    try (InputStream is = this.getClass().getResourceAsStream("/config.properties");
                         OutputStream os = new FileOutputStream(configFile)) {
                        ByteStreams.copy(is, os);
                    }

                }
                else {

                    this.getLogger().severe("Failed to create the configuration file!");

                }

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}