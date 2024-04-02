import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
public class ClassTeacher {
    // Pola
    private String groupName;
    private List<Teacher> teachers;
    private double maxTeachers;

    public ClassTeacher(String groupName, double maxTeachers) {
        this.groupName = groupName;
        this.maxTeachers = maxTeachers;
        this.teachers = new ArrayList<>();
    }


    public void addTeacher(Teacher teacher) {
        if (teachers.size() >= maxTeachers) {
            System.out.println("Cannot add teacher. Maximum capacity reached.");
            return;
        }

        for (Teacher t : teachers) {
            if (t.getFirstName().equals(teacher.getFirstName()) && t.getLastName().equals(teacher.getLastName())) {
                System.out.println("Teacher with the same name already exists in the group.");
                return;
            }
        }

        teachers.add(teacher);
        System.out.println("Teacher added to the group: " + teacher.getFirstName() + " " + teacher.getLastName());
    }

    public void addSalary(Teacher teacher, double salary){
        teacher.setSalary(teacher.getSalary()+salary);
    }

    public void removeTeacher(Teacher teacher) {
        if (teachers.remove(teacher)) {
            System.out.println("Teacher removed from the group: " + teacher.getFirstName() + " " + teacher.getLastName());
            teacher = null;
            System.gc();

        } else {
            System.out.println("Teacher not found in the group.");
        }
    }

    public void changeCondition(Teacher teacher, TeacherCondition condition){
        teacher.setCondition(condition);
    }

    public Teacher search(String lastName) {
        for (Teacher teacher : teachers) {
            if (teacher.getLastName().compareTo(lastName) == 0) {
                System.out.println("The same lastname");
                return teacher;
            }
        }
        return null;
    }


    public List<Teacher> searchPartial(String partialName) {
        List<Teacher> matchingTeachers = new ArrayList<>();
        for (Teacher teacher : teachers) {
            if (teacher.getLastName().contains(partialName) || teacher.getFirstName().contains(partialName)) {
                matchingTeachers.add(teacher);
            }
        }
        return matchingTeachers;
    }


    public int countByCondition(TeacherCondition condition){
        int numberOfTeachers = 0;
        for(Teacher teacher: teachers )
        {
            if(teacher.getCondition()==condition)
            {
                numberOfTeachers++;
            }
        }
        return  numberOfTeachers;
    }



    public void summary(){
        teachers.forEach((teacher) -> {
            teacher.printing();
        });
    }

    public List<Teacher> sortByName() {
        List<Teacher> sortedTeachers = new ArrayList<>(teachers);
        Collections.sort(sortedTeachers);
        return sortedTeachers;
    }

    public List<Teacher> sortBySalary() {
        List<Teacher> sortedTeachers = new ArrayList<>(teachers);

        Comparator<Teacher> salaryComparator = new Comparator<Teacher>() {
            @Override
            public int compare(Teacher teacher1, Teacher teacher2) {
                return Double.compare(teacher2.getSalary(), teacher1.getSalary());
            }
        };
        Collections.sort(sortedTeachers, salaryComparator);

        return sortedTeachers;
    }

    public Teacher max() {
        if (teachers.isEmpty()) {
            return null;
        }
        return Collections.max(teachers);
    }





    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public double getMaxTeachers() {
        return maxTeachers;
    }

    public void setMaxTeachers(int maxTeachers) {
        this.maxTeachers = maxTeachers;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}