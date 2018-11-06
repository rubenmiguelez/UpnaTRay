package models;
/**
 *
 * @author MAZ
 */

import java.awt.Color;
import javax.vecmath.Matrix3f;
import objects.boundingboxes.BoundingBox;
import primitives.Point3D;
import primitives.Vector3D;
import tracer.Hit;
import tracer.Ray;
import shaders.Material;

public final class Ellipsoid extends ProceduralObject3D {
  
  static private final Point3D O = new Point3D(0.0f, 0.0f, 0.0f);
  
  private Ellipsoid (final Point3D C, final Vector3D i, final Vector3D j,
                      final float a, final float b, final float c,
                      final Color color, final Material material) {
    super(color, material);

  }  
  
  public Ellipsoid (final Point3D C, final Vector3D i, final Vector3D j,
                     final float a, final float b, final float c,
                     final Color color) {
    this(C, i, j, a, b, c, color, null);
  }
  
  public Ellipsoid (final Point3D C, final Vector3D i, final Vector3D j,
                     final float a, final float b, final float c,
                     final Material material) {
    this(C, i, j, a, b, c, null, material);
  }  
  
  @Override
  protected float SDF (final Point3D P) {
    return 0.0f;
  }

  @Override
  public Hit intersectionWith (final Ray ray) {

    return Hit.NOHIT;
    
  }
  
  @Override
  protected float distanceUpperBound (final Point3D P) {
    return Float.POSITIVE_INFINITY;
  }  
  
}