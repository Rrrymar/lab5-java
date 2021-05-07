package Foundation;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class Route implements Comparable<Route> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location location; //Поле может быть null
    private enum EyeColor eyeColor; //Поле не может быть null, Значение поля должно быть больше 1
    private enum HairColor hairColor;


    public Route(long id, String name, Coordinates coordinates, Location location, EyeColor eyeColor, HairColor hairColor) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.location = location;
        this.eyeColor = Foundation.EyeColor;
        this.hairColor = Foundation.HairColor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int compareTo(Route route) {
        if (this.distance == route.distance) {
            return 0;
        } else if (this.distance > route.distance) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", location=" + location +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                '}';
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Location getLocation() {
        return location;
    }

    public Location getTo() {
        return to;
    }

    public Long getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }
}