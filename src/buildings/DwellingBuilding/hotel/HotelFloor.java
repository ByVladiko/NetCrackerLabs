package buildings.DwellingBuilding.Hotel;

import buildings.DwellingBuilding.DwellingFloor;
import buildings.Interfaces.Space;

public class HotelFloor extends DwellingFloor {

    private int stars;

    private static final int STARS_CONST = 1;

    public HotelFloor(int flatCount) {
        super(flatCount);
        this.stars = STARS_CONST;
    }

    public HotelFloor(Space[] flats) {
        super(flats);
        this.stars = STARS_CONST;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HotelFloor(" + stars + ", " + flats.length + ", ");
        for (Space flat : flats) {
            sb.append(flat.toString());
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        HotelFloor floor = (HotelFloor) object;
        if (object == this) {
            return true;
        }
        if (!(object instanceof HotelFloor)) {
            return false;
        }
        if (getSpaceCount() != floor.getSpaceCount()) {
            return false;
        }
        for (Space flat : flats) {
            if (flat.equals(floor) == false || getStars() != floor.getStars()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = flats.length ^ stars;
        for (Space flat : flats) {
            result = result ^ flat.hashCode();
        }
        return result;
    }
}
