package cn.edu.pku.pattern.chain;

/**
 * @author sniper
 * @date 2021/6/24 3:20 PM
 */
public class FinancialManager extends AbstractChainHandler {

    public FinancialManager(int maxApproveAmount, String approverName) {
        super(maxApproveAmount, approverName);
    }

    @Override
    public void approve(int amount) {
        System.out.println(approverName + " approve the amount:" + amount);
    }
}
