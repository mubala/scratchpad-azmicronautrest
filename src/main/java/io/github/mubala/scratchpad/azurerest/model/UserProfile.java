package io.github.mubala.scratchpad.azurerest.model;

public class UserProfile {

  private String name;
  private int age;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  private String email;

  public UserProfile() {}

  public UserProfile(String name, int age, String email) {
    this.name = name;
    this.age = age;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "UserProfile{"
        + "name='"
        + name
        + '\''
        + ", age="
        + age
        + ", email='"
        + email
        + '\''
        + '}';
  }
}
