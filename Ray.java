import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Ray has
 * direction
 * travel speed
 * range
 * classes if gets absorbed by 
 * classes it reports
 * 
 * Ray does
 * removes itself
 * reports to Monster event, Actor , x , y
 *  
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ray extends Actor
{
    Vision vision;
    int speed;
    int range;
    int countDown;
    int startX;
    int startY;
    int endX; 
    int endY;
    GreenfootImage img;
    VisionEvent event;
    
    public Ray(Vision v){
        vision = v;
        speed = v.speed;
        range = v.range;
        startX = v.getX();
        startY = v.getY();
        //getImage().scale(200,200);
        countDown = 1200;
        ArrayList<Ray> wave = vision.newRays;
        wave.add(this);
        
        getImage().setTransparency(0);
    }
    
    /**
     * Act - do whatever the Rays wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //System.out.println("ray");
        move(speed);
        countDown--;
        if(countDown<1){
            event = getEvent();
            return; 
        }
        
        if(distance(getX(), getY(),startX, startY)>range){
            event = getEvent();
            return;
        }
        if(isAtEdge()){
            setEvent("edge");
            getWorld().removeObject(this);
            return;
        }
        if(isTouching(Player.class)){
            
            Player player = (Player)getOneIntersectingObject(Player.class);
            setEvent("player");
            getWorld().removeObject(this);
            return;
        }
        else if(isTouching(Obstacle.class)){
            Obstacle obstacle = (Obstacle)getOneIntersectingObject(Obstacle.class);
            setEvent("obstacle");
            getWorld().removeObject(this);
            return;
        }
    }
    
    //use this when it has not hit anything
    VisionEvent getEvent(){
        
        if(event == null){
            event =  new VisionEvent("none", this);    
        }
        if(getWorld()!=null){
            getWorld().removeObject(this);    
        }
        return event;
    }
    
    void setEvent(String s){
        event =  new VisionEvent(s, this);
    }
    
    double distance(int x1, int y1, int x2, int y2){
        double dx = x1-x2;
        double dy = y1-y2;
        return Math.sqrt(dx*dx+dy*dy);
    }
    
}
