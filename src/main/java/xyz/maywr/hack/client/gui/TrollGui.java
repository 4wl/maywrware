package xyz.maywr.hack.client.gui;


import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.util.render.RenderUtil;
import xyz.maywr.hack.client.gui.components.Component;
import xyz.maywr.hack.client.gui.components.items.Item;
import xyz.maywr.hack.client.gui.components.items.buttons.ModuleButton;
import xyz.maywr.hack.client.managers.FontManager;
import xyz.maywr.hack.client.modules.Module;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;
import xyz.maywr.hack.client.modules.client.ClickGui;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class TrollGui extends GuiScreen {

    public final ArrayList<Component> components = new ArrayList<>();
    private static TrollGui INSTANCE = new TrollGui();
    public static boolean isListeningForText = false;

    public TrollGui() {
        INSTANCE = this;
        load();
    }

    public static TrollGui getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new TrollGui();
        }
        return INSTANCE;
    }

    public static TrollGui getClickGui() {
        return getInstance();
    }

    private void load() {
        int x = -84;
        for (final Module.Category category : Module.Category.values()) {
            components.add(new Component(category.name(), x += 110, 20, true) {
                @Override
                public void setupItems() {
                    for (Module module : MaywrWare.moduleManager.getModulesByCategory(category)) {
                        addButton(new ModuleButton(module));
                    }
                }
            });
        }
        for (Component component : components) {
            component.getItems().sort(Comparator.comparing(Item::getName));
        }
    }

    public void updateModule(Module module) {
        for (Component component : components) {
            for (final Item item : component.getItems()) {
                if (item instanceof ModuleButton) {
                    final ModuleButton button = (ModuleButton) item;
                    final Module mod = button.getModule();
                    if (module != null && module.equals(mod)) {
                        button.initSettings();
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        checkMouseWheel();
        for (Component component : components) {
            component.drawScreen(mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int clickedButton) {
        for (Component component : components) {
            component.mouseClicked(mouseX, mouseY, clickedButton);
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int releaseButton) {
        for (Component component : components) {
            component.mouseReleased(mouseX, mouseY, releaseButton);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public final ArrayList<Component> getComponents() {
        return components;
    }

    public void checkMouseWheel() {
        int dWheel = Mouse.getDWheel();
        if (dWheel < 0) {
            for (Component component : components) {
                component.setY(component.getY() - 10);
            }
        } else if (dWheel > 0) {
            for (Component component : components) {
                component.setY(component.getY() + 10);
            }
        }
    }

    public int getTextOffset() {
        return -6;
    }

    public Component getComponentByName(String name) {
        for(Component component : this.components) {
            if(component.getName().equalsIgnoreCase(name)) {
                return component;
            }
        }
        return null;
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        for (Component component : components) {
            component.onKeyTyped(typedChar, keyCode);
        }
    }

    @Override
    public void initGui() {
        if(ClickGui.getInstance().blur.getValue()){
            if (OpenGlHelper.shadersSupported && this.mc.getRenderViewEntity() instanceof EntityPlayer) {
                if (this.mc.entityRenderer.getShaderGroup() != null) {
                    this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
                }
                this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
            }
        }
    }

    @Override
    public void onGuiClosed() {
        if (mc.entityRenderer.isShaderActive()) mc.entityRenderer.stopUseShader();
        if (this.mc.entityRenderer.getShaderGroup() != null) {
            this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
    }
}