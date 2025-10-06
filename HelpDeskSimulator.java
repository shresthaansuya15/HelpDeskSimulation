import java.util.Scanner;

public class HelpDeskSimulator
{
    public static void main(String[] args) 
    {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("\n===== Welcome to Help Desk Simulation =====\n");

        System.out.print("Enter total simulation minutes: ");
        int totalSimulationMinutes = Integer.parseInt(stdIn.nextLine());

        ArrayBoundedQueue<Student> futureArrivals = new ArrayBoundedQueue<>(100);

        HelpDesk helpDesk = new HelpDesk();

        System.out.println("\nEnter student info in this format: \n"
                            + "(arrivalTime name courseNumber workloadTime)");
        System.out.println("Type DONE when finished!!");

        // Read all students
        while (true)
        {
            System.out.print("> ");
            String input = stdIn.nextLine();

            if (input.equalsIgnoreCase("DONE"))
            {
                break;
            }

            String[] parts = input.split(" ");

            int arrivalTime = Integer.parseInt(parts[0]);
            String studentName = parts[1];
            int courseNumber = Integer.parseInt(parts[2]);
            int workload = Integer.parseInt(parts[3]);

            futureArrivals.enqueue(new Student(arrivalTime, studentName, courseNumber, workload));
        }

        // Run simulation minute by minute
        for (int currentMinute = 0; currentMinute < totalSimulationMinutes; currentMinute++)
        {
            int studentsToCheck = futureArrivals.length();
            for (int i = 0; i < studentsToCheck; i++)
            {
                Student student = futureArrivals.dequeue();

                if (student.getArrivalTime() <= currentMinute)
                {
                    helpDesk.addStudent(student.getName(), student.getCourse(), student.getWorkload());
                }
                else
                {
                    futureArrivals.enqueue(student);
                }
            }

            // Print current status
            System.out.println(helpDesk.toString()); 
            
            // Advance HelpDesk
            helpDesk.step();
            } 
        
        System.out.println("\nLOG:");
        System.out.print(helpDesk.getLog());    
        stdIn.close();
    }
}
    

