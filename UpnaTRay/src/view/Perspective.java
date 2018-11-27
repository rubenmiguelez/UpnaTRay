package view;

import tracer.RayGenerator;
import primitives.Point3D;
import primitives.Vector3D;
import tracer.Ray;

public class Perspective extends Projection {

  public Perspective (final float fov, final float aspect) {
      super((float)(aspect*(2*Math.tan(fov/2))),(float)(2*Math.tan(fov/2)));
  }

  @Override
  public RayGenerator getRayGenerator(final Camera c, final int W, final int H) {
    return new PerspectiveRayGenerator(c, W, H);
  }

  static private class PerspectiveRayGenerator extends RayGenerator {

    public PerspectiveRayGenerator (final Camera c, final int W, final int H) {
      super(c, W, H);
    }

    @Override
    public Ray getRay (final int m, final int n) {
        Point3D R = camera.getPosition();
        Vector3D direccion = new Point3D((m+0.5f)*(wW)-(halfw),(n+0.5f)*(hH)-(halfh),-1).sub(R);
        Ray ray = new Ray(R,direccion);
        
        camera.toSceneCoordenates(ray);
        
        return ray;


    }

  }

}
