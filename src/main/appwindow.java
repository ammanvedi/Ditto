package main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.sun.awt.AWTUtilities;


public class appwindow extends JFrame implements MouseListener, KeyListener, MouseMotionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int initx = 0 ;
	int inity;
	int w;
	int h;
	boolean createarea = false;
	public Rectangle caparearec = new Rectangle(0,0,10,10);
	public boolean drawrec;
	public int currentx;
	public int currenty;
	public int currentw;
	public int currenth;
	
	public boolean hasmoved = false;
	
	public int initrecx;
	public int initrecy;
	
	public String fskey;
	public boolean autoset;
	
	Toolkit tk = getToolkit();
	
	public appwindow() throws IOException, SAXException, ParserConfigurationException{
		
		//ImageIcon  cii = new ImageIcon(this.getClass().getResource("c 32x32_00000.png"));
		
		
		Image cimg = tk.getImage("c 32x32_00000.cur");
		
		
		Point hs = new Point(13,13);
		Cursor c = tk.createCustomCursor(cimg, hs, "sscursor");
		
		//this.setCursor(c);

		
		ImageIcon  wiii = new ImageIcon(this.getClass().getResource("Icon 64x64.png"));
		Image wimg = wiii.getImage();

		JPanel jp = new JPanel();
		
		//jp.setCursor(c);
		
		this.getContentPane().add(jp);
		this.setUndecorated(true);
		
		AWTUtilities.setWindowOpacity(this, 0.2f);
		addMouseMotionListener(this);
		addKeyListener(this);
		addMouseListener(this);
		

		this.setSize(tk.getScreenSize().width, tk.getScreenSize().height);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setIconImage(wimg);
		this.setVisible(true);

		
		//CREATE AND START THE REPAINT TIMER	
		Timer repainttimer = new Timer();
		repainttimer.schedule( new TimerTask() {
		    public void run() {
		      repaint();
		    }
		 }, 0, 50);
		
		XMLparser cfgparse = new XMLparser( "config.xml");
		
		fskey = cfgparse.getcapchar();
		autoset = cfgparse.getcopysetting();

		



	}

	@Override
	public void mouseClicked(MouseEvent e) {
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

	@Override
	public void mousePressed(MouseEvent pressed) {
		// TODO Auto-generated method stub
		createarea = false;
		initx = pressed.getX();
		inity = pressed.getY();
		drawrec = true;
	}

	@Override
	public void mouseReleased(MouseEvent released) {
		// TODO Auto-generated method stub
		createarea = true;
		
		
	if(released.getX() > initx && released.getY() > inity){
		initrecx = initx;
		initrecy = inity;
		w = (released.getX() - initrecx);
		h = (released.getY() - initrecy);

	}
	
	if(released.getX() > initx && released.getY() < inity){
		initrecx = initx;
		initrecy = released.getY();
		
		w = (released.getX() - initx);
		h = (inity - released.getY()); 
		
		
	}
	
	if(released.getX() < initx && released.getY() > inity){
		initrecx = released.getX();
		initrecy = inity;
		
		w = (initx - released.getX());
		h = (released.getY() - inity);
		
	}
	
	if(released.getX() < initx && released.getY() < inity){
		initrecx = released.getX();
		initrecy = released.getY();
		
		w = (initx - released.getX());
		h = (inity - released.getY());
		
	}
	
	
	
	try {
		outinfo();
	} catch (IOException | SAXException | ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	

		drawrec = false;
		
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	public void outinfo() throws IOException, SAXException, ParserConfigurationException{

		AWTUtilities.setWindowOpacity(this, 0.01f);
		this.setExtendedState(JFrame.ICONIFIED);
		this.enableInputMethods(false);
		@SuppressWarnings("unused")
		upload u1 = new upload(initrecx, initrecy, w, h);

		
		XMLparser xml = new XMLparser( "SCREENSHOT.xml");

		
		this.dispose();
		
		if(autoset == false){
		new linkwindow(xml.getlink());
		}else{
			StringSelection ss = new StringSelection(xml.getlink());
			tk.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			
		}

	}

	@Override
	public void keyPressed(KeyEvent K) {
		// TODO Auto-generated method stub
		if(K.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		
		if(K.getKeyCode() == KeyEvent.VK_F1){
			try {
				new HomeScreen();
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
		

		
		if(K.getKeyChar() == fskey.charAt(0) ){
			
			createarea = true;
			w = (tk.getScreenSize().width);
			h = (tk.getScreenSize().height);
			try {
				outinfo();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			drawrec = false;
			
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		hasmoved = false;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void paint(Graphics g){
		
		
		
		super.paint(g);
	
	Graphics2D g2d = (Graphics2D)g;

		
		if(drawrec == true && hasmoved == true){
			g2d.setColor(Color.BLACK);

			g2d.drawLine(initx, inity, initx, currenty);
			g2d.drawLine(initx, inity, currentx, inity);
			g2d.drawLine(currentx, inity, currentx, currenty);
			g2d.drawLine(initx, currenty, currentx, currenty);
		}
		
		
		
		 Toolkit.getDefaultToolkit().sync();

	}

	@Override
	public void mouseDragged(MouseEvent dragged) {
		// TODO Auto-generated method stub
		currentx = dragged.getX() ;
		currenty = dragged.getY() ;
		hasmoved = true;
		
		repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent moved) {
		// TODO Auto-generated method stub

		
		
	}

}
