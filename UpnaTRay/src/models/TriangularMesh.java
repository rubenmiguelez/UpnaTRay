/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Collection;
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
    public TriangularMesh(final Collection<Triangle> triangulos){
        
        this.triangulos = triangulos;
        this.points = null;
        
    }
    public TriangularMesh (final Collection<Point3D> points,final Collection<Triangle> triangulos){
        this.triangulos = triangulos;
        this.points = points;
    }
    @Override
    public Hit intersectionWith(Ray ray) {
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
    
}
