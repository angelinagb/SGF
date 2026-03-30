package com.sgf;

import java.io.ObjectInputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class ServidorMonitor implements Runnable {

    private int puerto;
    private ControladorMonitor controlador;

    public ServidorMonitor(int puerto, ControladorMonitor controlador) {
        this.puerto = puerto;
        this.controlador = controlador;
    }

    @Override
    public void run() {
        // Atrapamos si el puerto está en uso
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor Monitor activo en puerto " + puerto);

            while (true) {
                Socket socketCliente = serverSocket.accept();

                // Pasamos la lectura a un hilo nuevo
                new Thread(() -> {
                    try (ObjectInputStream in = new ObjectInputStream(socketCliente.getInputStream())) {
                        
                        Turno nuevoTurno = (Turno) in.readObject();
                        // Delegamos la lógica al controlador
                        controlador.procesarNuevoTurno(nuevoTurno);

                    } catch (Exception e) {
                        System.err.println("Error recibiendo turno: " + e.getMessage());
                    } finally {
                        try {
                            if (!socketCliente.isClosed()) {
                                socketCliente.close();
                            }
                        } catch (IOException ex) {
                            System.err.println("Error al cerrar socket cliente: " + ex.getMessage());
                        }
                    }
                }).start();
            }
            
        } catch (BindException e) {
            System.err.println("¡Atención! El puerto " + puerto + " ya está en uso. Revisá que no haya otro Monitor corriendo de fondo.");
        } catch (Exception e) {
            System.err.println("Error fatal en ServidorMonitor: " + e.getMessage());
        }
    }
}