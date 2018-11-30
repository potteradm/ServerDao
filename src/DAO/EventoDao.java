package DAO;

import models.Evento;
import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventoDao {

    private Connection connection;

    public EventoDao() throws ClassNotFoundException {
        this.connection = new ConnectionFactory().getConnection();
    }

    public List<Evento> getAllEventos() {
        try {
            List<Evento> eventos = new ArrayList<>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from eventos");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Contato
               Evento evento = new Evento(rs.getInt("codigo"),rs.getString("descricao"),rs.getString("fim")
                        ,rs.getString("inicio"),rs.getString("nome"),rs.getInt("capacidade"),rs.getInt("local_codigo"),
                        rs.getString("organizador_cpf_cnpj"),rs.getString("img_link"),rs.getString("img_link2"));
                // adicionando o objeto à lista
                eventos.add(evento);
            }
            rs.close();
            stmt.close();
            connection.close();
            return eventos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Evento getEventoByCodigo(int codigo) {
        try {
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from eventos where codigo=?");
            stmt.setInt(1,codigo);
            ResultSet rs = stmt.executeQuery();

            rs.next();
                // criando o objeto Contato
                Evento evento = new Evento(rs.getInt("codigo"),rs.getString("descricao"),rs.getString("fim")
                        ,rs.getString("inicio"),rs.getString("nome"),rs.getInt("capacidade"),rs.getInt("local_codigo"),
                        rs.getString("organizador_cpf_cnpj"),rs.getString("img_link"),rs.getString("img_link2"));
                // adicionando o objeto à lista
            rs.close();
            stmt.close();
            connection.close();
            return evento;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Evento> getEventoByOrganizador(String codigo) {
        try {
            ArrayList<Evento> eventos = new ArrayList<>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from eventos where organizador_cpf_cnpj=?");
            stmt.setString(1,codigo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Contato
                Evento evento = new Evento(rs.getInt("codigo"),rs.getString("descricao"),rs.getString("fim")
                        ,rs.getString("inicio"),rs.getString("nome"),rs.getInt("capacidade"),rs.getInt("local_codigo"),
                        rs.getString("organizador_cpf_cnpj"),rs.getString("img_link"),rs.getString("img_link2"));
                // adicionando o objeto à lista
                eventos.add(evento);
            }
            rs.close();
            stmt.close();
            connection.close();
            return eventos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Evento> buscarEventos(String nome) {
        try {
            List<Evento> eventos = new ArrayList<>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from eventos where nome like ?");
            stmt.setString(1,"%"+nome+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Contato
                Evento evento = new Evento(rs.getInt("codigo"),rs.getString("descricao"),rs.getString("fim")
                        ,rs.getString("inicio"),rs.getString("nome"),rs.getInt("capacidade"),rs.getInt("local_codigo"),
                        rs.getString("organizador_cpf_cnpj"),rs.getString("img_link"),rs.getString("img_link2"));
                // adicionando o objeto à lista
                eventos.add(evento);
            }
            rs.close();
            stmt.close();
            connection.close();
            return eventos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void excluirEvento(Evento evento) {
        try {
            PreparedStatement stmt = connection
                    .prepareStatement("delete from eventos where codigo=?");
            stmt.setInt(1, evento.getCodigo());
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEvento(Evento evento) {
        String sql = "update eventos set" +
                "descricao=?,fim=?,inicio=?,nome=?,capacidade=?,local_codigo=?,organizador_cpf_cnpj=?,img_link=?,img_link2=?" +
                " where codigo=?";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores

            stmt.setString(1,evento.getDescricao());
            stmt.setString(2,evento.getFim());
            stmt.setString(3,evento.getInicio());
            stmt.setString(4,evento.getNome());
            stmt.setInt(5,evento.getCapacidade());
            stmt.setInt(6,evento.getLocal_codigo());


            stmt.setString(7,evento.getOrganizador_cpf_cnpj());
            stmt.setString(8,evento.getImg_link1());
            stmt.setString(9,evento.getImg_link2());
            stmt.setInt(10,evento.getCodigo());

            // executa
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insereEvento(Evento evento) {
        String sql = "insert into eventos " +
                "(descricao,fim,inicio,nome,capacidade,local_codigo,organizador_cpf_cnpj,img_link,img_link2)" +
                " values (?,?,?,?,?,?,?,?,?)";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores

            stmt.setString(1,evento.getDescricao());
            stmt.setString(2,evento.getFim());
            stmt.setString(3,evento.getInicio());
            stmt.setString(4,evento.getNome());
            stmt.setInt(5,evento.getCapacidade());
            stmt.setInt(6,evento.getLocal_codigo());


            stmt.setString(7,evento.getOrganizador_cpf_cnpj());
            stmt.setString(8,evento.getImg_link1());
            stmt.setString(9,evento.getImg_link2());

            // executa
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
