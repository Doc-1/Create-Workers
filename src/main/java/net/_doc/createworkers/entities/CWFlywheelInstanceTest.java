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

import net._doc.createworkers.registeries.CWBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class CWFlywheelInstanceTest extends EntityInstance<CWFlywheelEntityTest> implements DynamicInstance {
    private static PoseStack stack = new PoseStack();
    private final ModelData cogwheel;
    private final ModelData body;
    private final ModelData storageModel;
    
    public CWFlywheelInstanceTest(MaterialManager materialManager, CWFlywheelEntityTest entity) {
        super(materialManager, entity);
        
        cogwheel = materialManager.defaultSolid().material(Materials.TRANSFORMED).getModel(AllPartialModels.SHAFTLESS_COGWHEEL).createInstance();
        body = materialManager.defaultSolid().material(Materials.TRANSFORMED).getModel(CWBlocks.TEMP_BLOCK.getDefaultState()).createInstance();
        this.storageModel = materialManager.defaultSolid().material(Materials.TRANSFORMED).getModel(AllBlocks.ITEM_VAULT.getDefaultState()).createInstance();
        
        cogwheel.loadIdentity().translate(getInstancePosition()).centre().rotate(Direction.get(Direction.AxisDirection.POSITIVE, Axis.Y), 0).unCentre();
        body.loadIdentity().translate(getInstancePosition());
        this.storageModel.loadIdentity().translate(getInstancePosition());
        
    }
    
    @Override
    protected void remove() {
        cogwheel.delete();
        body.delete();
        storageModel.delete();
    }
    
    @Override
    public void updateLight() {
        relight(getWorldPosition(), cogwheel);
        relight(getWorldPosition(), body);
        relight(getWorldPosition(), storageModel);
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
        double x, y, z;
        if (!entity.getTorquePower().isHasTorque()) {
            x = entity.getX() - partOffset().x;
            y = entity.getY() - partOffset().y;
            z = entity.getZ() - partOffset().z;
            yaw = (float) Math.toRadians(entity.getYRot());
            rot = 0;
            pt = 0;
        } else {
            pt = AnimationTickHolder.getPartialTicks();
            x = Mth.lerp(pt, entity.xOld, entity.getX()) - partOffset().x;
            y = Mth.lerp(pt, entity.yOld, entity.getY()) - partOffset().y;
            z = Mth.lerp(pt, entity.zOld, entity.getZ()) - partOffset().z;
            if (!entity.isIgnoreFrames())
                yaw = (float) Math.toRadians(Mth.lerp(pt, entity.yRotO, entity.getYRot()));
            else {
                yaw = (float) Math.toRadians(Mth.lerp(pt, entity.yRotO, entity.yRotO + entity.deltaRotation));
            }
            rot = entity.getIndependentAngle(pt);
        }
        tstack.translate(x, y, z);
        body.loadIdentity().setTransform(stack).centre().rotate(Direction.get(Direction.AxisDirection.POSITIVE, Axis.Y), -yaw).unCentre();
        cogwheel.loadIdentity().setTransform(stack).centre().rotate(Direction.get(Direction.AxisDirection.POSITIVE, Axis.Y), rot).unCentre();
        
        tstack.translate(0, 0.75, 0);
        storageModel.loadIdentity().setTransform(stack).centre().rotate(Direction.get(Direction.AxisDirection.POSITIVE, Axis.Y), -yaw).unCentre();
        
    }
}
