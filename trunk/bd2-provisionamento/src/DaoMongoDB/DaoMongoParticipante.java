package DaoMongoDB;

import MyExceptions.DaoException;
import Sistema.Dao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import java.util.List;
import org.bson.types.ObjectId;
import provisionamento.model.Participante;

public class DaoMongoParticipante implements Dao<Participante> {

    private DBCollection collection;
    private static DaoMongoParticipante instancia;

    public static DaoMongoParticipante getInstancia() throws DaoException {
        if (instancia == null) {
            instancia = new DaoMongoParticipante();
        }
        return instancia;
    }

    private DaoMongoParticipante() throws DaoException {
        try {
            collection = 
                    DaoConexaoMongoDB.getInstancia().getCollection("Participante");
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
    
    @Override
    public void grava(Participante participante) throws DaoException {
        BasicDBObject doc = new BasicDBObject()
                .append("usuario", participante.getUsuario().getId())
                .append("flPago", participante.isPago());
        collection.insert(doc);
    }

    @Override
    public Participante deleta(Participante objeto) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Participante busca(String nome) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Participante busca(ObjectId id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Participante> busca() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
