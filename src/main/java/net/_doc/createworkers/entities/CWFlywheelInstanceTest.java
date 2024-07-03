package net._doc.createworkers.entities;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.api.instance.DynamicInstance;
import com.jozufozu.flywheel.backend.instancing.entity.EntityInstance;
import com.jozufozu.flywheel.core.Materials;
import com.jozufozu.flywheel.core.materials.model.ModelData;
import com.jozufozu.flywheel.util.AnimationTickHolder;
import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;

import net._doc.createworkers.blocks.CWBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class CWFlywheelInstanceTest extends EntityInstance<CWFlywheelEntityTest> implements DynamicInstance {
	private static PoseStack stack = new PoseStack();
	private final ModelData model;
	private final ModelData model2;

	public CWFlywheelInstanceTest(MaterialManager materialManager, CWFlywheelEntityTest entity) {
		super(materialManager, entity);

		model = materialManager.defaultSolid().material(Materials.TRANSFORMED)
				.getModel(AllPartialModels.SHAFTLESS_COGWHEEL).createInstance();
		model2 = materialManager.defaultSolid().material(Materials.TRANSFORMED)
				.getModel(CWBlocks.TEMP_BLOCK.getDefaultState()).createInstance();

		model.loadIdentity().translate(getInstancePosition()).centre()
				.rotate(Direction.get(Direction.AxisDirection.POSITIVE, Axis.Y), 0).unCentre();
		model2.loadIdentity().translate(getInstancePosition());

	}

	@Override
	protected void remove() {
		model.delete();
		model2.delete();
	}

	@Override
	public void updateLight() {
		relight(getWorldPosition(), model);
		relight(getWorldPosition(), model2);
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
		float pt = AnimationTickHolder.getPartialTicks();
		tstack.translate(Mth.lerp(pt, entity.xOld, entity.getX()) - partOffset().x,
				Mth.lerp(pt, entity.yOld, entity.getY()) - partOffset().y,
				Mth.lerp(pt, entity.zOld, entity.getZ()) - partOffset().z);

		if (!entity.torque.hasEnoughTorque())
			pt = 0;

		float yaw = (float) Math.toRadians(Mth.lerp(pt, entity.yRotO, entity.getYRot()));
		model2.loadIdentity().setTransform(stack).centre()
				.rotate(Direction.get(Direction.AxisDirection.POSITIVE, Axis.Y), -yaw).unCentre();
		model.loadIdentity().setTransform(stack).centre()
				.rotate(Direction.get(Direction.AxisDirection.POSITIVE, Axis.Y), entity.getIndependentAngle(pt) + yaw)
				.unCentre();

	}
}
