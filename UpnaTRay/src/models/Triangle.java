/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import primitives.Point3D;
import primitives.Vector3D;
import shaders.Material;
import tracer.Hit;
import tracer.Ray;

/**
 *
 * @author ruben
 */
public class Triangle extends Object3D{
    private final Point3D A,B,C;
    private final Vector3D n;
    public Triangle(Point3D A,Point3D B,Point3D C, Color color, Material mat){
        super(color,mat);
        this.A = A;
        this.B = B;
        this.C = C;
        this.n = (B.sub(A).cross(C.sub(A)));
    }
    public Triangle(Point3D A,Point3D B,Point3D C,Vector3D nA,Vector3D nB,Vector3D nC){
        super(Color.GREEN,null);
        this.A = A;
        this.B = B;
        this.C = C;
        //Modificar para almacenar las tres normales, de momento me vale.
        this.n = (B.sub(A).cross(C.sub(A)));
        //this.n = nA.add(nB).add(nC).multiplyByScalar((float)1/3);
    }
    public Triangle(Point3D A,Point3D B,Point3D C){
        this(A,B,C,Color.GREEN,null);
  
    }
    

    @Override
    public Hit intersectionWith(Ray ray) {
        final float c = -(ray.getDirection().dot(n));
        if(Math.signum(c)>0){
            final float b = (ray.getStartingPoint().sub(A)).dot(n);
            if(Math.signum(b)>=0){
                //Continuar algoritmo progresivo
                final Vector3D vAR = ray.getDirection().cross(ray.getStartingPoint().sub(A));
                final float beta = -((C.sub(A)).dot(vAR))/c;
                if((beta>=0)&&(1>=beta)){
                    final float gamma = ((B.sub(A)).dot(vAR))/c;
                    if((gamma>=0)&&(1>=gamma))
                        if(1>=(beta+gamma)){
                            final float a = b/c;
                            return (new Hit(0,A.add((B.sub(A)).multiplyByScalar(beta)).add((C.sub(A)).multiplyByScalar(gamma)),n.multiplyByScalar(1f/n.length()),this));
                        }
                }
            }
        }
        return Hit.NOHIT;
    }
    public Point3D getA(){
        return A;
    }
    public Point3D getB(){
        return B;
    }
    public Point3D getC(){
        return C;
    }
    public Collection<Point3D> getPoints(){
        Collection<Point3D> puntos = new ArrayList();
        puntos.add(A);
        puntos.add(B);
        puntos.add(C);
        return puntos;
    }
}
