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
package org.cady.jme3.dyn4monkey.samples.stack;

import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import org.cady.jme3.dyn4monkey.control.Dyn4jBodyControl;
import org.cady.jme3.dyn4monkey.samples.AbstractDyn4jTest;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.Rectangle;

/**
 * 
 * @author H
 */
public class TestBoxStack extends AbstractDyn4jTest {

    public static void main(final String[] args) {
        new TestBoxStack().start();
    }

    @Override
    protected void simpleInit() {
        // Create floor.
        createFloor(15, 1, 0, -4);

        final Box box = new Box(.5f, .5f, .5f);

        // Create rectangle physic object.
        final Rectangle boxShape = Geometry.createRectangle(1.0, 1.0);

        for (int i = 0; i < 5; i++) {

            final float posX = 0;
            final float posY = 2 + i * 1.2f;
            final Body boxPhysic = this.physicObjectBuilder.createBody(boxShape, posX, posY);

            // Create box.
            final Spatial boxGeom = this.geometryBuilder.createBox(box, posX, posY);
            this.dynamicObjects.attachChild(boxGeom);

            // Add control to boxGeom.
            boxGeom.addControl(new Dyn4jBodyControl(boxPhysic));

            this.dyn4jAppState.getPhysicsSpace().addBody(boxPhysic);
        }
    }

}
