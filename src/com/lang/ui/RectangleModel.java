package com.lang.ui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class RectangleModel extends BaseModel {
	private int newX, newY, oldX, oldY;
	private int startX, startY;
	private BufferedImage bi;
	private Graphics2D big = null;
	private Rectangle area;
	private Rectangle rect;


	public RectangleModel(int width, int height) {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setBounds(80, 80, width, height);
		this.setBackground(Color.GRAY);
		this.setDoubleBuffered(true);
		this.rect = this.getBounds();
	}

	@Override
	public void update(Graphics g1) {
		Graphics2D g2 = (Graphics2D) g1;
		if (big == null) {
			Dimension dim = this.getSize();
			int w = dim.width;
			int h = dim.height;
			this.area = new Rectangle(dim);
			rect.setLocation(w / 2 - 50, h / 2 - 25);
			bi = (BufferedImage) this.createImage(w, h);
			big = bi.createGraphics();
		}
		big.setColor(Color.WHITE);
		big.clearRect(rect.x, rect.y, rect.width, rect.height);

		big.setColor(Color.GRAY);
		big.fill(rect);

		big.setStroke(new BasicStroke(5));
		big.setColor(Color.BLUE);
		big.draw(rect);
		g2.drawImage(this.bi, 0, 0, this);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Component component = (Component) e.getSource();
		startX = component.getX();
		startY = component.getY();
		oldX = e.getXOnScreen();
		oldY = e.getYOnScreen();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Component component = (Component) e.getSource();
		newX = e.getXOnScreen();
		newY = e.getYOnScreen();
		component.setBounds(startX + (newX - oldX), startY + (newY - oldY), component.getWidth(), component.getHeight());
	}
}
