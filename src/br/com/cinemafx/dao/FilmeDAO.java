package br.com.cinemafx.dao;

import br.com.cinemafx.bean.Filme;
import br.com.cinemafx.util.Conector;
import br.com.cinemafx.util.Dao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO implements Dao {

    @Override
    public void insert(Object o) {
        Filme f = (Filme) o;
        String sql = "insert into filme (nomeFilme,classificacao,duracao,cartaz,vigencia,categoria) values (?,?,?,?,?,?)";
        
        try{
            
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            
            ps.setString(1, f.getNomeFilme());
            ps.setInt(2, f.getClassificacao());
            ps.setFloat(3, f.getDuracao());
            ps.setBoolean(4, f.isCartaz());
            ps.setDate(5, (Date) f.getVigencia());
            ps.setString(6, f.getCategoria());
            
            ps.execute();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object o) {
        Filme f = (Filme) o;
        String sql = "update filme set nomeFilme=?,classificacao=?,duracao=?,cartaz=?,vigencia=?,categoria=? where idFilme=?";
        
        try{
            
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            
            ps.setString(1, f.getNomeFilme());
            ps.setInt(2, f.getClassificacao());
            ps.setFloat(3, f.getDuracao());
            ps.setBoolean(4, f.isCartaz());
            ps.setDate(5, (Date) f.getVigencia());
            ps.setString(6, f.getCategoria());
            ps.setInt(7, f.getIdFilme());
            
            ps.execute();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object o) {
        Filme f = (Filme) o;
        String sql = "delete from filme where idFilme=?";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            
            ps.setInt(1, f.getIdFilme());
            
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Object select(int i) {
        Filme f = new Filme();
        
        String sql = "select * from filme where idFilme=?";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                f.setNomeFilme(rs.getString("nomeFilme"));
                f.setClassificacao(rs.getInt("classificacao"));
                f.setDuracao(rs.getFloat("duracao"));
                f.setCartaz(rs.getBoolean("cartaz"));
                f.setVigencia(rs.getDate("vigencia"));
                f.setCategoria(rs.getString("categoria"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public List select() {
        List list = new ArrayList();
        
        String sql = "select * from filme";
        try{
            
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Filme f = new Filme();
                
                f.setIdFilme(rs.getInt("idFilme"));
                f.setNomeFilme(rs.getString("nomeFilme"));
                f.setClassificacao(rs.getInt("classificacao"));
                f.setDuracao(rs.getFloat("duracao"));
                f.setCartaz(rs.getBoolean("cartaz"));
                f.setVigencia(rs.getDate("vigencia"));
                f.setCategoria(rs.getString("categoria"));
                
                list.add(f);
                
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    
}
