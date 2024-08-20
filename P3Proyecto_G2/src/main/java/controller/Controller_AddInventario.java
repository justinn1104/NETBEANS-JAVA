package controller;

import com.mongodb.client.MongoCollection;
import static controller.ControladorAdd_User.correoValido;
import static controller.ControladorAdd_User.numeroCelularValido;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.ConexionBD;
import model.Model_AddInventario;
import org.bson.Document;
import view.Add_Inventario;

public class Controller_AddInventario implements ActionListener{
    public Add_Inventario view;
    public Model_AddInventario model;
    MongoCollection<Document> collection;
    private ConexionBD conexionBD;

    public Controller_AddInventario(Add_Inventario view, Model_AddInventario model, MongoCollection<Document> collection) {
        this.view = view;
        this.model = model;
        this.collection = collection;
        this.conexionBD = ConexionBD.getInstance();
        this.view.btnGuardar.addActionListener(this);
        this.view.btnCancelar.addActionListener(this);
        this.limpiarCampos();
    }
    public void limpiarCampos(){                
        this.view.txtNombre.setText("");
        this.view.txtCodigo.setText("");
        this.view.txtObser.setText("");
        this.view.dateInventario.setDate(null);
        this.view.radNew.setSelected(false);
        this.view.radOld.setSelected(false);
        this.view.errName.setText("");
        this.view.errCodi.setText("");
        this.view.errEstado.setText("");
        this.view.errFecha.setText("");
    }
    public boolean validar() {
        String nom = view.txtNombre.getText();
        String codigo = view.txtCodigo.getText();
        String obsertv = view.txtObser.getText();
        boolean valid = true;

        // Validación de estado
        if (!(view.radNew.isSelected() || view.radOld.isSelected())) {
            view.errEstado.setText("**Campo Obligatorio");
            valid = false;
            System.out.println("--->>estado noSelected is: "+valid);
        } else {
            view.errEstado.setText("");
        }

        // Validación de nombres
        if (nom.isEmpty()) {
            view.errName.setText("**Campo Obligatorio");
            valid = false;
            System.out.println("--->>nombre vacio is: "+valid);
        } else {
            view.errName.setText("");
        }

        // Validación de código
        if (codigo.length() != 6) {
            view.errCodi.setText("**Formato ###### 6 dig.");
            valid = false;
            System.out.println("--->>codigo tamaño is: "+valid);
        } else if (codigo.isEmpty()) {
            view.errCodi.setText("**Campo Obligatorio");
            valid = false;
            System.out.println("--->>codigo vacio is: "+valid);
        } else {
            view.errCodi.setText("");
        }
        valid = validarFecha();
        System.out.println("--->>fecha is: "+valid);
        // Validación de fecha
        if (valid) {
            // Solo si la fecha no es null y es válida, convertirla a String
            Date fecha = view.dateInventario.getDate();
            long lfecha = fecha.getTime();
            java.sql.Date lfecha_sql = new java.sql.Date(lfecha);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaStr = dateFormat.format(lfecha_sql);

            // Setear valores en el modelo
            String estado = view.radNew.isSelected() ? "Nuevo" : "Usado";
            model.setNombre(nom);
            model.setCodigo(codigo);
            model.setObserva(obsertv);
            model.setEstado(estado);
            model.setFecha(fechaStr);
        }

        return valid;
    }
    public void guardarInventario(){      
        // Verificar si la codigo ya existe
        Document filtroCodigo = new Document("Codigo", model.getCodigo());
        if (!conexionBD.buscarRegistro(filtroCodigo).isEmpty()) {
            view.errCodi.setText("**Objeto ya existente");
            return;
        }
        Document doc = new Document("Nombre", model.getNombre())
                       .append("Codigo", model.getCodigo())
                       .append("Estado", model.getEstado())
                       .append("Fecha", model.getFecha())
                       .append("Observacion", model.getObserva());
        conexionBD.setCollName("Inventario"); // Configurar colección correcta
        conexionBD.insertarRegistro(doc);        
        JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.");
        limpiarCampos();
    }
    public void btnGuardar(){
        boolean vali = validar();
        if(vali){
            guardarInventario();
        }else{
            System.out.println("Error no guardo en el inventario");
        }
    }
    private boolean validarFecha() {
        Date fechaSeleccionada = view.dateInventario.getDate();
        if (fechaSeleccionada == null) {
            view.errFecha.setText("**Fecha Obligatoria");
            return false;
        }
        Date fechaActual = new Date(); // Obtiene la fecha actual
        long unDiaMilisegundos = 24 * 60 * 60 * 1000; // Un día en milisegundos
        // Validar que la fecha seleccionada no sea menor que la actual y no mayor a un día después
        if (fechaSeleccionada.getTime() < fechaActual.getTime()) {
            view.errFecha.setText("**La fecha no puede ser menor a la actual");
            return false;
        } else if (fechaSeleccionada.getTime() > (fechaActual.getTime() + unDiaMilisegundos)) {
            view.errFecha.setText("**La fecha no puede ser mayor a un día después de la actual");
            return false;
        } else {
            view.errFecha.setText(""); // Limpia el error si la validación es correcta
        }

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.btnGuardar){
            btnGuardar();
        }else if(e.getSource()==view.btnCancelar){
            view.dispose();
        }
    }
    
}
