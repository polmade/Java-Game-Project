package dytu.game;

import city.cs.engine.StepEvent;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

import dytu.world.*;

public class StepListener implements city.cs.engine.StepListener {
    //declare variables
    private GameView view;
    private Hero hero;
    private Covid covid;
    private WorldBuilder world;
    private int projCount = 0;
    private static List<StepListener> listenerCount = new ArrayList<>();


    //getter and setters for the List of step listeners
    public static List<StepListener> getListenerCount() {
        return listenerCount;
    }

    public static void setListenerCount() {
        StepListener.listenerCount.clear();
    }

    public StepListener(GameView view, Hero hero, Covid covid, WorldBuilder world) {
        this.view = view;
        this.hero = hero;
        this.covid = covid;
        this.world = world;
        listenerCount.add(0, this);
    }


    @Override
    public void preStep(StepEvent e) {
    }
    @Override
    public void postStep(StepEvent e) {
        Covid testCovid;
        //set view centre for the world, based on position of hero character and if it should be looking forward
        if(hero.isLookingForward() == false){
            this.view.setCentre(new Vec2(hero.getPosition().x,(hero.getPosition().y)+8f));
        } else{
            this.view.setCentre(new Vec2(hero.getPosition().x+10,(hero.getPosition().y)+8f));
        }
        //System.out.println(this.hero.getPosition().y);
        //check health of both character and covid instances, and if <=0 destroy them
        if (covid.getInfectiousness() <= 0){
            covid.destroy();
        }
        for(int i=0; i<world.getCovidList().size()-1; i++){
            testCovid = (Covid) world.getCovidList().get(i);
            double xLen = testCovid.getPosition().x - hero.getPosition().x;
            double yLen = testCovid.getPosition().y - hero.getPosition().y;
            if(Math.sqrt((xLen*xLen)+(yLen*yLen)) < 15 && (int) e.getStep() % 100 == 0){
                if(projCount < 1){
                    System.out.println("within firing range");
                    System.out.println(hero.getPosition());
                    System.out.println(xLen);
                    //double theta = Math.atan2(yLen, xLen);
                    CovidProjectile covProj = new CovidProjectile(this.world, (int) (e.getStep() % 2));
                    covProj.setCreationTime(this.world.getSimulationSettings().getFrameCount());
                    covProj.addCollisionListener(new CovidProjectileCollision(covProj));
                    if(xLen >= 0) {
                        System.out.println(xLen);
                        covProj.setPosition(new Vec2(testCovid.getPosition().x - 5, testCovid.getPosition().y + 1));
                        if(hero.getPosition().x < 0){
                            covProj.setLinearVelocity(new Vec2(this.hero.getPosition().x, this.hero.getPosition().y));
                        }else if(hero.getPosition().x > 0){
                            covProj.setLinearVelocity(new Vec2(-(this.hero.getPosition().x), this.hero.getPosition().y));
                        }
                    } else {
                        covProj.setPosition(new Vec2(testCovid.getPosition().x + 3, testCovid.getPosition().y + 1));
                        if(hero.getPosition().x < 0){
                            covProj.setLinearVelocity(new Vec2(this.hero.getPosition().x, this.hero.getPosition().y));
                        }else if(hero.getPosition().x > 0){
                            covProj.setLinearVelocity(new Vec2(this.hero.getPosition().x, this.hero.getPosition().y));
                        }
                    }


                    System.out.println("Created projectile at: "+covProj.getPosition());

                    System.out.println(covProj.getLinearVelocity());
                    System.out.println(covProj.getPosition());
                }
                ++projCount;
                if(projCount >= 100){
                    projCount = 0;
                }
            }

        }


    }


}
