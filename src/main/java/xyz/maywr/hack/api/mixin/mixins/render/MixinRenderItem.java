package xyz.maywr.hack.api.mixin.mixins.render;

import xyz.maywr.hack.api.interfaces.Minecraftable;
import xyz.maywr.hack.client.modules.visual.EnchantColor;
import xyz.maywr.hack.client.modules.visual.ViewmodelChanger;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderItem.class)
public class MixinRenderItem implements Minecraftable {

    @ModifyArg(method = "renderEffect", at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/RenderItem.renderModel(Lnet/minecraft/client/renderer/block/model/IBakedModel;I)V"))
    private int renderEffect(final int glintVal) {
        return EnchantColor.INSTANCE.isEnabled() ? EnchantColor.INSTANCE.getColor() : glintVal;
    }
}
