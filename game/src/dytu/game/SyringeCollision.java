package dytu.game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class SyringeCollision implements CollisionListener {
    private Syringe syringe;
    private Covid covid;
    public SyringeCollision(Syringe syringe){
        this.syringe = syringe;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof Covid && syringe.getCollisionWithCovid()){
            syringe.destroy();
        }
    }
}
