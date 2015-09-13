package br.com.main;

import br.com.model.Funcionario;

public class Principal extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	public Principal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
    }
	
    private void initComponents() {

        jdpPrincipal = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCadastroFuncionario = new javax.swing.JMenu();
        jmiCadastarFuncionario = new javax.swing.JMenuItem();
        jmiCadastarBebidas = new javax.swing.JMenuItem();
        jmiCadastarCardapio = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jmiGerenciamentoCaixa = new javax.swing.JMenuItem();
        jmCardapio = new javax.swing.JMenu();
        jmiCardapio = new javax.swing.JMenuItem();
        jmPedidoInterno = new javax.swing.JMenu();
        jmiPedidoCozinha = new javax.swing.JMenuItem();
        jmiPedidoGarcon = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CONTROLE INTERNO RESTAURANTE");

        javax.swing.GroupLayout jdpPrincipalLayout = new javax.swing.GroupLayout(jdpPrincipal);
        jdpPrincipal.setLayout(jdpPrincipalLayout);
        jdpPrincipalLayout.setHorizontalGroup(
            jdpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        jdpPrincipalLayout.setVerticalGroup(
            jdpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 496, Short.MAX_VALUE)
        );

        jMenuCadastroFuncionario.setText("GERENCIADOR DE COLABORADORES");
        jMenuCadastroFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCadastroFuncionarioActionPerformed(evt);
            }
        });

        jmiCadastarFuncionario.setText("Cadastrar Funcionarios");
        jmiCadastarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCadastarFuncionarioActionPerformed(evt);
            }
        });
        jMenuCadastroFuncionario.add(jmiCadastarFuncionario);

        jmiCadastarBebidas.setText("Cadastar Bebidas");
        jmiCadastarBebidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCadastarBebidasActionPerformed(evt);
            }
        });
        jMenuCadastroFuncionario.add(jmiCadastarBebidas);

        jmiCadastarCardapio.setText("GERENCIADOR DE CARDAPIOS");
        jmiCadastarCardapio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCadastarCardapioActionPerformed(evt);
            }
        });
        jMenuCadastroFuncionario.add(jmiCadastarCardapio);

        jMenuBar1.add(jMenuCadastroFuncionario);

        jMenu1.setText("GERENCIADOR DE CAIXA");

        jmiGerenciamentoCaixa.setText("Gerenciamento Caixa");
        jmiGerenciamentoCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiGerenciamentoCaixaActionPerformed(evt);
            }
        });
        jMenu1.add(jmiGerenciamentoCaixa);

        jMenuBar1.add(jMenu1);

        jmCardapio.setText("GERENCIADOR DE PEDIDOS");

        jmiCardapio.setText("Cardapio Cliente");
        jmiCardapio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCardapioActionPerformed(evt);
            }
        });
        jmCardapio.add(jmiCardapio);

        jMenuBar1.add(jmCardapio);

        jmPedidoInterno.setText("GERENCIADOR DE PEDIDOS INTERNOS ");

        jmiPedidoCozinha.setText("Pedido Cozinha");
        jmiPedidoCozinha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPedidoCozinhaActionPerformed(evt);
            }
        });
        jmPedidoInterno.add(jmiPedidoCozinha);

        jmiPedidoGarcon.setText("Pedido Garçom");
        jmiPedidoGarcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPedidoGarconActionPerformed(evt);
            }
        });
        jmPedidoInterno.add(jmiPedidoGarcon);

        jMenuBar1.add(jmPedidoInterno);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal)
        );

        setBounds(0, 0, 870, 555);
    }

    private void jmiCadastarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {
        CadastroFuncionario obj= new CadastroFuncionario(false, new Funcionario());
        jdpPrincipal.add(obj);
        obj.setVisible(true);
    }

    private void jMenuCadastroFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {
    
    }

    private void jmiCadastarBebidasActionPerformed(java.awt.event.ActionEvent evt) {
        CadastarBebida obj=new CadastarBebida();
        jdpPrincipal.add(obj);
        obj.setVisible(true);
    }

    private void jmiCadastarCardapioActionPerformed(java.awt.event.ActionEvent evt) {
        CadastarCardapio obj=new CadastarCardapio();
        jdpPrincipal.add(obj);
        obj.setVisible(true);
    }

    private void jmiGerenciamentoCaixaActionPerformed(java.awt.event.ActionEvent evt) {
       GerenciamentoCaixa obj=new GerenciamentoCaixa();
        jdpPrincipal.add(obj);
        obj.setVisible(true);
    }

    private void jmiCardapioActionPerformed(java.awt.event.ActionEvent evt) {
        PedidoCliente obj=new PedidoCliente();
        jdpPrincipal.add(obj);
        obj.setVisible(true);
    }

    private void jmiPedidoGarconActionPerformed(java.awt.event.ActionEvent evt) {
      PedidoGarcom obj=new PedidoGarcom();
        jdpPrincipal.add(obj);
        obj.setVisible(true);
    }

    private void jmiPedidoCozinhaActionPerformed(java.awt.event.ActionEvent evt) {
      PedidoCozinha obj=new PedidoCozinha();
        jdpPrincipal.add(obj);
        obj.setVisible(true);
    }


    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCadastroFuncionario;
    public static javax.swing.JDesktopPane jdpPrincipal;
    private javax.swing.JMenu jmCardapio;
    private javax.swing.JMenu jmPedidoInterno;
    private javax.swing.JMenuItem jmiCadastarBebidas;
    private javax.swing.JMenuItem jmiCadastarCardapio;
    private javax.swing.JMenuItem jmiCadastarFuncionario;
    private javax.swing.JMenuItem jmiCardapio;
    private javax.swing.JMenuItem jmiGerenciamentoCaixa;
    private javax.swing.JMenuItem jmiPedidoCozinha;
    private javax.swing.JMenuItem jmiPedidoGarcon;
    // End of variables declaration//GEN-END:variables
}
