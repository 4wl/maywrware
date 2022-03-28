package xyz.maywr.hack.api.mixin.mixins.player;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.interfaces.Minecraftable;
import xyz.maywr.hack.client.modules.visual.ViewmodelChanger;

@Mixin(ItemRenderer.class)
public class MixinItemRenderer implements Minecraftable {

    @Inject( method = "renderItemSide", at = @At( value = "HEAD" ) )
    public void renderItemSide(EntityLivingBase entityLivingBase, ItemStack stack, ItemCameraTransforms.TransformType transform, boolean leftHanded, CallbackInfo info) {
        ViewmodelChanger viewmodelChanger = (ViewmodelChanger) MaywrWare.moduleManager.getModuleByClass(ViewmodelChanger.class);
        if (viewmodelChanger.isEnabled() && entityLivingBase == mc.player) {
            GlStateManager.scale(viewmodelChanger.translateX.getValue(), viewmodelChanger.translateY.getValue(), viewmodelChanger.translateZ.getValue());
            if (mc.player.getActiveItemStack() != stack) {
                GlStateManager.translate((viewmodelChanger.translateX.getValue() * 0.1f) * (leftHanded ? -1 : 1), viewmodelChanger.translateY.getValue() * 0.1f, viewmodelChanger.translateZ.getValue() * 0.1);
            }
        }
    }
}
