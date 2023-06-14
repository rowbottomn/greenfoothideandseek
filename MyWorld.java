import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    int frameCount = 0;
    int numObstacles = 10;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 700, 1); 
        
        Monster monster = new Monster();
        addObject(monster, 100, 300);
        addObject(new Monster(), 400, 300);
        //Player player = new Player();
        //addObject(player, 650, 300);
        for (int i = 0; i < numObstacles; i++){
            addObject(new Obstacle(), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(600));     
        }
       
       
        
        
    }
    
    public void act(){

    }
}
