package br.com.cinemafx.bean;

import java.util.Date;

public class Filme {
    private Integer idFilme;
    private String nomeFilme;
    private Integer classificacao;
    private float duracao;
    private boolean cartaz;
    private Date vigencia = new Date();
    private String categoria;

    public Integer getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Integer idFilme) {
        this.idFilme = idFilme;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nome) {
        this.nomeFilme = nome;
    }

    public Integer getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Integer classificacao) {
        this.classificacao = classificacao;
    }

    public float getDuracao() {
        return duracao;
    }

    public void setDuracao(float duracao) {
        this.duracao = duracao;
    }

    public boolean isCartaz() {
        return cartaz;
    }

    public void setCartaz(boolean cartaz) {
        this.cartaz = cartaz;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
