package Model.Utility;

import Model.Misc.Consumable;
import Model.Misc.Item;
import Model.Misc.Statistic;
import Model.Exploration.*;
import Model.History.LocationQuest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Loader {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    public Loader()
    {

    }

    public void loadWorld(String path)
    {
        HashMap<GateNode, String> nodeToComplete = new HashMap<>();
        HashMap<LocationQuest, String> questToComplete = new HashMap<>();

        try
        {
            factory.setValidating(true);
            DocumentBuilder builder = factory.newDocumentBuilder();

            ErrorHandler errorHandler = new LoadingErrorHandler();
            builder.setErrorHandler(errorHandler);

            File worldsXml = new File(path);

            try
            {
                Document xml = builder.parse(worldsXml);

                Element root = xml.getDocumentElement();
                for(int i=0; i < root.getChildNodes().getLength(); i++)
                {
                    if(root.getChildNodes().item(i) instanceof Element)
                    {
                        Element currentWorldElement = (Element) root.getChildNodes().item(i);
                        String name = currentWorldElement.getAttribute("name");
                        int size = Integer.valueOf(currentWorldElement.getAttribute("size"));
                        World newWorld = new World(name, size);

                        Element foreground = (Element) currentWorldElement.getChildNodes().item(1);
                        for(int foregroundCount=0; foregroundCount < foreground.getChildNodes().getLength(); foregroundCount++)
                        {
                            if(foreground.getChildNodes().item(foregroundCount).getNodeName() == "Node")
                            {
                                Element tempNode = (Element) foreground.getChildNodes().item(foregroundCount);
                                Vector2D location = new Vector2D(Integer.valueOf(tempNode.getAttribute("x")), Integer.valueOf(tempNode.getAttribute("y")));
                                WorldNode node = new WorldNode(tempNode.getTextContent());
                                newWorld.placeNode(node, location);
                            }
                            else if(foreground.getChildNodes().item(foregroundCount).getNodeName() == "GateNode")
                            {
                                Element tempNode = (Element) foreground.getChildNodes().item(foregroundCount);
                                Vector2D coordinate = new Vector2D(Integer.valueOf(tempNode.getAttribute("xSpawn")), Integer.valueOf(tempNode.getAttribute("ySpawn")));
                                Vector2D location = new Vector2D(Integer.valueOf(tempNode.getAttribute("x")), Integer.valueOf(tempNode.getAttribute("y")));
                                GateNode gateNode = new GateNode(tempNode.getTextContent(), coordinate);
                                newWorld.placeNode(gateNode, location);

                                nodeToComplete.put(gateNode, tempNode.getAttribute("lead"));
                            }
                            else if(foreground.getChildNodes().item(foregroundCount).getNodeName() == "VillagerNode")
                            {
                                Element tempNode = (Element) foreground.getChildNodes().item(foregroundCount);
                                Vector2D location = new Vector2D(Integer.valueOf(tempNode.getAttribute("x")), Integer.valueOf(tempNode.getAttribute("y")));
                                VillagerNode villagerNode = new VillagerNode(tempNode.getAttribute("sprite"));
                                newWorld.placeNode(villagerNode, location);

                                if(tempNode.getChildNodes().getLength() > 0)
                                {
                                    for(int questCount=0; questCount < tempNode.getChildNodes().getLength(); questCount++)
                                    {
                                        if(tempNode.getChildNodes().item(questCount).getNodeName().equals("LocationQuest"))
                                        {
                                            Element newQuest = (Element) tempNode.getChildNodes().item(questCount);
                                            Vector2D coordinate = new Vector2D(Integer.valueOf(newQuest.getAttribute("x")), Integer.valueOf(newQuest.getAttribute("y")));
                                            LocationQuest locationQuest = new LocationQuest(newQuest.getTextContent(), newQuest.getAttribute("description"), coordinate);
                                            questToComplete.put(locationQuest, newQuest.getAttribute("map"));

                                            villagerNode.addQuest(locationQuest);
                                        }
                                    }
                                }
                            }
                            else if(foreground.getChildNodes().item(foregroundCount).getNodeName() == "ShopkeeperNode")
                            {
                                Element shopKeeperNode = (Element) foreground.getChildNodes().item(foregroundCount);
                                Vector2D coordinate = new Vector2D(Integer.valueOf(shopKeeperNode.getAttribute("x")), Integer.valueOf(shopKeeperNode.getAttribute("y")));
                                ShopkeeperNode skn = new ShopkeeperNode(ShopType.parseString(shopKeeperNode.getAttribute("type")));

                                for (int itemCount=0; itemCount < shopKeeperNode.getChildNodes().getLength(); itemCount++)
                                {
                                    if(shopKeeperNode.getChildNodes().item(itemCount).getNodeName().equals("Item"))
                                    {
                                        skn.getConsumables().add(Consumable.getConsumable(shopKeeperNode.getChildNodes().item(itemCount).getTextContent()));
                                    }
                                }

                                newWorld.placeNode(skn, coordinate);
                            }
                        }

                        Element background = (Element) currentWorldElement.getChildNodes().item(3);
                        newWorld.setBiomeBackgroundMap(Set.parseString(background.getAttribute("biome")));
                        for(int backgroundCount = 0; backgroundCount < background.getChildNodes().getLength(); backgroundCount++)
                        {
                            if(background.getChildNodes().item(backgroundCount) instanceof Element)
                            {
                                Element tile = (Element) background.getChildNodes().item(backgroundCount);
                                Vector2D coordinate = new Vector2D(Integer.valueOf(tile.getAttribute("x")), Integer.valueOf(tile.getAttribute("y")));
                                newWorld.setCellBackgroundMap(Set.parseString(tile.getTextContent()), coordinate);
                            }
                        }
                    }
                }
            }
            catch (SAXException error)
            {
                System.out.println(error.getMessage());
            }
        }
        catch (ParserConfigurationException error)
        {
            System.out.println(error.getMessage());
        }
        catch (IOException error)
        {
            System.out.println(error.getMessage());
        }

        for(Map.Entry<GateNode, String> entry : nodeToComplete.entrySet())
        {
            entry.getKey().setMapToLoad(World.getWorld(entry.getValue()));
        }
        for(Map.Entry<LocationQuest, String> entry : questToComplete.entrySet())
        {
            World.getWorld(entry.getValue()).setCellBackgroundMap(Set.GOAL, entry.getKey().getLocation());
            entry.getKey().setDestination(World.getWorld(entry.getValue()));
        }
    }

    public void loadItems(String path)
    {
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            ErrorHandler errorHandler = new LoadingErrorHandler();
            builder.setErrorHandler(errorHandler);

            File worldsXml = new File(path);

            try {
                Document xml = builder.parse(worldsXml);

                Element root = xml.getDocumentElement();
                for(int i=0; i < root.getChildNodes().getLength(); i++)
                {
                    if(root.getChildNodes().item(i).getNodeName().equals("ITEM"))
                    {
                        Element currentItem = (Element) root.getChildNodes().item(i);
                        Item item = new Item(currentItem.getTextContent(), currentItem.getAttribute("DESCRIPTION"), Integer.valueOf(currentItem.getAttribute("VALUE")));
                        Item.addItem(item);
                    }
                    else if(root.getChildNodes().item(i).getNodeName().equals("CONSUMABLE"))
                    {
                        Element currentConsumable = (Element) root.getChildNodes().item(i);
                        Statistic stat = Statistic.valueOf(currentConsumable.getAttribute("STAT"));
                        Consumable item = new Consumable(currentConsumable.getTextContent(), currentConsumable.getAttribute("DESCRIPTION"), Integer.valueOf(currentConsumable.getAttribute("VALUE")), stat, Integer.valueOf(currentConsumable.getAttribute("STATVALUE")));
                        Consumable.addConsumable(item);
                    }
                }
            }
            catch (SAXException error)
            {
                System.out.println(error.getMessage());
            }
        }
        catch (ParserConfigurationException error)
        {
            System.out.println(error.getMessage());
        }
        catch (IOException error)
        {
            System.out.println(error.getMessage());
        }
    }
}
