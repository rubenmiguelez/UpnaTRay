package models;
/**
 *
 * @author MAZ
 */
import java.awt.Color;
import java.util.List;
import javax.vecmath.Matrix3f;

import primitives.Point3D;
import primitives.Vector3D;
import shaders.Material;
import tracer.Hit;
import tracer.Ray;

public class Torus extends ProceduralObject3D {
   
  static private final Point3D O = new Point3D(0.0f, 0.0f, 0.0f);
  final Point3D C;
  final Vector3D v;
  final float R;
  final float r;
  private Torus (final Point3D C, final Vector3D v,
                 final float R, final float r,
                 final Color color, final Material material) {
    super(color, material);
    this.C = C;
    v.normalize();
    this.v = v;
    this.R = R;
    this.r = r;
            
  }   

  public Torus () {
    this(new Point3D(), new Vector3D(1, 0, 0), 2.0f, 1.0f, Color.green, null);
  }

  public Torus (final Point3D C, final float R, final float r) {
    this(new Point3D(), new Vector3D(1, 0, 0), R, r, Color.green, null);
  }
  
  public Torus (final Point3D C, final Vector3D v,
                final float R, final float r, final Color color) {
    this(C, v, R, r, color, null);
  }
  
  public Torus (final Point3D C, final Vector3D v,
                final float R, final float r, final Material material) {
    this(C, v, R, r, null, material);
  }  

  @Override
  public Hit intersectionWith (final Ray ray) {
    Sphere sphere = new Sphere(C,R+r);
    if(sphere.intersectionWith(ray)!= Hit.NOHIT){
        //TODO interseccion con toro
    }
    return Hit.NOHIT;

  }

  @Override
  protected float SDF (final Point3D P) {
    return 0.0f;
  }
  
  @Override
  protected float distanceUpperBound (final Point3D P) {
    return Float.POSITIVE_INFINITY;
  }
  
}