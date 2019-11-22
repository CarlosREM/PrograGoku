package ADT;

public enum FixedActivityCoord {
	ACTION_BED		(1103, 	751),
	ACTION_TOILET	(815, 	479),
	ACTION_FRIDGE	(1007,	512),
	ACTION_MEDITATE	(847, 	767),
	SLEEP			(1103, 751),
	TABLE1 			(1007, 575),
	TABLE2 			(1135, 575),
	TOILET 			(815, 479),
	POOL 			(0, 0),
	GARDEN1 		(0, 0),
	GARDEN2 		(0, 0),
	GARDEN3 		(0, 0),
	ARENA1 			(0, 0),
	ARENA2 			(0, 0),
	SOFA 			(0, 0),
	ENTRANCE 		(0, 0),
	FRONTDOOR_IN 	(1295, 592),
	FRONTDOOR_OUT 	(0, 0),
	BACKDOOR_IN 	(0, 0),
	BACKDOOR_OUT 	(0, 0),
	SIGN_BACKYARD 	(0, 0),
	SIGN_BATHROOM 	(0, 0),
	SIGN_BEDROOM 	(0, 0),
	SIGN_GARDEN 	(0, 0),
	SIGN_FRONTYARD	(0, 0),
	SIGN_KITCHEN 	(0, 0);
	
	public final float x, y;
	
	private FixedActivityCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
}
