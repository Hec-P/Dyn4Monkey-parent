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

        FloatBuffer fb = (FloatBuffer) tc.getData();
        fb.clear();

        scaleFaceTextureCoordinates(fb, this.xExtent, this.yExtent, scaleFactor); // back
        scaleFaceTextureCoordinates(fb, this.zExtent, this.yExtent, scaleFactor); // right
        scaleFaceTextureCoordinates(fb, this.xExtent, this.yExtent, scaleFactor); // front
        scaleFaceTextureCoordinates(fb, this.zExtent, this.yExtent, scaleFactor); // left
        scaleFaceTextureCoordinates(fb, this.zExtent, this.xExtent, scaleFactor); // top
        scaleFaceTextureCoordinates(fb, this.zExtent, this.xExtent, scaleFactor); // bottom

        fb.clear();
        tc.updateData(fb);
    }

    private static void scaleFaceTextureCoordinates(FloatBuffer fb, float u, float v, Vector2f scaleFactor) {
        for (int i = 0; i < 4; i++) {
            float x = fb.get();
            float y = fb.get();
            fb.position(fb.position() - 2);
            x *= u * 2 * scaleFactor.getX();
            y *= v * 2 * scaleFactor.getY();
            fb.put(x).put(y);
        }
    }
}
