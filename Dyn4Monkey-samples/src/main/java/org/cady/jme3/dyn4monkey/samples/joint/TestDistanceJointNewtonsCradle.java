package org.cady.jme3.dyn4monkey.samples.joint;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
import org.cady.jme3.dyn4monkey.control.Dyn4jBodyControl;
import org.cady.jme3.dyn4monkey.samples.AbstractDyn4jTest;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.joint.DistanceJoint;
import org.dyn4j.geometry.*;

public class TestDistanceJointNewtonsCradle extends AbstractDyn4jTest {

    public static void main(final String[] args) {
        new TestDistanceJointNewtonsCradle().start();
    }

    @Override
    protected void simpleInit() {
        // Create floor
        final Body floor = createFloor(15, 1, 0, 4);

        final Sphere sphere = new Sphere(16, 16, .5f);

        // Create circle physic object.
        final Circle circleShape = Geometry.createCircle(.5f);

        Body e = null;

        for (int i = 0; i < 5; i++) {

            final float posX = -2 + i;
            final float posY = 0;

            final BodyFixture fixture = new BodyFixture(circleShape);
            fixture.setRestitution(0.8);

            final Body spherePhysic = new Body();
            spherePhysic.addFixture(fixture);
            spherePhysic.setMass(MassType.FIXED_ANGULAR_VELOCITY);
            spherePhysic.setLinearDamping(0.1);
            spherePhysic.translate(posX, posY);
            e = spherePhysic;

            // Create sphere.
            final Spatial sphereGeom = this.geometryBuilder.createSphere(sphere, posX, posY);
            this.dynamicObjects.attachChild(sphereGeom);

            // Add control to sphereGeom.
            sphereGeom.addControl(new Dyn4jBodyControl(spherePhysic));

            this.dyn4jAppState.getPhysicsSpace().addBody(spherePhysic);

            // create the joint
            final DistanceJoint dj = new DistanceJoint(floor, spherePhysic, new Vector2(posX, 4),
                    spherePhysic.getWorldCenter());
            dj.setCollisionAllowed(true);
            this.dyn4jAppState.getPhysicsSpace().addJoint(dj);

        }

        // move the last body
        e.rotate(Math.toRadians(60), 2, 4);
    }

    @Override
    protected Vector3f getCamInitialLocation() {
        return new Vector3f(0, 0, 20);
    }

}
