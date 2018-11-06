package view;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector4f;

import tracer.RayGenerator;
import primitives.Point3D;
import primitives.Vector3D;

public class Camera {

  // ATRIBUTOS
  private final Point3D position;
  private final Vector3D up; // Vector Up
  private final Vector3D view; // Vector LookAt
  private final Matrix4f camera2scene;
  private Projection optics;

  // CONSTRUCTORES
  public Camera (final Point3D V, final Point3D C, final Vector3D up) {


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

  public final Vector3D getLook () {
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
