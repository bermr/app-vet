package com.app;

import static com.codename1.ui.CN.*;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

public class AppVet {
	private Form login;
	private Form user;
	private Form signup;
	private int fontSize;
	// private int fontSize2;
	// private Login login;
	public Resources theme;

	public void init(Object context) {
		updateNetworkThreadCount(2);
		theme = UIManager.initFirstTheme("/theme");
		Toolbar.setGlobalToolbar(true);
		this.fontSize = Display.getInstance().convertToPixels(8);
	}

	public void start() {
		login = new Form();
		Label title = new Label("Login");
		login.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		login.add(title);
		Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Font f = Font.createTrueTypeFont("native", "Pacifico.ttf").derive(fontSize, Font.STYLE_PLAIN);
		title.getUnselectedStyle().setFont(f);
		title.getSelectedStyle().setFont(f);

		/*
		 * Image logo = theme.getImage("dog.png"); Label l = new Label(logo); Container
		 * flow = new Container(new FlowLayout(Component.CENTER)); flow.addComponent(l);
		 * center.addComponent(flow);
		 */

		final TextField username = new TextField();
		username.setHint("Usuário");
		username.setAlignment(LEFT);
		username.setUIID("Login");

		final TextField pass = new TextField();
		pass.setHint("Senha");
		pass.setAlignment(LEFT);
		pass.setUIID("Login");
		pass.setConstraint(TextField.PASSWORD);
		center.addComponent(username);
		center.addComponent(pass);

		Button signIn = new Button("Entrar");
		Button signUp = new Button("Cadastrar-se");
		signUp.setUIID("SignUpButton");
		signIn.setUIID("SignInButton");

		signUp.addActionListener(evt -> {
			createSignUpForm();
		});

		signIn.addActionListener(evt -> {
			/*
			 * if (username.getText().length() == 0 || pass.getText().length() == 0) {
			 * return; }
			 */
			createUserForm();
		});
		
		center.addComponent(signIn);
		center.addComponent(signUp);
		Image img = theme.getImage("logo.png");
		img = img.scaledLargerRatio(Math.round(Display.getInstance().getDisplayHeight() / 3),
				Math.round(Display.getInstance().getDisplayWidth() / 2));
		Label logo = new Label(img);
		login.addComponent(logo);
		login.addComponent(center);
		login.show();
	}

	private void createSignUpForm() {
		signup = new Form();
		signup.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		Label title = new Label("Cadastro");
		Font f = Font.createTrueTypeFont("native", "Pacifico.ttf").derive(fontSize, Font.STYLE_PLAIN);
		title.getUnselectedStyle().setFont(f);
		title.getSelectedStyle().setFont(f);
		Label l = new Label("Preencha os campos a seguir para criar uma nova conta");
		l.setUIID("TitleSignUp");
		l.setAutoSizeMode(true);
		final TextField name = new TextField();
		name.setHint("Nome Completo");
		name.setUIID("Login");
		final TextField password = new TextField();
		password.setHint("Senha");
		password.setUIID("Login");
		password.setConstraint(TextField.PASSWORD);
		final TextField password2 = new TextField();
		password2.setHint("Confirmar Senha");
		password2.setUIID("Login");
		password2.setConstraint(TextField.PASSWORD);
		final TextField cpf = new TextField();
		cpf.setHint("CPF");
		cpf.setUIID("Login");
		Button back = new Button("Voltar");
		back.setUIID("SignUpButton");
		Button create = new Button("Criar");
		create.setUIID("SignInButton");
		create.addActionListener(evt -> {
			// login.showBack();
		});
		back.addActionListener(evt -> {
			login.showBack();
		});
		signup.add(title);
		signup.add(l);
		signup.add(name);
		signup.add(password);
		signup.add(password2);
		signup.add(cpf);
		signup.add(create);
		signup.add(back);
		signup.revalidate();
		signup.show();
	}

	private void createUserForm() {
		user = new Form();
		user.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

		Toolbar tb = user.getToolbar();
		tb.setScrollOffUponContentPane(true);
		Image img = theme.getImage("logo.png");
		img = img.scaledLargerRatio(Math.round(Display.getInstance().getDisplayHeight() / 3),
				Math.round(Display.getInstance().getDisplayWidth() / 2));
		Label logo = new Label(img);
		tb.addComponentToSideMenu(logo);
		Container topBar = BorderLayout.east(new Label(""));

		Label menu = new Label("Menu");
		topBar.add(BorderLayout.SOUTH, menu);
		Font f = Font.createTrueTypeFont("native", "Pacifico.ttf").derive(fontSize, Font.STYLE_PLAIN);
		menu.getUnselectedStyle().setFont(f);
		menu.getSelectedStyle().setFont(f);
		tb.addComponentToSideMenu(topBar);

		// user.revalidate();

		tb.addMaterialCommandToSideMenu("Novidades", FontImage.MATERIAL_NEW_RELEASES, e -> {
			showNews(user);
		});
		tb.addMaterialCommandToSideMenu("Meus Pets", FontImage.MATERIAL_PETS, e -> {
			showMyPets(user);
		});
		tb.addMaterialCommandToSideMenu("Autorizações", FontImage.MATERIAL_INBOX, e -> {
			showAuth(user);
		});
		tb.addMaterialCommandToSideMenu("Sobre", FontImage.MATERIAL_INFO, e -> {
			showAbout(user);
		});
		tb.addMaterialCommandToSideMenu("Sair", FontImage.MATERIAL_EXIT_TO_APP, e -> {
			login.showBack();
		});

		user.addComponent(new Label(""));
		user.show();

		showNews(user);
	}

	private void showNews(Form hi) {
		Container news = createNewsContainer();
		hi.getContentPane().replace(hi.getContentPane().getComponentAt(0), news,
				CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 300));
	}

	private Container createNewsContainer() {
		Container n = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Label title = new Label("Novidades");
		n.add(title);
		Font f = Font.createTrueTypeFont("native", "Pacifico.ttf").derive(fontSize, Font.STYLE_PLAIN);
		title.getUnselectedStyle().setFont(f);
		title.getSelectedStyle().setFont(f);

		// n.setScrollableY(true);

		Story s = new Story("TOSA GRATIS",
				"No dia 21 de agosto a veterinária estará fazendo tosa grátis de cães e gatos." + " Aguardamos vocês.",
				"Novo evento");
		Component new1 = createNewsComponent(s);

		Story s1 = new Story("NOVIDADE", "Novo app já está disponível na google play! Venha conferir.", "Novo evento");
		Component new2 = createNewsComponent(s1);

		n.add(new1);
		n.add(new2);
		
		return n;
	}

	private Component createNewsComponent(Story s) {
		Container mb = new Container(new BorderLayout());

		Button title = new Button();
		Label highlights = new Label(s.getText());
		highlights.setUIID("Highlights");

		TextArea details = new TextArea(s.getFullDescription());
		details.setUIID("Details");
		
		mb.addComponent(BorderLayout.CENTER, title);
		mb.addComponent(BorderLayout.CENTER, highlights);
		mb.setLeadComponent(title);

		title.addActionListener((e) -> {
			if (highlights.getParent() != null) {
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
		hi.getContentPane().replace(hi.getContentPane().getComponentAt(0), createMyPets(),
				CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 300));
	}

	private Container createMyPets() {
		Container myPets = new Container(new BoxLayout((BoxLayout.Y_AXIS)));
		Label title = new Label("Meus Pets");
		myPets.add(title);
		Font f = Font.createTrueTypeFont("native", "Pacifico.ttf").derive(fontSize, Font.STYLE_PLAIN);
		title.getUnselectedStyle().setFont(f);
		title.getSelectedStyle().setFont(f);

		Container mp = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		myPets.addComponent(mp);

		// mp.setScrollableY(true);
		/*
		 * Button b1 = new Button("Conectar"); b1.addActionListener((e) -> {
		 * Display.getInstance().execute("http://www.google.com"); });
		 */

		Label l1 = new Label("Cachorro 1");
		Label l2 = new Label("Cachorro 2");

		// mp.add(b1);
		mp.add(l1);
		mp.add(l2);

		return myPets;
	}

	private void showAbout(Form hi) {
		hi.getContentPane().replace(hi.getContentPane().getComponentAt(0), createAbout(),
				CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 300));
	}

	private Component createAbout() {
		Container about = new Container(new BoxLayout((BoxLayout.Y_AXIS)));
		Label title = new Label("Sobre");
		about.add(title);
		Font f = Font.createTrueTypeFont("native", "Pacifico.ttf").derive(fontSize, Font.STYLE_PLAIN);
		title.getUnselectedStyle().setFont(f);
		title.getSelectedStyle().setFont(f);

		Container ab = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		about.addComponent(ab);

		ab.setScrollableY(true);

		Label l1 = new Label("Veterinaria X");
		Label l2 = new Label("3551-3551, Rua Tal, Lavras");

		ab.add(l1);
		ab.add(l2);

		return about;
	}

	private void showAuth(Form hi) {
		hi.getContentPane().replace(hi.getContentPane().getComponentAt(0), createAuth(),
				CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 300));
	}

	private Component createAuth() {
		Container auth = new Container(new BoxLayout((BoxLayout.Y_AXIS)));
		Label title = new Label("Autorizações");
		auth.add(title);
		Font f = Font.createTrueTypeFont("native", "Pacifico.ttf").derive(fontSize, Font.STYLE_PLAIN);
		title.getUnselectedStyle().setFont(f);
		title.getSelectedStyle().setFont(f);
		Container au = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		auth.addComponent(au);

		au.setScrollableY(true);

		Label l1 = new Label("Autoriza castrar o gato x?");

		au.add(l1);

		return auth;
	}

	public void stop() {
		login = getCurrentForm();
		if (login instanceof Dialog) {
			((Dialog) login).dispose();
			login = getCurrentForm();
		}
	}

	public void destroy() {
	}

}
