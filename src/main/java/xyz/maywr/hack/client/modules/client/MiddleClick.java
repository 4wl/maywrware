package xyz.maywr.hack.client.modules.client;

import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

@ModuleManifest(label = "MiddleClick", listen = false, category = Module.Category.CLIENT)
public class MiddleClick extends Module {

    private static MiddleClick INSTANCE;

    private final Setting<Boolean> friends = register(new Setting<>("Friends", true));

    public MiddleClick() {
        INSTANCE = this;
    }

    public static MiddleClick getInstance() {
        return INSTANCE;
    }

    public void run(int mouse) {
        if (mouse == 2 && friends.getValue()) {
            if (mc.objectMouseOver.entityHit != null) {
                final Entity entity = mc.objectMouseOver.entityHit;

                if (!(entity instanceof EntityPlayer)) {
                    return;
                }

                if (MaywrWare.friendManager.isFriend(entity.getName())) {
                    MaywrWare.friendManager.removeFriend(entity.getName());
                } else {
                    MaywrWare.friendManager.addFriend(entity.getName());
                }
            }
        }
    }

}
