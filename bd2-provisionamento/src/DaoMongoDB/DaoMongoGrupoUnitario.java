package DaoMongoDB;

import MyExceptions.DaoException;
import Sistema.Dao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.Date;
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
    public void grava(GrupoUnitario grupoUnitario) throws DaoException {
        BasicDBObject doc = new BasicDBObject()
                .append("dsGrupoUnitario", grupoUnitario.getDescricao())
                .append("dsGrupoUnitarioUpper", grupoUnitario.getDescricao().trim().toUpperCase())
                .append("idCategoria", grupoUnitario.getCategoria().getId())
                .append("idCriador",grupoUnitario.getCriador().getId())
                .append("dtCriacao", grupoUnitario.getDataCriacao())
                .append("prazoValidade", grupoUnitario.getPrazoValidade())
                .append("diasNotificacao", grupoUnitario.getQrdDiasNotificacao())
                .append("quantidade", grupoUnitario.getQuantidade())
                .append("vlrCompra", grupoUnitario.getValorCompra());
        collection.insert(doc);
        grupoUnitario.setId(doc.getObjectId("_id"));
    }

    @Override
    public void deleta(GrupoUnitario grupoUnitario) throws DaoException {
        BasicDBObject query = new BasicDBObject("_id", grupoUnitario.getId());
        collection.remove(query);
    }

    @Override
    public GrupoUnitario busca(String nome) throws DaoException {
        BasicDBObject query = new BasicDBObject("dsGrupoUnitarioUpper", nome.trim().toUpperCase());
        return DBObjectToGrupoUnitario(collection.findOne(query));
    }

    @Override
    public GrupoUnitario busca(ObjectId id) throws DaoException {
        BasicDBObject query = new BasicDBObject("_id", id);
        return DBObjectToGrupoUnitario(collection.findOne(query));
    }

    @Override
    public List<GrupoUnitario> busca() throws DaoException {
        ArrayList<GrupoUnitario> grupos = new ArrayList();
        try (DBCursor cursor = collection.find()) {
            while (cursor.hasNext()) {
                grupos.add(DBObjectToGrupoUnitario(cursor.next()));
            }
        }
        return grupos;
    }
    
    private GrupoUnitario DBObjectToGrupoUnitario(DBObject ob) throws DaoException{
        GrupoUnitario grupo = new GrupoUnitario();

        grupo.setId((ObjectId)ob.get("_id"));
        grupo.setDescricao((String) ob.get("dsGrupoComunitario"));
        grupo.setDataCriacao((Date) ob.get("dtCriacao"));
        grupo.setPrazoValidade((Date) ob.get("prazoValidade"));
        grupo.setQrdDiasNotificacao((Integer) ob.get("diasNotificacao"));
        grupo.setValorCompra((Double) ob.get("vlrCompra"));
        grupo.setQuantidade((Integer) ob.get("quantidade"));
        
        grupo.setCategoria(DaoMongoCategoria.getInstancia().busca((ObjectId) ob.get("idCategoria")));
        grupo.setCriador(DaoMongoUsuario.getInstancia().busca((ObjectId) ob.get("idCriador")));
        
        return grupo;
    }
    
}
