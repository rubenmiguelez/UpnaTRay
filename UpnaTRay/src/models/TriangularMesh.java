/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Collection;
import objects.BBox;
import primitives.Point3D;
import tracer.Hit;
import tracer.Ray;

/**
 *
 * @author ruben
 */
public class TriangularMesh extends Object3D{
    final Collection<Triangle> triangulos;
    final Collection<Point3D> points;
    public BBox boundingBox;
    public TriangularMesh(final Collection<Triangle> triangulos){
        
        this.triangulos = triangulos;
        this.points = null;
        CreadorBoundingBox(triangulos);
        
    }
    public TriangularMesh (final Collection<Point3D> points,final Collection<Triangle> triangulos){
        this.triangulos = triangulos;
        this.points = points;
        CreadorBoundingBox(triangulos);
    }
    
    private void CreadorBoundingBox(final Collection<Triangle> triangulos){
        float xMin = 0,xMax = 0,yMin = 0,yMax = 0,zMin = 0,zMax = 0;
        for(Triangle triangulo : triangulos){
            if(xMin == 0 && xMax == 0 && yMin == 0 && yMax == 0 && zMin == 0 && zMax == 0){
                xMin = triangulo.getA().x;
                xMax = triangulo.getA().x;
                yMin = triangulo.getA().y;
                yMax = triangulo.getA().y;
                zMin = triangulo.getA().z;
                zMax = triangulo.getA().z;
            }
            for(Point3D punto : triangulo.getPoints()){
                if(xMin>punto.x)
                    xMin = punto.x;
                if(yMin>punto.y)
                    yMin = punto.y;
                if(zMin>punto.z)
                    zMin = punto.z;
                if(xMax<punto.x)
                    xMin = punto.x;
                if(yMax<punto.y)
                    yMin = punto.y;
                if(zMax<punto.z)
                    zMin = punto.z;
            
            }
        }
        System.out.println(xMin + " " + xMax + " " + yMin + " " + yMax + " " + zMin + " " + zMax + " ");
        boundingBox = new BBox(xMin,xMax,yMin,yMax,zMin,zMax);
    }
    
    @Override
    public Hit intersectionWith(Ray ray) {
        if(boundingBox.intersects(ray)){
            Hit hit;
            Hit puntoInterseccion = Hit.NOHIT;
            for(Triangle triangle : triangulos){
                hit = triangle.intersectionWith(ray);
                if(hit!=Hit.NOHIT)
                    if(puntoInterseccion.equals(Hit.NOHIT)){
                        puntoInterseccion = hit;
                    
                    }else if(puntoInterseccion.getPoint().sub(ray.getStartingPoint()).length()>hit.getPoint().sub(ray.getStartingPoint()).length()){
                        puntoInterseccion = hit;
                    }
            }
            return puntoInterseccion;
        }
        else 
            return Hit.NOHIT;
    }
    
}
