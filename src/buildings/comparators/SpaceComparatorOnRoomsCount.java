package buildings.comparators;

import buildings.Interfaces.Space;
import java.util.Comparator;

public class SpaceComparatorOnRoomsCount implements Comparator<Space> {

    @Override
    public int compare(Space o1, Space o2) {
        if (o1.getRoomCount() < o1.getRoomCount()) {
            return -1;
        }
        if (o1.getRoomCount() > o1.getRoomCount()) {
            return 1;
        }
        return 0;
    }
}
