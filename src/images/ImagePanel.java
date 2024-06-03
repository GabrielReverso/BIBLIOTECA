package images;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private Image img;

    public ImagePanel() {
        try {
            this.setLayout(new BorderLayout());
            this.img = ImageIO.read(new URL(getClass().getResource("/images/background8.png"), "background8.png"));
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

