package Model.Game;

import Model.Combat.CharacterClass;
import Model.Combat.Spell;

import java.util.ArrayList;

public class Character extends Entity{
    protected String name;
    protected int lvl;
    protected int xp;
    protected int lvlTable[] = {0,28,84,192,392,700,1148,1764,2576,3612,4900,6300,8200,10000,13000,16000,19000,23000,27000,32000,43000,50000,57000,65000};

    private ArrayList<Spell> spells;
    protected CharacterClass charClass;
    protected LvlUp lvlUp;

    public Character(String name, CharacterClass charClass, ArrayList<Spell> spells){
        super(name, charClass.getHpMax(),
                charClass.getManaMax(),
                charClass.getIntel(),
                charClass.getStr(),
                charClass.getDef(),
                charClass.getAgi(),
                charClass.getVit(),
                charClass.getLck());
        this.name = name;
        this.lvl = 1;
        this.xp = 0;
        this.spells = spells;
        lvlUp = new LvlUp();

        this.charClass = charClass;
    }

    /*------------ add ---------*/
    public String[] addXp(int addXp){
        xp += addXp;

        //test lvl up
        if (xp >= lvlTable[lvl]){
            xp -= lvlTable[lvl];
            addLvl(1);

            return lvlUp.testLvlUp(this);    /* Fous la merde  --> Si on ne définit pas lvlUp c'est sur */
        }
        return null;
    }

    public void addLvl(int addLvl){
        hp = hpMax;
        mana = manaMax;
        lvl += addLvl;
    }

    /*------------ get ---------*/
    public String getName(){
        return name;
    }  
    
    public int getLvl(){
        return lvl;
    }

    public int getXp(){
        return xp;
    }

    public CharacterClass getCharClass() {
        return charClass;
    }

    /*------------ set ---------*/
    public void setName(String name){
        this.name = name;
    }

    public void setLvl(int lvl){
        this.lvl = lvl;
    }

    public void setXp(int xp){
        this.xp = xp;
    }

    public ArrayList<Spell> getSpells() {
        return this.spells;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + mana + "/" + manaMax + "Pm";
    }
}
