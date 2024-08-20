package model;
public class ModeloMembres {
    public String nombre, genero, tipo, horario, pago, suplements;

    public ModeloMembres(String nombre, String genero, String tipo, String horario, String pago, String suplements) {
        this.nombre = nombre;
        this.genero = genero;
        this.tipo = tipo;
        this.horario = horario;
        this.pago = pago;
        this.suplements = suplements;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getSuplements() {
        return suplements;
    }

    public void setSuplements(String suplements) {
        this.suplements = suplements;
    }
    
    
}
