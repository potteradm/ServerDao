package DAO;

import models.Local;
import models.TipoIngresso;
import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoIngressoDao{


    private Connection connection;

    public TipoIngressoDao() throws ClassNotFoundException {
        this.connection = new ConnectionFactory().getConnection();
    }

    public ArrayList<TipoIngresso> getTipoIngressoOrganizador(String organizador){
        try {
            ArrayList<TipoIngresso> locais = new ArrayList<>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from tiposdeingresso as t, eventos as e where e.codigo=t.evento_codigo AND e.organizador_cpf_cnpj=?");
            stmt.setString(1,organizador);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                // criando o objeto Contato
                TipoIngresso tp = new TipoIngresso(rs.getInt("codigo"),rs.getString("nome"),rs.getString("descricao"),rs.getDouble("preco"),rs.getInt("evento_codigo"));
                // adicionando o objeto à lista
                locais.add(tp);
            }
            rs.close();
            stmt.close();
            connection.close();
            return locais;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public ArrayList<TipoIngresso> buscarTipoIngresso(String nome, String organizador){
        try {
            ArrayList<TipoIngresso> locais = new ArrayList<>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from tiposdeingresso as t, eventos as e where e.codigo=t.evento_codigo AND e.organizador_cpf_cnpj=? AND t.nome LIKE ?");
            stmt.setString(1,organizador);
            stmt.setString(2,'%'+nome+'%');
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Contato
                TipoIngresso tp = new TipoIngresso(rs.getInt("codigo"),rs.getString("nome"),rs.getString("descricao"),rs.getDouble("preco"),rs.getInt("evento_codigo"));
                // adicionando o objeto à lista
                locais.add(tp);
            }
            rs.close();
            stmt.close();
            connection.close();
            return locais;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<TipoIngresso> getTipoIngressoEvento(int codigo){
        try {
            ArrayList<TipoIngresso> locais = new ArrayList<>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from tiposdeingresso where evento_codigo=?");
            stmt.setInt(1,codigo);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                // criando o objeto Contato
                TipoIngresso tp = new TipoIngresso(rs.getInt("codigo"),rs.getString("nome"),rs.getString("descricao"),rs.getDouble("preco"),rs.getInt("evento_codigo"));
                // adicionando o objeto à lista
                locais.add(tp);
            }
            rs.close();
            stmt.close();
            connection.close();
            return locais;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public TipoIngresso getTipoIngresso(int codigo){
        try {
            ArrayList<TipoIngresso> locais = new ArrayList<>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from tiposdeingresso where codigo=?");
            stmt.setInt(1,codigo);
            ResultSet rs = stmt.executeQuery();
            TipoIngresso tp=null;
            if(rs.next()) {
                // criando o objeto Contato
                tp = new TipoIngresso(rs.getInt("codigo"),rs.getString("nome"),rs.getString("descricao"),rs.getDouble("preco"),rs.getInt("evento_codigo"));
                // adicionando o objeto à lista
            }
            rs.close();
            stmt.close();
            connection.close();
            return tp;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void excluirIngresso(TipoIngresso evento){

        try {
            PreparedStatement stmt = connection
                    .prepareStatement("delete from tiposdeingresso where codigo=?");
            stmt.setInt(1, evento.getCodigo());
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void updateIngresso(TipoIngresso evento){
        String sql="update tiposdeingresso set nome=?,descricao=?,preco=?,evento_codigo=? where codigo=?";


        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores

            stmt.setString(1,evento.getNome());
            stmt.setString(2,evento.getDescricao());
            stmt.setDouble(3,evento.getPreco());
            stmt.setInt(4,evento.getEvento_codigo());
            stmt.setInt(5,evento.getCodigo());

            // executa
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }
    public void insereIngresso(TipoIngresso evento){
        String sql="insert into tiposdeingresso(nome,descricao,preco,evento_codigo) values (?,?,?,?)";


        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores

            stmt.setString(1,evento.getNome());
            stmt.setString(2,evento.getDescricao());
            stmt.setDouble(3,evento.getPreco());
            stmt.setInt(4,evento.getEvento_codigo());


            // executa
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }


//
//    public ArrayList<TipoIngresso> buscarTipoIngresso(String nome, String organizador);
//    public ArrayList<TipoIngresso> getTipoIngressosOrganizador(String organizador);
//    public ArrayList<TipoIngresso> getTipoIngressoEvento(int codigo);
//    public TipoIngresso getTipoIngresso(int id);

}
