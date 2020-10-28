package org.ict.algorithm.fsm;

public enum ReplyState {
    no_picked_up(-1, "no_picked_up"),
    has_picked_up(0, "has_picked_up"),
    key_press_one(1, "key_press_one"),
    key_press_two(2, "key_press_two"),
    key_press_three(3, "key_press_three");
    
    private ReplyState(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    private int code;
    private String desc;
    
    public int getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
    
    public static ReplyState codeOf(int code) {
        for(ReplyState state : values()) {
            if (state.getCode() == code) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid ReplyState:" + code);
    }
}
