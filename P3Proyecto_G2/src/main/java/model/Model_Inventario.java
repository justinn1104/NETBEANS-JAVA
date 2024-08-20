package model;

public class Model_Inventario {
    private String fechaini, fechafin;

    public Model_Inventario(String fechaini, String fechafin) {
        this.fechaini = fechaini;
        this.fechafin = fechafin;
    }

    public String getFechaini() {
        return fechaini;
    }

    public void setFechaini(String fechaini) {
        this.fechaini = fechaini;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }
    
}
