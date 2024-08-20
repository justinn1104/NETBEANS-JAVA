package model;

public class ModeloAdd_User {
    public String nomb, apell, dire, cedula, celular, genero, edad, tipo, horario,  pago, suple;

    public ModeloAdd_User(String nomb, String apell, String dire, String cedula, String celular, String genero, String edad, String tipo, String horario, String pago, String suple) {
        this.nomb = nomb;
        this.apell = apell;
        this.dire = dire;
        this.cedula = cedula;
        this.celular = celular;
        this.genero = genero;
        this.edad = edad;
        this.tipo = tipo;
        this.horario = horario;
        this.pago = pago;
        this.suple = suple;
        this.celular = celular;
        this.genero = genero;
        this.edad = edad;
    }

    public String getNomb() {
        return nomb;
    }

    public void setNomb(String nomb) {
        this.nomb = nomb;
    }

    public String getApell() {
        return apell;
    }

    public void setApell(String apell) {
        this.apell = apell;
    }

    public String getDire() {
        return dire;
    }

    public void setDire(String dire) {
        this.dire = dire;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
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

    public String getSuple() {
        return suple;
    }

    public void setSuple(String suple) {
        this.suple = suple;
    }
    
}
