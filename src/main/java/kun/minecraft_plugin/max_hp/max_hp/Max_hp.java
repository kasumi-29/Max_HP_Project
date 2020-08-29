package kun.minecraft_plugin.max_hp.max_hp;

import org.bukkit.Server;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;


public final class Max_hp extends JavaPlugin {
    private int DF_HP=20;
    private HashMap<UUID,Integer> Player_HP;

    @Override
    public void onEnable() {
        Player_HP=new HashMap<>();
        saveDefaultConfig();
        DF_HP=getConfig().getInt("default");
        CONF_read();

        getServer().getPluginManager().registerEvents(new ERHE(this), this);
        Objects.requireNonNull(getCommand("super-effect")).setTabCompleter((sender, command, alias, args) -> new ArrayList<>());
        Objects.requireNonNull(getCommand("set-hp")).setTabCompleter((sender, command, alias, args) -> {
            if(args.length<=1){
                return null;
            }
            return new ArrayList<>();
        });
        Objects.requireNonNull(getCommand("set-hp")).setExecutor((sender, command, label, args) -> {
            if(args.length!=2){return false;}
            Player player=getServer().getPlayer(args[0]);
            int hp;
            try{
                hp=new Integer(args[1]);
            }catch (NumberFormatException e){
                sender.sendMessage("[!!!ERR!!!]HPの値が不正です。");
                return false;
            }
            if(player==null){
                sender.sendMessage("[!!!ERR!!!]現在接続中のプレイヤーとして、認識できませんでした。");
                sender.sendMessage("[!!!ERR!!!]オフラインプレイヤーについてHPを操作するには、config.ymlで指定してください。");
                return false;
            }else if(hp<1){
                sender.sendMessage("[!!!ERR!!!]HPの値が不正です。");
                return false;
            }
            setHP(player,hp);
            Player_HP.put(player.getUniqueId(),hp);
            return true;
        });
        Objects.requireNonNull(getCommand("set-default-hp")).setTabCompleter((sender, command, alias, args) -> new ArrayList<>());
        Objects.requireNonNull(getCommand("set-default-hp")).setExecutor((sender, command, label, args) ->{
            if(args.length!=1){return false;}
            int hp;
            try{
                hp=new Integer(args[0]);
            }catch (NumberFormatException e){
                sender.sendMessage("[!!!ERR!!!]HPの値が不正です。");
                return false;
            }
            DF_HP=hp;
            getConfig().set("default",hp);
            saveConfig();

            for (Player p: getServer().getOnlinePlayers()) {
                if(Player_HP.containsKey(p.getUniqueId())){continue;}
                setHP(p,hp);
            }
            return true;
        });

        getLogger().info("読み込み完了");
    }

    @Override
    public void onDisable(){
        CONF_write();
    }

    private void CONF_read(){
        ConfigurationSection conf=getConfig().getConfigurationSection("Player");
        assert conf != null;
        for (String Pname:conf.getKeys(false)){
            String read_uid=conf.getString(Pname+".UUID");
            int hp=conf.getInt(Pname+".HP");
            if((read_uid==null)||(hp<=0)){continue;}
            UUID id=UUID.fromString(read_uid);
            Player_HP.put(id,hp);
        }
    }
    private void CONF_write(){
        ConfigurationSection conf=getConfig().getConfigurationSection("Player");
        assert conf != null;
        for (Map.Entry<UUID,Integer> Pdata:Player_HP.entrySet()){
            String Pname= getServer().getOfflinePlayer(Pdata.getKey()).getName();
            conf.set(Pname+".UUID",Pdata.getKey().toString());
            conf.set(Pname+".HP",Pdata.getValue());
        }
        saveConfig();
    }

    public void setHP(Player player){
        setHP(player,DF_HP);
    }
    public void setHP(Player player, int hp){
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(hp);
    }
}
