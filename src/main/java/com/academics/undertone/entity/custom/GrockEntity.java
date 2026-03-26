package com.academics.undertone.entity.custom;

import com.academics.undertone.entity.goals.GrockAttackGoal;
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

public class GrockEntity extends Monster {
    private static final byte ATTACK_ANIMATION_EVENT = 60;

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int attackAnimationTimeout = 0;

    public GrockEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new GrockAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

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
            this.idleAnimationTimeout = 80; // TODO
            this.idleAnimationState.start(this.tickCount);
        } else{
            this.idleAnimationTimeout--;
        }

        if (this.attackAnimationTimeout > 0) {
            this.attackAnimationTimeout--;
        } else {
            this.attackAnimationState.stop();
        }
    }

    public void triggerAttackAnimation() {
        if (this.level().isClientSide) {
            return;
        }

        this.attackAnimationTimeout = 12;
        this.level().broadcastEntityEvent(this, ATTACK_ANIMATION_EVENT);
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == ATTACK_ANIMATION_EVENT) {
            this.attackAnimationTimeout = 12;
            this.attackAnimationState.start(this.tickCount);
            return;
        }

        super.handleEntityEvent(id);
    }

    @Override
    public void tick() {
        super.tick();
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
