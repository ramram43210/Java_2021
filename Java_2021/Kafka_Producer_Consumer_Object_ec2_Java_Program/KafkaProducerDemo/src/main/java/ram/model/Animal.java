package ram.model;

public class Animal
{
	private String name;
	private String color;
	private String animalType;
	
	public Animal() 
	{
		
	}

	public Animal(String name, String color, String animalType)
	{
		this.name = name;
		this.color = color;
		this.animalType = animalType;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public String getAnimalType()
	{
		return animalType;
	}

	public void setAnimalType(String animalType)
	{
		this.animalType = animalType;
	}

	@Override
	public String toString()
	{
		return "Animal [name=" + name + ", color=" + color + ", animalType=" + animalType + "]";
	}

}
