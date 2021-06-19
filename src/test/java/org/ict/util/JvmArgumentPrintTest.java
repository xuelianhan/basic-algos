package org.ict.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

/**
 * @author sniper
 * @date 2021/6/19 11:15 AM
 */
public class JvmArgumentPrintTest {

    public void printJvmArgument() {
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = runtimeMxBean.getInputArguments();
        arguments.forEach(System.out::println);
    }
}
