import java.util.List;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        Teacher t1 = new Teacher("Kasia","Aowal",TeacherCondition.OBECNY,1990,350.00);
        Teacher t2 = new Teacher("Ania","Bowalska",TeacherCondition.OBECNY,1990,150.00);
        Teacher t3 = new Teacher("Beata","Aowak",TeacherCondition.OBECNY,1990,550.00);
//        Teacher t2 = new Teacher("Kasia","Kowal",TeacherCondition.OBECNY,1990,250.00);

        t1.printing();

        ClassTeacher class1 = new ClassTeacher("1b", 5);


        class1.addTeacher(t1);
        class1.addTeacher(t2);
        class1.addTeacher(t3);




//

//        class1.getTeachers().forEach((teacher) -> {
//            System.out.println(teacher.getFirstName() + " " + teacher.getLastName()+ " " + teacher.getSalary());
//        });
//
//        class1.sortBySalary().forEach((teacher) -> {
//            System.out.println(teacher.getFirstName() + " " + teacher.getLastName()+ " " + teacher.getSalary());
//        });


        ClassContainer container = new ClassContainer();

        // Dodajemy kilka klas nauczycielskich
        container.addClass("Math", 30);
        container.addClass("Physics", 25);
        container.addClass("English", 20);

        // Wyświetlamy informacje o klasach
        container.summary();

        // Usuwamy jedną z klas nauczycielskich
        container.removeClass("Physics");

        // Wyświetlamy informacje o klasach po usunięciu
        container.summary();

        container.getClasses().get("Math").addTeacher(new Teacher("John", "Doe", TeacherCondition.OBECNY, 1980, 5000.0));


        container.summary();

        List<String> emptyClasses = container.findEmpty();
        System.out.println("Empty classes:");
        for (String className : emptyClasses) {
            System.out.println(className);
        }

    }
}