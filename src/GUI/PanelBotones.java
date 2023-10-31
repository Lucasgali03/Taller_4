package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelBotones extends JPanel implements ActionListener{

	private Vista ventana;
	private PanelOpciones opciones;
	
	public PanelBotones(Vista ventana) {
		
		this.ventana = ventana;
		
		JPanel botones = new JPanel();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JButton nuevo = new JButton("NUEVO");
		JButton reiniciar = new JButton("REINICIAR");
		JButton top10 = new JButton("TOP 10");
		JButton cambiarJugador = new JButton("CAMBIAR JUGADOR");
		
		nuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.nuevoJuego();
			}
		});
		
		reiniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ventana.reiniciar();
			}
			
		});
		
		cambiarJugador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ventana.setJugador();
			}
			
		});
		
		top10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ventana.mostrarTop();
			}
			
		});
		
		nuevo.setBackground(new Color(51, 153, 255));
		reiniciar.setBackground(new Color(51, 153, 255));
		top10.setBackground(new Color(51, 153, 255));
		cambiarJugador.setBackground(new Color(51, 153, 255));
		
		add(nuevo);
		add(reiniciar);
		add(top10);
		add(cambiarJugador);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
