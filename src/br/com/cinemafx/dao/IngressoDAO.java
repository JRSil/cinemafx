package br.com.cinemafx.dao;

import br.com.cinemafx.bean.Ingresso;
import br.com.cinemafx.util.Conector;
import br.com.cinemafx.util.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngressoDAO implements Dao {

    @Override
    public void insert(Object o) {
        Ingresso ing = (Ingresso) o;
        String sql = "insert into ingresso (idSessao,quantidade,total,formaPgmt) values (?,?,?,?)";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            
            ps.setInt(1, ing.getIdSessao());
            ps.setInt(2, ing.getQuantidade());
            ps.setFloat(3, ing.getTotal());
            ps.setString(4, ing.getFormaPgmt());
            
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object o) {
        Ingresso ing = (Ingresso) o;
        String sql = "update ingresso set idSessao=?,quantidade=?,total=?,formaPgmt=? where idIngresso=?";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            
            ps.setInt(1, ing.getIdSessao());
            ps.setInt(2, ing.getQuantidade());
            ps.setFloat(3, ing.getTotal());
            ps.setString(4, ing.getFormaPgmt());
            
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object o) {
        Ingresso ing = (Ingresso) o;
        String sql = "delete from ingresso where idIngresso=?";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            
            ps.setInt(1, ing.getIdIngresso());
            
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Object select(int i) {
        Ingresso ing = new Ingresso();
        String sql = "select * from ingresso where idIngresso=?";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ing.setIdSessao(rs.getInt("idSessao"));
                ing.setQuantidade(rs.getInt("quantidade"));
                ing.setTotal(rs.getFloat("total"));
                ing.setFormaPgmt(rs.getString("formaPgmt"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ing;
    }

    @Override
    public List select() {
        List list = new ArrayList();
        String sql = "select * from ingresso";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Ingresso ing = new Ingresso();
                
                ing.setIdIngresso(rs.getInt("idIngresso"));
                ing.setIdSessao(rs.getInt("idSessao"));
                ing.setQuantidade(rs.getInt("quantidade"));
                ing.setTotal(rs.getFloat("total"));
                ing.setFormaPgmt(rs.getString("formaPgmt"));
                
                list.add(ing);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    
}
