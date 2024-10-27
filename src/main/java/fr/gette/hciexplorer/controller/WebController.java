package fr.gette.hciexplorer.controller;

import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import fr.gette.hciexplorer.service.EventDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WebController
{
	private final EventDecoder eventDecoder;


	@GetMapping("/decodeEvent")
	@ResponseBody
	public HciMessage decodeEvent(@RequestParam("data") String data)
	{
		IoCtlMessage ioctlMessage = new IoCtlMessage(data);
		HciMessage eventMessage = eventDecoder.decode(ioctlMessage);
		return eventMessage;
	}
}
