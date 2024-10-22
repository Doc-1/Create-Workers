package net._doc.createworkers.entities;

import java.util.List;

import net._doc.createworkers.registeries.CWItems;
import net._doc.createworkers.worker_interactions.controller.ActionController;
import net.minecraft.BlockUtil;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public abstract class NonLivingEntity extends Entity {
    private static final EntityDataAccessor<Integer> DATA_ID_HURT = SynchedEntityData.defineId(NonLivingEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ID_HURTDIR = SynchedEntityData.defineId(NonLivingEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> DATA_ID_DAMAGE = SynchedEntityData.defineId(NonLivingEntity.class, EntityDataSerializers.FLOAT);
    
    protected ActionController controller = new ActionController();
    protected boolean entityCollision = false;
    
    public NonLivingEntity(EntityType<? extends Entity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.blocksBuilding = true;
        setControllerActions();
    }
    
    public abstract void setControllerActions();
    
    @Override
    public boolean canBeCollidedWith() {
        return true;
    }
    
    @Override
    protected Vec3 getRelativePortalPosition(Direction.Axis pAxis, BlockUtil.FoundRectangle pPortal) {
        return LivingEntity.resetForwardDirectionOfRelativePortalPosition(super.getRelativePortalPosition(pAxis, pPortal));
    }
    
    @Override
    public boolean canCollideWith(Entity pEntity) {
        return pEntity.canBeCollidedWith() || pEntity.isPushable();
    }
    
    @Override
    public boolean isPushable() {
        return false;
    }
    
    @Override
    public void push(Entity pEntity) {
        if (pEntity.getBoundingBox().minY <= this.getBoundingBox().minY)
            super.push(pEntity);
    }
    
    @SuppressWarnings("resource")
    @Override
    public void tick() {
        
        if (this.getHurtTime() > 0)
            this.setHurtTime(this.getHurtTime() - 1);
        
        if (this.getDamage() > 0.0F)
            this.setDamage(this.getDamage() - 1.0F);
        
        super.tick();
        this.checkInsideBlocks();
        
        List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate((double) 0.2F, (double) -0.01F, (double) 0.2F), EntitySelector.pushableBy(this));
        if (!list.isEmpty()) {
            boolean flag = this.level().isClientSide;
            
            for (int j = 0; j < list.size(); ++j) {
                Entity entity = list.get(j);
                if (flag && entity instanceof LivingEntity)
                    this.push(entity);
                else
                    this.entityCollision = false;
            }
        } else
            this.entityCollision = false;
        
    }
    
    @Override
    public void lerpTo(double pX, double pY, double pZ, float pYaw, float pPitch, int pPosRotationIncrements, boolean pTeleport) {}
    
    @Override
    public void animateHurt(float pYaw) {
        this.setHurtDir(-this.getHurtDir());
        this.setHurtTime(10);
        this.setDamage(this.getDamage() * 11.0F);
    }
    
    /** Called when the entity is attacked. */
    @SuppressWarnings("resource")
    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else if (!this.level().isClientSide && !this.isRemoved()) {
            this.setHurtDir(-this.getHurtDir());
            this.setHurtTime(10);
            this.setDamage(this.getDamage() + pAmount * 10.0F);
            this.markHurt();
            this.gameEvent(GameEvent.ENTITY_DAMAGE, pSource.getEntity());
            boolean flag = pSource.getEntity() instanceof Player && ((Player) pSource.getEntity()).getAbilities().instabuild;
            if (flag || this.getDamage() > 40.0F) {
                if (!flag && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                    this.destroy(pSource);
                }
                
                this.discard();
            }
            
            return true;
        } else {
            return true;
        }
    }
    
    protected void destroy(DamageSource pDamageSource) {
        this.spawnAtLocation(this.getDropItem());
    }
    
    public Item getDropItem() {
        return CWItems.MAINSPRING.get();
    }
    
    /** Returns {@code true} if the entity has not been {@link #removed}. */
    public boolean isAlive() {
        return !this.isRemoved();
    }
    
    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_ID_HURT, 0);
        this.entityData.define(DATA_ID_HURTDIR, 1);
        this.entityData.define(DATA_ID_DAMAGE, 0.0F);
    }
    
    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        // TODO Auto-generated method stub
        
    }
    
    /** Returns {@code true} if other Entities should be prevented from moving
     * through this Entity. */
    public boolean isPickable() {
        return !this.isRemoved();
    }
    
    /** Sets the forward direction of the entity. */
    public void setHurtDir(int pHurtDirection) {
        this.entityData.set(DATA_ID_HURTDIR, pHurtDirection);
    }
    
    /** Gets the forward direction of the entity. */
    public int getHurtDir() {
        return this.entityData.get(DATA_ID_HURTDIR);
    }
    
    /** Sets the damage taken from the last hit. */
    public void setDamage(float pDamageTaken) {
        this.entityData.set(DATA_ID_DAMAGE, pDamageTaken);
    }
    
    /** Gets the damage taken from the last hit. */
    public float getDamage() {
        return this.entityData.get(DATA_ID_DAMAGE);
    }
    
    /** Sets the time to count down from since the last time entity was hit. */
    public void setHurtTime(int pHurtTime) {
        this.entityData.set(DATA_ID_HURT, pHurtTime);
    }
    
    /** Gets the time since the last hit. */
    public int getHurtTime() {
        return this.entityData.get(DATA_ID_HURT);
    }
}
