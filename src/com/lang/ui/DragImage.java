package com.lang.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class DragImage extends JComponent implements MouseMotionListener {

	static int imageWidth = 60, imageHeight = 60;
	int grid = 10;
	int imageX, imageY;
	Image image;

	public DragImage(Image i) {
		image = i;
		addMouseMotionListener(this);
	}

	public static void main(String[] args) {
		//RepaintManager.currentManager(null).setDoubleBufferingEnabled(false);
		Image image = Toolkit.getDefaultToolkit().getImage(DragImage.class.getResource("L1-Light.png"));
		image = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);
		JFrame frame = new JFrame("DragImage");
		frame.add(new DragImage(image));
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		imageX = e.getX();
		imageY = e.getY();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		int w = getSize().width / grid;
		int h = getSize().height / grid;
		boolean black = false;
		for (int y = 0; y <= grid; y++) {
			for (int x = 0; x <= grid; x++) {
				g2.setPaint(black ? Color.black : Color.white);
				black = !black;
				g2.fillRect(x * w, y * h, w, h);
			}
		}
		g2.drawImage(image, imageX, imageY, this);
	}
}
