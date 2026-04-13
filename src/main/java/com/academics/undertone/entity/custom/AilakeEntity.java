package com.academics.undertone.entity.custom;

import com.academics.undertone.entity.goals.AilakeBiteGoal;
import com.academics.undertone.entity.goals.AilakeSpitGoal;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;

public class AilakeEntity extends Monster {

    private static final byte SPIT_ANIMATION_EVENT = 60;
    private static final byte BITE_ANIMATION_EVENT = 61;

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState idleAnimationState2 = new AnimationState();
    public final AnimationState attack1 = new AnimationState(); // spit
    public final AnimationState attack2 = new AnimationState(); // bite
    private int idleAnimationTimeout = 0;
    private int attackAnimationTimeout = 0;
    private int r = 0;

    private float yaw;
    private float pitch;

    private static final int ATTACK_LOCK_TICKS = 24;

    // Use world-level RNG for deterministic world-tied randomness.
    private int nextWorldRandomInt(int bound) {
        return this.level().getRandom().nextInt(bound);
    }

    public AilakeEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.setPathfindingMalus(PathType.WATER, -1.0F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AilakeSpitGoal(this, 1.0D, 12.0F, 2.8F));
        this.goalSelector.addGoal(2, new AilakeBiteGoal(this, 1.2D, true));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D);
    }


    private void setupAnimationStates(){
        if(this.idleAnimationTimeout <= 0){

            r = this.nextWorldRandomInt(5);

            if(r == 1){
                this.idleAnimationState2.stop();
                this.idleAnimationTimeout = 60 + this.nextWorldRandomInt(40);
                this.idleAnimationState.start(this.tickCount);
            } if (r == 2) {
                this.idleAnimationState.stop();
                this.idleAnimationTimeout = 10;
                this.idleAnimationState2.start(this.tickCount);
            } else {
                this.idleAnimationState.stop();
                this.idleAnimationState2.stop();
                idleAnimationTimeout = 200;
            }

        } else{
            this.idleAnimationTimeout--;
        }
    }

    private void tickAttackLock() {
        if (this.attackAnimationTimeout <= 0) {
            return;
        }

        this.attackAnimationTimeout--;

        // Freeze facing while either attack animation is active.
        this.setYRot(this.yaw);
        this.setXRot(this.pitch);
        this.yHeadRot = this.yaw;
        this.yHeadRotO = this.yaw;
        this.yBodyRot = this.yaw;
        this.yBodyRotO = this.yaw;

        if (this.attackAnimationTimeout == 0 && this.level().isClientSide) {
            this.attack1.stop();
            this.attack2.stop();
        }
    }

    public void triggerSpitAnimation() {
        if (this.level().isClientSide) {
            return;
        }

        this.yaw = this.getYRot();
        this.pitch = this.getXRot();
        this.attackAnimationTimeout = ATTACK_LOCK_TICKS;
        this.level().broadcastEntityEvent(this, SPIT_ANIMATION_EVENT);
    }

    public void triggerBiteAnimation() {
        if (this.level().isClientSide) {
            return;
        }

        this.yaw = this.getYRot();
        this.pitch = this.getXRot();
        this.attackAnimationTimeout = ATTACK_LOCK_TICKS;
        this.level().broadcastEntityEvent(this, BITE_ANIMATION_EVENT);
    }

    public void triggerAttackAnimation() {
        this.triggerBiteAnimation();
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == SPIT_ANIMATION_EVENT) {
            this.attackAnimationTimeout = ATTACK_LOCK_TICKS;
            this.attack2.stop();
            this.attack1.start(this.tickCount);
            this.yaw = this.getYRot();
            this.pitch = this.getXRot();
            return;
        }

        if (id == BITE_ANIMATION_EVENT) {
            this.attackAnimationTimeout = ATTACK_LOCK_TICKS;
            this.attack1.stop();
            this.attack2.start(this.tickCount);
            this.yaw = this.getYRot();
            this.pitch = this.getXRot();
            return;
        }

        super.handleEntityEvent(id);
    }

    @Override
    public void tick() {
        super.tick();
        this.tickAttackLock();
        if(this.level().isClientSide){
            this.setupAnimationStates();
        }
        if(!this.level().isClientSide){
            if (this.isInWater()){
                this.hurt(this.damageSources().drown(), 1.0F);
            }
        }
    }
}
