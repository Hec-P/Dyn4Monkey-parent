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
package org.cady.jme3.dyn4monkey;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import org.dyn4j.geometry.Vector2;

/**
 * 
 * @author H
 */
public class Converter {

    public static Vector3f toVector3f(final Vector2 from) {
        return new Vector3f(toFloat(from.x), toFloat(from.y), 0);
    }

    public static float toFloat(final double from) {
        // return new FloatingDecimal(from).floatValue();
        return (float) from;
    }

    public static Vector2 toVector2(final Vector3f from) {
        return new Vector2(from.getX(), from.getY());
    }

    public static Vector2 toVector2(final Vector2f from) {
        return new Vector2(from.x, from.y);
    }

    public static Vector3f[] toVector3f(final Vector2[] vertices) {
        final Vector3f[] vectors = new Vector3f[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            vectors[i] = toVector3f(vertices[i]);
        }
        return vectors;
    }

}
