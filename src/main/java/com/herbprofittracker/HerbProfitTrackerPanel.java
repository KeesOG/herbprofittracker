package com.herbprofittracker;


import net.runelite.client.callback.ClientThread;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.util.AsyncBufferedImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class HerbProfitTrackerPanel extends PluginPanel {


    private static final Logger log = LoggerFactory.getLogger(HerbProfitTrackerPanel.class);
    @Inject
    private ItemManager itemManager;
    @Inject
    private ClientThread clientThread;

    //Initialize ArrayList with id's of all grimy herbs.
    private ArrayList<Integer> herbIDs = new ArrayList<>(
            Arrays.asList(199, 201, 203, 205, 207, 209, 211, 213, 215, 217, 219, 2485, 3049, 3051)
    );


    private ArrayList<String> herbNames = new ArrayList<>();
    private ArrayList<AsyncBufferedImage> herbImages = new ArrayList<>();

    public void init(){
        //Initializing JFrame and setting its layout.
        JFrame jFrame = new JFrame("Herb run profit tracker");
        jFrame.setLayout(new GridBagLayout());
        Container container = jFrame.getContentPane();

        //Get item images and names and put them in ArrayList.
        addItemImagesToList();
        addNamesToList();
        addItemImagesToPanel(container);
        clientThread.invokeLater(()->{
            addNamesToPanel(container);
        });


        //Add container of JFrame to PluginPanel
        add(container);
    }


    //Gets all images from ItemManager by id from HerbIDs list.
    private void addItemImagesToList(){
        for(Integer herbId : herbIDs){
            AsyncBufferedImage img = itemManager.getImage(herbId);
            herbImages.add(img);
        }
        log.info(herbImages.size() + "");
    }

    private void addNamesToList(){
        for(Integer itemID : herbIDs){
            clientThread.invokeLater(()-> herbNames.add(itemManager.getItemComposition(itemID).getName()));
        }

    }

    private void addNamesToPanel(Container container){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;


        gbc.gridx = 1;
        gbc.gridy = 0;
        for(String itemName : herbNames){
                JLabel newLabel = new JLabel();
                newLabel.setText(itemName);
                container.add(newLabel, gbc);
                gbc.gridy++;
                log.info(itemName);
        }
    }


    private void addItemImagesToPanel(Container container){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        for(AsyncBufferedImage image : herbImages){
            JLabel newLabel = new JLabel();
            newLabel.setIcon(new ImageIcon(image));
            container.add(newLabel, gbc);
            gbc.gridy++;
        }
    }


}
