package Sistema;

import DaoMongoDB.DaoMongoCategoria;
import DaoMongoDB.DaoMongoGrupoComunitario;
import DaoMongoDB.DaoMongoGrupoUnitario;
import DaoMongoDB.DaoMongoParticipante;
import DaoMongoDB.DaoMongoUsuario;
import MyExceptions.DaoException;
import provisionamento.model.Categoria;
import provisionamento.model.GrupoComunitario;
import provisionamento.model.GrupoUnitario;
import provisionamento.model.Participante;
import provisionamento.model.Usuario;

public class Factoring {
    
    private Factoring(){
        
    }

    public static Dao<Usuario> getDaoUsuario() throws DaoException {
        return DaoMongoUsuario.getInstancia();
    }

    public static Dao<GrupoComunitario> getDaoGrupoComunitario() throws DaoException {
        return DaoMongoGrupoComunitario.getInstancia();
    }
    
    public static Dao<GrupoUnitario> getDaoGrupoUnitario() throws DaoException {
        return DaoMongoGrupoUnitario.getInstancia();
    }

    public static Dao<Categoria> getDaoCategoria() throws DaoException {
        return DaoMongoCategoria.getInstancia();
    }

    public static Dao<Participante> getDaoParticipante() throws DaoException {
        return DaoMongoParticipante.getInstancia();
    }
}
