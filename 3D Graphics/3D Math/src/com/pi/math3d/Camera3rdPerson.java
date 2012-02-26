package com.pi.math3d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Camera3rdPerson implements KeyListener {
	private static final float yawMilli = .1f;
	private static final float pitchMilli = .1f;
	private static final float magMilli = .5f;

	private int centerDist;
	private float pitch = 45, yaw = 0;
	private TransMatrix yawM = new TransMatrix(), pitchM = new TransMatrix();
	private boolean u, l, d, r, i, o;
	private long lastMoveProc = -1;

	public Camera3rdPerson(int centerDist) {
		this.centerDist = centerDist;
		yawM.setYRotation(Math.toRadians(yaw));
		pitchM.setXRotation(Math.toRadians(pitch));
	}

	public float getPitch() {
		return pitch;
	}

	public boolean doModification() {
		boolean mod = false;
		if (lastMoveProc != -1) {
			long passed = System.currentTimeMillis() - lastMoveProc;
			if (u) {
				mod = true;
				modPitch(passed * pitchMilli);
			} else if (d) {
				mod = true;
				modPitch(-passed * pitchMilli);
			}
			if (l) {
				mod = true;
				modYaw(passed * yawMilli);
			} else if (r) {
				mod = true;
				modYaw(-passed * yawMilli);
			}
			if (i) {
				mod = true;
				centerDist -= passed * magMilli;
			} else if (o) {
				mod = true;
				centerDist += passed * magMilli;
			}
		}
		lastMoveProc = System.currentTimeMillis();
		if (mod) {
			yawM.setYRotation(Math.toRadians(yaw));
			pitchM.setXRotation(Math.toRadians(pitch));
		}
		return mod;
	}

	public float getYaw() {
		return yaw;
	}

	public void modPitch(float val) {
		if (pitch + val >= 0 && pitch + val <= 90) {
			pitch += val;
		}
	}

	public void modYaw(float val) {
		yaw += val;
		if (yaw < 0)
			yaw += 360;
		if (yaw >= 360)
			yaw -= 360;
	}

	public Vector translate(Vector p) {
		p.multiply(yawM).multiply(pitchM).z+=centerDist;
		return p;
	}

	@Override
	public String toString() {
		return "3rd Person Camera: Yaw: " + yaw + " Pitch: " + pitch
				+ " Magnitude: " + centerDist;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			u = true;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			d = true;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			l = true;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			r = true;
		} else if (e.getKeyCode() == KeyEvent.VK_Q) {
			i = true;
		} else if (e.getKeyCode() == KeyEvent.VK_E) {
			o = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			u = false;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			d = false;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			l = false;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			r = false;
		} else if (e.getKeyCode() == KeyEvent.VK_Q) {
			i = false;
		} else if (e.getKeyCode() == KeyEvent.VK_E) {
			o = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}