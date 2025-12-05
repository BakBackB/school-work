public class Student implements Comparable<Student>
{
    private String fname, lname;
    private int grade;
    
    public Student(String fname, String lname, int grade)
    {
        this.fname = fname;
        this.lname = lname;
        this.grade = grade;
    }

    public String toString()
    {
        return fname + " " + lname + "\t" + grade;
    }

    // compare by grade (lower grade => negative), then by last name, then first name
    @Override
    public int compareTo(Student other)
    {
        if (other == null) return 1;
        int gradeCompare = Integer.compare(this.grade, other.grade);
        if (gradeCompare != 0) return gradeCompare;
        int lnameCompare = this.lname.compareTo(other.lname);
        if (lnameCompare != 0) return lnameCompare;
        return this.fname.compareTo(other.fname);
    }

    public static void insertionSortStudent(Student[] a, int nElems)
      {
      int in, out;

      for(out=1; out<nElems; out++)     // out is dividing line
         {
         Student temp = a[out];            // remove marked item
         in = out;                      // start shifts at out
         while(in>0 && a[in-1].compareTo(temp)>0) // until one is smaller,
            {
            a[in] = a[in-1];            // shift item to right
            --in;                       // go left one position
            }
         a[in] = temp;                  // insert marked item
         }  // end for
      }  // end insertionSort()
    public static void displayStudents(Student[] a, int nElems)
        {
        for(int j=0; j<nElems; j++)       // for each element,
           System.out.println(a[j]);  // display it
        System.out.println("");
        }

    public static void main(String[] args)
    {
        Student[] students = new Student[10];
        students[0] = new Student("John", "Doe", 90);
        students[1] = new Student("Jane", "Smith", 85);
        students[2] = new Student("Alice", "Johnson", 92);
        students[3] = new Student("Bob", "Brown", 78);
        students[4] = new Student("Charlie", "Davis", 88);
        students[5] = new Student("Eve", "Miller", 95);
        students[6] = new Student("Frank", "Wilson", 80);
        students[7] = new Student("Grace", "Moore", 89);
        students[8] = new Student("Henry", "Taylor", 91);
        students[9] = new Student("Ivy", "Anderson", 87);
        Student.displayStudents(students, 10);
        Student.insertionSortStudent(students, 10);
        Student.displayStudents(students, 10);
    }
}
