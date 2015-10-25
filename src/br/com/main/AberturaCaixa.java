package br.com.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AberturaCaixa extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmLista;
	public AberturaCaixa() {
        initComponents();
    }

    private void initComponents() {

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

        setClosable(true);
        setIconifiable(true);
        setTitle("Abrir Caixa");
        getContentPane().setLayout(null);    
        
        //LABELS
        jlbTroco.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
        jlbTroco.setText("TROCO");
        getContentPane().add(jlbTroco);
        jlbTroco.setBounds(20, 293, 90, 30);
        
        jlbCaixaAberto.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
        jlbCaixaAberto.setText("CAIXA ABERTO");
        getContentPane().add(jlbCaixaAberto);
        jlbCaixaAberto.setBounds(20, 10, 90, 30);

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
        
        //SEPARADOR
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(0, 52, 730, 10);
        
        //COMBOBOX
        jcbMesa = new JComboBox<String>();
		getContentPane().add(jcbMesa);
		jcbMesa.setBounds(20, 86, 130, 30);
		jcbMesa.addItem("01");
		jcbMesa.addItem("02");
	
		//CRIANDO A TABELA
		dtmLista = new DefaultTableModel();
		dtmLista.addColumn("DESCRIÇÃO");
		dtmLista.addColumn("VALOR");

		// POPULANDO A TABELA

		jtTabela = new JTable(dtmLista);
		jtTabela.getColumnModel().getColumn(0).setMaxWidth(250);
		jtTabela.getColumnModel().getColumn(1).setMaxWidth(100);
		jspRolagem = new JScrollPane(jtTabela);
		jspRolagem.setBounds(170, 86, 350, 287);
		jspRolagem.setBackground(Color.white);
		getContentPane().add(jspRolagem);
        
        //TEXTFIELD
        getContentPane().add(jtfTotal);
        jtfTotal.setBounds(20, 163, 110, 30);
        getContentPane().add(jtfEntradaCliente);
        jtfEntradaCliente.setBounds(20, 243, 110, 30);
        getContentPane().add(jtfTrocoCliente);
        jtfTrocoCliente.setBounds(20, 323, 110, 30);
        getContentPane().add(jtfTroco);
        jtfTroco.setBounds(430, 10, 80, 30);
        getContentPane().add(jtfTotalCaixa);
        jtfTotalCaixa.setBounds(110, 10, 70, 30);
        
        //BOTOES
        jbtAbrirCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2.png"))); 
        jbtAbrirCaixa.setText("ABRIR CAIXA");
        jbtAbrirCaixa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(jbtAbrirCaixa, "Em construção");				
			}
		});
        getContentPane().add(jbtAbrirCaixa);
        jbtAbrirCaixa.setBounds(548, 11, 160, 30);

        jbtFinalizarVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2.png"))); 
        jbtFinalizarVenda.setText("FINALIZAR VENDA");
        jbtFinalizarVenda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(jbtFinalizarVenda, "Em construção");
			}
		});
        getContentPane().add(jbtFinalizarVenda);
        jbtFinalizarVenda.setBounds(548, 163, 160, 30);

        jbtEncerrarCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2.png"))); 
        jbtEncerrarCaixa.setText("ENCERRAR CAIXA");
        jbtEncerrarCaixa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(jbtEncerrarCaixa, "Em construção");		
			}
		});
        getContentPane().add(jbtEncerrarCaixa);
        jbtEncerrarCaixa.setBounds(548, 214, 160, 30);

        setBounds(310, 70, 744, 439);
    }

    // INICIALIZAÇÃO DAS VARIAVEIS
    private javax.swing.JScrollPane jspRolagem;
    private javax.swing.JTable jtTabela;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtAbrirCaixa;
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
