package sample.Classes;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Consumer;

/**
 * @author S.Shayan Daneshvar
 */
public final class MapConsumer implements Consumer<String[]> {
    private static final MapConsumer INSTANCE = new MapConsumer();

    private MapConsumer() {
    }

    public static MapConsumer getInstance() {
        return INSTANCE;
    }

    @Override
    public void accept(String... places) {
        StringBuilder locations = new StringBuilder();
        Arrays.stream(places).limit(2)
                .map(x -> x.replace(" ", "-"))
                .forEach(p -> locations.append(p).append(" "));
        try {
            Runtime.getRuntime().exec("java -jar lib/map-utility-0.3" +
                    "-SNAPSHOT.jar " + locations.toString().trim() + " "
                    +/*Time out:*/ "20000");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.err.println("Map Started");
        }
    }

}
