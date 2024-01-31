package fr.gette.hciexplorer.controller;

import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.service.MessageDecoder;
import fr.gette.hciexplorer.service.Parser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RootController
{
	private final Parser parser;
	private final MessageDecoder messageDecoder;

	@PostMapping("/upload")
	public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException
	{
		log.info("Uploaded file [{}] {} bytes", file.getOriginalFilename(), file.getSize());
		parser.parse(file.getInputStream());
	}

	@GetMapping("/readMessage/{id}")
	@ResponseBody
	public HciMessage getReadMessage(@PathVariable Long id)
	{
		HciMessage readMessage = messageDecoder.decodeReadMessage(id);
		return readMessage;
	}
}
