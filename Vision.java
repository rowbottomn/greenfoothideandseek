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
    int range = 100;
    int speed = 10;
    int spread = 90;
    int updateFreq = 2;
    int visionFreq = 4;
    int frameCount = 0;
    ArrayList<ArrayList> waves = new ArrayList<ArrayList>();
    ArrayList<Ray> currentRays = new ArrayList<Ray>();
    ArrayList<Ray> newRays = new ArrayList<Ray>();
    
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
    
    int deltaAngle = 10;
    int numRays = spread/deltaAngle;
    double avoidX = 0;
    double avoidY = 0;
    void look(){
        
        newRays = new ArrayList<Ray>();
        if (waves.size()>1){
            currentRays = waves.get(0);
            for(int i = 0; i < currentRays.size();i++){
                //report the results from the currentRays
                Ray tempRay = currentRays.get(i);
                VisionEvent tempEvent = tempRay.getEvent();
                
                avoidX += (tempEvent.x-tempEvent.px)*tempEvent.distance;
                
                avoidY += (tempEvent.y-tempEvent.py)*tempEvent.distance;
                
            }
            waves.remove(currentRays);
            
            avoidX /= numRays;
            avoidY /= numRays;
            
            if((avoidX*avoidX + avoidY*avoidY)<200){
                monster.turn(Greenfoot.getRandomNumber(90)+180);
                monster.move(1);
                ArrayList<ArrayList> waves = new ArrayList<ArrayList>();
            }
            else{
                monster.turnTowards((int)(monster.getExactX()+avoidX),(int)(monster.getExactY()+ avoidY));
            }
        }
        
        for(int i = 1; i < numRays/2;i++){
            Ray temp = new Ray(this);
            getWorld().addObject(temp, getX(), getY());
            temp.setRotation(getRotation()+i*deltaAngle);
            temp.move(speed);
            newRays.add(temp);
            temp = new Ray(this);
            getWorld().addObject(temp, getX(), getY());
            temp.setRotation(getRotation()-i*deltaAngle);
            temp.move(speed);
            newRays.add(temp);
            //System.out.println("hey");
        }
        /*if(currentAngle>spread/2){
            deltaAngle *= -1;
        }*/

        waves.add(newRays);
        
    }
    
    
}

