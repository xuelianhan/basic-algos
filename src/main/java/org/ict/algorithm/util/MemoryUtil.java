package org.ict.algorithm.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Get the usage of physical memory by reading file /proc/$pid/status
 * Resident Set Size
 * Unique Set Size
 *
 * @author sniper
 * @date 2022/7/26
 */
@Slf4j
public class MemoryUtil {

    private static final String LINUX_OS_NAME = "Linux";

    private static final String OS_NAME_VAR = "os.name";

    public static void main(String[] args) {
        long result = getProcessRssInKb();
        System.out.println(result);
    }

    public static long getProcessRssInKb() {
        /**
         * Get the current process id
         */
        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        String osName = System.getProperty(OS_NAME_VAR);
        long invalidValuePlaceholder = -1L;

        if (!LINUX_OS_NAME.equals(osName)) {
            return invalidValuePlaceholder;
        }

        String statusFile = "/proc/" + pid + "/status";
        try {
            return Files.readAllLines(Paths.get(statusFile))
                    .stream()
                    .filter(line -> line.startsWith("VmRSS:"))
                    .map(line -> line.split("\\s+")[1].trim())
                    .mapToLong(Long::parseLong)
                    .findFirst()
                    .orElse(invalidValuePlaceholder);
        } catch (IOException e) {
            log.error("read rss failed", e);
        }
        return 0;
    }
}
