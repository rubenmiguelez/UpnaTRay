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
    private final Vector3D a,b,c;
    public Triangle(Point3D A,Point3D B,Point3D C,Vector3D a, Vector3D b,Vector3D c, Color color, Material mat){
        super(color,mat);
        this.A = A;
        this.B = B;
        this.C = C;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public Triangle(Point3D A,Point3D B,Point3D C,Vector3D a, Vector3D b,Vector3D c){
        this(A,B,C,a,b,c,new Color((int)Math.random()*255,(int)Math.random()*255,(int)Math.random()*255),null);
  
    }
    

    @Override
    public Hit intersectionWith(Ray ray) {
        return Hit.NOHIT;
    }
}
