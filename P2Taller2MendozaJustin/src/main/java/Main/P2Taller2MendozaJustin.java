package Main;

import Controller.ControllerRegistroEstudiantes;
import Model.ModelRegistroEstudiantes;
import View.RegistroEstudiantes;

public class P2Taller2MendozaJustin {

    public static void main(String[] args) {
        ModelRegistroEstudiantes modelo = new ModelRegistroEstudiantes("","","","","","","");
        RegistroEstudiantes vista = new RegistroEstudiantes();
        ControllerRegistroEstudiantes controlador = new ControllerRegistroEstudiantes(modelo, vista);
        controlador.iniciarVista();
    }
}
