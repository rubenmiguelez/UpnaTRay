package view;

import tracer.RayGenerator;
import primitives.Point3D;
import primitives.Vector3D;
import tracer.Ray;

public class Orthographic extends Projection {

  public Orthographic (final float h, final float aspect) {
      super(aspect*h,h);
  }

  @Override
  public RayGenerator getRayGenerator (final Camera c, final int W, final int H) {
    return new OrtographicRayGenerator(c, W, H);
  }

  static private class OrtographicRayGenerator extends RayGenerator {

    public OrtographicRayGenerator(final Camera c, final int W, final int H) {
      super(c, W, H);
    }

    @Override
    public Ray getRay (final int m, final int n) {
        Point3D R = new Point3D((wW)*(m+0.5f)-w*0.5f,hH*(n+0.5f)-h*0.5f,0);
        Vector3D direccion = this.camera.getLook();
        Ray ray = new Ray(R,direccion);
        camera.toSceneCoordenates(ray);
        return ray;
    }

  }

}
