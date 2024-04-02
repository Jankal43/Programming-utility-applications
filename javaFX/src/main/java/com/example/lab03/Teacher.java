public class Teacher implements Comparable<Teacher>  {
    private String firstName;
    private String lastName;
    private TeacherCondition condition;
    private int birthYear;
    private double salary;

    public Teacher(String firstName, String lastName, TeacherCondition condition, int birthYear, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.condition = condition;
        this.birthYear = birthYear;
        this.salary = salary;
    }


    public void printing() {
        System.out.println("Teacher Information:");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Condition: " + condition);
        System.out.println("Birth Year: " + birthYear);
        System.out.println("Salary: " + salary);
    }

    @Override
    public int compareTo(Teacher otherTeacher) {
        return this.lastName.compareTo(otherTeacher.lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public TeacherCondition getCondition() {
        return condition;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public double getSalary() {
        return salary;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCondition(TeacherCondition condition) {
        this.condition = condition;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
