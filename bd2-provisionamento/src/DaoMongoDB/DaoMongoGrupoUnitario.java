package DaoMongoDB;

import MyExceptions.DaoException;
import Sistema.Dao;
import com.mongodb.DBCollection;
import java.util.List;
import org.bson.types.ObjectId;
import provisionamento.model.GrupoUnitario;

public class DaoMongoGrupoUnitario implements Dao<GrupoUnitario>  {

    private DBCollection collection;
    private static DaoMongoGrupoUnitario instancia;

    public static DaoMongoGrupoUnitario getInstancia() throws DaoException {
        if (instancia == null) {
            instancia = new DaoMongoGrupoUnitario();
        }
        return instancia;
    }

    private DaoMongoGrupoUnitario() throws DaoException {
        try {
            collection = 
                    DaoConexaoMongoDB.getInstancia().getCollection(GrupoUnitario.class);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
    
    @Override
    public void grava(GrupoUnitario objeto) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleta(GrupoUnitario objeto) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GrupoUnitario busca(String nome) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GrupoUnitario busca(ObjectId id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GrupoUnitario> busca() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
