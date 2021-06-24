package cn.edu.pku.pattern.chain;

/**
 * @author sniper
 * @date 2021/6/24 2:02 PM
 */
public abstract class AbstractChainHandler {

    protected int maxApproveAmount;

    protected String approverName;

    public AbstractChainHandler(int maxApproveAmount, String approverName) {
        this.maxApproveAmount = maxApproveAmount;
        this.approverName = approverName;
    }

    private AbstractChainHandler nextChainHandler;

    public void setNextHandler(AbstractChainHandler nextChainHandler) {
        this.nextChainHandler = nextChainHandler;
    }

    /**
     * Let sub-class to do approve
     * @param amount
     */
    abstract public void approve(int amount);


    /**
     * parent-class handle
     * @param amount
     */
    public final void handle(int amount) {
        if (amount <= this.maxApproveAmount) {
            approve(amount);
            return;
        }
        if (null == nextChainHandler) {
            System.out.println(approverName + " No next approve handler set");
            return;
        }
        System.out.println(approverName + " cannot process, pass to next approve handler");
        nextChainHandler.handle(amount);
    }

}
