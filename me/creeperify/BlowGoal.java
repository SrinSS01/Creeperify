package me.creeperify;

import net.minecraft.server.v1_16_R2.EntityInsentient;
import net.minecraft.server.v1_16_R2.EntityLiving;
import net.minecraft.server.v1_16_R2.PathfinderGoal;

import java.util.EnumSet;

public class BlowGoal<T extends EntityInsentient & CreeperProperties> extends PathfinderGoal {
    private final T entity;
    private EntityLiving b;

    public BlowGoal(T entity) {
        this.entity = entity;
        this.a(EnumSet.of(Type.MOVE));
    }

    @Override
    public boolean a() {
        EntityLiving var0 = this.entity.getGoalTarget();
        return this.entity.getCreeperState() > 0 || var0 != null && this.entity.h(var0) < 9.0D;
    }
    public void c() {
        this.entity.getNavigation().o();
        this.b = this.entity.getGoalTarget();
    }
    public void d() {
        this.b = null;
    }
    public void e() {
        if (this.b == null) {
            this.entity.setCreeperState(-1);
        } else if (this.entity.h(this.b) > 49.0D) {
            this.entity.setCreeperState(-1);
        } else if (!this.entity.getEntitySenses().a(this.b)) {
            this.entity.setCreeperState(-1);
        } else {
            this.entity.setCreeperState(1);
        }
    }
}
