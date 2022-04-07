package xyz.maywr.hack.client.gui.mainmenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.interfaces.Minecraftable;
import xyz.maywr.hack.api.util.font.CustomFontRenderer;

import java.awt.*;
import java.io.IOException;

public class CustomMainMenu extends GuiScreen {

    CustomFontRenderer menuFontRenderer = null;
    Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.renderEngine.bindTexture(new ResourceLocation("maywrware", "menu.png"));
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int height = scaledResolution.getScaledHeight();
        int width = scaledResolution.getScaledWidth();
        Gui.drawScaledCustomSizeModalRect(0, 0, width, height, width, height, width, height, width, height);
        menuFontRenderer.drawString("maywrware 0.2", width / 2 - (menuFontRenderer.getStringWidth("maywrware 0.2") / 2), height / 2 - (menuFontRenderer.fontHeight / 2) - (height / 3), Color.WHITE.getRGB());

    }

    public CustomMainMenu() {
        try {
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("assets/fontie2.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        menuFontRenderer = new CustomFontRenderer(new Font("Montserrat SemiBold", 0, 28), true, true);
    }
}
