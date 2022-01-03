package fr.gouv.culture.francetransfert.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MimeService {

	@Value("${extension.name}")
	private List<String> extensionList;

	@Value("${mimetype.name}")
	private List<String> mimeList;

	private Tika tika = new Tika();

	public Boolean isAuthorisedMimeTypeFromFileName(String filename) {

		Boolean authorised = false;
		if (null == filename) { // test null parameter : file, flowFilename.
			authorised = false;
		} else {
			String ext = FilenameUtils.getExtension(filename);
			String mimeType = tika.detect(filename);
			if (extensionList.contains(ext)) {
				authorised = false;
			} else {
				authorised = true;
			}
			// WhiteList regex
//			for (String extent : mimeList) {
//				
//				if (mimeType.matches(extent)) {
//					authorised = true;
//					break;
//				}
//			}
		}
		return authorised;
	}

	public Boolean isAuthorisedMimeTypeFromFile(FileInputStream fileInputStream) throws IOException {

		Boolean authorised = false;
		String mimeType = tika.detect(fileInputStream);

		if (mimeList.contains(mimeType)) {
			authorised = false;
		} else {
			authorised = true;
		}

		// WhiteList regex
//		for (String extent : mimeList) {
//
//			if (mimeType.matches(extent)) {
//				authorised = true;
//				break;
//			}
//		}
		return authorised;
	}

	public Set<String> authorisedMimeList() {

		Set<String> retList = new HashSet<String>();

		for (String ext : extensionList) {
			String fileName = "ttoto." + ext;
			String mime = tika.detect(fileName);
			retList.add(mime);
		}

		return retList;

	}

}
