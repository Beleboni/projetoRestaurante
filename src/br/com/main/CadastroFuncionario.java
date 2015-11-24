package br.com.main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.dao.FuncionarioDAO;
import br.com.estilo.Mascara;
import br.com.exception.UsoException;
import br.com.jdbc.FuncionarioJDBC;
import br.com.model.Funcionario;
import br.com.tipo.StatusSetor;
import br.com.tipo.StatusFuncionario;

import javax.swing.ImageIcon;


public class CadastroFuncionario extends javax.swing.JInternalFrame {
	private static final long serialVersionUID = 1L;

	private FuncionarioDAO funcionarioDAO = new FuncionarioJDBC();
	private Funcionario funcionario = new Funcionario();

	private DefaultTableModel dtmLista;
	private JTable jtTabela;
	private JScrollPane jspRolagem;

	public CadastroFuncionario() {
		setFrameIcon(new ImageIcon(CadastroFuncionario.class.getResource("/img/pequeno.png")));
		initComponents();
	}

	// INICIA OS COMPONENTES
	private void initComponents() {

		jlbNome = new javax.swing.JLabel();
		jlbTelefone = new javax.swing.JLabel();
		jlbCpf = new javax.swing.JLabel();
		jlbUsuario = new javax.swing.JLabel();
		jlbSenha = new javax.swing.JLabel();
		jtfNome = new javax.swing.JTextField();
		jtfTelefone = new javax.swing.JFormattedTextField(Mascara.getTelefoneMask());
		jtfCpf = new javax.swing.JFormattedTextField(Mascara.getCpfMask());
		jbCadastro = new javax.swing.JButton();
		jbtAlterar = new javax.swing.JButton();
		jlbTitulo1 = new javax.swing.JLabel();
		jlbTitulo2 = new javax.swing.JLabel();
		jpfSenha = new javax.swing.JPasswordField();
		jlbSetor1 = new javax.swing.JLabel();
		jtfUsuario = new javax.swing.JTextField();

		// INICIO DA TELA
		setClosable(true);
		setIconifiable(true);
		setTitle("BURGUER SOFT --  CADASTRO FUNCIONARIO");
		getContentPane().setLayout(null);

		// LABELS
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

		jlbUsuario.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
		jlbUsuario.setText("USUARIO:");
		getContentPane().add(jlbUsuario);
		jlbUsuario.setBounds(20, 200, 70, 30);

		jlbSenha.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
		jlbSenha.setText("SENHA:");
		getContentPane().add(jlbSenha);
		jlbSenha.setBounds(20, 240, 80, 30);

		jlbTitulo1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
		jlbTitulo1.setText("CADASTRAR  NOVO FUNCIONARIO:");
		getContentPane().add(jlbTitulo1);
		jlbTitulo1.setBounds(20, 10, 280, 15);

		jlbTitulo2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
		jlbTitulo2.setText("TODOS OS FUNCIONARIOS CADASTRADOS: ");
		getContentPane().add(jlbTitulo2);
		jlbTitulo2.setBounds(400, 10, 350, 20);

		jlbSetor1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
		jlbSetor1.setText("SETOR:");
		getContentPane().add(jlbSetor1);
		jlbSetor1.setBounds(20, 160, 80, 30);

		// TEXTFIELDS
		getContentPane().add(jtfNome);
		jtfNome.setBounds(70, 40, 250, 30);
		getContentPane().add(jtfTelefone);
		jtfTelefone.setBounds(90, 120, 160, 30);
		getContentPane().add(jtfCpf);
	
		jtfCpf.setBounds(70, 80, 140, 30);
		getContentPane().add(jtfUsuario);
		jtfUsuario.setBounds(80, 200, 200, 30);
		getContentPane().add(jpfSenha);
		jpfSenha.setBounds(80, 240, 200, 30);

		
		// JCOMBOBOX
		jcbSetor = new JComboBox<String>();
		getContentPane().add(jcbSetor);
		jcbSetor.setBounds(80, 160, 170, 30);
		jcbSetor.addItem("ADMINSTRADOR");
		jcbSetor.addItem("CAIXA");
		jcbSetor.addItem("COZINHEIRO");
		jcbSetor.addItem("GARÇOM");

		dtmLista = new DefaultTableModel();
		dtmLista.addColumn("COD.");
		dtmLista.addColumn("NOME");
		dtmLista.addColumn("SETOR");
		dtmLista.addColumn("TELEFONE");
		dtmLista.addColumn("STATUS");


		// POPULANDO A TABELA
		funcionarioDAO.todos().forEach(
				f -> dtmLista.addRow(new String[] { f.getCodigo().toString(),
						f.getNome().toString(), f.getSetor().toString(),
						f.getTelefone().toString(), f.getStatus().toString()
						}));

		jtTabela = new JTable(dtmLista);
		jtTabela.getColumnModel().getColumn(0).setMaxWidth(50);
		jtTabela.getColumnModel().getColumn(1).setMaxWidth(300);
		jtTabela.getColumnModel().getColumn(2).setMaxWidth(150);
		jtTabela.getColumnModel().getColumn(3).setMaxWidth(150);
		jtTabela.getColumnModel().getColumn(4).setMaxWidth(60);
		jspRolagem = new JScrollPane(jtTabela);
		jspRolagem.setBounds(400, 40, 710, 280);
		getContentPane().add(jspRolagem);

		// CADASTRAR NOVO FUNCIONARIO
		jbCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/2.png"))); 
		jbCadastro.setText("CADASTRAR");
		jbCadastro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TESTANDO SE ESTÃO VAZIAS
				if (jtfNome.getText().equals("") || jtfCpf.getText().equals("")
						|| jtfTelefone.getText().equals("")
						|| jtfUsuario.getText().equals("")
						|| jpfSenha.getPassword().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Preencha todos sos dados !!!");
				} else {
					// CADASTRANDO UM USUARIO
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
					funcionario.setAdmissao(LocalDate.now());
					funcionario.setUsuario(jtfUsuario.getText());
					funcionario.setSenha(new String(jpfSenha.getPassword()));
					funcionario.setStatus(StatusFuncionario.ATIVO);

					// CADASTRADO O FUNCIONARIO
					try {
						funcionarioDAO.inserir(funcionario);
					} catch (UsoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
						return;
					}

					JOptionPane.showMessageDialog(null,
							"Cadastro realizado com sucesso !!!");

					// LIMPAR DADOS
					jtfNome.setText("");
					jtfCpf.setText("");
					jtfTelefone.setText("");
					jpfSenha.setText("");
					jtfUsuario.setText("");
					jcbSetor.setSelectedItem("");
					
					//LIMPANDO A TABELA
					dtmLista.setNumRows(0);
					
					// ATUALIZANDO A TABELA
					funcionarioDAO.todos().forEach(
							f -> dtmLista.addRow(new String[] {
									f.getCodigo().toString(),
									f.getNome().toString(),
									f.getSetor().toString(),
									f.getTelefone().toString(),
									f.getStatus().toString() }));
				}

			}
		});
		getContentPane().add(jbCadastro);
		jbCadastro.setBounds(10, 340, 130, 30);

		jbtAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/2.png"))); 
		jbtAlterar.setText("ALTERAR");
		jbtAlterar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// AÇÃO DO BOTAO ALTERAR
				if(jtTabela.getSelectedRowCount() == 0){
					JOptionPane.showMessageDialog(jtTabela, "Por favor, selecione o usuario para alterar !!!");
				}else{
					//PEGANDO A SELEÇÃO DO LINHA NA TABELA
					int linha = jtTabela.getSelectedRow();
					//PEGANDO O CODIGO NA LINHA SELECIONADA
					Integer codigo = Integer.valueOf((String) dtmLista.getValueAt(linha, 0));
					//ABRE A TELA ALTERARCADASTRO E TESTANDO SE O CODIGO PASSADO EXISTE NO BANCO
					AlterarFuncionario At = new AlterarFuncionario(funcionarioDAO.buscar(codigo));
					Principal.jdpPrincipal.add(At);
					At.setVisible(true);
					dispose();
				}
			}
		});
		getContentPane().add(jbtAlterar);
		jbtAlterar.setBounds(400, 340, 117, 30);

		// FIM DA TELA
		setBounds(115, 130, 1142, 416);
	}

	// DECLARAÇÃO DE VARIAVEIS
	private javax.swing.JButton jbCadastro;
	private javax.swing.JButton jbtAlterar;
	private javax.swing.JComboBox<String> jcbSetor;
	private javax.swing.JLabel jlbCpf;
	private javax.swing.JLabel jlbNome;
	private javax.swing.JLabel jlbSenha;
	private javax.swing.JLabel jlbSetor1;
	private javax.swing.JLabel jlbTelefone;
	private javax.swing.JLabel jlbTitulo1;
	private javax.swing.JLabel jlbTitulo2;
	private javax.swing.JLabel jlbUsuario;
	private javax.swing.JPasswordField jpfSenha;
	private javax.swing.JFormattedTextField jtfCpf;
	private javax.swing.JTextField jtfNome;
	private javax.swing.JFormattedTextField jtfTelefone;
	private javax.swing.JTextField jtfUsuario;

}
