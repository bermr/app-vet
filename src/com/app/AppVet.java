package com.app;


import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class AppVet {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });        
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        Form hi = new Form("app-vet");

        Toolbar tb = hi.getToolbar();
        Image icon = theme.getImage("icon.png"); 
        Container topBar = BorderLayout.east(new Label(icon));
        topBar.add(BorderLayout.SOUTH, new Label("Opções...", "SidemenuTagline")); 
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);

        tb.addMaterialCommandToSideMenu("Novidades", FontImage.MATERIAL_HOME, e -> {}); 
        tb.addMaterialCommandToSideMenu("Meus Pets", FontImage.MATERIAL_WEB, e -> {});
        tb.addMaterialCommandToSideMenu("Autorizações", FontImage.MATERIAL_INFO, e -> {});
        tb.addMaterialCommandToSideMenu("Configurações", FontImage.MATERIAL_SETTINGS, e -> {});

        hi.addComponent(new Label(""));
        hi.show();
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}