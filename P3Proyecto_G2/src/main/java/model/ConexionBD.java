package model;
//SINLETON ****************
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import java.util.ArrayList;
import org.bson.Document;

public class ConexionBD {    
    private MongoClient mongoClient;
    public MongoDatabase database;
    private static ConexionBD instancia;
    public MongoCollection<Document> collection;
    private String dbName, collName;
    private String dbMsg = "";

    public String getDbName() {
        return dbName;
    }
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    public String getCollName() {
        return collName;
    }
    public void setCollName(String collName) {
        this.collName = collName;
    }
    //PATRON SINGLE
    public ConexionBD(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }
    //PATRON SINGLE
    private ConexionBD() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("DataBase_GYM_PRO");
    }
    public String getDbMsg() {
        return dbMsg;
    }
    //PATRON SINGLE
    public static ConexionBD getInstance() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }
    //PATRON SINGLE
    public MongoDatabase getDatabase() {
        return database;
    }
    //CREATE
    public void insertarRegistro(Document documento) {
        collection = getInstance().getDatabase().getCollection(collName);
         if (collection.countDocuments() == 0) {
            dbMsg = "Colección " + collName + " no existente, se ha creado.";
        }
        collection.insertOne(documento);
    }

    //UPDATE Actualizar desde BD
    public void actualizarRegistro(Document filtro, Document actualizado) {
        collection = getInstance().getDatabase().getCollection(collName);
        UpdateOptions options = new UpdateOptions().upsert(false);
        collection.updateOne(filtro, new Document("$set", actualizado), options);
    }
    //DELE

    public void eliminarRegistro(Document filtro) {
        collection = getInstance().getDatabase().getCollection(collName);
        collection.deleteOne(filtro);
    }

    //READER Leer desde BD
    public ArrayList<Document> leerRegistro() {
        collection = getInstance().getDatabase().getCollection(collName);
        ArrayList<Document> datos = new ArrayList<>();
        for (Document doc : collection.find()) {
            datos.add(doc);
        }
        return datos;
    }
    
       public boolean existenRegistros (String isbn) {
        collection = getInstance().getDatabase().getCollection(collName);
        Document filtro = new Document("ISBN", isbn);
        Document resultado = collection.find(filtro).first();
        return resultado != null;
    }
    //Buscar desde la BD
    public ArrayList<Document> buscarRegistro(Document filtro) {
        collection = getInstance().getDatabase().getCollection(collName);
        ArrayList<Document> resultados = new ArrayList<>();
        for (Document doc : collection.find(filtro)) {
            resultados.add(doc);
        }
        return resultados;
    }
    
}
