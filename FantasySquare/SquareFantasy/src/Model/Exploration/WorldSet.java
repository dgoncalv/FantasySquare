package Model.Exploration;

import Model.Utility.Vector2D;

import java.util.ArrayList;

public class WorldSet {
    private World world;
    private ArrayList<ArrayList<Set>> map;
    private Set biome;

    public WorldSet(World world)
    {
        this.world = world;

        map = new ArrayList<>();

        for(int i = 0; i < world.getSize(); i++)
        {
            map.add(new ArrayList<>());
            for(int j = 0; j < world.getSize(); j++)
            {
                map.get(i).add(Set.NONE);
            }
        }
    }

    //Remplit la map du décors spécifier
    public void setMap(Set biome) {
        this.biome = biome;
        for(int i = 0; i < world.getSize(); i++)
        {
            for(int j = 0; j < world.getSize(); j++)
            {
                setCell(biome, new Vector2D(i, j));
            }
        }
    }

    //Remplit la case spécifier du décors spécifier
    public void setCell(Set set, Vector2D coordinate)
    {
        map.get(coordinate.getX()).set(coordinate.getY(), set);
    }

    public Set getCell(Vector2D coordinate)
    {
        Vector2D mapCoordinate = coordinate.reverseVector();

        return map.get(mapCoordinate.getX()).get(mapCoordinate.getY());
    }
    
    public Set getBiome()
    {
        return biome;
    }
}
