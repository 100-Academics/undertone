package com.academics.undertone.entity.custom;

import com.academics.undertone.entity.goals.RudahhAttackGoal;
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

public class RudahhEntity extends Monster {
    private static final byte ATTACK_ANIMATION_EVENT = 61;
    private static final int ATTACK_ANIMATION_TICKS = 10;

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attack = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int attackAnimationTimeout = 0;
    private int r = 0;
    private int nextWorldRandomInt(int bound) {
        return this.level().getRandom().nextInt(bound);
    }

    public RudahhEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 14.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.FOLLOW_RANGE, 20.0D);
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RudahhAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    private void setupAnimationStates(){
        if(this.idleAnimationTimeout <= 0){
            r = this.nextWorldRandomInt(3);
            if(r == 1) {
                this.idleAnimationState.start(this.tickCount);
                this.idleAnimationTimeout = 13 + 20 + this.random.nextInt(40);
            } else {
                this.idleAnimationTimeout = 200;
                this.idleAnimationState.stop();
            }
        } else {
            this.idleAnimationTimeout--;
        }
    }

    public void triggerAttackAnimation() {
        if (this.level().isClientSide) {
            return;
        }

        this.attackAnimationTimeout = ATTACK_ANIMATION_TICKS;
        this.level().broadcastEntityEvent(this, ATTACK_ANIMATION_EVENT);
    }

    private void tickAttackAnimation() {
        if (this.attackAnimationTimeout <= 0) {
            return;
        }

        this.attackAnimationTimeout--;

        if (this.attackAnimationTimeout == 0 && this.level().isClientSide) {
            this.attack.stop();
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == ATTACK_ANIMATION_EVENT) {
            this.attackAnimationTimeout = ATTACK_ANIMATION_TICKS;
            this.attack.start(this.tickCount);
            return;
        }

        super.handleEntityEvent(id);
    }

    @Override
    public void tick(){
        super.tick();
        this.tickAttackAnimation();
        if(this.level().isClientSide){
            this.setupAnimationStates();
        }
    }
}
