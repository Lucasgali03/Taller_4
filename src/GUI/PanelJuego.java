package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.Tablero;

public class PanelJuego extends JPanel implements MouseListener{

	private Vista ventana;
	private int tamano;
	private Tablero tablero;
	private int altoCajas;
	private int anchoCajas;
	private PanelInfo info;
	
	
	public PanelJuego(Tablero tablero, Vista ventana, PanelInfo info) {
		this.tamano = tablero.darTablero().length;
		this.tablero = tablero;
		this.ventana = ventana;
		this.info = info;
		addMouseListener(this);
	}
	
	
	
	public void paint(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setColor(Color.white);
		
		g2D.fillRect(0, 0, 610, 610);
		
		int alto = g2D.getClipBounds().height;
		int largo = g2D.getClipBounds().width;
		
		largo = (largo/tamano) - 5;
		alto = (alto/tamano) - 5;
		
		this.altoCajas = alto;
		this.anchoCajas = largo;
		
		int x = 0;
		int y = 0;
		
		BufferedImage on = null;
				try {
				    on = ImageIO.read(new File("./data/on.png"));
				} catch (IOException e) {
				    e.printStackTrace();
				}
				BufferedImage off = null;
				try {
				    off = ImageIO.read(new File("./data/off.png"));
				} catch (IOException e) {
				    e.printStackTrace();
				}
		
		for(boolean[] i: tablero.darTablero()) {
			x = 0;
			for(boolean ii: i) {
				
				
				if(!ii) {
					g2D.setColor(Color.yellow);
					g2D.fillRoundRect(x, y, largo, alto, 40, 40);
					g2D.drawImage(on, x+7, y+7, x + largo-10, y + largo -10, 0,0 ,on.getWidth(), on.getHeight(), ventana);
				}
				else {
					g2D.setColor(Color.gray);
					g2D.fillRoundRect(x, y, largo, alto, 40, 40);
					g2D.drawImage(off, x - 5, y+7, x + largo-10, y + largo -10, 0,0 ,on.getWidth() , on.getHeight(), ventana);
				}
				x += largo + 5;
			}
			y += alto + 5;
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		int x = e.getX();
		int y = e.getY();
		
		int[] casillas = convertirCoordenadasACasilla(x,y);
		
		tablero.jugar(casillas[0], casillas[1]);
		
		ventana.setJugadas(tablero.darJugadas());
		
		if(!tablero.tableroIluminado()) {
			repaint();
		}
		else if(tablero.tableroIluminado()){
			JDialog dialogo = new JDialog(ventana, "Completado", true);
			dialogo.add(new JLabel((String) "Felicitaciones " + info.getJugador() + " ha logrado completar el tablero, su puntaje es de " + tablero.calcularPuntaje()));
			dialogo.setSize(500, 150);
	        dialogo.setLocationRelativeTo(ventana);
	        dialogo.setVisible(true);
	        
	        ventana.actualizarTop(tablero.calcularPuntaje());
			
		}
	}
	
	private int[] convertirCoordenadasACasilla(int x, int y)
	{
		int ladoTablero = tablero.darTablero().length;
		int altoPanelTablero = getHeight();
		int anchoPanelTablero = getWidth();
		int altoCasilla = altoPanelTablero / ladoTablero;
		int anchoCasilla = anchoPanelTablero / ladoTablero;
		int fila = (int) (y / altoCasilla);
		int columna = (int) (x / anchoCasilla);
		return new int[] { fila , columna };
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
