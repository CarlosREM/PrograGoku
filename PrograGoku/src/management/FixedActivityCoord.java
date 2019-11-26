package management;

public enum FixedActivityCoord {
	ACTION_BED		(1104, 	742),
	ACTION_TOILET	(816, 	514),
	ACTION_FRIDGE	(944,	516),
	ACTION_MEDITATE	(848, 	790),
	ACTION_TRAIN	(123, 	703),
	SLEEP			(1152, 	726),
	TABLE1 			(1022, 	592),
	TABLE2 			(1118, 	592),
	TOILET 			(816, 	494),
	POOL1 			(206, 566),
	POOL2 			(206, 454),
	FIELD1 			(418, 462),
	FIELD2 			(530, 462),
	FIELD3 			(636, 462),
	GARDEN1 		(855, 1079),
	GARDEN2 		(975, 1079),
	GARDEN3 		(1097, 1079),
	ARENA1 			(240, 910),
	ARENA2 			(368, 910),
	SOFA1 			(832, 720),
	SOFA2 			(864, 720),
	FRONTDOOR_IN 	(1296, 594),
	FRONTDOOR_OUT 	(1296, 714),
	BACKDOOR_IN 	(656, 746),
	BACKDOOR_OUT 	(656, 626),
	SIGN_BACKYARD 	(319, 654),
	SIGN_BATHROOM 	(768, 648),
	SIGN_BEDROOM 	(1070, 712),
	SIGN_GARDEN 	(976, 970),
	SIGN_FRONTYARD	(1424, 848),
	SIGN_KITCHEN 	(980, 516);
	
	public final float x, y;
	
	private FixedActivityCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
}
