/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import gui.Image;
import java.awt.Color;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import models.Group3D;
import models.Sphere;
import models.Triangle;
import models.TriangularMesh;
import primitives.Point3D;
import primitives.Vector3D;
import view.Angular;
import view.Camera;
import view.Orthographic;
import view.Perspective;
import view.Projection;

/**
 *
 * @author ruben
 */
public class Tester {
    
    public static void main(String [] args) throws IOException{
        Image imagen = new Image("prueba",720,1080,Color.BLACK);
        Camera cam = new Camera(new Point3D(-10,10,20),new Point3D(0,0,0),new Vector3D(0,1,0));
        Projection persp = new Perspective(60f,1.5f);
        cam.setProjection(persp);
        Group3D objetosEscena = new Group3D();
        TriangularMesh triang = new TriangularMesh(parser.ParserOBJ.parse("Car.obj"));
        Collection<Triangle> triangulos = new LinkedList<>();
        Triangle triangulo = new Triangle(new Point3D(-1,1,-2),new Point3D(-1,-1,0),new Point3D(1,1,0)); 
        Triangle triangulo1 = new Triangle(new Point3D(1,1,0),new Point3D(-1,-1,0),new Point3D(1,-1,0));
        triangulos.add(triangulo);
        triangulos.add(triangulo1);
        Sphere esfera = new Sphere(new Point3D(0,0,-8),2,Color.RED);
        Sphere esfera2 = new Sphere(new Point3D(1,0,-10),3,Color.GREEN);
        Sphere esfera3 = new Sphere(new Point3D(-3,0,-4),3,Color.CYAN);
        Sphere esfera4 = new Sphere(new Point3D(4,3,-4),1,Color.MAGENTA);
        
        /*objetosEscena.addObject(esfera);
        objetosEscena.addObject(esfera2);
        objetosEscena.addObject(esfera3);
        objetosEscena.addObject(esfera4);*/
        objetosEscena.addObject(triang);
        
        imagen.synthesis(cam, objetosEscena);
        imagen.show();
    }
}
