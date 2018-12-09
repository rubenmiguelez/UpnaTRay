package view;
/**
 *
 * @author MAZ
 */

import primitives.Point3D;
import primitives.Vector3D;
import tracer.RayGenerator;
import tracer.Ray;

public class Angular extends Projection {
  
  public Angular (final float omega) {
      super((float)(2*Math.tan(omega/2)),(float)(2*Math.tan(omega/2)));
  }
  
  @Override
  public RayGenerator getRayGenerator (final Camera c, final int W, final int H) {
    return new AngularRayGenerator(c, W, H);
  }

  static private class AngularRayGenerator extends RayGenerator {

    public AngularRayGenerator (final Camera c, final int W, final int H) {
        super(c,W,H);
    }

    @Override
    public Ray getRay (final int m, final int n) {
        if((m-W/2)*(m-W/2)+(n-H/2)*(n-H/2) <= (H/2)*(H/2)){
            Point3D R = camera.getPosition();
            Vector3D direccion = new Point3D((m+1f/2f)*(w/W)-(w/2),(n+1f/2f)*(h/H)-(h/2),-1).sub(R);
            Ray ray = new Ray(R,direccion);
            camera.toSceneCoordenates(ray);
            return ray;
        }
        else{
            return new Ray(camera.getPosition(),new Vector3D(0,0,0));
        }
        

    }

  }

}
