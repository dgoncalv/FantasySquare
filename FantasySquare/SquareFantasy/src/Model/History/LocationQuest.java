package Model.History;

import Model.Exploration.PartyNode;
import Model.Exploration.World;
import Model.Utility.Vector2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class LocationQuest extends Quest{
    //Monde cible de la quète
    private World destination;
    //Coordonnée précise de la quète
    private Vector2D location;

    public LocationQuest(String questName, String questDescription, Vector2D location)
    {
        super(questName, questDescription);
        this.location = location;
        this.destination = null;
    }

    public void setDestination(World destination) {
        this.destination = destination;
    }

    //Vérification si la quète est compléter
    public boolean isAccomplished(PartyNode player)
    {
        //Si le joueur est sur la bonne map et les bonnes coordonée
        if(player.getActualMap() == destination && player.getCoordinate().equals(location))
        {
            destination.setCellBackgroundMap(destination.getBackgroundBiome(), location);
            return true;
        }
        return false;
    }

    public Vector2D getLocation() {
        return location;
    }

    public World getDestination() {
        return destination;
    }
}
