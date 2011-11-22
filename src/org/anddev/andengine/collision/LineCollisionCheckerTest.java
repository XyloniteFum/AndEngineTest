package org.anddev.andengine.collision;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.opengl.vbo.VertexBufferObjectManager;
import org.anddev.andengine.util.constants.Constants;

/**
 * @author Nicolas Gramlich
 * @since 13:50:55 - 22.06.2011
 */
public class LineCollisionCheckerTest extends TestCase {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final float DELTA = 0.0001f;

	private static final float[] VERTICES = new float[4];

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	@Override
	public void setUp() throws Exception {
		VertexBufferObjectManager.onDestroy();
		VertexBufferObjectManager.onCreate();
	}

	@Override
	public void tearDown() throws Exception {

	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void testSimple() {
		final Line line = new Line(-10, 0, 10, 0);

		this.assertLine(-10, 0, 10, 0, line);
	}
	
	public void testRotation90() {
		final Line line = new Line(-10, 0, 10, 0);
		line.setRotation(90);

		this.assertLine(0, -10, 0, 10, line);
	}
	
	public void testRotation180() {
		final Line line = new Line(-10, 0, 10, 0);
		line.setRotation(180);

		this.assertLine(10, 0, -10, 0, line);
	}

	private void assertLine(final int pX1, final int pY1, final int pX2, final int pY2, final Line pLine) {
		LineCollisionChecker.fillVertices(pLine, VERTICES);
		
		Assert.assertEquals(pX1, VERTICES[0 + Constants.VERTEX_INDEX_X], DELTA);
		Assert.assertEquals(pY1, VERTICES[0 + Constants.VERTEX_INDEX_Y], DELTA);
		Assert.assertEquals(pX2, VERTICES[2 + Constants.VERTEX_INDEX_X], DELTA);
		Assert.assertEquals(pY2, VERTICES[2 + Constants.VERTEX_INDEX_Y], DELTA);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
