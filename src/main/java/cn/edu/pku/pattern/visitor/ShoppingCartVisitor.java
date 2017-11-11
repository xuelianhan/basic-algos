package cn.edu.pku.pattern.visitor;

public interface ShoppingCartVisitor {
    
    int visit(Book book);
    
    int visit(Fruit fruit);
}
