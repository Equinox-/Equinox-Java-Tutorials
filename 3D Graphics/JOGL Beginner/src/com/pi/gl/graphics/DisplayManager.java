package com.pi.gl.graphics;

import java.awt.Component;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.FPSAnimator;

public class DisplayManager {
	private final static int MAX_FPS = 50;

	private final GLCanvas canvas;
	private final GLProfile profile;
	private final GLCapabilities caps;
	private final FPSAnimator animator;
	private final RenderLoop renderLoop;

	public DisplayManager() {
		GLProfile.initSingleton(false);
		profile = GLProfile.getDefault();
		caps = new GLCapabilities(profile);
		canvas = new GLCanvas(caps);
		renderLoop = new RenderLoop();
		canvas.addGLEventListener(renderLoop);
		animator = new FPSAnimator(MAX_FPS);
		animator.add(canvas);
	}

	public void start() {
		if (!animator.isStarted())
			animator.start();
	}

	public void dispose() {
		if (animator.isStarted())
			animator.stop();
	}

	public Component getGLCanvas() {
		return canvas;
	}
}
