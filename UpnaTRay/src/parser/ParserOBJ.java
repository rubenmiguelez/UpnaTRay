package parser;
/**
 *
 * @author MAZ
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import javax.vecmath.Point2f;

import models.Triangle;
import primitives.Point3D;
import primitives.Vector3D;
import static primitives.Vector3D.ZERO_VECTOR;

public final class ParserOBJ {
  
  public static Collection<Triangle> parse (final String filename) throws FileNotFoundException, IOException {
    
    final Collection<Triangle> triangles = new LinkedList<>();
    final Collection<Point3D>     points = new LinkedList<>();
    parse(filename, triangles, points);
    return triangles;
    
  }
  
  public static void parse (final String filename,
                     final Collection<Triangle> triangles,
                     final Collection<Point3D> points) throws FileNotFoundException, IOException {
    
    final String path = System.getProperty("user.dir") + File.separator + "scenes" + File.separator;
    
    final File inputFile = new File (path + filename);
    final Scanner scanner = new Scanner(inputFile);
    scanner.useLocale(new Locale("en"));

    final Collection<Triangle> _triangles = new LinkedList<>();
    final Map<Integer, Point3D>   _points = new LinkedHashMap<>();
    final Map<Integer, Vector3D> _normals = new LinkedHashMap<>();
    
    final Map<Integer, Point2f> uv = new LinkedHashMap<>();
    
    final Map<Point3D, Vector3D> verticesToNormalsMap = new LinkedHashMap<>();
    final Map<Point3D, Point2f> verticesToTexturesMap = new LinkedHashMap<>();    
    
    int pointIndex   = 0;
    int normalIndex  = 0;
    int textureIndex = 0;
             
    while (scanner.hasNextLine()) {
      final String type = scanner.next();
      switch(type) {
        
        case "v": {
          final float x = scanner.nextFloat();
          final float y = scanner.nextFloat();
          final float z = scanner.nextFloat();
          final Point3D P = new Point3D(x, y, z);
          _points.put(++pointIndex, P);
          verticesToNormalsMap.put(P, ZERO_VECTOR);
        }
        break;
        
        case "vn": {
          final float x = scanner.nextFloat();
          final float y = scanner.nextFloat();
          final float z = scanner.nextFloat();
          _normals.put(++normalIndex, new Vector3D(x, y, z));
        }
        break;        
          
        case "vt": {
          final float u = scanner.nextFloat();
          final float v = scanner.nextFloat();
          uv.put(++textureIndex, new Point2f(u, v));
        }
        break;          
          
        case "f": {
          final String[] infoVertexA = scanner.next().split("/");
          final String[] infoVertexB = scanner.next().split("/");
          final String[] infoVertexC = scanner.next().split("/");
                   
          // Vertices
          final Point3D A = _points.get(Integer.parseInt(infoVertexA[0]));
          final Point3D B = _points.get(Integer.parseInt(infoVertexB[0]));
          final Point3D C = _points.get(Integer.parseInt(infoVertexC[0]));
          
          final Triangle t = new Triangle(A, B, C);
          _triangles.add(t);

          // Normales promediadas en vertices
          final Vector3D AB = B.sub(A);
          final Vector3D AC = C.sub(A);
          final Vector3D normal = AB.cross(AC);
          verticesToNormalsMap.put(A, verticesToNormalsMap.get(A).add(normal));
          verticesToNormalsMap.put(B, verticesToNormalsMap.get(B).add(normal));
          verticesToNormalsMap.put(C, verticesToNormalsMap.get(C).add(normal));       
          
          // Coordenadas de textura
          final int vtA;
          final int vtB;
          final int vtC;
          if ((infoVertexA.length > 1) && (!infoVertexA[1].isEmpty())) {
            vtA = Integer.parseInt(infoVertexA[1]);
            vtB = Integer.parseInt(infoVertexB[1]);
            vtC = Integer.parseInt(infoVertexC[1]);
            verticesToTexturesMap.put(A, uv.get(vtA));
            verticesToTexturesMap.put(B, uv.get(vtB));
            verticesToTexturesMap.put(C, uv.get(vtC));           
          } else
            vtA = vtB = vtC = 0;
          
          // Normales no promediadas (precalculadas)
          if ((infoVertexA.length > 2) && (!infoVertexA[2].isEmpty())) {

            final Vector3D nA = _normals.get(Integer.parseInt(infoVertexA[2]));
            final Vector3D nB = _normals.get(Integer.parseInt(infoVertexB[2]));
            final Vector3D nC = _normals.get(Integer.parseInt(infoVertexC[2]));

            triangles.add(new Triangle(A, B, C, nA, nB, nC));

          }
          
        }
        break;          
        
      }
          
    }
    
    // El fichero OBJ no incluye normales precalculadas:
    if (triangles.isEmpty()) {

      // Se normaliza el vector normal asociado a cada vértice de la malla.
      // Eso proporciona un vector normal promedio que sirve bastante fielmente.
      verticesToNormalsMap.keySet().forEach((p) -> {
        verticesToNormalsMap.get(p).normalize();
      });
      
      // Por cada triangulo construido sin normales asociadas a vertices:  
      _triangles.forEach((t) -> {
        final Point3D A = t.getA();
        final Point3D B = t.getB();
        final Point3D C = t.getC();
        final Vector3D nA = verticesToNormalsMap.get(A);
        final Vector3D nB = verticesToNormalsMap.get(B);
        final Vector3D nC = verticesToNormalsMap.get(C);
        triangles.add(new Triangle(A, B, C, nA, nB, nC));
      });

    }    
    
    _points.values().forEach((P) -> {
      points.add(P);
    });

  }
  
}