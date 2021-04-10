package Model.Exploration;

import Model.Utility.Vector2D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class World {
    private static HashSet<World> worlds;

    private String name;
    private int size;
    private ArrayList<ArrayList<WorldNode>> map;
    //Carte représentant le background
    private WorldSet backgroundMap;

    public World(String name, int size)
    {
        this.name = name;
        this.size = size;
        map = new ArrayList<>();

        for(int i = 0; i < size; i++)
        {
            map.add(new ArrayList<>());
            for(int j = 0; j < size; j++)
            {
                map.get(i).add(null);
            }
        }

        backgroundMap = new WorldSet(this);
        addWorld(this);
    }

    private static void addWorld(World world)
    {
        if(worlds == null)
        {
            worlds = new HashSet<>();
        }
        worlds.add(world);
    }

    public static World getWorld(String worldName)
    {
        Iterator it = worlds.iterator();
        while(it.hasNext())
        {
            World temp = (World) it.next();
            if(temp.getName().equals(worldName))
            {
                return temp;
            }
        }
        return null;
    }

    //Affectation d'une coordonées à un node
    public void placeNode(WorldNode node, Vector2D coordinate)
    {
        //Inversion du vecteur pour cohérence avec l'affichage graphique
        Vector2D mapCoordinate = coordinate.reverseVector();

        //Si l'emplacement n'est pas déja prit
        if(map.get(mapCoordinate.getX()).get(mapCoordinate.getY()) == null)
        {
            map.get(mapCoordinate.getX()).set(mapCoordinate.getY(), node);
            node.setCoordinate(coordinate);
        }
    }

    //Suppression du Node aux coordonnées spécifier
    public void eraseNode(Vector2D coordinate)
    {
        //Inversion du vecteur pour cohérence avec l'affichage graphique
        Vector2D mapCoordinate = coordinate.reverseVector();

        map.get(mapCoordinate.getX()).get(mapCoordinate.getY()).setCoordinate(null);

        map.get(mapCoordinate.getX()).set(mapCoordinate.getY(), null) ;
    }

    //Si le nom de deux carte sont les mêmes, alors elles sont égales
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        World world = (World) o;
        return Objects.equals(name, world.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    //Remplit le map Background du décors spécifier
    public void setBiomeBackgroundMap(Set set)
    {
        backgroundMap.setMap(set);
    }

    //Remplit la case aux coordonées spécifier du décors spécifier
    public void setCellBackgroundMap(Set set, Vector2D coordinate)
    {
        backgroundMap.setCell(set, coordinate);
    }

    //Remplit la case aux coordonées spécifier du décors spécifier
    public Set getCellBackgroundMap(Vector2D coordinate)
    {
        return backgroundMap.getCell(coordinate);
    }

    //Retourne le node présent aux coordonée spécifier
    public WorldNode getNode(Vector2D coordinate)
    {
        Vector2D mapCoordinate = coordinate.reverseVector();

        return map.get(mapCoordinate.getX()).get(mapCoordinate.getY());
    }
    
    public Set getBackgroundBiome()
    {
        return backgroundMap.getBiome();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
