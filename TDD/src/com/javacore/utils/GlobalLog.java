package com.javacore.utils;

public class GlobalLog {
private static StringBuilder sb = new StringBuilder();

public static String addLog(String logElement){
	sb.append("\n");
	sb.append(logElement);
	return logElement;
}

public static String getLog(){
	return String.valueOf(sb);
}

}
