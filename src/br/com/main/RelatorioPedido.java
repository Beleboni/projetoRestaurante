package br.com.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RelatorioPedido extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmLista;

	public RelatorioPedido() {
        initComponents();
    }

	private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jspRolagem = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jbtMandarPedido = new javax.swing.JButton();
        jcbMesa = new javax.swing.JComboBox<String>();
        jlbNome = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setClosable(true);
        setIconifiable(true);
        setTitle("TODOS OS DADOS DO PEDIDO");
        getContentPane().setLayout(null);
        
        //LABELS
        jlbNome.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jlbNome.setText("MESA QUE CONTEM PEDIDOS");
        getContentPane().add(jlbNome);
        jlbNome.setBounds(20, 10, 190, 30);
        
        //COMBOBOX
        jcbMesa = new JComboBox<String>();
		getContentPane().add(jcbMesa);
		jcbMesa.setBounds(20, 51, 130, 30);
		jcbMesa.addItem("01");
		jcbMesa.addItem("02");
        
        //TABELA
        dtmLista = new DefaultTableModel();
		dtmLista.addColumn("COD.");
		dtmLista.addColumn("DESCRIÇÃO");
		
		jtTabela = new JTable(dtmLista);
		jtTabela.getColumnModel().getColumn(0).setMaxWidth(50);
		jtTabela.getColumnModel().getColumn(1).setMaxWidth(440);
		jspRolagem = new JScrollPane(jtTabela);
		jspRolagem.setBounds(20, 100, 490, 240);
		getContentPane().add(jspRolagem);
        
        //BOTOES
        jbtMandarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2.png"))); // NOI18N
        jbtMandarPedido.setText("MANDAR PEDIDO");
        jbtMandarPedido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(jbtMandarPedido, "Em desenvolvimento");
			}
		});
        getContentPane().add(jbtMandarPedido);
        jbtMandarPedido.setBounds(350, 350, 160, 30);

        setBounds(430, 150, 539, 435);
    }

    // INICIALIZAR VARIAVEIS
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jspRolagem;
    private javax.swing.JButton jbtMandarPedido;
    private javax.swing.JComboBox<String> jcbMesa;
    private javax.swing.JLabel jlbNome;
    private javax.swing.JTable jtTabela;
}
