package xyz.maywr.hack.client.gui.clickgui.components.items.buttons;

import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.api.util.render.RenderUtil;
import xyz.maywr.hack.client.gui.clickgui.TrollGui;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;

import java.awt.*;

public class BooleanButton extends Button {

    private final Setting setting;

    public BooleanButton(Setting setting) {
        super(setting.getName());
        this.setting = setting;
        width = 15;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRect(x, y, x + width + 7F, y + height - 0.5f, getColor(isHovering(mouseX, mouseY)));
        MaywrWare.fontManager.drawString(getName(), x + 3F, y - 1F - TrollGui.getClickGui().getTextOffset(), getState() ? getColor() : new Color(160, 160, 160, 255).getRGB());
    }

    @Override
    public void update() {
        this.setHidden(!setting.isVisible());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (isHovering(mouseX, mouseY)) {
            mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        }
    }

    @Override
    public int getHeight() {
        return 14;
    }

    public void toggle() {
        setting.setValue(!(boolean)setting.getValue());
    }

    public boolean getState() {
        return (boolean)setting.getValue();
    }
}
