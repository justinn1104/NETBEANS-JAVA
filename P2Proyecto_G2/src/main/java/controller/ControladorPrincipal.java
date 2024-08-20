package controller;

import ConexionBD.ConexionBD;
import javax.swing.*;
import java.awt.*;
import com.mongodb.client.MongoCollection;
import java.awt.event.*;
import model.ModeloAdd_User;
import model.ModeloClients;
import model.ModeloMembres;
import model.ModeloPrincipal;
import org.bson.Document;
import view.Add_User;
import view.Clients;
import view.Membres;
import view.Principal;

public class ControladorPrincipal implements ActionListener{
    private Principal view;
    private ModeloPrincipal model;
    private ConexionBD conexionBD;
    private Dimension amountPanter;

    public ControladorPrincipal(Principal view, ModeloPrincipal model) {
        this.view = view;
        this.model = model;
        this.conexionBD = ConexionBD.getInstance();
        this.view.menuCerrar.addActionListener(this);
        this.view.menuClients.addActionListener(this);
        this.view.menuMembres.addActionListener(this); 
        this.view.menuAdd.addActionListener(this);                
    }
    public void iniciarView(){
        amountPanter = Toolkit.getDefaultToolkit().getScreenSize();
        view.setSize(amountPanter.width, amountPanter.height);
        view.setExtendedState(JFrame.MAXIMIZED_BOTH);
        view.setVisible(true);
    }
    public void menuAdd(){
        Add_User a = new Add_User();
        ModeloAdd_User b = new ModeloAdd_User("","","","","","","","","","","");
        MongoCollection<Document> collection = conexionBD.database.getCollection("Clientes");
        ControladorAdd_User c = new ControladorAdd_User(a, b, collection);
        view.escritorio.add(c.view);
        c.view.show();
    }
    public void menuClientes(){
        Clients a = new Clients();
        ModeloClients b = new ModeloClients("","","","","","","");
        MongoCollection<Document> collection = conexionBD.database.getCollection("Clientes");
        ControladorClients c = new ControladorClients(a, b, collection);
        view.escritorio.add(c.view);
        c.view.show();
    }
    public void menuMenbre(){
        Membres a = new Membres();
        ModeloMembres b = new ModeloMembres("","","","","","");
        MongoCollection<Document> collection = conexionBD.database.getCollection("Clientes");
        ControladorMembres c = new ControladorMembres(a, b, collection);
        view.escritorio.add(c.View);
        c.View.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.menuAdd){
            menuAdd();
        }else if(e.getSource()==view.menuClients){
            menuClientes();
        }else if(e.getSource()==view.menuMembres){
            menuMenbre();
        }
    }
    
    
}
