package dytu.game;

import city.cs.engine.*;
import city.cs.engine.Shape;

import java.awt.*;

public class Covid extends DynamicBody {
    private int infectiousness = 50;
    private int selector;

    //initialise the covid shape and image
    private static float rad = 2f;
    private static final Shape covidShape = new CircleShape(rad);
    //private BodyImage covidImage = new BodyImage("Data/covid"+this.selector+".png", 2*rad);

    //get infectiousness of the object
    public int getInfectiousness() {
        return infectiousness;
    }
    public float getRadius(){return rad;}
    public int getRadiusInt(){return (int) rad;}


    //allow change of size of the shape as its health/infectiousness decreases
    public static void setRad(float rad) {
        Covid.rad = rad;
    }

    public Covid(World w, int mutator, int selector) {
        super(w, covidShape);
        System.out.println(this.selector);
        this.selector = selector;
        System.out.println(this.selector);
        BodyImage covidImage = new BodyImage("Data/covid"+this.selector+".png", 2*rad);
        this.infectiousness = infectiousness * mutator;
        System.out.println(this.infectiousness);
        addImage(covidImage);
    }



}
