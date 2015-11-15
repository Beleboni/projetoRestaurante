package br.com.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.conexao.Conexao;
import br.com.dao.ItemPedidoDAO;
import br.com.dao.PedidoDAO;
import br.com.jdbc.ItemPedidoJDBC;
import br.com.jdbc.PedidoJDBC;
import br.com.model.ItemPedido;
import br.com.model.Pedido;
import br.com.model.Produto;
import br.com.relatorio.RelatorioUtil;
import br.com.tipo.StatusPedido;

public class AberturaCaixa extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmLista;
	private PedidoDAO pedidoDAO = new PedidoJDBC();
	private ItemPedidoDAO itemDAO = new ItemPedidoJDBC();
	private List<Pedido> pedidos;
	private ArrayList<Produto> produtos;
	private Pedido pedido;
	private Double troco = 0.0;
	private Integer num_mesa;

	public AberturaCaixa() {
		initComponents();
	}

	private void initComponents() {
		// INICIANDO A LISTA DE PRODUTOS RESPOSAVEL POR CALCULAR O TOTAL DO
		// PEDIDO DO CLIENTE
		produtos = new ArrayList<>();

		jtfTroco = new javax.swing.JTextField();
		jtfTotalCaixa = new javax.swing.JTextField();
		jlbTroco = new javax.swing.JLabel();
		jlbEntradaTroco = new javax.swing.JLabel();
		jSeparator1 = new javax.swing.JSeparator();
		jcbMesa = new javax.swing.JComboBox<String>();
		jtTabela = new javax.swing.JTable();
		jspRolagem = new javax.swing.JScrollPane();
		jtfTotal = new javax.swing.JTextField();
		jtfEntradaCliente = new javax.swing.JTextField();
		jtfTrocoCliente = new javax.swing.JTextField();
		jlbCaixaAberto = new javax.swing.JLabel();
		jlbEntrada = new javax.swing.JLabel();
		jlbTotal = new javax.swing.JLabel();
		jbtAbrirCaixa = new javax.swing.JButton();
		jbtFinalizarVenda = new javax.swing.JButton();
		jbtEncerrarCaixa = new javax.swing.JButton();
		jbtCalcularTroco = new javax.swing.JButton();
		
		

		setClosable(true);
		setIconifiable(true);
		setTitle("Abrir Caixa");
		getContentPane().setLayout(null);

		// LABELS
		jlbTroco.setFont(new java.awt.Font("Trebuchet MS", 1, 12));
		jlbTroco.setText("TROCO");
		getContentPane().add(jlbTroco);
		jlbTroco.setBounds(20, 335, 90, 30);

		jlbCaixaAberto.setFont(new java.awt.Font("Trebuchet MS", 1, 12));
		jlbCaixaAberto.setText("DINHEIRO EM CAIXA");
		getContentPane().add(jlbCaixaAberto);
		jlbCaixaAberto.setBounds(20, 10, 130, 30);

		jlbEntrada.setFont(new java.awt.Font("Trebuchet MS", 1, 12));
		jlbEntrada.setText("ENTRADA");
		getContentPane().add(jlbEntrada);
		jlbEntrada.setBounds(20, 213, 90, 30);

		jlbTotal.setFont(new java.awt.Font("Trebuchet MS", 1, 12));
		jlbTotal.setText("TOTAL");
		getContentPane().add(jlbTotal);
		jlbTotal.setBounds(20, 127, 90, 29);

		jlbEntradaTroco.setFont(new java.awt.Font("Trebuchet MS", 1, 12));
		jlbEntradaTroco.setText("ENTRADA DE TROCO");
		getContentPane().add(jlbEntradaTroco);
		jlbEntradaTroco.setBounds(310, 10, 120, 30);

		// SEPARADOR
		getContentPane().add(jSeparator1);
		jSeparator1.setBounds(0, 52, 730, 10);

		// COMBOBOX
		jcbMesa = new JComboBox<String>();
		getContentPane().add(jcbMesa);
		// LISTANDO AS MESA QUE EXISTEM PEDIDOS
		pedidos = pedidoDAO.pedidoPronto();
		if (!pedidos.isEmpty()) {
			pedidos.forEach(p -> jcbMesa.addItem(p.getMesa().toString()));
		} else {
			jcbMesa.addItem("Não existem Pedidos");
		}
		// AÇÕES DE ALIMENTAÇÃO DOCOMBO BOX
		jcbMesa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// LISTAR OS ITENS DO PEDIDO REFERENTE A SUA MESA
				// LIMPANDO A LISTA DE UMA MESA PARA OUTRA
				dtmLista.setNumRows(0);
				// PEGANDO O ITEM SELECIONADO
				num_mesa = new Integer(Integer.parseInt(jcbMesa
						.getSelectedItem().toString()));
				List<ItemPedido> itens = itemDAO.itensPedidoPorMesa(num_mesa);
				// ATIVANDO BOTÃO
				jbtFinalizarVenda.setEnabled(true);
				// LIMPAR A ARRAYLIST DE PRODUTOS
				produtos.clear();
				//LIMPAR CAMPOS REFERENTES AO TROCO
				jtfEntradaCliente.setText("");
				jtfTrocoCliente.setText("");
				
				for (ItemPedido item : itens) {
					// CRIANDO UMA VARIAVEL PARA PEGAR TODOS OS ITENS DO PEDIDO
					// SELECIONADO PELA MESA ACIMA
					Produto produto = item.getProduto();
					// ADCIONANDO O PRODUTO NA TABELA
					dtmLista.addRow(new String[] { produto.getDescricao(),
							produto.getValor().toString() });
					produtos.add(produto);
				}
				// SETANDO O VALOR TOTAL DO PEDIDO NO CAMPO
				jtfTotal.setText(getTotalPedido(produtos).toString());
				//HABILITANDO A ENTRADA DO TROCO
				jtfEntradaCliente.setEnabled(true);

			}
		});
		jcbMesa.setEnabled(false);
		jcbMesa.setBounds(20, 86, 130, 30);

		// CRIANDO A TABELA
		dtmLista = new DefaultTableModel();
		dtmLista.addColumn("DESCRIÇÃO");
		dtmLista.addColumn("VALOR");

		// POPULANDO A TABELA

		jtTabela = new JTable(dtmLista);
		jtTabela.getColumnModel().getColumn(0).setMaxWidth(250);
		jtTabela.getColumnModel().getColumn(1).setMaxWidth(100);
		jspRolagem = new JScrollPane(jtTabela);
		jspRolagem.setBounds(170, 86, 350, 310);
		jspRolagem.setBackground(Color.white);
		getContentPane().add(jspRolagem);

		// TEXTFIELD
		getContentPane().add(jtfTotalCaixa);
		jtfTotalCaixa.setEnabled(false);
		jtfTotalCaixa.setBounds(146, 11, 120, 30);
		
		getContentPane().add(jtfTroco);
		jtfTroco.setBounds(430, 10, 80, 30);
		
		getContentPane().add(jtfTotal);
		jtfTotal.setEnabled(false);
		jtfTotal.setBounds(20, 163, 110, 30);
		
		getContentPane().add(jtfEntradaCliente);
		jtfEntradaCliente.setEnabled(false);
		jtfEntradaCliente.setBounds(20, 243, 110, 30);
		
		getContentPane().add(jtfTrocoCliente);
		jtfTrocoCliente.setEnabled(false);
		jtfTrocoCliente.setBounds(20, 367, 110, 30);
		
		// BOTOES
		jbtAbrirCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/2.png")));
		jbtAbrirCaixa.setText("ABRIR CAIXA");
		jbtAbrirCaixa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jtfTroco.getText().equals("")) {
					JOptionPane
							.showMessageDialog(jbtAbrirCaixa,
									"Por favor, Para você utilizar o caixa precisa por o troco !!!");
				} else {
					// VARIAVEL LOCAL POR ARMAZENAR O TROCO
					troco += Double.parseDouble(jtfTroco.getText());
					// VARIAVEL LOCAL POR ARMAZENAR O TOTALCAIXA
					jtfTotalCaixa.setText(Double.valueOf(troco).toString());
					// BLOQUEANDO E LIMPANDO O TEXTFIELD DO TROCO E OUTRAS
					// FUNÇÕES DO SISTEMA
					jtfTroco.setEnabled(false);
					jcbMesa.setEnabled(true);
					jbtEncerrarCaixa.setEnabled(true);
					jbtAbrirCaixa.setEnabled(false);
					JOptionPane.showMessageDialog(jbtAbrirCaixa,
							"Status: Caixa Aberto");
				}
			}
		});
		getContentPane().add(jbtAbrirCaixa);
		jbtAbrirCaixa.setBounds(548, 11, 160, 30);
		
		jbtCalcularTroco.setText("CALCULAR");
		jbtCalcularTroco.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//ACÇOES DE ATUALIZAR CAIXA
				troco += Double.parseDouble(jtfTotal.getText().toString());
				jtfTotalCaixa.setText("");
				jtfTotalCaixa.setText(Double.valueOf(troco).toString());
				
				//AÇÕES DE EFETUAR TROCO
				Double entradaCliente = Double.parseDouble(jtfEntradaCliente.getText().toString());
				entradaCliente -= Double.parseDouble(jtfTotal.getText().toString());
				jtfTrocoCliente.setText(Double.valueOf(entradaCliente).toString());
			}
		});
		jbtCalcularTroco.setBounds(20, 294, 110, 30);
		getContentPane().add(jbtCalcularTroco);
		
		jbtFinalizarVenda.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/img/2.png")));
		jbtFinalizarVenda.setText("FINALIZAR VENDA");
		jbtFinalizarVenda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//CONSULTA SE EXISTE UMA ASSOCIAÇÃO DA MESA A UM PEDIDO
				pedido = pedidoDAO.buscar(num_mesa);
				//ALTERANDO OS DADOS DO PEDIDO
				pedido.setStatus(StatusPedido.CONCLUIDO);
				pedido.setTotal(Double.parseDouble(jtfTotal.getText().toString()));
				pedidoDAO.alterar(pedido);
				//REMOVENDO A MESA SELECIONANDA DO COMBOBOX
				jcbMesa.removeItemAt(jcbMesa.getSelectedIndex());
				
				JOptionPane.showMessageDialog(jbtFinalizarVenda, "Pedido pago com sucesso !!!");
			}
		});
		getContentPane().add(jbtFinalizarVenda);
		jbtFinalizarVenda.setBounds(548, 163, 160, 30);
		jbtFinalizarVenda.setEnabled(false);

		
		jbtEncerrarCaixa.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/img/2.png")));
		jbtEncerrarCaixa.setText("ENCERRAR CAIXA");
		jbtEncerrarCaixa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//MONTANDO O RELATORIO GERAL EM PDF
				Map<String, Object> parametros = new HashMap<String, Object>();
				//PASSANDO O ENDEREÇO DO JASPER
				new RelatorioUtil().gerarPdf("src/br/com/relatorio/Relatorio_Pedidos.jasper", Conexao.getCon(), parametros);
				try {
					//ABRINDO O RELATORIO
					java.awt.Desktop.getDesktop().open( new File( "relatorio.pdf" ) );
				} catch (IOException e1) {
					// CASSO DE ALGUM ERRO
					e1.printStackTrace();
				}
				JOptionPane
						.showMessageDialog(jbtEncerrarCaixa, "Em construção");
			}
		});
		getContentPane().add(jbtEncerrarCaixa);
		jbtEncerrarCaixa.setEnabled(false);
		jbtEncerrarCaixa.setBounds(548, 214, 160, 30);
		
		setBounds(310, 70, 744, 454);
	}

	// METODO QUE CALCULA O VALOR DO PEDIDO DO CLIENTE
	public Double getTotalPedido(List<Produto> produtos) {
		Double total = 0.0;
		if (!produtos.isEmpty()) {
			for (Produto p : produtos) {
				total += p.getValor();
			}
			return total;
		}
		return total;
	}

	// INICIALIZAÇÃO DAS VARIAVEIS
	private javax.swing.JScrollPane jspRolagem;
	private javax.swing.JTable jtTabela;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JButton jbtAbrirCaixa;
	private javax.swing.JButton jbtCalcularTroco;
	private javax.swing.JButton jbtEncerrarCaixa;
	private javax.swing.JButton jbtFinalizarVenda;
	private javax.swing.JComboBox<String> jcbMesa;
	private javax.swing.JLabel jlbTroco;
	private javax.swing.JLabel jlbEntradaTroco;
	private javax.swing.JLabel jlbCaixaAberto;
	private javax.swing.JLabel jlbEntrada;
	private javax.swing.JLabel jlbTotal;
	private javax.swing.JTextField jtfEntradaCliente;
	private javax.swing.JTextField jtfTotal;
	private javax.swing.JTextField jtfTotalCaixa;
	private javax.swing.JTextField jtfTroco;
	private javax.swing.JTextField jtfTrocoCliente;
}
