package kun.minecraft_plugin.max_hp.max_hp;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class ERHE implements Listener {
    private Max_hp main;
    public ERHE(Max_hp m){
        main=m;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        main.setHP(event.getPlayer());
    }

    @EventHandler
    public void onGetCommand(ServerCommandEvent event){
        if(event.getCommand().contains("effect give")&&event.getCommand().contains("health_boost")){
            event.setCancelled(true);
            event.getSender().sendMessage("[警告]このコマンドはバグが発生する可能性があるためキャンセルされました。");
            event.getSender().sendMessage("[警告]危険性を理解した上で、コマンドを実行するには /super-effect コマンドを実行してください。");
        }else if(event.getCommand().contains("super-effect")){
            event.setCommand(event.getCommand().replace("super-effect","effect"));
        }
    }
}
