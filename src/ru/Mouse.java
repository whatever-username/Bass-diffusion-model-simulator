package ru;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {
	public static AppContext context;
	private static int x;
	private static int y;
	private static volatile boolean[] buttonsDown = new boolean[256];

	public static void create(Canvas c) {
		Mouse listener = new Mouse();
		c.addMouseListener(listener);
		c.addMouseMotionListener(listener);
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

	public static int getCellX() {
		return x / (600/context.settings.fieldWidth);
	}

	public static int getCellY() {
		return y / (600/context.settings.fieldHeight);
	}

	public static boolean isButtonDown(int buttonCode) {
		return buttonsDown[buttonCode];
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();


	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttonsDown[e.getButton()] = true;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		buttonsDown[e.getButton()] = false;
	}

	// NOT USED
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
