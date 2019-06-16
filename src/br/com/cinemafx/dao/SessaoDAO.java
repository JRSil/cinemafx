package br.com.cinemafx.dao;

import br.com.cinemafx.bean.Sessao;
import br.com.cinemafx.util.Conector;
import br.com.cinemafx.util.Dao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessaoDAO implements Dao {

    @Override
    public void insert(Object o) {
        Sessao s = (Sessao) o;
        String sql = "insert into sessao (idSala,dia,hora,dublagem,idFilme) values (?,?,?,?,?)";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            
            ps.setInt(1, s.getIdSala());
            ps.setDate(2, (Date) s.getDia());
            ps.setString(3, s.getHora());
            ps.setBoolean(4, s.isDublagem());
            ps.setInt(5, s.getIdFilme());
            
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object o) {
        Sessao s = (Sessao) o;
        String sql = "update sessao set idSala=?,dia=?,hora=?,dublagem=?,idFilme=? where idSessao=?";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            
            ps.setInt(1, s.getIdSala());
            ps.setDate(2, (Date) s.getDia());
            ps.setString(3, s.getHora());
            ps.setBoolean(4, s.isDublagem());
            ps.setInt(5, s.getIdFilme());
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object o) {
        Sessao s = (Sessao) o;
        String sql = "delete from sessao where idSessao=?";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            
            ps.setInt(1, s.getIdSessao());
            
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Object select(int i) {
        Sessao s = new Sessao();
        String sql = "select * from sessao where idSessao=?";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                s.setIdSala(rs.getInt("idSala"));
                s.setDia(rs.getDate("dia"));
                s.setHora(rs.getString("hora"));
                s.setDublagem(rs.getBoolean("dublagem"));
                s.setIdFilme(rs.getInt("idFilme"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public List select() {
        List list = new ArrayList();
        String sql = "select * from sessao";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Sessao s = new Sessao();
                
                s.setIdSessao(rs.getInt("idSessao"));
                s.setIdSala(rs.getInt("idSala"));
                s.setDia(rs.getDate("dia"));
                s.setHora(rs.getString("hora"));
                s.setDublagem(rs.getBoolean("dublagem"));
                s.setIdFilme(rs.getInt("idFilme"));
                
                list.add(s);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    
}
