package net._doc.createworkers.entity.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class CWAnimationDefinition {
    
    public static final AnimationDefinition RHINO_IDLE = AnimationDefinition.Builder.withLength(2f).looping()
            .addAnimation("rhino", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1f, KeyframeAnimations
                            .posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5f, KeyframeAnimations
                            .posVec(0f, -0.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.4583433f, KeyframeAnimations
                                    .posVec(0f, -0.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.9583433f, KeyframeAnimations
                                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5f, KeyframeAnimations
                            .posVec(0f, -1f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations
                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5f, KeyframeAnimations
                            .degreeVec(1f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.4583433f, KeyframeAnimations
                                    .degreeVec(-1f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.9583433f, KeyframeAnimations
                                            .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .build();
    public static final AnimationDefinition RHINO_WALK = AnimationDefinition.Builder.withLength(2f).looping()
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.125f, KeyframeAnimations
                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.75f, KeyframeAnimations
                            .posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1f, KeyframeAnimations
                                    .posVec(0f, 1.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_back_leg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.9167666f, KeyframeAnimations
                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25f, KeyframeAnimations
                            .posVec(0f, -1f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.6766667f, KeyframeAnimations
                                    .posVec(0f, -1f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_back_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.9167666f, KeyframeAnimations
                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25f, KeyframeAnimations
                            .degreeVec(2.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.6766667f, KeyframeAnimations
                                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                            .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_back_knee", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.9167666f, KeyframeAnimations
                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25f, KeyframeAnimations
                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.6766667f, KeyframeAnimations
                                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_back_knee", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.9167666f, KeyframeAnimations
                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25f, KeyframeAnimations
                            .degreeVec(7.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.6766667f, KeyframeAnimations
                                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                            .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_back_heel", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.9167666f, KeyframeAnimations
                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25f, KeyframeAnimations
                            .posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.6766667f, KeyframeAnimations
                                    .posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_back_heel", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.9167666f, KeyframeAnimations
                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25f, KeyframeAnimations
                            .degreeVec(-7.16f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.6766667f, KeyframeAnimations
                                    .degreeVec(-2.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                            .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_back_leg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.375f, KeyframeAnimations
                            .posVec(0f, -1f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.7083434f, KeyframeAnimations
                                    .posVec(0f, -1.25f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.125f, KeyframeAnimations
                                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_back_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations
                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.375f, KeyframeAnimations
                            .degreeVec(5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.7083434f, KeyframeAnimations
                                    .degreeVec(2.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.125f, KeyframeAnimations
                                            .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_back_knee", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.375f, KeyframeAnimations
                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.7083434f, KeyframeAnimations
                                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.125f, KeyframeAnimations
                                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_back_knee", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations
                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.375f, KeyframeAnimations
                            .degreeVec(7.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.7083434f, KeyframeAnimations
                                    .degreeVec(5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.125f, KeyframeAnimations
                                            .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_back_heel", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.2916767f, KeyframeAnimations
                            .posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5834334f, KeyframeAnimations
                                    .posVec(0f, 1.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1f, KeyframeAnimations
                                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_back_heel", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations
                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.2916767f, KeyframeAnimations
                            .degreeVec(-10f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5834334f, KeyframeAnimations
                                    .degreeVec(-7.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1f, KeyframeAnimations
                                            .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_front_leg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(1f, KeyframeAnimations
                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.2916767f, KeyframeAnimations
                            .posVec(0f, -1f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.625f, KeyframeAnimations
                                    .posVec(0f, -1f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_front_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(1f, KeyframeAnimations
                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.2916767f, KeyframeAnimations
                            .degreeVec(-7.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.625f, KeyframeAnimations
                                    .degreeVec(-2.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                            .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_front_knee", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(1f, KeyframeAnimations
                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.2916767f, KeyframeAnimations
                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.625f, KeyframeAnimations
                                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_front_knee", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(1f, KeyframeAnimations
                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.2916767f, KeyframeAnimations
                            .degreeVec(10f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.625f, KeyframeAnimations
                                    .degreeVec(2.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                            .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_front_leg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.25f, KeyframeAnimations
                            .posVec(0f, -1f, -1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.6766666f, KeyframeAnimations
                                    .posVec(0f, -1f, 1f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1f, KeyframeAnimations
                                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_front_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations
                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.25f, KeyframeAnimations
                            .degreeVec(-5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.6766666f, KeyframeAnimations
                                    .degreeVec(-2.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1f, KeyframeAnimations
                                            .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_front_knee", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.25f, KeyframeAnimations
                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.6766666f, KeyframeAnimations
                                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1f, KeyframeAnimations
                                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_front_knee", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations
                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.25f, KeyframeAnimations
                            .degreeVec(10f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.6766666f, KeyframeAnimations
                                    .degreeVec(17.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1f, KeyframeAnimations
                                            .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .build();
    public static final AnimationDefinition RHINO_ATTACK = AnimationDefinition.Builder.withLength(4f).looping()
            .addAnimation("skull", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations
                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.5f, KeyframeAnimations
                            .degreeVec(22.5f, 0f, -45f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                    .degreeVec(22.5f, 0f, -45f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.5f, KeyframeAnimations
                                            .degreeVec(-60.03f, 17.68f, -19.21f), AnimationChannel.Interpolations.LINEAR), new Keyframe(3.5f, KeyframeAnimations
                                                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(4f, KeyframeAnimations
                                                            .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .build();
    public static final AnimationDefinition RHINO_SIT = AnimationDefinition.Builder.withLength(2f).looping()
            .addAnimation("rhino", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, -1f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1f, KeyframeAnimations
                            .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                    .posVec(0f, -1f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, -4f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5f, KeyframeAnimations
                            .posVec(0f, -4.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.4583433f, KeyframeAnimations
                                    .posVec(0f, -4.5f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.9583433f, KeyframeAnimations
                                            .posVec(0f, -4f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                                    .posVec(0f, -4f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations
                    .degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5f, KeyframeAnimations
                            .posVec(0f, -1f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                    .posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations
                    .degreeVec(-7.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5f, KeyframeAnimations
                            .degreeVec(-10f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.4583433f, KeyframeAnimations
                                    .degreeVec(-10f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.9583433f, KeyframeAnimations
                                            .degreeVec(-10f, 0f, 0f), AnimationChannel.Interpolations.LINEAR), new Keyframe(2f, KeyframeAnimations
                                                    .degreeVec(-10f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_back_leg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, -11f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_back_knee", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, 3f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_back_knee", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations
                    .degreeVec(-25f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_back_heel", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, 2f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_back_heel", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations
                    .degreeVec(27.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_back_leg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, -11f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_back_knee", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, 4f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_back_knee", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations
                    .degreeVec(-22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_back_heel", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_back_heel", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0f, KeyframeAnimations
                    .degreeVec(25f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("right_front_leg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, 0f, 2f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_front_leg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0f, KeyframeAnimations
                    .posVec(0f, 0f, 2f), AnimationChannel.Interpolations.LINEAR)))
            .build();
}
