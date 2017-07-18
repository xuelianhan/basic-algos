package cn.edu.pku.pattern;

public class RemA {
    
    Pizza forCrust() {
        return new Crust();
    }
    
    Pizza forCheese(Pizza p) {
        return new Cheese(p.remA());
    }
    
    Pizza forOlive(Pizza p) {
        return new Olive(p.remA());
    }
    
    Pizza forSausage(Pizza p) {
        return new Sausage(p.remA());
    }

    Pizza forAnchovy(Pizza p) {
        return p.remA();
    }
}
