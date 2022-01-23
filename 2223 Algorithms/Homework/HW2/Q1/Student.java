public class Student implements Comparable<Student>
{

    public int id;

    public String name;

    public String year;

    public Student(int id, String name, String year)
    {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public int compareTo(Student std)
    {
        return this.id - std.id;
    }
}
