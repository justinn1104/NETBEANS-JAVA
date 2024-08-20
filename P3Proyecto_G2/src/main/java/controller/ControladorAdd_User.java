package controller;

import model.ConexionBD;
import com.mongodb.client.MongoCollection;
import model.Efectivo;
import model.Forma_Pago;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
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
    // Expresión regular para validar un número de teléfono
    private static final String TELEFONO_REGEX = "^09\\d{8}$";    
    // Expresión regular para validar un correo electrónico
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public ControladorAdd_User(Add_User view, ModeloAdd_User model, MongoCollection<Document> collection1) {
        this.view = view;
        this.model = model;
        this.collection = collection1;
        this.conexionBD = ConexionBD.getInstance();
        this.view.btnGuardar.addActionListener(this);
        this.view.btnCancelar.addActionListener(this);
        this.limpiarCampos();
    }
    public void limpiarCampos(){                
        this.view.txtNombres.setText("");
        this.view.txtApellidos.setText("");
        this.view.txtCedula.setText("");
        this.view.txtTelefono.setText("");
        this.view.txtCorrero.setText("");
        this.view.radioFeme.setSelected(false);
        this.view.radioMascu.setSelected(false);
        this.view.radioMatu.setSelected(false);
        this.view.radioVespe.setSelected(false);
        this.view.comboTipo.setSelectedItem("Seleccionar");
        this.view.comboPago.setSelectedItem("Seleccionar");
        this.view.errName.setText("");
        this.view.errLastName.setText("");
        this.view.errEmail.setText("");
        this.view.errDni.setText("");
        this.view.errTelefono.setText("");
        this.view.errGenero.setText("");
        this.view.errHorario.setText("");
        this.view.errTipo.setText("");
        this.view.errPago1.setText("");
        this.view.txtTotal.setEnabled(false);
        this.view.txtSuelto.setEnabled(false);
    }
    public boolean validar() {     
        String nom = view.txtNombres.getText();
        String apell = view.txtApellidos.getText();
        String cedula = view.txtCedula.getText();
        String celular = view.txtTelefono.getText();
        String email = view.txtCorrero.getText();
        String genero = "", horario = "";
        int totalSuple = 0;
        boolean valid = true;
        // Validación de género
        if (!(view.radioFeme.isSelected() || view.radioMascu.isSelected())) {
            view.errGenero.setText("**Campo Obligatorio");
            valid = false;
        } else {
            view.errGenero.setText("");
        }
        // Validación de horario
        if (!(view.radioMatu.isSelected() || view.radioVespe.isSelected())) {
            view.errHorario.setText("**Campo Obligatorio");
            valid = false;
        } else {
            view.errHorario.setText("");
        }
        // Validación de nombres
        if (nom.isEmpty()) {
            view.errName.setText("**Campo Obligatorio");
            valid = false;
        } else {
            view.errName.setText("");
        }
        // Validación de e-mail
        
        if(!correoValido(email)){
            view.errEmail.setText("**Formato nameuser@ejemplo.com");
            valid = false;
        }else if (email.isEmpty()) {
            view.errEmail.setText("**Campo Obligatorio");
            valid = false;
        } else {
            view.errEmail.setText("");
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
        if(!numeroCelularValido(celular)){
            view.errTelefono.setText("**Formato 09########");
            valid = false;
        }else if (celular.isEmpty()) {
            view.errTelefono.setText("**Campo Obligatorio");
            valid = false;
        } else if (celular.length() != 10) {
            view.errTelefono.setText("**Obligatorio 10 dígitos");
            valid = false;
        } else if (!celular.startsWith("09")) {         
            view.errTelefono.setText("Formato *09########");
            valid = false;
        } else {
            view.errTelefono.setText("");
        }
        // Validación de tipo
        if ("Seleccionar".equals(view.comboTipo.getSelectedItem())) {
            view.errTipo.setText("**Seleccionar Opción");
            valid = false;
        } else {
            view.errTipo.setText("");
        }

        // Validación de forma de pago
        if ("Seleccionar".equals(view.comboPago.getSelectedItem())) {
            view.errPago1.setText("**Seleccionar Opción");
            valid = false;
        } else {
            view.errPago1.setText("");
        }
        // Cálculo de suplementos
        if (view.checkCrea.isSelected()) totalSuple++;
        if (view.checkPre.isSelected()) totalSuple++;
        if (view.checkProte.isSelected()) totalSuple++;
        if (view.checkL_Arg.isSelected()) totalSuple++;
        if (view.checkL_Citru.isSelected()) totalSuple++;
        if (view.checkQuemador.isSelected()) totalSuple++;
        // Si todas las validaciones pasaron, se configuran los valores en el modelo
        if (valid) {
            genero = view.radioMascu.isSelected() ? "Masculino" : "Femenino";
            horario = view.radioMatu.isSelected() ? "Matutino" : "Vespertino";
            model.setSuple(Integer.toString(totalSuple));
            model.setNomb(nom);
            model.setApell(apell);
            model.setCedula(cedula);
            model.setCelular(celular);
            model.setEdad(view.spinnerEdad.getValue().toString());
            model.setTipo((String) view.comboTipo.getSelectedItem());
            model.setPago((String) view.comboPago.getSelectedItem());
            model.setGenero(genero);
            model.setDire(email);
            model.setHorario(horario);                     
        }
        return valid;
    }
    public void guardarCliente(){      
        // Verificar si la cédula ya existe
        conexionBD.setCollName("Clientes");
        Document filtroCedula = new Document("Cedula", model.cedula);
        if (!conexionBD.buscarRegistro(filtroCedula).isEmpty()) {
            view.errDni.setText("**Cliente ya existente");
            return;
        }
        Document doc = new Document("Nombre", model.nomb)
                       .append("Apellido", model.apell)
                       .append("Correo", model.dire)
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
    public void btnGuardar(){
        boolean vali = validar();
        if(vali){
            pago();
        }else{
            System.out.println("Error no guardo");
        }
    }
    public void pago() {
        double costoDia = 2.5;
        double costo = calcularCostoDias(model.getTipo(), costoDia);
        System.out.println("----Costo = $"+costo);
        if (costo == 0) {
            JOptionPane.showMessageDialog(view, "Error: No se pudo determinar el costo.");
            return;
        }
        interf.setPrecio(costo);
        interf.calcularPrecio();
        double subTotal = interf.getPrecio();
        System.out.println("----SubTotal = $"+costo);
        interf.calcularImpuesto();
        double total = interf.getPrecio();
        System.out.println("----Total = $"+costo);
        view.txtTotal.setText(""+total);
        double impuesto = total - subTotal;
        System.out.println("----Impuesto = $"+costo);
        String metodoPago = model.getPago();
        boolean pagoExitoso = false;

        if ("Efectivo".equals(metodoPago)) {
            pagoExitoso = procesarPagoEfectivo(total);
        } else if ("Transferencia".equals(metodoPago)) {
            pagoExitoso = procesarPagoTransferencia(total);
        } else if ("Card".equals(metodoPago)) {
            pagoExitoso = procesarPagoTarjeta(total);
        }

        if (pagoExitoso) {
            registrarPago(metodoPago, subTotal, impuesto, total);
            guardarCliente();
            limpiarCamposPago();
        } else {
            JOptionPane.showMessageDialog(view, "No se pudo realizar el pago. Inténtelo de nuevo.");
        }
    }
    private double calcularCostoDias(String tipo, double costoDia) {
        int dias; 
        switch (tipo) {
            case "Diaria": dias = 1; break;
            case "Semanal": dias = 6;break;
            case "Mensual": dias = 24;break;
            default: dias = 0;break;
        }
        return dias * costoDia;
    }
    private boolean procesarPagoEfectivo(double total) {
        String pagoStr = solicitarInput("INGRESA EL EFECTIVO ABONADO");
        if (pagoStr == null) return false;
        double pago = Double.parseDouble(pagoStr);
        if (pago < total) {
            JOptionPane.showMessageDialog(view, "El monto abonado debe ser mayor o igual al total.");
            return false;
        }
        double suelto = pago - total;
        view.txtSuelto.setText(String.format("%.2f", suelto));
        return true;
    }
    private boolean procesarPagoTransferencia(double total) {
        String pagoStr = solicitarInput("INGRESA EL MONTO DE LA TRANSFERENCIA");
        if (pagoStr == null || Double.parseDouble(pagoStr) != total) {
            JOptionPane.showMessageDialog(view, "El monto de la transferencia debe ser igual al total.");
            return false;
        }

        String idTransferencia = solicitarInput("INGRESA EL NUMERO DE LA TRANSFERENCIA (8 dígitos)", "\\d{8}");
        return idTransferencia != null;
    }
    private boolean procesarPagoTarjeta(double total) {
        String numeroTarjeta = solicitarNumeroTarjeta();
        if (numeroTarjeta == null) return false;
        String fechaVencimiento = solicitarFechaVencimiento();
        return fechaVencimiento != null;
    }
    private void registrarPago(String metodoPago, double subTotal, double impuesto, double total) {
        conexionBD.setCollName("Pago");
        String nomApe = model.getNomb() + " " + model.getApell();
        Document doc = new Document("Pago", metodoPago)
                .append("Nombre y Apellido", nomApe)
                .append("Cedula", model.getCedula())
                .append("Sub Total", subTotal)
                .append("IVA 15%", impuesto)
                .append("Total", total);
        conexionBD.setCollName("Pago");
        conexionBD.insertarRegistro(doc);
        JOptionPane.showMessageDialog(view, String.format(
                "PAGO REGISTRADO\n\nPago: %s\nNombre y Apellido: %s\nCedula: %s\nSub Total: %.2f\nIVA 15%%: %.2f\nTotal: %.2f",
                metodoPago, nomApe, model.getCedula(), subTotal, impuesto, total
        ));
    }
    private String solicitarInput(String mensaje) {
        return solicitarInput(mensaje, ".+");
    }
    private String solicitarInput(String mensaje, String regex) {
        String input;
        do {
            input = JOptionPane.showInputDialog(view, mensaje);
            if (input == null) return null;
        } while (!input.matches(regex));
        return input;
    }
    private void limpiarCamposPago() {
        view.txtTotal.setText("00.00");
        view.txtSuelto.setText("00.00");
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
    public static boolean correoValido(String correo) {
        if (correo == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(correo).matches();
    }
    public static boolean numeroCelularValido(String telefono) {
        if (telefono == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(TELEFONO_REGEX);
        return pattern.matcher(telefono).matches();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnGuardar) {
            btnGuardar();            
        } else if (e.getSource() == view.btnCancelar) {
            view.dispose();
        }
    }
}