package model;

import model.Forma_Pago;
import model.FormaPago;

public class transferencia extends Forma_Pago implements FormaPago{
    public String codigoTrnsferencia;
    public double montoPago;

    public transferencia(String codigoTrnsferencia, double montoPago, String nombre, String apellido, String cedula, double precio) {
        super(nombre, apellido, cedula, precio);
        this.codigoTrnsferencia = codigoTrnsferencia;
        this.montoPago = montoPago;
    }
    public String getCodigoTrnsferencia() {
        return codigoTrnsferencia;
    }
    public void setCodigoTrnsferencia(String codigoTrnsferencia) {
        this.codigoTrnsferencia = codigoTrnsferencia;
    }
    public double getMontoPago() {
        return montoPago;
    }
    public void setMontoPago(double montoPago) {
        this.montoPago = montoPago;
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
