package br.com.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.dao.ItemPedidoDAO;
import br.com.dao.PedidoDAO;
import br.com.jdbc.ItemPedidoJDBC;
import br.com.jdbc.PedidoJDBC;
import br.com.model.ItemPedido;
import br.com.model.Pedido;
import br.com.model.Produto;
import br.com.tipo.StatusItemPedido;
import br.com.tipo.StatusPedido;
import javax.swing.ImageIcon;

public class PedidoCliente extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmLista;
	private ArrayList<Produto> produtos;
	private PedidoDAO pedidoDAO = new PedidoJDBC();
	private ItemPedidoDAO itemPedidoDAO = new ItemPedidoJDBC();
	private Pedido pedido = new Pedido();

	public PedidoCliente(ArrayList<Produto> produtos) {
		setFrameIcon(new ImageIcon(PedidoCliente.class.getResource("/img/pequeno.png")));
		// TESTANDO SE A LISTA ESTÁ FAZIA
		if (produtos != null) {
			// SE ALISTA ESTIVER COM ALGUM ITEM ELE CARREGA JUNTO
			this.produtos = produtos;
		} else {
			// SE ESTIVER VAZIA ELE CLIA UMA LISTA VAZIA
			this.produtos = new ArrayList<Produto>();
		}
		initComponents();
	}

	private void initComponents() {

		jspRolagem = new javax.swing.JScrollPane();
		jtTabela = new javax.swing.JTable();
		jlbMeuPedido = new javax.swing.JLabel();
		jlbNumeroMesa = new javax.swing.JLabel();
		jlbTotalPedido = new javax.swing.JLabel();
		jtfTotal = new javax.swing.JTextField(this.getTotal(produtos)
				.toString());
		jtfMesa = new javax.swing.JTextField();
		jbtVisualizarCardapio = new javax.swing.JButton();
		jbtGerarPedido = new javax.swing.JButton();
		jtbRemoverItem = new javax.swing.JButton();

		setClosable(true);
		setIconifiable(true);
		setTitle("BURGUER SOFT -- MEU PEDIDO");
		getContentPane().setLayout(null);

		// LABELS
		jlbMeuPedido.setFont(new java.awt.Font("Trebuchet MS", 1, 12));
		jlbMeuPedido.setText("MEU PEDIDO");
		getContentPane().add(jlbMeuPedido);
		jlbMeuPedido.setBounds(10, 21, 70, 30);

		jlbNumeroMesa.setFont(new java.awt.Font("Trebuchet MS", 1, 12));
		jlbNumeroMesa.setText("NUMERO DA MESA:");
		getContentPane().add(jlbNumeroMesa);
		jlbNumeroMesa.setBounds(581, 21, 120, 30);

		jlbTotalPedido.setFont(new java.awt.Font("Trebuchet MS", 1, 12));
		jlbTotalPedido.setText("TOTAL DO PEDIDO:");
		getContentPane().add(jlbTotalPedido);
		jlbTotalPedido.setBounds(519, 308, 120, 30);

		// TEXTFIELDS
		getContentPane().add(jtfTotal);
		jtfTotal.setBounds(647, 308, 100, 30);
		jtfTotal.setEnabled(false);
		getContentPane().add(jtfMesa);
		jtfMesa.setBounds(697, 22, 50, 30);

		// TABELA
		dtmLista = new DefaultTableModel();
		dtmLista.addColumn("COD.");
		dtmLista.addColumn("PRODUTO");
		dtmLista.addColumn("VALOR");

		// MOSTRADO OS ITENS DA TABELA
		produtos.forEach(p -> dtmLista.addRow(new String[] {
				// POPULANDO A TABELA COM OS ITENS ESCOLHIDOS NA TELA CARDAPIO
				p.getCodigo().toString(), p.getDescricao().toString(),
				p.getValor().toString() }));

		jtTabela = new JTable(dtmLista);
		jtTabela.getColumnModel().getColumn(0).setMaxWidth(50);
		jtTabela.getColumnModel().getColumn(1).setMaxWidth(600);
		jtTabela.getColumnModel().getColumn(2).setMaxWidth(87);
		jspRolagem = new JScrollPane(jtTabela);
		jspRolagem.setBounds(10, 62, 737, 233);
		getContentPane().add(jspRolagem);

		// BOTOES
		jbtVisualizarCardapio.setText("VISUALIZAR CARDAPIO");
		jbtVisualizarCardapio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// RETORNANDO A TELA DE CARPIO PARA SELECIONAR MAIS ITENS
				dispose();
				Cardapio At = new Cardapio(produtos);
				Principal.jdpPrincipal.add(At);
				At.setVisible(true);
			}
		});
		getContentPane().add(jbtVisualizarCardapio);
		jbtVisualizarCardapio.setBounds(89, 22, 160, 30);

		jbtGerarPedido.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/img/2.png")));
		jbtGerarPedido.setText("GERAR PEDIDO");
		jbtGerarPedido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (produtos.isEmpty()) {
					JOptionPane
							.showMessageDialog(
									jbtGerarPedido,
									"Atenção, você não possui itens em seu pedido, escolha algum item para poder gerar seu pedido");
					return;
				}
				
				String mesa = jtfMesa.getText();
				if (mesa.isEmpty()) {
					JOptionPane.showMessageDialog(jbtGerarPedido,
							"Por Favor, Cadastre uma mesa para este Pedido");
					
					return;
				} else if (pedidoDAO.isOcupada(Integer.parseInt(mesa))) {
					JOptionPane.showMessageDialog(jbtGerarPedido,
							"A mesa já possui um pedido. Por favor, tente outra.");
					
					return;
				}

				// SALVAR UM PEDIDO
				pedido.setMesa(jtfMesa.getText());
				pedido.setDataPedido(LocalDate.now());
				pedido.setStatus(StatusPedido.PROCESSANDO);
				pedido.setTotal(0.0);
				pedidoDAO.inserir(pedido);
				pedido.setCodigo(pedidoDAO.ultimoPedidoId());

				// SALVAR OS ITENS DO PEDIDO A CIMA
				// A LISTA PRODUTOS CONTEM TODOS OS ITENS SELECIONADOS
				// PELO USUÁRIO
				// O FOR SERVE PARA PERCORER ESTA LISTA E ADICIONAR
				// TODOS
				for (Produto prod : produtos) {
					ItemPedido item = new ItemPedido();
					item.setProduto(prod);
					item.setPedido(pedido);
					item.setStatus(StatusItemPedido.PROCESSANDO);
					itemPedidoDAO.inserir(item);
				}

				JOptionPane.showMessageDialog(jbtGerarPedido,
						"Pedido Realizado com sucesso.");
				dispose();
			}
		});
		getContentPane().add(jbtGerarPedido);
		jbtGerarPedido.setBounds(190, 309, 160, 30);

		jtbRemoverItem.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/img/3.png")));
		jtbRemoverItem.setText("REMOVER ITEM");
		jtbRemoverItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TESTANDO A O USUARIO SELECIONOU UMA LINHA DA TABELA
				if (jtTabela.getSelectedRowCount() == 0) {
					JOptionPane
							.showMessageDialog(jtTabela,
									"Por favor, Selecione um item para remover do seu pedido !!!");
				} else {
					int linha = jtTabela.getSelectedRow();
					produtos.remove(linha);
					// LIMPANDO A TABELA
					dtmLista.setNumRows(0);
					// ATUALIZANDO A LISTA
					produtos.forEach(p -> dtmLista.addRow(new String[] {
							// POPULANDO A TABELA COM OS ITENS ESCOLHIDOS NA
							// TELA CARDAPIO
							p.getCodigo().toString(),
							p.getDescricao().toString(),
							p.getValor().toString() }));
				}

				jtfTotal.setText(getTotal(produtos).toString());
			}
		});
		jtbRemoverItem.setBounds(10, 309, 160, 30);
		getContentPane().add(jtbRemoverItem);

		// FIM DA TELA
		setBounds(310, 70, 773, 391);
	}

	// METODO TOTAL DO PEDIDO
	public Double getTotal(List<Produto> produtos) {
		Double total = 0.0;
		if (!produtos.isEmpty()) {
			for (Produto p : produtos) {
				total += p.getValor();
			}
			return total;
		}
		return total;
	}

	// DECLARANDO VARIAVEIS
	private javax.swing.JScrollPane jspRolagem;
	private javax.swing.JButton jbtGerarPedido;
	private javax.swing.JButton jtbRemoverItem;
	private javax.swing.JButton jbtVisualizarCardapio;
	private javax.swing.JLabel jlbMeuPedido;
	private javax.swing.JLabel jlbNumeroMesa;
	private javax.swing.JLabel jlbTotalPedido;
	private javax.swing.JTable jtTabela;
	private javax.swing.JTextField jtfMesa;
	private javax.swing.JTextField jtfTotal;
}
