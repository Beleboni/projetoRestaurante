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

public class RelatorioPedido extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmLista;
	private List<Pedido> pedidos;
	private PedidoDAO pedidoDAO = new PedidoJDBC();
	private ItemPedidoDAO itemDAO = new ItemPedidoJDBC();
	private Integer num_mesa;

	public RelatorioPedido() {
		initComponents();
	}

	private void initComponents() {

		jspRolagem = new javax.swing.JScrollPane();
		jtTabela = new javax.swing.JTable();
		jbtMandarPedido = new javax.swing.JButton();
		jcbMesa = new javax.swing.JComboBox<String>();
		jlbNome = new javax.swing.JLabel();

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
		// TODOS OS TRUES S�O LISTADOS NO COMBOBOX
		pedidos = pedidoDAO.pedidoEmProcesso();
		if (!pedidos.isEmpty()) {
				pedidos.forEach(p -> jcbMesa.addItem(p.getMesa().toString()));
		} else {
				jcbMesa.addItem("N�o existem Pedidos");
		}
		//A��ES DE ALIMENTA��O DOCOMBO BOX
		jcbMesa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// LISTAR OS ITENS DO PEDIDO REFERENTE A SUA MESA
				// LIMPANDO A LISTA DE UMA MESA PARA OUTRA
				dtmLista.setNumRows(0);
				num_mesa = new Integer(
						Integer.parseInt(jcbMesa.getSelectedItem().toString()));
				List<ItemPedido> itens = itemDAO.itensPedidoPorMesa(num_mesa);
				jbtMandarPedido.setEnabled(true);

				for (ItemPedido item : itens) {
					dtmLista.addRow(new String[] {
							item.getProduto().getCodigo().toString(),
							item.getProduto().getDescricao() });
				}
			}
		});
		jcbMesa.setBounds(20, 51, 255, 30);

		// TABELA
		dtmLista = new DefaultTableModel();
		dtmLista.addColumn("COD.");
		dtmLista.addColumn("DESCRI��O");

		jtTabela = new JTable(dtmLista);
		jtTabela.getColumnModel().getColumn(0).setMaxWidth(50);
		jtTabela.getColumnModel().getColumn(1).setMaxWidth(440);
		jspRolagem = new JScrollPane(jtTabela);
		jspRolagem.setBounds(20, 100, 490, 240);
		getContentPane().add(jspRolagem);

		// BOTOES
		jbtMandarPedido.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/img/2.png"))); // NOI18N
		jbtMandarPedido.setText("MANDAR PEDIDO");
		jbtMandarPedido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//PEGANDO A MESA SELECIONADA
				//NESTE CASO A OCORRE UMA CONSULTA NO BANCO PARA VER A ASSOCIA��O DA MESA A UM PEDIDO
				Pedido pedido = pedidoDAO.buscar(num_mesa);
				
				//ALTERANDO O PEDIDO
				pedido.setStatus(false);				
				pedidoDAO.alterar(pedido);
				
				//REMOVENDO A MESA SELECIONADA DO COMBOBOX
				jcbMesa.removeItemAt(jcbMesa.getSelectedIndex());
				
				JOptionPane.showMessageDialog(jbtMandarPedido,
						"Pedido processado com sucesso !!!");
			}
		});
		getContentPane().add(jbtMandarPedido);
		jbtMandarPedido.setBounds(350, 350, 160, 30);
		jbtMandarPedido.setEnabled(false);

		setBounds(430, 150, 539, 435);
	}

	// INICIALIZAR VARIAVEIS
	private javax.swing.JScrollPane jspRolagem;
	private javax.swing.JButton jbtMandarPedido;
	private javax.swing.JComboBox<String> jcbMesa;
	private javax.swing.JLabel jlbNome;
	private javax.swing.JTable jtTabela;
}
