package dytu.game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;

public class SyringeCollision implements CollisionListener {
    private Syringe syringe;
    private Covid covid;
    public SyringeCollision(Syringe syringe){
        this.syringe = syringe;
    }

    //checks if, upon collision with a static body, the syringe is too old, and, if true, removes the syringe
    //also checks if it has collided with a covid projectile, and if so, removes the projectile, and increments the players score by 3
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof StaticBody){
            if(syringe.getWorld().getSimulationSettings().getFrameCount() - syringe.getCreationTime() >= 10){
                syringe.destroy();
            }
        }
        //checks for collision with covidProjectiles, then updates the characters score
        if(collisionEvent.getOtherBody() instanceof CovidProjectile){
            syringe.getWorld().getHero().setPoints(3);
            collisionEvent.getOtherBody().destroy();
        }
    }
}
