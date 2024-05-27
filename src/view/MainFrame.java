package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import controller.AcervoDAO;
import controller.LivroAcervoDAO;
import controller.LivroDAO;
import controller.UsuarioDAO;
import images.ImagePanel;
import model.Acervo;
import model.Livro;
import model.LivroAcervo;
import model.Usuario;

public class MainFrame extends javax.swing.JFrame {

    protected static Usuario usuarioLogado;

    /**
     * Creates new form UsuarioView
     */
    public MainFrame() {
        //this.setContentPane(new ImagePanel());
        initComponents();
        configureLoginPaneWithBackground();
        configureTabbedPaneUI();
        loadIcons();
        adicionarLivrosScroll();
        adicionarEmprestimoScroll();
        preencherComboBox();
        setLocationRelativeTo(null);
        setResizable(false); 
        setTitle("BIBLIOTECA");
    }

    private void configureLoginPaneWithBackground() {
        // Crie um objeto ImagePanel
        ImagePanel background = new ImagePanel();
        
        // Torna o painel de login transparente
        paneLogin.setOpaque(false);
        //paneLogin.getComponent(0).setOpaque(false); // Repita isso para todos os componentes filhos
        // Defina o layout do painel de login para BorderLayout
        paneLogin.setLayout(new BorderLayout());
        
        // Adicione o painel de login ao painel de imagem
        background.add(paneLogin, BorderLayout.CENTER);
        
        // Substitua o painel de login original pelo painel de imagem
        tabPane.remove(paneLogin);
        tabPane.insertTab("Login", null, background, null, 0);
        tabPane.setSelectedIndex(0);
    }

    private void configureTabbedPaneUI() {
        tabPane.setUI(new BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tab_placement, int run_count, int max_tab_height) {
                return -1;
            }
        });
        tabPane.setEnabledAt(0, true);
        tabPane.setEnabledAt(1, true);
        tabPane.setEnabledAt(2, true);
    }

    private void loadIcons() {

        int homeIconWidth = 40;
        int homeIconHeight = 40;
    
        try {
            // Carrega e redimensiona a imagem para iconSair
            ImageIcon imageIconSair = new ImageIcon(getClass().getResource("/images/logout.png"));
            if (imageIconSair!= null) {
                Image imageSair = imageIconSair.getImage();
                Image scaledImageSair = imageSair.getScaledInstance(homeIconWidth, homeIconHeight, Image.SCALE_SMOOTH);
                ImageIcon scaledIconSair = new ImageIcon(scaledImageSair);
                iconSair.setIcon(scaledIconSair);
            }
        } catch (Exception e) {
            System.err.println("Imagem 'logout.png' nao existe, usando fallback textual!");
            iconSair.setText("Sair");
        }

        try {
            // Carrega e redimensiona a imagem para iconConfig
            ImageIcon imageIconConfig = new ImageIcon(getClass().getResource("/images/setting.png")); 
            if (imageIconConfig!= null) {
                Image imageConfig = imageIconConfig.getImage();
                Image scaledImageConfig = imageConfig.getScaledInstance(homeIconWidth, homeIconHeight, Image.SCALE_SMOOTH);
                ImageIcon scaledIconConfig = new ImageIcon(scaledImageConfig);
                iconConfig.setIcon(scaledIconConfig);
                iconConfig2.setIcon(scaledIconConfig);
            }
        } catch (Exception e) {
            System.err.println("Imagem 'setting.png' nao existe, usando fallback textual!");
            iconConfig.setText("Conf");
            iconConfig2.setText("Conf");
        }

        try {
            // Carrega e redimensiona a imagem para iconVoltar
            ImageIcon imageIconVoltar = new ImageIcon(getClass().getResource("/images/back.png")); 
            if (imageIconVoltar!= null) {
                Image imageVoltar = imageIconVoltar.getImage();
                Image scaledImageVoltar = imageVoltar.getScaledInstance(homeIconWidth, homeIconHeight, Image.SCALE_SMOOTH);
                ImageIcon scaledIconVoltar = new ImageIcon(scaledImageVoltar);
                iconVoltar_paneVerLivros.setIcon(scaledIconVoltar);
            }
        } catch (Exception e) {
            System.err.println("Imagem 'back.png' nao existe, usando fallback textual!");
            iconConfig.setText("Back");
        }
    }

    /*******VER LIVROS*****/
    private void adicionarLivrosScroll() {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
    
        LivroDAO dao = new LivroDAO();

        List<Livro> lista = dao.obterLivros();

        for (Livro l : lista) {
            LivroView panel = new LivroView(l.getTitulo(), l.getAutor(), l.getEditora(), l.getDescricao());
            container.add(panel);
        }    
        scrollVerLivros.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }

    private void adicionarVerLivrosScroll(String local) {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
    
        LivroAcervoDAO dao = new LivroAcervoDAO();

        List<LivroAcervo> lista = dao.obterLivrosDoAcervo(local);

        for (LivroAcervo l : lista) {
            LivroView panel = new LivroView(l.getLivro().getTitulo(), 
                                            l.getLivro().getAutor(), 
                                            l.getLivro().getEditora(), 
                                            l.getLivro().getDescricao(), 
                                            l.getDisponibilidade());
            container.add(panel);
        }    
        scrollVerLivros.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }

    private void adicionarBuscaScroll(String busca) {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
    
        LivroAcervoDAO dao = new LivroAcervoDAO();

        List<Livro> lista = dao.buscarLivrosDoAcervo(busca);

        for (Livro l : lista) {
            LivroView panel = new LivroView(l.getTitulo(), l.getAutor(), l.getEditora(), l.getDescricao());
            container.add(panel);
        }    
        scrollVerLivros.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }

    private void adicionarBuscaScroll(String local, String busca) {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
    
        LivroAcervoDAO dao = new LivroAcervoDAO();

        List<LivroAcervo> lista = dao.buscarLivrosDoAcervo(local, busca);

        for (LivroAcervo l : lista) {
            LivroView panel = new LivroView(l.getLivro().getTitulo(), 
                                            l.getLivro().getAutor(), 
                                            l.getLivro().getEditora(), 
                                            l.getLivro().getDescricao(), 
                                            l.getDisponibilidade());
            container.add(panel);
        }    
        scrollVerLivros.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }

    /*******EMPRESTIMO*****/
    private void adicionarEmprestimoScroll() {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
    
        LivroDAO dao = new LivroDAO();

        List<Livro> lista = dao.obterLivros();

        for (Livro l : lista) {
            LivroEmprestimoView panel = new LivroEmprestimoView(l);
            container.add(panel);
        }    
        scrollEmprestimo.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }

    private void adicionarEmprestimoScroll(String local) {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
    
        LivroAcervoDAO dao = new LivroAcervoDAO();

        List<LivroAcervo> lista = dao.obterLivrosDoAcervo(local);

        for (LivroAcervo l : lista) {
            LivroEmprestimoView panel = new LivroEmprestimoView(l);
            container.add(panel);
        }    
        scrollEmprestimo.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }
    private void adicionarEmprestimoBuscaScroll(String busca) {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
    
        LivroAcervoDAO dao = new LivroAcervoDAO();

        List<Livro> lista = dao.buscarLivrosDoAcervo(busca);

        for (Livro l : lista) {
            LivroEmprestimoView panel = new LivroEmprestimoView(l);
            container.add(panel);
        }    
        scrollEmprestimo.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }

    private void adicionarEmprestimoBuscaScroll(String local, String busca) {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
    
        LivroAcervoDAO dao = new LivroAcervoDAO();

        List<LivroAcervo> lista = dao.buscarLivrosDoAcervo(local, busca);

        for (LivroAcervo l : lista) {
            LivroEmprestimoView panel = new LivroEmprestimoView(l);
            container.add(panel);
        }    
        scrollEmprestimo.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }

    //COMBOBOX
    private void preencherComboBox(){
        DefaultComboBoxModel<String> m = new DefaultComboBoxModel<>();

        AcervoDAO dao = new AcervoDAO();

        List<Acervo> lista = dao.obterAcervos();

        for(Acervo a : lista) {
            m.addElement(a.toString());
        }
        cbxSelecioneRegiao_paneVerLivros.setModel(m);
        cbxSelecioneRegiao_paneVerLivros.setSelectedIndex(-1);
        cbxSelecioneRegiao_paneEmprestimo.setModel(m);
        cbxSelecioneRegiao_paneEmprestimo.setSelectedIndex(-1);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPane = new javax.swing.JTabbedPane();
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
        lblRetornarLogin_paneCadastro = new javax.swing.JLabel();
        paneHome = new javax.swing.JPanel();
        lblMenu_paneMenu = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblBusca_paneMenu = new javax.swing.JLabel();
        btnBuscar_paneMenu = new javax.swing.JButton();
        lblOperacoes_paneMenu = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnEmprestimo_paneMenu = new javax.swing.JButton();
        btnDevolucao_paneMenu = new javax.swing.JButton();
        btnRenovar_paneMenu = new javax.swing.JButton();
        btnLivrosEscolhidos_paneMenu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricaoBusca_paneMenu = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricaoOp_paneMenu = new javax.swing.JTextArea();
        iconSair = new javax.swing.JLabel();
        iconConfig = new javax.swing.JLabel();
        paneVerLivros = new javax.swing.JPanel();
        scrollVerLivros = new javax.swing.JScrollPane();
        lblLivros_paneVerLivros = new javax.swing.JLabel();
        cbxSelecioneRegiao_paneVerLivros = new javax.swing.JComboBox<>();
        lblSelecioneRegiao_paneVerLivros = new javax.swing.JLabel();
        txtBucarTitulo_paneVerLivros = new javax.swing.JTextField();
        lblBuscarTitulo_paneVerLivros = new javax.swing.JLabel();
        iconVoltar_paneVerLivros = new javax.swing.JLabel();
        iconConfig2 = new javax.swing.JLabel();
        btnLimpar_paneVerLivros = new javax.swing.JButton();
        paneEmprestimo = new javax.swing.JPanel();
        scrollEmprestimo = new javax.swing.JScrollPane();
        lblLivros_paneEmprestimo = new javax.swing.JLabel();
        cbxSelecioneRegiao_paneEmprestimo = new javax.swing.JComboBox<>();
        lblSelecioneRegiao_paneEmprestimo = new javax.swing.JLabel();
        txtBucarTitulo_paneEmprestimo = new javax.swing.JTextField();
        lblBuscarTitulo_paneEmprestimo = new javax.swing.JLabel();
        iconVoltar_paneEmprestimo = new javax.swing.JLabel();
        iconConfig4 = new javax.swing.JLabel();
        btnLimpar_paneEmprestimo = new javax.swing.JButton();
        paneLivrosEmprestados = new javax.swing.JPanel();
        scrollLivrosEmprestados = new javax.swing.JScrollPane();
        lblLivros_paneLivrosEmprestados = new javax.swing.JLabel();
        iconVoltar_paneLivrosEmprestados = new javax.swing.JLabel();
        iconConfig3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(700, 550));

        paneLogin.setPreferredSize(new java.awt.Dimension(800, 600));

        lblLogin_paneLogin.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblLogin_paneLogin.setText("LOGIN");

        lblNome_paneLogin.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblNome_paneLogin.setText("Nome ou Email");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneLoginLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblLogin_paneLogin)
                .addGap(341, 341, 341))
            .addGroup(paneLoginLayout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addGroup(paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSenha_paneLogin)
                    .addComponent(txtSenha_paneLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                    .addComponent(lblNome_paneLogin)
                    .addComponent(txtNome_paneLogin))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneLoginLayout.createSequentialGroup()
                .addContainerGap(266, Short.MAX_VALUE)
                .addGroup(paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneLoginLayout.createSequentialGroup()
                        .addComponent(lblNoConta_paneLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCadastre_paneLogin)
                        .addGap(265, 265, 265))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneLoginLayout.createSequentialGroup()
                        .addComponent(btnEntrar_paneLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(334, 334, 334))))
        );
        paneLoginLayout.setVerticalGroup(
            paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogin_paneLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(lblNome_paneLogin)
                .addGap(18, 18, 18)
                .addComponent(txtNome_paneLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblSenha_paneLogin)
                .addGap(18, 18, 18)
                .addComponent(txtSenha_paneLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(btnEntrar_paneLogin)
                .addGap(62, 62, 62)
                .addGroup(paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCadastre_paneLogin)
                    .addComponent(lblNoConta_paneLogin))
                .addGap(38, 38, 38))
        );

        tabPane.addTab("Login", paneLogin);

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

        lblRetornarLogin_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblRetornarLogin_paneCadastro.setForeground(new java.awt.Color(0, 0, 255));
        lblRetornarLogin_paneCadastro.setText("Retornar ao Login");
        lblRetornarLogin_paneCadastro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRetornarLogin_paneCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRetornarLogin_paneCadastroMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout paneCadastroLayout = new javax.swing.GroupLayout(paneCadastro);
        paneCadastro.setLayout(paneCadastroLayout);
        paneCadastroLayout.setHorizontalGroup(
            paneCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneCadastroLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(paneCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCadastrar_paneCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRetornarLogin_paneCadastro))
                .addGap(318, 318, 318))
            .addGroup(paneCadastroLayout.createSequentialGroup()
                .addGroup(paneCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneCadastroLayout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addGroup(paneCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConfirmarSenha_paneCadastro)
                            .addComponent(lblSenha_paneCadastro)
                            .addComponent(lblEmail_paneCadastro)
                            .addComponent(lblNome_paneCadastro)))
                    .addGroup(paneCadastroLayout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(lblCadastro_paneCadastro)))
                .addContainerGap(307, Short.MAX_VALUE))
            .addGroup(paneCadastroLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(paneCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtSenha_paneCadastro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                    .addComponent(txtEmail_paneCadastro, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNome_paneCadastro, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtConfirmarSenha_paneCadastro, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paneCadastroLayout.setVerticalGroup(
            paneCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCadastro_paneCadastro)
                .addGap(31, 31, 31)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnCadastrar_paneCadastro)
                .addGap(38, 38, 38)
                .addComponent(lblRetornarLogin_paneCadastro)
                .addGap(32, 32, 32))
        );

        tabPane.addTab("Cadastro", paneCadastro);

        lblMenu_paneMenu.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblMenu_paneMenu.setText("MENU DE OPÇÕES");

        jSeparator1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N

        lblBusca_paneMenu.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        lblBusca_paneMenu.setForeground(new java.awt.Color(153, 153, 153));
        lblBusca_paneMenu.setText("Busca");

        btnBuscar_paneMenu.setFont(new java.awt.Font("sansserif", 0, 22)); // NOI18N
        btnBuscar_paneMenu.setText("Buscar Livros Disponíveis");
        btnBuscar_paneMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscar_paneMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscar_paneMenuMouseExited(evt);
            }
        });
        btnBuscar_paneMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar_paneMenuActionPerformed(evt);
            }
        });

        lblOperacoes_paneMenu.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        lblOperacoes_paneMenu.setForeground(new java.awt.Color(153, 153, 153));
        lblOperacoes_paneMenu.setText("Operações");

        jSeparator2.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N

        btnEmprestimo_paneMenu.setFont(new java.awt.Font("sansserif", 0, 22)); // NOI18N
        btnEmprestimo_paneMenu.setText("Empréstimo de Livros ");
        btnEmprestimo_paneMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEmprestimo_paneMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEmprestimo_paneMenuMouseExited(evt);
            }
        });
        btnEmprestimo_paneMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmprestimo_paneMenuActionPerformed(evt);
            }
        });

        btnDevolucao_paneMenu.setFont(new java.awt.Font("sansserif", 0, 22)); // NOI18N
        btnDevolucao_paneMenu.setText("Devolução de Livros");
        btnDevolucao_paneMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDevolucao_paneMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDevolucao_paneMenuMouseExited(evt);
            }
        });
        btnDevolucao_paneMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolucao_paneMenuActionPerformed(evt);
            }
        });

        btnRenovar_paneMenu.setFont(new java.awt.Font("sansserif", 0, 22)); // NOI18N
        btnRenovar_paneMenu.setText("Renovar ");
        btnRenovar_paneMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRenovar_paneMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRenovar_paneMenuMouseExited(evt);
            }
        });
        btnRenovar_paneMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenovar_paneMenuActionPerformed(evt);
            }
        });

        btnLivrosEscolhidos_paneMenu.setFont(new java.awt.Font("sansserif", 0, 22)); // NOI18N
        btnLivrosEscolhidos_paneMenu.setText("Ver Livros Emprestados");
        btnLivrosEscolhidos_paneMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLivrosEscolhidos_paneMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLivrosEscolhidos_paneMenuMouseExited(evt);
            }
        });
        btnLivrosEscolhidos_paneMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLivrosEscolhidos_paneMenuActionPerformed(evt);
            }
        });

        txtDescricaoBusca_paneMenu.setEditable(false);
        txtDescricaoBusca_paneMenu.setColumns(1);
        txtDescricaoBusca_paneMenu.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        txtDescricaoBusca_paneMenu.setLineWrap(true);
        txtDescricaoBusca_paneMenu.setRows(2);
        txtDescricaoBusca_paneMenu.setWrapStyleWord(true);
        txtDescricaoBusca_paneMenu.setEnabled(false);
        jScrollPane1.setViewportView(txtDescricaoBusca_paneMenu);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtDescricaoOp_paneMenu.setEditable(false);
        txtDescricaoOp_paneMenu.setColumns(1);
        txtDescricaoOp_paneMenu.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        txtDescricaoOp_paneMenu.setLineWrap(true);
        txtDescricaoOp_paneMenu.setRows(5);
        txtDescricaoOp_paneMenu.setWrapStyleWord(true);
        txtDescricaoOp_paneMenu.setEnabled(false);
        jScrollPane2.setViewportView(txtDescricaoOp_paneMenu);

        iconSair.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        iconSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconSairMouseClicked(evt);
            }
        });

        iconConfig.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        iconConfig.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout paneHomeLayout = new javax.swing.GroupLayout(paneHome);
        paneHome.setLayout(paneHomeLayout);
        paneHomeLayout.setHorizontalGroup(
            paneHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneHomeLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(paneHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(paneHomeLayout.createSequentialGroup()
                        .addGroup(paneHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnLivrosEscolhidos_paneMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscar_paneMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
                        .addGap(67, 67, 67)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(paneHomeLayout.createSequentialGroup()
                            .addGroup(paneHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnDevolucao_paneMenu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEmprestimo_paneMenu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                                .addComponent(btnRenovar_paneMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(paneHomeLayout.createSequentialGroup()
                            .addComponent(iconSair, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(154, 154, 154)
                            .addComponent(lblMenu_paneMenu)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(iconConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(paneHomeLayout.createSequentialGroup()
                            .addComponent(lblOperacoes_paneMenu)
                            .addGap(18, 18, 18)
                            .addComponent(jSeparator2))
                        .addGroup(paneHomeLayout.createSequentialGroup()
                            .addComponent(lblBusca_paneMenu)
                            .addGap(18, 18, 18)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        paneHomeLayout.setVerticalGroup(
            paneHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMenu_paneMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iconSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iconConfig, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(paneHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBusca_paneMenu)
                    .addGroup(paneHomeLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(paneHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(paneHomeLayout.createSequentialGroup()
                        .addComponent(btnBuscar_paneMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLivrosEscolhidos_paneMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(paneHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOperacoes_paneMenu)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(paneHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneHomeLayout.createSequentialGroup()
                        .addComponent(btnEmprestimo_paneMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDevolucao_paneMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRenovar_paneMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        tabPane.addTab("Home", paneHome);

        scrollVerLivros.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        lblLivros_paneVerLivros.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblLivros_paneVerLivros.setText("LIVROS");

        cbxSelecioneRegiao_paneVerLivros.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        cbxSelecioneRegiao_paneVerLivros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxSelecioneRegiao_paneVerLivros.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSelecioneRegiao_paneVerLivrosItemStateChanged(evt);
            }
        });

        lblSelecioneRegiao_paneVerLivros.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lblSelecioneRegiao_paneVerLivros.setText("Selecione a região");

        txtBucarTitulo_paneVerLivros.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        txtBucarTitulo_paneVerLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBucarTitulo_paneVerLivrosActionPerformed(evt);
            }
        });

        lblBuscarTitulo_paneVerLivros.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lblBuscarTitulo_paneVerLivros.setText("Buscar por título:");

        iconVoltar_paneVerLivros.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        iconVoltar_paneVerLivros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconVoltar_paneVerLivros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconVoltar_paneVerLivrosMouseClicked(evt);
            }
        });

        iconConfig2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        iconConfig2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnLimpar_paneVerLivros.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        btnLimpar_paneVerLivros.setText("Limpar");
        btnLimpar_paneVerLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpar_paneVerLivrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneVerLivrosLayout = new javax.swing.GroupLayout(paneVerLivros);
        paneVerLivros.setLayout(paneVerLivrosLayout);
        paneVerLivrosLayout.setHorizontalGroup(
            paneVerLivrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollVerLivros)
            .addGroup(paneVerLivrosLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(paneVerLivrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneVerLivrosLayout.createSequentialGroup()
                        .addComponent(iconVoltar_paneVerLivros, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(250, 250, 250)
                        .addComponent(lblLivros_paneVerLivros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(iconConfig2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneVerLivrosLayout.createSequentialGroup()
                        .addGroup(paneVerLivrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxSelecioneRegiao_paneVerLivros, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSelecioneRegiao_paneVerLivros))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(paneVerLivrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneVerLivrosLayout.createSequentialGroup()
                                .addComponent(txtBucarTitulo_paneVerLivros, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                .addComponent(btnLimpar_paneVerLivros))
                            .addGroup(paneVerLivrosLayout.createSequentialGroup()
                                .addComponent(lblBuscarTitulo_paneVerLivros)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(35, 35, 35))
        );
        paneVerLivrosLayout.setVerticalGroup(
            paneVerLivrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneVerLivrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneVerLivrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconConfig2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneVerLivrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblLivros_paneVerLivros)
                        .addComponent(iconVoltar_paneVerLivros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(paneVerLivrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBuscarTitulo_paneVerLivros)
                    .addComponent(lblSelecioneRegiao_paneVerLivros))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneVerLivrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSelecioneRegiao_paneVerLivros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBucarTitulo_paneVerLivros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpar_paneVerLivros, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollVerLivros, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
        );

        tabPane.addTab("Ver Livros", paneVerLivros);

        scrollEmprestimo.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        lblLivros_paneEmprestimo.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblLivros_paneEmprestimo.setText("EMPRÉSTIMO");

        cbxSelecioneRegiao_paneEmprestimo.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        cbxSelecioneRegiao_paneEmprestimo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxSelecioneRegiao_paneEmprestimo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSelecioneRegiao_paneEmprestimoItemStateChanged(evt);
            }
        });

        lblSelecioneRegiao_paneEmprestimo.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lblSelecioneRegiao_paneEmprestimo.setText("Selecione a região");

        txtBucarTitulo_paneEmprestimo.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        txtBucarTitulo_paneEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBucarTitulo_paneEmprestimoActionPerformed(evt);
            }
        });

        lblBuscarTitulo_paneEmprestimo.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lblBuscarTitulo_paneEmprestimo.setText("Buscar por título:");

        iconVoltar_paneEmprestimo.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        iconVoltar_paneEmprestimo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconVoltar_paneEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconVoltar_paneEmprestimoMouseClicked(evt);
            }
        });

        iconConfig4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        iconConfig4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnLimpar_paneEmprestimo.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        btnLimpar_paneEmprestimo.setText("Limpar");
        btnLimpar_paneEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpar_paneEmprestimoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneEmprestimoLayout = new javax.swing.GroupLayout(paneEmprestimo);
        paneEmprestimo.setLayout(paneEmprestimoLayout);
        paneEmprestimoLayout.setHorizontalGroup(
            paneEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollEmprestimo)
            .addGroup(paneEmprestimoLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(paneEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneEmprestimoLayout.createSequentialGroup()
                        .addComponent(iconVoltar_paneEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(195, 195, 195)
                        .addComponent(lblLivros_paneEmprestimo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(iconConfig4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneEmprestimoLayout.createSequentialGroup()
                        .addGroup(paneEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxSelecioneRegiao_paneEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSelecioneRegiao_paneEmprestimo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(paneEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneEmprestimoLayout.createSequentialGroup()
                                .addComponent(txtBucarTitulo_paneEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                .addComponent(btnLimpar_paneEmprestimo))
                            .addGroup(paneEmprestimoLayout.createSequentialGroup()
                                .addComponent(lblBuscarTitulo_paneEmprestimo)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(35, 35, 35))
        );
        paneEmprestimoLayout.setVerticalGroup(
            paneEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneEmprestimoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconConfig4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblLivros_paneEmprestimo)
                        .addComponent(iconVoltar_paneEmprestimo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(paneEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBuscarTitulo_paneEmprestimo)
                    .addComponent(lblSelecioneRegiao_paneEmprestimo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSelecioneRegiao_paneEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBucarTitulo_paneEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpar_paneEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollEmprestimo, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
        );

        tabPane.addTab("Emprestimo", paneEmprestimo);

        scrollLivrosEmprestados.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        lblLivros_paneLivrosEmprestados.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblLivros_paneLivrosEmprestados.setText("LIVROS EMPRESTADOS");

        iconVoltar_paneLivrosEmprestados.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        iconVoltar_paneLivrosEmprestados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconVoltar_paneLivrosEmprestados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconVoltar_paneLivrosEmprestadosMouseClicked(evt);
            }
        });

        iconConfig3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        iconConfig3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout paneLivrosEmprestadosLayout = new javax.swing.GroupLayout(paneLivrosEmprestados);
        paneLivrosEmprestados.setLayout(paneLivrosEmprestadosLayout);
        paneLivrosEmprestadosLayout.setHorizontalGroup(
            paneLivrosEmprestadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollLivrosEmprestados)
            .addGroup(paneLivrosEmprestadosLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(iconVoltar_paneLivrosEmprestados, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114)
                .addComponent(lblLivros_paneLivrosEmprestados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(iconConfig3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        paneLivrosEmprestadosLayout.setVerticalGroup(
            paneLivrosEmprestadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneLivrosEmprestadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneLivrosEmprestadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconConfig3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneLivrosEmprestadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblLivros_paneLivrosEmprestados)
                        .addComponent(iconVoltar_paneLivrosEmprestados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollLivrosEmprestados, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE))
        );

        tabPane.addTab("Livros Emp.", paneLivrosEmprestados);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPane, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * MÉTODOS DE EVENTOS
     * 
     **/
    /*CADASTRO*************************************************************************/
    private void btnCadastrar_paneCadastroActionPerformed(java.awt.event.ActionEvent evt) {                                                          
        
        if(txtSenha_paneCadastro.getText().equals(txtConfirmarSenha_paneCadastro.getText())){

            Usuario u = new Usuario(txtNome_paneCadastro.getText(), txtEmail_paneCadastro.getText(), new String(txtSenha_paneCadastro.getPassword()));
            UsuarioDAO dao = new UsuarioDAO();
    
            if (dao.inserirNovoUsuario(u) != -1) {
                JOptionPane.showMessageDialog(null, "Cadastro realizado");
                txtNome_paneCadastro.setText("");
                txtEmail_paneCadastro.setText("");
                txtSenha_paneCadastro.setText("");
                txtConfirmarSenha_paneCadastro.setText("");
                tabPane.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
            }
        } else {
            lblConfirmarSenha_paneCadastro.setForeground(Color.RED);
            txtConfirmarSenha_paneCadastro.setForeground(Color.RED);
            JOptionPane.showMessageDialog(null, "Senhas não correspondem");
        }

    }

    private void lblRetornarLogin_paneCadastroMouseClicked(java.awt.event.MouseEvent evt) {   
        txtNome_paneCadastro.setText("");
        txtEmail_paneCadastro.setText("");
        txtSenha_paneCadastro.setText("");
        txtConfirmarSenha_paneCadastro.setText("");                                                        
        tabPane.setSelectedIndex(0);
        tabPane.setEnabledAt(0, true);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, false);
    }

    /*FIM-CADASTRO*************************************************************************/

    /*LOGIN*************************************************************************/
    private void btnEntrar_paneLoginActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        
        String nomeEmail = txtNome_paneLogin.getText();
        String senha = new String(txtSenha_paneLogin.getPassword());
        UsuarioDAO dao = new UsuarioDAO();

        if(!senha.isEmpty() && !nomeEmail.isEmpty()){
            usuarioLogado = dao.acessarUsuario(nomeEmail, senha);
            System.out.println(usuarioLogado);
            if (usuarioLogado == null){
                txtNome_paneLogin.setForeground(Color.RED);
                txtSenha_paneLogin.setForeground(Color.RED);
                JOptionPane.showMessageDialog(null, "Usuário ou Senha inválidos");
            } else {
                txtNome_paneLogin.setText("");
                txtSenha_paneLogin.setText("");
                tabPane.setSelectedIndex(2);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos para entrar!");
        }
    }

    private void lblCadastre_paneLoginMouseClicked(java.awt.event.MouseEvent evt) {                                                   
        tabPane.setSelectedIndex(1);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, true);
        tabPane.setEnabledAt(2, false);
    }

    private void txtNome_paneLoginFocusGained(java.awt.event.FocusEvent evt) {                                              
        txtNome_paneLogin.setForeground(Color.BLACK);
    }

    private void txtSenha_paneLoginFocusGained(java.awt.event.FocusEvent evt) {                                               

        txtSenha_paneLogin.setForeground(Color.BLACK);
    }

    /*FIM-LOGIN*************************************************************************/

    /*HOME*************************************************************************/
    private void btnBuscar_paneMenuActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        tabPane.setSelectedIndex(3);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, false);
    }

    private void btnEmprestimo_paneMenuActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        tabPane.setSelectedIndex(4);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, false);
    }

    private void btnDevolucao_paneMenuActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        // TODO add your handling code here:
    }

    private void btnRenovar_paneMenuActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // TODO add your handling code here:
    }

    private void btnLivrosEscolhidos_paneMenuActionPerformed(java.awt.event.ActionEvent evt) {                                                             
        // TODO add your handling code here:
    }

    private void btnBuscar_paneMenuMouseEntered(java.awt.event.MouseEvent evt) {                                                
        txtDescricaoBusca_paneMenu.setText("Veja todos os livros disponíveis no acervo!");
    }

    private void btnBuscar_paneMenuMouseExited(java.awt.event.MouseEvent evt) {
        txtDescricaoBusca_paneMenu.setText("");
    }

    private void btnLivrosEscolhidos_paneMenuMouseEntered(java.awt.event.MouseEvent evt) {
        txtDescricaoBusca_paneMenu.setText("Veja os livros em sua posse e o prazo de devulução!");
    }

    private void btnLivrosEscolhidos_paneMenuMouseExited(java.awt.event.MouseEvent evt) {
        txtDescricaoBusca_paneMenu.setText("");
    }

    private void btnEmprestimo_paneMenuMouseEntered(java.awt.event.MouseEvent evt) {
        txtDescricaoOp_paneMenu.setText("Clique para realizar o empréstimo de algum livro de seu interesse!");
    }

    private void btnEmprestimo_paneMenuMouseExited(java.awt.event.MouseEvent evt) {
        txtDescricaoOp_paneMenu.setText("");
    }

    private void btnDevolucao_paneMenuMouseEntered(java.awt.event.MouseEvent evt) {
        txtDescricaoOp_paneMenu.setText("Clique para realizar a devolução de um livro em sua posse!");
    }

    private void btnDevolucao_paneMenuMouseExited(java.awt.event.MouseEvent evt) {
        txtDescricaoOp_paneMenu.setText("");
    }

    private void btnRenovar_paneMenuMouseEntered(java.awt.event.MouseEvent evt) {
        txtDescricaoOp_paneMenu.setText("Renove aqui o empréstimo do livro!");
    }

    private void btnRenovar_paneMenuMouseExited(java.awt.event.MouseEvent evt) {
        txtDescricaoOp_paneMenu.setText("");
    }      
    
    private void iconSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconSairMouseClicked
        int resposta = JOptionPane.showConfirmDialog(
             null,
             "Você precisará fazer o login novamente ao sair",
             "Deseja realmente sair?",
             JOptionPane.YES_NO_OPTION
             );

        if(resposta == JOptionPane.YES_OPTION){
            tabPane.setSelectedIndex(0);
            tabPane.setEnabledAt(0, true);
            tabPane.setEnabledAt(1, false);
            tabPane.setEnabledAt(2, false);
            usuarioLogado = null;
        }

    }//GEN-LAST:event_iconSairMouseClicked

    /*FIM-HOME*************************************************************************/

    /*VER LIVROS*************************************************************************/
    private void iconVoltar_paneVerLivrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconVoltar_paneVerLivrosMouseClicked
        tabPane.setSelectedIndex(2);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, true);
    }//GEN-LAST:event_iconVoltar_paneVerLivrosMouseClicked

    private void cbxSelecioneRegiao_paneVerLivrosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSelecioneRegiao_paneVerLivrosItemStateChanged
        
        int posicao = cbxSelecioneRegiao_paneVerLivros.getSelectedIndex();

        if (posicao >= 0){
            String local = cbxSelecioneRegiao_paneVerLivros.getSelectedItem().toString();
            adicionarVerLivrosScroll(local);
        }
    }//GEN-LAST:event_cbxSelecioneRegiao_paneVerLivrosItemStateChanged

    private void txtBucarTitulo_paneVerLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBucarTitulo_paneVerLivrosActionPerformed
        
        String busca = txtBucarTitulo_paneVerLivros.getText();
        String local = "";
        int posicao = cbxSelecioneRegiao_paneVerLivros.getSelectedIndex();

        if (posicao >= 0){
            local = cbxSelecioneRegiao_paneVerLivros.getSelectedItem().toString();
        }

        if(!busca.isEmpty()){

            if(!local.isEmpty()){
                adicionarBuscaScroll(local,busca);
            } else {
                adicionarBuscaScroll(busca);
            }
        }

    }//GEN-LAST:event_txtBucarTitulo_paneVerLivrosActionPerformed

    private void btnLimpar_paneVerLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpar_paneVerLivrosActionPerformed
        
        txtBucarTitulo_paneVerLivros.setText("");
        cbxSelecioneRegiao_paneVerLivros.setSelectedIndex(-1);
        adicionarLivrosScroll();
    }//GEN-LAST:event_btnLimpar_paneVerLivrosActionPerformed

    /*LIVROS EMPRESTADOS*************************************************************************/
    private void iconVoltar_paneLivrosEmprestadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconVoltar_paneLivrosEmprestadosMouseClicked
        tabPane.setSelectedIndex(2);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, true);
    }//GEN-LAST:event_iconVoltar_paneLivrosEmprestadosMouseClicked
    
    /*EMPRESTIMO*************************************************************************/
    private void cbxSelecioneRegiao_paneEmprestimoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSelecioneRegiao_paneEmprestimoItemStateChanged
        int posicao = cbxSelecioneRegiao_paneEmprestimo.getSelectedIndex();

        if (posicao >= 0){
            String local = cbxSelecioneRegiao_paneEmprestimo.getSelectedItem().toString();
            adicionarEmprestimoScroll(local);
        }
    }//GEN-LAST:event_cbxSelecioneRegiao_paneEmprestimoItemStateChanged

    private void txtBucarTitulo_paneEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBucarTitulo_paneEmprestimoActionPerformed
        
        String busca = txtBucarTitulo_paneEmprestimo.getText();
        String local = "";
        int posicao = cbxSelecioneRegiao_paneEmprestimo.getSelectedIndex();

        if (posicao >= 0){
            local = cbxSelecioneRegiao_paneEmprestimo.getSelectedItem().toString();
        }

        if(!busca.isEmpty()){

            if(!local.isEmpty()){
                adicionarEmprestimoBuscaScroll(local,busca);
            } else {
                adicionarEmprestimoBuscaScroll(busca);
            }
        }
    }//GEN-LAST:event_txtBucarTitulo_paneEmprestimoActionPerformed

    private void iconVoltar_paneEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconVoltar_paneEmprestimoMouseClicked
        tabPane.setSelectedIndex(2);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, true);
    }//GEN-LAST:event_iconVoltar_paneEmprestimoMouseClicked

    private void btnLimpar_paneEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpar_paneEmprestimoActionPerformed
        txtBucarTitulo_paneEmprestimo.setText("");
        cbxSelecioneRegiao_paneEmprestimo.setSelectedIndex(-1);
        adicionarEmprestimoScroll();
    }//GEN-LAST:event_btnLimpar_paneEmprestimoActionPerformed

    
    /**             
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
    private javax.swing.JButton btnBuscar_paneMenu;
    private javax.swing.JButton btnCadastrar_paneCadastro;
    private javax.swing.JButton btnDevolucao_paneMenu;
    private javax.swing.JButton btnEmprestimo_paneMenu;
    private javax.swing.JButton btnEntrar_paneLogin;
    private javax.swing.JButton btnLimpar_paneEmprestimo;
    private javax.swing.JButton btnLimpar_paneVerLivros;
    private javax.swing.JButton btnLivrosEscolhidos_paneMenu;
    private javax.swing.JButton btnRenovar_paneMenu;
    private javax.swing.JComboBox<String> cbxSelecioneRegiao_paneEmprestimo;
    private javax.swing.JComboBox<String> cbxSelecioneRegiao_paneVerLivros;
    private javax.swing.JLabel iconConfig;
    private javax.swing.JLabel iconConfig2;
    private javax.swing.JLabel iconConfig3;
    private javax.swing.JLabel iconConfig4;
    private javax.swing.JLabel iconSair;
    private javax.swing.JLabel iconVoltar_paneEmprestimo;
    private javax.swing.JLabel iconVoltar_paneLivrosEmprestados;
    private javax.swing.JLabel iconVoltar_paneVerLivros;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblBusca_paneMenu;
    private javax.swing.JLabel lblBuscarTitulo_paneEmprestimo;
    private javax.swing.JLabel lblBuscarTitulo_paneVerLivros;
    private javax.swing.JLabel lblCadastre_paneLogin;
    private javax.swing.JLabel lblCadastro_paneCadastro;
    private javax.swing.JLabel lblConfirmarSenha_paneCadastro;
    private javax.swing.JLabel lblEmail_paneCadastro;
    private javax.swing.JLabel lblLivros_paneEmprestimo;
    private javax.swing.JLabel lblLivros_paneLivrosEmprestados;
    private javax.swing.JLabel lblLivros_paneVerLivros;
    private javax.swing.JLabel lblLogin_paneLogin;
    private javax.swing.JLabel lblMenu_paneMenu;
    private javax.swing.JLabel lblNoConta_paneLogin;
    private javax.swing.JLabel lblNome_paneCadastro;
    private javax.swing.JLabel lblNome_paneLogin;
    private javax.swing.JLabel lblOperacoes_paneMenu;
    private javax.swing.JLabel lblRetornarLogin_paneCadastro;
    private javax.swing.JLabel lblSelecioneRegiao_paneEmprestimo;
    private javax.swing.JLabel lblSelecioneRegiao_paneVerLivros;
    private javax.swing.JLabel lblSenha_paneCadastro;
    private javax.swing.JLabel lblSenha_paneLogin;
    private javax.swing.JPanel paneCadastro;
    private javax.swing.JPanel paneEmprestimo;
    private javax.swing.JPanel paneHome;
    private javax.swing.JPanel paneLivrosEmprestados;
    private javax.swing.JPanel paneLogin;
    private javax.swing.JPanel paneVerLivros;
    private javax.swing.JScrollPane scrollEmprestimo;
    private javax.swing.JScrollPane scrollLivrosEmprestados;
    private javax.swing.JScrollPane scrollVerLivros;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JTextField txtBucarTitulo_paneEmprestimo;
    private javax.swing.JTextField txtBucarTitulo_paneVerLivros;
    private javax.swing.JPasswordField txtConfirmarSenha_paneCadastro;
    private javax.swing.JTextArea txtDescricaoBusca_paneMenu;
    private javax.swing.JTextArea txtDescricaoOp_paneMenu;
    private javax.swing.JTextField txtEmail_paneCadastro;
    private javax.swing.JTextField txtNome_paneCadastro;
    private javax.swing.JTextField txtNome_paneLogin;
    private javax.swing.JPasswordField txtSenha_paneCadastro;
    private javax.swing.JPasswordField txtSenha_paneLogin;
    // End of variables declaration//GEN-END:variables
}
