package org.cady.jme3.dyn4monkey.debug.control;

import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import org.cady.jme3.dyn4monkey.Converter;
import org.cady.jme3.dyn4monkey.debug.Dyn4jDebugAppState;
import org.cady.jme3.dyn4monkey.debug.PhysicDebugColor;
import org.dyn4j.dynamics.joint.Joint;

public class Dyn4jRopeJointDebugControl extends Dyn4jJointDebugControl {

    public Dyn4jRopeJointDebugControl(final Dyn4jDebugAppState dyn4jDebugAppState, final Joint joint) {
        super(dyn4jDebugAppState, joint);

        final Vector3f p1 = Converter.toVector3f(joint.getAnchor1());
        final Vector3f p2 = Converter.toVector3f(joint.getAnchor2());

        this.lineGeom = createLine(String.valueOf(joint.hashCode()), p1, p2, 2);
        this.anchorGeom1 = createCircle("CircleGeom1", p1);
        this.anchorGeom2 = createCircle("CircleGeom2", p2);
    }

    @Override
    protected Material getLineGeomMaterial(final boolean isJointActive) {
        return getMaterial(isJointActive ? PhysicDebugColor.GREEN : PhysicDebugColor.PINK);
    }

    @Override
    protected Material getAnchorGeom1Material(final boolean isJointActive) {
        return getMaterial(isJointActive ? PhysicDebugColor.BROWN_FILLED : PhysicDebugColor.PINK_FILLED);
    }

}
