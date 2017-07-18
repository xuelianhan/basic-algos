package cn.edu.pku.pattern;

public class TopAwC {
    
    Pizza forCrust() {
        return new Crust();
    }
    
    Pizza forCheese(Pizza p) {
        return new Cheese(p.topAwC());
    }
    
    Pizza forOlive(Pizza p) {
        return new Olive(p.topAwC());
    }
    
    Pizza forSausage(Pizza p) {
        return new Cheese(new Anchovy(p.topAwC()));
    }

    Pizza forAnchovy(Pizza p) {
        return p.remA();
    }
}
