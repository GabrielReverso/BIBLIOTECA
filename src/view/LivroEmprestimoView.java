/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import javax.swing.JOptionPane;

import controller.LivroUsuarioDAO;
import model.Livro;
import model.LivroAcervo;

/**
 *
 * @author gabriel
 */
public class LivroEmprestimoView extends javax.swing.JPanel {
    
    public Livro livro;
    public LivroAcervo livroAcervo;

    /**
     * Creates new form LivroView
     * 
     * @param livro Livro
     */
    public LivroEmprestimoView(Livro livro) {
        initComponents();
        this.livro = livro;
        txtTitulo.setText(livro.getTitulo());
        txtAutor.setText(livro.getAutor());
        txtEditora.setText(livro.getEditora());
        txtDescricao.setText(livro.getDescricao());
        txtDisponibilidade.setText("Selecione uma região para ver disponibilidade");
    }

    public LivroEmprestimoView(LivroAcervo livroAcervo) {
        initComponents();
        
        this.livroAcervo = livroAcervo;
        Livro livro = livroAcervo.getLivro();
        this.livro = livro;
        txtTitulo.setText(livro.getTitulo());
        txtAutor.setText(livro.getAutor());
        txtEditora.setText(livro.getEditora());
        txtDescricao.setText(livro.getDescricao());
        txtDisponibilidade.setText(String.format("%d", livroAcervo.getDisponibilidade()));
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
        lblDisponibilidade = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDisponibilidade = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        btnPedirEmprestado = new javax.swing.JButton();

        setBackground(new java.awt.Color(244, 231, 207));

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
            .addComponent(lblImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
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

        lblAutor.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblAutor.setText("Autor:");

        txtAutor.setEditable(false);
        txtAutor.setColumns(20);
        txtAutor.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtAutor.setLineWrap(true);
        txtAutor.setRows(1);
        txtAutor.setWrapStyleWord(true);
        txtAutor.setEnabled(false);
        jScrollPane2.setViewportView(txtAutor);

        lblEditora.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblEditora.setText("Editora:");

        txtEditora.setEditable(false);
        txtEditora.setColumns(20);
        txtEditora.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtEditora.setLineWrap(true);
        txtEditora.setRows(1);
        txtEditora.setWrapStyleWord(true);
        txtEditora.setEnabled(false);
        jScrollPane3.setViewportView(txtEditora);

        lblDescricao.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblDescricao.setText("Descrição:");

        txtDescricao.setEditable(false);
        txtDescricao.setColumns(20);
        txtDescricao.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtDescricao.setLineWrap(true);
        txtDescricao.setRows(1);
        txtDescricao.setWrapStyleWord(true);
        txtDescricao.setEnabled(false);
        jScrollPane4.setViewportView(txtDescricao);

        lblDisponibilidade.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblDisponibilidade.setText("Disponibilidade:");

        txtDisponibilidade.setEditable(false);
        txtDisponibilidade.setColumns(20);
        txtDisponibilidade.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtDisponibilidade.setLineWrap(true);
        txtDisponibilidade.setRows(1);
        txtDisponibilidade.setWrapStyleWord(true);
        txtDisponibilidade.setEnabled(false);
        jScrollPane5.setViewportView(txtDisponibilidade);

        btnPedirEmprestado.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
        btnPedirEmprestado.setText("Pedir Emprestado!");
        btnPedirEmprestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedirEmprestadoActionPerformed(evt);
            }
        });

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
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDescricao)
                                    .addComponent(lblTitulo)
                                    .addComponent(lblDisponibilidade))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAutor)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEditora))
                                .addGap(0, 12, Short.MAX_VALUE))))
                    .addComponent(jSeparator1))
                .addGap(38, 38, 38))
            .addGroup(layout.createSequentialGroup()
                .addGap(282, 282, 282)
                .addComponent(btnPedirEmprestado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEditora)
                            .addComponent(lblAutor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDisponibilidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imageBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(btnPedirEmprestado)
                .addGap(26, 26, 26)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPedirEmprestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedirEmprestadoActionPerformed
        
        if(livroAcervo != null) {
            if(livroAcervo.getDisponibilidade() > 0) {
                LivroUsuarioDAO dao = new LivroUsuarioDAO();     
                System.out.println("Entrou");
                dao.emprestimoLivro(MainFrame.usuarioLogado, livroAcervo.getLivro(), livroAcervo.getAcervo());
            } else {
                JOptionPane.showMessageDialog(null, "Livro indisponível neste acervo!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma região");
        }
    }//GEN-LAST:event_btnPedirEmprestadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPedirEmprestado;
    private javax.swing.JPanel imageBackground;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblDisponibilidade;
    private javax.swing.JLabel lblEditora;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextArea txtAutor;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextArea txtDisponibilidade;
    private javax.swing.JTextArea txtEditora;
    private javax.swing.JTextArea txtTitulo;
    // End of variables declaration//GEN-END:variables

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LivroAcervo getLivroAcervo() {
        return livroAcervo;
    }

    public void setLivroAcervo(LivroAcervo livroAcervo) {
        this.livroAcervo = livroAcervo;
    }
}
