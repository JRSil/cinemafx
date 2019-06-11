package br.com.cinemafx.bean;

import java.util.Date;

public class Sessao {
    private Integer idSessao;
    private Integer idSala;
    private Date dia, hora;
    private boolean dublagem;
    private Integer idFilme;

    public Integer getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Integer idSessao) {
        this.idSessao = idSessao;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public boolean isDublagem() {
        return dublagem;
    }

    public void setDublagem(boolean dublagem) {
        this.dublagem = dublagem;
    }

    public Integer getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Integer idFilme) {
        this.idFilme = idFilme;
    }

    @Override
    public String toString() {
        return "Sala: " + this.idSala + ", Dia: " + this.dia; 
    }
}
