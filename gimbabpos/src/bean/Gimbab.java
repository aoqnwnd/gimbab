package bean;

public class Gimbab extends Object{
	
	private String name;
	private String drink;
	private String stock;
	private int price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDrink() {
		return drink;
	}
	public void setDrink(String drink) {
		this.drink = drink;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Gimbab [김밥=" + name + ", 음료=" + drink + ", 토핑=" + stock + ", 가격=" + price + "]\n";
	}

	
}
