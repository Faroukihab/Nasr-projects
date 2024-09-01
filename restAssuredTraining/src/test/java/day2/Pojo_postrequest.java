package day2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pojo_postrequest {

    @JsonProperty("name")
    String name;

    @JsonProperty("job")
    String job;

    @JsonProperty("courses")
    String[] courses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String[] getCourses() {
        return courses;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }
}
