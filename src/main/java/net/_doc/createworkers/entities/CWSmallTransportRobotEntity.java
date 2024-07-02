package net._doc.createworkers.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class CWSmallTransportRobotEntity extends Mob {

	protected CWSmallTransportRobotEntity(EntityType<? extends Mob> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return LivingEntity.createLivingAttributes().add(Attributes.MAX_HEALTH, 20D).add(Attributes.FOLLOW_RANGE, 24D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ARMOR_TOUGHNESS, 0.1f)
				.add(Attributes.ATTACK_KNOCKBACK, 0.5f).add(Attributes.ATTACK_DAMAGE, 2f);
	}

}
