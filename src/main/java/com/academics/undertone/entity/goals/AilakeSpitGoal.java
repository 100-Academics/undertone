package com.academics.undertone.entity.goals;

import com.academics.undertone.entity.custom.AilakeEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class AilakeSpitGoal extends Goal {
    // Spit animation apex is around 0.2917s in a 0.5s clip => ~6 ticks.
    private static final int WINDUP_TICKS = 6;
    private static final int BASE_COOLDOWN_TICKS = 22;

    private final AilakeEntity ailake;
    private final double speedModifier;
    private final float maxAttackDistanceSqr;
    private final float minAttackDistanceSqr;

    private int cooldownTicks;
    private int attackWarmupTicks = -1;

    public AilakeSpitGoal(AilakeEntity ailake, double speedModifier, float maxAttackDistance, float minAttackDistance) {
        this.ailake = ailake;
        this.speedModifier = speedModifier;
        this.maxAttackDistanceSqr = maxAttackDistance * maxAttackDistance;
        this.minAttackDistanceSqr = minAttackDistance * minAttackDistance;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.ailake.getTarget();
        if (target == null || !target.isAlive()) {
            return false;
        }

        double distanceSqr = this.ailake.distanceToSqr(target);
        return distanceSqr <= this.maxAttackDistanceSqr && distanceSqr >= this.minAttackDistanceSqr;
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = this.ailake.getTarget();
        return target != null && target.isAlive() && this.ailake.distanceToSqr(target) <= this.maxAttackDistanceSqr * 1.25F;
    }

    @Override
    public void start() {
        this.ailake.setAggressive(true);
    }

    @Override
    public void stop() {
        this.ailake.setAggressive(false);
        this.attackWarmupTicks = -1;
    }

    @Override
    public void tick() {
        LivingEntity target = this.ailake.getTarget();
        if (target == null) {
            return;
        }

        this.ailake.getLookControl().setLookAt(target, 30.0F, 30.0F);
        double distanceSqr = this.ailake.distanceToSqr(target);
        boolean hasLineOfSight = this.ailake.getSensing().hasLineOfSight(target);

        if (distanceSqr < this.minAttackDistanceSqr || distanceSqr > this.maxAttackDistanceSqr || !hasLineOfSight) {
            this.ailake.getNavigation().moveTo(target, this.speedModifier);
            return;
        }

        this.ailake.getNavigation().stop();

        if (this.cooldownTicks > 0) {
            this.cooldownTicks--;
        }

        if (this.attackWarmupTicks < 0 && this.cooldownTicks <= 0) {
            this.attackWarmupTicks = WINDUP_TICKS;
            this.ailake.triggerSpitAnimation();
            return;
        }

        if (this.attackWarmupTicks < 0) {
            return;
        }

        if (--this.attackWarmupTicks > 0) {
            return;
        }

        this.performSpit(target);
        this.attackWarmupTicks = -1;
        this.cooldownTicks = BASE_COOLDOWN_TICKS + this.ailake.getRandom().nextInt(14);
    }

    private void performSpit(LivingEntity target) {
        if (this.ailake.level().isClientSide || !target.isAlive() || !this.ailake.getSensing().hasLineOfSight(target)) {
            return;
        }

        Snowball snowball = new Snowball(this.ailake.level(), this.ailake);
        snowball.setPos(this.ailake.getX(), this.ailake.getEyeY() - 0.1D, this.ailake.getZ());

        Vec3 toTarget = target.getEyePosition().subtract(snowball.position());
        snowball.shoot(toTarget.x, toTarget.y, toTarget.z, 5F, 2.0F);

        this.ailake.level().addFreshEntity(snowball);
    }
}
