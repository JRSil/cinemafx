package br.com.cinemafx.dao;

import br.com.cinemafx.bean.Sala;
import br.com.cinemafx.util.Conector;
import br.com.cinemafx.util.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO implements Dao {

    @Override
    public void insert(Object o) {
        Sala s = (Sala) o;
        String sql = "insert into sala (tipoImagem,qtdMax) values (?,?)";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            
            ps.setString(1, s.getTipoImagem());
            ps.setInt(2, s.getQtdMax());
            
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object o) {
        Sala s = (Sala) o;
        String sql = "update sala set tipoImagem=?,qtdMax=? where idSala=?";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            
            ps.setString(1, s.getTipoImagem());
            ps.setInt(2, s.getQtdMax());
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object o) {
        Sala s = (Sala) o;
        String sql = "delete from sala where idSala=?";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            
            ps.setInt(1, s.getIdSala());
            
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Object select(int i) {
        Sala s = new Sala();
        String sql = "select * from sala where idSala=?";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                s.setTipoImagem(rs.getString("tipoImagem"));
                s.setQtdMax(rs.getInt("qtdMax"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public List select() {
        List list = new ArrayList();
        String sql = "select * from sala";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Sala s = new Sala();
                
                s.setIdSala(rs.getInt("idSala"));
                s.setTipoImagem(rs.getString("tipoImagem"));
                s.setQtdMax(rs.getInt("qtdMax"));
                
                list.add(s);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    
}
