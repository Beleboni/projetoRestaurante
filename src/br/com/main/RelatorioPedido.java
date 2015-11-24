package br.com.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import br.com.conexao.Conexao;
import br.com.dao.ItemPedidoDAO;
import br.com.dao.PedidoDAO;
import br.com.jdbc.ItemPedidoJDBC;
import br.com.jdbc.PedidoJDBC;
import br.com.model.ItemPedido;
import br.com.model.Pedido;
import br.com.relatorio.RelatorioUtil;
import br.com.tipo.StatusItemPedido;
import br.com.tipo.StatusPedido;

public class RelatorioPedido extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmLista;
	private List<Pedido> pedidos;
	private PedidoDAO pedidoDAO = new PedidoJDBC();
	private ItemPedidoDAO itemDAO = new ItemPedidoJDBC();
	private Integer num_mesa;
	private List<ItemPedido> itens;

	public RelatorioPedido() {
		setFrameIcon(new ImageIcon(
				RelatorioPedido.class.getResource("/img/pequeno.png")));
		initComponents();
	}

	private void initComponents() {

		jspRolagem = new javax.swing.JScrollPane();
		jtTabela = new javax.swing.JTable();
		jbtMandarPedido = new javax.swing.JButton();
		jcbMesa = new javax.swing.JComboBox<String>();
		jlbNome = new javax.swing.JLabel();
		jbtAplicarAcao = new javax.swing.JButton();
		jcbStatusItem = new javax.swing.JComboBox<String>();
		jbtImprimir = new javax.swing.JButton();

		setClosable(true);
		setIconifiable(true);
		setTitle("BURGUER SOFT -- OS DADOS DO PEDIDO");
		getContentPane().setLayout(null);

		// LABELS
		jlbNome.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
		jlbNome.setText("MESA QUE CONTEM PEDIDOS");
		getContentPane().add(jlbNome);
		jlbNome.setBounds(20, 10, 190, 30);

		// COMBOBOX
		jcbMesa = new JComboBox<String>();
		getContentPane().add(jcbMesa);
		// PEGANDO TODOS OS PEDIDOS QUE POSSUEM EM SEU STATUS TRUE
		// TODOS OS TRUES S�O LISTADOS NO COMBOBOX
		pedidos = pedidoDAO.pedidoEmProcesso();
		if (!pedidos.isEmpty()) {
			pedidos.forEach(p -> jcbMesa.addItem(p.getMesa().toString()));
		} else {
			jcbMesa.addItem("N�o existem Pedidos");
		}
		// A��ES DE ALIMENTA��O DOCOMBO BOX
		jcbMesa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				acaoComboBox();
			}

		});

		jcbMesa.setBounds(20, 51, 255, 30);
		//PEGA A��O NO INICIO DA CRIA��O DO COMPONENTE
		jcbMesa.addAncestorListener(new AncestorListener() {

			@Override
			public void ancestorRemoved(AncestorEvent event) {
			}

			@Override
			public void ancestorMoved(AncestorEvent event) {
			}

			@Override
			public void ancestorAdded(AncestorEvent event) {
				acaoComboBox();
			}
		});

		// TABELA
		dtmLista = new DefaultTableModel();
		dtmLista.addColumn("COD.");
		dtmLista.addColumn("DESCRI��O");
		dtmLista.addColumn("STATUS");

		jtTabela = new JTable(dtmLista);
		jtTabela.getColumnModel().getColumn(0).setMaxWidth(50);
		jtTabela.getColumnModel().getColumn(1).setMaxWidth(450);
		jtTabela.getColumnModel().getColumn(2).setMaxWidth(140);
		jspRolagem = new JScrollPane(jtTabela);
		jspRolagem.setBounds(20, 100, 640, 240);
		getContentPane().add(jspRolagem);

		jcbStatusItem = new JComboBox<>();
		jcbStatusItem.setBounds(20, 351, 255, 30);
		jcbStatusItem.addItem("ENTREGUE");
		jcbStatusItem.addItem("CANCELADO");
		getContentPane().add(jcbStatusItem);

		// BOTOES
		jbtAplicarAcao.setText("APLICAR A��O");
		jbtAplicarAcao.addActionListener(new ActionListener() {
			// A��ES DE MUDAN�AS NO STATUS DOS ITENS DO PEDIDO SELECIONADO POR
			// MESA
			@Override
			public void actionPerformed(ActionEvent e) {
				// ATUALIZAR O STATUS DO ITEN DO PEDIDO
				if (jtTabela.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(jtTabela,
							"Por Favor, selecione um Item para alterar !!!");
				} else {
					// PEGANDO A LINHA SELECIONADA
					int linha = jtTabela.getSelectedRow();
					// PEGANDO O CODIGO DO ITEM DO PEDIDO
					Integer codigo = Integer.valueOf((String) dtmLista
							.getValueAt(linha, 0));
					// BUSCANDO O ITEM DO PEDIDO NO BANCO
					ItemPedido itemPedido = itemDAO.buscar(codigo);

					switch (jcbStatusItem.getSelectedIndex()) {
					case 0:
						itemPedido.setStatus(StatusItemPedido.ENTREGUE);
						break;
					case 1:
						itemPedido.setStatus(StatusItemPedido.ENTREGUE);
						break;
					}
					// ALTERANDO O STATUS DO ITEM DO PEDIDO
					itemDAO.alterarStatus(itemPedido);

					JOptionPane
							.showMessageDialog(jtTabela,
									"Status do item de seu pedido foi alterado com sucesso !!!");

					// LIMPANDO A TABELA
					dtmLista.setNumRows(0);
				}
			}
		});
		jbtAplicarAcao.setBounds(285, 351, 160, 30);
		getContentPane().add(jbtAplicarAcao);

		jbtMandarPedido.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/img/2.png"))); 
		jbtMandarPedido.setText("MANDAR PEDIDO");
		jbtMandarPedido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				// �Pe.getMessage();
				// FOR TESTA SE TODOS OS ITENS DO PEDIDO FORMA PROCESSANDO
				for (ItemPedido itemPedido : itens) {
					if (StatusItemPedido.PROCESSANDO.equals(itemPedido
							.getStatus())) {
						JOptionPane
								.showMessageDialog(
										null,
										"Este pedido n�o est� totamente processado, por favor entregue todos os itens !!! ");
						return;
					}
				}
				// PEGANDO A MESA SELECIONADA
				// NESTE CASO A OCORRE UMA CONSULTA NO BANCO PARA VER A
				// ASSOCIA��O DA MESA A UM PEDIDO
				Pedido pedido = pedidoDAO.buscar(num_mesa);

				// ALTERANDO O PEDIDO
				pedido.setStatus(StatusPedido.CAIXA);
				pedidoDAO.alterar(pedido);

				// REMOVENDO A MESA SELECIONADA DO COMBOBOX
				jcbMesa.removeItemAt(jcbMesa.getSelectedIndex());

				JOptionPane.showMessageDialog(jbtMandarPedido,
						"Pedido processado com sucesso !!!");
			}
		});
		getContentPane().add(jbtMandarPedido);
		jbtMandarPedido.setBounds(501, 351, 160, 30);

		jbtImprimir.setText("IMPRIMIR PEDIDO");
		jbtImprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// IMPRIMIR RELATORIO DO PEDIDO
				Map<String, Object> parametros = new HashMap<String, Object>();
				// PASSANDO A MESSA SELECIONADA PARA BUSCAR O PEDIDO
				parametros.put("mesa",
						Integer.valueOf(jcbMesa.getSelectedItem().toString()));
				// GERANDO O PDF
				new RelatorioUtil().gerarPdf(
						"src/br/com/relatorio/Pedido_Cliente.jasper",
						Conexao.getCon(), parametros);
				try {
					// ABRINDO O PDF
					java.awt.Desktop.getDesktop().open(
							new File("relatorio.pdf"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(jbtImprimir,
						"O pedido foi gerado com sucesso !!!");
			}
		});
		getContentPane().add(jbtImprimir);
		jbtImprimir.setBounds(500, 51, 160, 30);

		setBounds(320, 150, 700, 435);
	}

	public void acaoComboBox() {
		// LISTAR OS ITENS DO PEDIDO REFERENTE A SUA MESA
		// LIMPANDO A LISTA DE UMA MESA PARA OUTRA
		dtmLista.setNumRows(0);
		num_mesa = new Integer(Integer.parseInt(jcbMesa.getSelectedItem()
				.toString()));

		itens = itemDAO.itensPedidoPorMesa(num_mesa);

		for (ItemPedido item : itens) {
			// LISTA TODOS OS ITENS DOP PEDIDO SELECIONADO POR MESA
			dtmLista.addRow(new String[] { item.getCodigo().toString(),
					item.getProduto().getDescricao(),
					item.getStatus().toString() });
		}
	}

	// INICIALIZAR VARIAVEIS
	private javax.swing.JScrollPane jspRolagem;
	private javax.swing.JButton jbtMandarPedido;
	private javax.swing.JButton jbtAplicarAcao;
	private javax.swing.JButton jbtImprimir;
	private javax.swing.JComboBox<String> jcbMesa;
	private javax.swing.JComboBox<String> jcbStatusItem;
	private javax.swing.JLabel jlbNome;
	private javax.swing.JTable jtTabela;
}
