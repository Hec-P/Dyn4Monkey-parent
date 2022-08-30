package org.cady.jme3.dyn4monkey.debug.control;

import com.jme3.material.Material;
import org.cady.jme3.dyn4monkey.Converter;
import org.cady.jme3.dyn4monkey.debug.Dyn4jDebugAppState;
import org.cady.jme3.dyn4monkey.debug.PhysicDebugColor;
import org.dyn4j.dynamics.joint.Joint;

public class Dyn4jRevoluteJointDebugControl extends Dyn4jJointDebugControl {

    public Dyn4jRevoluteJointDebugControl(final Dyn4jDebugAppState dyn4jDebugAppState, final Joint joint) {
        super(dyn4jDebugAppState, joint);

        // joint.getAnchor1() and joint.getAnchor2() are equals
        this.anchorGeom1 = createCircle("CircleGeom1", Converter.toVector3f(joint.getAnchor1()));
    }

    @Override
    protected Material getAnchorGeom1Material(final boolean isJointActive) {
        return getMaterial(isJointActive ? PhysicDebugColor.YELLOW : PhysicDebugColor.PINK_FILLED);
    }

}
