package Model.Exploration;

import Model.Misc.Consumable;

import java.util.ArrayList;

public class ShopkeeperNode extends WorldNode{
    private ShopType shopType;
    private ArrayList<Consumable> consumables;

    public ShopkeeperNode(ShopType shopType)
    {
        super(shopType.getSprite());
        this.shopType = shopType;
        consumables = new ArrayList<>();
    }

    public ArrayList<Consumable> getConsumables() {
        return consumables;
    }
}
