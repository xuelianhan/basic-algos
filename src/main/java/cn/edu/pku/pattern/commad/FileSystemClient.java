package cn.edu.pku.pattern.commad;

public class FileSystemClient {

    public static void main(String[] args) {
        //1.creating the receiver object
        FileSystemReceiver fs = FileSystemReceiverUtil.getUnderlyingFileSystem();
        //2.creating command and associating with receiver
        OpenFileCommand open = new OpenFileCommand(fs);
        //3.creating invoker and associating with command
        FileInvoker invoker = new FileInvoker(open);
        //perform action on invoker object
        invoker.execute();
        
        WriteFileCommand write = new WriteFileCommand(fs);
        invoker = new FileInvoker(write);
        invoker.execute();
        
        CloseFileCommand close = new CloseFileCommand(fs);
        invoker = new FileInvoker(close);
        invoker.execute();
    }
}
