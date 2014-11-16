package DaoMongoDB;

import MyExceptions.DaoException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import java.lang.reflect.Type;
import provisionamento.model.Categoria;
import provisionamento.model.GrupoComunitario;
import provisionamento.model.GrupoUnitario;
import provisionamento.model.Participante;
import provisionamento.model.Usuario;

public class DaoConexaoMongoDB {

    private DB db;
    private static DaoConexaoMongoDB instancia;

    private DaoConexaoMongoDB() throws DaoException {
        try {
            MongoClient mongoClient = new MongoClient("localhost");
            mongoClient.setWriteConcern(WriteConcern.JOURNALED); // não sei pra que serve :)
            db = mongoClient.getDB("Provisionamento");
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public static DaoConexaoMongoDB getInstancia() throws DaoException {
        if (instancia == null) {
            instancia = new DaoConexaoMongoDB();
        }
        return instancia;
    }

    public DBCollection getCollection(Type type) {
        DBCollection collection;
        if (type.equals(Usuario.class)) {
            collection = db.getCollection("Usuario");
        } else if (type.equals(Categoria.class)) {
            collection = db.getCollection("Categoria");
        } else if (type.equals(GrupoComunitario.class)) {
            collection = db.getCollection("GrupoComunitario");
        } else if (type.equals(GrupoUnitario.class)) {
            collection = db.getCollection("GrupoUnitario");
        } else if (type.equals(Participante.class)) {
            collection = db.getCollection("Participante");
        } else {
            collection = null;
        }

        return collection;
    }
}
