package cn.edu.pku.pattern;

public class Anchovy extends Pizza {

    Pizza p;
    
    Anchovy(Pizza p) {
        this.p = p;
    }
    
    @Override
    Pizza remA() {
        return p.remA();
    }

    @Override
    Pizza topAwC() {
        return new Cheese(new Anchovy(p.topAwC()));
    }

    @Override
    Pizza subAbC() {
        return new Cheese(p.subAbC());
    }

}
