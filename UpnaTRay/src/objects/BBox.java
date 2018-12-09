/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import primitives.Point3D;
import primitives.Vector3D;
import tracer.Ray;

/**
 *
 * @author linux1
 */
public class BBox extends BoundingBox{

    public final float xMin,xMax,yMin,yMax,zMin,zMax;
    final Point3D C;
    
    public BBox(float xMin, float xMax, float yMin, float yMax, float zMin, float zMax){
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.zMin = zMin;
        this.zMax = zMax;
        this.C = new Point3D((xMax+xMin)/2f,(yMax+yMin)/2f,(zMax+zMin)/2f);
    }
    
    @Override
    public boolean intersects(Ray ray) {
        final Point3D R = ray.getStartingPoint();
        final Vector3D v = ray.getDirection();
        if(Math.signum(v.x)!=0){
            //Cara en plano x = xMin
            float a = (xMin - R.x)/v.x;
            float dy = Math.abs(R.y + a*v.y - C.y);
            float dz = Math.abs(R.z + a*v.z - C.z);
            if(Math.signum(dy-(yMax-yMin)*0.5f)<=0 && Math.signum(dz - (zMax-zMin)*0.5f)<=0){
                return true;
            }
            //Cara en plano x = xMax
            a = (xMax - R.x)/v.x;
            dy = Math.abs(R.y + a*v.y - C.y);
            dz = Math.abs(R.z + a*v.z - C.z);
            if(Math.signum(dy-(yMax-yMin)*0.5f)<=0 && Math.signum(dz - (zMax-zMin)*0.5f)<=0){
                return true;
            }
        }
        if(Math.signum(v.y)!=0){
            //Cara en plano y = yMin
            float a = (yMin - R.y)/v.y;
            float dx = Math.abs(R.x + a*v.x - C.x);
            float dz = Math.abs(R.z + a*v.z - C.z);
            if(Math.signum(dx-(xMax-xMin)*0.5f)<=0 && Math.signum(dz - (zMax-zMin)*0.5f)<=0){
                return true;
            }
            //Cara en plano y = yMax
            a = (yMax - R.y)/v.y;
            dx = Math.abs(R.x + a*v.x - C.x);
            dz = Math.abs(R.z + a*v.z - C.z);
            if(Math.signum(dx-(xMax-xMin)*0.5f)<=0 && Math.signum(dz - (zMax-zMin)*0.5f)<=0){
                return true;
            }
        }
        if(Math.signum(v.z)!=0){
            //Cara en plano z = zMin
            float a = (zMin - R.z)/v.z;
            float dx = Math.abs(R.x + a*v.x - C.x);
            float dy = Math.abs(R.y + a*v.y - C.y);
            if(Math.signum(dx-(xMax-xMin)*0.5f)<=0 && Math.signum(dy - (yMax-yMin)*0.5f)<=0){
                return true;
            }
            //Cara en plano z = zMax
            a = (zMax - R.z)/v.z;
            dx = Math.abs(R.x + a*v.x - C.x);
            dy = Math.abs(R.y + a*v.y - C.y);
            if(Math.signum(dx-(xMax-xMin)*0.5f)<=0 && Math.signum(dy - (yMax-yMin)*0.5f)<=0){
                return true;
            }
        }
        return false;
    }
    
}
