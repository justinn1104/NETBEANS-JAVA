package model;

import model.Forma_Pago;
import model.FormaPago;

public class Efectivo extends Forma_Pago implements FormaPago{
    public double monntoPago, suelto;
    public Efectivo(double monntoPago, double suelto, String nombre, String apellido, String cedula, double precio) {
        super(nombre, apellido, cedula, precio);
        this.monntoPago = monntoPago;
        this.suelto = suelto;        
    }

    public double getMonntoPago() {
        return monntoPago;
    }

    public void setMonntoPago(double monntoPago) {
        this.monntoPago = monntoPago;
    }

    public double getSuelto() {
        return suelto;
    }

    public void setSuelto(double suelto) {
        this.suelto = suelto;
    }
    
    @Override
    public double calcularPrecio() {        
        return  precio * 1.1;
    }

    @Override
    public void calcularImpuesto() {
        precio = precio * 1.15;
    }
    
}
