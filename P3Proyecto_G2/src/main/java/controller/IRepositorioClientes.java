
package controller;
//*****+INTERFAZ PARA HACER LAS OPERACIONES DE LA BD
//********************++PATRON ESTRUCTURAR BRIDGE - PARTE EL CODIGO EN PARTES MAS PEQUEÑAS
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public interface IRepositorioClientes {
    List<Document> obtenerTodosLosRegistros();
    void actualizarRegistro(Document filtro, Document doc);
    void eliminarRegistro(Document filtro);
    List<Document> buscarRegistro(Document filtro);
}
