package Model.Combat;

public enum CharacterClass {
    BLACKMAGE(25,20,20,8,5,11,1,10),
    WARRIOR(35,5,1,20,10,5,10,5),
    WHITEMAGE(28,20,15,8,1,6,10,5),
    THIEF(30,10,5,5,10,12,5,15);

    private int hpMax;
    private int manaMax;
    private int intel;
    private int str;
    private int def;
    private int agi;
    private int vit;
    private int lck;

    CharacterClass(int hpMax, int manaMax, int intel, int str, int def, int agi, int vit, int lck){
        this.hpMax = hpMax;
        this.manaMax = manaMax;
        this.intel = intel;
        this.str = str;
        this.def = def;
        this.agi = agi;
        this.vit = vit;
        this.lck = lck;
    }

    /*------------ get ---------*/
    public int getHpMax(){
        return hpMax;
    }

    public int getManaMax(){
        return manaMax;
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
}
