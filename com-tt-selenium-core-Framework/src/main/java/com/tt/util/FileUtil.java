package com.tt.util;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

	public static String getFileName(String filePath) {
		
		String output = "";
		File f = new File(filePath);
		output = f.getName();
		return output;
	}

	public static String getAbsolutePath(String filePath) {
		
		String output = "";
		File f = new File(filePath);
		output = f.getAbsolutePath();
		return output;
	}

	public static boolean exists(String filePath) {
		return (new File(filePath)).exists();
	}

	public static void createFolders(String filePath) {
		try {
			Files.createDirectories(Paths.get(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createTextFile(String path, String fileName) {
		try {
			String fullFilePath = path + fileName + ".txt";
			File f = new File(fullFilePath);
			if (!f.exists() && f.createNewFile()) {
				System.out.println("File \"" + fullFilePath + "\" is created succesfully");
			} else if (f.exists()) {
				System.out.println("File already exists");
			} else {
				System.out.println("Unexpected errror occured and file was not created.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void appendToFile(String filePath, String content) {
		try {
			if (!exists(filePath)) {
				System.out.println("File does not exist, cannot append content  to it. ");
				return;
			} else {
				FileWriter fw = new FileWriter(filePath, true);
				fw.append("\n");
				fw.append(DateUtil.getCurrentDate("dd-MMM-yyy HH:mm:ss") + ":" + content);
				fw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeToFile(String filePath, String content) {
		try {
			FileWriter m = new FileWriter(filePath);
			m.write(content);
			m.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(FileUtil.getFileName("D:\\Selenium-Testing\\Selenium File Generate\\DemoReport.html"));
		System.out.println(FileUtil.getAbsolutePath("D:\\Selenium-Testing\\Selenium File Generate\\DemoReport.html"));
		System.out.println(FileUtil.exists("D:\\Selenium-Testing\\Selenium File Generate\\DemoReport.html"));
		FileUtil.createFolders("D:\\Selenium-Testing\\Selenium File Generate\\DemoScreenshots");
		FileUtil.createTextFile("D:\\Selenium-Testing\\Selenium File Generate\\DemoScreenshots\\", "log");
		FileUtil.appendToFile("D:\\Selenium-Testing\\Selenium File Generate\\DemoScreenshots\\log.txt",
				"Checking newline content to existing file");
	}
}
