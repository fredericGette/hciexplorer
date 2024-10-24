package fr.gette.hciexplorer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.service.EventDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Slf4j
@RequiredArgsConstructor
public class HciexplorerRunner  implements CommandLineRunner {

    private final EventDecoder eventDecoder;

    @Override
    public void run(String... args) {
        for (int i = 0; i < args.length; ++i) {
            log.info("args[{}]: {}", i, args[i]);
        }

        Scanner in = new Scanner(System.in);

        System.out.println("Input event data");
        String data = in.nextLine();
        HciMessage eventMessage = eventDecoder.decode(data);
        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT); //pretty print
        String s = null;
        try {
            s = om.writeValueAsString(eventMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(s);
    }
}
