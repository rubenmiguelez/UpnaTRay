package models;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector3D;
import shaders.Material;
import tracer.Hit;
import tracer.Ray;

public class Sphere extends Object3D {
    private Point3D C;
    private float radio;
  private Sphere (final Point3D C, final float radio, final Color color, final Material material) {
    super(color, material);
    this.C = C;
    this.radio = radio;
  }    

  public Sphere (final Point3D C, final float radio, final Color color) {
    this(C, radio, color, null);
    this.C = C;
    this.radio = radio;
  }
  
  public Sphere (final Point3D C, final float radio, final Material material) {
    this(C, radio, null, material);
    this.C = C;
    this.radio = radio;
  }  

  @Override
  public Hit intersectionWith (final Ray ray) {
      
      
    return Hit.NOHIT;

  }
  
}