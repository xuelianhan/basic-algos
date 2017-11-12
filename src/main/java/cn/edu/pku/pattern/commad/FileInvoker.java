package cn.edu.pku.pattern.commad;

public class FileInvoker {

    private Command cmd;
    
    public FileInvoker(Command cmd) {
        this.cmd = cmd;
    }
    
    public void execute() {
        this.cmd.execute();
    }
}
