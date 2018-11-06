package objects.boundingboxes;
/**
 *
 * @author MAZ
 */

import tracer.Ray;

public abstract class BoundingBox {
 
  /* 
    Devuelve true si el rayo atraviesa el volumen
    definido por la caja; false en caso contrario.
  */
  public abstract boolean intersects (final Ray ray);
  
}