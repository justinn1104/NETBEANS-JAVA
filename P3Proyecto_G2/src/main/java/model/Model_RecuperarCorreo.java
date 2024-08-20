package model;

import controller.RecuperarStrategy;

public class Model_RecuperarCorreo implements RecuperarStrategy{

    @Override
    public void recuperar(String email) {
        // Lógica para enviar un enlace de recuperación por correo electrónico.
        System.out.println("--->>PATRON STRATEGY");
        System.out.println("Enviando enlace de recuperación al correo: " + email);
    }
    
}
