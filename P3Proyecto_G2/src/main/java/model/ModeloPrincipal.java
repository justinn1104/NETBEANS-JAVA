package model;

public class ModeloPrincipal {
    public String Salir, add, infoCli, infoMenbres;

    public ModeloPrincipal(String Salir, String add, String infoCli, String infoMenbres) {
        this.Salir = Salir;
        this.add = add;
        this.infoCli = infoCli;
        this.infoMenbres = infoMenbres;
    }

    public String getSalir() {
        return Salir;
    }

    public void setSalir(String Salir) {
        this.Salir = Salir;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getInfoCli() {
        return infoCli;
    }

    public void setInfoCli(String infoCli) {
        this.infoCli = infoCli;
    }

    public String getInfoMenbres() {
        return infoMenbres;
    }

    public void setInfoMenbres(String infoMenbres) {
        this.infoMenbres = infoMenbres;
    }
}