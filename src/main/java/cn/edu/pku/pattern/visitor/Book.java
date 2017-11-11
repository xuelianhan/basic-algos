package cn.edu.pku.pattern.visitor;

public class Book implements VisitorItemElement {

    private int price;
    
    private String isbnNumber;
    
    public Book(int cost, String isbn) {
        this.price = cost;
        this.isbnNumber = isbn;
    }
    
    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }

    public int getPrice() {
        return price;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

}
