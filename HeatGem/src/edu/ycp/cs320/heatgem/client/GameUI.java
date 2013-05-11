package edu.ycp.cs320.heatgem.client;


import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;

import com.google.gwt.event.dom.client.MouseMoveHandler;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.ImageElement;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;


import edu.ycp.cs320.heatgem.shared.Battle;
import edu.ycp.cs320.heatgem.shared.Game;
import edu.ycp.cs320.heatgem.shared.Logic;
import edu.ycp.cs320.heatgem.shared.Player;
import edu.ycp.cs320.heatgem.shared.Score;
import edu.ycp.cs320.heatgem.shared.UserProfile;

public class GameUI extends Composite {

	public int MouseX;
	public int MouseY;
	private int gamestate;
	private Canvas buffer;
	private Context2d bufCtx;
	private Canvas canvas;
	private Context2d ctx;
	private Timer timer;
	private Image background, GameWin, GameLoss, HomePage;
	private Image Layer1, Layer2, Layer3, Layer4, Layer5, Layer6, Layer7, Layer8, Layer9, Layer10, Layer11, Layer12, Layer13, Layer14,Layer15;
	private Image PlayerFace;
	private Image EnemyFace;
	private Image Attack;
	private Image AttackSelected;
	private Image Heal;
	private Image HealSelected;
	private Image BattleH, BattleHSel, BattleM, BattleMSel, BattleB, BattleBSel;
	private Image Defeat;
	private Image Victory;
	private Image MediumHealth, LowHealth;
	private Image BFull, BMed, BLow, BWin;
	private Image MFull, MMed, MLow, MWin;
	private Image HFull, HMed, HLow;
	private Player player1;
	private Player player2;
	private Battle BattleState;
	// The game object contains all of the game state data.
	private Game game;
	private int MilliTime;
	private int SecondTime;
	public int TotalTime; 
	private int PScore;
	private Score score;
	private int HealthMove, HealthMove2, PrevHealth = 100, PrevHealth2 = 100;
	private int ChosenPlayer;

	// profile update things
	private String username;
	private UserProfile model;
	private int wins;
	private int losses;
	private boolean victoryBool;

	public GameUI() {
		activate();

		// FocusPanel
		final FocusPanel panel = new FocusPanel();
		// LayoutPanel layoutPanel = new LayoutPanel();
		panel.setSize("800px", "480px");

		// "buffer" canvas
		this.buffer = Canvas.createIfSupported();
		buffer.setSize(Game.Width + "px", Game.Height + "px");
		buffer.setCoordinateSpaceWidth(Game.Width);
		buffer.setCoordinateSpaceHeight(Game.Height);
		this.bufCtx = buffer.getContext2d();

		// The visible canvas
		this.canvas = Canvas.createIfSupported();
		canvas.setSize(Game.Width + "px", Game.Height + "px");
		canvas.setCoordinateSpaceWidth(Game.Width);
		canvas.setCoordinateSpaceHeight(Game.Height);
		this.ctx = canvas.getContext2d();
		panel.add(canvas);

		initWidget(panel);

		this.timer = new Timer() {
			@Override
			public void run() {
				Draw();
				if (gamestate == 1) {
					if (BattleState.battleState() == 0) {
						MilliTime++; // Framerate at 10 frames per second
						TotalTime++;
						if (MilliTime % 100 == 0) { //Increment timer by
									// seconds ONLY if game is
									// in session
							SecondTime++;
							MilliTime = 0;
						}
					} else { // Get Score
						PScore = (int) score.getScore(TotalTime,  player1);
						// Update user's score
						model.setWins(wins);
						model.setLosses(losses);
						
						int previousHighScore = model.getHighScore();
						if (previousHighScore < PScore) {
							model.setHighScore(PScore);
						} else { }
						//work-around to make timer NOT endlessly increment data in database
						//but it does still endlessly send the correct data
						if (victoryBool == true) {
							wins--;
						} else if (victoryBool == false) {
							losses--;
						}
						
						updateScore();
					}
				}
				else { 
				}	// Do nothing
			}
		};
		


		canvas.addMouseMoveHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {

				MouseX = event.getX();
				MouseY = event.getY();
			}

		});

		canvas.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				if (gamestate == 0) { // Menu
			        if ((MouseX >= 275 && MouseX <= 553)
					            && (MouseY >= 150 && MouseY <= 177)) {
					          ChosenPlayer = 1;
					          gamestate = 1;
			        }
			        else if ((MouseX >= 275 && MouseX <= 556)
			            && (MouseY >= 190 && MouseY <= 217)) {
			          ChosenPlayer = 2;
			          gamestate = 1;
			        }
			        else if ((MouseX >= 250 && MouseX <= 583)
			            && (MouseY >= 230 && MouseY <= 266)) {
			          ChosenPlayer = 3;
						gamestate = 1;
					}
				} else {
					if (BattleState.battleState() == 0) { // If No one has one,
															// allow the game to
															// continue
						if ((MouseX > 380 && MouseX < 455)
								&& (MouseY > 360 && MouseY < 390)) {

							Logic.doBattle(player1, player2);
							//Set localvariable to store and print the player's health change
							HealthMove = PrevHealth - player1.getHealth();
							HealthMove2 = PrevHealth2 - player2.getHealth();
							
							HealthMove *= -1; 
							HealthMove2 *= -1;
							
							PrevHealth = player1.getHealth();
              PrevHealth2 = player2.getHealth();
							
						} else if ((MouseX > 380 && MouseX < 455)
								&& (MouseY > 410 && MouseY < 440)) {

							Logic.doHeal(player1, player2);

				            //Set localvariable to store and print the player's health change
				            HealthMove = PrevHealth - player1.getHealth();
				            HealthMove2 = PrevHealth2 - player2.getHealth();
				            
				            HealthMove *= -1; 
				            HealthMove2 *= -1;
				            
				            PrevHealth = player1.getHealth();
			            	PrevHealth2 = player2.getHealth();

						}
					}
				}
			}
		});

	}

	public void onMouseMove(Widget sender, int x, int y) {
		x = MouseX;
		y = MouseY;
	}

	public void startGame() {
		// get background and sprite images that will be used for painting
		background = HeatGem.getImage("RoughBattle.jpg");
		PlayerFace = HeatGem.getImage("FullHealth.png");
		EnemyFace = HeatGem.getImage("FullHealth.png");
		Attack = HeatGem.getImage("Attack.png");
		Heal = HeatGem.getImage("Heal.png");
		Defeat = HeatGem.getImage("Defeat.png");
		Victory = HeatGem.getImage("Victory.png");
		AttackSelected = HeatGem.getImage("AttackSelected.png");
		HealSelected = HeatGem.getImage("HealSelected.png");
		GameWin = HeatGem.getImage("BattleWin.jpg");
		GameLoss = HeatGem.getImage("BattleLoss.jpg");
		MediumHealth = HeatGem.getImage("YellowHealth.png");
		LowHealth = HeatGem.getImage("LowHealth.png");
		HomePage = HeatGem.getImage("HomepageDif.gif");
		BattleH = HeatGem.getImage("Hovemeyer.png");
		BattleHSel = HeatGem.getImage("HovemeyerSelected.png");
		BattleM = HeatGem.getImage("Moscola.png");
		BattleMSel = HeatGem.getImage("MoscolaSelected.png");
		BattleB = HeatGem.getImage("Babcock.png");
		BattleBSel = HeatGem.getImage("BabcockSelected.png");
		BFull = HeatGem.getImage("BabcockFull.png");
		BMed = HeatGem.getImage("BabcockMedium.png");
		BLow = HeatGem.getImage("BabcockHurt.png");
		BWin = HeatGem.getImage("BabcockWin.png");
		MFull = HeatGem.getImage("MoscolaFull.png");
		MMed = HeatGem.getImage("MoscolaMedium.png");
		MLow = HeatGem.getImage("MoscolaLow.png");
		MWin = HeatGem.getImage("MoscolaWin.png");
		HFull = HeatGem.getImage("HovemeyerFull.png");
		HMed = HeatGem.getImage("HovemeyerMedium.png");
		HLow = HeatGem.getImage("HovemeyerLow.png");


		game = new Game();
		player1 = new Player("Player");
		player2 = new Player("Monster");
		BattleState = new Battle(player1, player2);
		score = new Score();
		gamestate = 0;
		ChosenPlayer = 0;
		// Add a listener for mouse motion.
		// Each time the mouse is moved, clicked, released, etc. the
		// handleMouseMove method
		// will be called.
		timer.scheduleRepeating(1000 / 100); // DeciSeconds
	}

	// protected void handleTimerEvent() {
	// // You should not need to change this method.
	// game.timerTick();
	//
	// }
	

	public void setUsername(String username) {
		//NICK
		this.username = username;
	}
	
	public void setWins(int wins) {
		//NICK
		this.wins = wins;
	}
	
	public void setLosses(int losses) {
		//NICK
		this.losses = losses;
	}

	public void activate() {
		//NICK
		RPC.userService.getUserProfile(username, new AsyncCallback <UserProfile>() {

			@Override
			public void onFailure(Throwable caught) {
				// show error message
				System.out.println("Could not communicate with server?");
			}

			@Override
			public void onSuccess(UserProfile result) {
					model = result;
			}
		});

	}


	protected void updateScore() {
		
		RPC.userService.updateUserProfile(username, model, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("GameUI-updateScore Error");
			}

			@Override
			public void onSuccess(Boolean result) {
				// TODO Auto-generated method stub
			}
			
			
		});
		
	}

	protected void Draw() {

		// Draw home menu
		if (gamestate == 0) {
		    for (int i = 1; i <= 15; i++){
			      bufCtx.drawImage((ImageElement) HomePage.getElement().cast(), 0, 0);
			}
		          //PICK YOUR OPPONENT
		            if ((MouseX >= 275 && MouseX <= 555)
		                && (MouseY >= 150 && MouseY <= 177)) {
		              bufCtx.drawImage((ImageElement) BattleBSel.getElement()
		                  .cast(), 275, 150); // Draw play selected
		            } else {
		              bufCtx.drawImage((ImageElement) BattleB.getElement().cast(), 275,
		                  150); // Draw play
		            }
		            if ((MouseX >= 275 && MouseX <= 546)
		                && (MouseY >= 190 && MouseY <= 217)) {
		              bufCtx.drawImage((ImageElement) BattleMSel.getElement()
		                  .cast(), 275, 190); // Draw play selected
		            } else {
		              bufCtx.drawImage((ImageElement) BattleM.getElement().cast(), 275,
		                  190); // Draw play
		            }
		            if ((MouseX >= 250 && MouseX <= 583)
		                && (MouseY >= 230 && MouseY <= 266)) {
		              bufCtx.drawImage((ImageElement) BattleHSel.getElement()
		                  .cast(), 250, 230); // Draw play selected
		            } else {
		              bufCtx.drawImage((ImageElement) BattleH.getElement().cast(), 250,
		                  230); // Draw play
		            }
		          

		} else {
			if (BattleState.battleState() == 0) {
				// Draw background
				bufCtx.drawImage((ImageElement) background.getElement().cast(),
						0, 0);
				// if (p1Health > 50){
				int player1Health = player1.getHealth();
				if (player1Health > 50) {
					bufCtx.setFillStyle("green");
					// Draw Sprite for character
					bufCtx.drawImage((ImageElement) PlayerFace.getElement()
							.cast(), 50, 200);
				} else if (player1Health <= 50 && player1Health > 25) {
					bufCtx.setFillStyle("yellow");
					// Draw Sprite for character
					bufCtx.drawImage((ImageElement) MediumHealth.getElement()
							.cast(), 50, 200);
				} else {
					bufCtx.setFillStyle("red");
					// Draw Sprite for character
					bufCtx.drawImage((ImageElement) LowHealth.getElement()
							.cast(), 50, 200);
				}
				// Draw PlayerHealth Bar that scales based on health size
				bufCtx.fillRect(30, 430, (double) player1Health * 3, 25);

				int player2Health = player2.getHealth();
				//FullHealth
				if (player2Health > 50) {
					bufCtx.setFillStyle("green");
					// Draw Sprite for Enemy
					          
					          ///
					          /// B appreviation is Babcock, M for Moscola, and H for Hovemeyer
					          ///
					          
					          if(ChosenPlayer == 1){ 
					            bufCtx.drawImage((ImageElement) BFull.getElement()
							.cast(), 580, 100);
					          }
					                    else if(ChosenPlayer == 2){
					                      bufCtx.drawImage((ImageElement) MFull.getElement()
					                          .cast(), 580, 100);
					                      }
					                    else if (ChosenPlayer == 3){
					                      bufCtx.drawImage((ImageElement) HFull.getElement()
					                          .cast(), 580, 100);
					                    }
					                    
					                    
					                    
					                    //Medium Health
				} else if (player2Health <= 50 && player2Health > 25) {
					bufCtx.setFillStyle("yellow");
					// Draw Sprite for Enemy
					if(ChosenPlayer == 1){
						            bufCtx.drawImage((ImageElement) BMed.getElement()
							.cast(), 580, 100);
						                      }
					          else if(ChosenPlayer == 2){
					            bufCtx.drawImage((ImageElement) MMed.getElement()
					                .cast(), 580, 100);
					            }
					          else if (ChosenPlayer == 3){
					            bufCtx.drawImage((ImageElement) HMed.getElement()
					                .cast(), 580, 100);
					          }
					          
					          
					          //Low Health
				} else {
					bufCtx.setFillStyle("red");
					// Draw Sprite for Enemy
					if(ChosenPlayer == 1){
						            bufCtx.drawImage((ImageElement) BLow.getElement()
							.cast(), 580, 100);
						                      }
					          else if(ChosenPlayer == 2){
					            bufCtx.drawImage((ImageElement) MLow.getElement()
					                .cast(), 580, 100);
					            }
					          else if (ChosenPlayer == 3){
					            bufCtx.drawImage((ImageElement) HLow.getElement()
					                .cast(), 580, 100);
					          }
				}

				// Draw EnemyHealth Bar that scales based on health size
				bufCtx.fillRect(450, 35, (double) player2Health * 3, 25);

				if ((MouseX > 380 && MouseX < 455)
						&& (MouseY > 360 && MouseY < 390)) {
					// Draw AttackSelected Button
					bufCtx.drawImage((ImageElement) AttackSelected.getElement()
							.cast(), 380, 360);
				} else {
					// Draw Attack Button
					bufCtx.drawImage((ImageElement) Attack.getElement().cast(),
							380, 360);
				}
				if ((MouseX > 380 && MouseX < 455)
						&& (MouseY > 410 && MouseY < 440)) {
					// Draw HealSelected Button
					bufCtx.drawImage((ImageElement) HealSelected.getElement()
							.cast(), 380, 410);
				} else {
					// Draw Heal Button
					bufCtx.drawImage((ImageElement) Heal.getElement().cast(),
							380, 410);
				}
				// Set font to red
				bufCtx.setFillStyle("red");
				// Sets font to sans-serif
				bufCtx.setFont("bold 16px sans-serif");
				// Prints Player 1 Health
				bufCtx.fillText((player1Health + " / 100"), 30, 430);
				// Prints Player 2 Health
				bufCtx.fillText(player2Health + " / 100", 450, 35);
				bufCtx.setFillStyle("red");
				bufCtx.setFont("bold 18px sans-serif");
				// Prints current game time
				bufCtx.fillText(
						Integer.toString(SecondTime) + ":"
								+ Integer.toString(MilliTime), 700, 378);
				bufCtx.fillText("Health changes after last move- Player1: " + HealthMove + " Player2:" + HealthMove2, 320, 330);

			} else if (BattleState.battleState() == 1) {
				// Draw loss image
				bufCtx.drawImage((ImageElement) GameLoss.getElement().cast(),
						0, 0);
				bufCtx.setFillStyle("green");
				bufCtx.setFont("bold 36px sans-serif");
				bufCtx.fillText(username + ", Your Score: " + Integer.toString(PScore), 400,
						200);

				// Draw Sprite for character
				if (ChosenPlayer == 1){
					        bufCtx.drawImage((ImageElement) BWin.getElement().cast(), 50,
					            200);
					        }
					        else if (ChosenPlayer == 2){
					        bufCtx.drawImage((ImageElement) MWin.getElement().cast(), 50,
					            200);
					        }
					        else if (ChosenPlayer == 3){
					        bufCtx.drawImage((ImageElement) HFull.getElement().cast(), 50,
						200);
					        }
				
				// Update global win/lose values
				victoryBool = false;
				losses++;

			} else {
				// Draw win image
				bufCtx.drawImage((ImageElement) GameWin.getElement().cast(), 0,
						0);
				bufCtx.setFillStyle("green");
				bufCtx.setFont("bold 36px sans-serif");
				bufCtx.fillText(username + ", Your Score: " + Integer.toString(PScore), 350,
						250);

				// Draw Sprite for character
				bufCtx.drawImage((ImageElement) Victory.getElement().cast(),
						50, 200);
				
				// Update global win/lose values
				victoryBool = true;
				wins++;
			}
		}
		// Copy buffer onto main canvas
		ctx.drawImage((CanvasElement) buffer.getElement().cast(), 0, 0);

	}

}