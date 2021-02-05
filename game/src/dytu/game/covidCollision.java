package dytu.game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class covidCollision implements CollisionListener {
    private covid Covid;

    public covidCollision(covid Covid){
        this.Covid = Covid;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof hero){
            System.out.println("Collision detected with: "+collisionEvent.getOtherBody());
        }
    }
}
