package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;


public class Vista extends JFrame {
	
	private Tablero tablero;
	private JFrame ventana;
	private PanelOpciones opciones;
	private PanelJuego juego;
	private Top10 top;
	private PanelInfo info;

	public Vista() {
		
		JFrame ventana = new JFrame();
	    this.top = new Top10();
	    top.cargarRecords(new File("./data/top10.csv"));
		
		ventana.setLayout(new BorderLayout());
		
		PanelBotones botones = new PanelBotones(this);
		PanelInfo info = new PanelInfo(this);
		PanelOpciones opciones = new PanelOpciones(this);
		JPanel panelBotones = new JPanel(new GridBagLayout());
		
		this.tablero = new Tablero(opciones.getTamano());
		tablero.desordenar(opciones.getDificultad());
		
		PanelJuego juego = new PanelJuego(tablero, this, info);
		panelBotones.add(botones);
		
		this.ventana = ventana;
		this.opciones = opciones;
		this.juego = juego;
		this.info = info;
		
		//botones.setBackground(Color.yellow);
		//info.setBackground(Color.red);
		this.opciones.setBackground(new Color(51, 153, 255));
		
		
		
		this.juego.setPreferredSize(new Dimension(800,800));;

		this.ventana.add(panelBotones, BorderLayout.EAST);
		this.ventana.add(info, BorderLayout.SOUTH);
		this.ventana.add(opciones, BorderLayout.NORTH);
		this.ventana.add(juego, BorderLayout.CENTER);
		
		this.ventana.setTitle("LightsOut");
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ventana.setSize(775,700);
		
		
		
		this.ventana.setVisible(true);
		
		this.ventana.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				try {
					top.salvarRecords(new File("./data/top10.csv"));
				} catch (FileNotFoundException | UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	
		
	}
	
	public void actualizarTop(int puntaje) {
		
		if(top.esTop10(puntaje)) {
			top.agregarRegistro(info.getJugador(), puntaje);
		}
		
	}
	
	public void mostrarTop() {
		new VentanaTop10(this.top, this);
	}
	
	public void nuevoJuego() {
		ventana.remove(juego);
		ventana.revalidate();
		ventana.repaint();
		tablero = new Tablero(opciones.getTamano());
		tablero.desordenar(opciones.getDificultad());
		juego = new PanelJuego(tablero, this, info);
		ventana.add(juego, BorderLayout.CENTER);
		ventana.revalidate();
		ventana.repaint();
		setJugadas(0);
	}
	
	public void reiniciar() {
		ventana.remove(juego);
		ventana.revalidate();
		ventana.repaint();
		tablero.reiniciar();
		juego = new PanelJuego(tablero, this, info);
		ventana.add(juego, BorderLayout.CENTER);
		ventana.revalidate();
		ventana.repaint();
		setJugadas(0);
	}
	
	public void setJugador() {
		info.setJugador();
	}
	
	public void setJugadas(int jugadas) {
		ventana.remove(info);
		ventana.revalidate();
		ventana.repaint();
		info.setJugadas(jugadas);
		ventana.add(info, BorderLayout.SOUTH);
		ventana.revalidate();
		ventana.repaint();
	}
	
	public Tablero getTablero(){
		return tablero;
	}
	
	public static void main(String[] args) {
		new Vista();
	}
}
