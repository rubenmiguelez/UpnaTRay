package view;

import tracer.RayGenerator;
import primitives.Point3D;
import tracer.Ray;

public class Orthographic extends Projection {

  public Orthographic (final float h, final float aspect) {

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

    }

  }

}
