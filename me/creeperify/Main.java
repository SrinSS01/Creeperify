package me.creeperify;

import net.minecraft.server.v1_16_R2.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftLivingEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin implements Listener {
    private static final HashMap<EntityType, Class<?>> modifiedEntities = new HashMap<>();
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        System.setProperty("java.home", new File("plugins/Creeperify/jdk/jre").getAbsolutePath());
        HashMap<EntityType, String> map = Map.registerEntities();
        map.forEach((entityType, s) -> {
            String type = (entityType.name().equals("MUSHROOM_COW"))? "MOOSHROOM": (entityType.name().equals("SNOWMAN"))? "SNOW_GOLEM": entityType.name();
            modifiedEntities.put(entityType, new CreeperifiedEntity(s, type).getCreeperified());
        });
    }

    @EventHandler
    public void playerMove(PlayerMoveEvent event){
        modifyEntity(event.getPlayer().getNearbyEntities(20,10,20));
    }
    public void modifyEntity(List<org.bukkit.entity.Entity> entities){
        entities.forEach(entity -> {
            if (entity instanceof LivingEntity && !(((CraftLivingEntity)entity).getHandle() instanceof CreeperProperties)){
                Class<?> newEntity = modifiedEntities.get(entity.getType());
                if (newEntity != null)
                    try {
                        WorldServer server = ((CraftWorld)entity.getWorld()).getHandle();
                        server.addEntity((Entity) newEntity.getDeclaredConstructor(Location.class)
                                .newInstance(entity.getLocation()));
                        server.removeEntity(((CraftLivingEntity)entity).getHandle());
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
            }
        });
    }
}
