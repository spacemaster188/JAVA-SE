package com.javacore.utils;
/** Save global logging for GUI */
public class GlobalLog {
private static StringBuilder sb = new StringBuilder();

public static String addLog(final String logElement) {
    sb.append(logElement);
    sb.append("\n");
    return logElement;
}

public static String getLog() {
    return String.valueOf(sb);
}

}
