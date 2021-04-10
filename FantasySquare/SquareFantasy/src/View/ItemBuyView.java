package View;

import Model.Misc.Item;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ItemBuyView extends HBox {
    private Item item;

    public ItemBuyView(Item item)
    {
        this.item = item;

        this.setSpacing(20);

        this.getChildren().addAll(new Label(item.getName()));

        this.getChildren().add(new Button("Acheter : " + item.getValue()));
    }
}
