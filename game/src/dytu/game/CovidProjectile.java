package dytu.game;

import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.World;
import dytu.world.WorldBuilder;

public class CovidProjectile extends DynamicBody {
    /*
    Empty class created for simplicity
    might be slightly redundant
     */
    private World world;
    private int mutator;
    //private int selector;
    //private static Covid covid;

    private static final Shape projectileShape = new CircleShape(0.7f);


    //get parent covid
    //public static Covid getCovid() {
        //return covid;
  //  }

    public CovidProjectile(WorldBuilder w, int mutator) {
        super(w, projectileShape);
        this.world = w;
        this.mutator = mutator;

    }
}
