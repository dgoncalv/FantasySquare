package Model.Misc;

public class Inventory {
    private InventorySection itemSection;
    private InventorySection consumableSection;
    private int gilsCount;

    public Inventory()
    {
        itemSection = new InventorySection();
        consumableSection = new InventorySection();
    }

    public void addItem(Item item)
    {
        if(item instanceof Consumable)
        {
            consumableSection.addItem(item);
        }
        else if(item instanceof Item)
        {
            itemSection.addItem(item);
        }
    }

    public void removeItem(Item item)
    {
        if(item instanceof Consumable)
        {
            consumableSection.getItems().remove(item);
        }
        else if(item instanceof Item)
        {
            itemSection.getItems().remove(item);
        }
    }

    public InventorySection getConsumableSection() {
        return consumableSection;
    }

    public InventorySection getItemSection() {
        return itemSection;
    }

    public int getGilsCount() {
        return gilsCount;
    }
}
