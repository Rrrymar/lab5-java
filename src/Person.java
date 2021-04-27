import javax.xml.bind.annotation.*;
import java.time.*;



@XmlRootElement(name = "emloyee")
@XmlAccessorType(XmlAccessType.NONE)
public class Person {
    // Поля ID
    @XmlElement
    private long id;
    // Поля name
    @XmlElement
    private String name;
    // Поля Coordinates
    @XmlElement
    private Coordinates coordinates;
    // Поля time
    @XmlElement
    private java.time.ZonedDateTime creationData;
    // Поле height
    @XmlElement
    private double height;
    // Поле цвет глаз
    @XmlElement
    private EyeColor eyeColor;
    // Поле цвет волос
    @XmlElement
    private HairColor hairColor;
    // Поле национальности
    @XmlElement
    private Country nationality;
    // Поле локации
    @XmlElement
    private Location location;

    public Person (long id, String name, Coordinates coordinates, java.time.ZonedDateTime creationData, double height, EyeColor eyeColor, HairColor hairColor, Country nationality, Location location) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationData = creationData;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    // Коструктор Person
    public Person() {}

    // для записи в строку всех параметров Person
    @Override
    public String toString() {
        return "Person{ id =" + id + ", name = " + name +
                ", coordinates = " + coordinates +
                ", creationData = " + creationData +
                ", height = " + height +
                ", eyeColor = " + hairColor +
                ", nationality = " + nationality +
                ", location = " + location +"}";
    }

    public long getId(){
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Coordinates getCoordinates(){
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
    public java.time.ZonedDateTime getCreationData(){
        return creationData;
    }
    public void setCreationData(java.time.ZonedDateTime creationData){
        this.creationData = creationData;
    }
    public double getHeight(){
        return height;
    }
    public void setHeight(double height){
        this.height = height;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }
    public void setEyeColor(EyeColor eyeColor){
        this.eyeColor = eyeColor;
    }
    public HairColor getHairColor(){
        return hairColor;
    }
    public void setHairColor(HairColor hairColor){
        this.hairColor = hairColor;
    }
    public Country getNationality(){
        return nationality;
    }
    public void setNationality(Country nationality){
        this.nationality = nationality;
    }
    public Location getLocation(){
        return location;
    }
    public void setLocation(Location location){
        this.location = location;
    }
    public String returnCreationData() {
        return ZonedDateTime.now().toString();
    }
}
