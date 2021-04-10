package Model.Exploration;

import Model.Utility.Vector2D;

public class WorldNode {
    protected String sprite;
    protected Vector2D coordinate;

    public WorldNode(String sprite)
    {
        this.sprite = sprite;
        coordinate = null;
    }

    public void setCoordinate(Vector2D coordinate) {
        this.coordinate = coordinate;
    }

    public Vector2D getCoordinate() {
        return coordinate;
    }

    public String getSprite() {
        return sprite;
    }
}
