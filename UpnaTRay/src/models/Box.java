package models;
/**
 *
 * @author MAZ
 */

import java.awt.Color;
import javax.vecmath.Matrix3f;
import primitives.Point3D;
import primitives.Vector3D;
import shaders.Material;
import tracer.Hit;
import tracer.Ray;

public final class Box extends Object3D {

  static private final Point3D O = new Point3D(0.0f, 0.0f, 0.0f);
  private final Point3D C;
  private final Vector3D i;
  private final Vector3D j;
  private final float w,h,d;
  
  private Box (final Point3D C, final Vector3D i, final Vector3D j,
               final float w, final float h, final float d,
               final Color color, final Material material) {
    super(color, material);
    this.C = C;
    this.i = i;
    this.j = j;
    this.w = w;
    this.h = h;
    this.d = d;
  }
  
  public Box (final Point3D C, final Vector3D i, final Vector3D j,
              final float w, final float h, final float d, final Color color) {
    this(C, i, j, w, h, d, color, null);
  }

  public Box (final Point3D C, final Vector3D i, final Vector3D j,
              final float w, final float h, final float d, final Material material) {
    this(C, i, j, w, h, d, null, material);
  }

  @Override
  public Hit intersectionWith (final Ray ray) {

    return Hit.NOHIT;
    
  }
   
}