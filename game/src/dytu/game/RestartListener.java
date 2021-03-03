package dytu.game;

import dytu.world.WorldBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RestartListener implements ActionListener {
    private WorldBuilder world;
    private GameView view;
    private int levelNo;

    //initialise level selector & view selector class
    private WorldChanger wc = new WorldChanger((WorldBuilder) world);
    private ViewChanger vc = new ViewChanger((WorldBuilder) world);

    public RestartListener(WorldBuilder world, GameView view){
        this.world = world;
        this.view = view;
        this.levelNo = this.world.getLevelNo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*world.getHero().setPosition(new Vec2(-8, -8));
                view.setCentre(world.getHero().getPosition());

                 */

        world = wc.levelSelect(1);
        vc.setWorld(world);
        vc.viewSelect(1);
    }
}
