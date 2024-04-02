import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class ClassContainer {
    private Map<String, ClassTeacher> classes;

    public ClassContainer() {
        this.classes = new HashMap<>();
    }

    public Map<String, ClassTeacher> getClasses() {
        return classes;
    }

    public void addClass(String className, double maxCapacity) {
        if (!classes.containsKey(className)) {
            classes.put(className, new ClassTeacher(className, maxCapacity));
            System.out.println("Class " + className + " added successfully.");
        } else {
            System.out.println("Class " + className + " already exists.");
        }
    }


    public void removeClass(String className) {
        if (classes.containsKey(className)) {
            classes.remove(className);
            System.out.println("Class " + className + " removed successfully.");
        } else {
            System.out.println("Class " + className + " does not exist.");
        }
    }

    public List<String> findEmpty() {
        List<String> emptyClasses = new ArrayList<>();
        for (Map.Entry<String, ClassTeacher> entry : classes.entrySet()) {
            if (entry.getValue().getTeachers().isEmpty()) {
                emptyClasses.add(entry.getKey());
            }
        }
        return emptyClasses;
    }

    public void summary() {
        for (Map.Entry<String, ClassTeacher> entry : classes.entrySet()) {
            String className = entry.getKey();
            double filledPercentage = (entry.getValue().getTeachers().size() / entry.getValue().getMaxTeachers()) * 100;
            System.out.println("Class: " + className + ", Filled Percentage: " + filledPercentage + "%");
        }
    }
}
