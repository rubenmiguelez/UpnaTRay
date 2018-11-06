package models;

/**
 *
 * @author MAZ
 */

import java.awt.Color;

import primitives.Vector3D;
import primitives.Point3D;
import shaders.Material;
import tracer.Hit;
import tracer.Ray;

public class Cylinder extends Object3D {


  private Cylinder (final Point3D B,
                    final Vector3D u,
                    final float r,
                    final float L,
                    final Color color,
                    final Material material) {
    super(color, material);

  }

  public Cylinder(final Point3D B,
          final Vector3D u,
          final float r,
          final float L,
          final Color color) {
    this(B, u, r, L, color, null);
  }

  public Cylinder(final Point3D B,
          final Vector3D u,
          final float r,
          final float L,
          final Material material) {
    this(B, u, r, L, null, material);
  }

  @Override
  public Hit intersectionWith (final Ray ray) {
    
    return Hit.NOHIT;

  }
  
}
