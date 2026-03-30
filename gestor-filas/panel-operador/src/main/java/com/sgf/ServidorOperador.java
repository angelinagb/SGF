package com.sgf;

import java.io.ObjectInputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class ServidorOperador implements Runnable {

    private int puerto;
    private LogicaFila logica; 
    private VentanaPanelOperador ventana;

    public ServidorOperador(int puerto, LogicaFila logica, VentanaPanelOperador ventana) {
        this.puerto = puerto;
        this.logica = logica;
        this.ventana = ventana;
    }

    @Override
    public void run() {
        // Atrapamos si el puerto está en uso acá
        try (ServerSocket server = new ServerSocket(puerto)) {
            System.out.println("Servidor Operador activo en puerto " + puerto);

            while (true) {
                // 1. Aceptamos la conexión entrante
                Socket socketCliente = server.accept();

                // 2. Creamos un hilo para atender a este cliente sin bloquear el servidor
                new Thread(() -> {
                    try (ObjectInputStream in = new ObjectInputStream(socketCliente.getInputStream())) {
                        
                        Turno turno = (Turno) in.readObject();

                        logica.agregarTurno(turno);
                        ventana.actualizarVista();//segun la IA esto puede causar error y tiene que ser ejecutado por fuera del hilo 

                        System.out.println("En cola: " + logica.getCantidadEnEspera());

                    } catch (Exception e) {
                        System.err.println("Error procesando turno: " + e.getMessage());
                    } finally {
                        // 3. Siempre cerramos el socket del cliente al terminar
                        try {
                            if (!socketCliente.isClosed()) {
                                socketCliente.close();
                            }
                        } catch (IOException ex) {
                            System.err.println("Error al cerrar socket cliente: " + ex.getMessage());
                        }
                    }
                }).start(); // ¡No te olvides de arrancar el hilo!
            }

        } catch (BindException e) {
            System.err.println("¡Atención! El puerto " + puerto + " ya está en uso. Cerrá la otra ventana del Operador.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}