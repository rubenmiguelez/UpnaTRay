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

public class Plane extends Object3D {
  
  private Plane (final Point3D Q, final Vector3D n, final Color color, final Material material) {
    super(color, material);
   
  }
  
  private Plane (final Point3D A, final Point3D B, final Point3D C, final Color color, final Material material) {
    super(color, material);

  }  
  
  public Plane (final Point3D Q, final Vector3D n, final Color color) {
    this(Q, n, color, null);        
  }

  public Plane (final Point3D A, final Point3D B, final Point3D C, final Color color) {
    this(A, B, C, color, null);        
  }
  
  public Plane (final Point3D P, final Vector3D n, final Material material) {
    this(P, n, null, material);
  }  
  
  public Plane (final Point3D A, final Point3D B, final Point3D C, final Material material) {
    this(A, B, C, null, material);
  }    
    
  @Override
  public Hit intersectionWith (final Ray ray) {

    return Hit.NOHIT;

  }

}