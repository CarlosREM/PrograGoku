package abstraction;

public abstract class ASickness implements IVisitor{
	protected String name;
	protected int damage;
	public ASickness(String name, int damage) {
		this.name = name;
		this.damage = damage;
	}
	
}
