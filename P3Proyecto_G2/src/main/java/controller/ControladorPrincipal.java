package controller;

import model.ConexionBD;
import javax.swing.*;
import java.awt.*;
import com.mongodb.client.MongoCollection;
import java.awt.event.*;
import model.Model_AddInventario;
import model.Model_Inventario;
import model.ModeloAdd_User;
import model.ModeloClients;
import model.ModeloMembres;
import model.ModeloPrincipal;
import org.bson.Document;
import view.Add_Inventario;
import view.Add_User;
import view.Clients;
import view.Inventario;
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
        this.view.menuAddInventario.addActionListener(this); 
        this.view.menuInventario.addActionListener(this);     
    }
    public void iniciarView(){
        /*amountPanter = Toolkit.getDefaultToolkit().getScreenSize();
        view.setSize(amountPanter.width, amountPanter.height);
        view.setExtendedState(JFrame.MAXIMIZED_BOTH);*/
        view.setVisible(true);
    }
    public void menuAdd(){
        Add_User a = new Add_User();
        ModeloAdd_User b = new ModeloAdd_User("","","","","","","","","","","");
        conexionBD.setCollName("Clientes");
        ControladorAdd_User c = new ControladorAdd_User(a, b, conexionBD.collection);
        view.escritorio.add(c.view);
        c.view.show();
    }
    public void menuClientes() {
        Clients cliens = new Clients();
        ModeloClients modelCliens = new ModeloClients("", "", "", "", "", "", "");
        IRepositorioClientes repositorioClientes = new ClienteMongo(conexionBD); // Asegúrate de que esto no es null
        conexionBD.setCollName("Clientes");
        ControladorClients controlador = new ControladorClients(cliens, modelCliens, repositorioClientes);
        view.escritorio.add(controlador.view);
        controlador.view.show();
        System.out.println("\n\n---->Menu Clientes Abierto\n\n");
    }
    public void menuMenbre(){
        Membres a = new Membres();
        ModeloMembres b = new ModeloMembres("","","","","","");
        conexionBD.setCollName("Clientes");
        ControladorMembres c = new ControladorMembres(a, b, conexionBD.collection);
        System.out.println("\n\n---->Menu Membrecias Abierto\n\n");
        view.escritorio.add(c.View);
        c.View.show();
    }
    public void menuAddInventario(){
        Add_Inventario a = new Add_Inventario();
        Model_AddInventario b = new Model_AddInventario("","","","","");
        conexionBD.setCollName("Inventario");
        Controller_AddInventario c = new Controller_AddInventario(a, b,conexionBD.collection);
        System.out.println("\n\n---->Menu Add inventario Abierto\n\n");
        view.escritorio.add(c.view);
        c.view.show();
    }
    public void menuInventari(){
        Inventario a = new Inventario();
        Model_Inventario b = new Model_Inventario("","");
        conexionBD.setCollName("Inventario");
        Controller_Inventario c = new Controller_Inventario(a, b,conexionBD.collection);
        System.out.println("\n\n---->Menu Inventario Abierto\n\n");
        view.escritorio.add(c.view);
        c.view.show();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.menuAdd){
            menuAdd();
        }else if(e.getSource()==view.menuClients){
            menuClientes();
        }else if(e.getSource()==view.menuMembres){
            menuMenbre();
        }else if(e.getSource()==view.menuAddInventario){
            menuAddInventario();
        }else if(e.getSource()==view.menuInventario){
            menuInventari();
        }
    }
    
    
}
