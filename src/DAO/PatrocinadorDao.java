package DAO;

import models.Evento;
import models.Organizador;
import models.Patrocinador;
import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatrocinadorDao {
    private Connection connection;

    public PatrocinadorDao() throws ClassNotFoundException {
        this.connection = new ConnectionFactory().getConnection();
    }


    public List<Patrocinador> getAllPatrocinadores() {
        return null;
    }


    public boolean isPatrocinado(int evento, String patrocinador){
        try {
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from patrocina_eventos where patrocinadores_cpf_cnpj=? AND eventos_codigo=?");
            stmt.setString(1,patrocinador);
            stmt.setInt(2,evento);

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
    public void removerPatrocinio(int cod,String pat){
        try {
            PreparedStatement stmt = connection
                    .prepareStatement("delete from patrocina_eventos where eventos_codigo=? AND patrocinadores_cpf_cnpj=?");
            stmt.setInt(1, cod);
            stmt.setString(2, pat);
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Patrocinador loginPatrocinador(String usuario, String senha){
        try {
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from patrocinadores where usuario=? AND senha=?");
            stmt.setString(1,usuario);
            stmt.setString(2,senha);

            ResultSet rs = stmt.executeQuery();
            boolean retorno=rs.next();
            Patrocinador org;
            if(retorno)org=new Patrocinador(rs.getString("cpf_cnpj"),rs.getString("telefone"),rs.getString("usuario"),rs.getString("senha"),rs.getString("nome"));
            else org=null;
            rs.close();
            stmt.close();
            connection.close();
            return org;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void excluirPatrocinador(Patrocinador evento) {
        try {
            PreparedStatement stmt = connection
                    .prepareStatement("delete from patrocinadores where cpf_cnpj=?");
            stmt.setString(1, evento.getCpfCnpj());
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void inserirPatrocinio(int evento,String patrocinador) {
        try {
            PreparedStatement stmt = connection
                    .prepareStatement("insert into patrocina_eventos (eventos_codigo,patrocinadores_cpf_cnpj) values(?,?)");
            stmt.setInt(1, evento);
            stmt.setString(2, patrocinador);
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void updatePatrocinador(Patrocinador evento) {
        String sql = "update patrocinadores set" +
                "telefone=?,nome=?,usuario=?,senha=?" +
                " where cpf_cnpj=?";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores

            stmt.setString(1,evento.getTelefone());
            stmt.setString(2,evento.getNome());
            stmt.setString(3,evento.getUsuario());
            stmt.setString(4,evento.getSenha());
            stmt.setString(5,evento.getCpfCnpj());
            // executa
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void inserePatrocinador(Patrocinador evento) {
        String sql = "insert into patrocinadores " +
                "(telefone,nome,usuario,senha,cpf_cnpj)" +
                " values (?,?,?,?,?)";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores

            stmt.setString(1,evento.getTelefone());
            stmt.setString(2,evento.getNome());
            stmt.setString(3,evento.getUsuario());
            stmt.setString(4,evento.getSenha());
            stmt.setString(5,evento.getCpfCnpj());
            // executa
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean exists(Patrocinador org){
        try {
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from patrocinadores where cpf_cnpj=?");
            stmt.setString(1,org.getCpfCnpj());

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
