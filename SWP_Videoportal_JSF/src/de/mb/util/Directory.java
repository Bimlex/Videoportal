package de.mb.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Directory {

		
		public Directory() {
		super();
		// TODO Auto-generated constructor stub
	}


		public String buffering_folder() {
			// This is the property name for accessing OS temporary directory.
			String property = "java.io.tmpdir";
			// Get the temporary directory and print it.
			String tempDir = System.getProperty(property);
			System.out.println("OS temporary directory is " + tempDir);
			return tempDir;
		}


		// Get the user directory and print it.

		public String output_folder(String folderName) {
		String home = System.getProperty("user.home");
		String fileName = home + "\\"+folderName+"\\";
		Path path = Paths.get(fileName);

		// if the directory does not exist, create it
		if (!Files.exists(path)) {
			try {
				Files.createDirectory(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Directory created");
		} else {
			System.out.println("Directory already exists");
		}
		System.out.println(fileName);
		return fileName;
	}
}

