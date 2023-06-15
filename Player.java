import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    int moveSpeed = 6;
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
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
       move(moveSpeed);
       if(isAtEdge()){
           turn(Greenfoot.getRandomNumber(180)+90);
       }
       if(Greenfoot.getRandomNumber(1000)>995){
           turn(Greenfoot.getRandomNumber(180)+180);
       }
       
    }
}
