    package DaoMongoDB;

import MyExceptions.DaoException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.List;
import provisionamento.model.Usuario;
import Sistema.Dao;
import com.mongodb.BasicDBList;
import java.util.ArrayList;
import org.bson.types.ObjectId;

public class DaoMongoUsuario implements Dao<Usuario> {

    private DBCollection collection;
    private static DaoMongoUsuario instancia;

    public static DaoMongoUsuario getInstancia() throws DaoException {
        if (instancia == null) {
            instancia = new DaoMongoUsuario();
        }
        return instancia;
    }

    private DaoMongoUsuario() throws DaoException {
        try {
            collection = 
                    DaoConexaoMongoDB.getInstancia().getCollection(Usuario.class);
            //collection.createIndex(new BasicDBObject("dsUsuarioUpper", 1));
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Usuario busca(String nome) {
        BasicDBObject query = new BasicDBObject("dsUsuarioUpper", nome.trim().toUpperCase());
        return DBObjectToUsuario(collection.findOne(query));
    }

    @Override
    public List<Usuario> busca() {
        List<Usuario> usuarios = new ArrayList<>();
        try (DBCursor cursor = collection.find()) {
            while (cursor.hasNext()) {
                usuarios.add(DBObjectToUsuario(cursor.next()));
            }
        }
        return usuarios;
    }

    @Override
    public void grava(Usuario usuario) {
        BasicDBObject doc = new BasicDBObject()
                .append("dsUsuario", usuario.getNome())
                .append("dsUsuarioUpper", usuario.getNome().trim().toUpperCase())
                .append("dsEmail", usuario.getEmail())
                .append("dsSenha", usuario.getSenha());
        collection.insert(doc);
        usuario.setId(doc.getObjectId("_id"));
    }

    @Override
    public Usuario busca(ObjectId id) throws DaoException {
        BasicDBObject query = new BasicDBObject("_id", id);
        return DBObjectToUsuario(collection.findOne(query));
    }
    
    /*public void alterar(ObjectId id, DBObject ob){
        DBObject query = new BasicDBObject("_id", id);
        collection.update(query, ob, true, true);
    }*/

    @Override
    public void deleta(Usuario usuario) throws DaoException {
        BasicDBObject query = new BasicDBObject("_id", usuario.getId());
        collection.remove(query);
    }

    private Usuario DBObjectToUsuario(DBObject ob) {
        if(ob == null)
            return null;
        Usuario usuario = new Usuario();
        usuario.setId((ObjectId)ob.get("_id"));
        usuario.setNome((String) ob.get("dsUsuario"));
        usuario.setEmail((String) ob.get("dsEmail"));
        BasicDBList dsSenha = (BasicDBList) ob.get("dsSenha");
        usuario.setSenha(ArrayObjectsToArrayChars(dsSenha.toArray()));
        return usuario;
    }

    private char[] ArrayObjectsToArrayChars(Object[] data) {
        char[] saida = new char[data.length];
        for (int i = 0; i < data.length; i++) {
            saida[i] = ((String) data[i]).charAt(0);
        }
        return saida;
    }
}
