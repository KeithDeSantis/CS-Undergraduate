import java.util.List;

class Dept {

    private String deptName;
    private List<Course> courses;


    public Dept(String deptName, List<Course> courses) {
        this.deptName = deptName;
        this.courses = courses;
    }

    public String getDeptName() {
        return deptName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int countCourses() {
        return this.courses.size();
    }

    public int countStudentsInDeptCourses() {
        int countOfStudents = 0;
        for(Course course : this.courses) {
            countOfStudents += course.getStudents().size();
        }
        return countOfStudents;
    }
}
