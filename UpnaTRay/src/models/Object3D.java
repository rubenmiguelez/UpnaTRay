package models;

import java.awt.Color;

import primitives.Point3D;
import shaders.Material;
import tracer.Hit;
import tracer.Ray;

public abstract class Object3D {

  protected Color color;
  protected Material material;

  public Object3D () {
    this.color = Color.black;
    this.material = null;    
  }

  /**
   * Este constructor permite definir el color del objeto a crear
   *
   * @param color
   */
  public Object3D (final Color color) {
    this.color = color;
    this.material = null;
  }
  
  /**
   * Este constructor permite definir el color del objeto a crear
   *
   * @param material
   */
  public Object3D (final Material material) {
    this.color = null;
    this.material = material;
  }
  
  /**
   * Este constructor permite definir el color del objeto a crear
   *
   * @param color
   * @param material
   */
  protected Object3D (final Color color, final Material material) {
    this.color = color;
    this.material = material;
  }    

  /**
   * Devuelve el color del objeto
   *
   * @return this.color
   */
  public Color getColor () {
    return color;
  }
  
  /**
   * Devuelve el color del objeto
   *
   * @param scene
   * @param lights
   * @param hit
   * @param V
   * @return this.color
   *
  public Color getColor (final Group3D scene,
                         final LightGroup lights,
                         final Hit hit,
                         final Point3D V) {
    if (material != null)
      return material.getColor(scene, lights, hit, V);
    else
      return color;
  }
  */
  
  /**
   * Devuelve el material del objeto
   *
   * @return this.material
   */
  public Material getMaterial () {
    return material;
  }

  /**
   * Determina si el rayo intersecta con el objeto
   *
   * @param ray Rayo del que se desea saber si intersecciona
   * @return
   */    
  public abstract Hit intersectionWith (final Ray ray);
  
}