package hu.ulyssys.java.course.database.jdbc;

public class Dog {
    private Long id;
    private String name;
    private String color;
    private String species;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append("Id: ").append(id)
                .append(" name: ").append(name)
                .append(" color: ").append(color)
                .append(" species: ").append(species);

        return strb.toString();
    }
}
