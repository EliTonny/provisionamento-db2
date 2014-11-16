package DaoMongoDB;

import MyExceptions.DaoException;
import Sistema.Dao;
import com.mongodb.DBCollection;
import java.util.List;
import org.bson.types.ObjectId;
import provisionamento.model.Categoria;

public class DaoMongoCategoria  implements Dao<Categoria> {

    private DBCollection collection;
    private static DaoMongoCategoria instancia;

    public static DaoMongoCategoria getInstancia() throws DaoException {
        if (instancia == null) {
            instancia = new DaoMongoCategoria();
        }
        return instancia;
    }

    private DaoMongoCategoria() throws DaoException {
        try {
            collection = 
                    DaoConexaoMongoDB.getInstancia().getCollection(Categoria.class);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
    
    @Override
    public void grava(Categoria objeto) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleta(Categoria objeto) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria busca(String nome) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria busca(ObjectId id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categoria> busca() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
