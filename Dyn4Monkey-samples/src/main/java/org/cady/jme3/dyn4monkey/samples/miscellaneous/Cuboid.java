package org.cady.jme3.dyn4monkey.samples.miscellaneous;

import com.jme3.math.Vector2f;
import com.jme3.scene.VertexBuffer;
import com.jme3.scene.shape.Box;

import java.nio.FloatBuffer;

public class Cuboid extends Box {

    public Cuboid(float x, float y, float z) {
        super(x, y, z);
    }


    @Override
    public void scaleTextureCoordinates(Vector2f scaleFactor) {
        VertexBuffer tc = getBuffer(VertexBuffer.Type.TexCoord);
        if (tc == null) {
            throw new IllegalStateException("The mesh has no texture coordinates");
        }

        if (tc.getFormat() != VertexBuffer.Format.Float) {
            throw new UnsupportedOperationException("Only float texture coord format is supported");
        }

        if (tc.getNumComponents() != 2) {
            throw new UnsupportedOperationException("Only 2D texture coords are supported");
        }

        FloatBuffer fb = (FloatBuffer) tc.getData();
        fb.clear();
        scaleFace(fb, this.xExtent, this.yExtent, scaleFactor); // back
        scaleFace(fb, this.zExtent, this.yExtent, scaleFactor); // right
        scaleFace(fb, this.xExtent, this.yExtent, scaleFactor); // front
        scaleFace(fb, this.zExtent, this.yExtent, scaleFactor); // left
        scaleFace(fb, this.zExtent, this.xExtent, scaleFactor); // top
        scaleFace(fb, this.zExtent, this.xExtent, scaleFactor); // bottom
        fb.clear();
        tc.updateData(fb);
    }

    private static void scaleFace(FloatBuffer fb, float u, float v, Vector2f scaleFactor) {
        u *= 2 * scaleFactor.getX();
        v *= 2 * scaleFactor.getY();
        for (int i = 0; i < 4; i++) {
            float x = fb.get();
            float y = fb.get();
            fb.position(fb.position() - 2);
            x *= u;
            y *= v;
            fb.put(x).put(y);
        }
    }
}
