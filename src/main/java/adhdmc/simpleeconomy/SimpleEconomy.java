package adhdmc.simpleeconomy;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleEconomy extends JavaPlugin {

    private static Economy economy;
    private static SimpleEconomy instance;
    private static MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public void onEnable() {
        RegisteredServiceProvider<Economy> rsp = this.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null) {
            economy = rsp.getProvider();
        } else {
            this.logger.severe("Something went wrong and SimpleEconomy was not able to connect to vault. Please make sure you have vault installed. Disabling SimpleEconomy");
            this.getServer().getPluginManager().disablePlugin(this);
        }
        instance = this;

    }

    public static Economy getEconomy() {
        return economy;
    }

    public static SimpleEconomy getInstance() {
        return instance;
    }

    public static MiniMessage getMiniMessage() {
        return miniMessage;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
