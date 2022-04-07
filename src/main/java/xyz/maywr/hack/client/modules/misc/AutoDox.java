package xyz.maywr.hack.client.modules.misc;

import net.minecraft.entity.player.EntityPlayer;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.api.util.MessageUtil;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest(name = "AutoDox", category = Module.Category.MISC, description = "doxes people")
public class AutoDox extends Module {

    private final Setting<Lang> lang = register(new Setting<>("Language", Lang.English));

    @Override
    public void onEnable() {
        MessageUtil.sendClientMessage("right click on player to dox him", false);
    }

    public void execute(int button) {
        if (button == 3 && mc.pointedEntity != null && mc.pointedEntity instanceof EntityPlayer) {
            dox((EntityPlayer) mc.pointedEntity);
        }
    }

    private void dox(EntityPlayer player) {

    }

    private enum Lang {
        English,
        Russian

    }
}
