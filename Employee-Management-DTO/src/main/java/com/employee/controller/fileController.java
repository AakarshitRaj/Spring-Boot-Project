package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.employee.service.fileService;

@RestController
@RequestMapping("/files")
public class fileController {

	@Autowired
	private fileService service;
	
	@PostMapping("/uploads")
	public String uploadFile(@RequestParam("file")MultipartFile file) throws Exception{
		return service.uploadFile(file);
	}
	
	@GetMapping("/download/{filename}")
	public ResponseEntity<Resource>
	downloadFile(
	        @PathVariable String filename)
	        throws Exception {

	    Resource resource =
	            service.downloadFile(filename);

	    return ResponseEntity.ok()
	    		
	    		//for pdf
	    		 .contentType(
	                     MediaType.APPLICATION_PDF)
	    		 //end for pdf

	            .header(
	                    HttpHeaders.CONTENT_DISPOSITION,
	                    "attachment; filename=\""
	                            + resource.getFilename()
	                            + "\"")

	            .body(resource);
	}
}
