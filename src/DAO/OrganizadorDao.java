package DAO;

import models.Organizador;
import models.Patrocinador;
import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrganizadorDao  {

    private Connection connection;

    public OrganizadorDao() throws ClassNotFoundException {
        this.connection = new ConnectionFactory().getConnection();
    }

    public ArrayList<Organizador> getAllOrganizadores() {
        return null;
    }

    public Organizador getOrganizadorByCodigo(String codigo) {
        return null;
    }


    public Organizador loginOrganizador(String usuario,String senha){
        try {
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from organizadores where usuario=? AND senha=?");
            stmt.setString(1,usuario);
            stmt.setString(2,senha);

            ResultSet rs = stmt.executeQuery();
            boolean retorno=rs.next();
            Organizador org;
            if(retorno)org=new Organizador(rs.getString("cpf_cnpj"),rs.getString("email"),rs.getString("telefone"),rs.getString("nome"),rs.getString("usuario"),rs.getString("senha"));
            else org=null;
            rs.close();
            stmt.close();
            connection.close();
            return org;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void excluirOrganizador(Organizador evento) {
        try {
            PreparedStatement stmt = connection
                    .prepareStatement("delete from organizadores where cpf_cnpj=?");
            stmt.setString(1, evento.getCpf_cnpj());
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOrganizador(Organizador evento) {
        String sql = "update organizadores set" +
                "(email=?,telefone=?,nome=?,usuario=?,senha=?)" +
                " where cpf_cnpj=?";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores

            stmt.setString(1,evento.getEmail());
            stmt.setString(2,evento.getTelefone());
            stmt.setString(3,evento.getNome());
            stmt.setString(4,evento.getUsuario());
            stmt.setString(5,evento.getSenha());
            stmt.setString(6,evento.getCpf_cnpj());
            // executa
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insereOrganizador(Organizador evento) {
        String sql = "insert into organizadores " +
                "(email,telefone,nome,usuario,senha,cpf_cnpj)" +
                " values (?,?,?,?,?,?)";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores
            stmt.setString(1,evento.getEmail());
            stmt.setString(2,evento.getTelefone());
            stmt.setString(3,evento.getNome());
            stmt.setString(4,evento.getUsuario());
            stmt.setString(5,evento.getSenha());
            stmt.setString(6,evento.getCpf_cnpj());
            // executa
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean exists(Organizador org){
        try {
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from organizadores where cpf_cnpj=?");
            stmt.setString(1,org.getCpf_cnpj());

            ResultSet rs = stmt.executeQuery();
            boolean retorno=rs.next();
            rs.close();
            stmt.close();
            connection.close();
            return retorno;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
