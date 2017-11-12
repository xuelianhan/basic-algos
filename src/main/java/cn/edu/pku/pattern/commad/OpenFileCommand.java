package cn.edu.pku.pattern.commad;

public class OpenFileCommand implements Command {

    private FileSystemReceiver fs;
    
    public OpenFileCommand(){}
    
    public OpenFileCommand(FileSystemReceiver fs) {
        this.fs = fs;
    }
    
    @Override
    public void execute() {
       fs.openFile();
    }

}
