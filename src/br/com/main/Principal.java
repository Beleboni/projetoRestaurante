package br.com.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import br.com.model.Funcionario;
import br.com.tipo.StatusSetor;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;

public class Principal extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private Funcionario funcionario;
	
	public Principal(Funcionario funcionario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/pequeno.png")));
		this.funcionario = funcionario;
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH); 
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
        setTitle("BURGUER SOFT -- CONTROLE INTERNO RESTAURANTE");
        
        //CONTROLE DE SEÇÕES
        jlbUsuario = new JLabel("Usuário:");
        jlbUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jlbUsuario.setForeground(Color.WHITE);
        
        jlbSecao = new JLabel(funcionario.getNome().toString());
        jlbSecao.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jlbSecao.setForeground(Color.WHITE);
        
        jlbSetor = new JLabel("Setor:");
        jlbSetor.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jlbSetor.setForeground(Color.WHITE);
        
        jlbSecaoSetor = new JLabel(funcionario.getSetor().toString());
        jlbSecaoSetor.setForeground(Color.WHITE);
        jlbSecaoSetor.setFont(new Font("Tahoma", Font.PLAIN, 12));

        //INICIANDO A PLICAÇÃO
        javax.swing.GroupLayout jdpPrincipalLayout = new javax.swing.GroupLayout(jdpPrincipal);
        jdpPrincipalLayout.setHorizontalGroup(
        	jdpPrincipalLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jdpPrincipalLayout.createSequentialGroup()
        			.addContainerGap(1021, Short.MAX_VALUE)
        			.addComponent(jlbUsuario)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jlbSecao)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jlbSetor)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jlbSecaoSetor)
        			.addGap(112))
        );
        jdpPrincipalLayout.setVerticalGroup(
        	jdpPrincipalLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jdpPrincipalLayout.createSequentialGroup()
        			.addGap(22)
        			.addGroup(jdpPrincipalLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jlbUsuario)
        				.addComponent(jlbSecao)
        				.addComponent(jlbSetor)
        				.addComponent(jlbSecaoSetor))
        			.addContainerGap(647, Short.MAX_VALUE))
        );
        jdpPrincipal.setLayout(jdpPrincipalLayout);
        
        
        //MENU DE COLABORADORES
        jmGerenciadorFuncionario.setText("GERENCIADOR DE COLABORADORES");
        jmiCadastrarFuncionario.setText("Cadastrar Funcionario");
        jmiCadastrarFuncionario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(funcionario.getSetor() == StatusSetor.ADMINISTRADOR){
			     	CadastroFuncionario obj=new CadastroFuncionario();
			        jdpPrincipal.add(obj);
			        obj.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Atênção, Você não tem permissão de acesso");
				}
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
				if(funcionario.getSetor() == StatusSetor.COZINHEIRO ||funcionario.getSetor() == StatusSetor.ADMINISTRADOR){
					MontarCardapio obj=new MontarCardapio();
			        jdpPrincipal.add(obj);
			        obj.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Atênção, Você não tem permissão de acesso");
				}
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
				if(funcionario.getSetor() == StatusSetor.GARCOM ||funcionario.getSetor() == StatusSetor.ADMINISTRADOR){
					 	Cardapio obj=new Cardapio(null);
				        jdpPrincipal.add(obj);
				        obj.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Atênção, Você não tem permissão de acesso");
				}
			}
		});
        jmGerenciadorPedidoCliente.add(jmiCardapio);
        jmGerenciadorMain.add(jmGerenciadorPedidoCliente);

        //MENU DE PEDIDOS INTERNOS
        jmGerenciadorPedidoInterno.setText("GERENCIADOR DE PEDIDOS INTERNOS");
        jmiRelatorioPedido.setText("Relatorio de Pedido");
        jmiRelatorioPedido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(funcionario.getSetor() == StatusSetor.GARCOM ||funcionario.getSetor() == StatusSetor.ADMINISTRADOR){
				  RelatorioPedido obj=new RelatorioPedido();
			        jdpPrincipal.add(obj);
			        obj.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Atênção, Você não tem permissão de acesso");
				}
				
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
				if(funcionario.getSetor() == StatusSetor.CAIXA){
				    AberturaCaixa obj=new AberturaCaixa();
			        jdpPrincipal.add(obj);
			        obj.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Atênção, Você não tem permissão de acesso");
				}
			}
		});
        jmGerenciadorCaixa.add(jmiAbrirCaixa);
        jmiRelatorioCaixa.setText("Relatório caixa");
        jmiRelatorioCaixa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(funcionario.getSetor() == StatusSetor.CAIXA ||funcionario.getSetor() == StatusSetor.ADMINISTRADOR){
					RelatorioCaixa obj=new RelatorioCaixa();
			        jdpPrincipal.add(obj);
			        obj.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Atênção, Você não tem permissão de acesso");
				}
				
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
        setVisible(false);
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
    private javax.swing.JLabel jlbUsuario;
    private javax.swing.JLabel jlbSecao;
    private javax.swing.JLabel jlbSetor;
    private javax.swing.JLabel jlbSecaoSetor;
}
