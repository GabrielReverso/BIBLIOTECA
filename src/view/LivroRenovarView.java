package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.Painter;
import javax.swing.UIDefaults;

import controller.LivroUsuarioDAO;
import model.LivroUsuario;

/**
 *
 * @author gabriel
 */
public class LivroRenovarView extends javax.swing.JPanel {

    private LivroUsuario livroUsuario;
    private Image background;

    /**
     * Creates new form LivroView
     * 
     * @param titulo
     * @param autor
     * @param dataEmprestimo
     * @param dataPrazo
     * @param status
     */
    public LivroRenovarView(LivroUsuario livroUsuario) {
        initComponents();
        this.livroUsuario = livroUsuario;
        loadImage(livroUsuario.getLivroAcervo().getLivro().getPathImagem());
        overrideLookAndFeel();
        loadBackground();
        txtTitulo.setText(livroUsuario.getLivroAcervo().getLivro().getTitulo());
        txtAutor.setText(livroUsuario.getLivroAcervo().getLivro().getAutor());
        txtDiaEmprestimo.setText(livroUsuario.getDataEmprestimo());
        txtPrazoVencimento.setText(livroUsuario.getPrazo());
        txtStatus.setText(livroUsuario.isExpirado()? "Expirado" : "Em dia");
    }

    private void overrideLookAndFeel(){
        UIDefaults overrides = new UIDefaults();
        overrides.put("TextArea[Disabled].backgroundPainter", new Painter<JTextArea>() {

            @Override
            public void paint(Graphics2D g, JTextArea field, int width, int height) {
                g.setColor(new Color(103, 46, 14));
                g.fill(new Rectangle(
                        0, 
                        0, 
                        width ,  // Tirar tudo pra ficar sem borda
                        height));
            }
        });
        txtTitulo.putClientProperty("Nimbus.Overrides", overrides);
        txtAutor.putClientProperty("Nimbus.Overrides", overrides);
        txtDiaEmprestimo.putClientProperty("Nimbus.Overrides", overrides);
        txtPrazoVencimento.putClientProperty("Nimbus.Overrides", overrides);
        txtStatus.putClientProperty("Nimbus.Overrides", overrides);
    }

    private void loadBackground(){
        try {
            this.background = ImageIO.read(new URL(getClass().getResource("/images/livroBackground.png"), "livroBackground.png"));
        } catch (IOException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background!= null) {
            // Redimensiona a imagem para cobrir toda a área do painel
            Image scaledImg = background.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
            g.drawImage(scaledImg, 0, 0, this);
        }
    }

    private void loadImage(String path){

        int width = 300;
        int height = 400;

        try {
            // Carrega e redimensiona a imagem do livro
            ImageIcon imageIconLivro = new ImageIcon(getClass().getResource(path));
            if (imageIconLivro!= null) {
                Image imageLivro = imageIconLivro.getImage();
                Image scaledImageLivro = imageLivro.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon scaledIconLivro = new ImageIcon(scaledImageLivro);
                lblImage.setIcon(scaledIconLivro);
            }
        } catch (Exception e) {
            System.err.println("Erro na obtencao da imagem: " + e.getMessage());
            lblImage.setText("                                    Image not found");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageBackground = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTitulo = new javax.swing.JTextArea();
        lblTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblPrazoVencimento = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPrazoVencimento = new javax.swing.JTextArea();
        lblStatus = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtStatus = new javax.swing.JTextArea();
        lblDiaEmprestimo = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDiaEmprestimo = new javax.swing.JTextArea();
        lblAutor = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtAutor = new javax.swing.JTextArea();
        btnRenovar = new javax.swing.JButton();

        setBackground(new java.awt.Color(67, 35, 17));
        setPreferredSize(new java.awt.Dimension(800, 530));

        imageBackground.setBackground(new java.awt.Color(204, 141, 72));
        imageBackground.setPreferredSize(new java.awt.Dimension(300, 420));

        javax.swing.GroupLayout imageBackgroundLayout = new javax.swing.GroupLayout(imageBackground);
        imageBackground.setLayout(imageBackgroundLayout);
        imageBackgroundLayout.setHorizontalGroup(
            imageBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        imageBackgroundLayout.setVerticalGroup(
            imageBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        txtTitulo.setEditable(false);
        txtTitulo.setColumns(20);
        txtTitulo.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtTitulo.setLineWrap(true);
        txtTitulo.setRows(1);
        txtTitulo.setWrapStyleWord(true);
        txtTitulo.setDisabledTextColor(new java.awt.Color(220, 220, 220));
        txtTitulo.setEnabled(false);
        jScrollPane1.setViewportView(txtTitulo);

        lblTitulo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(230, 230, 230));
        lblTitulo.setText("Título:");

        lblPrazoVencimento.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblPrazoVencimento.setForeground(new java.awt.Color(230, 230, 230));
        lblPrazoVencimento.setText("Prazo de vencimento:");

        txtPrazoVencimento.setEditable(false);
        txtPrazoVencimento.setColumns(20);
        txtPrazoVencimento.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtPrazoVencimento.setLineWrap(true);
        txtPrazoVencimento.setRows(1);
        txtPrazoVencimento.setWrapStyleWord(true);
        txtPrazoVencimento.setDisabledTextColor(new java.awt.Color(220, 220, 220));
        txtPrazoVencimento.setEnabled(false);
        jScrollPane2.setViewportView(txtPrazoVencimento);

        lblStatus.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(230, 230, 230));
        lblStatus.setText("Status:");

        txtStatus.setEditable(false);
        txtStatus.setColumns(20);
        txtStatus.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtStatus.setLineWrap(true);
        txtStatus.setRows(1);
        txtStatus.setWrapStyleWord(true);
        txtStatus.setDisabledTextColor(new java.awt.Color(220, 220, 220));
        txtStatus.setEnabled(false);
        jScrollPane3.setViewportView(txtStatus);

        lblDiaEmprestimo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblDiaEmprestimo.setForeground(new java.awt.Color(230, 230, 230));
        lblDiaEmprestimo.setText("Dia do empréstimo:");

        txtDiaEmprestimo.setEditable(false);
        txtDiaEmprestimo.setColumns(20);
        txtDiaEmprestimo.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtDiaEmprestimo.setLineWrap(true);
        txtDiaEmprestimo.setRows(1);
        txtDiaEmprestimo.setWrapStyleWord(true);
        txtDiaEmprestimo.setDisabledTextColor(new java.awt.Color(220, 220, 220));
        txtDiaEmprestimo.setEnabled(false);
        jScrollPane5.setViewportView(txtDiaEmprestimo);

        lblAutor.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblAutor.setForeground(new java.awt.Color(230, 230, 230));
        lblAutor.setText("Autor:");

        txtAutor.setEditable(false);
        txtAutor.setColumns(20);
        txtAutor.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtAutor.setLineWrap(true);
        txtAutor.setRows(1);
        txtAutor.setWrapStyleWord(true);
        txtAutor.setDisabledTextColor(new java.awt.Color(220, 220, 220));
        txtAutor.setEnabled(false);
        jScrollPane6.setViewportView(txtAutor);

        btnRenovar.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
        btnRenovar.setText("Renovar");
        btnRenovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenovarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                            .addComponent(jScrollPane6)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTitulo)
                                    .addComponent(lblPrazoVencimento)
                                    .addComponent(lblStatus)
                                    .addComponent(lblDiaEmprestimo)
                                    .addComponent(lblAutor))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPane2))))
                .addGap(38, 38, 38))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRenovar)
                .addGap(346, 346, 346))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAutor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDiaEmprestimo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPrazoVencimento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imageBackground, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(btnRenovar)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRenovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenovarActionPerformed
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            java.util.Date date = dateFormat.parse(txtPrazoVencimento.getText());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            LivroUsuarioDAO dao = new LivroUsuarioDAO();

            dao.RenovarEmprestimo(livroUsuario.getUsuario(), livroUsuario.getLivroAcervo(), sqlDate);

            JOptionPane.showMessageDialog(null, "Reinicie a página!");

        } catch (Exception e) {
            System.err.println("Erro na execução: " + e.getMessage());
        }
    }//GEN-LAST:event_btnRenovarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRenovar;
    private javax.swing.JPanel imageBackground;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblDiaEmprestimo;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblPrazoVencimento;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextArea txtAutor;
    private javax.swing.JTextArea txtDiaEmprestimo;
    private javax.swing.JTextArea txtPrazoVencimento;
    private javax.swing.JTextArea txtStatus;
    private javax.swing.JTextArea txtTitulo;
    // End of variables declaration//GEN-END:variables

    public LivroUsuario getLivroUsuario() {
        return livroUsuario;
    }

    public void setLivroUsuario(LivroUsuario livroUsuario) {
        this.livroUsuario = livroUsuario;
    }
}
