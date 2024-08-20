package controller;
// STRATEGY *********************************
// ***************************************
// Interface Segregation Principle (ISP)
// ***************************************
import model.ConexionBD;
import view.RegistrarUser;

public interface Strategy {
    // Método para validar los datos de entrada y actualizar la vista con mensajes de error
    boolean validarDatos(String nombre, String apellido, String celular, String cedula, RegistrarUser view);
    // Método para registrar el usuario en la base de datos
    void registrarUsuario(String nombre, String apellido, String celular, String cedula, ConexionBD conexionBD, RegistrarUser view);
}
