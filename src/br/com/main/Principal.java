package br.com.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	
	public Principal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);  
    }

     public static void main(String args[]) {      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (ClassNotFoundException ex) {
            //java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
           // java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
           // java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
           // java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
                new Login().setVisible(true);
            }
        });
    }
   
    private void initComponents() {

        jdpPrincipal = new javax.swing.JDesktopPane();
        jmGerenciadorMain = new javax.swing.JMenuBar();
        jmGerenciadorFuncionario = new javax.swing.JMenu();
        jmiCadastrarFuncionario = new javax.swing.JMenuItem();
        jmGerenciadorCardapio = new javax.swing.JMenu();
        jmiMontarCardapio = new javax.swing.JMenuItem();
        jmGerenciadorPedidoCliente = new javax.swing.JMenu();
        jmiCardapio = new javax.swing.JMenuItem();
        jmGerenciadorPedidoInterno = new javax.swing.JMenu();
        jmiRelatorioPedido = new javax.swing.JMenuItem();
        jmGerenciadorCaixa = new javax.swing.JMenu();
        jmiAbrirCaixa = new javax.swing.JMenuItem();
        jmiRelatorioCaixa = new javax.swing.JMenuItem();
        jmGerenciadorSistema = new javax.swing.JMenu();
        jmiLogout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CONTROLE INTERNO RESTAURANTE");

        javax.swing.GroupLayout jdpPrincipalLayout = new javax.swing.GroupLayout(jdpPrincipal);
        jdpPrincipal.setLayout(jdpPrincipalLayout);
        jdpPrincipalLayout.setHorizontalGroup(
            jdpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1053, Short.MAX_VALUE)
        );
        jdpPrincipalLayout.setVerticalGroup(
            jdpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );
        
        //MENU DE COLABORADORES
        jmGerenciadorFuncionario.setText("GERENCIADOR DE COLABORADORES");
        jmiCadastrarFuncionario.setText("Cadastrar Funcionario");
        jmiCadastrarFuncionario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			     	CadastroFuncionario obj=new CadastroFuncionario();
			        jdpPrincipal.add(obj);
			        obj.setVisible(true);
			}
		});
        jmGerenciadorFuncionario.add(jmiCadastrarFuncionario);
        jmGerenciadorMain.add(jmGerenciadorFuncionario);

        //MENU DE CARDAPIO
        jmGerenciadorCardapio.setText("GERENCIADOR DE CARDAPIO ");
        jmiMontarCardapio.setText("Montar Cardápio");
        jmiMontarCardapio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MontarCardapio obj=new MontarCardapio();
		        jdpPrincipal.add(obj);
		        obj.setVisible(true);
			}
		});
        jmGerenciadorCardapio.add(jmiMontarCardapio);
        jmGerenciadorMain.add(jmGerenciadorCardapio);

        //MENU DE CLIENTES
        jmGerenciadorPedidoCliente.setText("PEDIDO CLIENTE");
        jmiCardapio.setText("Visualizar Cardapio");
        jmiCardapio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 Cardapio obj=new Cardapio(null);
			        jdpPrincipal.add(obj);
			        obj.setVisible(true);
			}
		});
        jmGerenciadorPedidoCliente.add(jmiCardapio);
        jmGerenciadorMain.add(jmGerenciadorPedidoCliente);

        //MENU DE PEDIDOS INTERNOS
        jmGerenciadorPedidoInterno.setText("  GERENCIADOR DE PEDIDOS INTERNOS   ");
        jmiRelatorioPedido.setText("Relatorio de Pedido");
        jmiRelatorioPedido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				  RelatorioPedido obj=new RelatorioPedido();
			        jdpPrincipal.add(obj);
			        obj.setVisible(true);
			}
		});
        jmGerenciadorPedidoInterno.add(jmiRelatorioPedido);
        jmGerenciadorMain.add(jmGerenciadorPedidoInterno);

        //MENU DE GERENCIAMENTO DE CAIXA
        jmGerenciadorCaixa.setText("GERENCIADOR CAIXA");
        jmiAbrirCaixa.setText("Abrir caixa");
        jmiAbrirCaixa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    AberturaCaixa obj=new AberturaCaixa();
		        jdpPrincipal.add(obj);
		        obj.setVisible(true);
			}
		});
        jmGerenciadorCaixa.add(jmiAbrirCaixa);
        jmiRelatorioCaixa.setText("Relatório caixa");
        jmiRelatorioCaixa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RelatorioCaixa obj=new RelatorioCaixa();
		        jdpPrincipal.add(obj);
		        obj.setVisible(true);
			}
		});
        jmGerenciadorCaixa.add(jmiRelatorioCaixa);
        jmGerenciadorMain.add(jmGerenciadorCaixa);

        //MENU DE GERENCIAMENTO DE SISTEMA
        jmGerenciadorSistema.setText("GERENCIAR SISTEMA ");
        jmiLogout.setText("Sair");
        jmiLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
        jmGerenciadorSistema.add(jmiLogout);
        jmGerenciadorMain.add(jmGerenciadorSistema);
        setJMenuBar(jmGerenciadorMain);

        //MONTAGEM DO CONTENT
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jdpPrincipal)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        //FIM DA TELA
        setBounds(0, 0, 1079, 555);
    }

    //INICALIZAR VARIAVEIS
    private javax.swing.JMenu jmGerenciadorCaixa;
    private javax.swing.JMenuBar jmGerenciadorMain;
    private javax.swing.JMenu jmGerenciadorFuncionario;
    public static javax.swing.JDesktopPane jdpPrincipal;
    private javax.swing.JMenu jmGerenciadorCardapio;
    private javax.swing.JMenu jmGerenciadorSistema;
    private javax.swing.JMenu jmGerenciadorPedidoCliente;
    private javax.swing.JMenu jmGerenciadorPedidoInterno;
    private javax.swing.JMenuItem jmiMontarCardapio;
    private javax.swing.JMenuItem jmiCadastrarFuncionario;
    private javax.swing.JMenuItem jmiCardapio;
    private javax.swing.JMenuItem jmiAbrirCaixa;
    private javax.swing.JMenuItem jmiLogout;
    private javax.swing.JMenuItem jmiRelatorioPedido;
    private javax.swing.JMenuItem jmiRelatorioCaixa;
}
