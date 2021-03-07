package dytu.game;

import city.cs.engine.*;
import city.cs.engine.DynamicBody;
import dytu.world.WorldBuilder;

import static java.lang.Math.ceil;

public class CovidProjectile extends DynamicBody {
    /*
    projectile for Covid
     */
    private  World world;
    private int mutator;
    private static float rad = 0.7f;
    private long creationTime;

    //image for Covid Projectile
    private BodyImage projectileImage = new BodyImage("./Data/covProj.png", 2.5f);

    //getters and setters
    public void setCreationTime(long creationFrame) {
        this.creationTime = creationFrame;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public static float getRad() {
        return rad;
    }

    public static int getRadCeiling() {
        return (int) ceil(rad);
    }

    //create the shape for the projectile
    private static final Shape projectileShape = new CircleShape(rad);


    public CovidProjectile(WorldBuilder w, int mutator) {
        super(w, projectileShape);
        addImage(projectileImage);
        //setAlwaysOutline(true);
        Sensor projectileSensor = new Sensor(this, projectileShape);
        this.world = w;
        this.mutator = mutator;

    }
}
