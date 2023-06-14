import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Vision has
 * Monster
 * targetX
 * targetY
 * range of sight
 * angle of sight
 * arraylist of vision bullets
 * 
 * Vision does
 * look
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vision extends Actor
{
    protected Monster monster;
    int range = 500;
    int speed = 120;
    int spread = 90;
    int updateFreq = 1;
    int visionFreq = 40;
    int frameCount = 0;
    ArrayList<Ray> currentRays = new ArrayList<Ray>();
    ArrayList<Ray> newRays = new ArrayList<Ray>();
    int avoidanceX = 0;
    int avoidanceY = 0;
    
    public Vision(Monster m){
        monster = m;
        this.range = monster.vRange;
        this.speed = monster.vSpeed;
        this.spread = monster.vSpread;
    }
    
    /**
     * Act - do whatever the vision wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        frameCount++;
        if(frameCount%updateFreq == 0){
            setLocation(monster.getX(), monster.getY());
            setRotation(monster.getRotation());
        }
        if(frameCount%visionFreq ==0){
            look(); 
        }
        
    }
    
    int deltaAngle = 5;
    int numRays = spread/deltaAngle;
    int avoidX = 0;
    int avoidY = 0;
    void look(){
        
        newRays = new ArrayList<Ray>();
        for(int i = 0; i < currentRays.size();i++){
            //report the results from the currentRays
            Ray tempRay = currentRays.get(i);
            VisionEvent tempEvent = tempRay.getEvent();
            
            avoidX += (tempEvent.x-tempEvent.px)*2;
            avoidY += (tempEvent.y-tempEvent.py)*2;
        }
        for(int i = 0; i < numRays;i++){
            Ray temp = new Ray(this);
            getWorld().addObject(temp, getX(), getY());
            temp.setRotation(getRotation()+(-numRays/2+i)*deltaAngle);
            temp.move(speed);
            newRays.add(temp);
            //System.out.println("hey");
        }
        /*if(currentAngle>spread/2){
            deltaAngle *= -1;
        }*/
        avoidX /= numRays;
        avoidY /= numRays;
        monster.turnTowards(monster.getX()+avoidX,monster.getY()+ avoidY);
        currentRays = newRays;
    }
    
    
}

