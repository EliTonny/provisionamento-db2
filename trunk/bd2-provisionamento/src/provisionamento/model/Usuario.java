package provisionamento.model;

import DaoMongoDB.ModeloBase;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends ModeloBase {

    private String nome;
    private char[] senha;
    private String email;
    private transient List<Mensagem> mensagens;

    public Usuario() {
        this.mensagens = new ArrayList<>();
        nome = "";
        email = "";
        senha = new char[0];
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public char[] getSenha() {
        return senha;
    }

    public void setSenha(char[] senha) {
        this.senha = senha;
    }

    public void setMensagem(String mensagem, GrupoUnitario grupo) {
        Mensagem novaMsg = new Mensagem(mensagem, grupo);
        this.mensagens.add(novaMsg);
    }

    public void removeMensagem(Mensagem mensagem) {
        this.mensagens.remove(mensagem);
    }

    public void removeMensagens() {
        this.mensagens.clear();
    }

    public List<Mensagem> getMensagens() {
        return this.mensagens;
    }

    public void iniMensagens() {
        if (this.mensagens == null) {
            this.mensagens = new ArrayList<>();
        }
    }

    /*@Override
    public boolean equals(Object o) {
        Usuario usuario;
        if (o instanceof Usuario) {
            usuario = (Usuario) o;
            System.out.println(this.getId() == usuario.getId());
            if (this.getId() == usuario.getId()) {
                return true;
            }
        }
        return false;
    }*/

    @Override
    public String toString() {
        return this.getNome();
    }
}
