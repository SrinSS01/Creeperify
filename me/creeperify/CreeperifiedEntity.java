package me.creeperify;

import org.joor.Reflect;


public class CreeperifiedEntity {
    private final String EntityName;
    private final String EntityTypeName;
    private final String className;

    public CreeperifiedEntity(String entityName, String entityTypeName) {
        this.EntityName = entityName;
        this.EntityTypeName = entityTypeName;
        this.className = entityName.toLowerCase();
    }
    public Class<?> getCreeperified(){
        return Reflect.compile(
                "me.creeperify."+this.className,
                "package me.creeperify;\n" +
                        "\n" +
                        "import net.minecraft.server.v1_16_R2.*;\n" +
                        "import org.bukkit.Location;\n" +
                        "import org.bukkit.World;\n" +
                        "import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;\n" +
                        "import org.bukkit.event.entity.CreatureSpawnEvent;\n" +
                        "import org.bukkit.event.entity.ExplosionPrimeEvent;\n" +
                        "\n" +
                        "import java.util.Collection;\n" +
                        "import java.util.Objects;\n" +
                        "\n" +
                        "public class "+this.className+" extends "+this.EntityName+" implements CreeperProperties{\n" +
                        "    private static final DataWatcherObject<Integer> STATE = DataWatcher.a("+this.className+".class, DataWatcherRegistry.b);\n" +
                        "    private static final DataWatcherObject<Boolean> IGNITED = DataWatcher.a("+this.className+".class, DataWatcherRegistry.i);\n" +
                        "    private int lastActiveTime;\n" +
                        "    private int timeSinceIgnited;\n" +
                        "    private int fuseTime = 30;\n" +
                        "    private int explosionRadius = 3;\n" +
                        "    public "+this.className+"(Location location) {\n" +
                        "        super(EntityTypes."+this.EntityTypeName+", ((CraftWorld) Objects.requireNonNull(location.getWorld())).getHandle());\n" +
                        "        this.setPosition(location.getX(), location.getY(), location.getZ());\n" +
                        "    }\n" +
                        "    @Override\n" +
                        "    public void initPathfinder() {\n" +
                        "        super.initPathfinder();\n" +
                        "        this.goalSelector.a(0, new BlowGoal(this));\n" +
                        "        this.goalSelector.a(0, new PathfinderGoalNearestAttackableTarget<>(this, EntityPlayer.class,true));\n" +
                        "        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public void initDatawatcher() {\n" +
                        "        super.initDatawatcher();\n" +
                        "        this.datawatcher.register(STATE, -1);\n" +
                        "        this.datawatcher.register(IGNITED, false);\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public void saveData(NBTTagCompound nbttagcompound) {\n" +
                        "        super.saveData(nbttagcompound);\n" +
                        "        nbttagcompound.setShort(\"Fuse\", (short)this.fuseTime);\n" +
                        "        nbttagcompound.setByte(\"ExplosionRadius\", (byte)this.explosionRadius);\n" +
                        "        nbttagcompound.setBoolean(\"ignited\", this.isIgnited());\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public void loadData(NBTTagCompound nbttagcompound) {\n" +
                        "        super.loadData(nbttagcompound);\n" +
                        "        if (nbttagcompound.hasKeyOfType(\"Fuse\", 99)) {\n" +
                        "            this.fuseTime = nbttagcompound.getShort(\"Fuse\");\n" +
                        "        }\n" +
                        "\n" +
                        "        if (nbttagcompound.hasKeyOfType(\"ExplosionRadius\", 99)) {\n" +
                        "            this.explosionRadius = nbttagcompound.getByte(\"ExplosionRadius\");\n" +
                        "        }\n" +
                        "\n" +
                        "        if (nbttagcompound.getBoolean(\"ignited\")) {\n" +
                        "            this.ignite();\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public void tick() {\n" +
                        "        if (this.isAlive()) {\n" +
                        "            this.lastActiveTime = this.timeSinceIgnited;\n" +
                        "            if (this.isIgnited()) {\n" +
                        "                this.setCreeperState(1);\n" +
                        "            }\n" +
                        "\n" +
                        "            int i = this.getCreeperState();\n" +
                        "            if (i > 0 && this.timeSinceIgnited == 0) {\n" +
                        "                this.playSound(SoundEffects.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);\n" +
                        "            }\n" +
                        "\n" +
                        "            this.timeSinceIgnited += i;\n" +
                        "            if (this.timeSinceIgnited < 0) {\n" +
                        "                this.timeSinceIgnited = 0;\n" +
                        "            }\n" +
                        "\n" +
                        "            if (this.timeSinceIgnited >= this.fuseTime) {\n" +
                        "                this.timeSinceIgnited = this.fuseTime;\n" +
                        "                this.explode();\n" +
                        "            }\n" +
                        "        }\n" +
                        "        super.tick();\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {\n" +
                        "        ItemStack itemstack = entityhuman.b(enumhand);\n" +
                        "        if (itemstack.getItem() == Items.FLINT_AND_STEEL) {\n" +
                        "            this.world.playSound(entityhuman, this.locX(), this.locY(), this.locZ(), SoundEffects.ITEM_FLINTANDSTEEL_USE, this.getSoundCategory(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);\n" +
                        "            if (!this.world.isClientSide) {\n" +
                        "                this.ignite();\n" +
                        "                itemstack.damage(1, entityhuman, (entityhuman1) -> {\n" +
                        "                    entityhuman1.broadcastItemBreak(enumhand);\n" +
                        "                });\n" +
                        "            }\n" +
                        "            return EnumInteractionResult.a(this.world.isClientSide);\n" +
                        "        } else {\n" +
                        "            return super.b(entityhuman, enumhand);\n" +
                        "        }\n" +
                        "    }" +
                        "    @Override\n" +
                        "    public void explode() {\n" +
                        "        if (!this.world.isClientSide) {\n" +
                        "            Explosion.Effect explosion_effect = this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) ? Explosion.Effect.DESTROY : Explosion.Effect.NONE;\n" +
                        "            float f = 1.0F;\n" +
                        "            ExplosionPrimeEvent event = new ExplosionPrimeEvent(this.getBukkitEntity(), (float)this.explosionRadius * f, false);\n" +
                        "            this.world.getServer().getPluginManager().callEvent(event);\n" +
                        "            if (!event.isCancelled()) {\n" +
                        "                this.killed = true;\n" +
                        "                this.world.createExplosion(this, this.locX(), this.locY(), this.locZ(), event.getRadius(), event.getFire(), explosion_effect);\n" +
                        "                this.die();\n" +
                        "                this.createEffectCloud();\n" +
                        "            } else {\n" +
                        "                this.timeSinceIgnited = 0;\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public void createEffectCloud() {\n" +
                        "        Collection<MobEffect> collection = this.getEffects();\n" +
                        "        if (!collection.isEmpty()) {\n" +
                        "            EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(this.world, this.locX(), this.locY(), this.locZ());\n" +
                        "            entityareaeffectcloud.setSource(this);\n" +
                        "            entityareaeffectcloud.setRadius(2.5F);\n" +
                        "            entityareaeffectcloud.setRadiusOnUse(-0.5F);\n" +
                        "            entityareaeffectcloud.setWaitTime(10);\n" +
                        "            entityareaeffectcloud.setDuration(entityareaeffectcloud.getDuration() / 2);\n" +
                        "            entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());\n" +
                        "\n" +
                        "            for (MobEffect mobeffect : collection) {\n" +
                        "                entityareaeffectcloud.addEffect(new MobEffect(mobeffect));\n" +
                        "            }\n" +
                        "\n" +
                        "            this.world.addEntity(entityareaeffectcloud, CreatureSpawnEvent.SpawnReason.EXPLOSION);\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public void ignite() {\n" +
                        "        this.datawatcher.set(IGNITED, true);\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public boolean isIgnited() {\n" +
                        "        return this.datawatcher.get(IGNITED);\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public boolean attackEntity(Entity entity) {\n" +
                        "        return true;\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public int getCreeperState() {\n" +
                        "        return this.datawatcher.get(STATE);\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public void setCreeperState(int i) {\n" +
                        "        this.datawatcher.set(STATE, i);\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "}\n"
        ).get();
    }
}
