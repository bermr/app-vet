package com.app;


import static com.codename1.ui.CN.*;

import com.codename1.charts.compat.Paint.Style;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;


public class AppVet {

    private Form login;
    private Form user;
    //private Login login;
    public Resources theme;

    public void init(Object context) {
        updateNetworkThreadCount(2);
        theme = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);
    }
    
    public void start() {
    	
    	login = new Form("app-vet");
    	login.setLayout(new BorderLayout());
        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setUIID("ContainerWithPadding");
        
      /*Image logo = theme.getImage("dog.png");
        Label l = new Label(logo);
        Container flow = new Container(new FlowLayout(Component.CENTER));
        flow.addComponent(l);
        center.addComponent(flow);*/

        final TextField username = new TextField();
        username.setHint("Usuário");
        final TextField pass = new TextField();
        pass.setHint("Senha");
        pass.setConstraint(TextField.PASSWORD);

        center.addComponent(username);
        center.addComponent(pass);

        Button signIn = new Button("Entrar");
        signIn.addActionListener(evt -> {
		    if (username.getText().length() == 0 || pass.getText().length() == 0) {
		        return;
		    }
		    createUserForm();
		});
        center.addComponent(signIn);
        login.addComponent(BorderLayout.CENTER, center);
        login.show();
    }
    
    private void createUserForm() {
    	user = new Form("app-vet");
    	
    	Toolbar tb = user.getToolbar();
        Image icon = theme.getImage("dog.png"); 
        Container topBar = BorderLayout.east(new Label(icon));
        topBar.add(BorderLayout.SOUTH, new Label("Menu", "SidemenuTagline")); 
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);
 
        //user.revalidate();
        
        tb.addMaterialCommandToSideMenu("Novidades", FontImage.MATERIAL_HOME, e -> {showNews(user);}); 
        tb.addMaterialCommandToSideMenu("Meus Pets", FontImage.MATERIAL_WEB, e -> {showMyPets(user);});
        tb.addMaterialCommandToSideMenu("Autorizações", FontImage.MATERIAL_NOTE, e -> {showAuth(user);});
        tb.addMaterialCommandToSideMenu("Sobre", FontImage.MATERIAL_INFO, e -> {showAbout(user);});

        user.addComponent(new Label(""));
        user.show();
    
        showNews(user);
    }
    
    private void showNews(Form hi) {
    	Container news = createNewsContainer();
		hi.getContentPane().replace(hi.getContentPane().getComponentAt(0), news, CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 300));
	}
    
    private Container createNewsContainer(){
    	 Container n = BorderLayout.center(new Label());
    	 n.setScrollableY(true);
    	 
    	 Story s = new Story("NOVIDADE", "No dia 21 de agosto a veterinária estará fazendo tosa grátis de cães e gatos."
    	 		+ " Aguardamos vocês", "Novo evento");
    	 Component new1 = createNewsComponent(s);
         n.addComponent(BorderLayout.CENTER, new1);            
    	  
    	 return n;
    }
    
    private Component createNewsComponent(Story s) {
        Container mb = new Container(new BorderLayout());
    	Container box = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    	
    	Button title = new Button(s.getText());
    	title.setUIID("Title");
    	Label highlights = new Label(s.getHighlights());
    	highlights.setUIID("Body");
    	TextArea details = new TextArea(s.getFullDescription());
    	details.setUIID("Body");
    	box.addComponent(title);
    	box.addComponent(highlights);
    	mb.setLeadComponent(title);
    	
    	mb.add(BorderLayout.SOUTH, box);
    	    	
    	title.addActionListener((e) -> {
            if(highlights.getParent() != null) {
                box.removeComponent(highlights);
                box.addComponent(details);
            } else {
                box.removeComponent(details);
                box.addComponent(highlights);
            }
            mb.getParent().animateLayout(300);
    	});
    	   
    	return mb;
    }
    
	
	private void showMyPets(Form hi) {
    	hi.getContentPane().replace(hi.getContentPane().getComponentAt(0), createMyPets(), CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 300));
	}
    
    private Container createMyPets() {
        Container myPets = BorderLayout.center(new Label());
        
        Container mp = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        myPets.addComponent(BorderLayout.CENTER, mp);
        
        //mp.setScrollableY(true);
        
        Label l1 = new Label("Cachorro 1");
        Label l2 = new Label("Cachorro 2");
     
        mp.add(l1);
        mp.add(l2);
        
        return myPets;
}

    private void showAbout(Form hi) {
    	hi.getContentPane().replace(hi.getContentPane().getComponentAt(0), createAbout(), CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 300));
	}

	private Component createAbout() {
		Container about = BorderLayout.center(new Label());
        
        Container ab = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        about.addComponent(BorderLayout.CENTER, ab);
        
        ab.setScrollableY(true);
        
        Label l1 = new Label("Veterinaria X");
        Label l2 = new Label("3551-3551, Rua Tal, Lavras");
     
        ab.add(l1);
        ab.add(l2);
        
        return about;
	}

	private void showAuth(Form hi) {
		hi.getContentPane().replace(hi.getContentPane().getComponentAt(0), createAuth(), CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 300));
	}

	private Component createAuth() {
		Container auth = BorderLayout.center(new Label());
        
        Container au = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        auth.addComponent(BorderLayout.CENTER, au);
        
        au.setScrollableY(true);
        
        Label l1 = new Label("Autoriza castrar o gato x?");
     
        au.add(l1);
        
        return auth;
	}
    
    public void stop() {
    	login = getCurrentForm();
        if(login instanceof Dialog) {
            ((Dialog)login).dispose();
            login = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}
