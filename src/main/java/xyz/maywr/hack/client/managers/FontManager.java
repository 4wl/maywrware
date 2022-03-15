package xyz.maywr.hack.client.managers;

import xyz.maywr.hack.api.interfaces.*;
import xyz.maywr.hack.api.interfaces.Minecraftable;
import xyz.maywr.hack.api.util.font.CustomFontRenderer;
import xyz.maywr.hack.api.util.render.RenderUtil;

import java.awt.*;
import java.io.IOException;

public class FontManager implements Minecraftable {

    public CustomFontRenderer fontRenderer;
    public FontManager () {
        try {
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("assets/fontie.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        fontRenderer = new CustomFontRenderer(new Font("Montserrat Light", Font.PLAIN, 18), true, true);
    }

    public void drawString(String text, float x, float y, int color) {
            fontRenderer.drawString(text, x, y, color);
    }

    public void drawStringWithShadow (String text, float x, float y, int color) {
        fontRenderer.drawStringWithShadow(text, x, y, color);
    }

    public int getFontHeight() {
        return (fontRenderer.fontHeight / 2) - 1;
    }

    public int getStringWidth(String text) {
            return fontRenderer.getStringWidth(text);
    }

    public void drawChromoString (String text, float x, float y) {
        char[] femboy = text.toCharArray();

        int X = (int) x;
        float offset = 1f;

        for (char c : femboy) {
            int color = RenderUtil.generateRainbowFadingColor(offset, true);
            String letter = String.valueOf(c);

            this.drawString(letter, X, y, color);
            X += getStringWidth(letter); offset -= 0.3f;
        }
    }

    public void drawChromoShadowString (String text, float x, float y) {
        char[] femboy = text.toCharArray();

        int X = (int) x;
        float offset = 1f;

        for (char c : femboy) {
            int color = RenderUtil.generateRainbowFadingColor(offset, true);
            String letter = String.valueOf(c);

            this.drawStringWithShadow(letter, X, y, color);
            X += getStringWidth(letter); offset -= 0.3f;
        }
    }
}