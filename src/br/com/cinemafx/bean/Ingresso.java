package br.com.cinemafx.bean;

public class Ingresso {
    private Integer idIngresso;
    private Integer idSessao;
    private int quantidade;
    private float total;
    private String formaPgmt;

    public Integer getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(Integer idIngresso) {
        this.idIngresso = idIngresso;
    }

    public Integer getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Integer idSessao) {
        this.idSessao = idSessao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFormaPgmt() {
        return formaPgmt;
    }

    public void setFormaPgmt(String formaPgmt) {
        this.formaPgmt = formaPgmt;
    }
}
