package controller;

import ConexionBD.ConexionBD;
import com.mongodb.client.MongoCollection;
import d.p2proyecto_g2.Efectivo;
import d.p2proyecto_g2.Forma_Pago;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import model.ModeloAdd_User;
import org.bson.Document;
import view.Add_User;

public class ControladorAdd_User implements ActionListener {
    public Add_User view;
    private ModeloAdd_User model;
    MongoCollection<Document> collection;
    private ConexionBD conexionBD;
    public static Forma_Pago interf = new Efectivo(0,0,"","","",0);
    public ControladorAdd_User(Add_User view, ModeloAdd_User model, MongoCollection<Document> collection1) {
        this.view = view;
        this.model = model;
        this.collection = collection1;
        this.conexionBD = ConexionBD.getInstance();
        this.view.errName.setText("");
        this.view.errLastName.setText("");
        this.view.errDireccion.setText("");
        this.view.errDni.setText("");
        this.view.errTelefono.setText("");
        this.view.errGenero.setText("");
        this.view.errHorario.setText("");
        this.view.errTipo.setText("");
        this.view.errPago1.setText("");
        this.view.btnGuardar.addActionListener(this);
        this.view.btnCancelar.addActionListener(this);        
        this.view.btnGuardar.addActionListener(this);
        this.view.btnCancelar.addActionListener(this);        
    }
    public void limpiarCampos(){                
        view.txtNombres.setText("");
        view.txtApellidos.setText("");
        view.txtCedula.setText("");
        view.txtTelefono.setText("");
        view.txtDireccion.setText("");
        view.radioFeme.setSelected(false);
        view.radioMascu.setSelected(false);
        view.radioMatu.setSelected(false);
        view.radioVespe.setSelected(false);
        view.comboTipo.setSelectedItem("Seleccionar");
        view.comboPago.setSelectedItem("Seleccionar");
        
    }
    public boolean validar(){     
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
        if("Seleccionar".equals(view.comboTipo.getSelectedItem())){//Seleccionar
            view.errTipo.setText("**Seleccionar Opcion");
            valid = false;
        }else {
            view.errTipo.setText("");
        }
        if("Seleccionar".equals(view.comboPago.getSelectedItem())){//Seleccionar
            view.errPago1.setText("**Seleccionar Opcion");
            valid = false;
        }else {
            view.errPago1.setText("");
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
            model.setHorario(horario);                     
        }
        return valid;
    }
    public void guardarCliente(){      
        // Verificar si la cédula ya existe
        Document filtroCedula = new Document("Cedula", model.cedula);
        if (!conexionBD.buscarRegistro(filtroCedula).isEmpty()) {
            view.errDni.setText("**Cliente ya existente");
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
        conexionBD.setCollName("Clientes"); // Configurar colección correcta
        conexionBD.insertarRegistro(doc);        
        JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.");
        limpiarCampos();
    }
    public void guardar(){
        boolean vali = validar();
        if(vali){
            guardarCliente();
            pago();
        }else{
            System.out.println("Error no guardo");
        }
    }
    public void pago(){
        double costoDia=2.5, costo=0, pago=0, subTotal=0, inpuest=0, total=0;
        int dias=0;
        String tipo = model.getTipo();
        String pagos = model.getPago();      
        switch (tipo) {
            case "Diaria":
                dias=1;
                break;
            case "Semanal":
                dias=6;
                break;
            case "Mensual":
                dias=24;
                break;
            default:
                break;
        }
        if(dias!=0){
            costo=dias*costoDia;
        }else{
            System.out.println("ERROR DIAS.TIPOS, ES '0'");
        }
        interf.setPrecio(costo);
        interf.calcularPrecio();
        subTotal = interf.getPrecio();
        interf.calcularImpuesto();           
        total = interf.getPrecio();
        inpuest = total - subTotal;
        JOptionPane.showMessageDialog(view, "TOTAL A PAGAR: "+total);
        if(pagos.equals("Efectivo")){
            String pago1 = JOptionPane.showInputDialog(view, "INGRESA EL EFECTIVO ABONADO");                       
            while(pago1==null){
                pago1 = JOptionPane.showInputDialog(view, "INGRESA EL EFECTIVO ABONADO **CAMPO VACIO");
            }
            pago = Double.parseDouble(pago1); 
            while(pago<0){
                pago1 = JOptionPane.showInputDialog(view, "INGRESA EL EFECTIVO ABONADO **CAMPO DEBE SER MAYOR AL PRECIO");
                pago = Double.parseDouble(pago1);
            }
            double sueto = pago - total;
            JOptionPane.showMessageDialog(view, "CAMBIO DEL CLIENTE: "+sueto);
            String nomApe = model.getNomb()+" "+model.getApell();            
            Document doc = new Document("Pago",pagos)
                       .append("Nombre y Apellido", nomApe)
                       .append("Cedula", model.getCedula())
                       .append("Sub Total", subTotal)
                       .append("IVA 15%", inpuest)
                       .append("Total", total);
            conexionBD.setCollName("Pago"); // Configurar colección correcta
            conexionBD.insertarRegistro(doc);
            JOptionPane.showMessageDialog(view, "PAGO REGISTRADO \n"+
                    "\nPago: "+pagos+
                    "\nNombre y Apellido: "+nomApe+
                    "\nCedula: "+model.getCedula()+
                    "\nSub Total: "+subTotal+
                    "\nIVA 15%: "+inpuest+
                    "\nTotal: "+total);
        }else if (pagos.equals("Transferencia")){
            String pago2 = JOptionPane.showInputDialog(view, "INGRESA EL MONTO DE LA TRANSFERENCIA");                       
            while(pago2==null){
                pago2 = JOptionPane.showInputDialog(view, "INGRESA EL MONTO DE LA TRANSFERENCIA **CAMPO VACIO");
            }
            pago = Double.parseDouble(pago2); 
            while(pago!=total){
                pago2 = JOptionPane.showInputDialog(view, "INGRESA EL MONTO DE LA TRANSFERENCIA **CAMPO DEBE SER IGUAL AL PRECIO");
                pago = Double.parseDouble(pago2);
            }
            
            String idTransferencia = JOptionPane.showInputDialog(view, "INGRESA EL NUMERO DE LA TRANSFERENCIA");                       
            while(idTransferencia==null){
                idTransferencia = JOptionPane.showInputDialog(view, "INGRESA EL NUMERO DE LA TRANSFERENCIA **CAMPO VACIO");
            }
            while(idTransferencia.length()!=8){
                idTransferencia = JOptionPane.showInputDialog(view, "INGRESA EL NUMERO DE LA TRANSFERENCIA **8 DIGITOS");
            }
            String nomApe = model.getNomb()+" "+model.getApell();            
            Document doc = new Document("Pago",pagos)
                       .append("Nombre y Apellido", nomApe)
                       .append("Numero Transferencia", idTransferencia)
                       .append("Cedula", model.getCedula())
                       .append("Sub Total", subTotal)
                       .append("IVA 15%", inpuest)
                       .append("Total", total);
            conexionBD.setCollName("Pago"); // Configurar colección correcta
            conexionBD.insertarRegistro(doc);
            JOptionPane.showMessageDialog(view, "PAGO REGISTRADO \n"+
                    "\nPago: "+pagos+
                    "\nNombre y Apellido: "+nomApe+
                    "\nNumero Transferencia: "+idTransferencia+
                    "\nCedula: "+model.getCedula()+
                    "\nSub Total: "+subTotal+
                    "\nIVA 15%: "+inpuest+
                    "\nTotal: "+total);
        }else if(pagos.equals("Card")){
            realizarPago();
            String nomApe = model.getNomb()+" "+model.getApell();            
            Document doc = new Document("Pago",pagos)
                       .append("Nombre y Apellido", nomApe)
                       .append("Cedula", model.getCedula())
                       .append("Sub Total", subTotal)
                       .append("IVA 15%", inpuest)
                       .append("Total", total);
            conexionBD.setCollName("Pago"); // Configurar colección correcta
            conexionBD.insertarRegistro(doc);
            JOptionPane.showMessageDialog(view, "PAGO REGISTRADO \n"+
                    "\nPago: "+pagos+
                    "\nNombre y Apellido: "+nomApe+
                    "\nCedula: "+model.getCedula()+
                    "\nSub Total: "+subTotal+
                    "\nIVA 15%: "+inpuest+
                    "\nTotal: "+total);
        }
    }
    public String solicitarNumeroTarjeta() {
        String numeroTarjeta;
        while (true) {
            numeroTarjeta = JOptionPane.showInputDialog(null, "Por favor, ingrese el número de su tarjeta para realizar el pago:");
            if (numeroTarjeta == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return null;
            }
            if (numeroTarjeta.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El número de tarjeta no puede estar vacío.");
                continue;
            }
            if (numeroTarjeta.matches("\\d{13,19}")) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "El número de tarjeta debe contener entre 13 y 19 dígitos numéricos.");
            }
        }
        return numeroTarjeta;
    }

    // Nuevo método para solicitar la fecha de vencimiento de la tarjeta
    public String solicitarFechaVencimiento() {
        String fechaVencimiento;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

        while (true) {
            fechaVencimiento = JOptionPane.showInputDialog(null, "Por favor, ingrese la fecha de vencimiento de la tarjeta (MM/AA):");
            if (fechaVencimiento == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return null;
            }
            if (fechaVencimiento.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "La fecha de vencimiento no puede estar vacía.");
                continue;
            }
            try {
                YearMonth fecha = YearMonth.parse(fechaVencimiento, formatter);
                // Verifica si el mes está entre 01 y 12
                String[] partes = fechaVencimiento.split("/");
                int mes = Integer.parseInt(partes[0]);
                if (mes < 1 || mes > 12) {
                    JOptionPane.showMessageDialog(null, "El mes debe estar entre 01 y 12.");
                    continue;
                }
                // Verifica si la fecha es anterior al mes/año actual
                if (fecha.isBefore(YearMonth.now())) {
                    JOptionPane.showMessageDialog(null, "La tarjeta está vencida.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato incorrecto. Por favor, ingrese la fecha en formato MM/AA. Ejemplo: 08/24");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El mes debe ser un número entre 01 y 12.");
            }
        }
        return fechaVencimiento;
    }    
    public void realizarPago() {
        String numeroTarjeta = solicitarNumeroTarjeta();
        if (numeroTarjeta != null) {
            String fechaVencimiento = solicitarFechaVencimiento();
            if (fechaVencimiento != null) {
                JOptionPane.showMessageDialog(null, "Pago realizado exitosamente con la tarjeta: " + numeroTarjeta + " Vencimiento: " + fechaVencimiento);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo realizar el pago. Inténtelo de nuevo.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo realizar el pago. Inténtelo de nuevo.");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnGuardar) {
            guardar();            
        } else if (e.getSource() == view.btnCancelar) {
            view.dispose();
        }
    }
}

