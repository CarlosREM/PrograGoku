package abstraction;

public abstract class AConsumable implements IVisitor {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AConsumable(String name) {
		this.name = name;
	}
}
