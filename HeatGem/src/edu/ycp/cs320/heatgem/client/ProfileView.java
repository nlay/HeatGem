package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.NumberLabel;

import edu.ycp.cs320.heatgem.shared.UserProfile;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class ProfileView extends Composite {

	private Image UserFace;
	private NumberLabel<Integer> highScoreLabel;

	private String username;
	private String email;
	private int score;
	private int losses;
	private int wins;

	private NumberLabel<Integer> lossesLabel;
	private NumberLabel<Integer> winsLabel;
	private Label nameProfileLabel;
	private Label emailProfileLabel;
	private UserProfile model;
	
	private Image namepic;
	private Image emailpic;
	private Image scorepic;
	private Image winspic;
	private Image lossespic;

	/**
	 * @param args
	 */
	public ProfileView() {
		activate();
		
		//Stop music 
		HeatGem.StopMoscola();
		HeatGem.StopBabcock();
		HeatGem.StopHovemeyer();
		HeatGem.StopMusic();
		HeatGem.StopLeaderboards();
		HeatGem.PlayProfile();
				
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("568px", "472px");

		Button btnNewButton_1 = new Button("Delete Profile");
		btnNewButton_1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				deleteUser(username);
				LoginView view = new LoginView();
				HeatGem.setView(view);
				
			}
		});
		layoutPanel.add(btnNewButton_1);
		layoutPanel.setWidgetLeftWidth(btnNewButton_1, 639.0, Unit.PX, 122.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(btnNewButton_1, 371.0, Unit.PX, 30.0, Unit.PX);

		//Image image = new Image("Defeat.png");
		UserFace = HeatGem.getImage("Defeat.png");

		UserFace.setAltText("you");
		layoutPanel.add(UserFace);
		layoutPanel.setWidgetLeftWidth(UserFace, 600.0, Unit.PX, 179.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(UserFace, 80.0, Unit.PX, 197.0, Unit.PX);

		nameProfileLabel = new Label("NameHere");
		nameProfileLabel.setStyleName("gwt-Label-name");
		layoutPanel.add(nameProfileLabel);
		layoutPanel.setWidgetLeftWidth(nameProfileLabel, 201.0, Unit.PX, 131.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(nameProfileLabel, 47.0, Unit.PX, 30.0, Unit.PX);

		emailProfileLabel = new Label("EmailHere");
		emailProfileLabel.setStyleName("gwt-Label-name");
		layoutPanel.add(emailProfileLabel);
		layoutPanel.setWidgetLeftWidth(emailProfileLabel, 201.0, Unit.PX, 131.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(emailProfileLabel, 124.0, Unit.PX, 30.0, Unit.PX);
		
		highScoreLabel = new NumberLabel<Integer>();
		highScoreLabel.setStyleName("gwt-Label-name");
		layoutPanel.add(highScoreLabel);
		layoutPanel.setWidgetLeftWidth(highScoreLabel, 201.0, Unit.PX, 131.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(highScoreLabel, 204.0, Unit.PX, 30.0, Unit.PX);

		winsLabel = new NumberLabel<Integer>();
		winsLabel.setStyleName("gwt-Label-name");
		layoutPanel.add(winsLabel);
		layoutPanel.setWidgetLeftWidth(winsLabel, 201.0, Unit.PX, 131.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(winsLabel, 274.0, Unit.PX, 30.0, Unit.PX);
		
		lossesLabel = new NumberLabel<Integer>();
		lossesLabel.setStyleName("gwt-Label-name");
		layoutPanel.add(lossesLabel);
		layoutPanel.setWidgetLeftWidth(lossesLabel, 201.0, Unit.PX, 131.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lossesLabel, 351.0, Unit.PX, 30.0, Unit.PX);
		
		namepic = new Image();
		layoutPanel.add(namepic);
		layoutPanel.setWidgetLeftWidth(namepic, 24.0, Unit.PX, 160.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(namepic, 35.0, Unit.PX, 60.0, Unit.PX);
		namepic.setUrl(GWT.getModuleBaseForStaticFiles() + "name.png");

		emailpic = new Image();
		layoutPanel.add(emailpic);
		layoutPanel.setWidgetLeftWidth(emailpic, 24.0, Unit.PX, 160.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(emailpic, 110.0, Unit.PX, 60.0, Unit.PX);
		emailpic.setUrl(GWT.getModuleBaseForStaticFiles() + "email.png");

		scorepic = new Image();
		layoutPanel.add(scorepic);
		layoutPanel.setWidgetLeftWidth(scorepic, 24.0, Unit.PX, 160.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(scorepic, 185.0, Unit.PX, 60.0, Unit.PX);
		scorepic.setUrl(GWT.getModuleBaseForStaticFiles() + "score.png");

		winspic = new Image();
		layoutPanel.add(winspic);
		layoutPanel.setWidgetLeftWidth(winspic, 24.0, Unit.PX, 160.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(winspic, 260.0, Unit.PX, 60.0, Unit.PX);
		winspic.setUrl(GWT.getModuleBaseForStaticFiles() + "wins.png");

		lossespic = new Image();
		layoutPanel.add(lossespic);
		layoutPanel.setWidgetLeftWidth(lossespic, 24.0, Unit.PX, 160.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lossespic, 335.0, Unit.PX, 60.0, Unit.PX);
		lossespic.setUrl(GWT.getModuleBaseForStaticFiles() + "losses.png");

	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setLosses(int losses) {
		this.losses = losses;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void deleteUser(final String username) {
		RPC.userService.deleteUserAccount(username,  new AsyncCallback <Boolean>() {
			
			@Override
			public void onFailure(Throwable caught) {
				// show error message
				System.out.println("Could not communicate with server?");
			}

			@Override
			public void onSuccess(Boolean result) {
				// TODO Auto-generated method stub
				if (result == true) {
					System.out.println("Profile was not deleted!");
				} else {
					System.out.println("Profile Deleted!");
				}
			}
		});
	}

	public void activate() {

		RPC.userService.getUserProfile(username, new AsyncCallback <UserProfile>() {

			@Override
			public void onFailure(Throwable caught) {
				// show error message
				System.out.println("Could not communicate with server?");
			}

			@Override
			public void onSuccess(UserProfile result) {
					model = result;
					update();
			}
		});

	}

	protected void update() {
		// Use values in model object to update UI components
		nameProfileLabel.setText(username);
		emailProfileLabel.setText(email);
		highScoreLabel.setValue(score);
		lossesLabel.setValue(losses);
		winsLabel.setValue(wins);
	}
}
