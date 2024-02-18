package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class WriteSimplePairingMode extends Command {
    private CommandCode opCode = CommandCode.WRITE_SIMPLE_PAIRING_MODE;
    private SimplePairingMode simplePairingMode;


    public enum SimplePairingMode
    {
        DISABLED(0x00),
        ENABLED(0x01);

        private static final Map<Integer, SimplePairingMode> byValue = new HashMap<>();
        static {
            for (SimplePairingMode e : SimplePairingMode.values()) {
                if (byValue.put(e.getValue(), e) != null) {
                    throw new IllegalArgumentException("duplicate value: " + e.getValue());
                }
            }
        }

        public static SimplePairingMode get(int value) {
            return byValue.get(Integer.valueOf(value));
        }
        private final int value;

        SimplePairingMode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
