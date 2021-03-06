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

public class Capsule extends Object3D {
  final Point3D B;
  final Vector3D u;
  final float r;
  final float L;
  private Capsule (final Point3D B,
                   final Vector3D u,
                   final float r,
                   final float L,
                   final Color color,
                   final Material material) {
    super(color, material);
    this.B = B;
    u.normalize();
    this.u = u;
    this.r = r;
    this.L = L;

  }

  public Capsule (final Point3D B,
                  final Vector3D u,
                  final float r,
                  final float L,
                  final Color color) {
    this(B, u, r, L, color, null);
  }

  public Capsule (final Point3D B,
                  final Vector3D u,
                  final float r,
                  final float L,
                  final Material material) {
    this(B, u, r, L, null, material);
  }

  @Override
  public Hit intersectionWith (final Ray ray) {
    Point3D R = ray.getStartingPoint();
    Vector3D v = ray.getDirection();
    if(B.sub(R).dot(v)>=0){
        if(u.dot(v)!=1){
            float d = (B.sub(R)).multiplyByScalar(1/(v.cross(u)).length()).dot(v.cross(u));
            if(Math.abs(d) <= r){
                float p = (((B.sub(R)).cross(u)).dot(v.cross(u)))/((v.cross(u)).dot(v.cross(u)));
                Point3D P = R.add(v.multiplyByScalar(p));
                Point3D Q = B.add((v.cross(u).multiplyByScalar(1/(v.cross(u).length()))).multiplyByScalar(d));
                float s = (float) Math.abs(Math.sqrt((r*r-d*d)/(v.cross(u)).dot(v.cross(u))));
                float tIn = p - s;
                float tOut = p + s;
                Point3D H1 = R.add(v.multiplyByScalar(tIn));
                Point3D H2 = R.add(v.multiplyByScalar(tOut));
                if(tIn>=0){
                    if((r*r+(L/2f)*(L/2f)) >=(B.sub(H1).length()*B.sub(H1).length())){
                        Point3D N = B.add(u.multiplyByScalar(Math.abs(H1.sub(B).dot(u))));
                        Vector3D n = (H1.sub(N).multiplyByScalar(1f/r));
                        
                        return(new Hit(tIn,H1,n.multiplyByScalar(1f/n.length()),this));
                    }
                    else{
                        //Intersecta tapa.
                        if(H1.sub(B.add(u.multiplyByScalar(L/2))).length()<= H1.sub(B.add(u.multiplyByScalar(-L/2))).length()){
                            Sphere sphereTapa = new Sphere(B.add(u.multiplyByScalar(L/2)),r);
                            Hit hit = sphereTapa.intersectionWith(ray);
                            if(!hit.equals(Hit.NOHIT)){
                                hit.setObject(this);
                                return hit;
                            }
                        }
                        else{
                            Sphere sphereTapa = new Sphere(B.add(u.multiplyByScalar(-L/2)),r);
                            Hit hit = sphereTapa.intersectionWith(ray);
                            if(!hit.equals(Hit.NOHIT)){
                                hit.setObject(this);
                                return hit;
                            }
                        }

                    }
                }
                else if(0>=tIn && tOut>=0){
                    //TAPAS
                    if(R.sub(B.add(u.multiplyByScalar(L/2))).length()<= R.sub(B.add(u.multiplyByScalar(-L/2))).length()){
                        Sphere sphereTapa = new Sphere(B.add(u.multiplyByScalar(L/2)),r);
                        Hit hit = sphereTapa.intersectionWith(ray);
                        if(!hit.equals(Hit.NOHIT)){
                            hit.setObject(this);
                            return hit;
                        }
                    }
                    else{
                        Sphere sphereTapa = new Sphere(B.add(u.multiplyByScalar(-L/2)),r);
                        Hit hit = sphereTapa.intersectionWith(ray);
                        if(!hit.equals(Hit.NOHIT)){
                            hit.setObject(this);
                            return hit;
                        }  
                    }
                    
                }
            }
        
        }
                
    }
            
    return Hit.NOHIT;

  

  }
  
}