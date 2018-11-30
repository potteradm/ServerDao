package DAO;

import models.Local;
import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalDao {

    private Connection connection;

    public LocalDao() throws ClassNotFoundException {
        this.connection = new ConnectionFactory().getConnection();
    }

    public List<Local> getAllLocais() {
        try {
            List<Local> locais = new ArrayList<Local>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from locais");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Contato
                Local local = new Local(rs.getInt("codigo"),rs.getString("nome"),rs.getString("endereco"),rs.getString("observacoes"));
                // adicionando o objeto à lista
                locais.add(local);
            }
            rs.close();
            stmt.close();
            connection.close();
            return locais;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Local getLocalByCodigo(int codigo) {
        try {
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from locais where codigo=?");
            stmt.setInt(1,codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // criando o objeto Contato
                Local local = new Local(rs.getInt("codigo"),rs.getString("nome"),rs.getString("endereco"),rs.getString("observacoes"));
                // adicionando o objeto à lista
                rs.close();
                stmt.close();
                connection.close();

                return local;
            }
            rs.close();
            stmt.close();
            connection.close();
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirLocal(Local local) {
        try {
            PreparedStatement stmt = connection
                    .prepareStatement("delete from locais where codigo=?");
            stmt.setInt(1, local.getCodigo());
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateLocal(Local local) {
        String sql = "update locais set" +
                "(nome=?,endereco=?,observacoes=?)" +
                " where codigo=?";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores

            stmt.setString(1,local.getNome());
            stmt.setString(2,local.getEndereco());
            stmt.setString(3,local.getObservacoes());
            stmt.setInt(3,local.getCodigo());
            // executa
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insereLocal(Local local) {
        String sql = "insert into locais " +
                "(nome,endereco,observacoes)" +
                " values (?,?,?)";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores

            stmt.setString(1,local.getNome());
            stmt.setString(2,local.getEndereco());
            stmt.setString(3,local.getObservacoes());
            // executa
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Local> buscarLocal(String nome){
        try {
            List<Local> locais = new ArrayList<Local>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from locais where nome like ?");
            stmt.setString(1,"%"+nome+"%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Contato
                Local local = new Local(rs.getInt("codigo"),rs.getString("nome"),rs.getString("endereco"),rs.getString("observacoes"));
                // adicionando o objeto à lista
                locais.add(local);
            }
            rs.close();
            stmt.close();
            connection.close();
            return locais;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
