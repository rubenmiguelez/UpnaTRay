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
  final Point3D P;
  final Vector3D n;
  private Plane (final Point3D Q, final Vector3D n, final Color color, final Material material) {
    super(color, material);
   P = Q;
   n.normalize();
   this.n = n;
  }
  
  private Plane (final Point3D A, final Point3D B, final Point3D C, final Color color, final Material material) {
    super(color, material);
    P = A;
    Vector3D AB = A.sub(B);
    Vector3D AC = A.sub(C);
    this.n = AB.cross(AC);
    this.n.normalize();
    
  }  
  
  public Plane (final Point3D Q, final Vector3D n, final Color color) {
    this(Q, n, color, null);        
  }
  public Plane (final Point3D Q, final Vector3D n) {
    this(Q, n, null, null);        
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
      //Si el rayo no discurre paralelo al plano
    if(ray.getDirection().dot(n)!=0){
        float a = ((P.sub(ray.getStartingPoint())).dot(n))/(ray.getDirection().dot(n));
        if(a > 0){
            Point3D intersection = ray.getStartingPoint().add(ray.getDirection().multiplyByScalar(a));
            if((ray.getStartingPoint().sub(P)).dot(n)>= 0){
                return (new Hit(a,intersection,n,this));
            }
            else
                return (new Hit(a,intersection,n.multiplyByScalar(-1),this));
        }
    }
    return Hit.NOHIT;

  }

}