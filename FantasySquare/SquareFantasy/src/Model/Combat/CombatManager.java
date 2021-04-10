package Model.Combat;

import Controller.FightController;
import Model.Game.Character;
import Model.Game.Entity;
import Model.Game.Monster;
import Model.Sort.SortByAgility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CombatManager {

    private int xpReward;

    private ArrayList<Character> players;
    private ArrayList<Monster> monsters;
    private ArrayList<Entity> attackOrder;

    public CombatManager()
    {
        xpReward = 0;
    }


    // marche
    public ArrayList<Entity> set(ArrayList<Character> players, ArrayList<Monster> monsters)
    {
        xpReward = 0;

        this.players = players;
        this.monsters = monsters;

        for (Monster monster : monsters)
        {
            xpReward += monster.getXpGiven();
        }


        attackOrder = new ArrayList<>();
        attackOrder.addAll(monsters);
        attackOrder.addAll(players);

        Collections.sort(attackOrder, new SortByAgility());

        return attackOrder;
    }

    /*private void playerAction(ArrayList<Character> players, ArrayList<Monster> monsters, Character player)
    {
        Scanner sc = new Scanner(System.in);
        boolean hasPlayed = false;

        while(!hasPlayed)
        {
            System.out.println("\n" + player.getName() + " joue :");
            System.out.println("\tAttaquer - 1 ");
            System.out.println("\tSorts - 2 ");
            System.out.print("\tFuite - 3 ");

            int choice = sc.nextInt();

            switch (choice)
            {
                case 1:
                    System.out.println("\t\tSelectionner votre ennemie :");

                    for(int i=0; i < monsters.size(); i++)
                    {
                        System.out.println("\t\t\t" + (i+1) + " - " + monsters.get(i));
                    }

                    int choiceEnemy = sc.nextInt();

                    try
                    {
                        monsters.get(choiceEnemy - 1).phyDamage(player.getAttack());
                        System.out.println(monsters.get(choiceEnemy - 1));

                        if(monsters.get(choice - 1).getIsDead())
                        {
                            monsters.remove(choiceEnemy - 1);
                        }

                        hasPlayed = true;
                    }
                    catch (IndexOutOfBoundsException error)
                    {
                        System.out.println("Veuiller choisir un nombre cohérent");
                    }

                    break;

                case 2:
                    for (int i = 0; i < player.getSpells().size(); i++)
                    {
                        System.out.println("\t\t" + (i+1) + " - " + player.getSpells().get(i) + " ");
                    }

                    Scanner scanner = new Scanner(System.in);
                    int choiceSpell = scanner.nextInt() - 1;

                    if(choiceSpell < player.getSpells().size() && choiceSpell >= 0)
                    {
                        Spell spellChoice = player.getSpells().get(choiceSpell);
                        if(spellChoice.getDegats() < 0)
                        {
                            System.out.println("\t\tChoississer un allier à soigner : ");
                            for (int countAllyHeal=0; countAllyHeal<players.size(); countAllyHeal++)
                            {
                                System.out.println("\t\t\t" + (countAllyHeal + 1) + " - " + players.get(countAllyHeal));
                            }

                            int choiceAllyHeal = scanner.nextInt() - 1;

                            if(choiceAllyHeal < players.size() && choiceAllyHeal >= 0)
                            {
                                players.get(choiceAllyHeal).addHp(-spellChoice.getDegats());
                                hasPlayed = true;
                            }
                        }
                        else
                        {
                            System.out.println("\t\tChoississer un ennemie à taper : ");
                            for (int countEnemy=0; countEnemy<monsters.size(); countEnemy++)
                            {
                                System.out.println("\t\t\t" + (countEnemy + 1) + " - " + monsters.get(countEnemy));
                            }

                            int choiceEnnemyToAttack = scanner.nextInt() - 1;

                            if(choiceEnnemyToAttack < players.size() && choiceEnnemyToAttack >= 0)
                            {
                                if(player.getMana() >= spellChoice.getMana())
                                {
                                    monsters.get(choiceEnnemyToAttack).magDamage(player.getIntel(), spellChoice.getDegats());
                                    player.setMana(player.getMana() - spellChoice.getMana());
                                    if(monsters.get(choiceEnnemyToAttack).getIsDead())
                                    {
                                        monsters.remove(monsters.get(choiceEnnemyToAttack));
                                    }
                                    hasPlayed = true;
                                }
                                else
                                {
                                    System.out.println("Vous n'avez pas asser de mana");
                                }
                            }
                        }
                    }

                    break;

                case 3:
                    Random random = new Random();
                    hasPlayed = true;
                    if(random.nextInt(3) == 0)
                    {
                        players.clear();
                        System.out.println("Vous avez fuit");
                    }
                    break;

                default:
                    System.out.println("Erreur de saisie");
                    break;
            }
        }
    }*/

    public void aiAction(ArrayList<Character> players, Monster monster)
    {
        Random attackChoice = new Random();
        int choice = attackChoice.nextInt(players.size());
        int damageAmount = players.get(choice).phyDamage(monster.getAttack());
        FightController.getInstance().getLogLabel().setText(monster.getName() + " attaque " + players.get(choice).getName() + " pour " + damageAmount + " dégats.");
    }

    public ArrayList<Character> getPlayers() {
        return players;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public int getXpReward() {
        return xpReward;
    }
}
