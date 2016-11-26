import java.io.PrintWriter;

public class Flyout
{
    public Trajectory trajectory = new Trajectory();
    
    public Flyout()
    {
        
    }
    
    void setPosition(double x, double y)
    {
        trajectory.s = new Vec2d(x, y);
    }
    
    void setVelocity(double vx, double vy)
    {
        trajectory.v = new Vec2d(vx, vy);
    }
    
    void setAngleDegrees(double theta)
    {
        trajectory.setAngleDegrees(theta);
    }
    
    // t_end is the end time
    void writeTrajectory(double t_end)
    {
        try{
            PrintWriter writer = new PrintWriter("trajectory.csv", "UTF-8");
            
            double t = 0.0;
            while (t < t_end)
            {
                Vec2d s = trajectory.position(t);
                
                writer.println(t + ", " + s.x + ", " + s.y);
                
                t += 0.001;
            }
            
            writer.close();
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }
    
    void writeTrajectory(Vec2d target)
    {
        Solution sol = trajectory.intercept(target);
        trajectory.v = sol.v_aim;
        writeTrajectory(sol.t_impact);
    }
    
    void writeMaxHeightVsDistance()
    {
        try{
            PrintWriter writer = new PrintWriter("height_vs_dist.csv", "UTF-8");
            
            double x = 100.0;
            while (x < 1524.0) // 5 ft
            {
                try
                {
                    Solution sol = trajectory.intercept(new Vec2d(x, 861.06)); // 33.9 in height
                    
                    writer.println(x + ", " + sol.max_height);
                }
                catch (Exception e)
                {
                
                }
                
                x += 100.0;
            }
            
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
