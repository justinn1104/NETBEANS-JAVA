package controller;

import com.mongodb.client.MongoCollection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import model.ModeloClients;
import org.bson.Document;
import view.Clients;

public class ControladorClients implements ActionListener {
    private DefaultTableModel dtm;
    Clients view;
    private ModeloClients model;
    private IRepositorioClientes repositorioClientes;

  public ControladorClients(Clients view, ModeloClients model, IRepositorioClientes repositorioClientes) {
    this.model = model;
    this.view = view;
    this.repositorioClientes = repositorioClientes; // Inicializa repositorioClientes correctamente

    // Limpia los campos de error en la vista
    this.view.errName.setText("");
    this.view.errLastName.setText("");
    this.view.errDireccion.setText("");
    this.view.errDni.setText("");
    this.view.errTelefono.setText("");
    this.view.errGenero.setText("");

    // Configura los oyentes de acción
    this.view.btnBuscar.addActionListener(this);
    this.view.btnLeer.addActionListener(this);
    this.view.btnCancelar.addActionListener(this);
    this.view.btnEliminar.addActionListener(this);
    this.view.btnModificar.addActionListener(this);

    // Hace visible la vista
    view.setVisible(true);

    // Lee los datos iniciales
    leerDatos();
}
    public boolean validar() {
        String nom = view.txtNombres.getText();
        String apell = view.txtApellidos.getText();
        String cedula = view.txtCedula.getText();
        String celular = view.txtTelefono.getText();
        String direccion = view.txtDireccion.getText();
        String genero = "";
        boolean valid = true;
        boolean band1 = !(view.radioFeme.isSelected() || view.radioMascu.isSelected());

        // Validación de género
        if (band1) {
            view.errGenero.setText("**Campo Obligatorio");
            valid = false;
        } else {
            view.errGenero.setText("");
        }
        // Validación de nombres
        if (nom.isEmpty()) {
            view.errName.setText("**Campo Obligatorio");
            valid = false;
        } else {
            view.errName.setText("");
        }
        // Validación de dirección
        if (direccion.isEmpty()) {
            view.errDireccion.setText("**Campo Obligatorio");
            valid = false;
        } else {
            view.errDireccion.setText("");
        }
        // Validación de apellidos
        if (apell.isEmpty()) {
            view.errLastName.setText("**Campo Obligatorio");
            valid = false;
        } else {
            view.errLastName.setText("");
        }
        // Validación de teléfono
        if (celular.isEmpty()) {
            view.errTelefono.setText("**Campo Obligatorio");
            valid = false;
        } else if (celular.length() != 10) {
            view.errTelefono.setText("**Obligatorio 10 dígitos");
            valid = false;
        } else if (!"09".equals(celular.substring(0, 2))) {
            view.errTelefono.setText("Formato *09########");
            valid = false;
        } else {
            view.errTelefono.setText("");
        }
        if (valid) {
            if (view.radioMascu.isSelected()) {
                genero = "Masculino";
            } else {
                genero = "Femenino";
            }
            model.setCedula(cedula);
            model.setNomb(nom);
            model.setApell(apell);
            model.setDire(direccion);
            model.setCelular(celular);
            model.setEdad(view.spinnerEdad.getValue().toString());
            model.setGenero(genero);
        }
        return valid;
    }

    public void ModificarCliente() {
        Document filtroCedula = new Document("Cedula", model.cedula);
        Document doc = new Document("Nombre", model.nomb)
                .append("Apellido", model.apell)
                .append("Direccion", model.dire)
                .append("Telefono", model.celular)
                .append("Edad", model.edad)
                .append("Genero", model.genero);
        repositorioClientes.actualizarRegistro(filtroCedula, doc);
        JOptionPane.showMessageDialog(null, "Cliente Modificado exitosamente.");
    }
    //ENCAPSULAMIENTO
    private void LimpiarTab() {
        DefaultTableModel modelo = (DefaultTableModel) view.tblDatos.getModel();
        modelo.setRowCount(0);
    }
//ENCAPSULAMIENTO
    private void limpiarCampos() {
        view.txtNombres.setText("");
        view.txtApellidos.setText("");
        view.txtCedula.setText("");
        view.txtDireccion.setText("");
        view.txtTelefono.setText("");
        view.radioFeme.setSelected(false);
        view.radioMascu.setSelected(false);
    }

    protected void Buscar() {
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
        ArrayList<Document> resultados = (ArrayList<Document>) repositorioClientes.buscarRegistro(filtro);
        DefaultTableModel modelo = (DefaultTableModel) view.tblDatos.getModel();
        modelo.setRowCount(0);
        if (resultados != null && !resultados.isEmpty()) {
            for (Document doc : resultados) {
                modelo.addRow(new Object[]{
                        doc.getString("Nombre"),
                        doc.getString("Apellido"),
                        doc.getString("Cedula"),
                        doc.getString("Direccion"),
                        doc.getString("Telefono"),
                        doc.getString("Edad"),
                        doc.getString("Genero")
                });
            }
        } else {
            JOptionPane.showMessageDialog(view, "Usuario no existente.", "Búsqueda", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void leerDatos() {
        List<Document> documentos = repositorioClientes.obtenerTodosLosRegistros();
        String[] cabeceras = {"Nombre", "Apellido", "Cedula", "Direccion", "Telefono", "Edad", "Genero"};
        dtm = new DefaultTableModel(cabeceras, 0);
        view.tblDatos.setModel(dtm);
        for (Document doc : documentos) {
            Vector<Object> row = new Vector<>();
            row.add(doc.get("Nombre"));
            row.add(doc.get("Apellido"));
            row.add(doc.get("Cedula"));
            row.add(doc.get("Direccion"));
            row.add(doc.get("Telefono"));
            row.add(doc.get("Edad"));
            row.add(doc.get("Genero"));
            dtm.addRow(row);
        }
        view.tblDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DatosFilaSeleccionada();
            }
        });
    }

    public void Eliminar() {
        int filaSeleccionada = view.tblDatos.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(view, "Debe seleccionar una fila para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int opcion = JOptionPane.showConfirmDialog(view, "¿Está seguro de eliminar esta fila?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            String cedula = dtm.getValueAt(filaSeleccionada, 2).toString();
            dtm.removeRow(filaSeleccionada);
            Document filtro = new Document("Cedula", cedula);
            repositorioClientes.eliminarRegistro(filtro);
        }
    }

    public void modificar() {
        int filaSeleccionada = view.tblDatos.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(view, "Debe seleccionar una fila para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean valida = validar();
        if (valida) {
            ModificarCliente();
        }
        leerDatos();
    }

    private void DatosFilaSeleccionada() {
        int filaSeleccionada = view.tblDatos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String Nombre = dtm.getValueAt(filaSeleccionada, 0).toString();
            String Apellido = dtm.getValueAt(filaSeleccionada, 1).toString();
            String Cedula = dtm.getValueAt(filaSeleccionada, 2).toString();
            String Direccion = dtm.getValueAt(filaSeleccionada, 3).toString();
            String Telefono = dtm.getValueAt(filaSeleccionada, 4).toString();
            String Edad = dtm.getValueAt(filaSeleccionada, 5).toString();
            String Genero = dtm.getValueAt(filaSeleccionada, 6).toString();
            int a = Integer.parseInt(Edad);
            view.txtNombres.setText(Nombre);
            view.txtApellidos.setText(Apellido);
            view.txtCedula.setText(Cedula);
            view.txtDireccion.setText(Direccion);
            view.txtTelefono.setText(Telefono);
            view.spinnerEdad.setValue(a);
            if (Genero.equals("Masculino")) {
                view.radioMascu.setSelected(true);
            } else if (Genero.equals("Femenino")) {
                view.radioFeme.setSelected(true);
            }
            view.txtCedula.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnCancelar) {
            view.dispose();
        } else if (e.getSource() == view.btnBuscar) {
            Buscar();
        } else if (e.getSource() == view.btnLeer) {
            leerDatos();
        } else if (e.getSource() == view.btnEliminar) {
            Eliminar();
        } else if (e.getSource() == view.btnModificar) {
            modificar();
        }
    }
}
