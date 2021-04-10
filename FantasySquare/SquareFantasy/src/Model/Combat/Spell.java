package Model.Combat;

public enum Spell {
    HEAL("Soin",-20, 10),
    LIGHTBOLT_STRIKE("Foudroiement",5, 5),
    MAGIC_RAY("Rayon magique" ,15, 15),
    ASSASSINATION("Assassinat" ,10, 10);

    private String name;
    private int degats;
    private int mana;

    Spell(String name, int degats, int mana){
        this.name = name;
        this.degats = degats;
        this.mana = mana;
    }

    /*---------GET---------*/
    public int getDegats(){
        return degats;
    }

    public int getMana(){
        return mana;
    }

    public String toString(){
        return name + " : " + degats + " dégats, " + mana +" MP";
    }

    /*---------SET--------*/
    public void setDegats(int degats){
        this.degats = degats;
    }

    public void setMana(int mana){
        this.mana = mana;
    }
}
