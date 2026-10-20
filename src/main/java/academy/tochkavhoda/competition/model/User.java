package academy.tochkavhoda.competition.model;

import java.util.List;

public class User {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String company;
    private final String login;
    private final String password;
    private final Role role;
    private final List<String> directions;

    public User(int id, String firstName, String lastName, String company, String login, String password, Role role, List<String> directions) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.login = login;
        this.password = password;
        this.role = role;
        this.directions = java.util.Collections.unmodifiableList(new java.util.ArrayList<>(directions));
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public List<String> getDirections() {
        return directions;
    }
}
