package controller;

import ConexionBD.ConexionBD;
import com.mongodb.DBCollection;
import com.mongodb.client.MongoCollection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
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
    private DBCollection colec;

    public ControladorMembres(Membres View, ModeloMembres Model, MongoCollection<Document> collection) {
        this.View = View;
        this.Model = Model;
        this.collection = collection;
        conexionBD = new ConexionBD();
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
            String Genero = dtm.getValueAt(filaSeleccionada, 6).toString();            
            String tipo = dtm.getValueAt(filaSeleccionada, 7).toString();
            String horario = dtm.getValueAt(filaSeleccionada, 8).toString();
            String pago = dtm.getValueAt(filaSeleccionada, 9).toString();
            String Suplementos = dtm.getValueAt(filaSeleccionada, 10).toString();
            int a = Integer.parseInt(Suplementos);
            View.txtName.setText(Nombre);
            View.txtGenero.setText(Genero);            
            View.comboTipo.setSelectedItem(tipo);
            View.comboPago.setSelectedItem(pago);            
            for(int i=1; i<a ; i++){
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
                    default:
                        View.check1.setSelected(false);
                        View.check2.setSelected(false);
                        View.check3.setSelected(false);
                        View.check4.setSelected(false);
                        View.check5.setSelected(false);
                        View.check6.setSelected(false);
                        break;
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
            //limpiarCampos();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==View.btnCancelar){
            View.dispose();
        }
    }
    
}
