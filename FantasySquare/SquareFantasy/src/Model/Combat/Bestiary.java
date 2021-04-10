package Model.Combat;

public enum Bestiary {
    //monstre des plains
    GOBLIN(8,0,1,4,4,3,1,1,16,"Goblin",6,6,6),
    GOBLINGURAD(16,0,3,8,6,5,1,1,23,"Guarde Goblin",18,18,9),
    WOLF(20,0,1,8,0,18,1,1,28,"Loup",6,24,36),
    CRAZYHORSE(64,0,4,10,2,11,1,1,40,"Cheval fou",15,63,22),
    //monstres des forets
    OGRE(100,0,4,18,10,9,1,1,65,"Ogre",195,195,18),
    OGRECHIEF(132,0,6,23,14,15,1,1,17,"Chef Ogre",300,282,30),
    LIZZARD(92,0,3,18,12,12,1,1,55,"Lézard",50,153,24),
    //monstre des deserts
    DESERTBARETTA(76,0,0,30,7,2,1,1,55,"Beretta du désert",70,255,4),
    SANDWORM(80,0,11,12,8,23,1,1,53,"Ver de sable",80,132,45),
    LESSERTIGER(144,0,6,26,12,21,1,1,76,"Petit tigre",80,378,45),
    //monstres du donjons
    SKELETON(10,0,0,10,0,6,1,1,17,"Squelette",3,9,12),
    BLACKWIDOW(28,0,10,0,10,15,1,1,28,"Veuve noir",8,30,30),
    GIGASWORM(56,0,9,17,8,12,1,1,40,"Ver gigantesque",15,63,24),
    WARGWOLF(72,0,3,14,0,27,1,1,46,"Warg",22,93,54),
    //boss
    GARLAND(212,0,12,100,8,6,1,1,64,"Garland",250,130,12);

    private int hpMax;
    private int manaMax;
    private int intel;
    private int str;
    private int def;
    private int agi;
    private int vit;
    private int lck;

    private int magDef;
    private String name;
    private int goldGiven;
    private int xpGiven;
    private int escape;

    Bestiary(int hpMax, int manaMax, int intel, int str, int def, int agi, int vit, int lck, int magDef, String name, int goldGiven, int xpGiven, int escape){
        this.hpMax = hpMax;
        this.manaMax = manaMax;
        this.intel = intel;
        this.str = str;
        this.def = def;
        this.agi = agi;
        this.vit = vit;
        this.lck = lck;

        this.magDef = magDef;
        this.name = name;
        this.goldGiven = goldGiven;
        this.xpGiven = xpGiven;
        this.escape = escape;
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

}
