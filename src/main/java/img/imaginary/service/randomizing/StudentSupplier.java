package img.imaginary.service.randomizing;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import img.imaginary.service.entity.Student;

public class StudentSupplier implements DataSupplier<Student> {

    private static final Random random = new Random();
    private static final int STUDENTS_QUANTITY = 200;

    private class FirstName {

        String name;
        Enum<Gender> gender;

        public FirstName(String name, Enum<Gender> gender) {
            this.name = name;
            this.gender = gender;
        }
    }

    private enum Gender {
        MALE, FEMALE
    }

    @Override
    public Set<Student> supplyData() {
        FirstName[] firstNames = createFirstNames();
        String[] lastNamesMale = createMaleLastNames();
        String[] lastNamesFemale = createFemaleLastNames();
        HashSet<Student> students = new HashSet<>();
        while (students.size() < STUDENTS_QUANTITY) {
            FirstName firstName = firstNames[random.nextInt(20)];
            String lastName;
            if (firstName.gender == Gender.MALE) {
                lastName = lastNamesMale[random.nextInt(20)];
            } else {
                lastName = lastNamesFemale[random.nextInt(20)];
            }
            students.add(new Student(firstName.name, lastName));

        }
        return students;
    }

    private String[] createMaleLastNames() {
        return new String[] { "Ivanov", "Doe", "Smith", "Brown", "Davis", "Robinson", "Martin", "Petrov", "Sidorov",
                "White", "Valeev", "Kuzmich", "Gromov", "Jonhson", "Siliverstov", "Walker", "Mitchell", "Carter",
                "Clark", "Kolobov", "Shaban" };
    }

    private String[] createFemaleLastNames() {
        return new String[] { "Ivanova", "Jones", "Lee", "Ross", "Hill", "Spencer", "Zaharova", "Petrova", "Sidorova",
                "Taylor", "Wood", "Belaeva", "Kudravceva", "Nefedova", "Clark", "Hill", "Richardson", "King",
                "Solodova", "Strelec" };
    }

    private FirstName[] createFirstNames() {
        return new FirstName[] { new FirstName("Andrew", Gender.MALE), new FirstName("Denis", Gender.MALE),
                new FirstName("Anna", Gender.FEMALE), new FirstName("Kate", Gender.FEMALE),
                new FirstName("Winston", Gender.MALE), new FirstName("Bobby", Gender.MALE),
                new FirstName("Mary", Gender.FEMALE), new FirstName("Alexandr", Gender.MALE),
                new FirstName("Jimmy", Gender.MALE), new FirstName("Donald", Gender.MALE),
                new FirstName("katya", Gender.FEMALE), new FirstName("Victoria", Gender.FEMALE),
                new FirstName("Joseph", Gender.MALE), new FirstName("Dmitry", Gender.MALE),
                new FirstName("Valery", Gender.MALE), new FirstName("May", Gender.FEMALE),
                new FirstName("Georgy", Gender.MALE), new FirstName("Noah", Gender.MALE),
                new FirstName("Barbara", Gender.FEMALE), new FirstName("Anna", Gender.FEMALE) };
    }
}
