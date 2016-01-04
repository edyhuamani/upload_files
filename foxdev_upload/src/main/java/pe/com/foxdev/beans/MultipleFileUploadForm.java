package pe.com.foxdev.beans;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MultipleFileUploadForm {
	private List<MultipartFile> files;

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
}
