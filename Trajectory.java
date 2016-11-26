import java.lang.*;
import java.lang.Math;
import java.io.PrintWriter;

// Units are in millimeters
public class Trajectory
{
    // Initial position and launch velocity of the ball
    Vec2d s = new Vec2d(); // global position
    Vec2d v = new Vec2d(); // global velocity (includes vehicle speed)
    
    double theta = 0.7071; // launch angle in radians (default is 45 degrees)
    
    double g = 9800.0; // gravitational constant mm / s^2
    
    public Trajectory()
    {
        
    }
    
    void setAngleDegrees(double theta_degrees)
    {
        theta = Math.toRadians(theta_degrees);
    }
    
    Vec2d position(double t)
    {
        double x = s.x + v.x*t;
        double y = s.y + v.y*t - 0.5*g*t*t;
        return new Vec2d(x, y);
    }
    
    // Pass in the target's position
    // Returns the intercept Solution
    Solution intercept(Vec2d target)
    {   
        // t_impact = (target.x - s.x) / (v_norm * cos(theta))
        
        // y(t_impact) = s.y + v_norm*sin(theta)*t_impact - 0.5*g*t_impact*t_impact
        // substitute t_impact and simplify
        // target.y = s.y + sin(theta)/cos(theta)*(target.x - s.x) - 0.5*g*((target.x - s.x) / (v_norm * cos(theta)))^2
        // get v_norm by itself, invert and squre both sides
        
        double dist = target.x - s.x; // distance from launched ball to target
        
        double internal = 0.5 * g / (s.y - target.y + Math.tan(theta)*dist);
        
        double v_norm = 0.0;
        
        if (internal < 0.0)
        {
            throw new RuntimeException("Can't take square root of a negative number");
        }
        else
        {
            v_norm = Math.sqrt(internal) * dist / Math.cos(theta);
        }
        
        double vx = v_norm*Math.cos(theta);
        double vy = v_norm*Math.sin(theta);
        
        Vec2d v_aim = new Vec2d(vx, vy);
        
        // compute the time it takes to reach the target x value, this is our flight time
        double t_impact = Math.abs(dist) / vx;
        
        // compute the maximum height as the vertex of the y-component parabola
        // y = a*t*t + b*t + c
        // where the vertex time is -b / (2*a)
        // y(t)= -0.5*g*t*t + vy*t + s.y
        // simplified
        double t_max = vy / g; // time of maximum height
        double max_height = -0.5*g*t_max*t_max + vy*t_max + s.y;
        
        Solution sol = new Solution();
        sol.v_norm = v_norm;
        sol.max_height = max_height;
        sol.t_impact = t_impact;
        sol.v_aim = v_aim;
        
        return sol;
    }

}
