package Model.Exploration;

import Model.History.Quest;
import Model.History.QuestManager;

import java.util.Stack;

public class VillagerNode extends WorldNode{
    //Pile représentant les quètes que le NPC peut offrir
    private Stack<Quest> questStack;

    public VillagerNode(String sprite)
    {
        super(sprite);
        questStack = new Stack<>();
    }

    //Rajoute une quète aux stack
    public void addQuest(Quest quest)
    {
        quest.assignProvider(this);
        questStack.push(quest);
    }

    //Affiche la quète en cours de ce NPC
    public void triggerQuest(QuestManager questManager)
    {
        if(!questStack.empty())
        {
            questStack.peek().showQuest(questManager);
        }
    }
}
