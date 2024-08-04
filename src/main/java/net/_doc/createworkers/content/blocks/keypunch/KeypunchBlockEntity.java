package net._doc.createworkers.content.blocks.keypunch;

import java.util.List;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class KeypunchBlockEntity extends SmartBlockEntity {

	protected ItemStack holePunchCard;
	protected ItemStack key;

	public KeypunchBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void read(CompoundTag compound, boolean clientPacket) {
		holePunchCard = ItemStack.of(compound.getCompound("card"));
		key = ItemStack.of(compound.getCompound("key"));
		super.read(compound, clientPacket);
	}

	@Override
	protected void write(CompoundTag compound, boolean clientPacket) {
		compound.put("card", holePunchCard.serializeNBT());
		compound.put("key", key.serializeNBT());
		super.write(compound, clientPacket);
	}
}
