package view;

import java.text.SimpleDateFormat;

import controller.LivroUsuarioDAO;
import model.LivroUsuario;

/**
 *
 * @author gabriel
 */
public class LivroRenovarView extends javax.swing.JPanel {

    private LivroUsuario livroUsuario;

    /**
     * Creates new form LivroView
     * 
     * @param titulo
     * @param autor
     * @param dataEmprestimo
     * @param dataPrazo
     * @param status
     */
    public LivroRenovarView(LivroUsuario livro) {
        initComponents();
        this.livroUsuario = livro;
        txtTitulo.setText(livro.getLivro().getTitulo());
        txtAutor.setText(livro.getLivro().getAutor());
        txtDiaEmprestimo.setText(livro.getDataEmprestimo());
        txtPrazoVencimento.setText(livro.getPrazo());
        txtStatus.setText(livro.isExpirado()? "Expirado" : "Em dia");
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

        setBackground(new java.awt.Color(244, 231, 207));
        setPreferredSize(new java.awt.Dimension(800, 450));

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
        txtTitulo.setEnabled(false);
        jScrollPane1.setViewportView(txtTitulo);

        lblTitulo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblTitulo.setText("Título:");

        lblPrazoVencimento.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblPrazoVencimento.setText("Prazo de vencimento:");

        txtPrazoVencimento.setEditable(false);
        txtPrazoVencimento.setColumns(20);
        txtPrazoVencimento.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtPrazoVencimento.setLineWrap(true);
        txtPrazoVencimento.setRows(1);
        txtPrazoVencimento.setWrapStyleWord(true);
        txtPrazoVencimento.setEnabled(false);
        jScrollPane2.setViewportView(txtPrazoVencimento);

        lblStatus.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblStatus.setText("Status:");

        txtStatus.setEditable(false);
        txtStatus.setColumns(20);
        txtStatus.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtStatus.setLineWrap(true);
        txtStatus.setRows(1);
        txtStatus.setWrapStyleWord(true);
        txtStatus.setEnabled(false);
        jScrollPane3.setViewportView(txtStatus);

        lblDiaEmprestimo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblDiaEmprestimo.setText("Dia do empréstimo:");

        txtDiaEmprestimo.setEditable(false);
        txtDiaEmprestimo.setColumns(20);
        txtDiaEmprestimo.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtDiaEmprestimo.setLineWrap(true);
        txtDiaEmprestimo.setRows(1);
        txtDiaEmprestimo.setWrapStyleWord(true);
        txtDiaEmprestimo.setEnabled(false);
        jScrollPane5.setViewportView(txtDiaEmprestimo);

        lblAutor.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblAutor.setText("Autor:");

        txtAutor.setEditable(false);
        txtAutor.setColumns(20);
        txtAutor.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtAutor.setLineWrap(true);
        txtAutor.setRows(1);
        txtAutor.setWrapStyleWord(true);
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
                .addGap(345, 345, 345))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnRenovar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRenovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenovarActionPerformed
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            java.util.Date date = dateFormat.parse(txtPrazoVencimento.getText());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            LivroUsuarioDAO dao = new LivroUsuarioDAO();

            dao.RenovarEmprestimo(livroUsuario.getUsuario(), livroUsuario.getLivro(), sqlDate);

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
