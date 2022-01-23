import java.util.List;

class College {

    private String collegeName;
    private List<Dept> depts;

    public College(String collegeName, List<Dept> depts) {
        this.collegeName = collegeName;
        this.depts = depts;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public List<Dept> getDepts() {
        return depts;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public void setDepts(List<Dept> depts) {
        this.depts = depts;
    }

    public int countCourses() {
        int numCourses = 0;
        for(Dept dept : depts){
            numCourses += dept.countCourses();
        }
        return numCourses;
    }

    public int countStudentsInCourses() {
        int numStudents = 0;
        for(Dept dept : this.depts) {
            numStudents += dept.countStudentsInDeptCourses();
        }
        return numStudents;
    }
}
