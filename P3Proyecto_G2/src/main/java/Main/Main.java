package Main;

import controller.ControladorLogin;
import model.ModeloLogin;
import view.Login;

public class Main {
    public static void main(String[] args) {
        ModeloLogin model = new ModeloLogin("", "");
        Login vista = new Login();
        ControladorLogin control = new ControladorLogin(vista, model);
        control.InterfazVista();
    }
}
