package Foundation;


import java.time.ZonedDateTime;

public class Route implements Comparable<Route> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates;
    private java.time.ZonedDateTime creationDate;
    private Location location;
    private Long high;
    private EyeColor eyeColor;
    private HairColor hairColor;
    private Country country;


    public Route(long id, String name, Coordinates coordinates, Location location, Long high, EyeColor eyeColor, HairColor hairColor, Country country) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.location = location;
        this.high = high;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public int compareTo(Route route) {
        if (this.high == route.high) {
            return 0;
        } else if (this.high > route.high) {
            return 1;
        } else {
            return -1;
        }
    }


    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name=" + name +
                ", coordinates" + coordinates +
                ", location" + location +
                ", high" + high +
                ", eyeColor" + eyeColor +
                ", hairColor" + hairColor +
                ", country" + country + '\'' +
                '}';
    }



    public Coordinates getCoordinates() {
        return coordinates;
    }
    public ZonedDateTime getCreationDate(){
        return creationDate;
    }
    public Location getLocation() {
        return location;
    }
    public Long getHigh() {
        return high;
    }
    public String getName(){
        return name;
    }
    public EyeColor getEyeColor(){
        return eyeColor;
    }
    public HairColor getHairColor(){
        return hairColor;
    }
    public Country getCountry(){
        return country;
    }


}