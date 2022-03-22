package xyz.maywr.hack.installer;

import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.installer.actions.InstallAction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Installer {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        try {
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, Installer.class.getClassLoader().getResourceAsStream("assets/fontie.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        final int width = 600, height = 247;
        final int x = screenSize.width / 2 - (width / 2), y = screenSize.height / 2 - (height / 2);

        JFrame window = new JFrame(MaywrWare.modid);
        window.setBounds(x, y, width, height);
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BufferedImage picImage = null, iconImage = null;
        try {
            picImage = ImageIO.read(Installer.class.getClassLoader().getResourceAsStream("assets/maywrware/funnies/fridge.png"));
            iconImage = ImageIO.read(Installer.class.getClassLoader().getResourceAsStream("assets/maywrware/funnies/anime.png"));
        } catch (IOException e) { e.printStackTrace(); }

        ImageIcon picture = new ImageIcon(picImage);
        JLabel funnyPicLabel = new JLabel(picture);
        funnyPicLabel.setBounds(390, 0, 193, 200);
        window.add(funnyPicLabel);
        window.setIconImage(iconImage);

        JLabel text = new JLabel(MaywrWare.modid + " " + MaywrWare.VERSION);
        text.setBounds(130, -10, 200, 50);
        text.setFont(new Font("Montserrat Light", Font.PLAIN, 18));
        window.add(text);

        JProgressBar bar = new JProgressBar(0, 100);
        bar.setBounds(5, 140, 380, 20);
        window.add(bar);

        JLabel pathToGameText = new JLabel("path to game");
        pathToGameText.setBounds(5, 40, 120, 20);
        pathToGameText.setFont(new Font("Montserrat Light", Font.PLAIN, 16));
        window.add(pathToGameText);

        JTextField path = new JTextField("path to yo game");
        path.setBounds(5, 65, 360, 20);
        window.add(path);

        JButton installButton = new JButton("Install");
        installButton.setBounds(5, 165, 380, 30);
        installButton.setFont(new Font("Montserrat Light", Font.PLAIN, 18));
        installButton.setFocusPainted(false);
        installButton.addActionListener(new InstallAction(path));
        window.add(installButton);

        JButton choosePath = new JButton("...");
        choosePath.setBounds(366, 64, 22, 22);
        window.add(choosePath);

        window.setVisible(true);
    }
}
