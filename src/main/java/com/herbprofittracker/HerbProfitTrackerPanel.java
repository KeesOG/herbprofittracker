package com.herbprofittracker;

import net.runelite.api.Item;
import net.runelite.api.ItemID;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.util.AsyncBufferedImage;
import lombok.extern.slf4j.Slf4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;


public class HerbProfitTrackerPanel extends PluginPanel {


    private static final Logger log = LoggerFactory.getLogger(HerbProfitTrackerPanel.class);
    @Inject
    private ItemManager itemManager;

    //Initialize ArrayList with id's of all grimy herbs.
    private ArrayList<Integer> herbIDs = new ArrayList<>(
            Arrays.asList(199, 201, 203, 205, 207, 209, 211, 213, 215, 217, 219, 2485, 3049, 3051)
    );

    private ArrayList<AsyncBufferedImage> herbImages = new ArrayList<>();

    public void init(){
        //Initializing JFrame and setting its layout.
        JFrame jFrame = new JFrame("Herb run profit tracker");
        jFrame.setLayout(new GridBagLayout());
        Container container = jFrame.getContentPane();

        //Get item images and put them in ArrayList.
        addItemImagesToList();
        addItemImagesToPanel(container);
        addNamesToPanel(container);

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

    private void addNamesToPanel(Container container){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        for(Integer itemID : herbIDs){
            JLabel newLabel = new JLabel();

        }
    }


    private void addItemImagesToPanel(Container container){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = -0.3;
        gbc.weighty = 1;


        gbc.gridx = 1;
        gbc.gridy = 0;
        for(AsyncBufferedImage image : herbImages){
            JLabel newLabel = new JLabel();
            image.onLoaded(()-> newLabel.setIcon(new ImageIcon(image)));
            container.add(newLabel, gbc);
            gbc.gridy++;
        }
    }



//            for(Integer itemID : herbIDs){
//        SwingUtilities.invokeLater(() -> {
//            log.info(itemManager.getItemComposition(itemID).getName());
//            JLabel newLabel = new JLabel();
//            newLabel.setText(itemManager.getItemComposition(itemID).getName());
//            container.add(newLabel, gbc);
//            gbc.gridy++;
//        });
//    }
}
