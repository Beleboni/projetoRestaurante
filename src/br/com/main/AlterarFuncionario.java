package br.com.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import br.com.dao.FuncionarioDAO;
import br.com.jdbc.FuncionarioJDBC;
import br.com.model.Funcionario;
import br.com.tipo.StatusSetor;
import br.com.tipo.StatusFuncionario;
import javax.swing.ImageIcon;

public class AlterarFuncionario extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	
	//PASSANDO O FUNCIONARIO SELECIONADO NA TELA CADASTROFUNCIONARIO
	public AlterarFuncionario(Funcionario funcionario) {
		setFrameIcon(new ImageIcon(AlterarFuncionario.class.getResource("/img/pequeno.png")));
		//INCIANDO O FUNCIONARIO ACIMA
		initComponents(funcionario);
	}
	private void initComponents(Funcionario funcionario) {

		jbtAlterarCadastro = new javax.swing.JButton();
		jlbNome = new javax.swing.JLabel();
		jlbTelefone = new javax.swing.JLabel();
		jlbCpf = new javax.swing.JLabel();
		jlbSetor = new javax.swing.JLabel();
		jlbUsuario = new javax.swing.JLabel();
		jlbStatus = new javax.swing.JLabel();
		jlbCpf = new javax.swing.JLabel();
		jlbUsuario = new javax.swing.JLabel();
		jtfNome = new javax.swing.JTextField();
		jtfTelefone = new javax.swing.JTextField();
		jtfCpf = new javax.swing.JTextField();
		jtfUsuario = new javax.swing.JTextField();
		jtfSenha = new javax.swing.JTextField();
		jlbSenha = new javax.swing.JLabel();
		jcbSetor = new javax.swing.JComboBox<String>();
		jcbStatus = new javax.swing.JComboBox<String>();

		setClosable(true);
		setIconifiable(true);
		setTitle("BURGUER SOFT -- ATERAR DADOS DO FUNCIONARIO");
		getContentPane().setLayout(null);
		
		//LABELS
		jlbNome.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
		jlbNome.setText("NOME:");
		getContentPane().add(jlbNome);
		jlbNome.setBounds(20, 40, 70, 30);
		
		jlbCpf.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
		jlbCpf.setText("CPF:");
		getContentPane().add(jlbCpf);
		jlbCpf.setBounds(20, 80, 60, 30);
		
				
		jlbTelefone.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
		jlbTelefone.setText("TELEFONE:");
		getContentPane().add(jlbTelefone);
		jlbTelefone.setBounds(20, 120, 100, 30);

		jlbSetor.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
		jlbSetor.setText("SETOR:");
		getContentPane().add(jlbSetor);
		jlbSetor.setBounds(20, 160, 80, 30);

		jlbUsuario.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
		jlbUsuario.setText("USUARIO:");
		getContentPane().add(jlbUsuario);
		jlbUsuario.setBounds(20, 200, 70, 30);
		
		jlbSenha.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
		jlbSenha.setText("SENHA:");
		getContentPane().add(jlbSenha);
		jlbSenha.setBounds(20, 240, 80, 30);
		
		jlbStatus.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
		jlbStatus.setText("STATUS:");
		getContentPane().add(jlbStatus);
		jlbStatus.setBounds(20, 280, 80, 30);

		//JCOMBOBOX
		jcbSetor = new JComboBox<String>();
		getContentPane().add(jcbSetor);
		jcbSetor.setBounds(80, 160, 140, 30);
		jcbSetor.addItem("ADMINSTRADOR");
		jcbSetor.addItem("CAIXA");
		jcbSetor.addItem("COZINHEIRO");
		jcbSetor.addItem("GARÇOM");
		
		jcbStatus = new JComboBox<String>();
		getContentPane().add(jcbStatus);
		jcbStatus.setBounds(90, 280, 150, 30);
		jcbStatus.addItem("ATIVO");
		jcbStatus.addItem("INATIVO");
		
		// TEXTFIELDS
		getContentPane().add(jtfNome);
		jtfNome.setBounds(70, 40, 120, 30);	
		jtfNome.setEnabled(false);
		
		getContentPane().add(jtfCpf);
		jtfCpf.setBounds(60, 80, 120, 30);
		jtfCpf.setEnabled(false);
		
		getContentPane().add(jtfTelefone);
		jtfTelefone.setBounds(90, 120, 120, 30);
		
		getContentPane().add(jtfUsuario);
		jtfUsuario.setBounds(90, 200, 120, 30);	
		jtfUsuario.setEnabled(false);
		
		getContentPane().add(jtfSenha);
		jtfSenha.setBounds(80, 240, 200, 30);
		
		//CARREGANDO INFORMAÇÕES NOS TEXTFIELDS
		jtfTelefone.setText(funcionario.getTelefone());
		jtfUsuario.setText(funcionario.getUsuario());
		jtfSenha.setText(funcionario.getSenha());
		jcbSetor.setSelectedIndex(funcionario.getSetor().ordinal());
		jcbStatus.setSelectedItem(funcionario.getStatus().ordinal());
		jtfNome.setText(funcionario.getNome());
		jtfCpf.setText(funcionario.getCpf());
		jtfUsuario.setText(funcionario.getUsuario());

		
		//BOTOES
		jbtAlterarCadastro.setIcon(new ImageIcon(AlterarFuncionario.class.getResource("/img/2.png"))); 
		jbtAlterarCadastro.setText("ALTERAR CADASTRO");
		jbtAlterarCadastro
				.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						//AÇÃO DE ATUALIZAR OS DADOS
						funcionario.setNome(jtfNome.getText());
						funcionario.setCpf(jtfCpf.getText());
						funcionario.setTelefone(jtfTelefone.getText());
						
						// PEGANDO A SELEÇÃO USANDO O ENUM
						switch (jcbSetor.getSelectedIndex()) {
						case 0:
							funcionario.setSetor(StatusSetor.ADMINISTRADOR);
							break;
						case 1:
							funcionario.setSetor(StatusSetor.CAIXA);
							break;
						case 2:
							funcionario.setSetor(StatusSetor.COZINHEIRO);
							break;
						case 3:
							funcionario.setSetor(StatusSetor.GARCOM);
							break;
						}
						funcionario.setUsuario(jtfUsuario.getText());
						funcionario.setSenha(jtfSenha.getText());
						
						//PEGANDO A SELEÇÃO DO STATUS DO COLABORADOS EM ENUM
						switch (jcbStatus.getSelectedIndex()) {
						case 0:
							funcionario.setStatus(StatusFuncionario.ATIVO);
							break;

						case 1:
							funcionario.setStatus(StatusFuncionario.INATIVO);
							break;
						}
								
						//ALTERANDO O FUNCIONARIO
						FuncionarioDAO funcionarioDAO = new FuncionarioJDBC();
						funcionarioDAO.alterar(funcionario);
						
						JOptionPane.showMessageDialog(null, "Dados alterrados com sucesso !!!");
						CadastroFuncionario At = new CadastroFuncionario();
						Principal.jdpPrincipal.add(At);
						At.setVisible(true);
						dispose();
						
					}
				});
		getContentPane().add(jbtAlterarCadastro);
		jbtAlterarCadastro.setBounds(70, 340, 240, 30);

		//FIM DA TELA
		setBounds(480, 160, 397, 412);
	}
	
	// DECLARANDO AS VARIAVEIS
	private javax.swing.JLabel jlbSetor;
	private javax.swing.JButton jbtAlterarCadastro;
	private javax.swing.JComboBox<String> jcbSetor;
	private javax.swing.JComboBox<String> jcbStatus;
	private javax.swing.JLabel jlbCpf;
	private javax.swing.JLabel jlbNome;
	private javax.swing.JLabel jlbSenha;
	private javax.swing.JLabel jlbTelefone;
	private javax.swing.JLabel jlbUsuario;
	private javax.swing.JLabel jlbStatus;
	private javax.swing.JTextField jtfSenha;
	private javax.swing.JTextField jtfCpf;
	private javax.swing.JTextField jtfNome;
	private javax.swing.JTextField jtfTelefone;
	private javax.swing.JTextField jtfUsuario;
}
