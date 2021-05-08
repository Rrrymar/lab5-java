package Foundation;

import java.time.ZonedDateTime;

public class Route implements Comparable<Route> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location location; //Поле может быть null`
    private EyeColor eyeColor; //Поле не может быть null, Значение поля должно быть больше 1
    private HairColor hairColor;
    private Country country;

    public HairColor getHairColor(){
        return hairColor;
    }
    public EyeColor getEyeColor(){
        return eyeColor;
    }
    public Country getCountry() {
        return country;
    }



    public Route(long id, String name, Coordinates coordinates, Location location, EyeColor eyeColor, HairColor hairColor, Country country) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.location = location;
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

    public Long getCreationData() {
        return creationDate;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
    public void setEyeColor(String eyeColor){
        if (eyeColor.equals("GREEN")){
            this.eyeColor = EyeColor.GREEN;
        }
        if (eyeColor.equals("RED")){
            this.eyeColor = EyeColor.RED;
        }
        if (eyeColor.equals("YELLOW")){
            this.eyeColor = EyeColor.YELLOW;
        }
    }
    public void setHairColor(String hairColor){
        if (hairColor.equals("RED")){
            this.hairColor = HairColor.RED;
        }
        if (hairColor.equals("BLUE")){
            this.hairColor = HairColor.BLUE;
        }
        if (hairColor.equals("GREEN")){
            this.hairColor = HairColor.GREEN;
        }
    }
    public void setCountry(String country){
        if (country.equals("INDIA")){
            this.country = Country.INDIA;
        }
        if (country.equals("VATICAN")){
            this.country = Country.VATICAN;
        }
        if (country.equals("ITALY")){
            this.country = Country.ITALY;
        }
    }

}