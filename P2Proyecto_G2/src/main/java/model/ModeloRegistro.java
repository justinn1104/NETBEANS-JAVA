package model;

public class ModeloRegistro {
    private String nom, apell, cedula, celular;
     public ModeloRegistro (String nom, String apell, String cedula, String celular){
         this.nom = nom;
         this.apell = apell;
         this.cedula = cedula;
         this.celular = celular;
     }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApell() {
        return apell;
    }

    public void setApell(String apell) {
        this.apell = apell;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    
}
