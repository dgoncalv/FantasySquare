package Model.Game;

import Model.Combat.Bestiary;

public class Monster extends Entity{
    protected int magDef;
    protected String name;
    protected int goldGiven;
    protected int xpGiven;
    protected int escape;

    public Monster(Bestiary monster){
        super(monster.getName(), monster.getHpMax(),
            monster.getManaMax(),
            monster.getIntel(),
            monster.getStr(),
            monster.getDef(),
            monster.getAgi(),
            monster.getVit(),
            monster.getLck());
        magDef = monster.getMagDef();
        name = monster.getName();
        goldGiven = monster.getGoldGiven();
        xpGiven = monster.getXpGiven();
        escape = monster.getEscape();
    }

    /*------------ get ---------*/
    public int getMagDef(){
        return magDef;
    }

    public String getName(){
        return name;
    }

    public int getGoldGiven(){
        return goldGiven;
    }

    public int getXpGiven(){
        return xpGiven;
    }

    public int getEscape(){
        return escape;
    }

    /*------------ set ---------*/
    public void setMagDef(int magDef){
        this.magDef = magDef;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGoldGiven(int goldGiven){
        this.goldGiven = goldGiven;
    }

    public void setXpGiven(int xpGiven){
        this.xpGiven = xpGiven;
    }

    public void setEscape(int escape){
        this.escape = escape;
    }

}
