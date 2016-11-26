import java.lang.*;
import java.util.Vector;

public class Vec2d
{
    public double x = 0.0;
    public double y = 0.0;
    
    public Vec2d()
    {
        
    }
    
    public Vec2d(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    Vector<Double> toVector()
    {
        Vector<Double> vec = new Vector<Double>();
        vec.add(x);
        vec.add(y);
        return vec;
    }
    
    double norm()
    {
        return Math.sqrt(x*x + y*y);
    }
    
    Vec2d plus(double in)
    {
        x += in;
        y += in;
        return this;
    }
    
    Vec2d plus(Vec2d in)
    {
        x += in.x;
        y += in.y;
        return this;
    }
    
    Vec2d minus(double in)
    {
        x -= in;
        y -= in;
        return this;
    }
    
    Vec2d minus(Vec2d in)
    {
        x -= in.x;
        y -= in.y;
        return this;
    }
    
    Vec2d times(double in)
    {
        x *= in;
        y *= in;
        return this;
    }
    
    Vec2d divide(double in)
    {
        x /= in;
        y /= in;
        return this;
    }
}
