package view;
/**
 *
 * @author MAZ
 */

import primitives.Point3D;
import tracer.RayGenerator;
import tracer.Ray;

public class Angular extends Projection {

  public Angular (final float omega) {

  }
  
  @Override
  public RayGenerator getRayGenerator (final Camera c, final int W, final int H) {
    return new AngularRayGenerator(c, W, H);
  }

  static private class AngularRayGenerator extends RayGenerator {

    private final float w2;  // Cuadrado del radio de la imagen 
    private final float cos; // Coseno de omega / 2
    private final Point3D R;

    public AngularRayGenerator (final Camera c, final int W, final int H) {
     
    }

    @Override
    public Ray getRay (final int m, final int n) {



    }

  }

}
