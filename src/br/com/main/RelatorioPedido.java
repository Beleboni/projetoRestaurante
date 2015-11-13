package br.com.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
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

		setClosable(true);
		setIconifiable(true);
		setTitle("TODOS OS DADOS DO PEDIDO");
		getContentPane().setLayout(null);

		// LABELS
		jlbNome.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
		jlbNome.setText("MESA QUE CONTEM PEDIDOS");
		getContentPane().add(jlbNome);
		jlbNome.setBounds(20, 10, 190, 30);

		// COMBOBOX
		jcbMesa = new JComboBox<String>();
		getContentPane().add(jcbMesa);
		// PEGANDO TODOS OS PEDIDOS QUE POSSUEM EM SEU STATUS TRUE
		// TODOS OS TRUES SÃO LISTADOS NO COMBOBOX
		pedidos = pedidoDAO.pedidoEmProcesso();
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
				num_mesa = new Integer(Integer.parseInt(jcbMesa
						.getSelectedItem().toString()));

				itens = itemDAO.itensPedidoPorMesa(num_mesa);

				for (ItemPedido item : itens) {
					// LISTA TODOS OS ITENS DOP PEDIDO SELECIONADO POR MESA
					dtmLista.addRow(new String[] {
							item.getCodigo().toString(),
							item.getProduto().getDescricao(),
							item.getStatus().toString() });
				}
			}
		});
		jcbMesa.setBounds(20, 51, 255, 30);

		// TABELA
		dtmLista = new DefaultTableModel();
		dtmLista.addColumn("COD.");
		dtmLista.addColumn("DESCRIÇÃO");
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
		jbtAplicarAcao.setText("APLICAR AÇÃO");
		jbtAplicarAcao.addActionListener(new ActionListener() {
			// AÇÕES DE MUDANÇAS NO STATUS DOS ITENS DO PEDIDO SELECIONADO POR
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
						itemPedido.setStatus(StatusItemPedido.CANCELADO);
						break;
					}
					// ALTERANDO O STATUS DO ITEM DO PEDIDO
					itemDAO.alterarStatus(itemPedido);

					JOptionPane
							.showMessageDialog(jtTabela,
									"Status do item de seu pedido foi alterado com sucesso !!!");

					// LIMPANDO A TABELA
					dtmLista.setNumRows(0);
					
					//TODO FALTA CONSTRUIR UM METODO QUE ATUALIZE EM TEMPO DE EXECUÇÃO
				}

			}
		});
		jbtAplicarAcao.setBounds(285, 351, 160, 30);
		getContentPane().add(jbtAplicarAcao);
		
		
		jbtMandarPedido.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/img/2.png"))); // NOI18N
		jbtMandarPedido.setText("MANDAR PEDIDO");
		jbtMandarPedido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//FOR TESTA SE TODOS OS ITENS DO PEDIDO FORMA PROCESSANDO				
				for (ItemPedido itemPedido : itens) {
						if(itemPedido.getStatus().equals(StatusItemPedido.ENTREGUE)|| itemPedido.getStatus().equals(StatusItemPedido.CANCELADO)){
							JOptionPane.showMessageDialog(jbtMandarPedido, "Não");
							return;
						}
				}
				// PEGANDO A MESA SELECIONADA
				// NESTE CASO A OCORRE UMA CONSULTA NO BANCO PARA VER A
				// ASSOCIAÇÃO DA MESA A UM PEDIDO
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

		setBounds(320, 150, 700, 435);
	}

	// INICIALIZAR VARIAVEIS
	private javax.swing.JScrollPane jspRolagem;
	private javax.swing.JButton jbtMandarPedido;
	private javax.swing.JButton jbtAplicarAcao;
	private javax.swing.JComboBox<String> jcbMesa;
	private javax.swing.JComboBox<String> jcbStatusItem;
	private javax.swing.JLabel jlbNome;
	private javax.swing.JTable jtTabela;
}
