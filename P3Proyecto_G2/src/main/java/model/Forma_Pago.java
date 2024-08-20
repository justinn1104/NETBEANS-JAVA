package model;

public abstract class Forma_Pago {
    protected String nombre, apellido,cedula;
    protected double precio;

    public Forma_Pago(String nombre, String apellido, String cedula, double precio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.precio = precio;
    }

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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public abstract double calcularPrecio();
    public abstract void calcularImpuesto();
    
}
