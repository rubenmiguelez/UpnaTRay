/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.Color;
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
    public Triangle(Point3D A,Point3D B,Point3D C){
        this(A,B,C,new Color((int)Math.random()*255,(int)Math.random()*255,(int)Math.random()*255),null);
  
    }
    

    @Override
    public Hit intersectionWith(Ray ray) {
        final float c = -(ray.getDirection().dot(n));
        if(Math.signum(c)>0){
            final float b = (ray.getStartingPoint().sub(A)).dot(n);
            if(Math.signum(b)>=0){
                //Continuar algoritmo progresivo
            }
        }
        return Hit.NOHIT;
    }
}
