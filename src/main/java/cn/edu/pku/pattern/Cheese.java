package cn.edu.pku.pattern;

public class Cheese extends Pizza {

    Pizza p;
    
    Cheese (Pizza p) {
        this.p = p;
    }
    
    @Override
    Pizza remA() {
        return new Cheese(p.remA());
    }

    @Override
    Pizza topAwC() {
        return new Cheese(p.topAwC());
    }

    @Override
    Pizza subAbC() {
        return new Cheese(p.subAbC());
    }

}
