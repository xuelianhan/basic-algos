package cn.edu.pku.pattern.chain;

/**
 * @author sniper
 * @date 2021/6/24 3:25 PM
 */
public class ChainHandlerTest {

    public static void main(String[] args) {
        Cashier cashier = new Cashier(20, "cashier");
        Accountant accountant = new Accountant(50, "Accountant");
        FinancialManager financialManager = new FinancialManager(100, "FinancialManager");

        cashier.setNextHandler(accountant);
        accountant.setNextHandler(financialManager);
        cashier.handle(90);
    }
}
