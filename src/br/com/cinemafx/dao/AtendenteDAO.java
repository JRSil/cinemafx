package br.com.cinemafx.dao;

import br.com.cinemafx.bean.Atendente;
import br.com.cinemafx.util.Conector;
import br.com.cinemafx.util.Dao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtendenteDAO implements Dao {

    @Override
    public void insert(Object o) {
        Atendente a = (Atendente) o;
        String sql = "insert into atendente (nome,CPF,nasc,email,user,senha,tipo) values (?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            
            ps.setString(1, a.getNome());
            ps.setString(2, a.getCPF());
            ps.setDate(3, (Date) a.getNasc());
            ps.setString(4, a.getEmail());
            ps.setString(5, a.getUser());
            ps.setString(6, a.getSenha());
            ps.setString(7, a.getTipo());
            
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object o) {
        Atendente a = (Atendente) o;
        String sql = "update atendente set nome=?,cpf=?,nasc=?,email=?,user=?,senha=?,tipo=? where idAtendente=?";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            
            ps.setString(1, a.getNome());
            ps.setString(2, a.getCPF());
            ps.setDate(3, (Date) a.getNasc());
            ps.setString(4, a.getEmail());
            ps.setString(5, a.getUser());
            ps.setString(6, a.getSenha());
            ps.setString(7, a.getTipo());
            ps.setInt(8, a.getIdAtendente());
            
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object o) {
        Atendente a = (Atendente) o;
        String sql = "delete from atendente where idAtendente=?";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            
            ps.setInt(1, a.getIdAtendente());
            
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Object select(int i) {
        Atendente a = new Atendente();
        String sql = "select * from atendente where idAtendente=?";
        
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                a.setNome(rs.getString("nome"));
                a.setCPF(rs.getString("cpf"));
                a.setNasc(rs.getDate("nasc"));
                a.setEmail(rs.getString("email"));
                a.setUser(rs.getString("user"));
                a.setSenha(rs.getString("senha"));
                a.setTipo(rs.getString("tipo"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public List select() {
        List list = new ArrayList();
        String sql = "select * from atendente";
        try{
            PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Atendente a = new Atendente();
                
                a.setIdAtendente(rs.getInt("idAtendente"));
                a.setNome(rs.getString("nome"));
                a.setCPF(rs.getString("cpf"));
                a.setNasc(rs.getDate("nasc"));
                a.setEmail(rs.getString("email"));
                a.setUser(rs.getString("user"));
                a.setSenha(rs.getString("senha"));
                a.setTipo(rs.getString("tipo"));
                
                list.add(a);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    
}
