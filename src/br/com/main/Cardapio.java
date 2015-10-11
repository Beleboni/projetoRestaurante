package br.com.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.dao.ProdutoDAO;
import br.com.jdbc.ProdutoJDBC;
import br.com.model.Produto;
import br.com.tipo.TipoProduto;

public class Cardapio extends javax.swing.JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private ProdutoDAO produtoDao = new ProdutoJDBC();
	private DefaultTableModel dtmPorcao;
	private DefaultTableModel dtmRodizioPizza;
	private DefaultTableModel dtmSobremesa;
	private DefaultTableModel dtmSorvete;
	private DefaultTableModel dtmLanches;
	private DefaultTableModel dtmAlmocoBifeLivre;
	private DefaultTableModel dtmBifeSopas;
	private DefaultTableModel dtmBebidas;
	
	public Cardapio() {
        initComponents();
    }

    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        popupMenu1 = new java.awt.PopupMenu();
        jPanel3 = new javax.swing.JPanel();
        jtpGeral = new javax.swing.JTabbedPane();
        jtpDentro = new javax.swing.JTabbedPane();
        jtpContainerAbas = new javax.swing.JTabbedPane();
        jpPorcoes = new javax.swing.JPanel();
        jtPorcao = new javax.swing.JTable();
        jpSobremesa = new javax.swing.JPanel();
        jspSobremesa = new javax.swing.JScrollPane();
        jtSobremesa = new javax.swing.JTable();
        jpSorvete = new javax.swing.JPanel();
        jspSorvete = new javax.swing.JScrollPane();
        jtSorvete = new javax.swing.JTable();
        jpLanche = new javax.swing.JPanel();
        jspLanches = new javax.swing.JScrollPane();
        jtLanche = new javax.swing.JTable();
        jpAlmocoLivre = new javax.swing.JPanel();
        jspAlmocoLivre = new javax.swing.JScrollPane();
        jtAlmoco = new javax.swing.JTable();
        jpRodizioPizza = new javax.swing.JPanel();
        jspRodioPizza = new javax.swing.JScrollPane();
        jtRodizio = new javax.swing.JTable();
        jpBifeSopas = new javax.swing.JPanel();
        jspBifeSopas = new javax.swing.JScrollPane();
        jpBebida = new javax.swing.JPanel();
        jspBebidas = new javax.swing.JScrollPane();
        jtBebida = new javax.swing.JTable();
        jbtAdicionarPedido = new javax.swing.JButton();
        jbtFinalizarPedido = new javax.swing.JButton();
        jspPorcao  = new javax.swing.JScrollPane();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        popupMenu1.setLabel("popupMenu1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setTitle("CARDAPIO");
        getContentPane().setLayout(null);
        getContentPane().add(jtpGeral);
        jtpGeral.setBounds(100, -50, 5, 5);
        getContentPane().add(jtpDentro);
        jtpDentro.setBounds(200, -50, 5, 5);

        jpPorcoes.setLayout(null);
        //CRIANDO A TABELA
        dtmPorcao = new DefaultTableModel();
        dtmPorcao.addColumn("COD.");
        dtmPorcao.addColumn("DESCRI플O");
        dtmPorcao.addColumn("VALOR");
        
        //MOSTRA TODOS OS PRODUTOS POR TIPO
       for(Produto p: produtoDao.listarPor(TipoProduto.PORCOES)){
    	   dtmPorcao.addRow(new String[]{
    			   p.getCodigo().toString(), p.getDescricao().toString(), p.getValor().toString()
    	   });
       }
        
        jtPorcao= new JTable(dtmPorcao);
        jtPorcao.getColumnModel().getColumn(0).setMaxWidth(50);
        jtPorcao.getColumnModel().getColumn(1).setMaxWidth(600);
        jtPorcao.getColumnModel().getColumn(2).setMaxWidth(100);
        jspPorcao.setViewportView(jtPorcao);
        jspPorcao.setBounds(10, 10, 750, 350);
        jpPorcoes.add(jspPorcao);
        //ADICIONA O TABELA NO PAINEL
         jtpContainerAbas.addTab("POR합ES", jpPorcoes);

       
         jpSobremesa.setLayout(null);
         //CRIANDO A TABELA
         dtmSobremesa = new DefaultTableModel();
         dtmSobremesa.addColumn("COD.");
         dtmSobremesa.addColumn("DESCRI플O");
         dtmSobremesa.addColumn("VALOR");
         
       //MOSTRA TODOS OS PRODUTOS POR TIPO
         for(Produto p: produtoDao.listarPor(TipoProduto.SOBREMESA)){
      	   dtmSobremesa.addRow(new String[]{
      			   p.getCodigo().toString(), p.getDescricao().toString(), p.getValor().toString()
      	   });
         }
         
         jtSobremesa = new JTable(dtmSobremesa);
         jtSobremesa.getColumnModel().getColumn(0).setMaxWidth(50);
         jtSobremesa.getColumnModel().getColumn(1).setMaxWidth(600);
         jtSobremesa.getColumnModel().getColumn(2).setMaxWidth(100);
         jspSobremesa.setViewportView(jtSobremesa);
         jpSobremesa.add(jspSobremesa);
         jspSobremesa.setBounds(10, 10, 750, 350);
         //ADICIONA O TABELA NO PAINEL
         jtpContainerAbas.addTab("SOBREMESA", jpSobremesa);

        jpSorvete.setLayout(null);
      //CRIANDO A TABELA
        dtmSorvete = new DefaultTableModel();
        dtmSorvete.addColumn("COD.");
        dtmSorvete.addColumn("DESCRI플O");
        dtmSorvete.addColumn("VALOR");
        
      //MOSTRA TODOS OS PRODUTOS POR TIPO
        for(Produto p: produtoDao.listarPor(TipoProduto.SORVETE)){
     	   dtmSorvete.addRow(new String[]{
     			   p.getCodigo().toString(), p.getDescricao().toString(), p.getValor().toString()
     	   });
        }
                
        jtSorvete = new JTable(dtmSorvete);
        jtSorvete.getColumnModel().getColumn(0).setMaxWidth(50);
        jtSorvete.getColumnModel().getColumn(1).setMaxWidth(600);
        jtSorvete.getColumnModel().getColumn(2).setMaxWidth(100);
        jspSorvete.setViewportView(jtSorvete);
        jpSorvete.add(jspSorvete);
        jspSorvete.setBounds(10, 10, 750, 350);
        //ADICIONA O TABELA NO PAINEL
        jtpContainerAbas.addTab("SORVETE", jpSorvete);

        jpLanche.setLayout(null);
        //CRIANDO A TABELA
        dtmLanches = new DefaultTableModel();
        dtmLanches.addColumn("COD.");
        dtmLanches.addColumn("DESCRI플O");
        dtmLanches.addColumn("VALOR");
        
      //MOSTRA TODOS OS PRODUTOS POR TIPO
        for(Produto p: produtoDao.listarPor(TipoProduto.LANCHES)){
     	   dtmLanches.addRow(new String[]{
     			   p.getCodigo().toString(), p.getDescricao().toString(), p.getValor().toString()
     	   });
        }
                
        jtLanche = new JTable(dtmLanches);
        jtLanche.getColumnModel().getColumn(0).setMaxWidth(50);
        jtLanche.getColumnModel().getColumn(1).setMaxWidth(600);
        jtLanche.getColumnModel().getColumn(2).setMaxWidth(100);
        jspLanches.setViewportView(jtLanche);
        jpLanche.add(jspLanches);
        jspLanches.setBounds(10, 10, 750, 350);
        //ADICIONA O TABELA NO PAINEL
        jtpContainerAbas.addTab("LANCHES", jpLanche);

        jpAlmocoLivre.setLayout(null);
        //CRIANDO A TABELA
        dtmAlmocoBifeLivre = new DefaultTableModel();
        dtmAlmocoBifeLivre.addColumn("COD.");
        dtmAlmocoBifeLivre.addColumn("DESCRI플O");
        dtmAlmocoBifeLivre.addColumn("VALOR");
        
      //MOSTRA TODOS OS PRODUTOS POR TIPO
        for(Produto p: produtoDao.listarPor(TipoProduto.ALMOCOBIFELIVRE)){
        	dtmAlmocoBifeLivre.addRow(new String[]{
     			   p.getCodigo().toString(), p.getDescricao().toString(), p.getValor().toString()
     	   });
        }
        
        jtAlmoco = new JTable(dtmAlmocoBifeLivre);
        jtAlmoco.getColumnModel().getColumn(0).setMaxWidth(50);
        jtAlmoco.getColumnModel().getColumn(1).setMaxWidth(600);
        jtAlmoco.getColumnModel().getColumn(2).setMaxWidth(100);
        jspAlmocoLivre.setViewportView(jtAlmoco);
        jpAlmocoLivre.add(jspAlmocoLivre);
        jspAlmocoLivre.setBounds(10, 10, 750, 350);
        //ADICIONA O TABELA NO PAINEL
        jtpContainerAbas.addTab("ALMO�O BIFE LIVRE", jpAlmocoLivre);

        
        jpRodizioPizza.setLayout(null);
       //CRIANDO A TABELA
        dtmRodizioPizza = new DefaultTableModel();
        dtmRodizioPizza.addColumn("COD.");
        dtmRodizioPizza.addColumn("DESCRI플O");
        dtmRodizioPizza.addColumn("VALOR");
        
      //MOSTRA TODOS OS PRODUTOS POR TIPO
        for(Produto p: produtoDao.listarPor(TipoProduto.RODIZIOPIZZA)){
        	dtmRodizioPizza.addRow(new String[]{
     			   p.getCodigo().toString(), p.getDescricao().toString(), p.getValor().toString()
     	   });
        }
          
        jtRodizio = new JTable(dtmRodizioPizza);
        jtRodizio.getColumnModel().getColumn(0).setMaxWidth(50);
        jtRodizio.getColumnModel().getColumn(1).setMaxWidth(600);
        jtRodizio.getColumnModel().getColumn(2).setMaxWidth(100);
        jspRodioPizza.setViewportView(jtRodizio);
        jpRodizioPizza.add(jspRodioPizza);
        jspRodioPizza.setBounds(10, 10, 750, 350);
        //ADICIONA O TABELA NO PAINEL
        jtpContainerAbas.addTab("RODIZIO DE PIZZA", jpRodizioPizza);

        
        jpBifeSopas.setLayout(null);
      //CRIANDO A TABELA
        dtmBifeSopas = new DefaultTableModel();
        dtmBifeSopas.addColumn("COD.");
        dtmBifeSopas.addColumn("DESCRI플O");
        dtmBifeSopas.addColumn("VALOR");
        
      //MOSTRA TODOS OS PRODUTOS POR TIPO
        for(Produto p: produtoDao.listarPor(TipoProduto.BIFEDESOPAS)){
        	dtmBifeSopas.addRow(new String[]{
     			   p.getCodigo().toString(), p.getDescricao().toString(), p.getValor().toString()
     	   });
        }
          
        jtBifeSopas = new JTable(dtmBifeSopas);
        jtBifeSopas.getColumnModel().getColumn(0).setMaxWidth(50);
        jtBifeSopas.getColumnModel().getColumn(1).setMaxWidth(600);
        jtBifeSopas.getColumnModel().getColumn(2).setMaxWidth(100);
        jspBifeSopas.setViewportView(jtBifeSopas);
        jpBifeSopas.add(jspBifeSopas);
        jspBifeSopas.setBounds(10, 10, 750, 350);
        //ADICIONA O TABELA NO PAINEL
        jtpContainerAbas.addTab("BIFE DE SOPAS", jpBifeSopas);

        
        jpBebida.setLayout(null);
        //CRIANDO A TABELA
        dtmBebidas = new DefaultTableModel();
        dtmBebidas.addColumn("COD.");
        dtmBebidas.addColumn("DESCRI플O");
        dtmBebidas.addColumn("VALOR");
        
      //MOSTRA TODOS OS PRODUTOS POR TIPO
        for(Produto p: produtoDao.listarPor(TipoProduto.BEBIDAS)){
        	dtmBebidas.addRow(new String[]{
     			   p.getCodigo().toString(), p.getDescricao().toString(), p.getValor().toString()
     	   });
        }
          
        jtBebida = new JTable(dtmBebidas);
        jtBebida.getColumnModel().getColumn(0).setMaxWidth(50);
        jtBebida.getColumnModel().getColumn(1).setMaxWidth(600);
        jtBebida.getColumnModel().getColumn(2).setMaxWidth(100);
        jspBebidas.setViewportView(jtBebida);
        jpBebida.add(jspBebidas);
        jspBebidas.setBounds(10, 10, 750, 350);
        //ADICIONA O TABELA NO PAINEL
        jtpContainerAbas.addTab("BEBIDAS", jpBebida);
        
        //FINAL DO TABS
        getContentPane().add(jtpContainerAbas);
        jtpContainerAbas.setBounds(10, 10, 780, 410);

        jbtAdicionarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2.png"))); // NOI18N
        jbtAdicionarPedido.setText("ADICIONAR AO MEU PEDIDO");
        getContentPane().add(jbtAdicionarPedido);
        jbtAdicionarPedido.setBounds(350, 430, 220, 30);
        jbtAdicionarPedido.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// A합ES DO BOT홒 ADICIONAR
				JOptionPane.showMessageDialog(jbtAdicionarPedido, "Em desenvolvimento");
				
			}
		});

        jbtFinalizarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2.png"))); // NOI18N
        jbtFinalizarPedido.setText("FINALIZAR PEDIDO");
        getContentPane().add(jbtFinalizarPedido);
        jbtFinalizarPedido.setBounds(610, 430, 160, 30);
        jbtFinalizarPedido.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// //A합ES DO BOT홒 FINALIZAR
				JOptionPane.showMessageDialog(jbtFinalizarPedido, "Em desenvolvimento");
			}
		});
        //FIM DA TELA
        setBounds(310, 70, 802, 513);
    }

    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JPanel jpPorcoes;
    private javax.swing.JPanel jpBebida;
    private javax.swing.JPanel jpSobremesa;
    private javax.swing.JPanel jpLanche;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jpAlmocoLivre;
    private javax.swing.JPanel jpRodizioPizza;
    private javax.swing.JPanel jpBifeSopas;
    private javax.swing.JScrollPane jspSorvete;
    private javax.swing.JScrollPane jspLanches;
    private javax.swing.JScrollPane jspAlmocoLivre;
    private javax.swing.JScrollPane jspRodioPizza;
    private javax.swing.JScrollPane jspBifeSopas;
    private javax.swing.JScrollPane jspBebidas;
    private javax.swing.JScrollPane jspSobremesa;
    private javax.swing.JTabbedPane jtpGeral;
    private javax.swing.JTabbedPane jtpDentro;
    private javax.swing.JButton jbtAdicionarPedido;
    private javax.swing.JButton jbtFinalizarPedido;
    private javax.swing.JPanel jpSorvete;
    private javax.swing.JTable jtAlmoco;
    private javax.swing.JTable jtBebida;
    private javax.swing.JTable jtLanche;
    private javax.swing.JTable jtPorcao;
    private javax.swing.JTable jtRodizio;
    private javax.swing.JTable jtSobremesa;
    private javax.swing.JTable jtSorvete;
    private javax.swing.JTabbedPane jtpContainerAbas;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JScrollPane jspPorcao;
    private javax.swing.JTable jtBifeSopas;
}
