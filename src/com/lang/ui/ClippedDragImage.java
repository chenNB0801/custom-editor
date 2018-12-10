package com.lang.ui;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class ClippedDragImage extends DragImage {

	private int oldX, oldY;

	public ClippedDragImage(Image i) {
		super(i);
	}

	public static void main(String[] args) {
		// RepaintManager.currentManager(null).setDoubleBufferingEnabled(false);
		Image image = Toolkit.getDefaultToolkit().getImage(ClippedDragImage.class.getResource("L1-Light.png"));
		image = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);
		JFrame frame = new JFrame("ClippedDragImage");
		frame.add(new ClippedDragImage(image));
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		imageX = e.getX();
		imageY = e.getY();
		Rectangle r = getAffectedArea(oldX, oldY, imageX, imageY, imageWidth, imageHeight);
		repaint(r);
		oldX = imageX;
		oldY = imageY;
	}

	private Rectangle getAffectedArea(
			int oldx, int oldy, int newx, int newy, int width, int height) {
		int x = Math.min(oldx, newx);
		int y = Math.min(oldy, newy);
		int w = (Math.max(oldx, newx) + width) - x;
		int h = (Math.max(oldy, newy) + height) - y;
		return new Rectangle(x, y, w, h);
	}
}
