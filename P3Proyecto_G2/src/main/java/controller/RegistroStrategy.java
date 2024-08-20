package controller;
//STRATEGY - ESTRATEGIA PARA REGISTRAR/GUARDAR USUARIOS EN MI BD
//************************************************
import javax.swing.JOptionPane;
import org.bson.Document;
import model.ConexionBD;
import view.RegistrarUser;

// Single Responsibility Principle (SRP) - SU UNICA RESPONSABILIDAD ES VALIDAR Y REGISTRAR USUARIOS
//************************************************
//Liskov Substitution Principle (LSP) -  Si se implem la interfaz Strategy, y puede ser sustituida 
//por cualquier otra implementación de Strategy sin afectar el funcionamiento del código.

public class RegistroStrategy implements Strategy {
    
    @Override
    public boolean validarDatos(String nombre, String apellido, String celular, String cedula, RegistrarUser view) {
        boolean campoVa = true;
        // Validación de nombre
        if (nombre.isEmpty()) {
            view.txtVaNombre.setText("Campo Obligatorio");
            campoVa = false;
        } else {
            view.txtVaNombre.setText(null);
        }
        // Validación de apellido
        if (apellido.isEmpty()) {
            view.txtVaApellido.setText("Campo Obligatorio");
            campoVa = false;
        } else {
            view.txtVaApellido.setText(null);
        }

        // Validación de celular
        if (celular.isEmpty() || celular.length() != 10 || !celular.startsWith("09")) {
            view.txtVaCelular.setText("Campo Obligatorio o formato incorrecto");
            campoVa = false;
        } else {
            view.txtVaCelular.setToolTipText(null);
        }

        // Validación de cédula
        if (cedula.isEmpty() || cedula.length() != 10) {
            view.txtVaCedula.setText("Campo Obligatorio o formato incorrecto");
            campoVa = false;
        } else {
            view.txtVaCedula.setText(null);
        }

        return campoVa;
    }

    @Override
    public void registrarUsuario(String nombre, String apellido, String celular, String cedula, ConexionBD conexionBD, RegistrarUser view) {
        // Verifica si la cédula ya existe en la base de datos
        Document filtroCedula = new Document("cedula", cedula);
        if (!conexionBD.buscarRegistro(filtroCedula).isEmpty()) {
            view.txtVaCedula.setText("");
            view.txtVaCedula.setText("La cédula ya está registrada.");
            return;
        }

        // Limpia el mensaje de error si la cédula es válida
        view.txtVaCedula.setText(null);

        // Crea un nuevo documento con los datos del usuario
        String usuario = nombre.substring(0, 3) + cedula.substring(0, 3);
        String contrasena = apellido + (int)(Math.random() * 100);
        Document doc = new Document("usuario", usuario)
                       .append("contrasena", contrasena)
                       .append("cedula", cedula);

        // Inserta el documento en la base de datos
        conexionBD.insertarRegistro(doc);
        JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.\n"
                                          + "Usuario: " + usuario + "\n"
                                          + "Contraseña: " + contrasena);
    }
}
