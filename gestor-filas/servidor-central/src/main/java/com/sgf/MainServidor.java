package com.sgf;

public class MainServidor {
    public static void main(String[] args) {
        int puerto = Constantes.HOST_SERVIDOR_CENTRAL;
        LogicaFila logica = LogicaFila.getInstance();
        ServidorCentral servidor = new ServidorCentral(puerto);
        Thread hiloServidor = new Thread(servidor);
        hiloServidor.start();
    }

}
