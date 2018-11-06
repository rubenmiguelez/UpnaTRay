package models;
/**
 *
 * @author MAZ
 */
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import primitives.Point3D;
import primitives.Vector3D;
import tracer.Hit;
import tracer.Ray;

public class AffineTransformation extends Object3D {
  
  static private final Vector3f NEUTRAL_S = new Vector3f(1.0f, 1.0f, 1.0f);
  static private final Vector3f NEUTRAL_T = new Vector3f(0.0f, 0.0f, 0.0f);
  static private final Vector3f NEUTRAL_AXIS = new Vector3f(1.0f, 0.0f, 0.0f);
  static private final float NEUTRAL_THETA = 0.0f;  
  
  static private final Vector4f L = new Vector4f(0, 0, 0, 1);  

  public final Matrix4f M;     // Matriz de transformación directa
  public final Matrix4f invM;  // Matriz de transformación inversa
  public final Matrix4f invRS; // Matriz de transformación inversa para vectores
  public final Matrix4f N;     // Matriz para ajuste de normales
  public final Object3D model; // Modelo
  
  private Matrix4f getRotationMatrix (final Vector3f axis, final float theta) {
     
    final double radTheta = Math.toRadians(theta);
    final float s = (float) Math.sin(radTheta);
    final float t = (float) Math.cos(radTheta);
    axis.normalize();
    final float a = axis.x;
    final float b = axis.y;
    final float c = axis.z;
   
    final Vector4f r0 = new Vector4f(a * a * (1 - t) + t,     b * a * (1 - t) - c * s, c * a * (1 - t) + b * s, 0f);
    final Vector4f r1 = new Vector4f(a * b * (1 - t) + c * s, b * b * (1 - t) + t,     c * b * (1 - t) - a * s, 0f);
    final Vector4f r2 = new Vector4f(a * c * (1 - t) - b * s, b * c * (1 - t) + a * s, c * c * (1 - t) + t,     0f);    

    final Matrix4f R = new Matrix4f();
    
    R.setRow(0, r0);
    R.setRow(1, r1);
    R.setRow(2, r2);
    R.setRow(3, L);
   
    return R;
    
  }
  
  private Matrix4f getTranslationMatrix (final Vector3f d) {
    final Matrix4f T = new Matrix4f();
    T.setIdentity();
    T.m03 = d.x;
    T.m13 = d.y;
    T.m23 = d.z;
    return T;
  }
  
  private Matrix4f getScaleMatrix (final Vector3f s) {
    final Matrix4f S = new Matrix4f();
    S.setIdentity();
    S.m00 = s.x;
    S.m11 = s.y;
    S.m22 = s.z;
    return S; 
  }

  public AffineTransformation (final Vector3f s,
                               final Vector3f axis,
                               final float theta,
                               final Vector3f d,
                               final Object3D model) {

    if ((Math.signum(s.x) == 0) || (Math.signum(s.y) == 0) || (Math.signum(s.z) == 0))
      throw new IllegalArgumentException("Factor de escala igual a 0");
    if ((Math.signum(axis.x) == 0) && (Math.signum(axis.y) == 0) && (Math.signum(axis.z) == 0))
      throw new IllegalArgumentException("Eje de rotación nulo");
       
    // Modelo a transformar
    this.model = model;
    
    // Material y Color
    if (model.color != null)
      color = model.color;
    if (model.material != null)
      material = model.material;

    final Matrix4f T    = getTranslationMatrix(d);
    final Matrix4f invT = getTranslationMatrix(new Vector3f(-d.x, -d.y, -d.z));

    final Matrix4f S    = getScaleMatrix(s);
    final Matrix4f invS = getScaleMatrix(new Vector3f(1.0f / s.x, 1.0f / s.y, 1.0f / s.z));

    final Matrix4f R    = getRotationMatrix(axis, +theta);
    final Matrix4f invR = getRotationMatrix(axis, -theta);

    // Matriz de transformación afín
    M = new Matrix4f();
    M.setIdentity();
    M.mul(T);
    M.mul(R);
    M.mul(S);

    // Matriz de transformación afín inversa
    invM = new Matrix4f();
    invM.setIdentity();
    invM.mul(invS);
    invM.mul(invR);
    invM.mul(invT);

    // Matriz de transformación inversa para vector dirección del rayo
    invRS = new Matrix4f();
    invRS.setIdentity();
    invRS.mul(invS);
    invRS.mul(invR);

    // Matriz para transformación de normales
    N = new Matrix4f();
    N.setIdentity();
    N.mul(R);
    N.mul(invS);
    
    System.out.println("R:\n" + R);
    System.out.println("invR:\n" + invR);
    System.out.println("S:\n" + S);
    System.out.println("invS:\n" + invS);
    System.out.println("T:\n" + T);
    System.out.println("invT:\n" + invT);    
    System.out.println("M:\n" + M);
    System.out.println("invM:\n" + invM);
    final Matrix4f I = new Matrix4f();
    I.setIdentity();
    I.mul(M);
    I.mul(invM);
    System.out.println("I:\n" + I);    

  }
  
  public AffineTransformation (final Vector3f s,
                               final Vector3f axis,
                               final float theta,
                               final Object3D model) {
    this(s, axis, theta, NEUTRAL_T, model);
  }  
  
  public AffineTransformation (final Vector3f axis,
                               final float theta,
                               final Vector3f d,
                               final Object3D model) {
    this(NEUTRAL_S, axis, theta, d, model);
  }
  
  public AffineTransformation (final Vector3f s,
                               final Vector3f d,
                               final Object3D model) {
    this(s, NEUTRAL_AXIS, NEUTRAL_THETA, d, model);
  }  
  
  public AffineTransformation (final float sx, final float sy, final float sz,
                               final Object3D model) {
    this(new Vector3f(sx, sy, sz), NEUTRAL_AXIS, NEUTRAL_THETA, NEUTRAL_T, model);
  }
  
  public AffineTransformation (final Vector3f axis,
                               final float theta,
                               final Object3D model) {
    this(NEUTRAL_S, axis, theta, NEUTRAL_T, model);
  }
  
  public AffineTransformation (final Vector3f d,
                               final Object3D model) {
    this(NEUTRAL_S, NEUTRAL_AXIS, NEUTRAL_THETA, d, model);
  }  

  @Override
  public Hit intersectionWith (final Ray ray) {

    final Point3D  R = new Point3D(ray.getStartingPoint());
    final Vector3D v = new Vector3D(ray.getDirection());

    invM.transform(R);
    invRS.transform(v);
    //v.normalize();

    final Ray transformedRay = new Ray(R, v);
    
    final Vector3D diff = ray.getDirection().sub(v);
    final float d2 = diff.dot(diff);
            
//    System.err.println("R: " + ray.getStartingPoint());
//    System.err.println("R':" + transformedRay.getStartingPoint());
//    System.err.println("v: " + ray.getDirection());
//    System.err.println("v':" + transformedRay.getDirection());            

    final Hit hit = model.intersectionWith(transformedRay);
    if (hit.hits()) { // Se vuelve a espacio de la escena
      
      final Point3D P = hit.getPoint();
      M.transform(P);
      
      final Vector3D RP = new Vector3D(ray.getStartingPoint(), P);
      final float a = RP.length();

      final Vector3D n = hit.getNormal();
      N.transform(n);
      n.normalize();

      return new Hit(a, P, n, hit.getObject());

    } else {
      //final Vector3D x = new Vector3D(v);
//      M.transform(v);
//      System.err.println("v: " + ray.getDirection());
//      System.err.println("v':" + v); 
//      System.err.println(v.dot(v));
//      System.err.println();
    }

    return Hit.NOHIT;

  }

//  public List<Hit> completeIntersectionWith (final Ray ray) {
//    return new ArrayList();
//  }

}