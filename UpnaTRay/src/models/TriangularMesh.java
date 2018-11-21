/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Collection;
import tracer.Hit;
import tracer.Ray;

/**
 *
 * @author ruben
 */
public class TriangularMesh extends Object3D{

    public TriangularMesh(final Collection){
        
    }
    
    @Override
    public Hit intersectionWith(Ray ray) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
