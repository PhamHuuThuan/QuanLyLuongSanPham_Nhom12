package CustomUI;

import javax.swing.*;
import java.awt.*;

public class ImageScaler {
    private String filePath;
    private int width;
    private int height;

    public ImageScaler(String filePath, int width, int height) {
        this.filePath = filePath;
        this.width = width;
        this.height = height;
    }

    public ImageIcon getScaledImageIcon() {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(filePath));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
    public ImageIcon getScaledImageAvatar() {
        ImageIcon imageIcon = new ImageIcon(filePath);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
}