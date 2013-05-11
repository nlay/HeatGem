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

	/**
	 * @param args
	 */
	public ProfileView() {
		activate();
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

		Label lblProfileView = new Label("Profile Page");
		layoutPanel.add(lblProfileView);
		layoutPanel.setWidgetLeftWidth(lblProfileView, 244.0, Unit.PX, 99.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblProfileView, 23.0, Unit.PX, 18.0, Unit.PX);

		//Image image = new Image("Defeat.png");
		UserFace = HeatGem.getImage("Defeat.png");

		UserFace.setAltText("you");
		layoutPanel.add(UserFace);
		layoutPanel.setWidgetLeftWidth(UserFace, 600.0, Unit.PX, 179.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(UserFace, 80.0, Unit.PX, 197.0, Unit.PX);

		Label lblName = new Label("Name");
		layoutPanel.add(lblName);
		layoutPanel.setWidgetLeftWidth(lblName, 20.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblName, 80.0, Unit.PX, 18.0, Unit.PX);
		
		nameProfileLabel = new Label("NameHere");
		layoutPanel.add(nameProfileLabel);
		layoutPanel.setWidgetLeftWidth(nameProfileLabel, 105.0, Unit.PX, 260.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(nameProfileLabel, 80.0, Unit.PX, 18.0, Unit.PX);

		Label lblEmail = new Label("Email");
		layoutPanel.add(lblEmail);
		layoutPanel.setWidgetLeftWidth(lblEmail, 20.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblEmail, 120.0, Unit.PX, 18.0, Unit.PX);

		emailProfileLabel = new Label("EmailHere");
		layoutPanel.add(emailProfileLabel);
		layoutPanel.setWidgetLeftWidth(emailProfileLabel, 105.0, Unit.PX, 260.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(emailProfileLabel, 120.0, Unit.PX, 18.0, Unit.PX);
		
		Label lblExperience = new Label("HighScore");
		layoutPanel.add(lblExperience);
		layoutPanel.setWidgetLeftWidth(lblExperience, 20.0, Unit.PX, 70.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblExperience, 160.0, Unit.PX, 18.0, Unit.PX);
		
		highScoreLabel = new NumberLabel<Integer>();
		layoutPanel.add(highScoreLabel);
		layoutPanel.setWidgetLeftWidth(highScoreLabel, 105.0, Unit.PX, 131.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(highScoreLabel, 160.0, Unit.PX, 18.0, Unit.PX);

		Label lblWins = new Label("Wins");
		layoutPanel.add(lblWins);
		layoutPanel.setWidgetLeftWidth(lblWins, 20.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblWins, 200.0, Unit.PX, 18.0, Unit.PX);

		winsLabel = new NumberLabel<Integer>();
		layoutPanel.add(winsLabel);
		layoutPanel.setWidgetLeftWidth(winsLabel, 105.0, Unit.PX, 131.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(winsLabel, 200.0, Unit.PX, 18.0, Unit.PX);
		
		Label lblLosses = new Label("Losses");
		layoutPanel.add(lblLosses);
		layoutPanel.setWidgetLeftWidth(lblLosses, 20.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblLosses, 240.0, Unit.PX, 18.0, Unit.PX);

		lossesLabel = new NumberLabel<Integer>();
		layoutPanel.add(lossesLabel);
		layoutPanel.setWidgetLeftWidth(lossesLabel, 105.0, Unit.PX, 131.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lossesLabel, 240.0, Unit.PX, 18.0, Unit.PX);
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
