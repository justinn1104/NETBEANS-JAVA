package model;
//***RESPONSAB - Calcular Impuesto .- Calcular Precio
//***********************+++
//OPEN/CLOSE - SE PUEDE IMPLMENTAR SIN SAFECTAR MI CODIGO 
public interface FormaPago {
    /*
    * Calcular el precio del servicio.
    * @param precio.
    */
    double calcularPrecio();
    /*
    * Calcular el impuetosde cada uno de los servicios.
    * @param impuesto.
    */
    void calcularImpuesto();
}
