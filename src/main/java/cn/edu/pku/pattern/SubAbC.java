package cn.edu.pku.pattern;

public class SubAbC {
    
    Pizza forCrust() {
        return new Crust();
    }
    
    Pizza forCheese(Pizza p) {
        return new Cheese(p.subAbC());
    }
    
    Pizza forOlive(Pizza p) {
        return new Olive(p.subAbC());
    }
    
    Pizza forSausage(Pizza p) {
        return new Sausage(p.subAbC());
    }

    Pizza forAnchovy(Pizza p) {
        return new Cheese(p.subAbC());
    }
}
