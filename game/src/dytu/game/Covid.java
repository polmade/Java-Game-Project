package dytu.game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import dytu.world.WorldBuilder;

public class Covid extends DynamicBody {
    private int infectiousness = 50;
    private int selector;
    private WorldBuilder world;

    //initialise the covid shape and image
    private static float rad = 2f;
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

    public float getRadius(){return rad;}
    public int getRadiusInt(){return (int) rad;}

    public WorldBuilder getWorld(){
        return world;
    }


    //allow change of size of the shape as its health/infectiousness decreases
    public static void setRad(float rad) {
        Covid.rad = rad;
    }

    public Covid(WorldBuilder w, int mutator, int selector) {
        super(w, covidShape);
        this.world = w;
        System.out.println(this.selector);
        this.selector = selector;
        System.out.println(this.selector);
        BodyImage covidImage = new BodyImage("Data/covid"+this.selector+".png", 2*rad);
        this.infectiousness = infectiousness * mutator;
        System.out.println("Infectiousness: "+this.infectiousness);
        addImage(covidImage);
    }



}
