package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class HomeScreen extends JFrame implements MouseMotionListener, MouseListener  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Color c = new Color(0,0,0,0);
	
	public ImageIcon bgii;
	public Image bg;
	
	public int xonw;
	public int yonw;
	
	public int mx;
	public int my;
	
	public ImageIcon setnii;
	public Image setnormal;
	
	public ImageIcon setmoii;
	public Image setmo;
	
	public ImageIcon setcii;
	public Image setc;
	
	public boolean overset = false;
	public boolean pressset = false;
	public boolean clickset = false;
	
	public XMLparser configedithome;
	
	public Rectangle set = new Rectangle(45,190,83, 23);
	Rectangle Mainbar = new Rectangle(0,0,478, 27);
	Rectangle closerec = new Rectangle(445,10, 20, 20);
	Rectangle minirec = new Rectangle(415,10, 20, 20);
	
	
	public Font font = new Font("Arial", Font.BOLD, 13);
	
	public Color color = new Color(51, 102, 204);
	
	public String capkeychar;
	
	Toolkit tk = Toolkit.getDefaultToolkit();

	public HomeScreen() throws SAXException, IOException, ParserConfigurationException{
		
		this.setSize(478, 244);
		this.setUndecorated(true);
		this.setBackground(c);
		
		this.setLocation( (int) Math.round(tk.getScreenSize().getWidth()/2 - this.getWidth()/2)   , (int) Math.round(tk.getScreenSize().getHeight()/2 - this.getHeight()/2) );
		
		ImageIcon  wiii = new ImageIcon(this.getClass().getResource("Icon 64x64.png"));
		Image wimg = wiii.getImage();
		this.setIconImage(wimg);
		
		this.setVisible(true);
		this.requestFocus();
		this.toFront();
		
		addMouseMotionListener(this);
		addMouseListener(this);
		
		bgii = new ImageIcon(this.getClass().getResource("Ditto Base.png"));
		bg = bgii.getImage();
		
		//buttonimage normal
		setnii = new ImageIcon(this.getClass().getResource("Settings Button Normal.png"));
		setnormal = setnii.getImage();
		//mouseover
		setmoii = new ImageIcon(this.getClass().getResource("Settings Button Mouse-over.png"));
		setmo = setmoii.getImage();
		//pressed
		setcii = new ImageIcon(this.getClass().getResource("Settings Button Pressed.png"));
		setc = setcii.getImage();
		
		configedithome = new XMLparser( "config.xml");
		
		
		capkeychar = configedithome.getcapchar();
		
		
		
	}
	
	
	
	public void paint(Graphics g){

		
		g.drawImage(bg, 0, 0, this);
		
		Graphics2D g2d = (Graphics2D)g;

		
		g2d.drawImage(setnormal, 45, 190, this);
		
		
		if(overset == true){
			g2d.drawImage(setmo, 45, 190, this);
		}

		if(pressset == true){
			g2d.drawImage(setc, 45, 190, this);

		}
		
		g2d.setColor(color);
		g2d.setFont(font);
		
		if(capkeychar != null){
		g2d.drawString(capkeychar, 93, 144);
		}

		Toolkit.getDefaultToolkit().sync();
		
		
	}
	
	
	
	
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		if(closerec.contains(xonw,	yonw) == true){
			this.dispose();
			try {
				new appwindow();
			} catch (IOException | SAXException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(minirec.contains(xonw,	yonw) == true){
			this.setExtendedState(JFrame.ICONIFIED);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent p) {
		// TODO Auto-generated method stub
		
		xonw = p.getX();
		yonw = p.getY();
		
		if(set.contains(p.getX(), p.getY()) == true){
			pressset = true;
		}else{
			pressset = false;
		}
		repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent rel) {
		// TODO Auto-generated method stub

		pressset = false;

		if(set.contains(rel.getX(), rel.getY()) == true){
			try {
				new SettingsScreen();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.dispose();
		}
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent mdrag) {
		// TODO Auto-generated method stub
		
		if(Mainbar.contains(xonw, yonw) == true){
		mx = mdrag.getXOnScreen() - xonw;
		my = mdrag.getYOnScreen() - yonw;
		this.setBackground(c);
		
		this.setLocation(mx, my);
		repaint();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent moved) {
		// TODO Auto-generated method stub
		
		if(set.contains(moved.getX(), moved.getY()) == true){
			overset = true;
		}else{
			overset = false;
		}
		repaint();
		
	}

}
