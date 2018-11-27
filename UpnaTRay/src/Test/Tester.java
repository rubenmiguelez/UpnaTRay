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
import parser.ParserOBJ;
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
        Camera cam = new Camera(new Point3D(0,0,0),new Point3D(-0.5f,0.3f,-1),new Vector3D(0,1,0));
        Projection persp = new Perspective((float)Math.PI/4f,1.5f);
        cam.setProjection(persp);
        Group3D objetosEscena = new Group3D();
        TriangularMesh triang = new TriangularMesh(ParserOBJ.parse("pig.obj"));
        Collection<Triangle> triangulos = new LinkedList<>();
        Triangle triangulo = new Triangle(new Point3D(-1,1,-2),new Point3D(-1,-1,0),new Point3D(1,1,0)); 
        Triangle triangulo1 = new Triangle(new Point3D(1,1,0),new Point3D(-1,-1,0),new Point3D(1,-1,0));
        triangulos.add(triangulo);
        triangulos.add(triangulo1);
        Sphere esfera = new Sphere(new Point3D(triang.boundingBox.xMin,triang.boundingBox.yMin,triang.boundingBox.zMin),0.01f,Color.RED);
        Sphere esfera1 = new Sphere(new Point3D(triang.boundingBox.xMax,triang.boundingBox.yMin,triang.boundingBox.zMin),0.01f,Color.RED);
        Sphere esfera2 = new Sphere(new Point3D(triang.boundingBox.xMax,triang.boundingBox.yMax,triang.boundingBox.zMin),0.01f,Color.RED);
        Sphere esfera3 = new Sphere(new Point3D(triang.boundingBox.xMin,triang.boundingBox.yMax,triang.boundingBox.zMin),0.01f,Color.RED);
        Sphere esfera4 = new Sphere(new Point3D(triang.boundingBox.xMax,triang.boundingBox.yMin,triang.boundingBox.zMax),0.01f,Color.RED);
        Sphere esfera5 = new Sphere(new Point3D(triang.boundingBox.xMin,triang.boundingBox.yMin,triang.boundingBox.zMax),0.01f,Color.RED);
        Sphere esfera6 = new Sphere(new Point3D(triang.boundingBox.xMax,triang.boundingBox.yMax,triang.boundingBox.zMax),0.01f,Color.RED);
        Sphere esfera7 = new Sphere(new Point3D(triang.boundingBox.xMin,triang.boundingBox.yMax,triang.boundingBox.zMax),0.01f,Color.RED);
        
        objetosEscena.addObject(esfera);
        objetosEscena.addObject(esfera1);
        objetosEscena.addObject(esfera2);
        objetosEscena.addObject(esfera3);
        objetosEscena.addObject(esfera4);
        objetosEscena.addObject(esfera5);
        objetosEscena.addObject(esfera6);
        objetosEscena.addObject(esfera7);
        objetosEscena.addObject(triang);
        
        imagen.synthesis(cam, objetosEscena);
        imagen.show();
    }
}
