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
import provisionamento.model.GrupoComunitario;
import provisionamento.model.Participante;

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
    public void grava(GrupoComunitario grupoComunitario) throws DaoException {
        ArrayList<ObjectId> idParticipantes = new ArrayList<>();
        ArrayList<Participante> participantes = (ArrayList<Participante>) grupoComunitario.getParticipantes();
        
        for (Participante participante : participantes) {
            idParticipantes.add(participante.getId());
        }
        
        BasicDBObject doc = new BasicDBObject()
                .append("nmComprador", grupoComunitario.getPosComprador())
                .append("dsGrupoComunitario", grupoComunitario.getDescricao())
                .append("dsGrupoComunitarioUpper", grupoComunitario.getDescricao().trim().toUpperCase())
                .append("idCategoria", grupoComunitario.getCategoria().getId())
                .append("idCriador",grupoComunitario.getCriador().getId())
                .append("dtCriacao", grupoComunitario.getDataCriacao())
                .append("prazoValidade", grupoComunitario.getPrazoValidade())
                .append("diasNotificacao", grupoComunitario.getQrdDiasNotificacao())
                .append("quantidade", grupoComunitario.getQuantidade())
                .append("vlrCompra", grupoComunitario.getValorCompra())
                .append("idParticipantes", idParticipantes)
                .append("pago", grupoComunitario.isPago());
        collection.insert(doc);
        grupoComunitario.setId(doc.getObjectId("_id"));
    }

    @Override
    public void deleta(GrupoComunitario grupoComunitario) throws DaoException {
        BasicDBObject query = new BasicDBObject("_id", grupoComunitario.getId());
        collection.remove(query);
    }

    @Override
    public GrupoComunitario busca(String nome) throws DaoException {
        BasicDBObject query = new BasicDBObject("dsGrupoComunitarioUpper", nome.trim().toUpperCase());
        return DBObjectToGrupoComunitario(collection.findOne(query));
    }

    @Override
    public GrupoComunitario busca(ObjectId id) throws DaoException {
        BasicDBObject query = new BasicDBObject("_id", id);
        return DBObjectToGrupoComunitario(collection.findOne(query));          
    }

    @Override
    public List<GrupoComunitario> busca() throws DaoException {
        ArrayList<GrupoComunitario> grupos = new ArrayList();
        try (DBCursor cursor = collection.find()) {
            while (cursor.hasNext()) {
                grupos.add(DBObjectToGrupoComunitario(cursor.next()));
            }
        }
        return grupos;
    }
    
    private GrupoComunitario DBObjectToGrupoComunitario(DBObject ob) throws DaoException{
        GrupoComunitario grupo = new GrupoComunitario();
        grupo.setId((ObjectId)ob.get("_id"));
        grupo.setComprador((Integer) ob.get("nmComprador"));
        grupo.setDescricao((String) ob.get("dsGrupoComunitario"));
        grupo.setDataCriacao((Date) ob.get("dtCriacao"));
        grupo.setPrazoValidade((Date) ob.get("prazoValidade"));
        grupo.setQrdDiasNotificacao((Integer) ob.get("diasNotificacao"));
        grupo.setValorCompra((Double) ob.get("vlrCompra"));
        grupo.setQuantidade((Integer) ob.get("quantidade"));
        grupo.setPago((Boolean) ob.get("pago"));
        
        grupo.setCategoria(DaoMongoCategoria.getInstancia().busca((ObjectId) ob.get("idCategoria")));
        grupo.setCriador(DaoMongoUsuario.getInstancia().busca((ObjectId) ob.get("idCriador")));
        
        ArrayList<ObjectId> idParticipantes = (ArrayList<ObjectId>) ob.get("idParticipantes");
        
        for (ObjectId objectId : idParticipantes) {
            grupo.addParticipante(DaoMongoParticipante.getInstancia().busca(objectId));
        }
        
        return grupo;
    }
    
}
