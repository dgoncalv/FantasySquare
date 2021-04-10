package Model.History;

import Controller.ExploraterController;
import Model.Exploration.VillagerNode;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Scanner;

public class Quest {
    protected String questName;
    protected String questDescription;
    //Villageois donnant la quète
    protected VillagerNode provider;
    protected boolean isActive;

    public Quest(String questName, String questDescription)
    {
        this.questName = questName;
        this.questDescription = questDescription;
        isActive = false;
    }

    //Renseigne qui distribue la quète
    public void assignProvider(VillagerNode villager)
    {
        provider = villager;
    }

    //Vérification si le joueur veut prendre la quète
    public void takeQuest(QuestManager questManager)
    {
        if(ExploraterController.questAccept(this))
        {
            isActive = true;
            //Ajout de la quète au QuestManager
            questManager.addQuest(this);
        }
    }

    //Affiche les détails de la quète
    public void showQuest(QuestManager questManager)
    {
        if(!isActive)
        {
            this.takeQuest(questManager);
        }
        else
        {
            ExploraterController.questShow(this);
        }
    }

    public String getQuestName() {
        return questName;
    }

    public String getQuestDescription() {
        return questDescription;
    }

    public boolean isActive() {
        return isActive;
    }
}
