package xyz.maywr.hack.installer.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.*;

public class InstallAction implements ActionListener {

    private JTextField text;

    public InstallAction (JTextField text) {
        this.text = text;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        String path = text.getText();

        if (!path.contains("mods")) {
            path = path + "\\mods";
        }

        File currentJar = null;
        try {
            currentJar = new File(URLDecoder.decode(getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath(), "UTF8"));
        } catch (URISyntaxException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        Path original = currentJar.toPath();

        try {
            Files.copy(Paths.get(path), original, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "cant install mod", "maywrware", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }

        JOptionPane.showMessageDialog(null, "done!", "maywrware", JOptionPane.INFORMATION_MESSAGE);

    }
}
