package controller;

import com.mongodb.client.MongoCollection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ConexionBD;
import model.Model_Inventario;
import org.bson.Document;
import view.Inventario;

public class Controller_Inventario implements ActionListener{
    DefaultTableModel dtm;
    public Inventario view;
    public Model_Inventario model;
    MongoCollection<Document> collection;
    private ConexionBD conexionBD;
    private int filaSeleccionada;

    public Controller_Inventario(Inventario view, Model_Inventario model, MongoCollection<Document> collection) {
        this.view = view;
        this.model = model;
        this.collection = collection;
        this.conexionBD = ConexionBD.getInstance();
        this.view.btnBuscar.addActionListener(this);
        this.view.btnCancelar.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);
        leerDatos();
    }
    public void leerDatos(){
        collection = conexionBD.database.getCollection("Inventario");
        
        List<Document> documentos = collection.find().into(new ArrayList<>());
        String[] cabeceras = {"Nombre", "Codigo", "Estado","Fecha", "Observacion"};
        dtm = new DefaultTableModel(cabeceras, 0);
        view.tblDatos.setModel(dtm);
        for (Document doc : documentos) {
            Vector<Object> row = new Vector<>();
            row.add(doc.get("Nombre"));
            row.add(doc.get("Codigo"));
            row.add(doc.get("Estado"));
            row.add(doc.get("Fecha"));
            row.add(doc.get("Observacion"));
            dtm.addRow(row);
        }
        view.tblDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filaSeleccionada = view.tblDatos.getSelectedRow();
                System.out.println("----->filas seleccionada \n"
                        + "index -->#"+filaSeleccionada);
            }
            
        });
    }
    public void Buscar(){
    }
    public void Eliminar() {
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(view, "Debe seleccionar una fila para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int opcion = JOptionPane.showConfirmDialog(view, "¿Está seguro de eliminar esta fila?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            // Verificar si el valor en la celda es nulo antes de llamar a toString()
            Object valor = dtm.getValueAt(filaSeleccionada, 2);
            if (valor == null) {
                JOptionPane.showMessageDialog(view, "El valor del código en la fila seleccionada es nulo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String codigo = valor.toString();
            dtm.removeRow(filaSeleccionada);
            // Eliminar de la base de datos
            Document filtro = new Document("Codigo", codigo);
            conexionBD.setCollName("Inventario"); // Configurar colección correcta
            conexionBD.eliminarRegistro(filtro);
        }
    }
    private boolean validarFecha() {
        Date fechaSeleccionadaIni = view.dateInventarioIni.getDate();
        Date fechaSeleccionadafin = view.dateInventarioFin.getDate();
        Date fechaActual = new Date(); // Obtiene la fecha actual
        long unDiaMilisegundos = 24 * 60 * 60 * 1000; // Un día en milisegundos
        // Validar que la fecha de inicio no sea nula
        if (fechaSeleccionadaIni == null) {
            view.errFechaini.setText("**Fecha de inicio obligatoria");
            return false;
        }
        // Validar que la fecha de fin no sea nula
        if (fechaSeleccionadafin == null) {
            view.errFechafin.setText("**Fecha de fin obligatoria");
            return false;
        }
        // Validar que la fecha seleccionada no sea mayor a un día después de la actual
        if (fechaSeleccionadaIni.getTime() > (fechaActual.getTime() + unDiaMilisegundos)) {
            view.errFechaini.setText("**La fecha no puede ser mayor a un día después de la actual");
            return false;
        } else if (fechaSeleccionadaIni.getTime() > fechaSeleccionadafin.getTime()) {
            view.errFechaini.setText("**La fecha no puede ser mayor a la fecha de fin");
            return false;
        } else {
            view.errFechaini.setText(""); // Limpia el error si la validación es correcta
        }
        if (fechaSeleccionadafin.getTime() > (fechaActual.getTime() + unDiaMilisegundos)) {
            view.errFechafin.setText("**La fecha no puede ser mayor a un día después de la actual");
            return false;
        } else if (fechaSeleccionadafin.getTime() < fechaSeleccionadaIni.getTime()) {
            view.errFechafin.setText("**La fecha no puede ser menor a la de inicio");
            return false;
        } else {
            view.errFechafin.setText(""); // Limpia el error si la validación es correcta
        }
        return true;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.btnCancelar){
            view.dispose();
        }else if(e.getSource()==view.btnBuscar){
            Buscar();
        }else if(e.getSource()==view.btnEliminar){
            Eliminar();
        }
    }
    
}
