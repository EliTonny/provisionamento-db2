package provisionamento.controller;

import MyExceptions.DaoException;
import Sistema.ConcreteSubject;
import Sistema.Dao;
import Sistema.Factoring;
import java.util.ArrayList;
import java.util.List;
import provisionamento.model.*;

public class FramesController {

    public void gravar(Usuario usu) throws DaoException, Exception {
        if (this.buscarUsuario(usu.getNome()) != null) {
            throw new Exception("Usuário " + usu.getNome() + " já cadastrado.");
        }
        Dao<Usuario> dao = Factoring.getDaoUsuario();
        dao.grava(usu);
        this.notificar(usu);
    }

    public void gravar(Categoria cat) throws DaoException {
        Dao<Categoria> dao = Factoring.getDaoCategoria();
        dao.grava(cat);
        this.notificar(cat);
    }

    public void gravar(GrupoUnitario grupoUnitario) throws DaoException {
        Dao<GrupoUnitario> dao = Factoring.getDaoGrupoUnitario();
        dao.grava(grupoUnitario);
        this.notificar(grupoUnitario);
    }

    public void gravar(GrupoComunitario grupoComunitario) throws DaoException {
        Dao<GrupoComunitario> dao = Factoring.getDaoGrupoComunitario();
        dao.grava(grupoComunitario);
        this.notificar(grupoComunitario);
    }

    public void gravar(Participante participante) throws DaoException {
        Dao<Participante> dao = Factoring.getDaoParticipante();
        dao.grava(participante);
    }

    public Usuario buscarUsuario(String nome) throws DaoException {
        Dao<Usuario> dao = Factoring.getDaoUsuario();
        return dao.busca(nome.trim());
    }

    public List buscarCategoria() throws DaoException {
        Dao<Categoria> daoCat = Factoring.getDaoCategoria();
        return daoCat.busca();
    }

    public List buscarUsuario() throws DaoException {
        Dao<Usuario> daoUsu = Factoring.getDaoUsuario();
        return daoUsu.busca();
    }

    public void atualizarSituacaoParticipante(Participante p, boolean pago) throws DaoException {
        p.setPago(pago);
        Factoring.getDaoParticipante().grava(p);
    }

    public void pagarGrupo(GrupoComunitario grupo) throws DaoException {
        grupo.setPago(true);
        Factoring.getDaoGrupoComunitario().grava(grupo);
    }

    public ArrayList<GrupoComunitario> buscarGruposCriador(Usuario usu) throws DaoException {
        Dao<GrupoComunitario> daoG = Factoring.getDaoGrupoComunitario();
        ArrayList<GrupoComunitario> grupos = new ArrayList<>();
        for (GrupoComunitario g : daoG.busca()) {
            if (!g.isPago()) {
                if (g.getCriador().equals(usu)) {
                    grupos.add(g);
                }
            }
        }
        return grupos;
    }

    public ArrayList<GrupoUnitario> buscarGruposPessoais(Usuario usu) throws DaoException {
        Dao<GrupoUnitario> daoG = Factoring.getDaoGrupoUnitario();
        ArrayList<GrupoUnitario> grupos = new ArrayList<>();
        for (GrupoUnitario g : daoG.busca()) {
            if (!g.isFinalizado()) {
                if (g.getCriador().equals(usu)) {
                    grupos.add(g);
                }
            }
        }
        return grupos;
    }

    public ArrayList<GrupoComunitario> buscarGruposParticipante(Usuario usu) throws DaoException {
        Dao<GrupoComunitario> daoG = Factoring.getDaoGrupoComunitario();
        ArrayList<GrupoComunitario> grupos = new ArrayList<>();
        for (GrupoComunitario g : daoG.busca()) {
            //if (!g.isPago()) {
            for (Participante p : g.getParticipantes()) {
                if (p.getUsuario().equals(usu)
                        && !p.isPago()) {
                    grupos.add(g);
                    break;
                }
            }
            //}
        }
        return grupos;
    }

    public void notificar(Object obj) {
        ConcreteSubject.getInstancia().notifyObservers(obj);
    }
}
