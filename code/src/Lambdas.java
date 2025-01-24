import java.util.Arrays;

class Person {
    int age;
    int marks;
    int incomeUSD;

    Person(int age, int marks, int incomeUSD) {
        this.age = age;
        this.marks = marks;
        this.incomeUSD = incomeUSD;
    }

    static int compare(Person a, Person b) {
        if (a.marks != b.marks) return b.marks - a.marks;
        if (a.incomeUSD != b.incomeUSD) return a.incomeUSD - b.incomeUSD;
        return b.age - a.age;
    }

    @Override
    public String toString() {
        return String.format("marks = %d%%, income = %d USD, age = %d yrs", this.marks, this.incomeUSD, this.age);
    }
}

public class Lambdas {

//    public static int comparator(int a, int b) {
//        return b - a;
//    }

    public static void main(String[] args) {
        Integer[] arr = {1, 4, 2, 10, -2};
//        Arrays.sort(arr, Lambdas::comparator);
        Arrays.sort(arr, (a, b) -> b-a);
        for (var a: arr)
            System.out.print(a + " ");
        System.out.println();

        Person[] students = new Person[4];
        students[0] = new Person(18, 90, 5000);
        students[1] = new Person(18, 90, 4000);
        students[2] = new Person(20, 90, 4000);
        students[3] = new Person(20, 88, 4000);

//        Arrays.sort(students, (a, b) -> {
//            if (a.marks != b.marks) return b.marks - a.marks;
//            if (a.incomeUSD != b.incomeUSD) return a.incomeUSD - b.incomeUSD;
//            return b.age - a.age;
//        });
        Arrays.sort(students, Person::compare);
        for (var s: students) System.out.println(s);

        // We must provide our custom comparator function while sorting an array of custom class.
        // Below will not work.
        // Arrays.sort(students);
        // for (var s: students) System.out.println(s);
    }
}
