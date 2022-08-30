package org.cady.jme3.dyn4monkey.debug.control;

import com.jme3.math.Vector3f;
import org.cady.jme3.dyn4monkey.Converter;
import org.cady.jme3.dyn4monkey.debug.Dyn4jDebugAppState;
import org.dyn4j.dynamics.joint.Joint;

public class Dyn4jDistanceJointDebugControl extends Dyn4jJointDebugControl {

    public Dyn4jDistanceJointDebugControl(final Dyn4jDebugAppState dyn4jDebugAppState, final Joint joint) {
        super(dyn4jDebugAppState, joint);

        final Vector3f p1 = Converter.toVector3f(joint.getAnchor1());
        final Vector3f p2 = Converter.toVector3f(joint.getAnchor2());

        this.lineGeom = createLine(joint.getId().toString(), p1, p2, 2);
        this.anchorGeom1 = createCircle("CircleGeom1", p1);
        this.anchorGeom2 = createCircle("CircleGeom2", p2);
    }

}
