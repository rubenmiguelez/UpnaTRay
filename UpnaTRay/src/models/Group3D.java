package models;

import java.util.ArrayList;
import java.util.List;
import primitives.Vector3D;
import tracer.Hit;
import tracer.Ray;

public class Group3D extends Object3D {
  
  private final ArrayList<Object3D> objetos;

  public Group3D () {
    this.objetos = new ArrayList<>();
  }

  public Group3D addObject (final Object3D objeto) {
    objetos.add(objeto);
    return this;
  }

  public Object3D getObject (final int j) {

    assert ((j >= 0) && (j < objetos.size()));

    return objetos.get(j);
  }

  @Override
  public Hit intersectionWith (final Ray ray) {

    Hit closestHit = Hit.NOHIT;

    for (final Object3D objeto: objetos) {

      final Hit lastHit = objeto.intersectionWith(ray);

      if (lastHit.isCloser(closestHit)) {
        closestHit = lastHit;
      }

    }

    return closestHit;

  }
  
//  public Hit _intersects_ (final Ray ray) {
//
//    Hit closestHit = Hit.NOHIT;
//
//    for (final Object3D objeto: objetos) {
//
//      final Hit lastHit = objeto.intersects(ray);
//
//      if (lastHit.isCloser(closestHit)) {
//        closestHit = lastHit;
//      }
//
//    }
//
//    return closestHit;
//
//  }
  
  public boolean intersectsAnyCloser (final Ray ray, final Hit hit, final float squareDistance) {
    
    final Vector3D v = ray.getDirection();
    final Vector3D n = hit.getNormal();
    
    // Si el rayo se dirige al semiespacio posterior del plano tangente en P
    // es que parte del punto P atravesando el objeto (opaco); eso significa
    // que el propio objeto hace sombra al punto P.
    if (Math.signum(v.dot(n)) < 0)
      return true;
    
    final float shift = Ray.EVIL_SOLUTION;
    
    return (objetos.stream()
//            .map((objeto) -> objeto.intersectionWith(ray).getT())
              .map((x) -> x.intersectionWith(ray))
              .filter(Hit::hits)
              .map((h) -> h.getT())
              .anyMatch((t) -> (((t + shift) * (t + shift)) < squareDistance)));
    
  }
  
//  @Override
  public List<Hit> completeIntersectionWith (final Ray ray) {
    
    final List<Hit> hits = new ArrayList<>();

    return hits;
    
  }  

}