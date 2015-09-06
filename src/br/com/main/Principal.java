package br.com.main;

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

        jMenuCadastroFuncionario.setText("CADASTROS");
        jMenuCadastroFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCadastroFuncionarioActionPerformed(evt);
            }
        });

        jmiCadastarFuncionario.setText("CADASTAR FUNCIONARIOS");
        jmiCadastarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCadastarFuncionarioActionPerformed(evt);
            }
        });
        jMenuCadastroFuncionario.add(jmiCadastarFuncionario);

        jMenuBar1.add(jMenuCadastroFuncionario);

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
    	//GEN-FIRST:event_jmiCadastarFuncionarioActionPerformed
        CadastroFuncionario obj=new CadastroFuncionario();
        jdpPrincipal.add(obj);
        obj.setVisible(true);
    }

    private void jMenuCadastroFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {
    	//GEN-FIRST:event_jMenuCadastroFuncionarioActionPerformed
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
    
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCadastroFuncionario;
    public static javax.swing.JDesktopPane jdpPrincipal;
    private javax.swing.JMenuItem jmiCadastarFuncionario;
    // End of variables declaration//GEN-END:variables
}
