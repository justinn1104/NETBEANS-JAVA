package view;

public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        inicio = new javax.swing.JMenu();
        menuCerrar = new javax.swing.JMenuItem();
        servicios = new javax.swing.JMenu();
        menuAdd = new javax.swing.JMenuItem();
        menuAddInventario = new javax.swing.JMenuItem();
        informacion = new javax.swing.JMenu();
        menuClients = new javax.swing.JMenuItem();
        menuMembres = new javax.swing.JMenuItem();
        menuInventario = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        escritorio.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 844, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 557, Short.MAX_VALUE)
        );

        jMenuBar1.setBorder(null);

        inicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home-icon-silhouette.png"))); // NOI18N

        menuCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/person.png"))); // NOI18N
        menuCerrar.setText("Cerrar");
        menuCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCerrarActionPerformed(evt);
            }
        });
        inicio.add(menuCerrar);

        jMenuBar1.add(inicio);

        servicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png"))); // NOI18N

        menuAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cross-selling.png"))); // NOI18N
        menuAdd.setText("Add Servicio");
        menuAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAddActionPerformed(evt);
            }
        });
        servicios.add(menuAdd);

        menuAddInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory.png"))); // NOI18N
        menuAddInventario.setText("Add Inventario");
        servicios.add(menuAddInventario);

        jMenuBar1.add(servicios);

        informacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project.png"))); // NOI18N

        menuClients.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client.png"))); // NOI18N
        menuClients.setText("Usuarios");
        menuClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuClientsActionPerformed(evt);
            }
        });
        informacion.add(menuClients);

        menuMembres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/identification-card.png"))); // NOI18N
        menuMembres.setText("Membresías");
        menuMembres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMembresActionPerformed(evt);
            }
        });
        informacion.add(menuMembres);

        menuInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory-management.png"))); // NOI18N
        menuInventario.setText("Inventario");
        informacion.add(menuInventario);

        jMenuBar1.add(informacion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCerrarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuCerrarActionPerformed

    private void menuAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAddActionPerformed
                        
    }//GEN-LAST:event_menuAddActionPerformed

    private void menuClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuClientsActionPerformed

    }//GEN-LAST:event_menuClientsActionPerformed

    private void menuMembresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMembresActionPerformed

    }//GEN-LAST:event_menuMembresActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
    public javax.swing.JDesktopPane escritorio;
    public javax.swing.JMenu informacion;
    public javax.swing.JMenu inicio;
    public javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JMenuItem menuAdd;
    public javax.swing.JMenuItem menuAddInventario;
    public javax.swing.JMenuItem menuCerrar;
    public javax.swing.JMenuItem menuClients;
    public javax.swing.JMenuItem menuInventario;
    public javax.swing.JMenuItem menuMembres;
    public javax.swing.JMenu servicios;
    // End of variables declaration//GEN-END:variables
}
