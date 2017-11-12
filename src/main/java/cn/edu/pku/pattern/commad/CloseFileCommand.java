package cn.edu.pku.pattern.commad;

public class CloseFileCommand implements Command {

    private FileSystemReceiver fs;
    
    public CloseFileCommand(){}
    
    public CloseFileCommand(FileSystemReceiver fs) {
        this.fs = fs;
    }
    
    @Override
    public void execute() {
       fs.closeFile();
    }

}
