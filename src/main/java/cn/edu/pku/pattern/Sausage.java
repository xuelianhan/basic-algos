package cn.edu.pku.pattern;

public class Sausage extends Pizza {

    Pizza p;
    
    Sausage(Pizza p) {
       this.p = p; 
    }
    
    @Override
    Pizza remA() {
        return new Sausage(p.remA());
    }

    @Override
    Pizza topAwC() {
        return new Sausage(p.topAwC());
    }

    @Override
    Pizza subAbC() {
        return new Sausage(p.subAbC());
    }

}
