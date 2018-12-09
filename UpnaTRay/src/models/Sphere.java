package models;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector3D;
import shaders.Material;
import tracer.Hit;
import tracer.Ray;

public class Sphere extends Object3D {
    private Point3D C;
    private float radio;
  private Sphere (final Point3D C, final float radio, final Color color, final Material material) {
    super(color, material);
    this.C = C;
    this.radio = radio;
  }    

  public Sphere (final Point3D C, final float radio, final Color color) {
    this(C, radio, color, null);
    this.C = C;
    this.radio = radio;
  }
  public Sphere (final Point3D C, final float radio) {
    this(C, radio, null, null);
    this.C = C;
    this.radio = radio;
  }
  
  public Sphere (final Point3D C, final float radio, final Material material) {
    this(C, radio, null, material);
    this.C = C;
    this.radio = radio;
  }  

  @Override
  public Hit intersectionWith (final Ray ray) {
      final Vector3D esferaARayo = this.C.sub(ray.getStartingPoint());
      final float cosAlpha = (esferaARayo.dot(ray.getDirection()))/(esferaARayo.length()*ray.getDirection().length());
      final float alpha = (float) Math.acos(cosAlpha);
      //Si el ángulo entre los vectores no es agudo...
      //El rayo no intersecta con la esfera porque el centro se encuentra en el semiespacio posterior.
      if(alpha >= Math.PI/2 || -Math.PI/2 >= alpha){
          return Hit.NOHIT;
      }
      else{
          //Miramos si el punto de partida del rayo se encuentra dentro de la esfera.
          if(Math.abs(this.C.sub(ray.getStartingPoint()).length()) > this.radio){
              //Habrá intersección si y solo si el punto más próximo del rayo a C, está a una distancia menor o igual que R
              final float RF = Math.abs(this.C.sub(ray.getStartingPoint()).length())*cosAlpha;
              final Point3D F = ray.getStartingPoint().add(ray.getDirection().multiplyByScalar(RF));
              final float CF = Math.abs(this.C.sub(F).length());
              if(CF> this.radio){
                  return Hit.NOHIT;
              }
              else if(CF == this.radio){
                  final Vector3D normal = this.C.sub(F);
                  normal.normalize();
                  Hit hit = new Hit(0,F,normal,this);
                  return hit;
              }
              else{
                  //El rayo intersecta con la bola, necesitamos conocer su punto de entrada 
                  final float FP = (float) Math.sqrt(this.radio*this.radio - CF*CF);
                  final Point3D P = F.add(ray.getDirection().multiplyByScalar(-FP));
                  final Vector3D normal = this.C.sub(P);
                  normal.normalize();
                  Hit hit = new Hit(0,P,normal,this);
                  return hit;
              }
              
          }
          //Si no, el rayo empieza dentro de la esfera. No tomamos colisión.
          else{
              return Hit.NOHIT;
          }
      
      }

  }
  
}