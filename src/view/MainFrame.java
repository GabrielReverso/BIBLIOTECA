package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.sql.Connection;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Painter;
import javax.swing.UIDefaults;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import controller.AcervoDAO;
import controller.LivroAcervoDAO;
import controller.LivroDAO;
import controller.LivroUsuarioDAO;
import controller.UsuarioDAO;
import controller.conexao.Conexao;
import images.ImagePanel;
import model.Acervo;
import model.Livro;
import model.LivroAcervo;
import model.LivroUsuario;
import model.Usuario;
import components.CustomBorder;

public class MainFrame extends javax.swing.JFrame {

    protected static Usuario usuarioLogado;

    /**
     * Creates new form UsuarioView
     */
    public MainFrame() {
        initComponents();
        configureBackground();
        configureTabbedPaneUI();
        configureUI();
        verificarConexao();
        loadIcons();
        setLocationRelativeTo(null);
        setResizable(false); 
        setTitle("BIBLIOTECA");
    }

    /*Método para verificar se está conectado ao banco de dados*/
    private void verificarConexao(){
        Connection con = Conexao.conectar();

        if (con == null) {
            JOptionPane.showMessageDialog(null, 
                                          "Banco de dados indisponível, reinicie a aplicação!", 
                                          "Alerta", 
                                          JOptionPane.WARNING_MESSAGE);
            btnEntrar_paneLogin.setEnabled(false);
        } else {
            preencherComboBox();
            Conexao.desconectar(con);
        }
    }

    /*Método para configurar o UI dos TextArea e Separators*/
    private void configureUI(){
        UIDefaults overrides = new UIDefaults();
        overrides.put("TextArea[Disabled].backgroundPainter", new Painter<JTextArea>() {

            @Override
            public void paint(Graphics2D g, JTextArea field, int width, int height) {
                g.setColor(new Color(182,143,97));
                g.fill(new Rectangle(
                        0, 
                        0, 
                        width,
                        height));
            }

        });
        txtDescricaoBusca_paneMenu.putClientProperty("Nimbus.Overrides", overrides);
        txtDescricaoOp_paneMenu.putClientProperty("Nimbus.Overrides", overrides);
        overrides.put("Separator[Enabled].backgroundPainter", new Painter<JSeparator>() {

            @Override
            public void paint(Graphics2D g, JSeparator separator, int width, int height) {
                g.setColor(new Color(67, 35, 17));
                g.fill(new Rectangle(
                        0, 
                        3, 
                        width,
                        2));
            }

        });
        jSeparator2.putClientProperty("Nimbus.Overrides", overrides);
        jSeparator1.putClientProperty("Nimbus.Overrides", overrides);
        jSeparator5.putClientProperty("Nimbus.Overrides", overrides);
        jSeparator4.putClientProperty("Nimbus.Overrides", overrides);
    }

    /*Método para chamar as configurações de fundo*/
    private void configureBackground(){
        configureLoginPaneWithBackground();
        configureCadastroPaneWithBackground();
        configureHomePaneWithBackground();
        configureVerLivrosPaneWithBackground();
        configureDevolucaoPaneWithBackground();
        configureEmprestimoPaneWithBackground();
        configureLivrosEmprestadosPaneWithBackground();
        configureRenovarPaneWithBackground();
        configureConfigPaneWithBackground();
    }

    /*Método para configurar o fundo de LOGIN*/
    private void configureLoginPaneWithBackground() {
        // Crie objeto ImagePanel
        ImagePanel backgroundLogin = new ImagePanel();
        
        // Torna o painel pai transparente
        paneLogin.setOpaque(false);

        // Defina cores e bordas para a box
        paneLoginBox.setBackground(new Color(67, 35, 17, 230));    
        paneLoginBox.setBorder(new CustomBorder(new Color(210, 210, 210)));  
        
        // Adicione o painel ao painel de imagem
        backgroundLogin.add(paneLogin, BorderLayout.CENTER);
        
        // Substitua o painel original pelo painel com imagem
        tabPane.remove(paneLogin);
        tabPane.insertTab("Login", null, backgroundLogin, null, 0);
        tabPane.setSelectedIndex(0);
    }

    /*Método para configurar o fundo de CADASTRO*/
    private void configureCadastroPaneWithBackground() {
        // Crie objeto ImagePanel
        ImagePanel backgroundCadastro = new ImagePanel();
        
        // Torna o painel pai transparente
        paneCadastro.setOpaque(false);

        // Defina cores e bordas para a box
        paneCadastroBox.setBackground(new Color(67, 35, 17, 230));    
        paneCadastroBox.setBorder(new CustomBorder(new Color(210, 210, 210)));  
        
        // Adicione o painel ao painel de imagem
        backgroundCadastro.add(paneCadastro, BorderLayout.CENTER);
        
        // Substitua o painel original pelo painel com imagem
        tabPane.remove(paneCadastro);
        tabPane.insertTab("Cadastro", null, backgroundCadastro, null, 1);
        tabPane.setSelectedIndex(0);
    }

    /*Método para configurar o fundo de HOME*/
    private void configureHomePaneWithBackground() {
        // Crie objeto ImagePanel
        ImagePanel backgroundHome = new ImagePanel(ImagePanel.homeBackground);
        
        // Torna o painel pai transparente
        paneHome.setOpaque(false);
        jSeparator1.setBackground(new Color(67,35,17));
        jSeparator1.setForeground(new Color(67,35,17));
        
        // Adicione o painel ao painel de imagem
        backgroundHome.add(paneHome, BorderLayout.CENTER);
        
        // Substitua o painel original pelo painel com imagem
        tabPane.remove(paneHome);
        tabPane.insertTab("Home", null, backgroundHome, null, 2);
        tabPane.setSelectedIndex(0);
    }

    /*Método para configurar o fundo de VER LIVROS*/
    private void configureVerLivrosPaneWithBackground() {
        // Crie objeto ImagePanel
        ImagePanel backgroundVerLivros = new ImagePanel(ImagePanel.scrollBackground);
        
        // Torna o painel pai transparente
        paneVerLivros.setOpaque(false);
        
        // Adicione o painel ao painel de imagem
        backgroundVerLivros.add(paneVerLivros, BorderLayout.CENTER);
        
        // Substitua o painel original pelo painel com imagem
        tabPane.remove(paneVerLivros);
        tabPane.insertTab("Ver Livros", null, backgroundVerLivros, null, 3);
        tabPane.setSelectedIndex(0);
    }

    /*Método para configurar o fundo de LIVROS EMPRESTADOS*/
    private void configureLivrosEmprestadosPaneWithBackground() {
        // Crie objeto ImagePanel
        ImagePanel backgroundLivrosEmprestados = new ImagePanel(ImagePanel.scrollBackground);
        
        // Torna o painel pai transparente
        paneLivrosEmprestados.setOpaque(false);
        
        // Adicione o painel ao painel de imagem
        backgroundLivrosEmprestados.add(paneLivrosEmprestados, BorderLayout.CENTER);
        
        // Substitua o painel original pelo painel com imagem
        tabPane.remove(paneLivrosEmprestados);
        tabPane.insertTab("Livros Emp.", null, backgroundLivrosEmprestados, null, 5);
        tabPane.setSelectedIndex(0);
    }

    /*Método para configurar o fundo de RENOVAR*/
    private void configureRenovarPaneWithBackground() {
        // Crie objeto ImagePanel
        ImagePanel backgroundRenovar = new ImagePanel(ImagePanel.scrollBackground);
        
        // Torna o painel pai transparente
        paneRenovar.setOpaque(false);
        
        // Adicione o painel ao painel de imagem
        backgroundRenovar.add(paneRenovar, BorderLayout.CENTER);
        
        // Substitua o painel original pelo painel com imagem
        tabPane.remove(paneRenovar);
        tabPane.insertTab("Renovar", null, backgroundRenovar, null, 7);
        tabPane.setSelectedIndex(0);
    }

    /*Método para configurar o fundo de EMPRESTIMO*/
    private void configureEmprestimoPaneWithBackground() {
        // Crie objeto ImagePanel
        ImagePanel backgroundEmprestimo = new ImagePanel(ImagePanel.scrollBackground);
        
        // Torna o painel pai transparente
        paneEmprestimo.setOpaque(false);
        
        // Adicione o painel ao painel de imagem
        backgroundEmprestimo.add(paneEmprestimo, BorderLayout.CENTER);
        
        // Substitua o painel original pelo painel com imagem
        tabPane.remove(paneEmprestimo);
        tabPane.insertTab("Emprestimo", null, backgroundEmprestimo, null, 4);
        tabPane.setSelectedIndex(0);
    }

    /*Método para configurar o fundo de DEVOLUÇAÕ*/
    private void configureDevolucaoPaneWithBackground() {
        // Crie objeto ImagePanel
        ImagePanel backgroundDevolucao = new ImagePanel(ImagePanel.scrollBackground);
        
        // Torna o painel pai transparente
        paneDevolucao.setOpaque(false);
        
        // Adicione o painel ao painel de imagem
        backgroundDevolucao.add(paneDevolucao, BorderLayout.CENTER);
        
        // Substitua o painel original pelo painel com imagem
        tabPane.remove(paneDevolucao);
        tabPane.insertTab("Devolucao", null, backgroundDevolucao, null, 6);
        tabPane.setSelectedIndex(0);
    }

    /*Método para configurar o fundo de CONFIG*/
    private void configureConfigPaneWithBackground() {
        // Crie objeto ImagePanel
        ImagePanel backgroundConfig = new ImagePanel(ImagePanel.configBackground);
        
        // Torna o painel pai transparente
        paneConfig.setOpaque(false);
        
        // Adicione o painel ao painel de imagem
        backgroundConfig.add(paneConfig, BorderLayout.CENTER);
        
        // Substitua o painel original pelo painel com imagem
        tabPane.remove(paneConfig);
        tabPane.insertTab("Config", null, backgroundConfig, null, 8);
        tabPane.setSelectedIndex(0);
    }

    /*Método para onfiguração do tabPane*/
    private void configureTabbedPaneUI() {
        tabPane.setUI(new BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tab_placement, int run_count, int max_tab_height) {
                return -1;
            }
        });
        tabPane.setEnabledAt(0, true);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, false);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, false);
    }

    /*Método para carregar todos os ícones da aplicação*/
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
                iconConfig3.setIcon(scaledIconConfig);
                iconConfig4.setIcon(scaledIconConfig);
                iconConfig5.setIcon(scaledIconConfig);
                iconConfig6.setIcon(scaledIconConfig);
            }
        } catch (Exception e) {
            System.err.println("Imagem 'setting.png' nao existe, usando fallback textual!");
            iconConfig.setText("Config");
            iconConfig2.setText("Config");
            iconConfig3.setText("Config");
            iconConfig4.setText("Config");
            iconConfig5.setText("Config");
            iconConfig6.setText("Config");
        }

        try {
            // Carrega e redimensiona a imagem para iconVoltar
            ImageIcon imageIconVoltar = new ImageIcon(getClass().getResource("/images/back.png")); 
            if (imageIconVoltar!= null) {
                Image imageVoltar = imageIconVoltar.getImage();
                Image scaledImageVoltar = imageVoltar.getScaledInstance(homeIconWidth, homeIconHeight, Image.SCALE_SMOOTH);
                ImageIcon scaledIconVoltar = new ImageIcon(scaledImageVoltar);
                iconVoltar_paneVerLivros.setIcon(scaledIconVoltar);
                iconVoltar_paneRenovar.setIcon(scaledIconVoltar);
                iconVoltar_paneLivrosEmprestados.setIcon(scaledIconVoltar);
                iconVoltar_paneEmprestimo.setIcon(scaledIconVoltar);
                iconVoltar_paneDevolucao.setIcon(scaledIconVoltar);
                iconVoltar_paneConfig.setIcon(scaledIconVoltar);
            }
        } catch (Exception e) {
            System.err.println("Imagem 'back.png' nao existe, usando fallback textual!");
            iconVoltar_paneVerLivros.setText("Back");
            iconVoltar_paneRenovar.setText("Back");
            iconVoltar_paneLivrosEmprestados.setText("Back");
            iconVoltar_paneEmprestimo.setText("Back");
            iconVoltar_paneDevolucao.setText("Back");
            iconVoltar_paneConfig.setText("Back");
        }

        try {
            // Carrega e redimensiona a imagem para iconInformacao
            ImageIcon imageIconInformacao = new ImageIcon(getClass().getResource("/images/information-button.png"));
            if (imageIconInformacao!= null) {
                Image imageInformacao = imageIconInformacao.getImage();
                Image scaledImageInformacao = imageInformacao.getScaledInstance(homeIconWidth, homeIconHeight, Image.SCALE_SMOOTH);
                ImageIcon scaledIconInformacao = new ImageIcon(scaledImageInformacao);
                iconInformacao.setIcon(scaledIconInformacao);
            }
        } catch (Exception e) {
            System.err.println("Imagem 'information-button.png' nao existe, usando fallback textual!");
            iconInformacao.setText("Info");
        }
    }

    /*******SCROLLERS VER LIVROS*****/
    private void adicionarLivrosScroll() {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
        container.setBackground(new Color(67,35,17));
    
        LivroDAO dao = new LivroDAO();

        List<Livro> lista = dao.obterLivros();

        for (Livro l : lista) {
            LivroView panel = new LivroView(l);
            container.add(panel);
        }    
        scrollVerLivros.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }

    private void adicionarVerLivrosScroll(String local) {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
        container.setBackground(new Color(67,35,17));
    
        LivroAcervoDAO dao = new LivroAcervoDAO();

        List<LivroAcervo> lista = dao.obterLivrosDoAcervo(local);

        for (LivroAcervo l : lista) {
            LivroView panel = new LivroView(l);
            container.add(panel);
        }    
        scrollVerLivros.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }

    private void adicionarBuscaScroll(String busca) {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
        container.setBackground(new Color(67,35,17));
    
        LivroAcervoDAO dao = new LivroAcervoDAO();

        List<Livro> lista = dao.buscarLivrosDoAcervo(busca);

        for (Livro l : lista) {
            LivroView panel = new LivroView(l);
            container.add(panel);
        }
        if (lista.isEmpty()){
            container.setLayout(new BorderLayout());
            JLabel label = new JLabel("Nenhum livro encontrado com o termo '" + busca + "'");
            label.setFont(new java.awt.Font("sansserif", 0, 18));
            label.setForeground(new Color(230,230,230));
            label.setHorizontalAlignment(JLabel.CENTER);
            container.add(label, BorderLayout.CENTER);
        }    
        scrollVerLivros.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }

    private void adicionarBuscaScroll(String local, String busca) {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
        container.setBackground(new Color(67,35,17));
    
        LivroAcervoDAO dao = new LivroAcervoDAO();

        List<LivroAcervo> lista = dao.buscarLivrosDoAcervo(local, busca);

        for (LivroAcervo l : lista) {
            LivroView panel = new LivroView(l);
            container.add(panel);
        }   
        if (lista.isEmpty()){
            container.setLayout(new BorderLayout());
            JLabel label = new JLabel("Nenhum livro encontrado com o termo '" + busca + "'");
            label.setFont(new java.awt.Font("sansserif", 0, 18));
            label.setForeground(new Color(230,230,230));
            label.setHorizontalAlignment(JLabel.CENTER);
            container.add(label, BorderLayout.CENTER);
        }  
        scrollVerLivros.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }

    /*******SCROLLERS EMPRESTIMO*****/
    private void adicionarEmprestimoScroll() {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
        container.setBackground(new Color(67,35,17));
    
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
        container.setBackground(new Color(67,35,17));
    
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
        container.setBackground(new Color(67,35,17));
    
        LivroAcervoDAO dao = new LivroAcervoDAO();

        List<Livro> lista = dao.buscarLivrosDoAcervo(busca);

        for (Livro l : lista) {
            LivroEmprestimoView panel = new LivroEmprestimoView(l);
            container.add(panel);
        }
        if (lista.isEmpty()){
            container.setLayout(new BorderLayout());
            JLabel label = new JLabel("Nenhum livro encontrado com o termo '" + busca + "'");
            label.setFont(new java.awt.Font("sansserif", 0, 18));
            label.setForeground(new Color(230,230,230));
            label.setHorizontalAlignment(JLabel.CENTER);
            container.add(label, BorderLayout.CENTER);
        }     
        scrollEmprestimo.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }

    private void adicionarEmprestimoBuscaScroll(String local, String busca) {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
        container.setBackground(new Color(67,35,17));
    
        LivroAcervoDAO dao = new LivroAcervoDAO();

        List<LivroAcervo> lista = dao.buscarLivrosDoAcervo(local, busca);

        for (LivroAcervo l : lista) {
            LivroEmprestimoView panel = new LivroEmprestimoView(l);
            container.add(panel);
        }
        if (lista.isEmpty()){
            container.setLayout(new BorderLayout());
            JLabel label = new JLabel("Nenhum livro encontrado com o termo '" + busca + "'");
            label.setFont(new java.awt.Font("sansserif", 0, 18));
            label.setForeground(new Color(230,230,230));
            label.setHorizontalAlignment(JLabel.CENTER);
            container.add(label, BorderLayout.CENTER);
        }     
        scrollEmprestimo.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }

    /******SCROLLER EMPRESTADO******/
    private void adicionarLivrosEmprestadosScroll() {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
        container.setBackground(new Color(67,35,17));
    
        LivroUsuarioDAO dao = new LivroUsuarioDAO();

        List<LivroUsuario> lista = dao.obterLivrosUsuario(usuarioLogado);

        for (LivroUsuario l : lista) {
            LivroEmprestadoView panel = new LivroEmprestadoView(l);
            container.add(panel);
        }    

        if(lista.isEmpty()){
            container.setLayout(new BorderLayout());
            JLabel label = new JLabel("Nenhum livro encontrado");
            label.setFont(new java.awt.Font("sansserif", 0, 18));
            label.setForeground(new Color(230,230,230));
            label.setHorizontalAlignment(JLabel.CENTER);
            container.add(label, BorderLayout.CENTER);
        }

        scrollLivrosEmprestados.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }

    /******SCROLLER RENOVAR******/
    private void adicionarLivrosRenovarScroll() {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
        container.setBackground(new Color(67,35,17));
    
        LivroUsuarioDAO dao = new LivroUsuarioDAO();

        List<LivroUsuario> lista = dao.obterLivrosUsuario(usuarioLogado);

        for (LivroUsuario l : lista) {
            LivroRenovarView panel = new LivroRenovarView(l);
            container.add(panel);
        }    

        if(lista.isEmpty()){
            container.setLayout(new BorderLayout());
            JLabel label = new JLabel("Nenhum livro encontrado");
            label.setFont(new java.awt.Font("sansserif", 0, 18));
            label.setForeground(new Color(230,230,230));
            label.setHorizontalAlignment(JLabel.CENTER);
            container.add(label, BorderLayout.CENTER);
        }

        scrollRenovar.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }

    /******SCROLLER DEVOLUCAO******/
    private void adicionarLivrosDevolucaoScroll() {
        JPanel container = new JPanel(); // Criando um novo JPanel para conter os outros painéis
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar verticalmente
        container.setBackground(new Color(67,35,17));
    
        LivroUsuarioDAO dao = new LivroUsuarioDAO();

        List<LivroUsuario> lista = dao.obterLivrosUsuario(usuarioLogado);

        for (LivroUsuario l : lista) {
            LivroDevolucaoView panel = new LivroDevolucaoView(l);
            container.add(panel);
        }
        
        if(lista.isEmpty()){
            container.setLayout(new BorderLayout());
            JLabel label = new JLabel("Nenhum livro encontrado");
            label.setFont(new java.awt.Font("sansserif", 0, 18));
            label.setForeground(new Color(230,230,230));
            label.setHorizontalAlignment(JLabel.CENTER);
            container.add(label, BorderLayout.CENTER);
        }

        scrollDevolucao.setViewportView(container); // Definindo o container como o viewport do JScrollPane
    }

    /*Método para preencheimento da combobox*/
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
        paneLoginBox = new javax.swing.JPanel();
        lblLogin_paneLogin = new javax.swing.JLabel();
        lblNome_paneLogin = new javax.swing.JLabel();
        txtNome_paneLogin = new javax.swing.JTextField();
        lblSenha_paneLogin = new javax.swing.JLabel();
        btnEntrar_paneLogin = new javax.swing.JButton();
        lblNoConta_paneLogin = new javax.swing.JLabel();
        lblCadastre_paneLogin = new javax.swing.JLabel();
        txtSenha_paneLogin = new javax.swing.JPasswordField();
        paneCadastro = new javax.swing.JPanel();
        paneCadastroBox = new javax.swing.JPanel();
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
        paneDevolucao = new javax.swing.JPanel();
        scrollDevolucao = new javax.swing.JScrollPane();
        lblLivros_paneDevolucao = new javax.swing.JLabel();
        iconVoltar_paneDevolucao = new javax.swing.JLabel();
        iconConfig6 = new javax.swing.JLabel();
        paneRenovar = new javax.swing.JPanel();
        scrollRenovar = new javax.swing.JScrollPane();
        lblLivros_paneRenovar = new javax.swing.JLabel();
        iconVoltar_paneRenovar = new javax.swing.JLabel();
        iconConfig5 = new javax.swing.JLabel();
        paneConfig = new javax.swing.JPanel();
        lblCOnfiguracoes_paneConfig = new javax.swing.JLabel();
        lblOperacoes_paneConfig = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        btnAlterarNome_paneConfig = new javax.swing.JButton();
        iconVoltar_paneConfig = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        btnAlterarEmail_paneConfig = new javax.swing.JButton();
        btnAlterarSenha_paneConfig = new javax.swing.JButton();
        btnEncerrar_paneConfig = new javax.swing.JButton();
        iconInformacao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(700, 550));

        paneLogin.setPreferredSize(new java.awt.Dimension(800, 600));

        paneLoginBox.setBackground(new java.awt.Color(67, 35, 17));
        paneLoginBox.setForeground(new java.awt.Color(210, 210, 210));
        paneLoginBox.setPreferredSize(new java.awt.Dimension(800, 600));

        lblLogin_paneLogin.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblLogin_paneLogin.setForeground(new java.awt.Color(230, 230, 230));
        lblLogin_paneLogin.setText("LOGIN");

        lblNome_paneLogin.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblNome_paneLogin.setForeground(new java.awt.Color(210, 210, 210));
        lblNome_paneLogin.setText("Nome ou Email");

        txtNome_paneLogin.setBackground(new java.awt.Color(182, 143, 97));
        txtNome_paneLogin.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        txtNome_paneLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNome_paneLoginFocusGained(evt);
            }
        });

        lblSenha_paneLogin.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblSenha_paneLogin.setForeground(new java.awt.Color(210, 210, 210));
        lblSenha_paneLogin.setText("Senha");

        btnEntrar_paneLogin.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        btnEntrar_paneLogin.setText("Entrar");
        btnEntrar_paneLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrar_paneLoginActionPerformed(evt);
            }
        });

        lblNoConta_paneLogin.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblNoConta_paneLogin.setForeground(new java.awt.Color(210, 210, 210));
        lblNoConta_paneLogin.setText("Não possui conta?");

        lblCadastre_paneLogin.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblCadastre_paneLogin.setForeground(new java.awt.Color(102, 255, 255));
        lblCadastre_paneLogin.setText("Cadastre-se");
        lblCadastre_paneLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCadastre_paneLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCadastre_paneLoginMouseClicked(evt);
            }
        });

        txtSenha_paneLogin.setBackground(new java.awt.Color(182, 143, 97));
        txtSenha_paneLogin.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        txtSenha_paneLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSenha_paneLoginFocusGained(evt);
            }
        });

        javax.swing.GroupLayout paneLoginBoxLayout = new javax.swing.GroupLayout(paneLoginBox);
        paneLoginBox.setLayout(paneLoginBoxLayout);
        paneLoginBoxLayout.setHorizontalGroup(
            paneLoginBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneLoginBoxLayout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addGroup(paneLoginBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNome_paneLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSenha_paneLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSenha_paneLogin)
                    .addComponent(lblNome_paneLogin))
                .addGap(83, 83, 83))
            .addGroup(paneLoginBoxLayout.createSequentialGroup()
                .addGroup(paneLoginBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneLoginBoxLayout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(lblLogin_paneLogin))
                    .addGroup(paneLoginBoxLayout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(btnEntrar_paneLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneLoginBoxLayout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(lblNoConta_paneLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCadastre_paneLogin)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paneLoginBoxLayout.setVerticalGroup(
            paneLoginBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLoginBoxLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblLogin_paneLogin)
                .addGap(80, 80, 80)
                .addComponent(lblNome_paneLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome_paneLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lblSenha_paneLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha_paneLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(btnEntrar_paneLogin)
                .addGap(54, 54, 54)
                .addGroup(paneLoginBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoConta_paneLogin)
                    .addComponent(lblCadastre_paneLogin))
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout paneLoginLayout = new javax.swing.GroupLayout(paneLogin);
        paneLogin.setLayout(paneLoginLayout);
        paneLoginLayout.setHorizontalGroup(
            paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLoginLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(paneLoginBox, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        paneLoginLayout.setVerticalGroup(
            paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLoginLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(paneLoginBox, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        tabPane.addTab("Login", paneLogin);

        paneCadastroBox.setBackground(new java.awt.Color(67, 35, 17));
        paneCadastroBox.setPreferredSize(new java.awt.Dimension(600, 550));

        lblCadastro_paneCadastro.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblCadastro_paneCadastro.setForeground(new java.awt.Color(230, 230, 230));
        lblCadastro_paneCadastro.setText("CADASTRO");

        lblNome_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblNome_paneCadastro.setForeground(new java.awt.Color(210, 210, 210));
        lblNome_paneCadastro.setText("Nome");

        txtNome_paneCadastro.setBackground(new java.awt.Color(182, 143, 97));
        txtNome_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        lblEmail_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblEmail_paneCadastro.setForeground(new java.awt.Color(210, 210, 210));
        lblEmail_paneCadastro.setText("Email");

        txtEmail_paneCadastro.setBackground(new java.awt.Color(182, 143, 97));
        txtEmail_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        lblSenha_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblSenha_paneCadastro.setForeground(new java.awt.Color(210, 210, 210));
        lblSenha_paneCadastro.setText("Senha");

        txtSenha_paneCadastro.setBackground(new java.awt.Color(182, 143, 97));
        txtSenha_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        lblConfirmarSenha_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblConfirmarSenha_paneCadastro.setForeground(new java.awt.Color(210, 210, 210));
        lblConfirmarSenha_paneCadastro.setText("Confirmar senha");

        txtConfirmarSenha_paneCadastro.setBackground(new java.awt.Color(182, 143, 97));
        txtConfirmarSenha_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        btnCadastrar_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        btnCadastrar_paneCadastro.setText("Cadastrar");
        btnCadastrar_paneCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrar_paneCadastroActionPerformed(evt);
            }
        });

        lblRetornarLogin_paneCadastro.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblRetornarLogin_paneCadastro.setForeground(new java.awt.Color(102, 255, 255));
        lblRetornarLogin_paneCadastro.setText("Retornar ao Login");
        lblRetornarLogin_paneCadastro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRetornarLogin_paneCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRetornarLogin_paneCadastroMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout paneCadastroBoxLayout = new javax.swing.GroupLayout(paneCadastroBox);
        paneCadastroBox.setLayout(paneCadastroBoxLayout);
        paneCadastroBoxLayout.setHorizontalGroup(
            paneCadastroBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCadastroBoxLayout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(paneCadastroBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneCadastroBoxLayout.createSequentialGroup()
                        .addGroup(paneCadastroBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome_paneCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblConfirmarSenha_paneCadastro)
                            .addComponent(lblNome_paneCadastro)
                            .addComponent(lblEmail_paneCadastro)
                            .addComponent(lblSenha_paneCadastro)
                            .addComponent(txtConfirmarSenha_paneCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail_paneCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSenha_paneCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneCadastroBoxLayout.createSequentialGroup()
                        .addComponent(lblCadastro_paneCadastro)
                        .addGap(200, 200, 200))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneCadastroBoxLayout.createSequentialGroup()
                        .addGroup(paneCadastroBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCadastrar_paneCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRetornarLogin_paneCadastro))
                        .addGap(221, 221, 221))))
        );
        paneCadastroBoxLayout.setVerticalGroup(
            paneCadastroBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCadastroBoxLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblCadastro_paneCadastro)
                .addGap(16, 16, 16)
                .addComponent(lblNome_paneCadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome_paneCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblEmail_paneCadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail_paneCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblSenha_paneCadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha_paneCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblConfirmarSenha_paneCadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfirmarSenha_paneCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnCadastrar_paneCadastro)
                .addGap(15, 15, 15)
                .addComponent(lblRetornarLogin_paneCadastro)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout paneCadastroLayout = new javax.swing.GroupLayout(paneCadastro);
        paneCadastro.setLayout(paneCadastroLayout);
        paneCadastroLayout.setHorizontalGroup(
            paneCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCadastroLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(paneCadastroBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        paneCadastroLayout.setVerticalGroup(
            paneCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCadastroLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(paneCadastroBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        tabPane.addTab("Cadastro", paneCadastro);

        lblMenu_paneMenu.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblMenu_paneMenu.setText("MENU DE OPÇÕES");

        jSeparator1.setForeground(new java.awt.Color(67, 35, 17));
        jSeparator1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N

        lblBusca_paneMenu.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        lblBusca_paneMenu.setForeground(new java.awt.Color(67, 35, 17));
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
        lblOperacoes_paneMenu.setForeground(new java.awt.Color(67, 35, 17));
        lblOperacoes_paneMenu.setText("Operações");

        jSeparator2.setBackground(new java.awt.Color(67, 35, 17));
        jSeparator2.setForeground(new java.awt.Color(67, 35, 17));
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
        txtDescricaoBusca_paneMenu.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(67, 35, 17)), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));
        txtDescricaoBusca_paneMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtDescricaoBusca_paneMenu.setDisabledTextColor(new java.awt.Color(230, 230, 230));
        txtDescricaoBusca_paneMenu.setEnabled(false);
        jScrollPane1.setViewportView(txtDescricaoBusca_paneMenu);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtDescricaoOp_paneMenu.setEditable(false);
        txtDescricaoOp_paneMenu.setColumns(1);
        txtDescricaoOp_paneMenu.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        txtDescricaoOp_paneMenu.setLineWrap(true);
        txtDescricaoOp_paneMenu.setRows(5);
        txtDescricaoOp_paneMenu.setWrapStyleWord(true);
        txtDescricaoOp_paneMenu.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(67, 35, 17)), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));
        txtDescricaoOp_paneMenu.setDisabledTextColor(new java.awt.Color(230, 230, 230));
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
        iconConfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconConfigMouseClicked(evt);
            }
        });

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
                .addGap(60, 60, 60)
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
                .addContainerGap(58, Short.MAX_VALUE))
        );

        tabPane.addTab("Home", paneHome);

        scrollVerLivros.setBackground(new java.awt.Color(67, 35, 17));
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
        iconConfig2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconConfig2MouseClicked(evt);
            }
        });

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
                .addComponent(scrollVerLivros, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
        );

        tabPane.addTab("Ver Livros", paneVerLivros);

        scrollEmprestimo.setBackground(new java.awt.Color(67, 35, 17));
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
        iconConfig4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconConfig4MouseClicked(evt);
            }
        });

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
                .addComponent(scrollEmprestimo, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
        );

        tabPane.addTab("Emprestimo", paneEmprestimo);

        scrollLivrosEmprestados.setBackground(new java.awt.Color(67, 35, 17));
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
        iconConfig3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconConfig3MouseClicked(evt);
            }
        });

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
                .addComponent(scrollLivrosEmprestados, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
        );

        tabPane.addTab("Livros Emp.", paneLivrosEmprestados);

        scrollDevolucao.setBackground(new java.awt.Color(67, 35, 17));
        scrollDevolucao.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        lblLivros_paneDevolucao.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblLivros_paneDevolucao.setText("DEVOLUÇÃO DE LIVROS");

        iconVoltar_paneDevolucao.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        iconVoltar_paneDevolucao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconVoltar_paneDevolucao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconVoltar_paneDevolucaoMouseClicked(evt);
            }
        });

        iconConfig6.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        iconConfig6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconConfig6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconConfig6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout paneDevolucaoLayout = new javax.swing.GroupLayout(paneDevolucao);
        paneDevolucao.setLayout(paneDevolucaoLayout);
        paneDevolucaoLayout.setHorizontalGroup(
            paneDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollDevolucao)
            .addGroup(paneDevolucaoLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(iconVoltar_paneDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addComponent(lblLivros_paneDevolucao)
                .addGap(106, 106, 106)
                .addComponent(iconConfig6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        paneDevolucaoLayout.setVerticalGroup(
            paneDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneDevolucaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconConfig6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblLivros_paneDevolucao)
                        .addComponent(iconVoltar_paneDevolucao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollDevolucao, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
        );

        tabPane.addTab("Devolucao", paneDevolucao);

        scrollRenovar.setBackground(new java.awt.Color(67, 35, 17));
        scrollRenovar.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        lblLivros_paneRenovar.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblLivros_paneRenovar.setText("RENOVAR EMPRÉSTIMO");

        iconVoltar_paneRenovar.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        iconVoltar_paneRenovar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconVoltar_paneRenovar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconVoltar_paneRenovarMouseClicked(evt);
            }
        });

        iconConfig5.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        iconConfig5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconConfig5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconConfig5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout paneRenovarLayout = new javax.swing.GroupLayout(paneRenovar);
        paneRenovar.setLayout(paneRenovarLayout);
        paneRenovarLayout.setHorizontalGroup(
            paneRenovarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollRenovar)
            .addGroup(paneRenovarLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(iconVoltar_paneRenovar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(lblLivros_paneRenovar)
                .addGap(104, 104, 104)
                .addComponent(iconConfig5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        paneRenovarLayout.setVerticalGroup(
            paneRenovarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneRenovarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneRenovarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconConfig5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneRenovarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblLivros_paneRenovar)
                        .addComponent(iconVoltar_paneRenovar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollRenovar, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
        );

        tabPane.addTab("Renovar", paneRenovar);

        lblCOnfiguracoes_paneConfig.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblCOnfiguracoes_paneConfig.setText("CONFIGURAÇÕES");

        lblOperacoes_paneConfig.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        lblOperacoes_paneConfig.setForeground(new java.awt.Color(67, 35, 17));
        lblOperacoes_paneConfig.setText("Operações");

        jSeparator4.setForeground(new java.awt.Color(67, 35, 17));
        jSeparator4.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N

        btnAlterarNome_paneConfig.setFont(new java.awt.Font("sansserif", 0, 22)); // NOI18N
        btnAlterarNome_paneConfig.setText("Alterar Nome");
        btnAlterarNome_paneConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarNome_paneConfigActionPerformed(evt);
            }
        });

        iconVoltar_paneConfig.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        iconVoltar_paneConfig.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconVoltar_paneConfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconVoltar_paneConfigMouseClicked(evt);
            }
        });

        jSeparator5.setForeground(new java.awt.Color(67, 35, 17));
        jSeparator5.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N

        btnAlterarEmail_paneConfig.setFont(new java.awt.Font("sansserif", 0, 22)); // NOI18N
        btnAlterarEmail_paneConfig.setText("Alterar Email");
        btnAlterarEmail_paneConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarEmail_paneConfigActionPerformed(evt);
            }
        });

        btnAlterarSenha_paneConfig.setFont(new java.awt.Font("sansserif", 0, 22)); // NOI18N
        btnAlterarSenha_paneConfig.setText("Alterar Senha");
        btnAlterarSenha_paneConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarSenha_paneConfigActionPerformed(evt);
            }
        });

        btnEncerrar_paneConfig.setFont(new java.awt.Font("sansserif", 0, 22)); // NOI18N
        btnEncerrar_paneConfig.setText("Encerrar Seção");
        btnEncerrar_paneConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncerrar_paneConfigActionPerformed(evt);
            }
        });

        iconInformacao.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        iconInformacao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconInformacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconInformacaoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout paneConfigLayout = new javax.swing.GroupLayout(paneConfig);
        paneConfig.setLayout(paneConfigLayout);
        paneConfigLayout.setHorizontalGroup(
            paneConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneConfigLayout.createSequentialGroup()
                .addGroup(paneConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneConfigLayout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addGroup(paneConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAlterarNome_paneConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAlterarEmail_paneConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAlterarSenha_paneConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(paneConfigLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(paneConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneConfigLayout.createSequentialGroup()
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(lblOperacoes_paneConfig)
                                .addGap(27, 27, 27)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(paneConfigLayout.createSequentialGroup()
                                .addComponent(iconVoltar_paneConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160)
                                .addComponent(lblCOnfiguracoes_paneConfig)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(iconInformacao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(paneConfigLayout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(btnEncerrar_paneConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paneConfigLayout.setVerticalGroup(
            paneConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(iconInformacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(paneConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblCOnfiguracoes_paneConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(iconVoltar_paneConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(paneConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOperacoes_paneConfig, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(btnAlterarNome_paneConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAlterarEmail_paneConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAlterarSenha_paneConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109)
                .addComponent(btnEncerrar_paneConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        tabPane.addTab("Config", paneConfig);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPane, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
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
        
        if(txtEmail_paneCadastro.getText().contains("@")){
            if(txtSenha_paneCadastro.getText().equals(txtConfirmarSenha_paneCadastro.getText())){
    
                Usuario u = new Usuario(txtNome_paneCadastro.getText(), txtEmail_paneCadastro.getText(), new String(txtSenha_paneCadastro.getPassword()));
                UsuarioDAO dao = new UsuarioDAO();
    
                int resultado = dao.inserirNovoUsuario(u);
    
                if (resultado == -1){
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
                } else if (resultado == -2) {
                    JOptionPane.showMessageDialog(null, "Nome já cadastrado, operação cancelada");
                } else if (resultado == -3){
                    JOptionPane.showMessageDialog(null, "Email já cadastrado, operação cancelada");
                } else {
                    JOptionPane.showMessageDialog(null, "Cadastro realizado");
                    txtNome_paneCadastro.setText("");
                    txtEmail_paneCadastro.setText("");
                    txtSenha_paneCadastro.setText("");
                    txtConfirmarSenha_paneCadastro.setText("");
                    tabPane.setSelectedIndex(0);
                    tabPane.setEnabledAt(0, true);
                    tabPane.setEnabledAt(1, false);
                    tabPane.setEnabledAt(2, false);
                    tabPane.setEnabledAt(3, false);
                    tabPane.setEnabledAt(4, false);
                    tabPane.setEnabledAt(5, false);
                    tabPane.setEnabledAt(6, false);
                    tabPane.setEnabledAt(7, false);
                    tabPane.setEnabledAt(8, false);
                }
    
            } else {
                txtSenha_paneCadastro.setForeground(new Color(135, 12, 12));
                txtConfirmarSenha_paneCadastro.setForeground(new Color(135, 12, 12));
                JOptionPane.showMessageDialog(null, "Senhas não correspondem!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Insira um email válido!");
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
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, false);
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
                txtNome_paneLogin.setForeground(new Color(135, 12, 12));
                txtSenha_paneLogin.setForeground(new Color(135, 12, 12));
                JOptionPane.showMessageDialog(null, "Usuário ou Senha inválidos");
            } else {
                txtNome_paneLogin.setText("");
                txtSenha_paneLogin.setText("");
                tabPane.setSelectedIndex(2);

                LivroUsuarioDAO luDao = new LivroUsuarioDAO();
                luDao.VerificarLivrosExpirados(usuarioLogado);
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
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, false);
    }

    private void txtNome_paneLoginFocusGained(java.awt.event.FocusEvent evt) {                                              
        txtNome_paneLogin.setForeground(Color.BLACK);
        txtSenha_paneLogin.setForeground(Color.BLACK);
    }

    private void txtSenha_paneLoginFocusGained(java.awt.event.FocusEvent evt) {                                               
        txtSenha_paneLogin.setForeground(Color.BLACK);
        txtNome_paneLogin.setForeground(Color.BLACK);
    }

    /*FIM-LOGIN*************************************************************************/

    /*HOME*************************************************************************/
    private void btnBuscar_paneMenuActionPerformed(java.awt.event.ActionEvent evt) {  
        adicionarLivrosScroll();                                              
        tabPane.setSelectedIndex(3);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, false);
        tabPane.setEnabledAt(3, true);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, false);
    }

    private void btnEmprestimo_paneMenuActionPerformed(java.awt.event.ActionEvent evt) {  
        adicionarEmprestimoScroll();                                                        
        tabPane.setSelectedIndex(4);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, false);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, true);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, false);
    }

    private void btnDevolucao_paneMenuActionPerformed(java.awt.event.ActionEvent evt) {   
        adicionarLivrosDevolucaoScroll();   
        tabPane.setSelectedIndex(6);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, false);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, true);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, false);
    }

    private void btnRenovar_paneMenuActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        adicionarLivrosRenovarScroll();                                                 
        tabPane.setSelectedIndex(7);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, false);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, true);
        tabPane.setEnabledAt(8, false);
    }

    private void btnLivrosEscolhidos_paneMenuActionPerformed(java.awt.event.ActionEvent evt) {                                                             
        adicionarLivrosEmprestadosScroll();
        tabPane.setSelectedIndex(5);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, false);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, true);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, false);
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
    
    private void iconSairMouseClicked(java.awt.event.MouseEvent evt) {
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
            tabPane.setEnabledAt(3, false);
            tabPane.setEnabledAt(4, false);
            tabPane.setEnabledAt(5, false);
            tabPane.setEnabledAt(6, false);
            tabPane.setEnabledAt(7, false);
            tabPane.setEnabledAt(8, false);
            usuarioLogado = null;
        }

    }

    /*FIM-HOME*************************************************************************/

    /*VER LIVROS*************************************************************************/
    private void iconVoltar_paneVerLivrosMouseClicked(java.awt.event.MouseEvent evt) {
        tabPane.setSelectedIndex(2);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, true);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, false);
    }

    private void cbxSelecioneRegiao_paneVerLivrosItemStateChanged(java.awt.event.ItemEvent evt) {
        
        int posicao = cbxSelecioneRegiao_paneVerLivros.getSelectedIndex();

        if (posicao >= 0){
            String local = cbxSelecioneRegiao_paneVerLivros.getSelectedItem().toString();
            if (txtBucarTitulo_paneVerLivros.getText().isEmpty()) {
                adicionarVerLivrosScroll(local);
            } else {
                adicionarBuscaScroll(local, txtBucarTitulo_paneVerLivros.getText());
            }
        }
    }

    private void txtBucarTitulo_paneVerLivrosActionPerformed(java.awt.event.ActionEvent evt) {
        
        String busca = txtBucarTitulo_paneVerLivros.getText();
        String local = "";
        int posicao = cbxSelecioneRegiao_paneVerLivros.getSelectedIndex();

        if (posicao >= 0){
            local = cbxSelecioneRegiao_paneVerLivros.getSelectedItem().toString();
        }

        if(!local.isEmpty()){
            adicionarBuscaScroll(local,busca);
        } else {
            adicionarBuscaScroll(busca);
        }
    }

    private void btnLimpar_paneVerLivrosActionPerformed(java.awt.event.ActionEvent evt) {
        
        txtBucarTitulo_paneVerLivros.setText("");
        cbxSelecioneRegiao_paneVerLivros.setSelectedIndex(-1);
        adicionarLivrosScroll();
    }
    /*FIM-VER LIVROS*************************************************************************/

    /*LIVROS EMPRESTADOS*************************************************************************/
    private void iconVoltar_paneLivrosEmprestadosMouseClicked(java.awt.event.MouseEvent evt) {
        tabPane.setSelectedIndex(2);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, true);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, false);
    }
    /*FIM-LIVROS EMPRESTADOS*************************************************************************/
    
    /*EMPRESTIMO*************************************************************************/
    private void cbxSelecioneRegiao_paneEmprestimoItemStateChanged(java.awt.event.ItemEvent evt) {
        int posicao = cbxSelecioneRegiao_paneEmprestimo.getSelectedIndex();

        if (posicao >= 0){
            String local = cbxSelecioneRegiao_paneEmprestimo.getSelectedItem().toString();
            if (txtBucarTitulo_paneEmprestimo.getText().isEmpty()) {
                adicionarEmprestimoScroll(local);
            } else {
                adicionarEmprestimoBuscaScroll(local, txtBucarTitulo_paneEmprestimo.getText());
            }
        }
    }

    private void txtBucarTitulo_paneEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {
        
        String busca = txtBucarTitulo_paneEmprestimo.getText();
        String local = "";
        int posicao = cbxSelecioneRegiao_paneEmprestimo.getSelectedIndex();

        if (posicao >= 0){
            local = cbxSelecioneRegiao_paneEmprestimo.getSelectedItem().toString();
        }


        if(!local.isEmpty()){
            adicionarEmprestimoBuscaScroll(local,busca);
        } else {
            adicionarEmprestimoBuscaScroll(busca);
        }
        
    }

    private void iconVoltar_paneEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {
        tabPane.setSelectedIndex(2);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, true);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, false);
    }

    private void btnLimpar_paneEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {
        txtBucarTitulo_paneEmprestimo.setText("");
        cbxSelecioneRegiao_paneEmprestimo.setSelectedIndex(-1);
        adicionarEmprestimoScroll();
    }
    /*FIM-EMPRESTIMO*************************************************************************/

    /*RENOVAR**************************************************************************/
    private void iconVoltar_paneRenovarMouseClicked(java.awt.event.MouseEvent evt) {
        tabPane.setSelectedIndex(2);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, true);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, false);
    }
    /*FIM-RENOVAR*************************************************************************/

    /*DEVOLUÇÃO**************************************************************************/
    private void iconVoltar_paneDevolucaoMouseClicked(java.awt.event.MouseEvent evt) {
        tabPane.setSelectedIndex(2);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, true);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, false);
    }
    /*FIM-DEVOLUÇÃO*************************************************************************/

    /*CONFIGURAÇÕES***********************************************************************/
    private void btnAlterarNome_paneConfigActionPerformed(java.awt.event.ActionEvent evt) {
        JTextField novoNome = new JTextField();
        String mensagem = "Informe seu novo nome";
        int resposta = JOptionPane.showOptionDialog(this, new Object[] {mensagem, novoNome},
                                        "Alterar nome!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                         null, null, null);
        if (resposta == JOptionPane.OK_OPTION){
            if (!novoNome.getText().isEmpty()) {
                if (novoNome.getText().equals(usuarioLogado.getNome())){
                    JOptionPane.showMessageDialog(null, "Nome não pode ser igual o atual!");
                } else {
                    int respostaFinal = JOptionPane.showConfirmDialog(null, "Confirmar alteração do nome?","Confirmação",JOptionPane.YES_NO_OPTION);
                    if (respostaFinal == JOptionPane.OK_OPTION){
                        UsuarioDAO dao = new UsuarioDAO();
                        dao.alterarNomeUsuario(usuarioLogado, novoNome.getText());
                        usuarioLogado.setNome(novoNome.getText());
                        JOptionPane.showMessageDialog(null, "Alteração bem sucedida!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Operação cancelada!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Campos vazios, operação cancelada!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada!");
        }
    }

    private void btnAlterarEmail_paneConfigActionPerformed(java.awt.event.ActionEvent evt) {
        JTextField novoEmail = new JTextField();
        String mensagem = "Informe seu novo email";
        int resposta = JOptionPane.showOptionDialog(this, new Object[] {mensagem, novoEmail},
                                        "Alterar email!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                         null, null, null);
        if (resposta == JOptionPane.OK_OPTION){
            if (!novoEmail.getText().isEmpty()){
                if (novoEmail.getText().equals(usuarioLogado.getEmail())){
                    JOptionPane.showMessageDialog(null, "Email não pode ser igual o atual!");
                } else {
                    int respostaFinal = JOptionPane.showConfirmDialog(null, "Confirmar alteração do email?","Confirmação",JOptionPane.YES_NO_OPTION);
                    if (respostaFinal == JOptionPane.OK_OPTION){
                        UsuarioDAO dao = new UsuarioDAO();
                        dao.alterarEmailUsuario(usuarioLogado, novoEmail.getText());
                        usuarioLogado.setEmail(novoEmail.getText());
                        JOptionPane.showMessageDialog(null, "Alteração bem sucedida!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Operação cancelada!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Campos vazios, operação cancelada!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada!");
        }
    }

    private void btnAlterarSenha_paneConfigActionPerformed(java.awt.event.ActionEvent evt) {
        JPasswordField novaSenha = new JPasswordField();
        JPasswordField novaSenhaConfirmar = new JPasswordField();
        String mensagem = "Informe sua nova senha!\n";
        Object obj = new Object[] {
            mensagem, 
            "Nova Senha:", 
            novaSenha, 
            "Confirmar Senha:", 
            novaSenhaConfirmar
        };
        int resposta = JOptionPane.showOptionDialog(this, obj,"Alterar senha!", 
                                                  JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                             null, null, null);
        if (resposta == JOptionPane.OK_OPTION){
            if(!novaSenha.getText().isEmpty() && !novaSenhaConfirmar.getText().isEmpty()){

                if (!novaSenha.getText().equals(novaSenhaConfirmar.getText())){
                    JOptionPane.showMessageDialog(null, "Senhas não conferem!");
                } else {
                    int respostaFinal = JOptionPane.showConfirmDialog(null, "Confirmar alteração da senha?", "Confirmação",JOptionPane.YES_NO_OPTION);
                    if (respostaFinal == JOptionPane.OK_OPTION){
                        UsuarioDAO dao = new UsuarioDAO();
                        dao.alterarSenhaUsuario(usuarioLogado, novaSenha.getText());
                        JOptionPane.showMessageDialog(null, "Alteração bem sucedida!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Operação cancelada!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Campos vazios, operação cancelada!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada!");
        }
    }

    private void iconVoltar_paneConfigMouseClicked(java.awt.event.MouseEvent evt) {
        tabPane.setSelectedIndex(2);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, true);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, false);
    }

    private void btnEncerrar_paneConfigActionPerformed(java.awt.event.ActionEvent evt) {
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
            tabPane.setEnabledAt(3, false);
            tabPane.setEnabledAt(4, false);
            tabPane.setEnabledAt(5, false);
            tabPane.setEnabledAt(6, false);
            tabPane.setEnabledAt(7, false);
            tabPane.setEnabledAt(8, false);
            usuarioLogado = null;
        }
    }

    private void iconInformacaoMouseClicked(java.awt.event.MouseEvent evt) {
        JOptionPane.showMessageDialog(this, "Projeto feito usando Java Swing com intuito de ser " +  
                                            "uma aplicação simples, elegante e com banco de dados " + 
                                            "para uma biblioteca física!" + 
                                            "\n\nDesenvolvedor: Gabriel Reverso Pereira" +
                                            "\nCódigo: 837789");
    }

    //ICONES DE CONFIGURAÇÃO
    private void iconConfigMouseClicked(java.awt.event.MouseEvent evt) {
        tabPane.setSelectedIndex(8);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, false);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, true);
    }

    private void iconConfig2MouseClicked(java.awt.event.MouseEvent evt) {
        tabPane.setSelectedIndex(8);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, false);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, true);
    }

    private void iconConfig4MouseClicked(java.awt.event.MouseEvent evt) {
        tabPane.setSelectedIndex(8);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, false);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, true);
    }

    private void iconConfig3MouseClicked(java.awt.event.MouseEvent evt) {
        tabPane.setSelectedIndex(8);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, false);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, true);
    }

    private void iconConfig6MouseClicked(java.awt.event.MouseEvent evt) {
        tabPane.setSelectedIndex(8);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, false);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, true);
    }

    private void iconConfig5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconConfig5MouseClicked
        tabPane.setSelectedIndex(8);
        tabPane.setEnabledAt(0, false);
        tabPane.setEnabledAt(1, false);
        tabPane.setEnabledAt(2, false);
        tabPane.setEnabledAt(3, false);
        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.setEnabledAt(6, false);
        tabPane.setEnabledAt(7, false);
        tabPane.setEnabledAt(8, true);
    }//GEN-LAST:event_iconConfig5MouseClicked
    /*FIM-CONFIGURAÇÕES*************************************************************************/

    
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
    private javax.swing.JButton btnAlterarEmail_paneConfig;
    private javax.swing.JButton btnAlterarNome_paneConfig;
    private javax.swing.JButton btnAlterarSenha_paneConfig;
    private javax.swing.JButton btnBuscar_paneMenu;
    private javax.swing.JButton btnCadastrar_paneCadastro;
    private javax.swing.JButton btnDevolucao_paneMenu;
    private javax.swing.JButton btnEmprestimo_paneMenu;
    private javax.swing.JButton btnEncerrar_paneConfig;
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
    private javax.swing.JLabel iconConfig5;
    private javax.swing.JLabel iconConfig6;
    private javax.swing.JLabel iconInformacao;
    private javax.swing.JLabel iconSair;
    private javax.swing.JLabel iconVoltar_paneConfig;
    private javax.swing.JLabel iconVoltar_paneDevolucao;
    private javax.swing.JLabel iconVoltar_paneEmprestimo;
    private javax.swing.JLabel iconVoltar_paneLivrosEmprestados;
    private javax.swing.JLabel iconVoltar_paneRenovar;
    private javax.swing.JLabel iconVoltar_paneVerLivros;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lblBusca_paneMenu;
    private javax.swing.JLabel lblBuscarTitulo_paneEmprestimo;
    private javax.swing.JLabel lblBuscarTitulo_paneVerLivros;
    private javax.swing.JLabel lblCOnfiguracoes_paneConfig;
    private javax.swing.JLabel lblCadastre_paneLogin;
    private javax.swing.JLabel lblCadastro_paneCadastro;
    private javax.swing.JLabel lblConfirmarSenha_paneCadastro;
    private javax.swing.JLabel lblEmail_paneCadastro;
    private javax.swing.JLabel lblLivros_paneDevolucao;
    private javax.swing.JLabel lblLivros_paneEmprestimo;
    private javax.swing.JLabel lblLivros_paneLivrosEmprestados;
    private javax.swing.JLabel lblLivros_paneRenovar;
    private javax.swing.JLabel lblLivros_paneVerLivros;
    private javax.swing.JLabel lblLogin_paneLogin;
    private javax.swing.JLabel lblMenu_paneMenu;
    private javax.swing.JLabel lblNoConta_paneLogin;
    private javax.swing.JLabel lblNome_paneCadastro;
    private javax.swing.JLabel lblNome_paneLogin;
    private javax.swing.JLabel lblOperacoes_paneConfig;
    private javax.swing.JLabel lblOperacoes_paneMenu;
    private javax.swing.JLabel lblRetornarLogin_paneCadastro;
    private javax.swing.JLabel lblSelecioneRegiao_paneEmprestimo;
    private javax.swing.JLabel lblSelecioneRegiao_paneVerLivros;
    private javax.swing.JLabel lblSenha_paneCadastro;
    private javax.swing.JLabel lblSenha_paneLogin;
    private javax.swing.JPanel paneCadastro;
    private javax.swing.JPanel paneCadastroBox;
    private javax.swing.JPanel paneConfig;
    private javax.swing.JPanel paneDevolucao;
    private javax.swing.JPanel paneEmprestimo;
    private javax.swing.JPanel paneHome;
    private javax.swing.JPanel paneLivrosEmprestados;
    private javax.swing.JPanel paneLogin;
    private javax.swing.JPanel paneLoginBox;
    private javax.swing.JPanel paneRenovar;
    private javax.swing.JPanel paneVerLivros;
    private javax.swing.JScrollPane scrollDevolucao;
    private javax.swing.JScrollPane scrollEmprestimo;
    private javax.swing.JScrollPane scrollLivrosEmprestados;
    private javax.swing.JScrollPane scrollRenovar;
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
