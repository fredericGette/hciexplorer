package fr.gette.hciexplorer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.gette.hciexplorer.hciSpecification.AclDirection;
import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.hciSpecification.HciPacketType;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import fr.gette.hciexplorer.service.CommandDecoder;
import fr.gette.hciexplorer.service.DataDecoder;
import fr.gette.hciexplorer.service.EventDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
@RequiredArgsConstructor
@Profile("cli")
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

        while(true) {
            Scanner in = new Scanner(System.in);

            System.out.println("\nInput hexadecimal data (q=quit, example of valid input=03 00 00 00 01 03 0C 00)");
            System.out.print(">");
            String data = in.nextLine();

            if (data != null && data.toLowerCase().startsWith("q")) {
                System.out.println("Bye!");
                break;
            }

            IoCtlMessage ioctlMessage = null;
            try {
                ioctlMessage = new IoCtlMessage(data);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Not a hexadecimal number. "+e.getMessage());
                continue;
            }
            if (ioctlMessage.getDataLength() < 5) {
                System.out.println("Data must be at least 5 bytes long.");
                continue;
            }
            long size = ioctlMessage.read4octets();
            if (ioctlMessage.getDataLength()-size < 5) {
                System.out.println(String.format("Expected %d bytes, found only %d", size+5, ioctlMessage.getDataLength()));
                continue;
            }
            short hciPacketTypeValue = ioctlMessage.read1octet();
            HciPacketType hciPacketType = HciPacketType.get(hciPacketTypeValue);
            if (hciPacketType == null) {
                System.out.println(String.format("Unknown packet type : 0x%02X", hciPacketTypeValue));
                continue;
            }

            System.out.println(String.format("Detected %s", hciPacketType.name()));
            if (HciPacketType.ACLDATA.equals(hciPacketType)) {
                System.out.println(String.format("ACL direction is unknown. Assuming %s", AclDirection.CONTROLLER_TO_HOST));
            }

            HciMessage hciMessage = null;
            switch (hciPacketType) {
                case COMMAND -> hciMessage = commandDecoder.decode(ioctlMessage);
                case ACLDATA -> hciMessage = dataDecoder.decodeRead(ioctlMessage); // AclDirection.CONTROLLER_TO_HOST
                case SCODATA -> System.out.println("SCO Data unsupported.");
                case EVENT -> hciMessage = eventDecoder.decode(ioctlMessage);
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

        }
    }
}
