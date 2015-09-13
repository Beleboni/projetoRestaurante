package br.com.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.dao.FuncionarioDAO;
import br.com.jdbc.FuncionarioJDBC;
import br.com.model.DateUtil;
import br.com.model.Funcionario;
import br.com.model.Setor;

public class CadastroFuncionario extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmLista;
	private JScrollPane jspRolagem;
	private JTable jtTabela;
	private JLabel jlbAdmissaoOuDemissao;
	private JTextField jtfAdmissaoOuDemissao;

	private boolean demissao;
	private Funcionario funcionario;

	public CadastroFuncionario(boolean demissao, Funcionario funcionario) {
		this.demissao = demissao;
		this.funcionario = funcionario;
		initComponents();
	}

	public boolean isDemissao() {
		return this.demissao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	private void initComponents() {
		jlbNome = new javax.swing.JLabel();
		jlbTelefone = new javax.swing.JLabel();
		jlbCpf = new javax.swing.JLabel();
		jlbSetor = new javax.swing.JLabel();
		jlbUsuario = new javax.swing.JLabel();
		jlbSenha = new javax.swing.JLabel();
		jtfNome = new javax.swing.JTextField();
		jtfTelefone = new javax.swing.JTextField();
		jtfAdmissaoOuDemissao = new javax.swing.JTextField();
		jtfCpf = new javax.swing.JTextField();
		jtfUsuario = new javax.swing.JTextField();
		jbCadastro = new javax.swing.JButton();
		jbtAlterar = new javax.swing.JButton();
		jlbTitulo1 = new javax.swing.JLabel();
		jlbTitulo2 = new javax.swing.JLabel();
		jlbAdmissaoOuDemissao = new javax.swing.JLabel();
		jcbSetor = new javax.swing.JComboBox<String>();
		jpfSenha = new javax.swing.JPasswordField();

		setClosable(true);
		setIconifiable(true);
		setTitle("CADASTRO FUNCIONARIO");
		getContentPane().setLayout(null);

		// LABELS
		jlbTitulo1.setFont(new java.awt.Font("Miriam", 1, 14)); // NOI18N
		jlbTitulo1.setText("CADASTRAR  NOVO FUNCIONARIO:");
		getContentPane().add(jlbTitulo1);
		jlbTitulo1.setBounds(20, 10, 280, 15);

		jlbTitulo2.setFont(new java.awt.Font("Miriam", 1, 14)); // NOI18N
		jlbTitulo2.setText("TODOS OS FUNCIONARIOS CADASTRADOS: ");
		getContentPane().add(jlbTitulo2);
		jlbTitulo2.setBounds(400, 10, 350, 20);

		jlbNome.setFont(new java.awt.Font("Trebuchet MS", 1, 12));
		jlbNome.setText("NOME:");
		getContentPane().add(jlbNome);
		jlbNome.setBounds(20, 40, 70, 30);

		jlbTelefone.setFont(new java.awt.Font("Trebuchet MS", 1, 12));
		jlbTelefone.setText("TELEFONE:");
		getContentPane().add(jlbTelefone);
		jlbTelefone.setBounds(20, 120, 100, 30);

		jlbCpf.setFont(new java.awt.Font("Trebuchet MS", 1, 12));
		jlbCpf.setText("CPF:");
		getContentPane().add(jlbCpf);
		jlbCpf.setBounds(20, 80, 60, 30);

		jlbSetor.setFont(new java.awt.Font("Trebuchet MS", 1, 12));
		jlbSetor.setText("SETOR:");
		getContentPane().add(jlbSetor);
		jlbSetor.setBounds(20, 160, 80, 30);

		jlbAdmissaoOuDemissao.setFont(new java.awt.Font("Trebuchet MS", 1, 12));
		jlbAdmissaoOuDemissao.setText(this.isDemissao() ? "DEMISSÃO:"
				: "ADMISSÃO:");
		//System.out.println(jlbAdmissaoOuDemissao.getText());
		getContentPane().add(jlbAdmissaoOuDemissao);
		jlbAdmissaoOuDemissao.setBounds(20, 200, 80, 30);

		jlbUsuario.setFont(new java.awt.Font("Trebuchet MS", 1, 12));
		jlbUsuario.setText("USUARIO:");
		getContentPane().add(jlbUsuario);
		jlbUsuario.setBounds(20, 240, 70, 30);

		jlbSenha.setFont(new java.awt.Font("Trebuchet MS", 1, 12));
		jlbSenha.setText("SENHA:");
		getContentPane().add(jlbSenha);
		jlbSenha.setBounds(20, 280, 80, 30);

		// TEXTFIELDS
		getContentPane().add(jtfNome);
		jtfNome.setBounds(70, 40, 250, 30);

		getContentPane().add(jtfTelefone);
		jtfTelefone.setBounds(90, 120, 120, 30);

		getContentPane().add(jtfCpf);
		jtfCpf.setBounds(70, 80, 110, 30);

		getContentPane().add(jtfAdmissaoOuDemissao);
		jtfAdmissaoOuDemissao.setBounds(90, 200, 110, 30);

		getContentPane().add(jtfUsuario);
		jtfUsuario.setBounds(80, 240, 132, 30);

		getContentPane().add(jpfSenha);
		jpfSenha.setBounds(80, 280, 130, 30);

		// JCOMBOBOX
		jcbSetor = new JComboBox<String>();
		getContentPane().add(jcbSetor);
		jcbSetor.setBounds(80, 160, 170, 30);
		jcbSetor.addItem("ADMINISTRADOR");
		jcbSetor.addItem("CAIXA");
		jcbSetor.addItem("COZINHEIRO");
		jcbSetor.addItem("GARÇOM");

		dtmLista = new DefaultTableModel();
		dtmLista.addColumn("COD.");
		dtmLista.addColumn("NOME");
		dtmLista.addColumn("SETOR");
		dtmLista.addColumn("TELEFONE");
		dtmLista.addColumn("ADMISSÃO");
		dtmLista.addColumn("DEMISSÃO");

		// POPULANDO A TABELA
		FuncionarioDAO funcionarioDAO = new FuncionarioJDBC();
		funcionarioDAO.todos().forEach(
				f -> dtmLista.addRow(new String[] { f.getCodigo().toString(),
						f.getNome().toString(), f.getSetor().toString(),
						f.getTelefone().toString(),
						DateUtil.toString(f.getAdmissao()),
						DateUtil.toString(f.getDemissao()) }));

		jtTabela = new JTable(dtmLista);
		jspRolagem = new JScrollPane(jtTabela);
		jspRolagem.setBounds(400, 40, 660, 280);
		jspRolagem.setBackground(Color.white);
		getContentPane().add(jspRolagem);

		// FIM DA TELA
		setBounds(100, 130, 1100, 420);

		// AÇÕES DE BOTOES
		jbCadastro.setText("CADASTRAR");
		jbCadastro.addActionListener(new ActionListener() {

			// CADASTRAR NOVO FUNCIONARIO
			@Override
			public void actionPerformed(ActionEvent e) {
				// TESTANDO SE ESTAO VAZIAS
				if (jtfNome.getText().equals("") || jtfCpf.getText().equals("")
						|| jtfTelefone.getText().equals("")
						|| jtfUsuario.getText().equals("")
						|| jpfSenha.getPassword().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Preencha todos os dados !!!");

				} else {
					// INICIA AS CLASSES RESPONSAVEIS POR CADASTRAR UM NOVO
					// VALOR
					FuncionarioDAO funcionarioDAO = new FuncionarioJDBC();
					Funcionario funcionario = getFuncionario();

					funcionario.setNome(jtfNome.getText());
					funcionario.setCpf(jtfCpf.getText());
					funcionario.setTelefone(Integer.parseInt(jtfTelefone
							.getText()));

					// TESTA O ENUM CLASSE
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

					if (!isDemissao()) {
						funcionario.setAdmissao(DateUtil
								.toLocalDate(jtfAdmissaoOuDemissao.getText()));
						funcionario.setDemissao(null);
					} else {
						funcionario.setDemissao(DateUtil
								.toLocalDate(jtfAdmissaoOuDemissao.getText()));
					}
					funcionario.setUsuario(jtfUsuario.getText());
					funcionario.setSenha(new String(jpfSenha.getPassword()));

					// AÇÃO QUE CADASTRA O FUNCIONARIO NO BANCO
					funcionarioDAO.inserir(funcionario);

					JOptionPane.showMessageDialog(null,
							"Cadastro realizado com sucesso !!!");

					// LIMPAR CAMPOS
					jtfNome.setText("");
					jtfCpf.setText("");
					jtfTelefone.setText("");
					jtfUsuario.setText("");
					jpfSenha.setText("");

					// LIMPAR TABELA
					dtmLista.setNumRows(0);
					// ATUALIZANDO A TABELA
					funcionarioDAO.todos().forEach(
							f -> dtmLista.addRow(new String[] {
									f.getCodigo().toString(),
									f.getNome().toString(),
									f.getSetor().toString(),
									f.getTelefone().toString() }));
				}
			}
		});
		getContentPane().add(jbCadastro);
		jbCadastro.setBounds(20, 340, 170, 30);

		jbtAlterar.setIcon(new ImageIcon("img/2.png"));
		jbtAlterar.setText("ALTERAR");
		getContentPane().add(jbtAlterar);
		jbtAlterar.setBounds(400, 340, 117, 30);
		jbtAlterar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = jtTabela.getSelectedRow();
				Integer codigo = Integer.valueOf((String) dtmLista.getValueAt(
						linha, 0));
				new CadastroFuncionario(true, funcionarioDAO.buscar(codigo));
				// JOptionPane.showMessageDialog(null,
				// "Escolha alguém da tabela para alterar");
			}
		});

	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jbCadastro;
	private javax.swing.JButton jbtAlterar;
	private javax.swing.JComboBox<String> jcbSetor;
	private javax.swing.JLabel jlbCpf;
	private javax.swing.JLabel jlbNome;
	private javax.swing.JLabel jlbSenha;
	private javax.swing.JLabel jlbSetor;
	private javax.swing.JLabel jlbTelefone;
	private javax.swing.JLabel jlbTitulo1;
	private javax.swing.JLabel jlbTitulo2;
	private javax.swing.JLabel jlbUsuario;
	private javax.swing.JPasswordField jpfSenha;
	private javax.swing.JTextField jtfCpf;
	private javax.swing.JTextField jtfNome;
	private javax.swing.JTextField jtfTelefone;
	private javax.swing.JTextField jtfUsuario;
	// End of variables declaration//GEN-END:variables
}
