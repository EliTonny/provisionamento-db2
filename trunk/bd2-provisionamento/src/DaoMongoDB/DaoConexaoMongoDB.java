package DaoMongoDB;

import MyExceptions.DaoException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

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

    public DBCollection getCollection(String nomeCollection) {
        return db.getCollection(nomeCollection);
    }
}
