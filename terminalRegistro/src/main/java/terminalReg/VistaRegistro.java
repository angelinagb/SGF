package main.java.terminalReg;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;

public class VistaRegistro extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel panelCentral;
    private JPanel panelSaludo;
    private JLabel lblBienvenida;
    private JPanel panelIngreso;
    private JPanel panelTexto;
    private JPanel panelTeclado;
    private JTextField textDNI;
    private JButton btnIngresar;
    private JPanel panelDNI;
    private JPanel panelBotonIngreso;
    private JButton btnUno;
    private JButton btnDos;
    private JButton btnTres;
    private JButton btnCuatro;
    private JButton btnCinco;
    private JButton btnSeis;
    private JButton btnSiete;
    private JButton btnOcho;
    private JButton btnNueve;
    private JPanel panel;
    private JButton btnCero;
    private JButton btnBorrar;
    private JLabel lblBienvenida2;
    private JPanel panel_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(
                javax.swing.UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaRegistro frame = new VistaRegistro();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void escribirNumero(String numero) {  // funcion que escribe el numero en el text field
        this.textDNI.setText(this.textDNI.getText() + numero);
    }

    private void configurarBotones() {    // define la funcion de cada boton numerico
        JButton[] botones = {
            btnUno, btnDos, btnTres,
            btnCuatro, btnCinco, btnSeis,
            btnSiete, btnOcho, btnNueve,
            btnCero
        };

        for (JButton btn : botones) {
            btn.addActionListener(e -> {
                escribirNumero(btn.getText());
            });
        }
    }

    private void borrarUltimo() {  // funcion boton borrar
        String texto = textDNI.getText();

        if (texto.length() > 0) {
            textDNI.setText(texto.substring(0, texto.length() - 1));
        }
    }

    private boolean validarDNI() {  // se fija si el dni tiene una longitud valida
        String dni = textDNI.getText();

        if (dni.length() < 7 || dni.length() > 8) {
            return false;
        }

        return true; // no chequeo que sean numeros porque solo se pueden ingresar por pantalla
    }

    /**
     * Create the frame.
     */
    public VistaRegistro() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 641, 360);

   

        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(this.contentPane);
        this.contentPane.setLayout(new BorderLayout(0, 0));

        this.panelCentral = new JPanel();
        this.contentPane.add(this.panelCentral);
        this.panelCentral.setLayout(new BorderLayout(0, 0));
     

        this.panelIngreso = new JPanel();
        this.panelCentral.add(this.panelIngreso, BorderLayout.CENTER);
        this.panelIngreso.setLayout(new GridLayout(0, 2, 0, 0));

        this.panelTexto = new JPanel();
        this.panelIngreso.add(this.panelTexto);
        this.panelTexto.setLayout(new BorderLayout(0, 0));

        this.panelDNI = new JPanel();
        this.panelTexto.add(this.panelDNI, BorderLayout.CENTER);
        this.panelDNI.setLayout(new GridLayout(2, 2, 0, 0));

        this.panelSaludo = new JPanel();
        this.panelDNI.add(this.panelSaludo);
        this.panelSaludo.setLayout(new GridLayout(0, 1, 0, 0));

        this.lblBienvenida = new JLabel("¡Bienvenido!");
        this.lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 30));
        this.lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
        this.panelSaludo.add(this.lblBienvenida);
        this.panelSaludo.setPreferredSize(new Dimension(100, 50));

        this.lblBienvenida2 = new JLabel("Ingrese su DNI para registrarse");
        this.lblBienvenida2.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblBienvenida2.setFont(new Font("Segoe UI", Font.PLAIN, 19));
        this.panelSaludo.add(this.lblBienvenida2);

        this.panel_1 = new JPanel();
        this.panelDNI.add(this.panel_1);

        this.textDNI = new JTextField();
        this.panel_1.add(this.textDNI);
        this.textDNI.setFont(new Font("Segoe UI", Font.BOLD, 26));
        this.textDNI.setPreferredSize(new Dimension(200, 50));
        this.textDNI.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.textDNI.setHorizontalAlignment(SwingConstants.CENTER);
        this.textDNI.setColumns(10);

        this.panelBotonIngreso = new JPanel();
        this.panelTexto.add(this.panelBotonIngreso, BorderLayout.SOUTH);

        this.btnIngresar = new JButton("Ingresar");
        this.panelBotonIngreso.add(this.btnIngresar);

        this.panelTeclado = new JPanel();
        this.panelIngreso.add(this.panelTeclado);
        this.panelTeclado.setLayout(new GridLayout(4, 3, 0, 0));

        this.btnUno = new JButton("1");
        this.panelTeclado.add(this.btnUno);

        this.btnDos = new JButton("2");
        this.panelTeclado.add(this.btnDos);

        this.btnTres = new JButton("3");
        this.panelTeclado.add(this.btnTres);

        this.btnCuatro = new JButton("4");
        this.panelTeclado.add(this.btnCuatro);

        this.btnCinco = new JButton("5");
        this.panelTeclado.add(this.btnCinco);

        this.btnSeis = new JButton("6");
        this.panelTeclado.add(this.btnSeis);

        this.btnSiete = new JButton("7");
        this.panelTeclado.add(this.btnSiete);

        this.btnOcho = new JButton("8");
        this.panelTeclado.add(this.btnOcho);

        this.btnNueve = new JButton("9");
        this.panelTeclado.add(this.btnNueve);

        this.panel = new JPanel();
        this.panelTeclado.add(this.panel);

        this.btnCero = new JButton("0");
        this.panelTeclado.add(this.btnCero);

        this.btnBorrar = new JButton("←");
        this.panelTeclado.add(this.btnBorrar);

        Font fuenteBoton = new Font("Segoe UI", Font.BOLD, 18);

        JButton[] botones = {
            btnUno, btnDos, btnTres,
            btnCuatro, btnCinco, btnSeis,
            btnSiete, btnOcho, btnNueve,
            btnCero, btnBorrar, btnIngresar
        };

        for (JButton b : botones) {
            b.setFont(fuenteBoton);
            b.setFocusPainted(false);
            b.setBorderPainted(false);
      
        }

        configurarBotones();

        btnBorrar.addActionListener(e -> borrarUltimo());

        btnIngresar.addActionListener(e -> {
            if (validarDNI()) {
                JOptionPane.showMessageDialog( this, "¡Ingreso válido! DNI: " + textDNI.getText() );
            } else {
                JOptionPane.showMessageDialog(  this,"Ingrese un DNI válido");
            }
        });
    }
}
