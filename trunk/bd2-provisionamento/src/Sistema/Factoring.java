package Sistema;

import DaoArquivo.DaoArquivoCategoria;
import DaoArquivo.DaoArquivoGrupoComunitario;
import DaoArquivo.DaoArquivoGrupoUnitario;
import DaoArquivo.DaoArquivoParticipante;
import DaoArquivo.DaoArquivoUsuario;
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
        return DaoArquivoUsuario.getInstancia();
    }

    public static Dao<GrupoComunitario> getDaoGrupoComunitario() throws DaoException {
        return DaoArquivoGrupoComunitario.getInstancia();
    }
    
    public static Dao<GrupoUnitario> getDaoGrupoUnitario() throws DaoException {
        return DaoArquivoGrupoUnitario.getInstancia();
    }

    public static Dao<Categoria> getDaoCategoria() throws DaoException {
        return DaoArquivoCategoria.getInstancia();
    }

    public static Dao<Participante> getDaoParticipante() throws DaoException {
        return DaoArquivoParticipante.getInstancia();
    }
}
