package controller;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import model.ConexionBD;

public class ClienteMongo implements IRepositorioClientes {
    private ConexionBD conexionBD;

    public ClienteMongo (ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public void actualizarRegistro(Document filtro, Document nuevoDoc) {
        conexionBD.setCollName("Clientes");
        conexionBD.actualizarRegistro(filtro, nuevoDoc);
    }

    @Override
    public ArrayList<Document> buscarRegistro(Document filtro) {
        conexionBD.setCollName("Clientes");
        return conexionBD.buscarRegistro(filtro);
    }

    @Override
    public void eliminarRegistro(Document filtro) {
        conexionBD.setCollName("Clientes");
        conexionBD.eliminarRegistro(filtro);
    }

    @Override
    public List<Document> obtenerTodosLosRegistros() {
        MongoCollection<Document> collection = conexionBD.database.getCollection("Clientes");
        return collection.find().into(new ArrayList<>());
    }
}
