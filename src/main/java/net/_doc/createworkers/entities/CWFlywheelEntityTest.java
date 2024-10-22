package net._doc.createworkers.entities;

import java.util.List;

import javax.annotation.Nullable;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;

import net._doc.createworkers.torque.TorquePower;
import net._doc.createworkers.worker_interactions.controller.actions.MoveToVecAction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.ContainerEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CWFlywheelEntityTest extends Worker implements IHaveGoggleInformation, ContainerEntity {
    
    private NonNullList<ItemStack> itemStacks = NonNullList.withSize(20, ItemStack.EMPTY);
    private float chasingVelocity = 0;
    private float independentAngle = 0;
    
    public CWFlywheelEntityTest(EntityType<? extends Entity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    
    @Override
    public void tick() {
        chasingVelocity += ((20 * 10 / 3f) - chasingVelocity) * .25f;
        independentAngle += chasingVelocity;
        super.tick();
    }
    
    public float getIndependentAngle(float partialTicks) {
        return (independentAngle + partialTicks * chasingVelocity) / 360;
    }
    
    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        return super.addToGoggleTooltip(tooltip, isPlayerSneaking);
    }
    
    @Override
    public void setControllerActions() {
        // this.controller.add(new MoveToVecAction(new Vec3(242, 0, -24)));
        
        this.controller.add(new MoveToVecAction(this, new Vec3(236.5, 0, -16.5), 20));
        
        // this.controller.add(new MoveToVecAction(this, new Vec3(246.5, 0, -7.5)));
        
        this.controller.add(new MoveToVecAction(this, new Vec3(236.5, 0, -24.5), 80));
        this.controller.add(new MoveToVecAction(this, new Vec3(242.5, 0, -23.5), 0));
        this.controller.add(new MoveToVecAction(this, new Vec3(248.5, 0, -13.5), 0));
        this.controller.add(new MoveToVecAction(this, new Vec3(248.5, 0, -10.5), 0));
        this.controller.add(new MoveToVecAction(this, new Vec3(242.5, 0, -10.5), 0));
        this.controller.add(new MoveToVecAction(this, new Vec3(242.5, 0, -8.5), 0));
    }
    
    @Override
    public TorquePower setTorquePower() {
        return new TorquePower(500, 2);
    }
    
    @Override
    public int getContainerSize() {
        return 20;
    }
    
    @Override
    public ItemStack getItem(int pSlot) {
        // TODO Auto-generated method stub
        return this.getItemStacks().get(pSlot);
    }
    
    @Override
    public ItemStack removeItem(int pSlot, int pAmount) {
        return ContainerHelper.removeItem(this.getItemStacks(), pSlot, pAmount);
    }
    
    @Override
    public ItemStack removeItemNoUpdate(int pSlot) {
        ItemStack itemstack = this.getItemStacks().get(pSlot);
        if (itemstack.isEmpty())
            return ItemStack.EMPTY;
        else {
            this.getItemStacks().set(pSlot, ItemStack.EMPTY);
            return itemstack;
        }
    }
    
    @Override
    public void setItem(int pSlot, ItemStack pStack) {
        this.getItemStacks().set(pSlot, pStack);
        if (!pStack.isEmpty() && pStack.getCount() > this.getMaxStackSize())
            pStack.setCount(this.getMaxStackSize());
    }
    
    @Override
    public SlotAccess getSlot(int pSlot) {
        return this.getChestVehicleSlot(pSlot);
    }
    
    @Override
    public void setChanged() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public boolean stillValid(Player pPlayer) {
        return this.isChestVehicleStillValid(pPlayer);
    }
    
    @SuppressWarnings("resource")
    @Override
    public void remove(RemovalReason pReason) {
        if (!this.level().isClientSide && pReason.shouldDestroy())
            Containers.dropContents(this.level(), this, this);
        
        super.remove(pReason);
    }
    
    @SuppressWarnings("resource")
    @Override
    protected void destroy(DamageSource pDamageSource) {
        super.destroy(pDamageSource);
        if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS))
            Containers.dropContents(this.level(), this, this);
    }
    
    @Override
    public void clearContent() {
        this.getItemStacks().clear();
    }
    
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return null;
    }
    
    @Override
    public ResourceLocation getLootTable() {
        return null;
    }
    
    @Override
    public void setLootTable(ResourceLocation pLootTable) {}
    
    @Override
    public long getLootTableSeed() {
        return 0;
    }
    
    @Override
    public void setLootTableSeed(long pLootTableSeed) {}
    
    // Forge Start
    private net.minecraftforge.common.util.LazyOptional<?> itemHandler = net.minecraftforge.common.util.LazyOptional
            .of(() -> new net.minecraftforge.items.wrapper.InvWrapper(this));
    
    @Override
    public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable net.minecraft.core.Direction facing) {
        if (capability == net.minecraftforge.common.capabilities.ForgeCapabilities.ITEM_HANDLER && this.isAlive())
            return itemHandler.cast();
        return super.getCapability(capability, facing);
    }
    
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemHandler.invalidate();
    }
    
    @Override
    public void reviveCaps() {
        super.reviveCaps();
        itemHandler = net.minecraftforge.common.util.LazyOptional.of(() -> new net.minecraftforge.items.wrapper.InvWrapper(this));
    }
    
    public NonNullList<ItemStack> getItemStacks() {
        return this.itemStacks;
    }
    
    public void clearItemStacks() {
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
    }
    
}
