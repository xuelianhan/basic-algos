package cn.edu.pku.pattern.commad;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FileSystemReceiverUtil {

    private static Map<String, FileSystemReceiver> fsReceiverMap = new HashMap<String, FileSystemReceiver>();
    
    static {
        fsReceiverMap.put("Windows", new WindowsFileSystemReceiver());
        fsReceiverMap.put("Unix", new UnixFileSystemReceiver());
        fsReceiverMap.put("Linux", new LinuxFileSystemReceiver());
    }
    
    public static FileSystemReceiver getUnderlyingFileSystem() {
        String osName = System.getProperty("os.name");
        System.out.println("Underlying OS is:" + osName);
        Set<String> osNameSet = fsReceiverMap.keySet();
        for(String name : osNameSet) {
            if (name.equals(osName)) {
                return fsReceiverMap.get(name);
            }
        }
        return null;
    }
    
}
