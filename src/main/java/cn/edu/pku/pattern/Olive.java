package cn.edu.pku.pattern;

public class Olive extends Pizza {
    
    Pizza p;
    
    Olive(Pizza p) {
        this.p = p;
    }
    
    @Override
    Pizza remA() {
        return new Olive(p.remA());
    }

    @Override
    Pizza topAwC() {
        return new Olive(p.topAwC());
    }

    @Override
    Pizza subAbC() {
        return new Olive(p.subAbC());
    }

}
