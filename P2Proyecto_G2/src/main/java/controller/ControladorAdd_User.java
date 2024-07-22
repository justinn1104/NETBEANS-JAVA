package controller;

import ConexionBD.ConexionBD;
import com.mongodb.client.MongoCollection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.ModeloAdd_User;
import model.ModeloClients;
import org.bson.Document;
import view.Add_User;
import view.Clients;
import view.Principal;

public class ControladorAdd_User implements ActionListener {
    public Add_User view;
    private ModeloAdd_User model;
    MongoCollection<Document> collection;
    private ConexionBD conexionBD;

    public ControladorAdd_User(Add_User view, ModeloAdd_User model, MongoCollection<Document> collection1) {
        this.view = view;
        this.model = model;
        this.collection = collection1;
        conexionBD = new ConexionBD();
        this.view.btnGuardar.addActionListener(this);
        this.view.btnCancelar.addActionListener(this);
        this.view.errName.setText("");
        this.view.errLastName.setText("");
        this.view.errDireccion.setText("");
        this.view.errDni.setText("");
        this.view.errTelefono.setText("");
        this.view.errGenero.setText("");
        this.view.errHorario.setText("");
        this.view.btnGuardar.addActionListener(this);
        this.view.btnCancelar.addActionListener(this);        
    }

    public void validar(){     
        String nom = view.txtNombres.getText();
        String apell = view.txtApellidos.getText();
        String cedula = view.txtCedula.getText();
        String celular = view.txtTelefono.getText();
        String direccion = view.txtDireccion.getText();
        String genero="", horario="",suplemento="";
        int totalSuple=0;
        boolean valid = true;
        boolean band1 =!(view.radioFeme.isSelected()||view.radioMascu.isSelected());
        boolean band2 =!(view.radioMatu.isSelected()||view.radioVespe.isSelected());
        // Validación de genero
        if(band1){
            view.errGenero.setText("**Campo Obligatorio");
            valid = false;
        }else{
            view.errGenero.setText("");
        }
        // Validación de horario
        if(band2){
            view.errHorario.setText("**Campo Obligatorio");
            valid = false;
        }else{
            view.errHorario.setText("");
        }
        // Validación de nombres
        if (nom.isEmpty()) {
            view.errName.setText("**Campo Obligatorio");
            valid = false;
        } else {
            view.errName.setText("");
        }
        // Validación de diereccion
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
        // Validación de cédula
        if (cedula.isEmpty()) {
            view.errDni.setText("**Campo Obligatorio");
            valid = false;
        } else if (cedula.length() != 10) {
            view.errDni.setText("**Obligatorio 10 dígitos");
            valid = false;
        } else {
            view.errDni.setText("");
        }
        // Validación de teléfono
        if (celular.isEmpty()) {
            view.errTelefono.setText("**Campo Obligatorio");
            valid = false;
        } else if (celular.length() != 10) {
            view.errTelefono.setText("**Obligatorio 10 dígitos");
            valid = false;
        } else if(!"09".equals(celular.substring(0, 2))){         
            view.errTelefono.setText("Formato *09########");
            valid = false;
        }else{
            view.errTelefono.setText("");
        }
        totalSuple=0;
        if(view.checkCrea.isSelected()){
            totalSuple= 1 + totalSuple;
        }else{ 
            totalSuple=totalSuple;
        }
        if(view.checkPre.isSelected()){
            totalSuple= 1 + totalSuple;
        }else{
            totalSuple=totalSuple;
        }
        if(view.checkProte.isSelected()){
            totalSuple= 1 + totalSuple;
        }else{
            totalSuple=totalSuple;
        }
        if(view.checkL_Arg.isSelected()){
            totalSuple= 1 + totalSuple;
        }else{
            totalSuple=totalSuple;
        }
        if(view.checkL_Citru.isSelected()){
            totalSuple= 1 + totalSuple;
        }else{
            totalSuple=totalSuple;
        }
        if(view.checkQuemador.isSelected()){
            totalSuple= 1 + totalSuple;
        }else{
            totalSuple=totalSuple;
        }     
        if (valid) {
            if(view.radioMascu.isSelected()){
                genero="Masculino";
            }else{
                genero="Femenino";
            }
            if(view.radioMatu.isSelected()){
                horario="Matutino";
            }else{
                horario="Vespertino";
            }
            suplemento = Integer.toString(totalSuple);
            model.setSuple(suplemento);
            model.setNomb(nom);
            model.setApell(apell);
            model.setCedula(cedula);
            model.setCelular(celular);
            model.setEdad(view.spinnerEdad.getValue().toString());
            model.setTipo((String) view.comboTipo.getSelectedItem());
            model.setPago((String) view.comboPago.getSelectedItem());
            model.setSuple(suplemento);
            model.setGenero(genero);
            model.setDire(direccion);
            guardarCliente();            
        }       
    }
    public void guardarCliente(){      
        // Verificar si la cédula ya existe
        Document filtroCedula = new Document("Cedula", model.cedula);
        if (!conexionBD.buscarRegistro("RegistroUsuario", "Clientes", filtroCedula).isEmpty()) {
            view.errDni.setText("**Cédula ya existente");
            return;
        }
        Document doc = new Document("Nombre", model.nomb)
                       .append("Apellido", model.apell)
                       .append("Direccion", model.dire)
                       .append("Cedula", model.cedula)
                       .append("Telefono", model.celular)
                       .append("Edad", model.edad)
                       .append("Genero", model.genero)
                       .append("Tipo", model.tipo)
                       .append("Horario", model.horario)
                       .append("Pago", model.pago)
                       .append("Suplementos", model.suple);
        conexionBD.insertarRegistro("RegistroUsuario", "Clientes", doc);        
        JOptionPane.showMessageDialog(null, "Cliebte registrado exitosamente.");        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnGuardar) {
            validar();
        } else if (e.getSource() == view.btnCancelar) {
            view.dispose();
        }
    }
}

