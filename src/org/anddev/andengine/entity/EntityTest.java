package org.anddev.andengine.entity;

import junit.framework.Assert;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.opengl.vbo.VertexBufferObjectManager;
import org.anddev.andengine.util.AssertUtils;

import android.test.AndroidTestCase;

/**
 * @author Nicolas Gramlich
 * @since 15:27:27 - 12.05.2010
 */
public class EntityTest extends AndroidTestCase {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final float DELTA = 0.0001f;

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
	// Test-Methods
	// ===========================================================

	public void testConvertLocalToSceneCoordinatesOrigin() throws Exception {
		final IEntity entity = new Entity(0, 0);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 5;
		final float expectedY = 5;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesOriginWithRotation() throws Exception {
		final IEntity entity = new Entity(0, 0);
		entity.setRotation(180);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = -5;
		final float expectedY = -5;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesOriginWithScale() throws Exception {
		final IEntity entity = new Entity(0, 0);
		entity.setScale(2);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 10;
		final float expectedY = 10;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesOriginWithParent() throws Exception {
		final IEntity parent = new Entity(0, 0);
		final IEntity entity = new Entity(0, 0);

		parent.attachChild(entity);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 5;
		final float expectedY = 5;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesOriginWithParentAndRotation() throws Exception {
		final IEntity parent = new Entity(0, 0);
		parent.setRotation(180);
		final IEntity entity = new Entity(0, 0);
		entity.setRotation(180);

		parent.attachChild(entity);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 5;
		final float expectedY = 5;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesWithParent() throws Exception {
		final IEntity parent = new Entity(5, 5);
		final IEntity entity = new Entity(5, 5);

		parent.attachChild(entity);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 15;
		final float expectedY = 15;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesWithParentAndRotation() throws Exception {
		final IEntity parent = new Entity(5, 5);
		final IEntity entity = new Entity(5, 5);
		entity.setRotation(180);

		parent.attachChild(entity);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 5;
		final float expectedY = 5;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesWithParentAndRotation2() throws Exception {
		final IEntity parent = new Entity(5, 5);
		final IEntity entity = new Entity(5, 5);
		entity.setRotation(90);

		parent.attachChild(entity);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 5;
		final float expectedY = 15;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesNotOrigin() throws Exception {
		final IEntity entity = new Entity(5, 5);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 10;
		final float expectedY = 10;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesNotOriginWithRotation() throws Exception {
		final IEntity entity = new Entity(5, 5);
		entity.setRotation(180);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 0;
		final float expectedY = 0;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertLocalToSceneCoordinatesNotOriginWithRotation2() throws Exception {
		final IEntity entity = new Entity(5, 5);
		entity.setRotation(180);

		final float testX = 10;
		final float testY = 10;

		final float expectedX = -5;
		final float expectedY = -5;

		this.testConvertLocalToSceneCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertSceneToLocalCoordinatesOrigin() throws Exception {
		final IEntity entity = new Entity(5, 5);

		final float testX = 10;
		final float testY = 10;

		final float expectedX = 5;
		final float expectedY = 5;

		this.testConvertSceneToLocalCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertSceneToLocalCoordinatesOriginWithScale() throws Exception {
		final IEntity entity = new Entity(0, 0);
		entity.setScale(2);

		final float testX = 10;
		final float testY = 10;

		final float expectedX = 5;
		final float expectedY = 5;

		this.testConvertSceneToLocalCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertSceneToLocalCoordinatesNotOriginWithScale() throws Exception {
		final IEntity entity = new Entity(5, 5);
		entity.setScale(2);

		final float testX = 5;
		final float testY = 5;

		final float expectedX = 0;
		final float expectedY = 0;

		this.testConvertSceneToLocalCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertSceneToLocalCoordinatesNotOriginWithScale1() throws Exception {
		final IEntity entity = new Entity(5, 5);
		entity.setScale(2);

		final float testX = 10;
		final float testY = 10;

		final float expectedX = 2.5f;
		final float expectedY = 2.5f;

		this.testConvertSceneToLocalCoordinates(entity, testX, testY, expectedX, expectedY);
	}

	public void testConvertSceneToLocalCoordinatesNotOriginWithScale2() throws Exception {
		final Rectangle rectangle = new Rectangle(5, 5, 5, 5);

		final float testX = 7.5f;
		final float testY = 7.5f;

		final float expectedX = 2.5f;
		final float expectedY = 2.5f;

		this.testConvertSceneToLocalCoordinates(rectangle, testX, testY, expectedX, expectedY);
	}

	public void testConvertSceneToLocalCoordinatesNotOriginWithScale3() throws Exception {
		final Rectangle rectangle = new Rectangle(5, 5, 5, 5);
		rectangle.setScale(2);

		final float testX = 7.5f;
		final float testY = 7.5f;

		final float expectedX = 2.5f;
		final float expectedY = 2.5f;

		this.testConvertSceneToLocalCoordinates(rectangle, testX, testY, expectedX, expectedY);
	}

	public void testRectangleContains() throws Exception {
		final Rectangle rectangle = new Rectangle(0, 0, 10, 10);

		Assert.assertTrue(rectangle.contains(5, 5));
		Assert.assertTrue(rectangle.contains(0, 0));
		Assert.assertTrue(rectangle.contains(0, 10));
		Assert.assertTrue(rectangle.contains(10, 0));
		Assert.assertTrue(rectangle.contains(10, 10));

		Assert.assertFalse(rectangle.contains(-0.01f, -0.01f));
		Assert.assertFalse(rectangle.contains(10.01f, -0.01f));
		Assert.assertFalse(rectangle.contains(-0.01f, 10.01f));
		Assert.assertFalse(rectangle.contains(10.01f, 10.01f));
	}

	public void testRectangleContainsWithScale() throws Exception {
		final Rectangle rectangle = new Rectangle(0, 0, 10, 10);
		rectangle.setScale(2);

		Assert.assertTrue(rectangle.contains(5, 5));
		Assert.assertTrue(rectangle.contains(-5, -5));
		Assert.assertTrue(rectangle.contains(15, -5));
		Assert.assertTrue(rectangle.contains(-5, 15));
		Assert.assertTrue(rectangle.contains(15, 15));

		Assert.assertFalse(rectangle.contains(-5.01f, -5.01f));
		Assert.assertFalse(rectangle.contains(15.01f, -5.01f));
		Assert.assertFalse(rectangle.contains(-5.01f, 15.01f));
		Assert.assertFalse(rectangle.contains(15.01f, 15.01f));
	}

	public void testRectangleContainsOriginWithParent() throws Exception {
		final IEntity parent = new Entity(0, 0);
		final Rectangle rectangle = new Rectangle(0, 0, 10, 10);
		rectangle.setParent(parent);

		Assert.assertTrue(rectangle.contains(5, 5));
		Assert.assertTrue(rectangle.contains(0, 0));
		Assert.assertTrue(rectangle.contains(0, 10));
		Assert.assertTrue(rectangle.contains(10, 0));
		Assert.assertTrue(rectangle.contains(10, 10));

		Assert.assertFalse(rectangle.contains(-0.01f, -0.01f));
		Assert.assertFalse(rectangle.contains(10.01f, -0.01f));
		Assert.assertFalse(rectangle.contains(-0.01f, 10.01f));
		Assert.assertFalse(rectangle.contains(10.01f, 10.01f));
	}

	public void testRectangleContainsOriginWithParentAndScale() throws Exception {
		final IEntity parent = new Entity(0, 0);
		final Rectangle rectangle = new Rectangle(0, 0, 10, 10);
		rectangle.setParent(parent);
		rectangle.setScale(2);

		Assert.assertTrue(rectangle.contains(5, 5));
		Assert.assertTrue(rectangle.contains(-5, -5));
		Assert.assertTrue(rectangle.contains(15, -5));
		Assert.assertTrue(rectangle.contains(-5, 15));
		Assert.assertTrue(rectangle.contains(15, 15));

		Assert.assertFalse(rectangle.contains(-5.01f, -5.01f));
		Assert.assertFalse(rectangle.contains(15.01f, -5.01f));
		Assert.assertFalse(rectangle.contains(-5.01f, 15.01f));
		Assert.assertFalse(rectangle.contains(15.01f, 15.01f));
	}

	public void testRectangleContainsOriginWithParentAndScale2() throws Exception {
		final IEntity parent = new Entity(0, 0);
		parent.setScale(2);
		final Rectangle rectangle = new Rectangle(0, 0, 10, 10);
		rectangle.setParent(parent);

		Assert.assertTrue(rectangle.contains(10, 10));
		Assert.assertTrue(rectangle.contains(0, 0));
		Assert.assertTrue(rectangle.contains(0, 20));
		Assert.assertTrue(rectangle.contains(20, 0));
		Assert.assertTrue(rectangle.contains(20, 20));

		Assert.assertFalse(rectangle.contains(-0.01f, -0.01f));
		Assert.assertFalse(rectangle.contains(20.01f, -0.01f));
		Assert.assertFalse(rectangle.contains(-0.01f, 20.01f));
		Assert.assertFalse(rectangle.contains(20.01f, 20.01f));
	}

	public void testRectangleContainsOriginWithParentAndScale3() throws Exception {
		final IEntity parent = new Entity(0, 0);
		parent.setScale(2);
		final Rectangle rectangle = new Rectangle(0, 0, 10, 10);
		rectangle.setParent(parent);
		rectangle.setScale(2);

		Assert.assertTrue(rectangle.contains(10, 10));
		Assert.assertTrue(rectangle.contains(-10, -10));
		Assert.assertTrue(rectangle.contains(-10, 30));
		Assert.assertTrue(rectangle.contains(30, -10));
		Assert.assertTrue(rectangle.contains(30, 30));

		Assert.assertFalse(rectangle.contains(-10.01f, -10.01f));
		Assert.assertFalse(rectangle.contains(30.01f, -10.01f));
		Assert.assertFalse(rectangle.contains(-10.01f, 30.01f));
		Assert.assertFalse(rectangle.contains(30.01f, 30.01f));
	}

	public void testRectangleContainsNonOriginWithParent() throws Exception {
		final IEntity parent = new Entity(5, 5);
		final Rectangle rectangle = new Rectangle(0, 0, 10, 10);
		rectangle.setParent(parent);

		Assert.assertTrue(rectangle.contains(10, 10));
		Assert.assertTrue(rectangle.contains(5, 5));
		Assert.assertTrue(rectangle.contains(5, 15));
		Assert.assertTrue(rectangle.contains(15, 5));
		Assert.assertTrue(rectangle.contains(15, 15));

		Assert.assertFalse(rectangle.contains(4.99f, 4.99f));
		Assert.assertFalse(rectangle.contains(15.01f, 4.99f));
		Assert.assertFalse(rectangle.contains(4.99f, 15.01f));
		Assert.assertFalse(rectangle.contains(15.01f, 15.01f));
	}

	public void testRectangleContainsNonOriginWithParent2() throws Exception {
		final IEntity parent = new Entity(5, 5);
		final Rectangle rectangle = new Rectangle(5, 5, 10, 10);
		rectangle.setParent(parent);

		Assert.assertTrue(rectangle.contains(15, 15));
		Assert.assertTrue(rectangle.contains(10, 10));
		Assert.assertTrue(rectangle.contains(10, 20));
		Assert.assertTrue(rectangle.contains(20, 10));
		Assert.assertTrue(rectangle.contains(20, 20));

		Assert.assertFalse(rectangle.contains(9.99f, 9.99f));
		Assert.assertFalse(rectangle.contains(20.01f, 9.99f));
		Assert.assertFalse(rectangle.contains(9.99f, 20.01f));
		Assert.assertFalse(rectangle.contains(20.01f, 20.01f));
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	private void testConvertLocalToSceneCoordinates(final IEntity pEntity, final float pTestX, final float pTestY, final float pExpectedX, final float pExpectedY) {
		final float[] actual = pEntity.convertLocalToSceneCoordinates(pTestX, pTestY);

		AssertUtils.assertArrayEquals(new float[]{pExpectedX, pExpectedY}, actual, EntityTest.DELTA);
	}

	private void testConvertSceneToLocalCoordinates(final IEntity pEntity, final float pTestX, final float pTestY, final float pExpectedX, final float pExpectedY) {
		final float[] actual = pEntity.convertSceneToLocalCoordinates(pTestX, pTestY);

		AssertUtils.assertArrayEquals(new float[]{pExpectedX, pExpectedY}, actual, EntityTest.DELTA);
	}
}
