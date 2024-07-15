package net._doc.createworkers.items;

import java.util.function.Consumer;

import com.simibubi.create.foundation.item.render.SimpleCustomRenderer;
import com.simibubi.create.foundation.utility.VecHelper;

import net._doc.createworkers.items.renderer.HolePunchItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class HolePunchItem extends Item {

	public HolePunchItem(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack pStack) {
		return UseAnim.DRINK;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 24;
	}

	@Override
	public SoundEvent getDrinkingSound() {
		return SoundEvents.AXE_STRIP;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
		return true;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(SimpleCustomRenderer.create(this, new HolePunchItemRenderer()));
	}

	public static void spawnParticles(Vec3 location, ItemStack polishedStack, Level world) {
		for (int i = 0; i < 2; i++) {
			Vec3 motion = VecHelper.offsetRandomly(Vec3.ZERO, world.random, 1 / 8f);
			world.addParticle(new ItemParticleOption(ParticleTypes.ITEM, polishedStack), location.x, location.y,
					location.z, motion.x, motion.y, motion.z);
		}
	}

	@Override
	public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
		if (pLevel.isClientSide && pRemainingUseDuration % 4 == 0) {
			spawnParticles(pLivingEntity.getEyePosition(1).add(pLivingEntity.getLookAngle().scale(.5f)),
					pLivingEntity.getOffhandItem(), pLevel);
		}
		super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		playerIn.startUsingItem(handIn);
		playerIn.startUsingItem(InteractionHand.OFF_HAND);
		return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);
	}
}
