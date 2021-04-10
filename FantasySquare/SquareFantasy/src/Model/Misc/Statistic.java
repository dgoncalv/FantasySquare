package Model.Misc;

public enum Statistic {
    HP("Point de vie"), MP("Point de mana"), STR("Force"), AGI("Agilité"), INT("Intelligence"), VIT("Vitesse"), LCK("Chance");

    private String description;

    private Statistic(String description)
    {
        this.description = description;
    }

    public Statistic getValue(String value)
    {
        if(value.equals("HP"))
        {
            return HP;
        }
        else if(value.equals("MP"))
        {
            return MP;
        }
        else if(value.equals("STR"))
        {
            return STR;
        }
        else if(value.equals("AGI"))
        {
            return AGI;
        }
        else if(value.equals("INT"))
        {
            return INT;
        }
        else if(value.equals("VIT"))
        {
            return VIT;
        }
        else if(value.equals("LCK"))
        {
            return LCK;
        }
        return null;
    }

    public String getDescription() {
        return description;
    }
}
