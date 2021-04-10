package Model.Exploration;

import Model.Utility.Vector2D;

public class GateNode extends WorldNode {
    private World mapToLoad;
    //Vecteur représentant ou le joueur va apparaitre
    private Vector2D spawn;

    public GateNode(String sprite, Vector2D spawn)
    {
        super(sprite);
        this.mapToLoad = null;
        this.spawn = spawn;
    }

    public void setMapToLoad(World mapToLoad) {
        this.mapToLoad = mapToLoad;
    }

    public World getMapToLoad() {
        return mapToLoad;
    }

    public Vector2D getSpawn() {
        return spawn;
    }
}
