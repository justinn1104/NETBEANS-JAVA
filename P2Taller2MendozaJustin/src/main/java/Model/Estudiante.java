package Model;

import javax.swing.JOptionPane;

public class Estudiante extends Persona implements Interface{
    private String promedio,Becario, Ncreditos, total;
    //Metodo Constructor
    public Estudiante(String promedio, String Becario, String Ncreditos, String total, String nombre, String apellido, String cedula, String nivel) {
        super(nombre, apellido, cedula, nivel);
        this.promedio = promedio;
        this.Becario = Becario;
        this.Ncreditos = Ncreditos;
        this.total = total;
    }
    //Getters y setters
    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public String getBecario() {
        return Becario;
    }

    public void setBecario(String Becario) {
        this.Becario = Becario;
    }

    public String getNcreditos() {
        return Ncreditos;
    }

    public void setNcreditos(String Ncreditos) {
        this.Ncreditos = Ncreditos;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    public double calcularPago(String tipoBeca, String Ncreditos){
        double pagoTotal=0;
        double subTotal=0;
        double VALORCREDITO = 17.5;
        int creditos = Integer.parseInt(Ncreditos);
        subTotal = creditos*VALORCREDITO;
        if(tipoBeca.equals("Socail-Acedemica")){
           //descuento del 65%
           double desc = subTotal*0.65;
           pagoTotal = subTotal-desc;
        } else if(tipoBeca.equals("Social")){
           //descuento del 45%
           double desc = subTotal*0.45;
           pagoTotal = subTotal-desc;
        }else if(tipoBeca.equals("Academica")){
           //descuento del 30%            
           double desc = subTotal*0.30;
           pagoTotal = subTotal-desc;
        }else if(tipoBeca.equals("Normal")) {
           //descuento del 0%
           pagoTotal = subTotal;
        }
        return pagoTotal;
    }
    
    public String evaluarBeca(String promedio) {
        try {
            double promedioD = Double.parseDouble(promedio);
            // Solicitar ingreso socioeconómico al usuario
            String ingresoStr = JOptionPane.showInputDialog(null, "Ingrese el ingreso mensual del estudiante (en USD):", "Evaluar Beca", JOptionPane.QUESTION_MESSAGE);
            if (ingresoStr == null || ingresoStr.isEmpty()) {
                System.out.println("No se ingresó un ingreso mensual válido.");
                return null;
            }
            float ingreso = Float.parseFloat(ingresoStr);
            if (ingreso <= 0) {
                System.out.println("El ingreso ingresado no es válido.");
                return null;
            }
            // Evaluar tipo de beca
            if (promedioD >= 17.5 && ingreso <= 300) {
                return "Socail-Acedemica";
            } else if (promedioD >= 17.5) {
                return "Academica";
            } else if (ingreso <= 300) {
                return "Social";
            } else {
                return "Normal";
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void generarDatos() {
        JOptionPane.showMessageDialog(null, "Paciente agregado con éxito!!!" +
                "\nNombre: " + getNombre() +
                "\nApellido: " + getApellido() +
                "\nCédula: " + getCedula() +
                "\nNivel: " + getNivel() +
                "\nPromedio: " + getPromedio() +
                "\nBecario: " + getBecario() +
                "\nNumero de creditos: " + getNcreditos() +
                "\nTotal a pagar: " + getTotal());
    }
    
}
