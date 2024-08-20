

package d.p2proyecto_g2;

import controller.ControladorLogin;
import model.ModeloLogin;
import view.Login;

public class P3Proyecto_G2 {
    public static void main(String[] args) {
        ModeloLogin model = new ModeloLogin("", "");
        Login vista = new Login();
        ControladorLogin control = new ControladorLogin(vista, model);
        control.InterfazVista();
    }
}
