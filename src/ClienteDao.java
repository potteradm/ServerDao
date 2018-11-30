import models.*;

import java.util.ArrayList;

public interface ClienteDao {
    public ArrayList<Evento> getAllEventos();
    public ArrayList<Evento> getEventosByOrganizador(String organizador);
    public ArrayList<Local> getAllLocais();
    public Evento getEventosById(int evento);
    public Local getLocalEvento(int evento);

    public void salvarLocal(Local local);//se codigo estiver setado => comando update;
    public void deletarLocal(Local local);
    public ArrayList<Local> buscarLocal(String nome);

    public void salvarEvento(Evento evento);//se codigo estiver setado => comando update;
    public void deletarEvento(Evento evento);
    public ArrayList<Evento> buscarEventos(String nome);

    public void salvarTipoIngresso(TipoIngresso ingresso);//se codigo estiver setado => comando update;
    public void deletarTipoIngresso(TipoIngresso ingresso);

    public ArrayList<TipoIngresso> buscarTipoIngresso(String nome, String organizador);
    public ArrayList<TipoIngresso> getTipoIngressosOrganizador(String organizador);
    public ArrayList<TipoIngresso> getTipoIngressoEvento(int codigo);
    public TipoIngresso getTipoIngresso(int id);

    public ArrayList<Evento> getEventosPatrocinio(String patrocinador);
    public boolean isPatrocinado(int evento, String patrocinador);
    public void removerPatrocinio(int evento, String patrocinador);
    public void adicionarPatrocinio(int evento, String patrocinador);

    public boolean criarOrganizador(Organizador organizador);

    public boolean criarPatrocinador(Patrocinador organizador);

    public Organizador loginOrganizador(String usuario,String senha);
    public Patrocinador loginPatrocinador(String usuario,String senha);



}
