package View;

import Model.Misc.Item;
import Model.Exploration.ShopkeeperNode;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BuyView extends Stage {
    private ShopkeeperNode shopkeeperNode;

    public BuyView(ShopkeeperNode shopkeeperNode)
    {
        this.shopkeeperNode = shopkeeperNode;
        this.setWidth(300);
        this.setHeight(300);
        this.setResizable(false);

        HBox view = new HBox();

        this.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(view);
        this.setScene(scene);

        for (Item item : shopkeeperNode.getConsumables())
        {
            view.getChildren().add(new ItemBuyView(item));
        }

        this.show();
    }
}
