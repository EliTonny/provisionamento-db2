package DaoArquivo;

import MyExceptions.DaoException;
import MyExceptions.GravaDadosException;
import Sistema.Dao;
import provisionamento.model.GrupoComunitario;
import provisionamento.model.Participante;
import provisionamento.model.Usuario;

public class DaoArquivoGrupoComunitario extends DaoArquivo<GrupoComunitario> implements Dao<GrupoComunitario> {

    private static DaoArquivoGrupoComunitario instancia;

    private DaoArquivoGrupoComunitario() throws DaoException {
        super(GrupoComunitario.class.getSimpleName());
    }

    public static DaoArquivoGrupoComunitario getInstancia() throws DaoException {
        if (instancia == null) {
            instancia = new DaoArquivoGrupoComunitario();
        }
        return instancia;
    }

    @Override
    public void grava(GrupoComunitario grupo) throws GravaDadosException {
        if (!estaGravado(grupo.getCriador())) {
            throw new GravaDadosException("Tentativa de gravar um Grupo Comunitário que possui referência a outros objetos que não estão gravados");
        }
        if (!estaGravado(grupo.getCategoria())) {
            throw new GravaDadosException("Tentativa de gravar um Grupo Comunitário que possui referência a outros objetos que não estão gravados");
        }
        for (Participante p : grupo.getParticipantes()) {
            if (!estaGravado(p)) {
                throw new GravaDadosException("Tentativa de gravar um Grupo Comunitário que possui referência a outros objetos que não estão gravados");
            }
        }
        super.grava(grupo);
    }

    @Override
    public GrupoComunitario busca(String descricao) {
        for (GrupoComunitario val : this.dados.buscaTodos()) {
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
            Dao<Participante> DaoParticipante = DaoArquivoParticipante.getInstancia();
            for (GrupoComunitario g : this.dados.buscaTodos()) {
                u = DaoUsuario.busca(g.getCriador().getId());
                g.setCriador(u);

                for (int i = 0; i < g.getParticipantes().size(); i++) {
                    g.getParticipantes().set(i, DaoParticipante.busca(g.getParticipantes().get(i).getId()));
                }

                //assim não funciona
                /*for (Participante p : g.getParticipantes()) {
                 p = DaoParticipante.busca(p.getId());
                 int i = 0;
                 }*/
            }
        } catch (DaoException ex) {
            throw new DaoException("Erro ao atualizar as referencias.", ex);
        }
    }
}
