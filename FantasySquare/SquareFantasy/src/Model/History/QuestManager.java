package Model.History;

import Controller.ExploraterController;
import Model.Exploration.PartyNode;

import java.util.HashSet;

public class QuestManager {
    //Ensemble de toute les quètes relatives à des emplacement
    private HashSet<LocationQuest> activeLocationQuest;
    private PartyNode player;

    public QuestManager(PartyNode player)
    {
        this.player = player;
        activeLocationQuest = new HashSet<>();
    }

    //Ajout d'une quète à un ensemble
    public void addQuest(Quest newQuest)
    {
        //Si la quète est une LocationQuest
        if(newQuest instanceof LocationQuest)
        {
            LocationQuest temp = (LocationQuest) newQuest;
            //Ajout à l'ensemble des LocationQuest
            activeLocationQuest.add(temp);
        }
    }

    //Vérifie si des quètes son completer
    public void checkQuest()
    {
        //Verification LocationQuest
        for (LocationQuest quest : activeLocationQuest)
        {
            //Si la quète est complete
            if(quest.isAccomplished(player))
            {
                //La quète est enlever de l'ensemble
                activeLocationQuest.remove(quest);
                ExploraterController.questFinish(quest);
            }
        }
    }
}
