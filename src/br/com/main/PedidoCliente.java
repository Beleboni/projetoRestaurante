package br.com.main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

public class PedidoCliente extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmLista;
	private ArrayList<Produto> produtos;
	private PedidoDAO pedidoDAO = new PedidoJDBC();
	private ItemPedidoDAO itemPedidoDAO = new ItemPedidoJDBC();
	private Pedido pedido = new Pedido();
	private ItemPedido item = new ItemPedido();
	
	public PedidoCliente(ArrayList<Produto> produtos) {
		//TESTANDO SE A LISTA ESTÁ FAZIA
		if(produtos != null){
			//SE ALISTA ESTIVER COM ALGUM ITEM ELE CARREGA JUNTO
			this.produtos = produtos;
		}else{
			//SE ESTIVER VAZIA ELE CLIA UMA LISTA VAZIA
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
        jtfTotal = new javax.swing.JTextField();
        jtfMesa = new javax.swing.JTextField();      
        jbtVisualizarCardapio = new javax.swing.JButton();
        jbtGerarPedido = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Menu pedido");
        getContentPane().setLayout(null);

        //LABELS
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
        
        //TEXTFIELDS
        jlbTotalPedido.setBounds(519, 308, 120, 30);
        getContentPane().add(jtfTotal);
        jtfTotal.setBounds(647, 308, 100, 30);
        getContentPane().add(jtfMesa);
        jtfMesa.setBounds(697, 22, 50, 30);
        
        //TABELA
        dtmLista = new DefaultTableModel();
		dtmLista.addColumn("COD.");
		dtmLista.addColumn("PRODUTO");
		dtmLista.addColumn("VALOR");

		//MOSTRADO OS ITENS DA TABELA
		produtos.forEach(p -> dtmLista.addRow(new String[]{
				//POPULANDO A TABELA COM OS ITENS ESCOLHIDOS NA TELA CARDAPIO
				p.getCodigo().toString(),p.getDescricao().toString(), p.getValor().toString()
		}));
		
		jtTabela = new JTable(dtmLista);
		jtTabela.getColumnModel().getColumn(0).setMaxWidth(50);
		jtTabela.getColumnModel().getColumn(1).setMaxWidth(600);
		jtTabela.getColumnModel().getColumn(2).setMaxWidth(87);
		jspRolagem = new JScrollPane(jtTabela);
		jspRolagem.setBounds(10, 62, 737, 233);
		getContentPane().add(jspRolagem);
        
        //BOTOES
        jbtVisualizarCardapio.setText("VISUALIZAR CARDAPIO");
        jbtVisualizarCardapio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//RETORNANDO A TELA DE CARPIO PARA SELECIONAR MAIS ITENS
				dispose();
				Cardapio At = new Cardapio(produtos);
				Principal.jdpPrincipal.add(At);
				At.setVisible(true);
			}
		});
        getContentPane().add(jbtVisualizarCardapio);
        jbtVisualizarCardapio.setBounds(10, 308, 160, 30);

        jbtGerarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2.png"))); 
        jbtGerarPedido.setText("GERAR PEDIDO");
        jbtGerarPedido.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jtfMesa.getText().equals("")){
					JOptionPane.showMessageDialog(jbtGerarPedido, "Por Favor, Cadastre uma mesa para este Pedido");
				}else{
//					public static void main(String[] args) {
//						Pedido p = new Pedido();
//						
//						//p.salvar(p);
//						
//						ItemPedido i = new ItemPedido();
//						i.setPedido(p);
//						i.setProduto(new Produto());
//						
//						p.getItens().add(i);
//						
//						for (ItemPedido it : p.getItens()){ 
//							//itemdao.salvar(it);
//						}
					
					pedido.setMesa(jtfMesa.getText());
					pedido.setStatus(true);
					item.setPedido(pedido);
					//item.setProduto(produtos);
					
					
					pedidoDAO.inserir(pedido);
					itemPedidoDAO.inserir(item);
				}
				
				JOptionPane.showMessageDialog(jbtGerarPedido, "AINDA EM DESENVOLVIMENTO");
				
			}
		});
        getContentPane().add(jbtGerarPedido);
        jbtGerarPedido.setBounds(190, 308, 160, 30);

        //FIM DA TELA
        setBounds(310, 70, 773, 391);
    }

    //DECLARANDO VARIAVEIS
    private javax.swing.JScrollPane jspRolagem;
    private javax.swing.JButton jbtGerarPedido;
    private javax.swing.JButton jbtVisualizarCardapio;
    private javax.swing.JLabel jlbMeuPedido;
    private javax.swing.JLabel jlbNumeroMesa;
    private javax.swing.JLabel jlbTotalPedido;
    private javax.swing.JTable jtTabela;
    private javax.swing.JTextField jtfMesa;
    private javax.swing.JTextField jtfTotal;
}
