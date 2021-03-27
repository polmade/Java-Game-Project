package dytu.game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.DestructionEvent;
import city.cs.engine.StaticBody;

public class CovidProjectileCollision implements CollisionListener {
    private CovidProjectile covProj;

    public CovidProjectileCollision(CovidProjectile covProj){
        this.covProj = covProj;
    }

    /*
    defines what happens upon collision with hero objects, and instances of StaticBody
    StaticBody check is used to remove them from the world once they have touched a static body, or are too old
     */
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof Hero){
            System.out.println("Collision detected with: "+collisionEvent.getOtherBody());
            covProj.destroy();
        } if(collisionEvent.getOtherBody() instanceof StaticBody){
            if(covProj.getWorld().getSimulationSettings().getFrameCount() - covProj.getCreationTime() >= 10){
                covProj.destroy();
            }
        }
    }
}
