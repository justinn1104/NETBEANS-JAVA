package Model;

public abstract class Persona {
    protected String nombre,apellido,cedula,nivel;
    //Metodo Contructor
    public Persona(String nombre, String apellido, String cedula, String nivel) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.nivel = nivel;
    }
    //Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    //Metodos abstractos
    public abstract void generarDatos();
    
}
