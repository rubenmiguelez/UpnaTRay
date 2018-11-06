package models;
/**
 *
 * @author MAZ
 */

import java.awt.Color;
import primitives.Point3D;
import shaders.Material;
import tracer.Ray;

abstract class ProceduralObject3D extends Object3D {
  
  static private final float MIN_STEP_CONTRIBUTION = 0.5E-5f;

  protected ProceduralObject3D (final Color color, final Material material) {
    super(color, material);
  }
  
  protected float rayMarching (final Ray ray) {
    
    final Point3D R = ray.getStartingPoint();
    
    float d = 0.0f;
    final float distanceUpperBound = distanceUpperBound(R);
    
    for (float step = SDF(R); (Math.signum(step - d * MIN_STEP_CONTRIBUTION) > 0)
                               &&
                              (Math.signum(distanceUpperBound - d) >= 0);) {
      d += step;
      step = SDF(ray.pointAtParameter(d));      
    }
   
    if (Math.signum(distanceUpperBound - d) >= 0)
      return d;
    else
      return Float.NEGATIVE_INFINITY;

  }
  
  protected abstract float SDF (final Point3D P);
  
  protected abstract float distanceUpperBound (final Point3D P);  
  
}