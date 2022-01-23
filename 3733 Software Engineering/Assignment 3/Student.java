import java.util.ArrayList;
import java.util.List;

class Student {
    private String name;
    private List<Course> courses;

    public Student(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void listStudentCourses() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.name);
        builder.append("'s courses: ");
        for(Course course : this.courses) {
            builder.append(course.getName());
            builder.append(' ');
        }
        System.out.println(builder.toString());
    }

    public void addCourse(Course course) {
        course.addStudent(this);
        this.courses.add(course);
    }
}
