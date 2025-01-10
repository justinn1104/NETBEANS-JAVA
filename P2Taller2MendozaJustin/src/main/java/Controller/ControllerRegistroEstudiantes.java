package Controller;

import Model.Estudiante;
import Model.ModelRegistroEstudiantes;
import Model.mongoDB;
import View.RegistroEstudiantes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

public class ControllerRegistroEstudiantes implements ActionListener{
    ModelRegistroEstudiantes model;
    RegistroEstudiantes view;
    private mongoDB mongo = new mongoDB();

    public ControllerRegistroEstudiantes(ModelRegistroEstudiantes model, RegistroEstudiantes view) {
        this.model = model;
        this.view = view;
        this.view.btnLimpiar.addActionListener(this);
        this.view.btnGuardar.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);
        this.view.btnBuscar.addActionListener(this);
        this.view.btnActualizar.addActionListener(this);
        this.view.btnRefrescar.addActionListener(this);
        mongo.setCollectionName("Estudiantes");
        mongo.createConnection();
        this.limpiarCampos();
        this.cargarDatosTabla();
        // Agregar un ListSelectionListener a la tabla
        view.tbDatos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Comprobar que la selección no esta ajustando
                    int row = view.tbDatos.getSelectedRow(); // Obtener la fila seleccionada
                    if (row != -1) { // Verificar que se ha seleccionado una fila
                        // Obtener los valores de la fila seleccionada
                        String cedula = view.tbDatos.getValueAt(row, 0).toString();
                        String nombre = view.tbDatos.getValueAt(row, 1).toString();
                        String apellido = view.tbDatos.getValueAt(row, 2).toString();
                        String carrera = view.tbDatos.getValueAt(row, 3).toString();
                        String creditosString = view.tbDatos.getValueAt(row, 4).toString(); // Recuperar el valor como String
                        int creditos = 0;
                        try {
                            creditos = Integer.parseInt(creditosString); // Intentar convertirlo a Integer
                        } catch (NumberFormatException ex) {
                            System.out.println("Error al convertir los créditos a entero: " + ex.getMessage());
                            creditos = 0;
                        }
                        String promedio = view.tbDatos.getValueAt(row, 5).toString();
                        //column 6 es de beca
                        String nivel = view.tbDatos.getValueAt(row, 7).toString();
                        //String pagoTotal = vista.tbDatos.getValueAt(row, 8).toString();
                        // Colocar los valores en los campos de texto
                        view.txtCedula.setText(cedula);
                        view.txtNombre.setText(nombre);
                        view.txtApellido.setText(apellido);
                        view.boxCarrera.setSelectedItem(carrera);
                        view.spnCreditos.setValue(creditos);
                        view.txtPromedio.setText(promedio);
                        if (nivel.equals("Pregrado")) {
                            view.buttonGroup1.setSelected(view.radPregrado.getModel(), true);
                        } else if (nivel.equals("Posgrado")) {
                            view.buttonGroup1.setSelected(view.radPosgrado.getModel(), true);
                        }
                        view.txtCedula.setEnabled(false);
                    }
                }
            }
        });
    }
    
    // Metodo para cargar los datos de MongoDB en la Tabla
    public void cargarDatosTabla() {
        DefaultTableModel tdm = mongo.cargarDataTable("Estudiantes");
        view.tbDatos.setModel(tdm);
        view.txtCedula.setEnabled(true);
    }
    
    //Limpia todos los datos de mi Tbla
    private void LimpiarTab() {
        DefaultTableModel modelo = (DefaultTableModel) view.tbDatos.getModel();
        modelo.setRowCount(0); 
    }
    
    public void iniciarVista(){
        view.setVisible(true);
    }
    
    public void guardarEstudiante() {
        // Obtener datos de la vista y setear en el modelo
        model.setNombre(view.txtNombre.getText());
        model.setApellido(view.txtApellido.getText());
        model.setCedula(view.txtCedula.getText());
        model.setCarrera((String) view.boxCarrera.getSelectedItem());
        model.setCreditos(view.spnCreditos.getValue().toString());
        model.setPromedio(view.txtPromedio.getText());
        if (view.radPregrado.isSelected()) {
            model.setNivel("Pregrado");
        } else if (view.radPosgrado.isSelected()) {
            model.setNivel("Posgrado");
        } else {
            model.setNivel(null); // Ninguna opción seleccionada
        }
        // Validar datos
        boolean rasultado = model.validarDatos(view);
        if (rasultado) {
            // Si la validación es exitosa, guardar en la base de datos
            // Crear documento común para la colección "Usuarios"
            Estudiante userE = new Estudiante(model.getPromedio(), "", model.getCreditos(), "", model.getNombre(), model.getApellido(), model.getCedula(), model.getNivel());
            String tipoBeca = userE.evaluarBeca(model.getPromedio());
            double total = userE.calcularPago(tipoBeca, model.getCreditos());
            userE.setBecario(tipoBeca);
            userE.setTotal(String.valueOf(total));
            userE.generarDatos();
            Document doc = new Document("Cedula", model.getCedula())
                .append("Nombre", model.getNombre())
                .append("Apellido", model.getApellido())
                .append("Carrera", model.getCarrera())
                .append("Creditos", model.getCreditos())
                .append("Promedio", model.getPromedio())
                .append("Becario", userE.getBecario())
                .append("Nivel", model.getNivel())
                .append("Pago-Total", userE.getTotal());
            // Insertar en la colección "Estudiantes"
            mongo.createDocument("Estudiantes", doc);
            userE.generarDatos();
            System.out.println("Estudiante guardado exitosamente");
            limpiarCampos();
        } else {
            System.out.println("Error al guardar el estudiante en la base de datos");
        }
    }
    
    public void actualizarEstudiante(){
        // Obtener datos de la vista y setear en el modelo
        model.setNombre(view.txtNombre.getText());
        model.setApellido(view.txtApellido.getText());
        model.setCedula(view.txtCedula.getText());
        model.setCarrera((String) view.boxCarrera.getSelectedItem());
        model.setCreditos(view.spnCreditos.getValue().toString());
        model.setPromedio(view.txtPromedio.getText());
        if (view.radPregrado.isSelected()) {
            model.setNivel("Pregrado");
        } else if (view.radPosgrado.isSelected()) {
            model.setNivel("Posgrado");
        } else {
            model.setNivel(null); // Ninguna opción seleccionada
        }
        // Validar datos
        boolean rasultado = model.validarDatos(view);
        if(rasultado){
            // Si la validación es exitosa, guardar en la base de datos
            // Crear documento común para la colección "Usuarios"
            Estudiante userE = new Estudiante(model.getPromedio(), "", model.getCreditos(), "", model.getNombre(), model.getApellido(), model.getCedula(), model.getNivel());
            String tipoBeca = userE.evaluarBeca(model.getPromedio());
            double total = userE.calcularPago(tipoBeca, model.getCreditos());
            userE.setBecario(tipoBeca);
            userE.setTotal(String.valueOf(total));
            userE.generarDatos();
            Document filtroCedula = new Document("Cedula", model.getCedula());   
            Document doc1 = new Document("Cedula", model.getCedula())
                .append("Nombre", model.getNombre())
                .append("Apellido", model.getApellido())
                .append("Carrera", model.getCarrera())
                .append("Creditos", model.getCreditos())
                .append("Promedio", model.getPromedio())
                .append("Becario", userE.getBecario())
                .append("Nivel", model.getNivel())
                .append("Pago-Total", userE.getTotal());
            mongo.updateDocument("Estudiantes", filtroCedula, doc1);
            userE.generarDatos();
            limpiarCampos();
        }else{
            System.out.println("\n\n--->>>CAMPOS INVALIDOS<<<---\n\n");
        }
    }
    
    public void buscarEstudiante() {
        String cedula = null;
        boolean cedulaValida = false;
        while (!cedulaValida) {
            cedula = JOptionPane.showInputDialog(view, "Ingrese la cédula a buscar:");
            if (cedula == null) {
                return; 
            } else if (cedula.isEmpty() || cedula.length() != 10) {
                JOptionPane.showMessageDialog(view, "La cédula debe tener 10 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                cedulaValida = true;
            }
        }
        LimpiarTab();
        Document filtro = new Document("Cedula", cedula);
        ArrayList<Document> resultados = mongo.searchDocument("Estudiantes", filtro);
        DefaultTableModel dtm = (DefaultTableModel) view.tbDatos.getModel();
        dtm.setRowCount(0);
        if (resultados != null && !resultados.isEmpty()) {
            for (Document doc : resultados) {
                    Object[] row = {
                    doc.get("Cedula"),
                    doc.get("Nombre"), 
                    doc.get("Apellido"),
                    doc.get("Carrera"),
                    doc.get("Creditos"),
                    doc.get("Promedio"),
                    doc.get("Becario"),
                    doc.get("Nivel"),
                    doc.get("Pago-Total"),
                };
                dtm.addRow(row);
                limpiarCampos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no existente.", "Búsqueda", JOptionPane.WARNING_MESSAGE);
        }
    }
    public void eliminarEstudiante() {
        int filaSeleccionada = view.tbDatos.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) view.tbDatos.getModel();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(view, "Debe seleccionar una fila para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int opcion = JOptionPane.showConfirmDialog(view, "¿Está seguro de eliminar este cliente?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            String cedula = dtm.getValueAt(filaSeleccionada, 0).toString();
            dtm.removeRow(filaSeleccionada);
            // Eliminar de la base de datos
            Document filtro = new Document("Cedula", cedula);
            mongo.deleteDocument("Estudiantes", filtro);
            limpiarCampos();
        }        
    }

    private void limpiarCampos() {
        view.txtNombre.setText("");
        view.txtApellido.setText("");
        view.txtCedula.setText("");
        view.boxCarrera.setSelectedIndex(0);
        view.spnCreditos.setValue(0);
        view.txtPromedio.setText("");
        view.buttonGroup1.clearSelection();

        // Ocultar mensajes de error
        view.errNombre.setVisible(false);
        view.errApellido.setVisible(false);
        view.errCedula.setVisible(false);
        view.errCarrera.setVisible(false);
        view.errCreditos.setVisible(false);
        view.errPromedio.setVisible(false);
        view.errNivel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.btnLimpiar){
            limpiarCampos();
        }else if(e.getSource()==view.btnGuardar){
            guardarEstudiante();
        }else if(e.getSource()==view.btnEliminar){
            eliminarEstudiante();
        }else if(e.getSource()==view.btnBuscar){
            buscarEstudiante();
        }else if(e.getSource()==view.btnActualizar){
            actualizarEstudiante();
        }
        else if(e.getSource()==view.btnRefrescar){
            limpiarCampos();
            cargarDatosTabla();
        }
    }
}
