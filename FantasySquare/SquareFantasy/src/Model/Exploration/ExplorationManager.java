package Model.Exploration;

import Controller.ExploraterController;
import Model.Combat.Bestiary;
import Model.Game.Monster;
import Model.History.QuestManager;
import Model.Utility.Vector2D;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ExplorationManager {
    private PartyNode partyPlayer;
    private QuestManager questManager;

    public ExplorationManager(PartyNode player, QuestManager questManager)
    {
        partyPlayer = player;
        this.questManager = questManager;
    }

    //Loop principale pour le mouvement du joueur
    public void move(String direction)
    {

        Vector2D temp = partyPlayer.getCoordinate();
        Vector2D target = temp;

        switch (direction)
        {
            case "D":
                target = new Vector2D(temp.getX() + 1, temp.getY());
                break;
            case "B":
                target = new Vector2D(temp.getX(), temp.getY() + 1);
                break;
            case "G":
                target = new Vector2D(temp.getX() - 1, temp.getY());
                break;
            case "H":
                target = new Vector2D(temp.getX(), temp.getY() - 1);
            default:
                break;
        }

        if(verifyMovement(target))
        {
            partyPlayer.getActualMap().eraseNode(temp);
            partyPlayer.getActualMap().placeNode(partyPlayer, target);
            questManager.checkQuest();
        }
    }

    //Permet de vérifier sur quoi le joueur veut aller
    public boolean verifyMovement(Vector2D target)
    {
        boolean xCondition = (target.getX() >= 0) && (target.getX() < partyPlayer.getActualMap().getSize());
        boolean yCondition = (target.getY() >= 0) && (target.getY() < partyPlayer.getActualMap().getSize());

        //Si le joueur n'est pas hors limite
        if(xCondition && yCondition)
        {
            //Si la case est libre
            if(partyPlayer.getActualMap().getNode(target) == null)
            {
                Random random = new Random();
                Set tile = partyPlayer.getActualMap().getCellBackgroundMap(target);
                if(random.nextInt(20) == 0)
                {
                    if(tile == Set.GRASS)
                    {
                        ArrayList<Monster> monsters = new ArrayList<>();
                        for(int i=0; i < 4; i++)
                        {
                            int randomNumber = random.nextInt(11);
                            if(randomNumber < 5)
                            {
                                monsters.add(new Monster(Bestiary.GOBLIN));
                            }
                            else if(randomNumber < 8)
                            {
                                monsters.add(new Monster(Bestiary.GOBLINGURAD));
                            }
                            else if(randomNumber < 10)
                            {
                                monsters.add(new Monster(Bestiary.WOLF));
                            }
                            else
                            {
                                monsters.add(new Monster(Bestiary.CRAZYHORSE));
                            }
                        }

                        ExploraterController.getInstance().startFight(monsters);
                    }

                    else if(tile == Set.FOREST)
                    {
                        ArrayList<Monster> monsters = new ArrayList<>();
                        for(int i=0; i < 4; i++)
                        {
                            int randomNumber = random.nextInt(9);
                            if(randomNumber < 5)
                            {
                                monsters.add(new Monster(Bestiary.OGRE));
                            }
                            else if(randomNumber < 8)
                            {
                                monsters.add(new Monster(Bestiary.LIZZARD));
                            }
                            else if(randomNumber < 9)
                            {
                                monsters.add(new Monster(Bestiary.OGRECHIEF));
                            }
                        }

                        ExploraterController.getInstance().startFight(monsters);
                    }

                    else if(tile == Set.DESERT)
                    {
                        ArrayList<Monster> monsters = new ArrayList<>();
                        for(int i=0; i < 4; i++)
                        {
                            int randomNumber = random.nextInt(9);
                            if(randomNumber < 5)
                            {
                                monsters.add(new Monster(Bestiary.DESERTBARETTA));
                            }
                            else if(randomNumber < 8)
                            {
                                monsters.add(new Monster(Bestiary.SANDWORM));
                            }
                            else if(randomNumber < 9)
                            {
                                monsters.add(new Monster(Bestiary.LESSERTIGER));
                            }
                        }

                        ExploraterController.getInstance().startFight(monsters);
                    }
                    else if(tile == Set.DUNGEONFLOOR)
                    {
                        ArrayList<Monster> monsters = new ArrayList<>();
                        for(int i=0; i < 4; i++)
                        {
                            int randomNumber = random.nextInt(11);
                            if(randomNumber < 5)
                            {
                                monsters.add(new Monster(Bestiary.SKELETON));
                            }
                            else if(randomNumber < 8)
                            {
                                monsters.add(new Monster(Bestiary.BLACKWIDOW));
                            }
                            else if(randomNumber < 9)
                            {
                                monsters.add(new Monster(Bestiary.GIGASWORM));
                            }
                            else
                            {
                                monsters.add(new Monster(Bestiary.WARGWOLF));
                            }
                        }

                        ExploraterController.getInstance().startFight(monsters);
                    }
                }

                return true;
            }
            //Si la case est une porte vers une autre case
            else if(partyPlayer.getActualMap().getNode(target) instanceof GateNode)
            {
                GateNode temp = (GateNode) partyPlayer.getActualMap().getNode(target);
                //Changer de map vers celle cible
                partyPlayer.swapMap(temp);
                return false;
            }
            //Si la case est un villageoi
            else if(partyPlayer.getActualMap().getNode(target) instanceof VillagerNode)
            {
                VillagerNode temp = (VillagerNode) partyPlayer.getActualMap().getNode(target);
                //Voir la quète qu'il a a proposé
                temp.triggerQuest(questManager);
            }
            else if (partyPlayer.getActualMap().getNode(target) instanceof ShopkeeperNode)
            {
                ShopkeeperNode temp = (ShopkeeperNode) partyPlayer.getActualMap().getNode(target);
                ExploraterController.getInstance().shopTrigger(temp);
            }
        }
        return false;
    }
}
