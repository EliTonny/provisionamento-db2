package DaoMongoDB;

import MyExceptions.DaoException;
import Sistema.Dao;
import com.mongodb.DBCollection;
import java.util.List;
import org.bson.types.ObjectId;
import provisionamento.model.GrupoComunitario;

public class DaoMongoGrupoComunitario implements Dao<GrupoComunitario> {

    private DBCollection collection;
    private static DaoMongoGrupoComunitario instancia;

    public static DaoMongoGrupoComunitario getInstancia() throws DaoException {
        if (instancia == null) {
            instancia = new DaoMongoGrupoComunitario();
        }
        return instancia;
    }

    private DaoMongoGrupoComunitario() throws DaoException {
        try {
            collection = 
                    DaoConexaoMongoDB.getInstancia().getCollection(GrupoComunitario.class);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
    
    @Override
    public void grava(GrupoComunitario objeto) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleta(GrupoComunitario objeto) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GrupoComunitario busca(String nome) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GrupoComunitario busca(ObjectId id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GrupoComunitario> busca() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
