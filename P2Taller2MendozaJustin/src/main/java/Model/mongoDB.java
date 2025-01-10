package Model;
import com.mongodb.client.MongoClient; // Esta línea debe estar presente
import com.mongodb.MongoException;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
public final class mongoDB {
    private final String dataBaseName = "P2Taller2SemanateDiego";
    private final MongoClient client;
    private MongoDatabase mongoDB;
    public String collectionName;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
    public MongoCollection<Document> getCollection(String collectionName) {
        return mongoDB.getCollection(collectionName);
    }

    public mongoDB(){
        client = (MongoClient) MongoClients.create("mongodb://localhost:27017");
        mongoDB = client.getDatabase(dataBaseName);
        setCollectionName("Estudiantes");
        if(mongoDB.getCollection(getCollectionName()).countDocuments() == 0){
            mongoDB.createCollection(getCollectionName());
        }
    }
    public MongoDatabase getMongoDB(){
        return mongoDB;
    }
    //connection
    public MongoDatabase createConnection(){
        try {
            mongoDB = getMongoDB();
            System.out.println("\n\n--->>>>Connection exitosa a DataBase mongoDb<<<<----\n\n");
            return mongoDB;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //create
    public boolean createDocument(String collectionName, Document doc) {
    try {
        MongoCollection<Document> collection = getCollection(collectionName);
        collection.insertOne(doc);
        System.out.println("\n\n--->>>Documento insertado exitosamente en la colección '" + collectionName + "'<<<---\n\n");
        return true;
    } catch (MongoException e) {
        e.printStackTrace();
        return false;
    }
}

    
    //read
    public List<Document> readDocument(String collectionName, Document doc){
        List<Document> result = new ArrayList<>();
        try{
            MongoDatabase db = createConnection();
            MongoCollection<Document> collection = db.getCollection(collectionName);
            result = collection.find(doc).into(result);
            System.out.println("\n\n--->>>DATOS LEIDOS DE LA DB CON EXITO<<<---\n\n");
            return result;
        }catch(MongoException e){
            e.printStackTrace();
        }
        return result;
    }
    //delete
    public boolean deleteDocument(String collectionName, Document doc){
        try{
            MongoDatabase db = createConnection();
            if(db!=null){
                MongoCollection<Document> collection = db.getCollection(collectionName);
                collection.deleteOne(doc);
                System.out.println("\n\n--->>>DATOS ELIMINADOS DE LA DB CON EXITO<<<---\n\n");
                return true;
            }
        }catch(MongoException e){
            e.printStackTrace();
        }
        return  false;
    }
    //update
    public boolean updateDocument(String collectionName, Document docOld, Document docNew){
        try{
            MongoDatabase db = createConnection();
            if(db!= null){
                MongoCollection<Document> collection = db.getCollection(collectionName);
                collection.updateOne(docOld, new Document("$set",docNew));
                System.out.println("\n\n--->>>DATOS MODIFICADOS DE LA DB CON EXITO<<<---\n\n");
                return true;
            }
        }catch(MongoException e){
            e.printStackTrace();
        }
        return false;
    }
    //search
    public ArrayList searchDocument(String collectionName, Document filtro) {
        MongoDatabase db = createConnection();
        try{
            MongoCollection<Document> collection = db.getCollection(collectionName);
            ArrayList resultados = new ArrayList<>();
            for (Document doc : collection.find(filtro)) {
                resultados.add(doc);
            }
            System.out.println("\n\n--->>>DATOS ENCONTRADOS DE LA DB CON EXITO<<<---\n\n");
            return resultados;
        }catch(MongoException e){
            e.printStackTrace();
        }
        return null ;
    }
    public DefaultTableModel cargarDataTable(String collectionName) {
        // Obtener la colección "Clientes"
        MongoCollection<Document> collection = mongoDB.getCollection(collectionName);
        // Crear una lista de documentos
        List<Document> documents = collection.find().into(new ArrayList<>());
        // Definir los nombres de las columnas
        String[] columnNames = {"Cedula", "Nombre", "Apellido", "Carrera", "Creditos" ,"Promedio","Becario","Nivel","Pago-Total"};  // Cambia estos según tus campos en MongoDB
        // Crear un modelo de tabla
        DefaultTableModel tdm = new DefaultTableModel(columnNames, 0);
        // Iterar sobre los documentos y añadir filas al modelo de la tabla
        for (Document doc : documents) {
            // Asumiendo que cada documento tiene los campos _id, nombre, edad, direccion
            Object[] row = {
                doc.get("Cedula"),
                doc.get("Nombre"), 
                doc.get("Apellido"),
                doc.get("Carrera"),
                doc.get("Creditos"),
                doc.get("Promedio"),
                doc.get("Becario"),
                doc.get("Nivel"),
                doc.get("Pago-Total"),
            };
            tdm.addRow(row);
        }
        
        return tdm;
    }
    
}
