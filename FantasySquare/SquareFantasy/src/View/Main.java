package View;

import Model.Exploration.*;
import Model.History.QuestManager;
import Model.Utility.Loader;
import Model.Utility.Vector2D;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/exploration.fxml"));

        primaryStage.setTitle("SquareFantasy");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setResizable(false);
        primaryStage.show();

        root.requestFocus();
    }

    public static void main(String[] args) {

        /*//World
        World world = new World("Monde", 3);
        //Remplis le background d'herbre
        world.setBiomeBackgroundMap(Set.GRASS);
        //Teste useless
        world.setCellBackgroundMap(Set.WATER, new Vector2D(1, 1));

        //Création de l'équipe joueur
        PartyNode playerParty = new PartyNode(world);
        world.placeNode(playerParty, new Vector2D(0, 0));
        //World

        //Village
        World village = new World("Village", 5);
        //Remplis le background d'herbre
        village.setBiomeBackgroundMap(Set.GRASS);

        //Création des routes
        for(int i=0; i < village.getSize(); i++)
        {
            village.setCellBackgroundMap(Set.ROAD, new Vector2D(2, i));
            village.setCellBackgroundMap(Set.ROAD, new Vector2D(i, 2));
        }

        //Création d'un NPC "Le roi"
        VillagerNode king = new VillagerNode("K");

        String questName = "Un retour triomphale";
        String questDescription = "Vous êtes de retour, vous devez écouter le Roi.";

        // Création d'une LocationQuest "Un retour triomphale" avec pour objectif de se rendre
        // A "Village" aux coordonée "(0, 0)"
        LocationQuest quest1 = new LocationQuest(questName, questDescription, village, new Vector2D(0, 0));

        //Assignation de la quète aux roi
        king.addQuest(quest1);

        village.placeNode(king, new Vector2D(4, 2));
        //Village

        //Gate
        GateNode villageGate = new GateNode("M", village, new Vector2D(1, 2));
        world.placeNode(villageGate, new Vector2D(1, 1));
        //Gate*/

        //Manager
        Loader loader = new Loader();
        loader.loadItems("src/Data/Item.xml");
        loader.loadWorld("src/Data/World.xml");

        /*
        PartyNode playerParty = new PartyNode(World.getWorld("Monde"));

        playerParty.getActualMap().placeNode(playerParty, new Vector2D(0, 0));

        QuestManager questManager = new QuestManager(playerParty);
        ExplorationManager explorationManager = new ExplorationManager(playerParty, questManager);*/


        //Manager

        //Model.Game Loop
        /*while(true)
        {
            playerParty.getActualMap().showMap();
            explorationManager.move();
        }*/

        launch(args);
    }
}
