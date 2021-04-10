package Model.Exploration;

public enum ShopType {
    WEAPONSMITH("Forgeron", "W"), ARMOURER("Armurier", "A"), WHOLESALER("Grossiste", "P");

    private String type;
    private String sprite;

    private ShopType(String type, String sprite)
    {
        this.type = type;
        this.sprite = sprite;
    }

    public static ShopType parseString(String shopType)
    {
        if(shopType.equals("WEAPONSMITH"))
        {
            return WEAPONSMITH;
        }
        else if(shopType.equals("ARMOURER"))
        {
            return ARMOURER;
        }
        else if(shopType.equals("WHOLESALER"))
        {
            return WHOLESALER;
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public String getSprite() {
        return sprite;
    }
}
