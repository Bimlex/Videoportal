package de.awk.videoverwaltung.facade.impl.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.Part;

public class Converter {

	private Directory directory = new Directory();
	IdGen idGen = new IdGen();
	private String videoPath;

	private Part fileToUpload;
	private File file;
	private String output;
	private String typ;

	// private String video_typ = "." + "mp4";
	// private String saveVideoFolder = "Videoportal";

	public String unique_id() {
		String id = idGen.unique_id();
		return id;
	}

	public boolean uploadNewVideo(File file, Part fileToUpload, String output, String typ) throws IOException {
		System.out.println(output + typ);
		this.output = output;
		this.typ = "." + typ;

		this.fileToUpload = fileToUpload;
		try {
			if (upload()) {
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Boolean upload() throws IOException {
		String buffering_folder = directory.buffering_folder();
		String full_path_of_video = buffering_folder + getFilename(fileToUpload);
		fileToUpload.write(full_path_of_video);
		System.out.println(full_path_of_video);
		if (converter(full_path_of_video)) {
			file = new File(full_path_of_video);
			if (file.delete()) {
				System.out.println("File " + full_path_of_video + " deleted");
			} else
				System.out.println("File " + full_path_of_video + " doesn't exists");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Create and Execute Process for each command
	 */
	static Process executeProcess(String command) throws InterruptedException, IOException {
		Process clipProcess = Runtime.getRuntime().exec(command);
		clipProcess.waitFor();
		return clipProcess;
	}

	private static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				String filenames = filename.substring(filename.lastIndexOf('/') + 1)
						.substring(filename.lastIndexOf('\\') + 1);
				return filenames;
			}
		}
		return null;
	}

	private Boolean converter(String path) throws IOException {
		String id = unique_id();
		String output_folder = directory.output_folder(output);
		String full_path_of_output_video = output_folder + id;
		setVideoPath(full_path_of_output_video + typ);
		String command = "ffmpeg -i \"" + path + "\" -y \"" + videoPath + "\" -loglevel quiet";
		System.out.println(command);
		try {
			executeProcess(command);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public Part getFileToUpload() {
		return fileToUpload;
	}

	public void setFileToUpload(Part fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Directory getDirectory() {
		return directory;
	}

	public void setDirectory(Directory directory) {
		this.directory = directory;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

}