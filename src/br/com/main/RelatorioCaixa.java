package br.com.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.conexao.Conexao;
import br.com.dao.PedidoDAO;
import br.com.jdbc.PedidoJDBC;
import br.com.model.Pedido;
import br.com.relatorio.RelatorioUtil;

public class RelatorioCaixa extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmLista;
	private PedidoDAO pedidoDao = new PedidoJDBC();

	public RelatorioCaixa() {
        initComponents();
    }

    private void initComponents() {

    	jspRolagem = new javax.swing.JScrollPane();
    	jtTabela = new javax.swing.JTable();
        jlbValorTotal = new javax.swing.JLabel();
        jtfValorTotal = new javax.swing.JTextField();
        jtfValorTotal.setEnabled(false);
        jbtFinalizar = new javax.swing.JButton();
        jbtImpimir = new javax.swing.JButton();

        setClosable(true);
        setForeground(java.awt.Color.white);
        setIconifiable(true);
        setTitle("Gerenciamento de Caixa");
        getContentPane().setLayout(null);
        
        //LABELS
        jlbValorTotal.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
        jlbValorTotal.setText("MOVIMENTO DO DIA: ");
        getContentPane().add(jlbValorTotal);
        jlbValorTotal.setBounds(420, 460, 120, 30);
        
        //TABELAS
        dtmLista = new DefaultTableModel();
		dtmLista.addColumn("COD DO PEDIDO");
		dtmLista.addColumn("NUMERO DA MESA");
		dtmLista.addColumn("VALOR TOTAL");
		//CALCULANDO O TOTAL DE MOVIMENTO DO DIA
		Double total = 0.0;
		
		//MOSTRANDO TODOS OS PEDIDOS DO DIA
		for (Pedido p : pedidoDao.todosPedidosConcluidos()) {
			dtmLista.addRow(new String[]{
					p.getCodigo().toString(),
					p.getMesa().toString(),
					p.getTotal().toString()
			});
			total += p.getTotal();
		}
				
		jtTabela = new JTable(dtmLista);
		jtTabela.getColumnModel().getColumn(0).setMaxWidth(120);
		jtTabela.getColumnModel().getColumn(1).setMaxWidth(500);
		jtTabela.getColumnModel().getColumn(2).setMaxWidth(100);
		jspRolagem = new JScrollPane(jtTabela);
		jspRolagem.setBounds(0, 10, 720, 430);
		getContentPane().add(jspRolagem);
        
        //TEXTFIELDS
        getContentPane().add(jtfValorTotal);
        jtfValorTotal.setText(total.toString());
        jtfValorTotal.setBounds(560, 460, 150, 30);

        //BOTOES
        jbtFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2.png"))); 
        jbtFinalizar.setText("ENCERRAR CAIXA");
        jbtFinalizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(jbtFinalizar, "Caixa encerrado com Sucesso !!!");
				dispose();
			}
		});
        getContentPane().add(jbtFinalizar);
        jbtFinalizar.setBounds(550, 510, 160, 30);

        jbtImpimir.setText("IMPRIMIR RELATORIO");
        jbtImpimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
				JOptionPane.showMessageDialog(jbtFinalizar, "O relatório foi gerado com sucesso !!!");
			}
		});
        getContentPane().add(jbtImpimir);
        jbtImpimir.setBounds(10, 510, 170, 30);

        setBounds(310, 40, 739, 582);
    }
    
    //TODO METODO QUE CALCULA O TODA DO MOVIMENTO DO DIA
    
    

    // INICIANDO AS VARIAVEIS
    private javax.swing.JScrollPane jspRolagem;
    private javax.swing.JButton jbtFinalizar;
    private javax.swing.JButton jbtImpimir;
    private javax.swing.JLabel jlbValorTotal;
    private javax.swing.JTable jtTabela;
    private javax.swing.JTextField jtfValorTotal;
}
