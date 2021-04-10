package Model.Sort;


import Model.Game.Entity;

import java.util.Comparator;

public class SortByHp implements Comparator<Entity> {
    @Override
    public int compare(Entity a, Entity b) {
        return a.getHp() - b.getHp();
    }
}
