package dev.andante.fasterminecarts.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractMinecartEntity.class)
public class AbstractMinecartEntityMixin {
    @Inject(method = "getMaxSpeed", at = @At("RETURN"), cancellable = true)
    private void onGetMaxSpeed(CallbackInfoReturnable<Double> cir) {
        AbstractMinecartEntity that = (AbstractMinecartEntity) (Object) this;
        if (that instanceof MinecartEntity && that.hasPassenger(entity -> entity instanceof PlayerEntity)) {
            cir.setReturnValue(cir.getReturnValueD() * 2);
        }
    }
}
