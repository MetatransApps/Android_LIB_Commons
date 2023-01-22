package org.metatrans.commons.cfg.publishedapp;


public class MarketURLGen_OurWebsite implements IMarketURLGen {
	
	
	private String url;
	
	
	public MarketURLGen_OurWebsite(String app_package) {
		if ("com.chessboardscanner".equals(app_package)) {
			url = "https://metatransapps.com/chess-board-scanner-and-analyzer/";
		} else if ("com.bagaturchess".equals(app_package)) {
			url = "https://metatransapps.com/bagatur-chess-engine-with-gui-chess-ai/";
		} else if ("com.mathforkids5".equals(app_package)) {
			url = "https://metatransapps.com/math-for-kids-1-2-3-4-grade-class-graders/";
		} else if ("com.chessartforkids".equals(app_package)) {
			url = "https://metatransapps.com/chess-art-for-kids-kindergarten-to-grandmaster/";
		} else if ("com.wisconsin".equals(app_package)) {
			url = "https://metatransapps.com/wisconsin-card-sorting-test-wcst-variant-cards/";
		} else if ("com.easycolours".equals(app_package)) {
			url = "https://metatransapps.com/stroop-effect-test-challenge-and-test-your-brain/";
		} else if ("com.gravityplay".equals(app_package)) {
			url = "https://metatransapps.com/gravity-force-finger-137-cross-the-orbits/";
		} else if ("com.stoptheballs".equals(app_package)) {
			url = "https://metatransapps.com/non-stop-balloons-shooter-for-kids-and-adults/";
		} else if ("com.maze_squirrel".equals(app_package)) {
			url = "https://metatransapps.com/maze-runner-2d-old-school-labyrinth-offline-game/";
		} else if ("com.maze_dinosaurs".equals(app_package)) {
			url = "https://metatransapps.com/the-dinosaurs-maze/";
		} else if ("mat.cards".equals(app_package)) {
			url = "https://metatransapps.com/mind-adaptivity-test-cards-mat-cards/";
		} else {
			url = "https://metatransapps.com/";
		}
	}
	
	
	@Override
	public String getUrl() {
		return url;
	}
}
