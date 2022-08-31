package org.cady.jme3.dyn4monkey.samples.joint;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import org.cady.jme3.dyn4monkey.control.Dyn4jBodyControl;
import org.cady.jme3.dyn4monkey.samples.AbstractDyn4jTest;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.joint.RevoluteJoint;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

public class TestRevoluteJointChain extends AbstractDyn4jTest {

    public static void main(final String[] args) {
        new TestRevoluteJointChain().start();
    }

    private static final int LENGTH = 20;

    @Override
    protected void simpleInit() {
        final Body floorBody = createFloor(15, 1, 0, -4);

        // create a reusable rectangle
        final float h = 0.125f;
        final float w = 0.5f;
        final Rectangle r = Geometry.createRectangle(w, h);
        final BodyFixture f = new BodyFixture(r);
        f.setDensity(20.0);

        final Box box = new Box(w / 2, h / 2, .5f);

        Body previous = floorBody;

        final float overlap = 0.03f;
        float posX = 0;
        final float posY = 3;
        for (int i = 0; i < LENGTH; i++) {
            // create a chain link
            final Body boxPhysic = this.physicObjectBuilder.createBody(f, posX, posY);
            this.dyn4jAppState.getPhysicsSpace().addBody(boxPhysic);

            // Create box.
            final Spatial boxGeom = this.geometryBuilder.createBox(box, posX, posY);
            this.dynamicObjects.attachChild(boxGeom);

            // Add control to boxGeom.
            boxGeom.addControl(new Dyn4jBodyControl(boxPhysic));

            // define the anchor point
            final Vector2 anchor = new Vector2(posX - (w * 0.5 - overlap), posY);

            // create a joint from the previous body to this body
            final RevoluteJoint joint = new RevoluteJoint(previous, boxPhysic, anchor);
            this.dyn4jAppState.getPhysicsSpace().addJoint(joint);

            posX += w - overlap * 2.0;
            previous = boxPhysic;
        }
    }

    @Override
    protected Vector3f getCamInitialLocation() {
        return new Vector3f(0, 0, 17);
    }

}
