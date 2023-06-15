import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Monster here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Monster extends SmoothMover
{
     int moveSpeed = 3;

    private MouseInfo mouse;

    Player player;
    
    public int vRange = 300;
    public int vSpeed = 60;
    public int vSpread = 120;//for some reason this is NOT the number used for vision
    
    Vision vision;
    VisionEvent sighting;
    int playerX = -9999;
    int playerY = -9999;
    double playerDistance;
    //int closestObjectX,closestObjectY; 
    //int closestObject = 10000;
    
    
    
    public void report(VisionEvent sighting){
        if(sighting.owner.equals("player")){
           playerX = sighting.x;
           playerY = sighting.y;
           if(vision.DEBUG){
               vision.getImage().setColor(vision.seen);
               vision.getImage().fill();
            }
           //System.out.println("Saw player!");
        }
    /*    else if(sighting.owner.equals("obstacle")){
            if(findDistance(sighting.x, sighting.y, getX(), getY())<closestObject){
                closestObjectX = sighting.x;
                closestObjectY = sighting.y;
                System.out.println("Saw player!");
            }
            
        }*/
    }
    public void reportPlayer(){}
    
    public void reportObject(){}

    private double calcDisp(double v1, double v2){
        return v2 - v1;
    }

    private double findDistance (double x1, double y1, double x2, double y2 ){
        double dx = calcDisp(x1, x2);
        double dy = calcDisp(y1, y2);
        return Math.sqrt(dx*dx*dy*dy);
    }

    private double findManhattan (double x1, double y1, double x2, double y2 ){
        double dx = calcDisp(x1, x2);
        double dy = calcDisp(x1, x2);
        return Math.abs(dx) + Math.abs(dy);
    }

    public void moveTo(int targetX, int targetY){
        double x = getX();
        double y = getY();

        double dx = calcDisp(x, targetX);
        double dy = calcDisp(y, targetY);
        if(player != null){
          /*  if (player.isHideing == false){
                turnTowards( targetX, targetY);
                move(moveSpeed);
            } else { move(moveSpeed);}*/
        }
        
        // it is unkonwn to me if I should keep this code {{
            
        // if (Math.abs(dx) > Math.abs(dy)){
        // if (dx < 0 ){
        // //setRotation(180);
        // move(moveSpeed);
        // //return BattleBotArena.LEFT;
        // } else{
        // //setRotation(0);
        // move(moveSpeed);
        // //return BattleBotArena.RIGHT;
        // }
        // }
        // else{

        // if (dy > 0 ){
        // //setRotation(90);
        // move(moveSpeed);
        // //return BattleBotArena.UP;
        // } else {
        // //setRotation(-90);
        // move(moveSpeed);
        // //return BattleBotArena.DOWN;
        // }
        // }

        // }}
    }

    
    /**
     * Act - do whatever the Monster wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(  )
    {
        if(vision == null){
            vision = new Vision(this);
            getWorld().addObject(vision, getX(), getY());
            playerX = getX();
            playerY = getY();
        }
        
        if(playerX != -9999){
            
            turnTowards(playerX, playerY);
            move(2*moveSpeed);
            playerDistance = findDistance((double)playerX, (double)playerY, (double)getX(), (double)getY());
            if(playerDistance > vRange || playerDistance < 10){
                playerX = -9999;
                if(vision.DEBUG){         
                    vision.getImage().setColor(vision.looking);
                    vision.getImage().fill();
                }
            }
        }
        
        
        // Add your action code here.
        move(moveSpeed);
    }
}