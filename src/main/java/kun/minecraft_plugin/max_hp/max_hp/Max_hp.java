package kun.minecraft_plugin.max_hp.max_hp;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Max_hp extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Objects.requireNonNull(getCommand("set-safezone")).setExecutor((sender, command, label, args) -> {
            if(!(sender instanceof Player)){
                sender.sendMessage("[!!!ERR!!!]プレイヤーが実行してください");
                return false;
            }
            setHP((Player) sender,4);
            return true;
        });

        getLogger().info("読み込み完了");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void setHP(Player player, int hp){
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(hp);
    }
}
