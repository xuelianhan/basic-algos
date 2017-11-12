package cn.edu.pku.pattern.commad;

public class WriteFileCommand implements Command {

    private FileSystemReceiver fs;
    
    public WriteFileCommand(){}
    
    public WriteFileCommand(FileSystemReceiver fs) {
        this.fs = fs;
    }
    
    @Override
    public void execute() {
       fs.writeFile();
    }

}
