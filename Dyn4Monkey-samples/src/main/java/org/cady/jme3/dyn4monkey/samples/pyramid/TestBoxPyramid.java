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
package org.cady.jme3.dyn4monkey.samples.pyramid;

import com.jme3.math.Vector3f;
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
public class TestBoxPyramid extends AbstractDyn4jTest {

    /** The height of the pyramid. To calculate # of Bodies use this formula: # of bodies = h(h+1)/2 */
    private static final int HEIGHT = 25;

    public static void main(final String[] args) {
        final TestBoxPyramid testBoxPyramid = new TestBoxPyramid();
        testBoxPyramid.setDebugEnabled(false);
        testBoxPyramid.start();
    }

    @Override
    protected void simpleInit() {
        // Create floor.
        createFloor(15, 1, 0, 0);

        final float width = .5f;
        final float height = .5f;

        final Box box = new Box(width / 2, height / 2, width / 2);

        // Create rectangle physic object.
        final Rectangle boxShape = Geometry.createRectangle(width, height);

        // the current x position
        float posX = 0;
        // the current y position
        float posY = .26f;

        // the spacing between the boxes
        final float yspacing = .01f;
        final float xspacing = .01f;

        // loop to create the rows
        for (int i = 0; i < HEIGHT; i++) {
            // the number of boxes on this row
            final int num = HEIGHT - i;

            // increment y
            posY += height + yspacing;

            // set x
            posX = -(num * (width + xspacing)) / 2 + (width + xspacing) / 2;

            // loop to create the bodies in the rows
            for (int j = 0; j < num; j++) {
                // create a body
                final Body boxPhysic = this.physicObjectBuilder.createBody(boxShape, posX, posY);

                // Create box.
                final Spatial boxGeom = this.geometryBuilder.createBox(box, posX, posY);
                this.dynamicObjects.attachChild(boxGeom);

                // Add control to boxGeom.
                boxGeom.addControl(new Dyn4jBodyControl(boxPhysic));

                this.dyn4jAppState.getPhysicsSpace().addBody(boxPhysic);

                // increment x
                posX += width + xspacing;
            }
        }
    }

    @Override
    protected Vector3f getCamInitialLocation() {
        return new Vector3f(0, 5, 20);
    }

}
