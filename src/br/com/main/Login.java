package br.com.main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.com.dao.FuncionarioDAO;
import br.com.jdbc.FuncionarioJDBC;
import br.com.model.Funcionario;
import java.awt.Color;
import java.awt.Toolkit;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/pequeno.png")));
		initComponents();
	}

	private void initComponents() {
		jbtAcessar = new javax.swing.JButton();
		jlbSenha = new javax.swing.JLabel();
		jlbUsuario = new javax.swing.JLabel();
		jpfSenha = new javax.swing.JPasswordField();
		jtfUsuario = new javax.swing.JTextField();
		jlbImagem = new javax.swing.JLabel();

		setTitle("BURGUER SOFT");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// LABELS
		jlbUsuario = new JLabel("Usu\u00E1rio:");
		jlbUsuario.setForeground(Color.WHITE);
		jlbUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlbUsuario.setBounds(34, 22, 152, 29);
		getContentPane().add(jlbUsuario);

		jlbSenha = new JLabel("Senha:");
		jlbSenha.setForeground(Color.WHITE);
		jlbSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlbSenha.setBounds(34, 87, 152, 29);
		getContentPane().add(jlbSenha);

		// TEXTFIELDS
		getContentPane().add(jtfUsuario);
		jtfUsuario.setBounds(34, 50, 224, 30);
		getContentPane().add(jpfSenha);
		jpfSenha.setBounds(32, 118, 154, 30);

		// BOTOES
		jbtAcessar = new JButton("ACESSAR SISTEMA");
		jbtAcessar.setBounds(34, 169, 186, 30);

		jbtAcessar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FuncionarioDAO funcionarioDAO = new FuncionarioJDBC();
				Funcionario funcionario = funcionarioDAO.buscar(jtfUsuario.getText());

				if (funcionario != null
						&& funcionario.verificaSenha(new String(jpfSenha.getPassword()))) {
					dispose();
					try {
						for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
								.getInstalledLookAndFeels()) {
							if ("Nimbus".equals(info.getName())) {
								javax.swing.UIManager.setLookAndFeel(info
										.getClassName());
								break;
							}
						}
					} catch (ClassNotFoundException ex) {} 
					catch (InstantiationException ex) {}
					catch (IllegalAccessException ex) {} 
					catch (javax.swing.UnsupportedLookAndFeelException ex) {}

					java.awt.EventQueue.invokeLater(new Runnable() {
						public void run() {
							new Principal(funcionario).setVisible(true);
						}
					});
				} else {
					JOptionPane.showMessageDialog(null,
							"Usuário ou senha inválidos");
				}
			}
		});

		getContentPane().add(jbtAcessar);

		// IMAGEM
		jlbImagem = new JLabel();
		jlbImagem.setBounds(0, 0, 600, 400);
		jlbImagem
				.setIcon(new ImageIcon(Login.class.getResource("/img/4.png")));
		getContentPane().add(jlbImagem);

		setVisible(true);
		setBounds(320, 150, 610, 435);
	}

	// INICIALIZAR VARIAVEIS
	private javax.swing.JButton jbtAcessar;
	private javax.swing.JTextField jtfUsuario;
	private javax.swing.JPasswordField jpfSenha;
	private javax.swing.JLabel jlbUsuario;
	private javax.swing.JLabel jlbSenha;
	private javax.swing.JLabel jlbImagem;

	public static void main(String[] args) {
		new Login();
	}

}
