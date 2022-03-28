package xyz.maywr.hack.api.mixin.mixins.render;

import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.maywr.hack.client.modules.visual.NoRender;

@Mixin(LayerArmorBase.class)
public class MixinLayerArmorBase {

    @Inject(method = "doRenderLayer", at = @At("HEAD"), cancellable = true)
    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, CallbackInfo ci) {
        if (NoRender.INSTANCE.isEnabled() && NoRender.INSTANCE.armor.getValue()) {
            ci.cancel();
        }
    }
}
