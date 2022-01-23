import java.util.ArrayList;
import java.util.List;

class Course {
    protected String name;
    protected List<Student> students;

    public Course(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", students=" + students +
                '}';
    }

    public void listCourseStudents() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.name);
        builder.append("'s students: ");
        for(Student student : this.students) {
            builder.append(student.getName());
            builder.append(' ');
        }
        System.out.println(builder.toString());
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }
}
