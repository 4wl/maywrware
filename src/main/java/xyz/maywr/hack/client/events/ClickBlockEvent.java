package xyz.maywr.hack.client.events;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;


public class ClickBlockEvent extends Event {

    final BlockPos pos;
    final EnumFacing facing;
    final int stage;

    public ClickBlockEvent(int stage, BlockPos pos, EnumFacing facing) {
        this.stage = stage;
        this.pos = pos;
        this.facing = facing;
    }

    public final BlockPos getPos() {
        return pos;
    }

    public final EnumFacing getFacing() {
        return facing;
    }

    public final int getStage() {
        return stage;
    }
}
