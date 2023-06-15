import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

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
    final boolean DEBUG = false;
    protected Monster monster;
    int hearingRange = 60;
    int range = 100;
    int speed = 10;
    int spread = 120;//for some reason this is the number used for vision, not the Monster value
    int updateFreq = 2;
    int visionFreq = 4;
    int frameCount = 0;
    int deltaAngle = 15;
    int numRays = spread/deltaAngle;
    double avoidX = 0;
    double avoidY = 0;
    ArrayList<ArrayList> waves = new ArrayList<ArrayList>();
    ArrayList<Ray> currentRays = new ArrayList<Ray>();
    ArrayList<Ray> newRays = new ArrayList<Ray>();
    Color seen = new Color(255, 255,0);
    Color looking = new Color(0, 100, 150);
    
    public Vision(Monster m){
        monster = m;
        this.range = monster.vRange;
        this.speed = monster.vSpeed;
        this.spread = monster.vSpread;
        if(!DEBUG){
            getImage().setTransparency(0);
        }
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
        if(frameCount%visionFreq == 0){
            look(); 
            hear();
        }
        
    }
    

    void look(){
        
        newRays = new ArrayList<Ray>();
        if (waves.size()>1){
            currentRays = waves.get(0);
            for(int i = 0; i < currentRays.size();i++){
                
                //report the results from the currentRays
                Ray tempRay = currentRays.get(i);
                VisionEvent tempEvent = tempRay.getEvent();
                //check to see if we saw a player.
                if(tempEvent.owner.equals("player")){
                    monster.report(tempEvent);
                    
                }
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
        Ray temp = new Ray(this);
        getWorld().addObject(temp, getX(), getY());
        temp.setRotation(getRotation());
        temp.move(speed);
        newRays.add(temp);
        for(int i = 1; i < numRays/2;i++){
            temp = new Ray(this);
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
    
    void hear(){
        List<Player> players = getObjectsInRange(hearingRange, Player.class);
        
        if (players.size()>0){
            monster.report(new VisionEvent(players.get(0).getX(),players.get(0).getY()));     
            //System.out.println("heard player!");
        }
    }
    
    
}

