package dytu.game;

import city.cs.engine.StepEvent;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;
import java.lang.Math;
import dytu.world.*;

public class StepListener implements city.cs.engine.StepListener {
    //declare variables
    private GameView view;
    private Hero hero;
    private Covid covid;
    private WorldBuilder world;
    private int projCount = 0;


    public StepListener(GameView view, Hero hero, Covid covid, WorldBuilder world) {
        this.view = view;
        this.hero = hero;
        this.covid = covid;
        this.world = world;
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
        if (hero.getHealth() <= 0){
            //Hero.setHealth(0);
            hero.destroy();
        } if (covid.getInfectiousness() <= 0){
            covid.destroy();
        }
        for(int i=0; i<world.getCovidList().size()-1; i++){
            testCovid = (Covid) world.getCovidList().get(i);
            double xLen = testCovid.getPosition().x - hero.getPosition().x;
            double yLen = testCovid.getPosition().y - hero.getPosition().y;
            if(Math.sqrt((xLen*xLen)+(yLen*yLen)) < 10 && (int) e.getStep() % 100 == 0 && projCount <= 1){
                System.out.println("within firing range");
                //double theta = Math.atan2(yLen, xLen);
                CovidProjectile covProj = new CovidProjectile(this.world, (int) (e.getStep() % 2));
                covProj.setPosition(new Vec2(testCovid.getPosition().x + 3, testCovid.getPosition().y));
                System.out.println("Created projectile at: "+covProj.getPosition());
                covProj.applyForce(new Vec2((float)yLen, (float)xLen));
                ++projCount;

            }

        }


    }


}
