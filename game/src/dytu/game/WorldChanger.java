package dytu.game;

import city.cs.engine.DebugViewer;
import city.cs.engine.DynamicBody;
import city.cs.engine.World;
import dytu.world.Level_One;
import dytu.world.Level_Two;
import dytu.world.WorldBuilder;

import javax.swing.*;

public class WorldChanger {
    private WorldBuilder world;
    //private GameView view;

    public WorldBuilder levelSelect(int selection){
        switch(selection){
            case (0) -> {
                if (world != null){
                    for(Covid body: world.getCovidList()){
                        body.destroy();
                        System.out.println("Covid body destroyed");
                    }
                    world.getCovidList().clear();
                    world.stop();
                    world = new Level_One();
                } else{
                    world = new Level_One();
                }
                world.start();
                return world;
            }
            case (1) -> {
                if (world != null){
                    world.getCovidList().clear();
                    world.stop();
                    world = new Level_Two();
                }else{
                    world = new Level_Two();
                }
                world.start();
                return world;
            }
        }
        return null;
    }

    public WorldChanger(WorldBuilder world/*, GameView view*/){
        this.world = world;
        //this.view = view;

    }

}
