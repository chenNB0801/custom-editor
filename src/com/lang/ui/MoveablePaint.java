package com.lang.ui;

import com.intellij.ui.JBColor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * @author guoliang
 * @description:
 * @company: https://www.huobi.com
 * @email guoliang@huobi.com
 * @date 12:39 PM 2018/12/6
 */
public class MoveablePaint extends JPanel implements MouseMotionListener, MouseListener {

	private Rectangle rectangle = new Rectangle(100, 100, 100, 100);
	private boolean pressIn = false;
	private int lastx;
	private int lasty;
	static JFrame frame;

	private BufferedImage bi;
	private Graphics2D big;


	public MoveablePaint() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public static void main(String[] args) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		MoveablePaint moveablePaint = new MoveablePaint();
		frame.getContentPane().add(moveablePaint);
		frame.setSize(800, 800);
		frame.setVisible(true);
	}

	@Override
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		if (null == this.bi) {
			this.bi = (BufferedImage) createImage(this.getWidth(), this.getHeight());
			big = this.bi.createGraphics();
		}
//		frame.getContentPane().getGraphics().clearRect(0, 0, frame.getWidth(), frame.getHeight());
		big.clearRect(0, 0, this.getWidth(),  this.getHeight());
		big.setColor(JBColor.BLUE);
		big.fill(rectangle);
		g.drawImage(this.bi, 0, 0, this);
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		if (this.pressIn) {
			this.rectangle.setLocation(e.getX() + lastx, e.getY() + lasty);
			this.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.lastx = this.rectangle.x - e.getX();
		this.lasty = this.rectangle.y - e.getY();
		pressIn = this.rectangle.contains(e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}

