package br.com.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import br.com.dao.FuncionarioDAO;
import br.com.jdbc.FuncionarioJDBC;
import br.com.model.Funcionario;
import br.com.tipo.Setor;
import br.com.util.DateUtil;

public class AlterarFuncionario extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	
	//PASSANDO O FUNCIONARIO SELECIONADO NA TELA CADASTROFUNCIONARIO
	public AlterarFuncionario(Funcionario funcionario) {
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
		jlbDemissao = new javax.swing.JLabel();
		jtfNome = new javax.swing.JTextField();
		jtfTelefone = new javax.swing.JTextField();
		jtfCpf = new javax.swing.JTextField();
		jtfUsuario = new javax.swing.JTextField();
		jtfSenha = new javax.swing.JTextField();
		jlbSenha1 = new javax.swing.JLabel();
		jtfDemissao = new javax.swing.JTextField();
		jcbSetor = new javax.swing.JComboBox<String>();

		setClosable(true);
		setIconifiable(true);
		setTitle("ATERAR DADOS DO FUNCIONARIO");
		getContentPane().setLayout(null);
		
		//LABELS
		jlbNome.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
		jlbNome.setText("NOME:");
		getContentPane().add(jlbNome);
		jlbNome.setBounds(20, 40, 70, 30);

		jlbTelefone.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
		jlbTelefone.setText("TELEFONE:");
		getContentPane().add(jlbTelefone);
		jlbTelefone.setBounds(20, 120, 100, 30);

//		jlbCpf.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
//		jlbCpf.setText("CPF:");
//		getContentPane().add(jlbCpf);
//		jlbCpf.setBounds(20, 80, 60, 30);

		jlbSetor.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
		jlbSetor.setText("SETOR:");
		getContentPane().add(jlbSetor);
		jlbSetor.setBounds(20, 160, 80, 30);

//		jlbUsuario.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
//		jlbUsuario.setText("USUARIO:");
//		getContentPane().add(jlbUsuario);
//		jlbUsuario.setBounds(20, 200, 70, 30);

//		jlbDemissao.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
//		jlbDemissao.setText("FUNCIONARIO: ");
//		getContentPane().add(jlbDemissao);
//		jlbDemissao.setBounds(20, 290, 100, 30);
		
		jlbSenha1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
		jlbSenha1.setText("SENHA:");
		getContentPane().add(jlbSenha1);
		jlbSenha1.setBounds(20, 240, 80, 30);

		
		// TEXTFIELDS
		getContentPane().add(jtfNome);
		jtfNome.setBounds(70, 40, 250, 30);
		getContentPane().add(jtfTelefone);
		jtfTelefone.setBounds(90, 120, 120, 30);
		getContentPane().add(jtfCpf);
		jtfCpf.setBounds(70, 80, 110, 30);
		getContentPane().add(jtfUsuario);
		jtfUsuario.setBounds(80, 200, 132, 30);
		getContentPane().add(jtfSenha);
		jtfSenha.setBounds(80, 240, 130, 30);
		getContentPane().add(jtfDemissao);
		jtfDemissao.setBounds(120, 290, 150, 30);

		//JCOMBOBOX
		jcbSetor = new JComboBox<String>();
		getContentPane().add(jcbSetor);
		jcbSetor.setBounds(80, 160, 150, 30);
		jcbSetor.addItem("ADMINSTRADOR");
		jcbSetor.addItem("CAIXA");
		jcbSetor.addItem("COZINHEIRO");
		jcbSetor.addItem("GARÇOM");
		
		//CARREGANDO INFORMAÇÕES NOS TEXTFIELDS
		jlbNome.setText(funcionario.getNome());
		jtfCpf.setText(funcionario.getCpf());
		jtfTelefone.setText(funcionario.getTelefone());
		jlbUsuario.setText(funcionario.getUsuario());
		jtfSenha.setText(funcionario.getSenha());
		
		//BOTOES
		jbtAlterarCadastro.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/img/2.png"))); // NOI18N
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
							funcionario.setSetor(Setor.ADMINISTRADOR);
							break;
						case 1:
							funcionario.setSetor(Setor.CAIXA);
							break;
						case 2:
							funcionario.setSetor(Setor.COZINHEIRO);
							break;
						case 3:
							funcionario.setSetor(Setor.GARCOM);
							break;
						}
						funcionario.setUsuario(jtfUsuario.getText());
						funcionario.setSenha(jtfSenha.getText());
						funcionario.setDemissao(DateUtil.toLocalDate(jtfDemissao.getText()));
						
						//ALTERANDO O FUNCIONARIO
						FuncionarioDAO funcionarioDAO = new FuncionarioJDBC();
						funcionarioDAO.alterar(funcionario);
						
					}
				});
		getContentPane().add(jbtAlterarCadastro);
		jbtAlterarCadastro.setBounds(70, 340, 240, 30);

		//FIM DA TELA
		setBounds(480, 160, 385, 412);
	}
	
	// DECLARANDO AS VARIAVEIS
	private javax.swing.JLabel jlbSetor;
	private javax.swing.JButton jbtAlterarCadastro;
	private javax.swing.JTextField jtfDemissao;
	private javax.swing.JComboBox<String> jcbSetor;
	private javax.swing.JLabel jlbCpf;
	private javax.swing.JLabel jlbDemissao;
	private javax.swing.JLabel jlbNome;
	private javax.swing.JLabel jlbSenha1;
	private javax.swing.JLabel jlbTelefone;
	private javax.swing.JLabel jlbUsuario;
	private javax.swing.JTextField jtfSenha;
	private javax.swing.JTextField jtfCpf;
	private javax.swing.JTextField jtfNome;
	private javax.swing.JTextField jtfTelefone;
	private javax.swing.JTextField jtfUsuario;
}
