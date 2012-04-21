package com.pi.gl.graphics.objects;

import java.awt.Color;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.jogamp.common.nio.Buffers;

public class HeightmapMesh {
	private float[][] heightMap;
	private FloatBuffer vertexBuffer;
	private FloatBuffer colorBuffer;
	private IntBuffer indexBuffer;
	private Vector3D offset;
	private float spacing;
	private float minHeight;
	private float maxHeight;
	private Color[] colorMapping = { Color.BLUE, Color.GREEN,
			new Color(139, 169, 19), Color.WHITE };

	public HeightmapMesh(float[][] heightMap, float spacing, Vector3D off) {
		this.heightMap = heightMap;
		this.spacing = spacing;
		this.offset = off;

		minHeight = Float.MAX_VALUE;
		maxHeight = Float.MIN_VALUE;
		for (float[] fA : heightMap) {
			for (float f : fA) {
				minHeight = Math.min(minHeight, f);
				maxHeight = Math.max(maxHeight, f + 1f);
			}
		}

		createBuffers();
	}

	public void createBuffers() {
		vertexBuffer = Buffers.newDirectFloatBuffer(heightMap.length
				* heightMap[0].length * 3);
		colorBuffer = Buffers.newDirectFloatBuffer(heightMap.length
				* heightMap[0].length * 3);
		indexBuffer = Buffers.newDirectIntBuffer((heightMap.length - 1)
				* (heightMap[0].length - 1) * 6);
		for (int x = 0; x < heightMap.length; x++) {
			for (int z = 0; z < heightMap[0].length; z++) {
				vertexBuffer.put(((float) x) * spacing + offset.x);
				vertexBuffer.put(heightMap[x][z] + offset.y);
				vertexBuffer.put(((float) z) * spacing + offset.z);

				Color c = getColorAt((heightMap[x][z] - minHeight)
						/ (maxHeight - minHeight));
				colorBuffer.put(c.getRed() / 255f);
				colorBuffer.put(c.getGreen() / 255f);
				colorBuffer.put(c.getBlue() / 255f);

				if (x > 0 && z > 0) {
					indexBuffer.put(z + (x * heightMap[0].length));
					indexBuffer.put(z - 1 + (x * heightMap[0].length));
					indexBuffer.put(z + ((x - 1) * heightMap[0].length));

					indexBuffer.put(z - 1 + (x * heightMap[0].length));
					indexBuffer.put(z + ((x - 1) * heightMap[0].length));
					indexBuffer.put(z - 1 + ((x - 1) * heightMap[0].length));
				}
			}
		}
		colorBuffer = (FloatBuffer) colorBuffer.flip();
		vertexBuffer = (FloatBuffer) vertexBuffer.flip();
		indexBuffer = (IntBuffer) indexBuffer.flip();
	}

	public Color getColorAt(float heightNormal) {
		float mapRoot = heightNormal * (colorMapping.length - 1f);
		int lowerCap = (int) Math.floor(mapRoot);
		int upperCap = lowerCap + 1;
		mapRoot -= lowerCap;
		float rC = colorMapping[upperCap].getRed()
				- colorMapping[lowerCap].getRed();
		float gC = colorMapping[upperCap].getGreen()
				- colorMapping[lowerCap].getGreen();
		float bC = colorMapping[upperCap].getBlue()
				- colorMapping[lowerCap].getBlue();
		return new Color(
				colorMapping[lowerCap].getRed() + (int) (rC * mapRoot),
				colorMapping[lowerCap].getGreen() + (int) (gC * mapRoot),
				colorMapping[lowerCap].getBlue() + (int) (bC * mapRoot));
	}

	public int getIndexCount() {
		return indexBuffer.limit();
	}

	public FloatBuffer getVertexBuffer() {
		return vertexBuffer;
	}

	public FloatBuffer getColorBuffer() {
		return colorBuffer;
	}

	public IntBuffer getIndexBuffer() {
		return indexBuffer;
	}
}
