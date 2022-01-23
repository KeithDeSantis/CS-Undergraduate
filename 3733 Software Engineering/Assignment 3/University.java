import java.util.Arrays;

public class University {
    public static void main(String[] args) {
        final String[] names = {"Brenda", "Billy", "Charlotte", "Bobby", "Nick", "Tammy", "Jessie", "Kayla",
                "Alan", "Tamara", "Declan", "Ben", "Colby", "Anna", "J"};
        final String[] courseNames = {"Science", "Math", "History", "English", "PE"};
        Student[] students = new Student[names.length];
        Course[] courses = new Course[courseNames.length];
        for(int i = 0; i < students.length; i++) {
            students[i] = new Student(names[i]);
        }
        for(int i = 0; i < courses.length; i++) {
            courses[i] = new Course(courseNames[i]);
        }

        final int NUM_STUDENTS_IN_COURSE = 6;
        int index = 0;
        for(int i = 0; i < courses.length; i++) {
            for(int j = 0; j < NUM_STUDENTS_IN_COURSE; j++) {
                students[index].addCourse(courses[i]);
                index = (index + 1) % students.length;
            }
        }

        students[0].addCourse(courses[1]);
        students[1].addCourse(courses[3]);
        students[2].addCourse(courses[4]);
        Dept STEM = new Dept("STEM", Arrays.asList(courses[0], courses[1]));
        Dept NOT_STEM = new Dept("NOT_STEM", Arrays.asList(courses[2], courses[3], courses[4]));

        College WPI = new College("WPI", Arrays.asList(STEM, NOT_STEM));

        System.out.println("Students in STEM: " + STEM.countStudentsInDeptCourses());
        System.out.println("Students in NOT_STEM: " + NOT_STEM.countStudentsInDeptCourses());
        System.out.println("Students in WPI: " + WPI.countStudentsInCourses());

        System.out.println("Courses in STEM: " + STEM.countCourses());
        System.out.println("Courses in NOT_STEM: " + NOT_STEM.countCourses());
        System.out.println("Courses in WPI: " + WPI.countCourses());

        courses[0].listCourseStudents();
        courses[1].listCourseStudents();
        students[0].listStudentCourses();
        students[1].listStudentCourses();
    }
}
