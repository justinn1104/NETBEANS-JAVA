package model;

public class ModeloLogin {
    public String usuario, contra;
    public ModeloLogin (String usuario, String contra){
        this.usuario = usuario;
        this.contra = contra;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
    
}
