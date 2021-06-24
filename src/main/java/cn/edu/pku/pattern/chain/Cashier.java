package cn.edu.pku.pattern.chain;

/**
 * @author sniper
 * @date 2021/6/24 3:12 PM
 */
public class Cashier extends AbstractChainHandler {

    public Cashier(int maxApproveAmount, String approverName) {
        super(maxApproveAmount, approverName);
    }

    @Override
    public void approve(int amount) {
        System.out.println(approverName + " approve the amount:" + amount);
    }
}
