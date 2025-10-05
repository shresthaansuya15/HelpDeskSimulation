public class HelpDesk 
{
    private ArrayBoundedQueue<Student>[] courseLevelQueues;
    private Student currentStudent;
    private int remainingWorkTime;
    private String helpDeskLog;
    private int simulationTime;
    private int totalStudentsServed;
    private int totalWaitTime;

    public HelpDesk()
    {
        // an array to hold 4 queues
        courseLevelQueue = new ArrayBoundedQueue[4];

        for (int queueIndex = 0; queueIndex < queues.length; queueIndex++) 
        {
            // Each course level can hold up to 3 students
            courseLevelQueues[queueIndex] = new ArrayBoundedQueue<>(3);
        }

            currentStudent = null;
            remainingWorkTime = 0;
            helpDeskLog = "";
            simulationTime = 0;
            totalStudentsServed = 0;
            totalWaitTime = 0;

            /*
            1. queues[0] -> queue for 100-level courses
            2. queues[1] -> queue for 200-level courses
            3. queues[2] -> queue for 300-level courses
            4. queues[3] -> queue for 400-level courses
            */
          
    }

    // ---------------------------------------------
    // Adding a student to their respective queue
    // ---------------------------------------------
    public void addStudent(String studentName, int course, int workload)
    {
        Student pio = new Student(studentName, course, workload, time);

        int initialQueueIndex = (pio.getCourseLevel() / 100) - 1;
        boolean student_added = false;

        // Enqueue student in their course level or escalate to higher levels if full
        for (int queueIndex = initialQueueIndex; queueIndex < 4; queueIndex++)
        {
            if (!courseLevelQueues[queueIndex].isFull())
            {
                courseLevelQueues[queueIndex].enqueue(pio);
                helpDeskLog = helpDeskLog + "Time " + simulationTime
                              + ", Queued " + pio.getName()
                              + " from CSC" + pio.getCourse() + "\n";
                
                student_added = true;

                break;
            }
        }

        // If all queues are full, student is turned away
        if (!student_added)
        {
            helpDeskLog = helpDeskLog + "Time " + simulationTime
                          + ", Turned away " + pio.getName()
                          + " from CSC" + pio.getCourse() + "\n";                  
        }
    }

    // ---------------------------------------------
    // Advancing simulation by 1 minute
    // ---------------------------------------------
    public void step()
    {
        if (currentStudent != null)
        {
            remainingWorkTime--;        // reducing workload of current student being helped by 1 minute

            // completion of log if finished
            if (remainingWorkTime == 0)
            {
                helpDeskLog = helpDeskLog + "Time " + simulationTime
                              + ", Finished helping " + currentStudent.getName()
                              + " from CSC" + currentStudent.getCourse() + "\n";

                currentStudent = null;   
            }
        }

        // if no student is being helped, picking next student from highest-priority non-empty queue
        if (currentStudent == null)
        {
            for (int queueIndex = 0; queueIndex < 4; queueIndex++)
            {
                if (!courseLevelQueues[queueIndex].isEmpty())
                {
                    currentStudent = courseLevelQueues[queueIndex].dequeue();
                    remainingWorkTime = currentStudent.getWorkload();
                    helpDeskLog = helpDeskLog + "Time " + simulationTime
                                  + ", Started helping " + currentStudent.getName()
                                  + " from CSC" + currentStudent.getCourse() + "\n";

                    break;
                }
            }
        }

        // Advancing the simulation clock
        simulationTime++;
    }

    
}
