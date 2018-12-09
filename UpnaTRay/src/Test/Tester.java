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
import models.Capsule;
import models.Cylinder;
import models.Group3D;
import models.Plane;
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
        Camera cam = new Camera(new Point3D(0f,0,0),new Point3D(0f,0f,-1),new Vector3D(0,1,0));
        Projection persp = new Angular((float)Math.PI/4f);
        cam.setProjection(persp);
        Group3D objetosEscena = new Group3D();
       
        objetosEscena.addObject(new Capsule(new Point3D(0,0,-8),new Vector3D(0.5f,0,0.4f),1,2,Color.YELLOW));
        imagen.synthesis(cam, objetosEscena);
        imagen.show();
    }
}
