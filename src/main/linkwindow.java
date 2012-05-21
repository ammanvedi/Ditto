package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class linkwindow extends JFrame implements MouseMotionListener, MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ImageIcon bgii;
	public Image bg;
	
	public ImageIcon copynii;
	public Image copynormal;
	
	public ImageIcon copymoii;
	public Image copymo;
	
	public ImageIcon copycii;
	public Image copyc;
	
	public ImageIcon gocii;
	public Image goc;
	
	public ImageIcon gomoii;
	public Image gomo;
	
	public ImageIcon gonii;
	public Image gonormal;
	
	public boolean overcopy = false;
	public boolean overgo = false;
	
	public boolean presscopy = false;
	public boolean pressgo = false;
	
	public Rectangle copy = new Rectangle(15,25,83, 23);
	public Rectangle go = new Rectangle(115,25,83, 23);
	
	public String URL;
	
	public Color c = new Color(0,0,0,0);
	public int mx;
	public int my;
	Rectangle Mainbar = new Rectangle(0,0,279, 27);
	Rectangle closerec = new Rectangle(246,10, 20, 20);
	Rectangle minirec = new Rectangle(220,10, 20, 20);
	public int xonw;
	public int yonw;
	Toolkit tk = Toolkit.getDefaultToolkit();
	
	public linkwindow(String Link){
		
		URL = Link;
		
		addMouseMotionListener(this);
		addMouseListener(this);
		
		
		this.setSize(279, 64);
		this.setLocation( (int) Math.round(tk.getScreenSize().getWidth()/2 - this.getWidth()/2)   , (int) Math.round(tk.getScreenSize().getHeight()/2 - this.getHeight()/2) );

		
		ImageIcon  wiii = new ImageIcon(this.getClass().getResource("Icon 64x64.png"));
		Image wimg = wiii.getImage();
		this.setIconImage(wimg);
		
		this.setUndecorated(true);
		this.setBackground(c);
		this.setVisible(true);
		
		
		//bgimage
		bgii = new ImageIcon(this.getClass().getResource("URL Box Base.png"));
		bg = bgii.getImage();
		
		//buttonimage normal
		copynii = new ImageIcon(this.getClass().getResource("Copy URL Normal.png"));
		copynormal = copynii.getImage();
		//mouseover
		copymoii = new ImageIcon(this.getClass().getResource("Copy URL Mouse-over.png"));
		copymo = copymoii.getImage();
		//pressed
		copycii = new ImageIcon(this.getClass().getResource("Copy URL Pressed.png"));
		copyc = copycii.getImage();
		
		
		//buttonimage normal
		gonii = new ImageIcon(this.getClass().getResource("Open URL Normal.png"));
		gonormal = gonii.getImage();
		//mouseover
		gomoii = new ImageIcon(this.getClass().getResource("Open URL Mouse-over.png"));
		gomo = gomoii.getImage();
		//pressed
		gocii = new ImageIcon(this.getClass().getResource("Open URL Pressed.png"));
		goc = gocii.getImage();
		
		
		
		
	}

	public void paint(Graphics g){

		
		g.drawImage(bg, 0, 0, this);
		
		Graphics2D g2d = (Graphics2D)g;

		g2d.drawImage(copynormal, 15, 25, this);
		g2d.drawImage(gonormal, 115, 25, this);
		
		if(overcopy == true){
			g2d.drawImage(copymo, 15, 25, this);
		}
		
		if(overgo == true){
			g2d.drawImage(gomo, 115, 25, this);
		}
		
		
		if(presscopy == true){
			g2d.drawImage(copyc, 15, 25, this);
		}
		
		if(pressgo == true){
			g2d.drawImage(goc, 115, 25, this);
		}

		Toolkit.getDefaultToolkit().sync();
		
		
	}

	@Override
	public void mouseClicked(MouseEvent mc) {
		// TODO Auto-generated method stub
		if(closerec.contains(xonw,	yonw) == true){
			System.exit(0);

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
		
		

		if(go.contains(p.getX(), p.getY()) == true){
			pressgo = true;
		}else{
			pressgo = false;
		}
		
		if(copy.contains(p.getX(), p.getY()) == true){
			presscopy = true;
		}else{
			presscopy = false;
		}
		repaint();
		
		
	}

	@SuppressWarnings("static-access")
	@Override
	public void mouseReleased(MouseEvent rel) {
		// TODO Auto-generated method stub
		
		presscopy = false;
		pressgo = false;
		
		
		
		if(go.contains(rel.getX(), rel.getY()) == true){
			try {
				java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));
				System.exit(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(copy.contains(rel.getX(), rel.getY()) == true){
			StringSelection ss = new StringSelection(URL);
			tk.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			System.exit(0);
		}
		
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
		
		if(go.contains(moved.getX(), moved.getY()) == true){
			overgo = true;
		}else{
			overgo = false;
		}
		
		if(copy.contains(moved.getX(), moved.getY()) == true){
			overcopy = true;
		}else{
			overcopy = false;
		}

		repaint();
		
		
		
	}
	

}
