package com.lang.ui;

import com.intellij.ui.JBColor;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

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
	private BufferedImage bi;
	private Graphics2D big;


	public MoveablePaint() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		MoveablePaint moveablePaint = new MoveablePaint();
		frame.getContentPane().add(moveablePaint);
		frame.setSize(800, 800);
		frame.setVisible(true);
	}

	@Override
	public void update(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		if (this.bi == null) {
			this.bi = (BufferedImage) createImage(rectangle.width, rectangle.height);
			this.big = this.bi.createGraphics();
		}
		this.big.setColor(Color.BLUE);
		this.big.fill(this.rectangle);
		g.drawImage(this.bi, 0, 0, this);
		paint(g1);
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

