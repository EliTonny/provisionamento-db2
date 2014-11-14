package DaoArquivo;

import MyExceptions.DaoException;
import MyExceptions.GravaDadosException;
import Sistema.Dao;
import provisionamento.model.GrupoUnitario;
import provisionamento.model.Usuario;

public class DaoArquivoGrupoUnitario extends DaoArquivo<GrupoUnitario> implements Dao<GrupoUnitario> {

    private static DaoArquivoGrupoUnitario instancia;

    private DaoArquivoGrupoUnitario() throws DaoException {
        super(GrupoUnitario.class.getSimpleName());
    }

    public static DaoArquivoGrupoUnitario getInstancia() throws DaoException {
        if (instancia == null) {
            instancia = new DaoArquivoGrupoUnitario();
        }
        return instancia;
    }

    @Override
    public void grava(GrupoUnitario grupo) throws GravaDadosException {
        if (!estaGravado(grupo.getCriador())) {
            throw new GravaDadosException("Tentativa de gravar um Grupo Unitário que possui referência a outros objetos que não estão gravados");
        }
        if (!estaGravado(grupo.getCategoria())) {
            throw new GravaDadosException("Tentativa de gravar um Grupo Unitário que possui referência a outros objetos que não estão gravados");
        }
        super.grava(grupo);
    }

    @Override
    public GrupoUnitario busca(String descricao) {
        for (GrupoUnitario val : this.dados.buscaTodos()) {
            if (val.getDescricao().equals(descricao)) {
                return val;
            }
        }
        return null;
    }

    @Override
    protected void AtualizaReferencias() throws DaoException {
        try {
            Usuario u;
            Dao<Usuario> DaoUsuario = DaoArquivoUsuario.getInstancia();
            for (GrupoUnitario g : this.dados.buscaTodos()) {
                u = DaoUsuario.busca(g.getCriador().getId());
                g.setCriador(u);
            }
        } catch (DaoException ex) {
            throw new DaoException("Erro ao atualizar as referencias.", ex);
        }
    }
}
