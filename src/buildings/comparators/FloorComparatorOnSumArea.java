package buildings.comparators;

import buildings.Interfaces.Floor;
import java.util.Comparator;

public class FloorComparatorOnSumArea implements Comparator<Floor> {

    @Override
    public int compare(Floor o1, Floor o2) {
        if (o1.getSumArea() < o1.getSumArea()) {
            return -1;
        }
        if (o1.getSumArea() > o1.getSumArea()) {
            return 1;
        }
        return 0;
    }
}
