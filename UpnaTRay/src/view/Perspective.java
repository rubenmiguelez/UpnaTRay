package view;

import tracer.RayGenerator;
import primitives.Point3D;
import tracer.Ray;

public class Perspective extends Projection {

  public Perspective (final float fov, final float aspect) {

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



    }

  }

}
