/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import gui.Image;
import java.awt.Color;
import models.Group3D;
import models.Sphere;
import primitives.Point3D;
import primitives.Vector3D;
import view.Camera;
import view.Orthographic;
import view.Perspective;
import view.Projection;

/**
 *
 * @author ruben
 */
public class Tester {
    
    public static void main(String [] args){
        Image imagen = new Image("prueba",720,1080,Color.BLACK);
        Camera cam = new Camera(new Point3D(0,0,0),new Point3D(0,0,-1),new Vector3D(0,1,0));
        Projection persp = new Perspective(30,1.5f);
        cam.setProjection(persp);
        Group3D objetosEscena = new Group3D();
        Sphere esfera = new Sphere(new Point3D(0,0,-9),2,Color.RED);
        Sphere esfera2 = new Sphere(new Point3D(1,0,-10),3,Color.GREEN);
        Sphere esfera3 = new Sphere(new Point3D(-3,0,-4),4,Color.CYAN);
        Sphere esfera4 = new Sphere(new Point3D(4,3,-4),1,Color.MAGENTA);
        objetosEscena.addObject(esfera);
        objetosEscena.addObject(esfera2);
        objetosEscena.addObject(esfera3);
        objetosEscena.addObject(esfera4);
        imagen.synthesis(cam, objetosEscena);
        imagen.show();
    }
}
