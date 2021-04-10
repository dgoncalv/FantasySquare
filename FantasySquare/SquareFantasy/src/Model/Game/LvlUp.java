package Model.Game;


import Model.Combat.CharacterClass;

public class LvlUp {

    boolean lvlTableBlackMage[][] = {{false,false,true,false,false,true},
                                    {false,true,true,true,false,false},
                                    {true,false,true,false,true,true},
                                    {false,false,true,true,false,false},
                                    {false,true,true,false,true,true},
                                    {true,false,true,true,false,false},
                                    {false,false,true,false,true,true},
                                    {false,true,true,true,false,false},
                                    {true,false,true,false,true,false},
                                    {false,false,true,false,false,true},
                                    {false,true,true,true,false,false},
                                    {true,false,true,false,false,false},
                                    {false,false,true,false,true,true},
                                    {false,true,true,false,false,false},
                                    {true,false,true,true,false,false},
                                    {false,false,true,false,false,false},
                                    {false,true,true,false,true,true},
                                    {true,false,true,false,false,false},
                                    {false,false,true,true,false,false},
                                    {false,true,true,false,false,false},
                                    {true,false,true,false,true,true},
                                    {false,false,true,false,false,false}};

    boolean lvlTableWarrior[][] =   {{true,true,false,true,false,true},
                                    {true,true,false,true,true,true},
                                    {true,true,true,false,true,true},
                                    {true,true,false,true,false,true},
                                    {true,true,false,true,true,true},
                                    {true,true,true,false,true,true},
                                    {true,true,false,true,false,true},
                                    {true,true,false,true,true,true},
                                    {true,true,true,false,true,true},
                                    {true,true,false,true,false,true},
                                    {true,true,false,true,true,false},
                                    {true,true,true,false,true,true},
                                    {true,true,false,true,false,true},
                                    {true,true,false,true,true,false},
                                    {true,true,true,false,true,true},
                                    {true,true,false,true,false,true},
                                    {true,true,false,true,true,false},
                                    {true,true,true,false,true,true},
                                    {true,true,false,true,false,true},
                                    {true,false,false,true,true,false},
                                    {true,true,true,false,true,true},
                                    {true,true,false,true,false,true}};

    boolean lvlTableWhiteMage[][] = {{true,true,true,false,true,true},
                                    {true,true,true,true,false,false},
                                    {true,false,true,false,true,true},
                                    {false,true,true,true,false,false},
                                    {false,false,true,false,true,true},
                                    {true,false,true,true,false,false},
                                    {false,true,true,false,true,true},
                                    {false,false,true,true,false,false},
                                    {true,false,true,false,true,true},
                                    {false,true,true,true,false,false},
                                    {false,false,true,false,true,true},
                                    {true,false,true,true,false,false},
                                    {false,true,true,false,true,true},
                                    {false,false,true,true,false,false},
                                    {true,false,true,false,true,false},
                                    {false,true,true,true,false,true},
                                    {false,false,true,false,true,false},
                                    {true,false,true,true,false,false},
                                    {false,true,true,false,true,true},
                                    {false,false,true,true,false,false},
                                    {true,false,true,false,false,false},
                                    {false,true,true,false,true,false}};

    boolean lvlTableThief[][] =     {{true,true,false,false,true,true},
                                    {true,false,true,true,true,true},
                                    {true,true,false,false,true,true},
                                    {true,false,true,false,true,true},
                                    {true,true,false,true,true,false},
                                    {true,false,true,false,true,true},
                                    {true,true,false,false,true,false},
                                    {true,false,false,true,true,true},
                                    {true,true,true,false,true,false},
                                    {false,false,false,false,true,true},
                                    {true,true,false,true,true,false},
                                    {true,false,true,false,true,true},
                                    {false,true,false,false,true,false},
                                    {true,false,false,true,true,true},
                                    {true,true,true,false,true,false},
                                    {false,true,false,false,true,false},
                                    {true,false,false,true,true,true},
                                    {true,true,true,false,true,false},
                                    {false,true,false,false,true,false},
                                    {true,false,false,true,true,true},
                                    {true,true,true,false,true,false},
                                    {false,true,false,false,true,false}};

    public LvlUp(){
    }

    public String[] testLvlUp(Character character){
        CharacterClass charClass = character.getCharClass();

        String[] lvlUpText = new String[0];

        int lvl = character.getLvl();
        switch (charClass){
            case BLACKMAGE :
                lvlUpText = lvlUpBlackMage(lvl, character);
                break;
            case WARRIOR :
                lvlUpText = lvlUpWarrior(lvl, character);
                break;
            case WHITEMAGE :
                lvlUpText = lvlUpWhiteMage(lvl, character);
                break;
            case THIEF :
                lvlUpText = lvlUpThief(lvl, character);
                break;
        }
        return lvlUpText;
    }

    public String[] lvlUpBlackMage(int lvl, Character character){
        String[] lvlUpText = {"\n\t" + character.getName() + " gagne 0 de STRENGTH",
                "\n\t" + character.getName() + " gagne 0 d'AGILITY",
                "\n\t" + character.getName() + " gagne 0 d'INTELLIGENCE",
                "\n\t" + character.getName() + " gagne 0 de VITALITY",
                "\n\t" + character.getName() + " gagne 0 de LUCK",
                "\n\t" + character.getName() + " gagne 0 d'HP MAX"};
        //str
        if (lvlTableBlackMage[lvl-2][0])
        {
            lvlUpText[0] = "\n\t" + character.getName() + " gagne 1 de STRENGTH";
            character.addStr(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[0] = "\n\t" + character.getName() + " gagne 1 de STRENGTH";
                character.addStr(1);
            }
        }
        //agi
        if (lvlTableBlackMage[lvl-2][1])
        {
            lvlUpText[1] = "\n\t" + character.getName() + " gagne 1 d'AGILITY";
            character.addAgi(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[1] = "\n\t" + character.getName() + " gagne 1 d'AGILITY";
                character.addAgi(1);
            }
        }
        //intel
        if (lvlTableBlackMage[lvl-2][2])
        {
            lvlUpText[2] = "\n\t" + character.getName() + " gagne 1 d'INTELLIGENCE";
            character.addIntel(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[2] = "\n\t" + character.getName() + " gagne 1 d'INTELLIGENCE";
                character.addIntel(1);
            }
        }
        //vit
        if (lvlTableBlackMage[lvl-2][3])
        {
            lvlUpText[3] = "\n\t" + character.getName() + " gagne 1 de VITALITY";
            character.addVit(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[3] = "\n\t" + character.getName() + " gagne 1 de VITALITY";
                character.addVit(1);
            }
        }
        //lck
        if (lvlTableBlackMage[lvl-2][4])
        {
            lvlUpText[4] = "\n\t" + character.getName() + " gagne 1 de LUCK";
            character.addLck(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[4] = "\n\t" + character.getName() + " gagne 1 de LUCK";
                character.addLck(1);
            }
        }
        //hp
        int r = 0;
        if (lvlTableBlackMage[lvl-2][5])
        {
            r = ((int)(Math.random() * 5))+21;
            character.addHpMax(r);
        }
        lvlUpText[5] = "\n\t" + character.getName() + " gagne " + (r + character.getVit()/4) + " d'HP MAX";
        character.addHpMax(character.getVit()/4);

        return lvlUpText;
    }

    public String[] lvlUpWarrior(int lvl, Character character){
        String[] lvlUpText = {"\n\t" + character.getName() + " gagne 0 de STRENGTH",
                "\n\t" + character.getName() + " gagne 0 d'AGILITY",
                "\n\t" + character.getName() + " gagne 0 d'INTELLIGENCE",
                "\n\t" + character.getName() + " gagne 0 de VITALITY",
                "\n\t" + character.getName() + " gagne 0 de LUCK",
                "\n\t" + character.getName() + " gagne 0 d'HP MAX"};
        //str
        if (lvlTableWarrior[lvl-2][0])
        {
            lvlUpText[0] = "\n\t" + character.getName() + " gagne 1 de STRENGTH";
            character.addStr(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[0] = "\n\t" + character.getName() + " gagne 1 de STRENGTH";
                character.addStr(1);
            }
        }
        //agi
        if (lvlTableWarrior[lvl-2][1])
        {
            lvlUpText[1] = "\n\t" + character.getName() + " gagne 1 d'AGILITY";
            character.addAgi(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[1] = "\n\t" + character.getName() + " gagne 1 d'AGILITY";
                character.addAgi(1);
            }
        }
        //intel
        if (lvlTableWarrior[lvl-2][2])
        {
            lvlUpText[2] = "\n\t" + character.getName() + " gagne 1 d'INTELLIGENCE";
            character.addIntel(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[2] = "\n\t" + character.getName() + " gagne 1 d'INTELLIGENCE";
                character.addIntel(1);
            }
        }
        //vit
        if (lvlTableWarrior[lvl-2][3])
        {
            lvlUpText[3] = "\n\t" + character.getName() + " gagne 1 de VITALITY";
            character.addVit(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[3] = "\n\t" + character.getName() + " gagne 1 de VITALITY";
                character.addVit(1);
            }
        }
        //lck
        if (lvlTableWarrior[lvl-2][4])
        {
            lvlUpText[4] = "\n\t" + character.getName() + " gagne 1 de LUCK";
            character.addLck(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[4] = "\n\t" + character.getName() + " gagne 1 de LUCK";
                character.addLck(1);
            }
        }
        //hp
        int r = 0;
        if (lvlTableWarrior[lvl-2][5])
        {
            r = ((int)(Math.random() * 5))+21;
            character.addHpMax(r);
        }
        lvlUpText[5] = "\n\t" + character.getName() + " gagne " + (r + character.getVit()/4) + " d'HP MAX";
        character.addHpMax(character.getVit()/4);

        return lvlUpText;
    }

    public String[] lvlUpWhiteMage(int lvl, Character character){
        String[] lvlUpText = {"\n\t" + character.getName() + " gagne 0 de STRENGTH",
                "\n\t" + character.getName() + " gagne 0 d'AGILITY",
                "\n\t" + character.getName() + " gagne 0 d'INTELLIGENCE",
                "\n\t" + character.getName() + " gagne 0 de VITALITY",
                "\n\t" + character.getName() + " gagne 0 de LUCK",
                "\n\t" + character.getName() + " gagne 0 d'HP MAX"};
        //str
        if (lvlTableWhiteMage[lvl-2][0])
        {
            lvlUpText[0] = "\n\t" + character.getName() + " gagne 1 de STRENGTH";
            character.addStr(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[0] = "\n\t" + character.getName() + " gagne 1 de STRENGTH";
                character.addStr(1);
            }
        }
        //agi
        if (lvlTableWhiteMage[lvl-2][1])
        {
            lvlUpText[1] = "\n\t" + character.getName() + " gagne 1 d'AGLITY";
            character.addAgi(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[1] = "\n\t" + character.getName() + " gagne 1 d'AGLITY";
                character.addAgi(1);
            }
        }
        //intel
        if (lvlTableWhiteMage[lvl-2][2])
        {
            lvlUpText[2] = "\n\t" + character.getName() + " gagne 1 d'INTELLIGENCE";
            character.addIntel(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[2] = "\n\t" + character.getName() + " gagne 1 d'INTELLIGENCE";
                character.addIntel(1);
            }
        }
        //vit
        if (lvlTableWhiteMage[lvl-2][3])
        {
            lvlUpText[3] = "\n\t" + character.getName() + " gagne 1 de VITALITY";
            character.addVit(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[3] = "\n\t" + character.getName() + " gagne 1 de VITALITY";
                character.addVit(1);
            }
        }
        //lck
        if (lvlTableWhiteMage[lvl-2][4])
        {
            lvlUpText[4] = "\n\t" + character.getName() + " gagne 1 de LUCK";
            character.addLck(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[4] = "\n\t" + character.getName() + " gagne 1 de LUCK";
                character.addLck(1);
            }
        }
        //hp
        int r = 0;
        if (lvlTableWhiteMage[lvl-2][5])
        {
            r = ((int)(Math.random() * 5))+21;
            character.addHpMax(r);
        }

        lvlUpText[5] = "\n\t" + character.getName() + " gagne " + (r + character.getVit()/4) + " d'HP MAX";
        character.addHpMax(character.getVit()/4);

        return lvlUpText;
    }

    public String[] lvlUpThief(int lvl, Character character){
        String[] lvlUpText = {"\n\t" + character.getName() + " gagne 0 de STRENGTH",
                "\n\t" + character.getName() + " gagne 0 d'AGILITY",
                "\n\t" + character.getName() + " gagne 0 d'INTELLIGENCE",
                "\n\t" + character.getName() + " gagne 0 de VITALITY",
                "\n\t" + character.getName() + " gagne 0 de LUCK",
                "\n\t" + character.getName() + " gagne 0 d'HP MAX"};

        if (lvlTableThief[lvl-2][0])
        {
            lvlUpText[0] = "\n\t" + character.getName() + " gagne 1 de STRENGTH";
            character.addStr(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[0] = "\n\t" + character.getName() + " gagne 1 de STRENGTH";
                character.addStr(1);
            }
        }
        //agi
        if (lvlTableThief[lvl-2][1])
        {
            lvlUpText[1] = "\n\t" + character.getName() + " gagne 1 d'AGLITY";
            character.addAgi(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[1] = "\n\t" + character.getName() + " gagne 1 d'AGLITY";
                character.addAgi(1);
            }
        }
        //intel
        if (lvlTableThief[lvl-2][2])
        {
            lvlUpText[2] = "\n\t" + character.getName() + " gagne 1 d'INTELLIGENCE";
            character.addIntel(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[2] = "\n\t" + character.getName() + " gagne 1 d'INTELLIGENCE";
                character.addIntel(1);
            }
        }
        //vit
        if (lvlTableThief[lvl-2][3])
        {
            lvlUpText[3] = "\n\t" + character.getName() + " gagne 1 de VITALITY";
            character.addVit(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[3] = "\n\t" + character.getName() + " gagne 1 de VITALITY";
                character.addVit(1);
            }
        }
        //lck
        if (lvlTableThief[lvl-2][4])
        {
            lvlUpText[4] = "\n\t" + character.getName() + " gagne 1 de LUCK";
            character.addLck(1);
        }
        else
        {
            int r = (int)(Math.random() * 4);
            if (r == 0)
            {
                lvlUpText[4] = "\n\t" + character.getName() + " gagne 1 de LUCK";
                character.addLck(1);
            }
        }
        //hp
        int r = 0;
        if (lvlTableThief[lvl-2][5])
        {
            r = ((int)(Math.random() * 5))+21;
            character.addHpMax(r);
        }

        lvlUpText[5] = "\n\t" + character.getName() + " gagne " + (r + character.getVit()/4) + " d'HP MAX";
        character.addHpMax(character.getVit()/4);

        return lvlUpText;
    }
}
