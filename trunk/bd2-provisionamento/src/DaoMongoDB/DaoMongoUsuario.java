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
                    DaoConexaoMongoDB.getInstancia().getCollection("Usuario");
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Usuario busca(String nome) {
        Usuario usuario = null;
        BasicDBObject query = new BasicDBObject("dsUsuarioUpper", nome.trim().toUpperCase());
        try (DBCursor cursor = collection.find(query)) {
            while (cursor.hasNext()) {
                usuario = DBObjectToUsuario(cursor.next());
                break;
            }
        }
        return usuario;
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
    }

    @Override
    public Usuario busca(ObjectId id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario deleta(Usuario object) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Usuario DBObjectToUsuario(DBObject ob) {
        Usuario usuario = new Usuario();
        usuario.setId((ObjectId) ob.get("_id"));
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
