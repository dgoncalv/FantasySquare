package Model.Exploration;

import Model.Combat.CharacterClass;
import Model.Combat.Spell;
import Model.Game.Character;
import Model.Misc.Inventory;

import java.util.ArrayList;
import java.util.HashSet;

public class PartyNode extends WorldNode{

    private World actualMap;
    private Inventory inventory;
    private static ArrayList<Character> characterList = new ArrayList<>();

    public PartyNode(World map)
    {
        super("P");
        inventory = new Inventory();
        actualMap = map;
    }

    public static void setCharacterList() {

        ArrayList<Spell> thiefSpells = new ArrayList<>();
        thiefSpells.add(Spell.ASSASSINATION);

        ArrayList<Spell> blackMageSpells = new ArrayList<>();
        blackMageSpells.add(Spell.LIGHTBOLT_STRIKE);
        blackMageSpells.add(Spell.MAGIC_RAY);

        ArrayList<Spell> whiteMageSpells = new ArrayList<>();
        whiteMageSpells.add(Spell.HEAL);
        whiteMageSpells.add(Spell.MAGIC_RAY);

        characterList.add(new Character("Guerrier", CharacterClass.WARRIOR, null));
        characterList.add(new Character("Voleur", CharacterClass.THIEF, thiefSpells));
        characterList.add(new Character("Mage noir", CharacterClass.BLACKMAGE, blackMageSpells));
        characterList.add(new Character("Mage blanc", CharacterClass.WHITEMAGE, whiteMageSpells));
    }

    public static ArrayList<Character> getCharacterList()
    {
        return characterList;
    }

    public World getActualMap() {
        return actualMap;
    }

    //Méthode permettant de changer la carte actuelle du joueur
    public void swapMap(GateNode node)
    {
        actualMap.eraseNode(coordinate);
        actualMap = node.getMapToLoad();
        actualMap.placeNode(this, node.getSpawn());
    }

    public Inventory getInventory() {
        return inventory;
    }
}
