package view;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector4f;

import tracer.RayGenerator;
import primitives.Point3D;
import primitives.Vector3D;
import tracer.Ray;

public class Camera {

  // ATRIBUTOS
  private final Point3D position;
  private final Vector3D up; // Vector Up
  private final Vector3D view; // Vector LookAt
  private final Matrix4f camera2scene;
  private Projection optics;

  // CONSTRUCTORES
  public Camera (final Point3D V, final Point3D C, final Vector3D up) {
    optics = null;
    this.position = V;
    up.normalize();
    this.up= up;
    this.view = C.sub(V);
    this.view.normalize();
    Vector3D w = this.view;
    float s = up.dot(w);
    float t = (float) (1/(Math.sqrt(1-s*s)));
    this.camera2scene = new Matrix4f(t*(up.getZ()*w.getY()-up.getY()*w.getZ()),t*(up.getX()-s*w.getX()),-w.getX(),V.getX(),
                                     t*(up.getX()*w.getZ()-up.getZ()*w.getX()),t*(up.getY()-s*w.getY()),-w.getY(),V.getY(),
                                     t*(up.getY()*w.getX()-up.getX()*w.getY()),t*(up.getZ()-s*w.getZ()),-w.getZ(),V.getZ(),
                                     0,0,0,1);
  }

  public Camera (final Camera c) {
    this.position = new Point3D(c.position);
    this.up = new Vector3D(c.up);
    this.view = new Vector3D(c.view);
    this.camera2scene = new Matrix4f(c.camera2scene);
    this.optics = c.optics;
  }

  public final void toSceneCoordenates (final Vector3D v) {
    camera2scene.transform(v);
  }

  public final void toSceneCoordenates (final Point3D P) {
    camera2scene.transform(P);
  }

  public final void toSceneCoordenates(final Ray R){
      toSceneCoordenates(R.getStartingPoint());
      toSceneCoordenates(R.getDirection());
  }
  
  public final Vector3D getLook () {
    this.view.normalize();
    return this.view;
  }

  public final Point3D getPosition () {
    return this.position;
  }

  public final void setProjection (final Projection p) {
    this.optics = p;
  }

  public final Projection getProjection () {
    return this.optics;
  }

  public final RayGenerator getRayGenerator (final int W, final int H) {
    return this.optics.getRayGenerator(this, W, H);
  }

}
