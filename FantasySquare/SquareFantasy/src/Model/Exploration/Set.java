package Model.Exploration;

import javafx.scene.paint.Color;

//Enumération représentant les décors des maps |TEMPORAIRE|
public enum Set {
    GRASS(Color.LAWNGREEN), SAFEGRASS(Color.LAWNGREEN), GOAL(Color.TURQUOISE), FOREST(Color.FORESTGREEN), DESERT(Color.GREENYELLOW), DUNGEONFLOOR(Color.RED), DUNGEONWALL(Color.DARKRED) , WATER(Color.BLUE), ROAD(Color.GREY), NONE(Color.WHITE);

    private Color color;

    private Set(Color color)
    {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public static Set parseString(String set)
    {
        if(set.equals("GRASS"))
        {
            return Set.GRASS;
        }
        else if(set.equals("SAFEGRASS"))
        {
            return Set.SAFEGRASS;
        }
        else if(set.equals("FOREST"))
        {
            return Set.FOREST;
        }
        else if(set.equals("DESERT"))
        {
            return Set.DESERT;
        }
        else if(set.equals("DUNGEONFLOOR"))
        {
            return Set.DUNGEONFLOOR;
        }
        else if(set.equals("DUNGEONWALL"))
        {
            return Set.DUNGEONWALL;
        }
        else if(set.equals("WATER"))
        {
            return Set.WATER;
        }
        else if(set.equals("ROAD"))
        {
            return Set.ROAD;
        }
        else if(set == "NONE")
        {
            return Set.ROAD;
        }
        return null;
    }
}
