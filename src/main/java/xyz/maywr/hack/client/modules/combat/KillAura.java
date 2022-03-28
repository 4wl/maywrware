package xyz.maywr.hack.client.modules.combat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ModuleManifest(name = "KillAura", color = 0x22, category = Module.Category.COMBAT)
public class KillAura extends Module {

    private Entity target;
    private final Setting<Float> range = register(new Setting<>("Range", 3.5f, 0.5f, 5.0f));
    private final Setting<Boolean> playerOnly = register(new Setting<>("Only Players", false));

    @Override
    public void onTick() {
        List<Entity> entitiesLoaded = mc.world.loadedEntityList;
        target = entitiesLoaded.get(1);

        if (target == null) return;
        System.out.println(target);

    }

    private boolean ableToAttack(Entity e) {
        if (!(e instanceof EntityPlayer) && playerOnly.getValue()) return false;
        if (mc.player.getDistance(e) <= range.getValue()) return true;
        return false;


    }
}

