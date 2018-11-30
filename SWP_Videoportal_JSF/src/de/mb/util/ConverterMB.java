package de.mb.util;

import java.io.File;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

@ManagedBean
@SessionScoped
public class ConverterMB {

	private Part fileToUpload;
	private File file;
	private Directory directory = new Directory();

	/**
	 * 0. @video_typ that can be any typ of videos 1. @buffering_folder path Buffer
	 * folder 2. @output_folder converter folder 3. @id do not change
	 * 4. @full_path_of_video do not change 5. @full_path_of_output_video do not
	 * change
	 */
	private String video_typ = "." + "mp4";
	private String saveVideoFolder = "Videoportal";

	public String unique_id() {
		IdGen idGen = new IdGen();
		String id = idGen.unique_id();
		return id;
	}

	public String upload() throws IOException {
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
			return "success";
		} else {
			return "unsuccess";
		}
	}

	private Boolean converter(String path) throws IOException {
		String id = unique_id();
		String output_folder = directory.output_folder(saveVideoFolder);
		String full_path_of_output_video = output_folder + id;

		String command = "cmd /c ffmpeg.exe -i \"" + path + "\" -y \"" + full_path_of_output_video + video_typ
				+ "\" -loglevel quiet";
		System.out.println(command);
		try {
			executeProcess(command);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

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

	/**
	 * Create and Execute Process for each command
	 */
	static Process executeProcess(String command) throws InterruptedException, IOException {
		Process clipProcess = Runtime.getRuntime().exec(command);
		clipProcess.waitFor();
		return clipProcess;
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

}
