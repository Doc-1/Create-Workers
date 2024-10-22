package net._doc.createworkers.entities.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;

public class CWSmallTransportRobotModel<T extends Entity> extends EntityModel<T> {
    
    private final ModelPart bb_main;
    
    public CWSmallTransportRobotModel(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }
    
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        
        partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 32).addBox(-9.0F, -4.0F, -9.0F, 18.0F, 3.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(44, 53).addBox(-6.0F, -3.0F, -11.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 53)
                .addBox(-11.0F, -3.0F, -6.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(16, 53).addBox(-6.0F, -3.0F, 9.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(48, 0).addBox(9.0F, -3.0F, -6.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(0, 32)
                .addBox(4.0F, -2.0F, 4.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 10).addBox(4.0F, -2.0F, -7.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 5).addBox(-7.0F, -2.0F, -7.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
                .addBox(-7.0F, -2.0F, 4.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-8.0F, -21.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(10, 35).addBox(7.0F, -6.0F, 7.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(10, 35)
                .addBox(7.0F, -6.0F, -9.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(10, 35).addBox(-9.0F, -6.0F, -9.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(10, 35).addBox(-9.0F, -6.0F, 7.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(10, 35)
                .addBox(-9.0F, -6.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(10, 35).addBox(-1.0F, -6.0F, -9.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(10, 35).addBox(7.0F, -6.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(10, 35)
                .addBox(-1.0F, -6.0F, 7.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        
        return LayerDefinition.create(meshdefinition, 128, 128);
    }
    
    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        
    }
    
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
    
}