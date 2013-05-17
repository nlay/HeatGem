package edu.ycp.cs320.heatgem.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HeatGem implements EntryPoint {
	private static IsWidget currentView;
	
	// images that will be drawn on a canvas
	private static String[] SPRITES = {
		"Attack.png",
		"CriticalAttack.png",
		"RoughBattle.jpg",
		"Defeat.png",
		"DoubleDamage.png",
		"FullHealth.png",
		"Heal.png",
		"logoT.png",
		"LowHealth.png",
		"TBAR.jpg",
		"UserInfo.png",
		"Victory.png",
		"YellowHealth.png",
		"AttackSelected.png",
		"HealSelected.png",
		"fireRuby1.gif",
		"BattleLoss.jpg",
		"BattleWin.jpg",
		"Homepage.gif",
		"Homepage.png",
		"HomepageDif.gif",
		"Play.png",
		"PlaySelected.png",
		"Candle.gif",
		"black_box.png",
		"heat_gem_vid.gif",
		"bottom_black_box.png",
		"name.png",
		"level.png",
		"exp.png",
		"wins.png",
		"losses.png",
		"fireRuby_small.gif",
//		"Layer1.png", "Layer2.png", "Layer3.png", "Layer4.png", "Layer5.png", "Layer6.png", "Layer7.png", "Layer8.png", "Layer9.png", "Layer10.png", "Layer11.png", "Layer12.png", "Layer13.png", "Layer14.png", "Layer15.png",
	    "BabcockFull.png", "BabcockMedium.png", "BabcockHurt.png", "BabcockWin.png",
	    "HovemeyerFull.png", "HovemeyerMedium.png", "HovemeyerLow.png",
	    "MoscolaFull.png", "MoscolaLow.png", "MoscolaMedium.png", "MoscolaWin.png",
	    "Hovemeyer.png", "HovemeyerSelected.png", "Moscola.png", "MoscolaSelected.png", 
	    "Babcock.png", "BabcockSelected.png"
	};
	private static Map<String, Image> imageMap = new HashMap<String, Image>();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		FlowPanel imagePanel = new FlowPanel();
		RootLayoutPanel.get().add(imagePanel);
		RootLayoutPanel.get().setWidgetRightWidth(imagePanel, 0.0, Unit.PX, 0.0, Unit.PX);
		RootLayoutPanel.get().setWidgetBottomHeight(imagePanel, 0.0, Unit.PX, 0.0, Unit.PX);
		
		for (String spriteImage : SPRITES) {
			Image image = new Image(GWT.getModuleBaseForStaticFiles() + spriteImage);
			imagePanel.add(image);
			imageMap.put(spriteImage, image);
		}

		setView(new LoginView());
	}
	
	public static void setView(IsWidget view) {
		if (currentView != null) {
			RootLayoutPanel.get().remove(currentView);
		}
		
		RootLayoutPanel.get().add(view);
		RootLayoutPanel.get().setWidgetLeftRight(view, 10.0, Unit.PX, 10.0, Unit.PX);
		RootLayoutPanel.get().setWidgetTopBottom(view, 10.0, Unit.PX, 10.0, Unit.PX);
		
		currentView = view;
	}
	
	public static Image getImage(String spriteImage) {
		Image image = imageMap.get(spriteImage);
		if (image == null) {
			throw new IllegalArgumentException("Unknown sprite image: " + spriteImage);
		}
		return image;
	}
	
	//Load ALL the music files
		private static Audio Music;

		{
		    Music = Audio.createIfSupported();
		    Music.setSrc(GWT.getModuleBaseForStaticFiles() + "MusicOgg.ogg");
		    
		}
		private static Audio HBattle;

		{
		    HBattle = Audio.createIfSupported();
		    HBattle.setSrc(GWT.getModuleBaseForStaticFiles() + "hovemeyer.ogg");
		    
		}
		private static Audio MBattle;

		{
		    MBattle = Audio.createIfSupported();
		    MBattle.setSrc(GWT.getModuleBaseForStaticFiles() + "moscola.ogg");
		    
		}
		private static Audio BBattle;

		{
		    BBattle = Audio.createIfSupported();
		    BBattle.setSrc(GWT.getModuleBaseForStaticFiles() + "babcock.ogg");
		    
		}
		private static Audio Attack;

		{
			Attack = Audio.createIfSupported();
			Attack.setSrc(GWT.getModuleBaseForStaticFiles() + "attack.ogg");
		    
		}
		private static Audio Heal;

		{
			Heal = Audio.createIfSupported();
			Heal.setSrc(GWT.getModuleBaseForStaticFiles() + "heal.ogg");
		    
		}
		private static Audio PMusic;

		{
			PMusic = Audio.createIfSupported();
			PMusic.setSrc(GWT.getModuleBaseForStaticFiles() + "profile.ogg");
		    
		}
		private static Audio LMusic;

		{
			LMusic = Audio.createIfSupported();
			LMusic.setSrc(GWT.getModuleBaseForStaticFiles() + "leaderboard.ogg");
		    
		}
		//Music
		public static void PlayMusic(){
		    Music.play();
		}
		public static void StopMusic(){
			Music.pause();
		}
		//Hovemeyer Battle Music
		public static void PlayHovemeyer(){
			HBattle.play();
		}
		public static void StopHovemeyer(){
			HBattle.pause();
		}
		//Moscola Battle Music
		public static void PlayMoscola(){
			MBattle.play();
		}
		public static void StopMoscola(){
			MBattle.pause();
		}
		//Babcock Battle Music
		public static void PlayBabcock(){
			BBattle.play();
		}
		public static void StopBabcock(){
			BBattle.pause();
		}
		//Attack sound
		public static void PlayAttack(){
		    Attack.play();
		}
		//Heal sound
		public static void PlayHeal(){
		    Heal.play();
		}
		//Profile music
		public static void PlayProfile(){
		    PMusic.play();
		}
		public static void StopProfile(){
			PMusic.pause();
		}
		//Leaderboards music
		public static void PlayLeaderboards(){
		    LMusic.play();
		}
		public static void StopLeaderboards(){
			LMusic.pause();
		}
}
