package ConexionBD;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ConexionBD {
    private MongoClient mongoClient;    
    public MongoDatabase database;
      MongoCollection<Document> collection;

    public ConexionBD() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");        
        database = mongoClient.getDatabase("RegistroUsuario");  //Nombre de mi Base de Datos
    }

    public void insertarRegistro(String databaseName, String collectionName, Document documento) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(documento);
    }

    public void eliminarRegistro(String databaseName, String collectionName, Document filtro) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.deleteOne(filtro);
    }
    
    public void actualizarRegistro(String databaseName, String collectionName, Document filtro, Document actualizado) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        UpdateOptions options = new UpdateOptions().upsert(false);
        collection.updateOne(filtro, new Document("$set", actualizado), options);
}
    
    public ArrayList<Document> buscarRegistro(String databaseName, String collectionName, Document filtro) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        ArrayList<Document> resultados = new ArrayList<>();
        for (Document doc : collection.find(filtro)) {
            resultados.add(doc);
        }
        return resultados;
    }
    
    public void mostrarMensajeConexionExitosa() {
        JOptionPane.showMessageDialog(null, "Conexión exitosa a MongoDB");
    }
    //Leer desde BD
    public ArrayList<Document> leerRegistros(String databaseName, String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        ArrayList<Document> datos = new ArrayList<>();
        for (Document doc : collection.find()) {
            datos.add(doc);
        }
        return datos;
    }
    
}
