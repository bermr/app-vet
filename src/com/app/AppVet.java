package com.app;


import static com.codename1.ui.CN.*;

import com.codename1.charts.util.ColorUtil;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.diamonddevgroup.utils.Helper;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.geom.Dimension;
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
		    /*if (username.getText().length() == 0 || pass.getText().length() == 0) {
		        return;
		    }*/
		    createUserForm();
		});
        center.addComponent(signIn);
        login.addComponent(BorderLayout.CENTER, center);
        login.show();
    }
    
    private void createUserForm() {	
    	user = new Form("app-vet");
    	user.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    	
    	Toolbar tb = user.getToolbar();
        Container topBar = BorderLayout.east(new Label(""));
        topBar.add(BorderLayout.SOUTH, new Label("Menu", "SidemenuTagline")); 
        topBar.setUIID("SideMenu");
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
    	 Container n = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    	 
    	 //n.setScrollableY(true);
    	 
    	 Story s = new Story("NOVIDADE", "No dia 21 de agosto a veterinária estará fazendo tosa grátis de cães e gatos."
    	 		+ " Aguardamos vocês.", "Novo evento");
    	 Component new1 = createNewsComponent(s);
  	 
         Story s1 = new Story("NOVIDADE", "Novo app já está disponível na google play! Venha conferir.", "Novo evento");
     	 Component new2 = createNewsComponent(s1);
     
     	 n.add(new1);
     	 n.add(new2); 
    	  
        /* Label myLabel = new Label("MASSA");
         new Helper(myLabel).pa1().ma0().textGreen().bgGrey();
         n.add(myLabel);*/
         
    	 return n;
    }
    
    private Component createNewsComponent(Story s) {
    	int fontSize = Display.getInstance().convertToPixels(6);
    	int fontSize2 = Display.getInstance().convertToPixels(3);
    	Font f = Font.createTrueTypeFont("native","Roboto-Regular.ttf").derive(fontSize, Font.STYLE_PLAIN);
    	Font f2 = Font.createTrueTypeFont("native","Roboto-Regular.ttf").derive(fontSize2, Font.STYLE_PLAIN);
    	
        Container mb = new Container(new BorderLayout());
        
        mb.getAllStyles().setBorder(Border.createGrooveBorder(1));
        mb.getAllStyles().setBackgroundType((byte) 1);
        mb.getAllStyles().setBgTransparency(255);
        mb.getAllStyles().setBgColor(0xe1e1e1);
        
        
    	Button title = new Button();
    	Label highlights = new Label(s.getText());
    	highlights.getUnselectedStyle().setFont(f);
    	
    	TextArea details = new TextArea(s.getFullDescription());
    	details.setUIID("DetailsTextArea");
    	
    	details.getUnselectedStyle().setFont(f2);
    	mb.addComponent(BorderLayout.CENTER, title);
    	mb.addComponent(BorderLayout.CENTER, highlights);
    	mb.setLeadComponent(title);
    	
    	    	
    	title.addActionListener((e) -> {
            if(highlights.getParent() != null) {
                mb.removeComponent(highlights);
                mb.addComponent(BorderLayout.CENTER, details);
            } else {
                mb.removeComponent(details);
                mb.addComponent(BorderLayout.CENTER, highlights);
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
        Button b1 = new Button("Conectar");
        b1.addActionListener((e) -> {
            Display.getInstance().execute("http://192.168.1.114:10080");
        });
        
        Label l1 = new Label("Cachorro 1");
        Label l2 = new Label("Cachorro 2");
     
        mp.add(b1);
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
