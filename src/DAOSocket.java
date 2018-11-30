import DAO.*;
import models.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DAOSocket implements Runnable {
    private Socket cliente;
    private ObjectOutputStream fSaida;
    private ObjectInputStream fEntrada;
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        ServerSocket server=new ServerSocket(5005);
        while(true){
            new Thread(new DAOSocket(server.accept())).run();
        }



    }


    public DAOSocket(Socket cliente) throws IOException {
        // TODO Auto-generated constructor stub
        this.cliente=cliente;
        this.fSaida=new ObjectOutputStream(cliente.getOutputStream());
        this.fEntrada=new ObjectInputStream(cliente.getInputStream());
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {


            while (true) {

                String r = fEntrada.readUTF();
                System.out.println(r);

                if (r.equalsIgnoreCase("GETALLEVENTOS")) {
                    System.out.println("entendi");
                    EventoDao eventoDao = new EventoDao();
                    fSaida.writeObject(eventoDao.getAllEventos());
                    fSaida.flush();
                }
                else if(r.equalsIgnoreCase("GETALLEVENTOS_ORG")){
                    System.out.println("entendi");
                    String msg2=fEntrada.readUTF();
                    System.out.println(msg2);
                    EventoDao eventoDao = new EventoDao();
                    fSaida.writeObject(eventoDao.getEventoByOrganizador(msg2));
                    fSaida.flush();
                }

                else if(r.equalsIgnoreCase("GETALLLOCAIS")){
                    System.out.println("entendi");
                    LocalDao localDao = new LocalDao();
                    fSaida.writeObject(localDao.getAllLocais());
                    fSaida.flush();
                }
                else if(r.equalsIgnoreCase("GETEVENTO_ID")){
                    System.out.println("entendi");
                    int val=fEntrada.read();
                    System.out.println(val);
                    EventoDao eventoDao = new EventoDao();
                    fSaida.writeObject(eventoDao.getEventoByCodigo(val));
                    fSaida.flush();
                }
                else if(r.equalsIgnoreCase("GETLOCAL_EVENTO")) {
                    System.out.println("entendi");
                    int val=fEntrada.read();
                    System.out.println(val);
                    EventoDao eventoDao = new EventoDao();
                    int codlugar=eventoDao.getEventoByCodigo(val).getLocal_codigo();
                    LocalDao localDao=new LocalDao();
                    fSaida.writeObject(localDao.getLocalByCodigo(codlugar));
                    fSaida.flush();
                }
                else if(r.equalsIgnoreCase("SAVE_LOCAL")) {
                    System.out.println("entendi");
                    Local param =(Local)fEntrada.readObject();
                    System.out.println(param.getNome());
                    LocalDao localDao=new LocalDao();
                    if(param.getCodigo()==-1)localDao.insereLocal(param);
                    else localDao.updateLocal(param);
                }
                else if(r.equalsIgnoreCase("SEARCHLOCAL_NOME")) {
                    System.out.println("entendi");
                    String msg2=fEntrada.readUTF();
                    System.out.println(msg2);
                    LocalDao localDao=new LocalDao();
                    fSaida.writeObject(localDao.buscarLocal(msg2));
                    fSaida.flush();
                }

                else if(r.equalsIgnoreCase("SAVE_EVENTO")) {
                    System.out.println("entendi");
                    Evento param =(Evento)fEntrada.readObject();
                    System.out.println(param.getNome());
                    EventoDao eventoDao=new EventoDao();
                    if(param.getCodigo()==-1)eventoDao.insereEvento(param);
                    else eventoDao.updateEvento(param);
                }
                else if(r.equalsIgnoreCase("DELETE_EVENTO")) {
                    System.out.println("entendi");
                    Evento param =(Evento)fEntrada.readObject();
                    System.out.println(param.getNome());
                    EventoDao eventoDao=new EventoDao();
                    eventoDao.excluirEvento(param);
                }
                else if(r.equalsIgnoreCase("DELETE_LOCAL")) {
                    System.out.println("entendi");
                    Local param =(Local)fEntrada.readObject();
                    System.out.println(param.getNome());
                    LocalDao eventoDao=new LocalDao();
                    eventoDao.excluirLocal(param);
                }

                else if(r.equalsIgnoreCase("SEARCHEVENTO_NOME")) {
                    System.out.println("entendi");
                    String msg2=fEntrada.readUTF();
                    System.out.println(msg2);
                    EventoDao eventoDao=new EventoDao();
                    fSaida.writeObject(eventoDao.buscarEventos(msg2));
                    fSaida.flush();
                }

                else if(r.equalsIgnoreCase("SAVE_TIPOINGRESSO")) {
                    System.out.println("entendi");
                    TipoIngresso param =(TipoIngresso)fEntrada.readObject();
                    System.out.println(param.getNome());
                    TipoIngressoDao tipoIngressoDao=new TipoIngressoDao();
                    if(param.getCodigo()==-1)tipoIngressoDao.insereIngresso(param);
                    else tipoIngressoDao.updateIngresso(param);
                }
                else if(r.equalsIgnoreCase("DELETE_TIPOINGRESSO")) {
                    System.out.println("entendi");
                    TipoIngresso param =(TipoIngresso)fEntrada.readObject();
                    System.out.println(param.getNome());
                    TipoIngressoDao tipoIngressoDao=new TipoIngressoDao();
                    tipoIngressoDao.excluirIngresso(param);
                }
                else if(r.equalsIgnoreCase("SEARCHTIPOINGRESSO_NOME_ORG")) {
                    System.out.println("entendi");
                    String msg2=fEntrada.readUTF();
                    System.out.println(msg2);
                    String msg3=fEntrada.readUTF();
                    System.out.println(msg3);
                    TipoIngressoDao tipoIngressoDao=new TipoIngressoDao();
                    fSaida.writeObject(tipoIngressoDao.buscarTipoIngresso(msg2,msg3));
                    fSaida.flush();
                }
                else if(r.equalsIgnoreCase("GETALLTIPOINGRESSOS_ORG")) {
                    System.out.println("entendi");
                    String msg2=fEntrada.readUTF();
                    System.out.println(msg2);
                    TipoIngressoDao tipoIngressoDao=new TipoIngressoDao();
                    fSaida.writeObject(tipoIngressoDao.getTipoIngressoOrganizador(msg2));
                    fSaida.flush();
                }
                else if(r.equalsIgnoreCase("GETALLTIPOINGRESSOS_COD")) {
                    System.out.println("entendi");
                    int val=fEntrada.read();
                    System.out.println(val);
                    TipoIngressoDao tipoIngressoDao=new TipoIngressoDao();
                    fSaida.writeObject(tipoIngressoDao.getTipoIngressoEvento(val));
                    fSaida.flush();
                }
                else if(r.equalsIgnoreCase("GETTIPOINGRESSOS_COD")) {
                    System.out.println("entendi");
                    int val=fEntrada.read();
                    System.out.println(val);
                    TipoIngressoDao tipoIngressoDao=new TipoIngressoDao();
                    fSaida.writeObject(tipoIngressoDao.getTipoIngresso(val));
                    fSaida.flush();
                }
                else if(r.equalsIgnoreCase("GETLOCAL_COD")) {
                    System.out.println("entendi");
                    int val=fEntrada.read();
                    System.out.println(val);
                    LocalDao tipoIngressoDao=new LocalDao();
                    fSaida.writeObject(tipoIngressoDao.getLocalByCodigo(val));
                    fSaida.flush();
                }
                else if(r.equalsIgnoreCase("ISPATROCINADO_COD_PAT")) {
                    System.out.println("entendi");
                    int val=fEntrada.read();
                    System.out.println(val);
                    String val2=fEntrada.readUTF();
                    System.out.println(val2);
                    PatrocinadorDao patrocinadorDao= new PatrocinadorDao();
                    fSaida.writeBoolean(patrocinadorDao.isPatrocinado(val,val2));
                    fSaida.flush();
                }
                else if(r.equalsIgnoreCase("DELETE_PAT_EVENTO")) {
                    System.out.println("entendi");
                    int val=fEntrada.read();
                    System.out.println(val);
                    String val2=fEntrada.readUTF();
                    System.out.println(val2);
                    PatrocinadorDao patrocinadorDao=new PatrocinadorDao();
                    patrocinadorDao.removerPatrocinio(val,val2);
                }
                else if(r.equalsIgnoreCase("ADD_PAT_EVENTO")) {
                    System.out.println("entendi");
                    int val=fEntrada.read();
                    System.out.println(val);
                    String val2=fEntrada.readUTF();
                    System.out.println(val2);
                    PatrocinadorDao patrocinadorDao=new PatrocinadorDao();
                    patrocinadorDao.inserirPatrocinio(val,val2);
                }
                else if(r.equalsIgnoreCase("CREATE_ORG")) {
                    System.out.println("entendi");
                    Organizador org=(Organizador)fEntrada.readObject();
                    System.out.println(org.getNome());
                    OrganizadorDao organizadorDao=new OrganizadorDao();
                    boolean ret=organizadorDao.exists(org);
                    if(!ret){
                        organizadorDao=new OrganizadorDao();
                        organizadorDao.insereOrganizador(org);
                    }
                    fSaida.writeBoolean(!ret);
                    fSaida.flush();
                }
                else if(r.equalsIgnoreCase("CREATE_PAT")) {
                    System.out.println("entendi");
                    Patrocinador org=(Patrocinador) fEntrada.readObject();
                    System.out.println(org.getNome());
                    PatrocinadorDao patrocinadorDao=new PatrocinadorDao();
                    boolean ret=patrocinadorDao.exists(org);
                    if(!ret){
                        patrocinadorDao=new PatrocinadorDao();
                        patrocinadorDao.inserePatrocinador(org);
                    }
                    fSaida.writeBoolean(!ret);
                    fSaida.flush();
                }
                else if(r.equalsIgnoreCase("LOGIN_ORG")) {
                    System.out.println("entendi");
                    String msg2=fEntrada.readUTF();
                    System.out.println(msg2);
                    String msg3=fEntrada.readUTF();
                    System.out.println(msg3);
                    OrganizadorDao organizadorDao=new OrganizadorDao();
                    fSaida.writeObject(organizadorDao.loginOrganizador(msg2,msg3));
                    fSaida.flush();
                }
                else if(r.equalsIgnoreCase("LOGIN_PAT")) {
                    System.out.println("entendi");
                    String msg2=fEntrada.readUTF();
                    System.out.println(msg2);
                    String msg3=fEntrada.readUTF();
                    System.out.println(msg3);
                    PatrocinadorDao organizadorDao=new PatrocinadorDao();
                    fSaida.writeObject(organizadorDao.loginPatrocinador(msg2,msg3));
                    fSaida.flush();
                }




            }

            } catch(ClassNotFoundException e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch(IOException e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }



}
