package com.academics.undertone.entity.goals;

import com.academics.undertone.entity.custom.AilakeEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class AilakeBiteGoal extends MeleeAttackGoal {
    // Bite animation apex is around 0.1736s in a 0.4167s clip => ~4 ticks.
    private static final int DAMAGE_DELAY_TICKS = 4;

    private final AilakeEntity ailake;
    private int ticksUntilDamage = -1;
    private LivingEntity pendingTarget;

    public AilakeBiteGoal(AilakeEntity ailake, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(ailake, speedModifier, followingTargetEvenIfNotSeen);
        this.ailake = ailake;
    }

    @Override
    public void start() {
        super.start();
        this.ailake.setAggressive(true);
    }

    @Override
    public void stop() {
        super.stop();
        this.ailake.setAggressive(false);
        this.ticksUntilDamage = -1;
        this.pendingTarget = null;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.ticksUntilDamage < 0 || this.pendingTarget == null) {
            return;
        }

        if (--this.ticksUntilDamage > 0) {
            return;
        }

        if (this.pendingTarget.isAlive()) {
            boolean canLandHit = this.ailake.isWithinMeleeAttackRange(this.pendingTarget)
                    && this.ailake.getSensing().hasLineOfSight(this.pendingTarget);
            if (canLandHit) {
                this.ailake.doHurtTarget(this.pendingTarget);
            }
        }

        this.pendingTarget = null;
        this.ticksUntilDamage = -1;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity target) {
        if (this.ticksUntilDamage >= 0 || !this.canPerformAttack(target)) {
            return;
        }

        this.resetAttackCooldown();
        this.pendingTarget = target;
        this.ticksUntilDamage = DAMAGE_DELAY_TICKS;

        this.ailake.swing(InteractionHand.MAIN_HAND);
        this.ailake.triggerBiteAnimation();
    }
}

