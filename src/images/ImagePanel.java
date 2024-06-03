package images;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    public static final int defaultBackground = 1;
    public static final int homeBackground = 2;
    public static final int scrollBackground = 3;
    public static final int configBackground = 4;
    private Image img;

    public ImagePanel(int backgroundType) {
        try {
            this.setLayout(new BorderLayout());
            switch (backgroundType) {
                case defaultBackground:{
                    this.img = ImageIO.read(new URL(getClass().getResource("/images/mainBackground.png"), "mainBackground.png"));
                    break;
                }
                case homeBackground: {
                    this.img = ImageIO.read(new URL(getClass().getResource("/images/homeBackground.png"), "homeBackground.png"));
                    break;
                }
                case scrollBackground: {
                    this.img = ImageIO.read(new URL(getClass().getResource("/images/scrollBackground.png"), "scrollBackground.png"));
                    break;
                }
                case configBackground: {
                    this.img = ImageIO.read(new URL(getClass().getResource("/images/configBackground.png"), "configBackground.png"));
                    break;
                }
                default:{
                    this.img = ImageIO.read(new URL(getClass().getResource("/images/mainBackground.png"), "mainBackground.png"));
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    public ImagePanel() {
        try {
            this.setLayout(new BorderLayout());
            this.img = ImageIO.read(new URL(getClass().getResource("/images/mainBackground.png"), "mainBackground.png"));
        } catch (IOException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img!= null) {
            // Redimensiona a imagem para cobrir toda a área do painel
            Image scaledImg = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
            g.drawImage(scaledImg, 0, 0, this);
        }

    }

}

