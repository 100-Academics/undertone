package com.academics.undertone.entity.goals;

import com.academics.undertone.entity.custom.GrockEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class GrockAttackGoal extends MeleeAttackGoal {
    private static final int DAMAGE_DELAY_TICKS = 6;

    private final GrockEntity grock;
    private int ticksUntilDamage = -1;
    private LivingEntity pendingTarget;

    public GrockAttackGoal(GrockEntity grock, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(grock, speedModifier, followingTargetEvenIfNotSeen);
        this.grock = grock;
    }

    @Override
    public void start() {
        super.start();
        this.grock.setAggressive(true);
    }

    @Override
    public void stop() {
        super.stop();
        this.grock.setAggressive(false);
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
            boolean canLandHit = this.grock.isWithinMeleeAttackRange(this.pendingTarget)
                    && this.grock.getSensing().hasLineOfSight(this.pendingTarget);
            if (canLandHit) {
                this.grock.doHurtTarget(this.pendingTarget);
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

        this.grock.swing(InteractionHand.MAIN_HAND);
        this.grock.triggerAttackAnimation();
    }
}
