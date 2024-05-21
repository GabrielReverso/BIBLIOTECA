/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;

import javax.swing.JOptionPane;

import controller.UsuarioDAO;
import images.ImagePanel;
import model.Usuario;

/**
 *
 * @author gabriel
 */
public class MainFrame extends javax.swing.JFrame {

    protected static Usuario usuarioLogado;

    /**
     * Creates new form UsuarioView
     */
    public MainFrame() {
        this.setContentPane(new ImagePanel());
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false); 
    }

    /*
     * jTabbedPane1.setUI(new BasicTabbedPaneUI() {  
            @Override  
            protected int calculateTabAreaHeight(int tab_placement, int run_count, int max_tab_height) {  
                    return 0;  
            }  
        });
     */

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbPane = new javax.swing.JTabbedPane();
        paneLogin = new javax.swing.JPanel();
        lblLogin_paneLogin = new javax.swing.JLabel();
        lblNome_paneLogin = new javax.swing.JLabel();
        txtNome_paneLogin = new javax.swing.JTextField();
        lblSenha_paneLogin = new javax.swing.JLabel();
        btnEntrar_paneLogin = new javax.swing.JButton();
        lblNoConta_paneLogin = new javax.swing.JLabel();
        lblCadastre_paneLogin = new javax.swing.JLabel();
        txtSenha_paneLogin = new javax.swing.JPasswordField();
        paneCadastro = new javax.swing.JPanel();
        lblCadastro_paneCadastro = new javax.swing.JLabel();
        lblNome_paneCadastro = new javax.swing.JLabel();
        txtNome_paneCadastro = new javax.swing.JTextField();
        lblEmail_paneCadastro = new javax.swing.JLabel();
        txtEmail_paneCadastro = new javax.swing.JTextField();
        lblSenha_paneCadastro = new javax.swing.JLabel();
        txtSenha_paneCadastro = new javax.swing.JPasswordField();
        lblConfirmarSenha_paneCadastro = new javax.swing.JLabel();
        txtConfirmarSenha_paneCadastro = new javax.swing.JPasswordField();
        btnCadastrar_paneCadastro = new javax.swing.JButton();
        paneHome = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(700, 550));

        paneLogin.setPreferredSize(new java.awt.Dimension(700, 550));

        lblLogin_paneLogin.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblLogin_paneLogin.setText("LOGIN");

        lblNome_paneLogin.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblNome_paneLogin.setText("Nome");

        txtNome_paneLogin.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        txtNome_paneLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNome_paneLoginFocusGained(evt);
            }
        });

        lblSenha_paneLogin.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblSenha_paneLogin.setText("Senha");

        btnEntrar_paneLogin.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        btnEntrar_paneLogin.setText("Entrar");
        btnEntrar_paneLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrar_paneLoginActionPerformed(evt);
            }
        });

        lblNoConta_paneLogin.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblNoConta_paneLogin.setText("Não possui conta?");

        lblCadastre_paneLogin.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblCadastre_paneLogin.setForeground(new java.awt.Color(0, 0, 255));
        lblCadastre_paneLogin.setText("Cadastre-se");
        lblCadastre_paneLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCadastre_paneLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCadastre_paneLoginMouseClicked(evt);
            }
        });

        txtSenha_paneLogin.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        txtSenha_paneLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSenha_paneLoginFocusGained(evt);
            }
        });

        javax.swing.GroupLayout paneLoginLayout = new javax.swing.GroupLayout(paneLogin);
        paneLogin.setLayout(paneLoginLayout);
        paneLoginLayout.setHorizontalGroup(
            paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLoginLayout.createSequentialGroup()
                .addGroup(paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneLoginLayout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(lblNoConta_paneLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCadastre_paneLogin))
                    .addGroup(paneLoginLayout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(lblLogin_paneLogin))
                    .addGroup(paneLoginLayout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(btnEntrar_paneLogin)))
                .addContainerGap(219, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneLoginLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNome_paneLogin)
                    .addComponent(txtNome_paneLogin)
                    .addComponent(lblSenha_paneLogin)
                    .addComponent(txtSenha_paneLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(169, 169, 169))
        );
        paneLoginLayout.setVerticalGroup(
            paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogin_paneLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(lblNome_paneLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNome_paneLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblSenha_paneLogin)
                .addGap(18, 18, 18)
                .addComponent(txtSenha_paneLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(btnEntrar_paneLogin)
                .addGap(57, 57, 57)
                .addGroup(paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoConta_paneLogin)
                    .addComponent(lblCadastre_paneLogin))
                .addGap(41, 41, 41))
        );

        tbPane.addTab("Login", paneLogin);

        lblCadastro_paneCadastro.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblCadastro_paneCadastro.setText("CADASTRO");

        lblNome_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblNome_paneCadastro.setText("Nome");

        txtNome_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        lblEmail_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblEmail_paneCadastro.setText("Email");

        txtEmail_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        lblSenha_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblSenha_paneCadastro.setText("Senha");

        txtSenha_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        lblConfirmarSenha_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblConfirmarSenha_paneCadastro.setText("Confirmar senha");

        txtConfirmarSenha_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        btnCadastrar_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        btnCadastrar_paneCadastro.setText("Cadastrar");
        btnCadastrar_paneCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrar_paneCadastroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneCadastroLayout = new javax.swing.GroupLayout(paneCadastro);
        paneCadastro.setLayout(paneCadastroLayout);
        paneCadastroLayout.setHorizontalGroup(
            paneCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCadastroLayout.createSequentialGroup()
                .addGroup(paneCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneCadastroLayout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(btnCadastrar_paneCadastro))
                    .addGroup(paneCadastroLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addGroup(paneCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneCadastroLayout.createSequentialGroup()
                                .addComponent(lblCadastro_paneCadastro)
                                .addGap(85, 85, 85))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblConfirmarSenha_paneCadastro)
                                .addComponent(lblSenha_paneCadastro)
                                .addComponent(lblEmail_paneCadastro)
                                .addComponent(txtEmail_paneCadastro)
                                .addComponent(lblNome_paneCadastro)
                                .addComponent(txtNome_paneCadastro)
                                .addComponent(txtSenha_paneCadastro)
                                .addComponent(txtConfirmarSenha_paneCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 174, Short.MAX_VALUE))
        );
        paneCadastroLayout.setVerticalGroup(
            paneCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCadastro_paneCadastro)
                .addGap(18, 18, 18)
                .addComponent(lblNome_paneCadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNome_paneCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblEmail_paneCadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEmail_paneCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblSenha_paneCadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSenha_paneCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblConfirmarSenha_paneCadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtConfirmarSenha_paneCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnCadastrar_paneCadastro)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        tbPane.addTab("Cadastro", paneCadastro);

        javax.swing.GroupLayout paneHomeLayout = new javax.swing.GroupLayout(paneHome);
        paneHome.setLayout(paneHomeLayout);
        paneHomeLayout.setHorizontalGroup(
            paneHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        paneHomeLayout.setVerticalGroup(
            paneHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );

        tbPane.addTab("Home", paneHome);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    /*CADASTRO*/
    private void btnCadastrar_paneCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrar_paneCadastroActionPerformed
        
        if(txtSenha_paneCadastro.getText().equals(txtConfirmarSenha_paneCadastro.getText())){

            Usuario u = new Usuario(txtNome_paneCadastro.getText(), txtEmail_paneCadastro.getText(), new String(txtSenha_paneCadastro.getPassword()));
            UsuarioDAO dao = new UsuarioDAO();
    
            if (dao.inserirNovoUsuario(u) != -1) {
                JOptionPane.showMessageDialog(null, "Cadastro realizado");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
            }
            tbPane.setSelectedIndex(0);
        } else {
            lblConfirmarSenha_paneCadastro.setForeground(Color.RED);
            txtConfirmarSenha_paneCadastro.setForeground(Color.RED);
            JOptionPane.showMessageDialog(null, "Senhas não correspondem");
        }

    }//GEN-LAST:event_btnCadastrar_paneCadastroActionPerformed

    /*FIM-CADASTRO*/

    /*LOGIN*/
    private void btnEntrar_paneLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrar_paneLoginActionPerformed
        
        String nome = txtNome_paneLogin.getText();
        String senha = new String(txtSenha_paneLogin.getPassword());
        UsuarioDAO dao = new UsuarioDAO();

        if(!senha.isEmpty() && !nome.isEmpty()){
            usuarioLogado = dao.acessarUsuario(nome, senha);
            System.out.println(usuarioLogado);
            if (usuarioLogado == null){
                txtNome_paneLogin.setForeground(Color.RED);
                txtSenha_paneLogin.setForeground(Color.RED);
                JOptionPane.showMessageDialog(null, "Usuário ou Senha inválidos");
            } else {
                tbPane.setSelectedIndex(2);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos para entrar!");
        }
    }//GEN-LAST:event_btnEntrar_paneLoginActionPerformed

    private void lblCadastre_paneLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCadastre_paneLoginMouseClicked
        tbPane.setSelectedIndex(1);
    }//GEN-LAST:event_lblCadastre_paneLoginMouseClicked

    private void txtNome_paneLoginFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNome_paneLoginFocusGained
        txtNome_paneLogin.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtNome_paneLoginFocusGained

    private void txtSenha_paneLoginFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSenha_paneLoginFocusGained

        txtSenha_paneLogin.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtSenha_paneLoginFocusGained

    /*FIM-LOGIN*/

    /**                txtNome_paneLogin.setBorder(BorderFactory.createLineBorder(Color.RED));
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar_paneCadastro;
    private javax.swing.JButton btnEntrar_paneLogin;
    private javax.swing.JLabel lblCadastre_paneLogin;
    private javax.swing.JLabel lblCadastro_paneCadastro;
    private javax.swing.JLabel lblConfirmarSenha_paneCadastro;
    private javax.swing.JLabel lblEmail_paneCadastro;
    private javax.swing.JLabel lblLogin_paneLogin;
    private javax.swing.JLabel lblNoConta_paneLogin;
    private javax.swing.JLabel lblNome_paneCadastro;
    private javax.swing.JLabel lblNome_paneLogin;
    private javax.swing.JLabel lblSenha_paneCadastro;
    private javax.swing.JLabel lblSenha_paneLogin;
    private javax.swing.JPanel paneCadastro;
    private javax.swing.JPanel paneHome;
    private javax.swing.JPanel paneLogin;
    private javax.swing.JTabbedPane tbPane;
    private javax.swing.JPasswordField txtConfirmarSenha_paneCadastro;
    private javax.swing.JTextField txtEmail_paneCadastro;
    private javax.swing.JTextField txtNome_paneCadastro;
    private javax.swing.JTextField txtNome_paneLogin;
    private javax.swing.JPasswordField txtSenha_paneCadastro;
    private javax.swing.JPasswordField txtSenha_paneLogin;
    // End of variables declaration//GEN-END:variables
}