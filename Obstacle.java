import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Obstacle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Obstacle extends Actor
{

    
    public Obstacle(){
        GreenfootImage img = getImage();
        img.fillRect(0,0, img.getWidth(),img.getHeight() );
    }
    
    /**
     * Act - do whatever the Obstacle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        /*
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse==null){
            return;
        }
        setLocation(mouse.getX(), mouse.getY());
        */
       Player player = (Player)getOneIntersectingObject(Player.class);
       if(player!=null){
         player.move(-player.moveSpeed);       
       }
       List<Monster> monsters = getIntersectingObjects(Monster.class);
       for (Monster monster: monsters){
           monster.move(-monster.moveSpeed*1.1); 
           monster.turn(Greenfoot.getRandomNumber(180)-90);
           monster.vision.waves.clear();
       }
       
    }
}
