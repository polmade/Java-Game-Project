package dytu.game;

import city.cs.engine.*;
import city.cs.engine.DynamicBody;
import dytu.world.WorldBuilder;

import static java.lang.Math.ceil;

public class CovidProjectile extends DynamicBody {
    /*
    Empty class created for simplicity
    might be slightly redundant
     */
    private  World world;
    private int mutator;
    private static float rad = 0.7f;
    private long creationTime;

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


    private static final Shape projectileShape = new CircleShape(rad);


    public CovidProjectile(WorldBuilder w, int mutator) {
        super(w, projectileShape);
        Sensor projectileSensor = new Sensor(this, projectileShape);
        this.world = w;
        this.mutator = mutator;

    }
}
