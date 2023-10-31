package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelInfo extends JPanel {

	private Vista ventana;
	private JLabel jugadas;
	private JTextField jugador;
	private String nomJugador;
	
	public PanelInfo(Vista ventana) {
		this.ventana = ventana;
		this.jugador = new JTextField("Invitado");
		this.nomJugador = "Invitado";
		
		JPanel panel = new JPanel();
		
		this.jugadas = new JLabel("0");
		
		add(new JLabel("Jugadas:  "));
		add(jugadas);
		
		add(new JLabel("       Jugador:  "));
		add(jugador);
		
		
		
	}
	
	public void setJugadas(int jugadas) {
		remove(this.jugadas);
		ventana.revalidate();
		ventana.repaint();
		this.jugadas = new JLabel(Integer.toString(jugadas));
		add(this.jugadas,1);
		ventana.revalidate();
		ventana.repaint();
	}
	
	public String getJugador() {
		return this.nomJugador;
	}
	
	public void setJugador() {
		this.nomJugador = jugador.getText();
	}

}
