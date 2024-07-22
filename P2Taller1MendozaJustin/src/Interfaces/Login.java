package Interfaces;

import java.awt.Color;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        this.setBounds(400, 50, 739, 546);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        errorPws = new javax.swing.JLabel();
        errorUser = new javax.swing.JLabel();
        textUsuario = new javax.swing.JTextField();
        passUsuario = new javax.swing.JPasswordField();
        ButtonIngresar = new javax.swing.JButton();
        ButtonSalir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Viner Hand ITC", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INICIAR SECION");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 480, 70));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Usuario : ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contraseña :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, -1, -1));
        getContentPane().add(errorPws, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, -1, -1));
        getContentPane().add(errorUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, -1, -1));

        textUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        textUsuario.setSelectedTextColor(new java.awt.Color(242, 242, 242));
        textUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(textUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 240, 30));
        getContentPane().add(passUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 240, 30));

        ButtonIngresar.setText("Igresar");
        ButtonIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonIngresarActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 92, 45));

        ButtonSalir.setText("Salir");
        ButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 380, 90, 45));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fondos/Fondo1.jpg"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 740, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonIngresarActionPerformed
        String user="admin", pass="123";
        String UserIngreso = textUsuario.getText();
        String pswIngreso = new String(passUsuario.getPassword());
        errorUser.setText("");
        errorPws.setText("");
        boolean band1 =(UserIngreso.equals(user)&&pswIngreso.equals(pass));
        boolean band2 =(UserIngreso.equals(user));
        boolean band3 =(pswIngreso.equals(pass));
        if(UserIngreso.equals(user)&&pswIngreso.equals(pass)){
            MenuOptions acceso = new MenuOptions();
            acceso.setVisible(true);
            this.setVisible(false);            
        }else{            
            if(band1!=true){
                errorUser.setText("*Campo incorrecto");
                errorUser.setForeground(Color.red);
                errorPws.setText("*Campo incorrecto");
                errorPws.setForeground(Color.red);
            }else{
                if(band2!=true){
                    errorUser.setText("*Campo incorrecto");
                    errorUser.setForeground(Color.red);
                }
                if(band3!=true){
                    errorPws.setText("*Campo incorrecto");
                    errorPws.setForeground(Color.red);
                }
            }
            if(UserIngreso.length()==0 && pswIngreso.length()==0){
                errorUser.setText("*Campo vacio");
                errorUser.setForeground(Color.red);
                errorPws.setText("*Campo vacio");
                errorPws.setForeground(Color.red);
            }else{
                if(UserIngreso.length()==0){
                    errorUser.setText("*Campo vacio");
                    errorUser.setForeground(Color.red);
                }
                if(pswIngreso.length()==0){
                    errorPws.setText("*Campo vacio");
                    errorPws.setForeground(Color.red);
                }
            }
            if(band2==true){
                errorUser.setText("");        
            }
            if(band3==true){
                errorPws.setText("");
            }
        }
    }//GEN-LAST:event_ButtonIngresarActionPerformed

    private void textUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textUsuarioActionPerformed

    private void ButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSalirActionPerformed
        CierrePrograma accec = new CierrePrograma();
        accec.setVisible(true);
        this.setVisible(false);        
    }//GEN-LAST:event_ButtonSalirActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonIngresar;
    private javax.swing.JButton ButtonSalir;
    private javax.swing.JLabel errorPws;
    private javax.swing.JLabel errorUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField passUsuario;
    private javax.swing.JTextField textUsuario;
    // End of variables declaration//GEN-END:variables
}
