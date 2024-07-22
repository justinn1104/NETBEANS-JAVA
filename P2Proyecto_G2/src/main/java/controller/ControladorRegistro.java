package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ModeloRegistro;
import view.Login;
import view.RegistrarUser;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import javax.swing.JOptionPane;
import model.ModeloLogin;
import ConexionBD.ConexionBD;

public class ControladorRegistro implements ActionListener {
    RegistrarUser view;
    ModeloRegistro model;
    MongoCollection<Document> collection;
    private ConexionBD conexionBD;

    public ControladorRegistro(RegistrarUser view, ModeloRegistro model, MongoCollection<Document> collection1) {
        this.view = view;
        this.model = model;
        this.collection = collection1;
        conexionBD = new ConexionBD();

        this.view.btnRegresar.addActionListener(this);
        this.view.btnRegistrar.addActionListener(this);
    }

    public void registrar() {
        String nombre = view.txtNombre.getText();
        String cedula = view.txtCedula.getText();
        String celular = view.txtCelular.getText();
        String apellido = view.txtApellido.getText();
        boolean campoVa = true;
        //
        if (nombre.isEmpty()) {
            view.txtVaNombre.setText("**Campo Obligatorio");
            campoVa = false;
        } else {
            view.txtVaNombre.setText("");
        }
        if (apellido.isEmpty()) {
            view.txtVaApellido.setText("**Campo Obligatorio");
            campoVa = false;
        } else {
            view.txtVaApellido.setText("");
        }
        if (celular.isEmpty()) {
            view.txtVaCelular.setText("**Campo Obligatorio");
            campoVa = false;
        } else if (celular.length() != 10) {
            view.txtVaCelular.setText("**Obligatorio 10 dígitos");
            campoVa = false;
        } else if("09".equals(celular.substring(0, 1))){
            
        }else{
            view.txtVaCelular.setText("");
        }
        if (cedula.isEmpty()) {
            view.txtVaCedula.setText("**Campo Obligatorio");
            campoVa = false;
        } else if (cedula.length() != 10) {
            view.txtVaCedula.setText("**Obligatorio 10 dígitos");
            campoVa = false;
        } else {
            view.txtVaCedula.setText("");
        }
        if (campoVa) {
            // Verificar si la cédula ya existe
            Document filtroCedula = new Document("cedula", cedula);
            if (!conexionBD.buscarRegistro("RegistroUsuario", "Usuarios", filtroCedula).isEmpty()) {
                view.txtVaCedula.setText("**Cédula ya existente");
                return;
            }

            String usuario = nombre.substring(0, 3) + cedula.substring(0, 3);
            String contrasena = apellido + (int)(Math.random() * 100);
            Document doc = new Document("usuario", usuario)
                           .append("contrasena", contrasena)
                           .append("cedula", cedula);
            conexionBD.insertarRegistro("RegistroUsuario", "Usuarios", doc);
            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.\n"
                                              + "Usuario: " + usuario + "\n"
                                              + "Contraseña: " + contrasena);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnRegresar) {
            view.setVisible(false);
            Login loginView = new Login();
            ModeloLogin modeloLogin = new ModeloLogin("", ""); 
            ControladorLogin controladorLogin = new ControladorLogin(loginView, modeloLogin);
            loginView.setVisible(true);
        } else if (e.getSource() == view.btnRegistrar) {
            registrar();
        }
    }
}
