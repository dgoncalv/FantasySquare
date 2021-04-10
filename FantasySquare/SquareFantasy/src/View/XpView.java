package View;

import Model.Game.Character;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class XpView extends Stage {
    private HBox view;
    private ArrayList<Character> characters;
    private int xpToGive;

    public XpView(ArrayList<Character> characters, int xpToGive)
    {
        this.characters = characters;
        this.xpToGive = xpToGive;

        view = new HBox();
        view.setSpacing(30);
        this.setScene(new Scene(view));
        this.setTitle("Gain de level");
        this.setResizable(false);
        this.initModality(Modality.APPLICATION_MODAL);

        for(Character character : characters)
        {
            String content = character + " : ";
            String[] lines = character.addXp(xpToGive);
            if(lines != null)
            {
                for(String line : lines)
                {
                    content += line;
                }
            }

            Label label = new Label(content);
            view.getChildren().add(label);
        }

        this.show();
    }

}
