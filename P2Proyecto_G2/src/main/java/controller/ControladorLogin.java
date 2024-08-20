package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import model.ModeloLogin;
import view.Login;
import view.RegistrarUser;
import javax.swing.JOptionPane;
import model.ModeloRegistro;
import ConexionBD.ConexionBD;
import model.ModeloPrincipal;
import view.Principal;

public class ControladorLogin implements ActionListener {
    Login view;
    ModeloLogin model;
    private boolean isPasswordVisible = false;
    private ConexionBD conexionBD;
    

    public ControladorLogin(Login view, ModeloLogin model) {
        this.view = view;
        this.model = model;
        this.view.btnEntrar.addActionListener(this);
        this.view.btnCrear.addActionListener(this);
        this.conexionBD = ConexionBD.getInstance();
        conexionBD.setCollName("Credenciales"); // Configurar colección correcta
    }

    public void guardar() {
        String usuario = view.txtUsuario.getText();
        String contra = new String(view.txtContra.getPassword());
        boolean campo = true;

        if (usuario.isEmpty()) {
            view.txtVaUsuario.setText("**Campo Obligatorio");
            campo = false;
        } else {
            view.txtVaUsuario.setText("");
        }

        if (contra.isEmpty()) {
            view.txtVaContra.setText("**Campo Obligatorio");
            campo = false;
        } else {
            view.txtVaContra.setText("");
        }

        if (campo) {
            MongoCollection<Document> collection = conexionBD.database.getCollection("Credenciales");
            Document filtro = new Document("usuario", usuario).append("contrasena", contra);
            Document usuarioDoc = collection.find(filtro).first();
            
            if (usuarioDoc != null) {
                JOptionPane.showMessageDialog(null, "Conexión exitosa");
                view.setVisible(false);
                Principal view = new Principal();
                ModeloPrincipal model = new ModeloPrincipal("","","","");
                ControladorPrincipal ctrl = new ControladorPrincipal(view,model);
                ctrl.iniciarView();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no registrado");
            }
        }
    }
    
    public void vistasInter (){
        view.setVisible(false);
        RegistrarUser registrarView = new RegistrarUser();
        ModeloRegistro modeloRegistro = new ModeloRegistro("", "", "", "");
        MongoCollection<Document> collection = conexionBD.database.getCollection("Usuarios");
        ControladorRegistro controladorRegistro = new ControladorRegistro(registrarView, modeloRegistro, collection);
        registrarView.setVisible(true);
    }
    
    public void InterfazVista() {
        view.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnEntrar) {
            guardar();
        } else if (e.getSource() == view.btnCrear) {
            vistasInter();
        }
    }
}
