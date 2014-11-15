package DaoMongoDB;

import MyExceptions.DaoException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import java.util.List;
import provisionamento.model.Usuario;
import Sistema.Dao;
import com.mongodb.BasicDBList;
import java.util.ArrayList;

public class DaoMongoUsuario implements Dao<Usuario> {

    private DBCollection collectionUsuario;

    private static DaoMongoUsuario instancia;

    public static DaoMongoUsuario getInstancia() throws DaoException {
        if (instancia == null) {
            instancia = new DaoMongoUsuario();
        }
        return instancia;
    }

    private DaoMongoUsuario() throws DaoException {
        try {
            MongoClient mongoClient = new MongoClient("localhost");
            mongoClient.setWriteConcern(WriteConcern.JOURNALED); // não sei pra que serve :)
            DB db = mongoClient.getDB("Provisionamento");
            collectionUsuario = db.getCollection("usuario");
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Usuario busca(String nome) throws DaoException {
        Usuario usuario = null;
        BasicDBObject query = new BasicDBObject("dsUsuario", nome);
        try (DBCursor cursor = collectionUsuario.find(query)) {
            while (cursor.hasNext()) {
                DBObject ob = cursor.next();
                usuario = new Usuario();
                usuario.setNome((String) ob.get("dsUsuario"));
                usuario.setEmail((String) ob.get("dsEmail"));
                //char[] senha = (char[]) ob.get("dsSenha"); 
                //usuario.setSenha(senha);
                break;
            }
        }
        return usuario;
    }

    @Override
    public List<Usuario> busca() throws DaoException {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try (DBCursor cursor = collectionUsuario.find()) {
            while (cursor.hasNext()) {
                DBObject ob = cursor.next();
                Usuario usu = new Usuario();
                usu.setNome((String) ob.get("dsUsuario"));
                usu.setEmail((String) ob.get("dsEmail"));
                //BasicDBList asdasda = (BasicDBList) ob.get("dsSenha");
                //char[] senha = (char[]) ob.get("dsSenha"); 
                //usu.setSenha(senha);
                usuarios.add(usu);
            }
        }
        return usuarios;
    }

    @Override
    public void grava(Usuario usuario) throws DaoException {
        try {
            BasicDBObject doc = new BasicDBObject("dsUsuario", usuario.getNome())
                    .append("dsEmail", usuario.getEmail())
                    .append("dsSenha", usuario.getSenha());
            collectionUsuario.insert(doc, WriteConcern.JOURNALED);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Usuario busca(int id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario deleta(Usuario object) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void abre() throws DaoException {
        //lembrar de remover esse metodo
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
