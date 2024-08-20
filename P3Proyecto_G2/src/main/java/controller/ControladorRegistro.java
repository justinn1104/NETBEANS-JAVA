package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ModeloRegistro;
import view.Login;
import view.RegistrarUser;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import javax.swing.JOptionPane;
import model.ConexionBD;
import model.ModeloLogin;

public class ControladorRegistro implements ActionListener {
    private RegistrarUser view;
    private ModeloRegistro model;
    private ConexionBD conexionBD;
    private Strategy registroStrategy;

    public ControladorRegistro(RegistrarUser view, ModeloRegistro model, MongoCollection<Document> collection1) {
        this.view = view;
        this.model = model;
        this.conexionBD = ConexionBD.getInstance();
        this.conexionBD.setCollName("Credenciales"); // COLECCION
        this.registroStrategy = new RegistroStrategy(); // USAMOS LA STRATEGIA PARA LOS REGISTROS
        this.view.btnRegresar.addActionListener(this);
        this.view.btnRegistrar.addActionListener(this);
    }

    public void registrar() {
        String nombre = view.txtNombre.getText();
        String apellido = view.txtApellido.getText();
        String celular = view.txtCelular.getText();
        String cedula = view.txtCedula.getText();

        boolean datosValidos = registroStrategy.validarDatos(nombre, apellido, celular, cedula, view);
        
        if (datosValidos) {
            registroStrategy.registrarUsuario(nombre, apellido, celular, cedula, conexionBD, view);
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
