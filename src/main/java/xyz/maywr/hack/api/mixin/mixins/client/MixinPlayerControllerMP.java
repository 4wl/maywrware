package xyz.maywr.hack.api.mixin.mixins.client;

import net.minecraftforge.common.MinecraftForge;
import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.mixin.accessors.IPlayerControllerMP;
import xyz.maywr.hack.client.events.ClickBlockEvent;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP implements IPlayerControllerMP {

    @Override
    @Accessor("isHittingBlock")
    public abstract void setIsHittingBlock(boolean b);

    @Override
    @Accessor("blockHitDelay")
    public abstract void setBlockHitDelay(int delay);

    @Override
    @Accessor("curBlockDamageMP")
    public abstract float getCurBlockDamageMP();

    @Inject(method = "clickBlock", at = @At("HEAD"), cancellable = true)
    public void clickBlock(BlockPos loc, EnumFacing face, CallbackInfoReturnable<Boolean> cir) {
        final ClickBlockEvent event = new ClickBlockEvent(0, loc, face);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            cir.cancel();
        }
    }

    @Inject(method = "onPlayerDamageBlock(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)Z", at = @At("HEAD"), cancellable = true)
    public void onPlayerDamageBlock(BlockPos posBlock, EnumFacing directionFacing, CallbackInfoReturnable<Boolean> cir) {
        ClickBlockEvent event = new ClickBlockEvent(1, posBlock, directionFacing);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
            cir.cancel();
    }

}
