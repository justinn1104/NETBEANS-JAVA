package controller;

import model.ConexionBD;
//import com.mongodb.DBCollection;
import com.mongodb.client.MongoCollection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ModeloMembres;
import org.bson.Document;
import view.Membres;

public class ControladorMembres implements ActionListener{
    DefaultTableModel dtm;
    Membres View;
    ModeloMembres Model;
    MongoCollection<Document> collection;
    private ConexionBD conexionBD;
   // private DBCollection colec;

    public ControladorMembres(Membres View, ModeloMembres Model, MongoCollection<Document> collection) {
        this.View = View;
        this.Model = Model;
        this.collection = collection;
        this.conexionBD = ConexionBD.getInstance();
        this.View.errHorario.setText("");
        this.View.errPago1.setText("");
        this.View.errTipo.setText("");
        this.View.btnBuscar.addActionListener(this);
        this.View.btnCancelar.addActionListener(this);
        this.View.btnEliminar.addActionListener(this);
        this.View.btnLeer.addActionListener(this);
        this.View.btnModificar.addActionListener(this);
        leerDatos();
    }
    //mostrar los datos de mi db en la tabla
    public void leerDatos(){
        collection = conexionBD.database.getCollection("Clientes");
        List<Document> documentos = collection.find().into(new ArrayList<>());
        String[] cabeceras = {"Nombre", "Genero", "Cedula","Tipo", "Horario", "Pago", "Suplementos"};
        dtm = new DefaultTableModel(cabeceras, 0);
        View.tbDatos.setModel(dtm);
        for (Document doc : documentos) {
            Vector<Object> row = new Vector<>();
            row.add(doc.get("Nombre"));
            row.add(doc.get("Genero"));
            row.add(doc.get("Cedula"));
            row.add(doc.get("Tipo"));
            row.add(doc.get("Horario"));
            row.add(doc.get("Pago"));
            row.add(doc.get("Suplementos"));
            dtm.addRow(row);
        }
        View.tbDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DatosFilaSeleccionada();
            }
            
        });
    }
    private void DatosFilaSeleccionada() {
        int filaSeleccionada = View.tbDatos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            // Obtener los datos de la fila seleccionada
            String Nombre = dtm.getValueAt(filaSeleccionada, 0).toString();
            String Genero = dtm.getValueAt(filaSeleccionada, 1).toString();           
            String tipo = dtm.getValueAt(filaSeleccionada, 3).toString();
            String horario = dtm.getValueAt(filaSeleccionada, 4).toString();
            String pago = dtm.getValueAt(filaSeleccionada, 5).toString();
            String Suplementos = dtm.getValueAt(filaSeleccionada, 6).toString();
            int a = Integer.parseInt(Suplementos);
            View.txtName.setText(Nombre);
            View.txtGenero.setText(Genero);            
            View.comboTipo.setSelectedItem(tipo);
            View.comboPago.setSelectedItem(pago);            
            for(int i=0; i<=a ; i++){
                switch (i) {
                    case 1:
                        View.check1.setSelected(true);
                        break;
                    case 2:
                        View.check2.setSelected(true);
                        break;
                    case 3:
                        View.check3.setSelected(true);
                        break;
                    case 4:
                        View.check4.setSelected(true);
                        break;
                    case 5:
                        View.check5.setSelected(true);
                        break;
                    case 6:
                        View.check6.setSelected(true);
                        break;
                    case 0:
                        View.check1.setSelected(false);
                        View.check2.setSelected(false);
                        View.check3.setSelected(false);
                        View.check4.setSelected(false);
                        View.check5.setSelected(false);
                        View.check6.setSelected(false);
                        break;
                    default:
                }
            }
            //view.jComboBox1.setSelectedItem(puesto);
            if (horario.equals("Matutino")) {
                View.radioMatu.setSelected(true);
            } else if (horario.equals("Vespertino")) {
                View.radioVespe.setSelected(true);
            }
            View.txtGenero.setEnabled(false);
            View.txtName.setEnabled(false);
        }
    }
    public boolean validarDatos(){
        boolean valid=true;
        String horario="",suplemento="";
        int totalSuple=0;
        boolean band =!(View.radioMatu.isSelected()||View.radioVespe.isSelected());    
        // Validación de horario
        if(band){
            View.errHorario.setText("**Campo Obligatorio");
            valid = false;
        }else{
            View.errHorario.setText("");
        }
        if("Seleccionar".equals(View.comboTipo.getSelectedItem())){//Seleccionar
            View.errTipo.setText("**Seleccionar Opcion");
            valid = false;
        }else {
            View.errTipo.setText("");
        }
        if("Seleccionar".equals(View.comboPago.getSelectedItem())){//Seleccionar
            View.errPago1.setText("**Seleccionar Opcion");
            valid = false;
        }else {
            View.errPago1.setText("");
        }
        totalSuple=0;
        if(View.check1.isSelected()){
            totalSuple= 1 + totalSuple;
        }else{ 
            totalSuple=totalSuple;
        }
        if(View.check2.isSelected()){
            totalSuple= 1 + totalSuple;
        }else{
            totalSuple=totalSuple;
        }
        if(View.check3.isSelected()){
            totalSuple= 1 + totalSuple;
        }else{
            totalSuple=totalSuple;
        }
        if(View.check4.isSelected()){
            totalSuple= 1 + totalSuple;
        }else{
            totalSuple=totalSuple;
        }
        if(View.check5.isSelected()){
            totalSuple= 1 + totalSuple;
        }else{
            totalSuple=totalSuple;
        }
        if(View.check6.isSelected()){
            totalSuple= 1 + totalSuple;
        }else{
            totalSuple=totalSuple;
        }
        suplemento = Integer.toString(totalSuple);
        if (valid) {
            if(View.radioMatu.isSelected()){
                horario="Matutino";
            }else{
                horario="Vespertino";
            }
            Model.setSuplements(suplemento);
            Model.setTipo((String) View.comboTipo.getSelectedItem());
            Model.setPago((String) View.comboPago.getSelectedItem());
            Model.setHorario(horario);                     
        }
        
        return valid;
    }
    public void ModificarDatos(){              
        Document filtroCedula = new Document("Tipo", Model.getTipo());   
        Document doc = new Document("Horario", Model.getHorario())
                       .append("Pago", Model.getPago())
                       .append("Suplementos", Model.getSuplements());
        conexionBD.setCollName("Clientes"); // Configurar colección correcta
        conexionBD.actualizarRegistro(filtroCedula , doc);        
        JOptionPane.showMessageDialog(null, "Cliente Modificado exitosamente.");                
    }
    public void modificar(){
        int filaSeleccionada = View.tbDatos.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(View, "Debe seleccionar una fila para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean valida = validarDatos();
        if(valida){
            ModificarDatos();
            System.out.println("Retorno de model.suple -->"+Model.getSuplements());
        }else{
            System.out.println("Error no modifica retorno false");
        }
        leerDatos();
    }
    private void LimpiarTab() {
        DefaultTableModel modelo = (DefaultTableModel) View.tbDatos.getModel();
        modelo.setRowCount(0); 
    }
    private void limpiarCampos() {
        View.comboTipo.setSelectedItem("");
        View.comboPago.setSelectedItem("");
        View.radioMatu.setSelected(false);
        View.radioVespe.setSelected(false);
        View.check1.setSelected(false);
        View.check2.setSelected(false);
        View.check3.setSelected(false);
        View.check4.setSelected(false);
        View.check5.setSelected(false);
        View.check6.setSelected(false);
    }  
    public void Buscar() {
        String cedula = null;
        boolean cedulaValida = false;
        while (!cedulaValida) {
            cedula = JOptionPane.showInputDialog(View, "Ingrese la cédula a buscar:");
            if (cedula == null) {
                return; 
            } else if (cedula.isEmpty() || cedula.length() != 10) {
                JOptionPane.showMessageDialog(View, "La cédula debe tener 10 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                cedulaValida = true;
            }
        }
        LimpiarTab();
        Document filtro = new Document("Cedula", cedula);
        conexionBD.setCollName("Clientes"); // Configurar colección correcta
        ArrayList<Document> resultados = conexionBD.buscarRegistro(filtro);
        DefaultTableModel modelo = (DefaultTableModel) View.tbDatos.getModel();
        modelo.setRowCount(0);
        if (resultados != null && !resultados.isEmpty()) {
            for (Document doc : resultados) {
                modelo.addRow(new Object[]{
                        doc.getString("Nombre"),
                        doc.getString("Genero"),
                        doc.getString("Cedula"),
                        doc.getString("Tipo"),
                        doc.getString("Horario"),
                        doc.getString("Pago"),
                        doc.getString("Suplementos")
                });
            }
        } else {
            JOptionPane.showMessageDialog(View, "Usuario no existente.", "Búsqueda", JOptionPane.WARNING_MESSAGE);
        }
    }
    public void Eliminar() {
        int filaSeleccionada = View.tbDatos.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(View, "Debe seleccionar una fila para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int opcion = JOptionPane.showConfirmDialog(View, "¿Está seguro de eliminar esta fila?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            String cedula = dtm.getValueAt(filaSeleccionada, 2).toString();
            dtm.removeRow(filaSeleccionada);
            // Eliminar de la base de datos
            Document filtro = new Document("Cedula", cedula);
            conexionBD.setCollName("Clientes"); // Configurar colección correcta
            conexionBD.eliminarRegistro(filtro);
        }        
    }                    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==View.btnCancelar){
            View.dispose();
        }else if(e.getSource()==View.btnBuscar){
            Buscar();
        }else if(e.getSource()==View.btnEliminar){
            Eliminar();
        }else if(e.getSource()==View.btnLeer){
            leerDatos();
        }else if(e.getSource()==View.btnModificar){
            modificar();
            limpiarCampos();
        }
    }
    
}
