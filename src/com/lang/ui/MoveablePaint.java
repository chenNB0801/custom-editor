package com.lang.ui;

import com.intellij.ui.JBColor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author guoliang
 * @description:
 * @company: https://www.huobi.com
 * @email guoliang@huobi.com
 * @date 12:39 PM 2018/12/6
 */
public class MoveablePaint extends JPanel implements MouseMotionListener, MouseListener {

	private Rectangle rectangle = new Rectangle(100, 100, 100, 30);
	private boolean pressIn = false;
	private int lastx;
	private int lasty;


	public MoveablePaint() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		MoveablePaint moveablePaint = new MoveablePaint();
		frame.getContentPane().add(moveablePaint);
		frame.setSize(800, 800);
		frame.setVisible(true);
	}

	@Override
	public void paint(Graphics g1) {
		super.paint(g1);
		Graphics2D g = (Graphics2D) g1;
		g.setColor(JBColor.BLUE);
		g.fill(rectangle);
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		if (this.pressIn) {
			System.out.println((e.getX() + lastx) +"," + (e.getY() + lasty));
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

