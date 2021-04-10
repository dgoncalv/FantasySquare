package Model.Sort;


import Model.Game.Entity;

import java.util.Comparator;

public class SortByAgility implements Comparator<Entity> {
    @Override
    public int compare(Entity a, Entity b) {
        return b.getAgi() - a.getAgi();
    }
}
