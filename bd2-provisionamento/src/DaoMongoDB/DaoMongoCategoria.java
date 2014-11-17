package DaoMongoDB;

import MyExceptions.DaoException;
import Sistema.Dao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
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
    public void grava(Categoria categoria) throws DaoException {
        BasicDBObject doc = new BasicDBObject()
                .append("dsCategoria", categoria.getDescricao())
                .append("dsCategoriaUpper", categoria.getDescricao().trim().toUpperCase());
        collection.insert(doc);
        categoria.setId(doc.getObjectId("_id"));
    }

    @Override
    public void deleta(Categoria categoria) throws DaoException {
        BasicDBObject query = new BasicDBObject("_id", categoria.getId());
        collection.remove(query);
    }

    @Override
    public Categoria busca(String nome) throws DaoException {
        BasicDBObject query = new BasicDBObject("dsCategoriaUpper", nome.trim().toUpperCase());
        return DBObjectToCategoria(collection.findOne(query));
    }

    @Override
    public Categoria busca(ObjectId id) throws DaoException {
        BasicDBObject query = new BasicDBObject("_id", id);
        return DBObjectToCategoria(collection.findOne(query));        
    }

    @Override
    public List<Categoria> busca() throws DaoException {
        ArrayList<Categoria> categorias = new ArrayList();
        try (DBCursor cursor = collection.find()) {
            while (cursor.hasNext()) {
                categorias.add(DBObjectToCategoria(cursor.next()));
            }
        }        
        return categorias;
    }
    
    private Categoria DBObjectToCategoria(DBObject ob){
        Categoria categoria = new Categoria();
        categoria.setId((ObjectId)ob.get("_id"));
        categoria.setDescricao((String)ob.get("dsCategoria"));
        return categoria;
    }
}
