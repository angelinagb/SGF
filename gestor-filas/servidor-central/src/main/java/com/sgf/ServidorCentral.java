package com.sgf;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.BindException;

public class ServidorCentral implements Runnable{
    private int puerto;
    private LogicaFila logica;

    public ServidorCentral(int puerto) {
        this.puerto = puerto;
        this.logica = LogicaFila.getInstance();
    }

    @Override
    public void run() {
        try(serverSocket server = new ServerSocket(puerto)){
            system.out.println("Servidor Central escuchando en puerto " + puerto);
            while(true){
                Socket socketCliente = serverSocket.accept();
               
                ManejadorCliente manejador = new ManejadorCliente(socketCliente, logica);
                Thread hiloCliente = new Thread(manejador);
                hiloCliente.start();
            }
        }catch(BindException e){
            System.err.println("Error: El puerto " + puerto + " ya está en uso.");
    }catch(Exception e){
            e.printStackTrace();
        }
    }


}
