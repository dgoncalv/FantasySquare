package Model.Misc;

import java.util.ArrayList;

public class InventorySection {
    private ArrayList<Item> items;

    public InventorySection()
    {
        items = new ArrayList<>();
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
