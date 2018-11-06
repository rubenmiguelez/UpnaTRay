package models;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector3D;
import shaders.Material;
import tracer.Hit;
import tracer.Ray;

public class Sphere extends Object3D {

  private Sphere (final Point3D C, final float radio, final Color color, final Material material) {
    super(color, material);
  }    

  public Sphere (final Point3D C, final float radio, final Color color) {
    this(C, radio, color, null);
  }
  
  public Sphere (final Point3D C, final float radio, final Material material) {
    this(C, radio, null, material);
  }  

  @Override
  public Hit intersectionWith (final Ray ray) {
      
      
    return Hit.NOHIT;

  }
  
}