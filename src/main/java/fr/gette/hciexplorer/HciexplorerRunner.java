package fr.gette.hciexplorer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.service.CommandDecoder;
import fr.gette.hciexplorer.service.DataDecoder;
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
    private final CommandDecoder commandDecoder;
    private final DataDecoder dataDecoder;

    @Override
    public void run(String... args) {
        for (int i = 0; i < args.length; ++i) {
            log.info("args[{}]: {}", i, args[i]);
        }

        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT); //pretty print

        String type = null;
        do {
            Scanner in = new Scanner(System.in);

            System.out.println("\nInput data (prefix: e=event, c=command, r=readData, w=writeData, q=quit):");
            String data = in.nextLine();
            type = data.substring(0,1).toLowerCase();
            data = data.substring(1);

            HciMessage hciMessage = null;

            switch (type) {
                case "e":
                    hciMessage = eventDecoder.decode(data);
                    break;
                case "c":
                    hciMessage = commandDecoder.decode(data);
                    break;
                case "r":
                    hciMessage = dataDecoder.decodeRead(data);
                    break;
                case "w":
                    hciMessage = dataDecoder.decodeWrite(data);
                    break;
                case "q":
                    System.out.println("Bye!");
                    continue;
                default:
                    System.out.println("Unknown type.");
                    continue;
            }

            if (hciMessage != null) {
                String jsonString = null;
                try {
                    jsonString = om.writeValueAsString(hciMessage);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(jsonString);
            }

        } while (!"q".equals(type));
    }
}
