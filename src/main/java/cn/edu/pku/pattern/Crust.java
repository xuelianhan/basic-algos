package cn.edu.pku.pattern;

public class Crust extends Pizza {

    @Override
    Pizza remA() {
        return new Crust();
    }

    @Override
    Pizza topAwC() {
        return new Crust();
    }

    @Override
    Pizza subAbC() {
        return new Crust();
    }

}
