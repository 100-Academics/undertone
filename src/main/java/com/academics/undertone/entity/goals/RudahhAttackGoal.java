package com.academics.undertone.entity.goals;

import com.academics.undertone.entity.custom.RudahhEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class RudahhAttackGoal extends MeleeAttackGoal {
    // tongueattack apex is around 0.2917s in a 0.5s clip => ~6 ticks at 20 TPS
    private static final int DAMAGE_DELAY_TICKS = 6;

    private final RudahhEntity rudahh;
    private int ticksUntilDamage = -1;
    private LivingEntity pendingTarget;

    public RudahhAttackGoal(RudahhEntity rudahh, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(rudahh, speedModifier, followingTargetEvenIfNotSeen);
        this.rudahh = rudahh;
    }

    @Override
    public void start() {
        super.start();
        this.rudahh.setAggressive(true);
    }

    @Override
    public void stop() {
        super.stop();
        this.rudahh.setAggressive(false);
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
            boolean canLandHit = this.rudahh.isWithinMeleeAttackRange(this.pendingTarget)
                    && this.rudahh.getSensing().hasLineOfSight(this.pendingTarget);
            if (canLandHit) {
                this.rudahh.doHurtTarget(this.pendingTarget);
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

        this.rudahh.swing(InteractionHand.MAIN_HAND);
        this.rudahh.triggerAttackAnimation();
    }
}
