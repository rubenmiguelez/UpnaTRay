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

  final Point3D B;
  final Vector3D u;
  final float r;
  final float L;
  private Cylinder (final Point3D B,
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
                    if((r*r+(L/2f)*(L/2f))>=(B.sub(H1).length()*B.sub(H1).length())){
                        
                        Vector3D H1aB = H1.sub(B);
                        float cosAlpha = (H1aB.dot(u))/(H1aB.length()*u.length());
                        float a = Math.abs(H1.sub(B).length()*cosAlpha);
                        Point3D N = B.add(u.multiplyByScalar(a));
                        
                        Vector3D n = H1.sub(N);
                        
                        return(new Hit(tIn,H1,n.multiplyByScalar(1f/n.length()),this));
                    }
                    else if((r*r+(L/2f)*(L/2f))>=(B.sub(H2).length()*B.sub(H2).length())){
                        //Intersecta tapa.
                        if(H1.sub(B.add(u.multiplyByScalar(L/2))).length()<= H1.sub(B.add(u.multiplyByScalar(-L/2))).length()){
                            Plane planoTapa = new Plane(B.add(u.multiplyByScalar(L/2)),u);
                            Hit hit = planoTapa.intersectionWith(ray);
                            if(!hit.equals(Hit.NOHIT)){
                                hit.setObject(this);
                                return hit;
                            }
                        }
                        else{
                            Plane planoTapa = new Plane(B.add(u.multiplyByScalar(-L/2)),u);
                            Hit hit = planoTapa.intersectionWith(ray);
                            if(!hit.equals(Hit.NOHIT)){
                                hit.setObject(this);
                                return hit;
                            }
                        }

                    }
                    else{
                        return Hit.NOHIT;
                    }
                }
                else if(0>=tIn && tOut>=0){
                    //TAPAS
                    if(R.sub(B.add(u.multiplyByScalar(L/2))).length()<= R.sub(B.add(u.multiplyByScalar(-L/2))).length()){
                        Plane planoTapa = new Plane(B.add(u.multiplyByScalar(L/2)),u);
                        Hit hit = planoTapa.intersectionWith(ray);
                        if(!hit.equals(Hit.NOHIT)){
                            hit.setObject(this);
                            if(hit.getPoint().sub(B).length()*hit.getPoint().sub(B).length() <= r*r+L/2*L/2)
                                return hit;
                            else 
                                return Hit.NOHIT;
                        }
                    }
                    else{
                        Plane planoTapa = new Plane(B.add(u.multiplyByScalar(-L/2)),u);
                        Hit hit = planoTapa.intersectionWith(ray);
                        if(!hit.equals(Hit.NOHIT)){
                            hit.setObject(this);
                            if(hit.getPoint().sub(B).length()*hit.getPoint().sub(B).length() <= r*r+L/2*L/2)
                                return hit;
                            else
                                return Hit.NOHIT;
                        }
                    }
                    
                }
            }
        
        }
                
    }
            
    return Hit.NOHIT;

  }
  
}
