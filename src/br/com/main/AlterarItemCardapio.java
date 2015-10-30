package br.com.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.com.dao.ProdutoDAO;
import br.com.jdbc.ProdutoJDBC;
import br.com.model.Produto;
import br.com.tipo.TipoProduto;

public class AlterarItemCardapio extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	
	//PASSANDO O FUNCIONARIO SELECIONADO NA TELA CADASTROFUNCIONARIO
	public AlterarItemCardapio(Produto produto) {
		//INCIANDO O FUNCIONARIO ACIMA
		initComponents(produto);
	}
	private void initComponents(Produto produto) {
		
		jbtAlterarCadastro = new javax.swing.JButton();
		jlbNome = new javax.swing.JLabel();
		jlbCpf = new javax.swing.JLabel();
		jlbSetor = new javax.swing.JLabel();
		jlbStatus = new javax.swing.JLabel();
		jlbCpf = new javax.swing.JLabel();
		jtsRolagem = new javax.swing.JScrollPane();
		jtfValor = new javax.swing.JTextField();
		jtaDescricao = new javax.swing.JTextArea();
		jcbStatus = new javax.swing.JComboBox<String>();
		jcbTipoProduto = new javax.swing.JComboBox<String>();

		setClosable(true);
		setIconifiable(true);
		setTitle("ATERAR DADOS DO PRODUTO");
		getContentPane().setLayout(null);
		
		//LABELS
		jlbNome.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
		jlbNome.setText("DESCRI\u00C7\u00C3O:");
		getContentPane().add(jlbNome);
		jlbNome.setBounds(20, 110, 70, 30);
		
		jlbCpf.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
		jlbCpf.setText("VALOR:");
		getContentPane().add(jlbCpf);
		jlbCpf.setBounds(20, 64, 60, 30);

		jlbSetor.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
		jlbSetor.setText("TIPO PRODUTO:");
		getContentPane().add(jlbSetor);
		jlbSetor.setBounds(20, 23, 100, 30);
		
		jlbStatus.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
		jlbStatus.setText("STATUS:");
		getContentPane().add(jlbStatus);
		jlbStatus.setBounds(20, 256, 80, 30);

		//JCOMBOBOX
		jcbTipoProduto = new JComboBox<String>();
		getContentPane().add(jcbTipoProduto);
		jcbTipoProduto.setBounds(131, 24, 229, 30);
		jcbTipoProduto.addItem("ALMOÇO BIFE LIVRE");
		jcbTipoProduto.addItem("RODIZIO DE PIZZA");
		jcbTipoProduto.addItem("BIFE DE SOPAS");
		jcbTipoProduto.addItem("LANCHES");
		jcbTipoProduto.addItem("PORÇÕES");
		jcbTipoProduto.addItem("SOBREMESA");
		jcbTipoProduto.addItem("SORVETE");
		jcbTipoProduto.addItem("BEBIDAS");
		
		jcbStatus = new JComboBox<String>();
		getContentPane().add(jcbStatus);
		jcbStatus.setBounds(92, 257, 150, 30);
		jcbStatus.addItem("ATIVO");
		jcbStatus.addItem("INATIVO");
		
		getContentPane().add(jtfValor);
		jtfValor.setBounds(76, 65, 120, 30);
		
		jtaDescricao = new JTextArea(5,0);
		jtsRolagem = new JScrollPane(jtaDescricao);
		jtsRolagem.setBounds(20, 143, 468, 99);
		getContentPane().add(jtsRolagem);
		
		//CARREGANDO INFORMAÇÕES NOS TEXTFIELDS
		jcbTipoProduto.setSelectedIndex(produto.getTipoProduto().ordinal());
		jcbStatus.setSelectedItem(produto.getStatus().booleanValue());
		jtfValor.setText(produto.getValor().toString());
		jtaDescricao.append(produto.getDescricao().toString());

		
		//BOTOES
		jbtAlterarCadastro.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/img/2.png"))); // NOI18N
		jbtAlterarCadastro.setText("ATUALIZAR CADASTRO");
		jbtAlterarCadastro
				.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						//AÇÃO PARA ATUALIZAR O PRODUTO
						switch (jcbTipoProduto.getSelectedIndex()) {
						case 0:
							produto.setTipoProduto(TipoProduto.ALMOCOBIFELIVRE);						
							break;
						case 1:
							produto.setTipoProduto(TipoProduto.RODIZIOPIZZA);	
							break;
						case 2:
							produto.setTipoProduto(TipoProduto.BIFEDESOPAS);	
							break;
						case 3:
							produto.setTipoProduto(TipoProduto.LANCHES);	
							break;
						case 4:
							produto.setTipoProduto(TipoProduto.PORCOES);	
							break;
						case 5:
							produto.setTipoProduto(TipoProduto.SOBREMESA);	
							break;
						case 6:
							produto.setTipoProduto(TipoProduto.SORVETE);	
							break;
						case 7:
							produto.setTipoProduto(TipoProduto.BEBIDAS);	
							break;
						}
						
						produto.setValor(Double.parseDouble(jtfValor.getText()));
						produto.setDescricao(jtaDescricao.getText());
						produto.setStatus(jcbStatus.getSelectedIndex() == 0 ? true : false);
						
						//ALTERARNDO O PRODUTO
						ProdutoDAO produtoDAO = new ProdutoJDBC();
						produtoDAO.alterar(produto);
						JOptionPane.showMessageDialog(jbtAlterarCadastro, "Produto alterado com sucesso !!!");
						
						//RETORNANDO PARA TELA DE MONTAGEM DE CARDAPIO
						MontarCardapio mC = new MontarCardapio();
						Principal.jdpPrincipal.add(mC);
						mC.setVisible(true);
						dispose();					
					}
				});
		getContentPane().add(jbtAlterarCadastro);
		jbtAlterarCadastro.setBounds(167, 329, 240, 30);
		
		

		//FIM DA TELA
		setBounds(480, 160, 535, 412);
	}
	
	// DECLARANDO AS VARIAVEIS
	private javax.swing.JLabel jlbSetor;
	private javax.swing.JButton jbtAlterarCadastro;
	private javax.swing.JComboBox<String> jcbTipoProduto;
	private javax.swing.JComboBox<String> jcbStatus;
	private javax.swing.JLabel jlbCpf;
	private javax.swing.JScrollPane jtsRolagem;
	private javax.swing.JLabel jlbNome;
	private javax.swing.JTextArea jtaDescricao;
	private javax.swing.JLabel jlbStatus;
	private javax.swing.JTextField jtfValor;
}
