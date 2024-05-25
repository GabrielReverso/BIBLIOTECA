/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

/**
 *
 * @author gabriel
 */
public class LivroView extends javax.swing.JPanel {

    /**
     * Creates new form LivroView
     */
    public LivroView(String titulo, String autor, String editora, String descricao) {
        initComponents();
        txtTitulo.setText(titulo);
        txtAutor.setText(autor);
        txtEditora.setText(editora);
        txtDescricao.setText(descricao);
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
        lblAutor = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAutor = new javax.swing.JTextArea();
        lblEditora = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtEditora = new javax.swing.JTextArea();
        lblDescricao = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        lblDescricao1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtEditora1 = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(244, 231, 207));

        imageBackground.setBackground(new java.awt.Color(204, 141, 72));
        imageBackground.setPreferredSize(new java.awt.Dimension(300, 420));

        javax.swing.GroupLayout imageBackgroundLayout = new javax.swing.GroupLayout(imageBackground);
        imageBackground.setLayout(imageBackgroundLayout);
        imageBackgroundLayout.setHorizontalGroup(
            imageBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imageBackgroundLayout.createSequentialGroup()
                .addGap(0, 87, Short.MAX_VALUE)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imageBackgroundLayout.setVerticalGroup(
            imageBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imageBackgroundLayout.createSequentialGroup()
                .addGap(0, 107, Short.MAX_VALUE)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtTitulo.setEditable(false);
        txtTitulo.setColumns(20);
        txtTitulo.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        txtTitulo.setLineWrap(true);
        txtTitulo.setRows(1);
        txtTitulo.setWrapStyleWord(true);
        txtTitulo.setEnabled(false);
        jScrollPane1.setViewportView(txtTitulo);

        lblTitulo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblTitulo.setText("Título:");

        lblAutor.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblAutor.setText("Autor:");

        txtAutor.setEditable(false);
        txtAutor.setColumns(20);
        txtAutor.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        txtAutor.setLineWrap(true);
        txtAutor.setRows(1);
        txtAutor.setWrapStyleWord(true);
        txtAutor.setEnabled(false);
        jScrollPane2.setViewportView(txtAutor);

        lblEditora.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblEditora.setText("Editora:");

        txtEditora.setEditable(false);
        txtEditora.setColumns(20);
        txtEditora.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        txtEditora.setLineWrap(true);
        txtEditora.setRows(1);
        txtEditora.setWrapStyleWord(true);
        txtEditora.setEnabled(false);
        jScrollPane3.setViewportView(txtEditora);

        lblDescricao.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblDescricao.setText("Descrição:");

        txtDescricao.setEditable(false);
        txtDescricao.setColumns(20);
        txtDescricao.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        txtDescricao.setLineWrap(true);
        txtDescricao.setRows(1);
        txtDescricao.setWrapStyleWord(true);
        txtDescricao.setEnabled(false);
        jScrollPane4.setViewportView(txtDescricao);

        lblDescricao1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblDescricao1.setText("Disponibilidade:");

        txtEditora1.setEditable(false);
        txtEditora1.setColumns(20);
        txtEditora1.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        txtEditora1.setLineWrap(true);
        txtEditora1.setRows(1);
        txtEditora1.setWrapStyleWord(true);
        txtEditora1.setEnabled(false);
        jScrollPane5.setViewportView(txtEditora1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblDescricao)
                                .addComponent(lblEditora)
                                .addComponent(lblAutor)
                                .addComponent(lblTitulo)
                                .addComponent(lblDescricao1)
                                .addComponent(jScrollPane3)
                                .addComponent(jScrollPane2)
                                .addComponent(jScrollPane1)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))))
                    .addComponent(jSeparator1))
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAutor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEditora)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescricao1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imageBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel imageBackground;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblDescricao1;
    private javax.swing.JLabel lblEditora;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextArea txtAutor;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextArea txtEditora;
    private javax.swing.JTextArea txtEditora1;
    private javax.swing.JTextArea txtTitulo;
    // End of variables declaration//GEN-END:variables
}