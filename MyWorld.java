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
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        
        Monster monster = new Monster();
        addObject(monster, 100, 300);
        
        //Player player = new Player();
        //addObject(player, 650, 300);
        Obstacle obstacle = new Obstacle();
        addObject(obstacle, 650, 350);
        
    }
    
    public void act(){

    }
}
