package Model.Misc;

import java.util.HashSet;

public class Item {
    protected String name;
    protected String description;
    protected int value;
    private static HashSet<Item> itemList = new HashSet<>();

    public Item(String name, String description, int value)
    {
        this.name = name;
        this.value = value;
        this.description = description;
    }

    public static void addItem(Item item)
    {
        itemList.add(item);
    }

    public static Item getItem(String name)
    {
        for(Item item : itemList)
        {
            if(item.getName().equals(name))
            {
                return item;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + " (ITEM) : " + description + ", " + value + " Gils";
    }
}
