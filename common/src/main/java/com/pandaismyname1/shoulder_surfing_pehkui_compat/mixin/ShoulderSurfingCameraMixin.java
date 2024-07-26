package com.pandaismyname1.shoulder_surfing_pehkui_compat.mixin;

import com.github.exopandora.shouldersurfing.client.ShoulderSurfingCamera;
import net.minecraft.client.Camera;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShoulderSurfingCamera.class)
public class ShoulderSurfingCameraMixin {

    @Inject(method = "maxZoom", at = @At("HEAD"), cancellable = true)
    private static void maxZoom(Camera camera, BlockGetter level, Vec3 cameraOffset, float partialTick, CallbackInfoReturnable<Double> cir) {
        double distance = cameraOffset.length();
        Vec3 worldOffset = new Vec3(camera.getUpVector()).scale(cameraOffset.y())
                .add(new Vec3(camera.getLeftVector()).scale(cameraOffset.x()))
                .add(new Vec3(camera.getLookVector()).scale(-cameraOffset.z()));
        Vec3 eyePosition = camera.getEntity().getEyePosition(partialTick);

        for(int i = 0; i < 8; i++)
        {
            Vec3 offset = new Vec3(i & 1, i >> 1 & 1, i >> 2 & 1)
                    .scale(2)
                    .subtract(1, 1, 1)
                    .scale(0.15)
                    .yRot(-camera.getYRot() * Mth.DEG_TO_RAD);
            Vec3 from = eyePosition.add(offset).add(new Vec3(worldOffset.x, 0, worldOffset.z).normalize().scale(0.3));
            Vec3 to = from.add(worldOffset);
            ClipContext context = new ClipContext(from, to, ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, camera.getEntity());
            HitResult hitResult = level.clip(context);

            if(hitResult.getType() != HitResult.Type.MISS)
            {
                double newDistance = hitResult.getLocation().distanceTo(eyePosition);

                if(newDistance < distance)
                {
                    distance = newDistance;
                }
            }
        }

        cir.setReturnValue(distance);
    }
}
