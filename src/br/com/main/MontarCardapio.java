package br.com.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;

import br.com.dao.ProdutoDAO;
import br.com.jdbc.ProdutoJDBC;
import br.com.model.Produto;
import br.com.tipo.StatusProduto;
import br.com.tipo.TipoProduto;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class MontarCardapio extends javax.swing.JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private ProdutoDAO produtoDao = new ProdutoJDBC();
	private Produto produto = new Produto();
	
	private DefaultTableModel dtmLista;
	
    public MontarCardapio() {
    	setFrameIcon(new ImageIcon(MontarCardapio.class.getResource("/img/pequeno.png")));
        initComponents();
    }

    private void initComponents() {

        jlbItens = new javax.swing.JLabel();
        jlbValor = new javax.swing.JLabel();
        jlbCodigo = new javax.swing.JLabel();
        jtfValor = new javax.swing.JTextField();
        jbtCancelar = new javax.swing.JButton();
        jbtCadastrar = new javax.swing.JButton();
        jbtAlterarDados = new javax.swing.JButton();
        jlbNome1 = new javax.swing.JLabel();
        jtsRolagem = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jcbTipoProduto = new javax.swing.JComboBox<String>();
        jspRolagem = new javax.swing.JScrollPane();
        jtaDescricao = new javax.swing.JTextArea();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("BURGUER SOFT -- MONTAR CARDAPIO");
        getContentPane().setLayout(null);

        jlbItens.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
        jlbItens.setText("ITENS ADICIONADOS AO CARDAPIO:");
        getContentPane().add(jlbItens);
        jlbItens.setBounds(20, 280, 210, 30);

        jlbValor.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
        jlbValor.setText("VALOR:");
        getContentPane().add(jlbValor);
        jlbValor.setBounds(430, 10, 80, 30);

        jlbCodigo.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
        jlbCodigo.setText("TIPO DE PRODUTO:");
        getContentPane().add(jlbCodigo);
        jlbCodigo.setBounds(20, 10, 130, 30);
        getContentPane().add(jtfValor);
        jtfValor.setBounds(510, 10, 100, 30);

        
        jlbNome1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
        jlbNome1.setText("DESCRIÇÃO:");
        getContentPane().add(jlbNome1);
        jlbNome1.setBounds(20, 60, 110, 30);

                
        jcbTipoProduto = new JComboBox<String>();
		getContentPane().add(jcbTipoProduto);
		jcbTipoProduto.setBounds(150, 10, 200, 30);
		jcbTipoProduto.addItem("ALMOÇO BIFE LIVRE");
		jcbTipoProduto.addItem("RODIZIO DE PIZZA");
		jcbTipoProduto.addItem("BIFE DE SOPAS");
		jcbTipoProduto.addItem("LANCHES");
		jcbTipoProduto.addItem("PORÇÕES");
		jcbTipoProduto.addItem("SOBREMESA");
		jcbTipoProduto.addItem("SORVETE");
		jcbTipoProduto.addItem("BEBIDAS");
		
    
		dtmLista = new DefaultTableModel();
		dtmLista.addColumn("COD.");
		dtmLista.addColumn("TIPO DE PRODUTO");
		dtmLista.addColumn("VALOR");
		dtmLista.addColumn("DESCRIÇÃO");
		dtmLista.addColumn("STATUS");

		//POPULANDO A TABELA
		produtoDao.todos().forEach(f-> dtmLista.addRow(new String[]{
				f.getCodigo().toString(), f.getTipoProduto().toString(), f.getValor().toString(), f.getDescricao().toString(), f.getStatus().toString()
		}));
		

		jtTabela = new JTable(dtmLista);
		jspRolagem = new JScrollPane(jtTabela);
		jtTabela.getColumnModel().getColumn(0).setMaxWidth(50);
		jtTabela.getColumnModel().getColumn(1).setMaxWidth(150);
		jtTabela.getColumnModel().getColumn(2).setMaxWidth(100);
		jtTabela.getColumnModel().getColumn(3).setMaxWidth(350);
		jtTabela.getColumnModel().getColumn(4).setMaxWidth(60);
		jspRolagem.setBounds(20, 321, 710, 165);
		jspRolagem.setBackground(Color.white);
		getContentPane().add(jspRolagem);
		
		jtaDescricao = new JTextArea(5,0);
		jtsRolagem = new JScrollPane(jtaDescricao);
		jtsRolagem.setBounds(20, 101, 590, 99);
		getContentPane().add(jtsRolagem);
				
		jbtCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/3.png"))); 
        jbtCancelar.setText("CANCELAR");
        getContentPane().add(jbtCancelar);
        jbtCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "FALTA CONCLUIR");
				
			}
		});
        jbtCancelar.setBounds(180, 230, 130, 30);

        jbtCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2.png"))); 
        jbtCadastrar.setText("CADASTRAR");
        jbtCadastrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//CADASTRANDO PRODUTO
				//TESTANDO SE OS CAMPOS ESTÃO VAZIOS
				if(jtfValor.getText().equals("") || jtaDescricao.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos");					
				}else{
					//CADASTRANDO PRODUTO
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
					produto.setStatus(StatusProduto.ATIVO);
					
					//CADASTRANDO O PRODUTO
					produtoDao.inserir(produto);
					JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso !!!");
					
					//LIMPAR CAMPOS
					jtfValor.setText("");
					jtaDescricao.setText("");
					jcbTipoProduto.setSelectedItem("");
					
					//LIMPADO A TABELA
					dtmLista.setNumRows(0);
					
					//ATUALIZANDO A TABELA
					produtoDao.todos().forEach(
							f -> dtmLista.addRow(new String[]{
									f.getCodigo().toString(),
									f.getTipoProduto().toString(),
									f.getValor().toString(),
									f.getDescricao().toString(),
									f.getStatus().toString()
									}));					
				}
				
			}
        });
        getContentPane().add(jbtCadastrar);
        jbtCadastrar.setBounds(20, 230, 130, 30);
        
        JLabel lblAlterarDados = new JLabel();
        lblAlterarDados.setText("ALTERAR DADOS:");
        lblAlterarDados.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
        lblAlterarDados.setBounds(20, 497, 117, 30);
        getContentPane().add(lblAlterarDados);
        
        jbtAlterarDados = new JButton();
        jbtAlterarDados.setIcon(new ImageIcon(MontarCardapio.class.getResource("/img/2.png")));
        jbtAlterarDados.setText("ALTERAR DADOS");
        jbtAlterarDados.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//AÇAO DO BOTAO ALTERAR
				if (jtTabela.getSelectedRowCount()==0) {
					JOptionPane.showMessageDialog(jtTabela, "Por favor, selecione o produto para alterar !!!");	
				}else{
					//PEGANDO A SELEÇÃO DA LINHA NA TABELA
					int linha = jtTabela.getSelectedRow();
					//PEGANDO O CODIGO DA LINHA SELECIONADA
					Integer codigo = Integer.valueOf((String) dtmLista.getValueAt(linha, 0));
					//ABalmoRE A TELA ALTERARITEMCARDAPIO
					AlterarItemCardapio Ati= new AlterarItemCardapio(produtoDao.buscar(codigo));
					Principal.jdpPrincipal.add(Ati);
					Ati.setVisible(true);
					dispose();
				}
				
			}
		});
        jbtAlterarDados.setBounds(136, 497, 160, 30);
        getContentPane().add(jbtAlterarDados);

        //FIM DA TELA
        setBounds(310, 70, 775, 575);
    }  

    private javax.swing.JScrollPane jspRolagem;
    private javax.swing.JButton jbtCadastrar;
    private javax.swing.JButton jbtAlterarDados;
    private javax.swing.JButton jbtCancelar;
    private JComboBox<String> jcbTipoProduto;
    private javax.swing.JLabel jlbCodigo;
    private javax.swing.JLabel jlbItens;
    private javax.swing.JScrollPane jtsRolagem;
    private javax.swing.JLabel jlbNome1;
    private javax.swing.JLabel jlbValor;
    private javax.swing.JTextArea jtaDescricao;
    private javax.swing.JTable jtTabela;
    private javax.swing.JTextField jtfValor;
}
