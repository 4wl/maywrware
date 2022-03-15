package xyz.maywr.hack.client.events;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;

public class DamageBlockEvent extends Event {

    private final BlockPos blockPos;
    private final EnumFacing enumFacing;

    public DamageBlockEvent(BlockPos blockPos, EnumFacing enumFacing) {
        this.blockPos = blockPos;
        this.enumFacing = enumFacing;
    }

    public BlockPos getPos() {
        return this.blockPos;
    }

    public EnumFacing getFacing() {
        return this.enumFacing;
    }

}
