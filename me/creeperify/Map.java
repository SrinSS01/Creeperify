package me.creeperify;

import org.bukkit.entity.EntityType;

import java.util.HashMap;

public class Map {
    public static HashMap<EntityType,String> registerEntities() {
        HashMap<EntityType, String> map = new HashMap<>();
        map.put(EntityType.BAT, "EntityBat");
        map.put(EntityType.BEE, "EntityBee");
        map.put(EntityType.BLAZE, "EntityBlaze");
        map.put(EntityType.CAT, "EntityCat");
        map.put(EntityType.CAVE_SPIDER, "EntityCaveSpider");
        map.put(EntityType.CHICKEN, "EntityChicken");
        map.put(EntityType.COD, "EntityCod");
        map.put(EntityType.COW, "EntityCow");
        map.put(EntityType.CREEPER, "EntityCreeper");
        map.put(EntityType.DONKEY, "EntityHorseDonkey");
        map.put(EntityType.DOLPHIN, "EntityDolphin");
        map.put(EntityType.DROWNED, "EntityDrowned");
        map.put(EntityType.ELDER_GUARDIAN, "EntityGuardianElder");
        map.put(EntityType.ENDER_DRAGON, "EntityEnderDragon");
        map.put(EntityType.ENDERMAN, "EntityEnderman");
        map.put(EntityType.ENDERMITE, "EntityEndermite");
        map.put(EntityType.EVOKER, "EntityEvoker");
        map.put(EntityType.FOX, "EntityFox");
        map.put(EntityType.GHAST, "EntityGhast");
        map.put(EntityType.GIANT, "EntityGiantZombie");
        map.put(EntityType.GUARDIAN, "EntityGuardian");
        map.put(EntityType.HOGLIN, "EntityHoglin");
        map.put(EntityType.HORSE, "EntityHorse");
        map.put(EntityType.HUSK, "EntityZombieHusk");
        map.put(EntityType.ILLUSIONER, "EntityIllagerIllusioner");
        map.put(EntityType.IRON_GOLEM, "EntityIronGolem");
        map.put(EntityType.LLAMA, "EntityLlama");
        map.put(EntityType.MULE, "EntityHorseMule");
        map.put(EntityType.MUSHROOM_COW, "EntityMushroomCow");//MOOSHROOM
        map.put(EntityType.OCELOT, "EntityOcelot");
        map.put(EntityType.PANDA, "EntityPanda");
        map.put(EntityType.PARROT, "EntityParrot");
        map.put(EntityType.PHANTOM, "EntityPhantom");
        map.put(EntityType.PIG, "EntityPig");
        map.put(EntityType.PIGLIN, "EntityPiglin");
        map.put(EntityType.PIGLIN_BRUTE, "EntityPiglinBrute");
        map.put(EntityType.PILLAGER, "EntityPillager");
        map.put(EntityType.POLAR_BEAR, "EntityPolarBear");
        map.put(EntityType.PUFFERFISH, "EntityPufferFish");
        map.put(EntityType.RABBIT, "EntityRabbit");
        map.put(EntityType.RAVAGER, "EntityRavager");
        map.put(EntityType.SALMON, "EntitySalmon");
        map.put(EntityType.SHEEP, "EntitySheep");
        map.put(EntityType.SHULKER, "EntityShulker");
        map.put(EntityType.SILVERFISH, "EntitySilverfish");
        map.put(EntityType.SKELETON, "EntitySkeleton");
        map.put(EntityType.SKELETON_HORSE, "EntityHorseSkeleton");
        map.put(EntityType.SLIME, "EntitySlime");
        map.put(EntityType.SNOWMAN, "EntitySnowman");//SNOW_GOLEM
        map.put(EntityType.SPIDER, "EntitySpider");
        map.put(EntityType.SQUID, "EntitySquid");
        map.put(EntityType.STRAY, "EntitySkeletonStray");
        map.put(EntityType.STRIDER, "EntityStrider");
        map.put(EntityType.TRADER_LLAMA, "EntityLlamaTrader");
        map.put(EntityType.TROPICAL_FISH, "EntityTropicalFish");
        map.put(EntityType.TURTLE, "EntityTurtle");
        map.put(EntityType.VEX, "EntityVex");
        map.put(EntityType.VILLAGER, "EntityVillager");
        map.put(EntityType.VINDICATOR, "EntityVindicator");
        map.put(EntityType.WANDERING_TRADER, "EntityVillagerTrader");
        map.put(EntityType.WITCH, "EntityWitch");
        map.put(EntityType.WITHER, "EntityWither");
        map.put(EntityType.WITHER_SKELETON, "EntitySkeletonWither");
        map.put(EntityType.WOLF, "EntityWolf");
        map.put(EntityType.ZOGLIN, "EntityZoglin");
        map.put(EntityType.ZOMBIE, "EntityZombie");
        map.put(EntityType.ZOMBIE_HORSE, "EntityHorseZombie");
        map.put(EntityType.ZOMBIE_VILLAGER, "EntityZombieVillager");
        map.put(EntityType.ZOMBIFIED_PIGLIN, "EntityPigZombie");
        return map;
    }
}
