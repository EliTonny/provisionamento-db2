package DaoMongoDB;

import MyExceptions.DaoException;
import Sistema.Dao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import java.util.List;
import org.bson.types.ObjectId;
import provisionamento.model.Participante;
import provisionamento.model.Usuario;

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
            collection
                    = DaoConexaoMongoDB.getInstancia().getCollection(Participante.class);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void grava(Participante participante) throws DaoException {
        BasicDBObject doc = new BasicDBObject()
                .append("usuario", participante.getUsuario().getId())
                .append("dsUsuario", participante.getUsuario().getNome().trim().toUpperCase())
                .append("flPago", participante.isPago());
        collection.insert(doc);
    }

    @Override
    public void deleta(Participante objeto) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Participante busca(String nome) throws DaoException {
        BasicDBObject filtro = new BasicDBObject("dsUsuario", nome.trim().toUpperCase());
        return DBObjectToParticipante(collection.findOne(filtro));
    }

    @Override
    public Participante busca(ObjectId id) throws DaoException {
        BasicDBObject filtro = new BasicDBObject("_id", id);
        return DBObjectToParticipante(collection.findOne(filtro));
    }

    @Override
    public List<Participante> busca() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Participante DBObjectToParticipante(DBObject ob) throws DaoException {
        if (ob == null) {
            return null;
        }
        Participante participante = new Participante();
        participante.setId((ObjectId) ob.get("_id"));
        Usuario usuario
                = DaoMongoUsuario.getInstancia().busca((ObjectId)ob.get("usuario"));
        participante.setUsuario(usuario);
        return participante;
    }
}
