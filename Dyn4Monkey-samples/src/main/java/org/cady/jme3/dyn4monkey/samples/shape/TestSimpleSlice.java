/*
 * Copyright (c) 2009-2014 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.cady.jme3.dyn4monkey.samples.shape;

import com.jme3.math.FastMath;
import org.cady.jme3.dyn4monkey.samples.AbstractDyn4jTest;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Slice;

/**
 * 
 * @author H
 */
public class TestSimpleSlice extends AbstractDyn4jTest {

    public static void main(final String[] args) {
        new TestSimpleSlice().start();
    }

    @Override
    protected void simpleInit() {
        // Create floor.
        createFloor(15, 1, 0, -4);

        // TODO Dyn4j: Create slice.
        // final Spatial sliceGeom = this.geometryBuilder.createSlice(.5f, .5f, .5f, -2, 4);
        // this.dynamicObjects.attachChild(sliceGeom);

        // Create slice physic object.
        final Slice sliceShape = new Slice(2.0, FastMath.HALF_PI);
        final Body slicePhysic = new Body();
        slicePhysic.addFixture(sliceShape);

        // Important!: Always call setMass in order to compute object's mass.
        slicePhysic.setMass();

        slicePhysic.translate(-2.0, -2.0);
        slicePhysic.rotate(-FastMath.PI + .4f);

        // Setting velocity.
        slicePhysic.getLinearVelocity().set(4.0, 0.0);

        // TODO Dyn4j: Add control to sliceGeom.
        // sliceGeom.addControl(new Dyn4jBodyControl(slicePhysic));

        this.dyn4jAppState.getPhysicsSpace().addBody(slicePhysic);
    }

}
