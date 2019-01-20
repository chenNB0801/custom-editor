package com.lang.ui;

import com.intellij.ui.JBColor;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class RectangleModel extends BaseModel {
	private int newX, newY, oldX, oldY;
	private int startX, startY;
	private BufferedImage bi;
	private Graphics2D big = null;
	private Rectangle rectangle;


	public RectangleModel(Rectangle rectangle) {
		this.rectangle = rectangle;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setBounds(rectangle);
		this.setBackground(Color.getColor("#FFECD9"));
		this.setDoubleBuffered(true);
	}

	@Override
	public void update(Graphics g1) {
		Graphics2D g2 = (Graphics2D) g1;
		if (big == null) {
			Dimension dim = this.getSize();
			int w = dim.width;
			int h = dim.height;
			rectangle.setLocation(w / 2 - 50, h / 2 - 25);
			bi = (BufferedImage) this.createImage(w, h);
			big = bi.createGraphics();
		}
		big.setColor(JBColor.WHITE);
		big.clearRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);

		big.setColor(Color.WHITE);
		big.fill(rectangle);

		big.setStroke(new BasicStroke(5));
		big.setColor(JBColor.BLUE);
		big.draw(rectangle);

		paint(big);

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
