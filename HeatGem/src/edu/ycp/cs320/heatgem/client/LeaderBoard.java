package edu.ycp.cs320.heatgem.client;

//import java.util.List;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Label;

import edu.ycp.cs320.heatgem.shared.UserProfile;

public class LeaderBoard extends Composite {
	private UserProfile[] highScoreList = new UserProfile[10];
	int size;
	private String username;
	private FlexTable t = new FlexTable();
	
	public LeaderBoard(){

	    updateHighScoreList();
	    
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		t.setCellPadding(4);
		
		layoutPanel.add(t);
		layoutPanel.setWidgetLeftWidth(t, 32.0, Unit.PX, 400.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(t, 14.0, Unit.PX, 400.0, Unit.PX);
		
		
	    t.setText(0, 0, "Username");
	    t.setText(0, 1, "High Score");
	    t.setText(0, 2, "Wins");
	    t.setText(0, 3, "Losses");
	    t.setBorderWidth(1);
	    

	    
	    
	    // DEBUG testing values to be deleted later
//	    Label lbltestHigh = new Label("testHigh");
//	    layoutPanel.add(lbltestHigh);
//	    layoutPanel.setWidgetLeftWidth(lbltestHigh, 20.0, Unit.PX, 56.0, Unit.PX);
//	    layoutPanel.setWidgetTopHeight(lbltestHigh, 88.0, Unit.PX, 18.0, Unit.PX);
//	    
//	    Label lbltestLow = new Label("testLow");
//	    layoutPanel.add(lbltestLow);
//	    layoutPanel.setWidgetLeftWidth(lbltestLow, 20.0, Unit.PX, 56.0, Unit.PX);
//	    layoutPanel.setWidgetTopHeight(lbltestLow, 119.0, Unit.PX, 18.0, Unit.PX);
		
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	// DEBUG for those not named Nick, ignore this function for now
	private void updateHighScoreList() {
		RPC.userService.updateLeaderboard(new AsyncCallback<UserProfile[]>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("Leaderboard Update FAIL");
			}

			@Override
			public void onSuccess(UserProfile[] result) {
				highScoreList = result;
				
				//TODO: Sort here by score before setting labels
				for (int j = 0; j < highScoreList.length; j++) {
					for (int i = 0; i < highScoreList.length-1; i++) {
						
						int score1 = highScoreList[i].getHighScore();
						int score2 = highScoreList[i+1].getHighScore();
						//System.out.println("Pass " + j + ", " + i + ", score1 = " + score1 + ", score2 = " + score2);
						if (score1 < score2) {
							UserProfile temp = new UserProfile();
							temp = highScoreList[i];
							highScoreList[i] = highScoreList[i+1];
							highScoreList[i+1] = temp;
						}
						
					}
				}
				
				for (int i = 0; i < highScoreList.length; i++) {
				    t.setText(i+1, 0, "");
					Integer score = highScoreList[i].getHighScore();
					Integer wins = highScoreList[i].getWins();
					Integer losses = highScoreList[i].getLosses();
					
					t.setText(i+1, 0, highScoreList[i].getName());
					t.setText(i+1, 1, score.toString());
					t.setText(i+1, 2, wins.toString());
					t.setText(i+1, 3, losses.toString());
					
				}
			    
			}
		});
	}
}
