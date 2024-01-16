package fr.gette.hciexplorer.controller;

import fr.gette.hciexplorer.service.Parser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RootController
{
	private final Parser parser;

	@PostMapping(path="upload")
	public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException
	{
		log.info("Uploaded file [{}] {} bytes", file.getOriginalFilename(), file.getSize());
		parser.parse(file.getInputStream());
	}
}
