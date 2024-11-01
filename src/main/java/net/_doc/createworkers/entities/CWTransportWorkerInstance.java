package net._doc.createworkers.entities;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.api.instance.DynamicInstance;
import com.jozufozu.flywheel.backend.instancing.entity.EntityInstance;
import com.jozufozu.flywheel.core.Materials;
import com.jozufozu.flywheel.core.materials.model.ModelData;
import com.jozufozu.flywheel.util.AnimationTickHolder;
import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllPartialModels;

import net._doc.createworkers.registeries.CWPartialModels;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class CWTransportWorkerInstance extends EntityInstance<CWTransportWorker> implements DynamicInstance {
    private static PoseStack stack = new PoseStack();
    private final ModelData cogwheel;
    private final ModelData body;
    private final ModelData lift;
    
    private ModelData storage;
    
    public CWTransportWorkerInstance(MaterialManager materialManager, CWTransportWorker entity) {
        super(materialManager, entity);
        
        cogwheel = materialManager.defaultSolid().material(Materials.TRANSFORMED).getModel(AllPartialModels.SHAFTLESS_COGWHEEL).createInstance();
        body = materialManager.defaultSolid().material(Materials.TRANSFORMED).getModel(CWPartialModels.TRANSPORT_WORKER).createInstance();
        this.lift = materialManager.defaultSolid().material(Materials.TRANSFORMED).getModel(CWPartialModels.TRANSPORT_WORKER_LIFT).createInstance();
        if (entity.isHoldingVault())
            storage = getStorage();
        
    }
    
    @Override
    protected void remove() {
        cogwheel.delete();
        body.delete();
        lift.delete();
        
        if (storage != null && !storage.isRemoved())
            storage.delete();
    }
    
    private ModelData getStorage() {
        return materialManager.defaultSolid().material(Materials.TRANSFORMED).getModel(AllBlocks.ITEM_VAULT.getDefaultState()).createInstance();
    }
    
    @Override
    public void updateLight() {
        relight(getWorldPosition(), cogwheel);
        relight(getWorldPosition(), body);
        relight(getWorldPosition(), lift);
        
        if (storage != null && !storage.isRemoved())
            relight(getWorldPosition(), storage);
    }
    
    private Vec3 partOffset() {
        Vec3i originCoordinate = materialManager.getOriginCoordinate();
        Vec3 offsetOrigin = new Vec3(0.5, 0, 0.5);
        return offsetOrigin.add(originCoordinate.getX(), originCoordinate.getY(), originCoordinate.getZ());
    }
    
    @Override
    public void beginFrame() {
        TransformStack tstack = TransformStack.cast(stack);
        stack.setIdentity();
        
        float rot, yaw, pt;
        float pos;
        double x, y, z;
        if (entity.getTorque() != null && !entity.getTorque().isEmpty()) {
            x = entity.getX() - partOffset().x;
            y = entity.getY() - partOffset().y;
            z = entity.getZ() - partOffset().z;
            yaw = (float) Math.toRadians(entity.getYRot());
            rot = 0;
            pt = 0;
            pos = 0;
        } else {
            pt = AnimationTickHolder.getPartialTicks();
            x = Mth.lerp(pt, entity.xOld, entity.getX()) - partOffset().x;
            y = Mth.lerp(pt, entity.yOld, entity.getY()) - partOffset().y;
            z = Mth.lerp(pt, entity.zOld, entity.getZ()) - partOffset().z;
            yaw = (float) Math.toRadians(Mth.lerp(pt, entity.yRotO, !entity.isIgnoreFrames() ? entity.getYRot() : entity.yRotO));
            rot = entity.getIndependentAngle(pt);
            pos = entity.getLiftPosition(pt);
        }
        
        tstack.translate(x, y, z);
        cogwheel.loadIdentity().setTransform(stack).centre().rotate(Direction.get(Direction.AxisDirection.POSITIVE, Axis.Y), rot).unCentre();
        
        body.loadIdentity().setTransform(stack).centre().rotate(Direction.get(Direction.AxisDirection.POSITIVE, Axis.Y), -yaw);
        
        tstack.translate(0, -0.0625 + pos, 0);
        lift.loadIdentity().setTransform(stack).centre().rotate(Direction.get(Direction.AxisDirection.POSITIVE, Axis.Y), -yaw);
        if (storage != null && !storage.isRemoved()) {
            tstack.translate(0, 0.8125, 0);
            storage.loadIdentity().setTransform(stack).centre().rotate(Direction.get(Direction.AxisDirection.POSITIVE, Axis.Y), -yaw).unCentre();
        }
    }
}
