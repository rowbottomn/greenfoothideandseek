import greenfoot.*;

/**
 * Write a description of class VisionEvent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VisionEvent  
{
    String owner;
    int x;
    int y;
    int px; 
    int py;
    double distance;
    /**
     * Constructor for objects of class VisionEvent
     */
    public VisionEvent(Actor a, Ray r)
    {
        this.owner = getActorClass(a);
        this.x = r.getX(); 
        this.y = r.getY();
        this.px = r.startX;
        this.py = r.startY;
        distance = r.distance(x, y, px, py);
    }
    
    public VisionEvent(String _owner,Ray r )
    {
        this.owner = _owner;
        this.x = r.getX(); 
        this.y = r.getY();
        this.px = r.startX;
        this.py = r.startY;
        distance = r.distance(x, y, px, py);
    }
    
    public VisionEvent(int _x, int _y){
        this.owner = "player";
        this.x = _x; 
        this.y = _y;
    }
    
    private String getActorClass(Actor a){
        if(a instanceof Obstacle){
            return "obstacle";
        }
        else if(a instanceof Player){
            return "player";
        }
        return "other";
    }

}
