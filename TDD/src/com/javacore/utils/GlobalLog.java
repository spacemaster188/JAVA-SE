package com.javacore.utils;

public class GlobalLog {
private static StringBuilder sb = new StringBuilder();
/** Save global logging for Swing GUI window */
public static String addLog(String logElement){
	sb.append(logElement);
	sb.append("\n");
	return logElement;
}

public static String getLog(){
	return String.valueOf(sb);
}

}
