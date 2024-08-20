package model;

import java.util.Random;
import javax.swing.JOptionPane;

public class Model_PswRecuperar {
    public String codigo, dataUser;

    public Model_PswRecuperar(String codigo, String dataUser) {
        this.codigo = codigo;
        this.dataUser = dataUser;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDataUser() {
        return dataUser;
    }

    public void setDataUser(String dataUser) {
        this.dataUser = dataUser;
    }
    
    public boolean seguridad(String codigoSegu){
        String seguridad = null;
        boolean seguri= false;
        while(!seguri){
            seguridad = JOptionPane.showInputDialog(null, "Ingrese el codigo de seguridad: [6dig]");
            if (seguridad == null) {
                return false; 
            } else if (seguridad.isEmpty() || seguridad.length() != 6) {
                JOptionPane.showMessageDialog(null, "EL codigo debe tener 6 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
                seguri=false;
            } else if(codigoSegu.equals(seguridad)){
                seguri=true;
            }else{
                seguri=false;
            }
        }
        return seguri;
    }    
    public String generarCodigo() {
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000);
        setCodigo(String.valueOf(codigo));
        return getCodigo();
    }
}
