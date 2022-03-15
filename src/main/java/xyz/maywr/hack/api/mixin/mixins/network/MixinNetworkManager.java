package xyz.maywr.hack.api.mixin.mixins.network;

import io.netty.channel.ChannelHandlerContext;
import net.minecraftforge.common.MinecraftForge;
import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.client.events.PacketEvent;
import xyz.maywr.hack.client.modules.Module;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkManager.class)
public abstract class MixinNetworkManager {

    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
    public void onSendPacket(Packet<?> packetIn, CallbackInfo ci) {
        final PacketEvent.Send event = new PacketEvent.Send(packetIn);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    public void onReceivePacket(ChannelHandlerContext p_channelRead0_1_, Packet<?> packet, CallbackInfo ci) {
        final PacketEvent.Receive event = new PacketEvent.Receive(packet);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method = "handleDisconnection", at = @At("HEAD"))
    public void onDisconnect(CallbackInfo ci) {
        MaywrWare.moduleManager.getModules().forEach(Module::onDisconnect);
    }

}
