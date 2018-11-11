package tracer;

import primitives.Point3D;
import primitives.Vector3D;

public class Ray {
  
  public static final float EVIL_SOLUTION = 0.05f;  
  
  private final Vector3D v;
  private Point3D R;
  private final float evilSeed;
  private final float invEvilSeed;  

  public Ray (final Point3D R, final Vector3D v) {
    this.v = new Vector3D(v);
    this.v.normalize();
    this.evilSeed = this.v.dot(this.v);
    this.invEvilSeed = (float) (1.0 / Math.sqrt(this.evilSeed));
    this.R = new Point3D(R);
    //if (Math.signum(Math.abs(evilSeed - 1.0d)) != 0) System.err.println(Math.abs(evilSeed - 1.0f));
  }

  public Ray (final Point3D R, final Point3D Q) {
    this(R, Q.sub(R));
  }
  
//  protected Ray (final Point3D R, final Vector3D v, final float shift) {
//    this(R.add(shift, v), v);
//  }
//
//  public Ray (final Point3D R, final Point3D Q, final float shift) {
//    this(R, Q.sub(R), shift);
//  }  

  /**
   * Constructor copia
   *
   * @param r
   */
  public Ray (final Ray r) {
    this.v = new Vector3D(r.getDirection());
    this.evilSeed = r.getEvilSeed();
    this.invEvilSeed = (float) (1.0 / Math.sqrt(this.evilSeed));
    this.R = new Point3D(r.getStartingPoint());
  }

  public Vector3D getDirection () {
    this.v.normalize();
    return this.v;
  }

//  public void setDirection (final Vector3D dir) {
//    this.v = dir;
//  }

  public Point3D getStartingPoint () {
    return this.R;
  }

  public void setStartingPoint (final Point3D R) {
    this.R = R;
  }

  public Point3D pointAtParameter (final float t) {
    //return R.add(t * invEvilSeed, v);
    return R.add(t, v);
  }
  
  public float getEvilSeed () {
    return evilSeed;
  }
  
  public void shift () {
    R = R.add(EVIL_SOLUTION, v);
  }

  @Override
  public String toString () {
    return "Rayo: Origen " + R.toString() + " Direccion " + v.toString();
  }
  
  public boolean isOperative () {
    return (Math.signum(this.getEvilSeed() - 0.5e-7f) > 0);
  } 
  
}