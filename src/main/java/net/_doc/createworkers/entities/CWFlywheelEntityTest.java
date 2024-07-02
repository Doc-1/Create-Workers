package net._doc.createworkers.entities;

import java.util.List;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.foundation.utility.Lang;

import net.minecraft.BlockUtil;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CWFlywheelEntityTest extends Entity implements IHaveGoggleInformation {

	private float chasingVelocity = 0;
	private float independentAngle = 0;
	private float lerpSteps;
	private double lerpX;
	private double lerpY;
	private double lerpZ;

	protected CWFlywheelEntityTest(EntityType<? extends Entity> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		this.blocksBuilding = true;

	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	protected Entity.MovementEmission getMovementEmission() {
		return Entity.MovementEmission.EVENTS;
	}

	@Override
	protected Vec3 getRelativePortalPosition(Direction.Axis pAxis, BlockUtil.FoundRectangle pPortal) {
		return LivingEntity
				.resetForwardDirectionOfRelativePortalPosition(super.getRelativePortalPosition(pAxis, pPortal));
	}

	@Override
	public boolean canCollideWith(Entity pEntity) {
		return pEntity.canBeCollidedWith() || pEntity.isPushable();
	}

	@Override
	public boolean isPushable() {
		return true;
	}

	@Override
	public void push(Entity pEntity) {
		if (pEntity instanceof CWFlywheelEntityTest) {
			if (pEntity.getBoundingBox().minY < this.getBoundingBox().maxY) {
				super.push(pEntity);
			}
		} else if (pEntity.getBoundingBox().minY <= this.getBoundingBox().minY) {
			super.push(pEntity);
		}

	}

	@Override
	public void tick() {
		super.tick();
		chasingVelocity += ((20 * 10 / 3f) - chasingVelocity) * .25f;
		independentAngle += chasingVelocity;
		tickLerp();
		this.move(MoverType.SELF, new Vec3(0.05, -0.3, 0));

		this.checkInsideBlocks();
		List<Entity> list = this.level().getEntities(this,
				this.getBoundingBox().inflate((double) 0.2F, (double) -0.01F, (double) 0.2F),
				EntitySelector.pushableBy(this));
		if (!list.isEmpty()) {
			boolean flag = this.level().isClientSide;

			for (int j = 0; j < list.size(); ++j) {
				Entity entity = list.get(j);
				if (flag && entity instanceof Player) {
					this.push(entity);
				}

			}
		}
	}

	private void tickLerp() {
		if (this.lerpSteps > 0) {
			double d0 = this.getX() + (this.lerpX - this.getX()) / (double) this.lerpSteps;
			double d1 = this.getY() + (this.lerpY - this.getY()) / (double) this.lerpSteps;
			double d2 = this.getZ() + (this.lerpZ - this.getZ()) / (double) this.lerpSteps;
			double d3 = Mth.wrapDegrees(this.getYRot() - (double) this.getYRot());
			this.setYRot(this.getYRot() + (float) d3 / (float) this.lerpSteps);
			this.setXRot(this.getXRot() + (float) (this.getXRot() - (double) this.getXRot()) / (float) this.lerpSteps);
			--this.lerpSteps;
			this.setPos(d0, d1, d2);
			this.setRot(this.getYRot(), this.getXRot());
		}
	}

	@Override
	public void lerpTo(double pX, double pY, double pZ, float pYaw, float pPitch, int pPosRotationIncrements,
			boolean pTeleport) {
		this.lerpX = pX;
		this.lerpY = pY;
		this.lerpZ = pZ;
		this.lerpSteps = 10;
	}

	public float getIndependentAngle(float partialTicks) {
		return (independentAngle + partialTicks * chasingVelocity) / 360;
	}

	@Override
	public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
		tooltip.add(Lang.number(10).component());
		return true;
	}

	@Override
	public Iterable<ItemStack> getArmorSlots() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void defineSynchedData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void readAdditionalSaveData(CompoundTag pCompound) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addAdditionalSaveData(CompoundTag pCompound) {
		// TODO Auto-generated method stub

	}

}
