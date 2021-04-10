package Model.Misc;

import java.util.HashMap;
import java.util.HashSet;

public class Consumable extends Item{
    private HashMap<Statistic, Integer> effect;
    private static HashSet<Consumable> consumableList = new HashSet<>();

    public Consumable(String name, String description, int value, Statistic stat, int effectValue)
    {
        super(name, description, value);
        effect = new HashMap<>();
        effect.put(stat, effectValue);
    }

    public static void addConsumable(Consumable consumable)
    {
        consumableList.add(consumable);
    }

    public static Consumable getConsumable(String name)
    {
        for(Consumable consumable : consumableList)
        {
            if(consumable.getName().equals(name))
            {
                return consumable;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name + " (CONSOMMABLE) : " + description + ", " + effect.keySet().toArray()[0] + " +" + effect.values().toArray()[0] + ", " + value + " Gils";
    }

    public void use(String target)
    {
        System.out.println(target + " utilise " + name + " (+" + effect.values().toArray()[0] + " " + effect.keySet().toArray()[0] + ")");
    }
}
