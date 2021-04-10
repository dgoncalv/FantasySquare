package Model.Game;

public class Entity {
    protected String name;
    protected int hpMax;
    protected int hp;
    protected int manaMax;
    protected int mana;

    protected int intel;
    protected int str;
    protected int def;
    protected int agi;
    protected int vit;
    protected int lck;

    protected boolean isDead;

    protected int actionScore;

    public Entity(String name, int hpMax, int manaMax, int intel, int str, int def, int agi, int vit, int lck){
        this.name = name;
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.manaMax = manaMax;
        this.mana = manaMax;

        this.intel = intel;
        this.str = str;
        this.def = def;
        this.agi = agi;
        this.vit = vit;
        this.lck = lck;
        this.isDead = false;
    }
    
    /*---------- add -------*/
    public void addHpMax(int addHpMax){
        hpMax += addHpMax;
    }

    public void addHp(int addHp){
        hp += addHp;
        if (hp >= hpMax){
            hp = hpMax;
        }
    }

    public void addManaMax(int addManaMax){
        manaMax += addManaMax;
    }

    public void addMana(int addMana){
        mana += addMana;
        if (mana >= manaMax){
            mana = manaMax;
        }
    }

    public void addIntel(int addIntel){
        intel += addIntel;
    }

    public void addStr(int addStr){
        str += addStr;
    }

    public void addDef(int addDef){
        def += addDef;
    }

    public void addAgi(int addAgi){
        agi += addAgi;
    }

    public void addVit(int addVit){
        vit += addVit;
    }

    public void addLck(int addLck){
        lck += addLck;
    }

    /*------------ get ---------*/
    public int getHpMax(){
        return hpMax;
    }

    public int getHp(){
        return hp;
    }

    public int getManaMax(){
        return manaMax;
    }

    public int getMana(){
        return mana;
    }
    
    public int getIntel(){
        return intel;
    }

    public int getStr(){
        return str;
    }

    public int getDef(){
        return def;
    }

    public int getAgi(){
        return agi;
    }

    public int getVit(){
        return vit;
    }

    public int getLck(){
        return lck;
    }

    public boolean getIsDead(){
        return isDead;
    }

    /*---------- set -------*/
    public void setHpMax(int hpMax){
        this.hpMax = hpMax;
    }

    public void setHp(int hp){
        this.hp = hp;
    }

    public void setManaMax(int manaMax){
        this.manaMax = manaMax;
    }

    public void setMana(int mana){
        this.mana = mana;
    }

    public void setIntel(int intel){
        this.intel = intel;
    }

    public void setStr(int str){
        this.str = str;
    }

    public void setDef(int def){
        this.def = def;
    }

    public void setAgi(int agi){
        this.agi = agi;
    }

    public void setVit(int vit){
        this.vit = vit;
    }

    public void setLck(int lck){
        this.lck = lck;
    }

    public void setIsDead(boolean isDead){
        this.isDead = isDead;
    }

    public int getAttack() {
        return this.str;
    }

    public int phyDamage(int attack){
        int degats = attack - this.def;

        if (degats < 1){
            degats = 0;
        }

        this.hp -= degats;
        this.checkDeath();

        return degats;
    }

    public int magDamage(int intel, int spellDamage){
        this.hp -=  intel + spellDamage;

        this.checkDeath();

        return intel + spellDamage;
    }

    @Override
    public String toString() {
        return name + " : " + hp + "/" + hpMax + "Pv";
    }

    private void checkDeath(){
        if (this.getHp() < 1){
            this.setHp(0);
            this.isDead = true;
        }else {
            this.isDead = false;
        }
    }

    public String getName() {
        return name;
    }
}
