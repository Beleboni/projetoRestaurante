package br.com.main;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class Login extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;

	public Login() {
		initComponents();
		setVisible(true);
	}

	private void initComponents() {

		jbtAcessar = new javax.swing.JButton();
		jlbSenha = new javax.swing.JLabel();
		jlbUsuario = new javax.swing.JLabel();
		jpfSenha = new javax.swing.JPasswordField();
		jtfUsuario = new javax.swing.JTextField();
		jlbImagem = new javax.swing.JLabel();

		setClosable(true);
		setIconifiable(true);
		setTitle("ACESSO AO SISTEMA V2");
		getContentPane().setLayout(null);
		
		//LABELS
		jlbUsuario = new JLabel("Usu\u00E1rio:");
		jlbUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlbUsuario.setBounds(34, 22, 152, 29);
		getContentPane().add(jlbUsuario);
		
		jlbSenha = new JLabel("Senha:");
		jlbSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlbSenha.setBounds(34, 87, 152, 29);
		getContentPane().add(jlbSenha);
		
		//TEXTFIELDS
		getContentPane().add(jtfUsuario);
		jtfUsuario.setBounds(34, 50, 224, 30);
		getContentPane().add(jpfSenha);
		jpfSenha.setBounds(32, 118, 154, 30);
		
		//BOTOES
		jbtAcessar = new JButton("ACESSAR SISTEMA");
		jbtAcessar.setBounds(34, 169, 186, 30);
		getContentPane().add(jbtAcessar);
		
		//IMAGEM
		jlbImagem = new JLabel();
		jlbImagem.setBounds(0, 0, 600, 400);
		jlbImagem.setIcon(new ImageIcon("C:\\Users\\Fernando\\Documents\\Bakup Fernando\\Projetos\\Java 4\\projetoRestaurante\\src\\img\\4.png"));
		getContentPane().add(jlbImagem);
		

		setBounds(320, 150, 610, 435);
	}

	// INICIALIZAR VARIAVEIS
	private javax.swing.JButton jbtAcessar;
	private javax.swing.JTextField jtfUsuario;
	private javax.swing.JPasswordField jpfSenha;
	private javax.swing.JLabel jlbUsuario;
	private javax.swing.JLabel jlbSenha;
	private javax.swing.JLabel jlbImagem;
}
