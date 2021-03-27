package dytu.game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import dytu.world.WorldBuilder;

public class Covid extends DynamicBody {
    //int infectiouness acts as the covid instances health
    private int infectiousness = 50;
    //int selector allows the choice between different covid images, will be implemented into a slightly more random process
    private int selector;
    //tells you what world the instance is in
    private WorldBuilder world;

    //too destory covid
    private int index;

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    //initialise the covid shape and image
    private static float rad = 2.0f;
    private static final Shape covidShape = new CircleShape(rad);
    //private BodyImage covidImage = new BodyImage("Data/covid"+this.selector+".png", 2*rad);

    //get infectiousness of the object
    public int getInfectiousness() {
        return infectiousness;
    }

    //implement a health system for the covid class
    public void setInfectiousness(int damage){
        this.infectiousness = this.infectiousness - damage;
    }

    /*
    allows you to get the radius of the covid instance
    there are two methods, a float method and an int method
    these are both in place to allow for points system
    the getRadiusInt is used in the HeroCollision class, to allow for the hero's health to be changed based on the radius of the instance
     */
    public float getRadius(){return rad;}
    public int getRadiusInt(){return (int) rad;}

    //get the world that the instance is in
    public WorldBuilder getWorld(){
        return world;
    }

    //allow change of size of the shape as its health/infectiousness decreases
    public static void setRad(float rad) {
        Covid.rad = rad;
    }

    //constructor method for enemy
    public Covid(WorldBuilder w, int mutator, int selector) {
        super(w, covidShape);
        this.world = w;
        this.selector = selector;
        System.out.println(this.selector);
        BodyImage covidImage = new BodyImage("Data/covid"+this.selector+".png", 2*rad);
        /*allows for some randomness in the health of each different covid instance
        mutator could also be used to change the size of rad upon creation, to create some difference in instances
         */
        this.infectiousness = infectiousness * mutator;
        System.out.println("Infectiousness: "+this.infectiousness);
        addImage(covidImage);
    }



}
