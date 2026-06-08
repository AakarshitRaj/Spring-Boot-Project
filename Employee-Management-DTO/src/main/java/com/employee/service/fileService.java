package com.employee.service;

import java.io.IOException;
import java.nio.file.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class fileService {

	@Value("${file.upload-dir}")
	private String uploadDir;
	
	public String uploadFile(MultipartFile file)throws IOException{
		Path path=Paths.get(uploadDir);
		
		if(!Files.exists(path)) {
			Files.createDirectories(path);
		}
		String filename=file.getOriginalFilename();
		
		Files.copy(file.getInputStream(),path.resolve(filename),StandardCopyOption.REPLACE_EXISTING);
		
		return filename;
	}
	
	public Resource downloadFile(
	        String filename)
	        throws Exception {

	    Path path =
	            Paths.get(uploadDir)
	                    .resolve(filename);

	    Resource resource =
	            new UrlResource(path.toUri());

	    if(resource.exists()) {
	        return resource;
	    }

	    throw new RuntimeException(
	            "File Not Found");
	}
}
