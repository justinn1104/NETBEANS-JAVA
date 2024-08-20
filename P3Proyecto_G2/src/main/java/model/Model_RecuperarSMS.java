package model;

import controller.RecuperarStrategy;

public class Model_RecuperarSMS implements RecuperarStrategy {

    @Override
    public void recuperar(String telefono) {
        // Lógica para enviar un código de recuperación por SMS.
        System.out.println("--->>PATRON STRATEGY");
        System.out.println("Enviando código de recuperación al número: " + telefono);
    }
    
}
