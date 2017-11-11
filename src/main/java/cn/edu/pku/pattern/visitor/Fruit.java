package cn.edu.pku.pattern.visitor;

public class Fruit implements VisitorItemElement {

    private int pricePerKg;
    private int weight;
    private String name;
    
    public Fruit(int priceKg, int weight, String name) {
        this.pricePerKg = priceKg;
        this.weight = weight;
        this.name = name;
    }
    
    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

}
