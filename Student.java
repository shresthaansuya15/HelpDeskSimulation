public class Student 
{
    private String name;
    private int course;
    private int workload;
    private int arrivalTime;

    public Student(int arrivalTime, String name, int course, int workload) 
    {
        this.arrivalTime = arrivalTime;
        this.name = name;
        this.course = course;
        this.workload = workload;
    }

    public int getArrivalTime() 
    {
        return arrivalTime;
    }

    public String getName() 
    {
        return name;
    }

    public int getCourse() 
    {
        return course;
    }

    public int getWorkload() 
    {
        return workload;
    }

    public int getCourseLevel()
    {
        int courseLevel = (course / 100) * 100;

        if (courseLevel < 100)
        {
            return 100;
        }
        else if (courseLevel > 400)
        {
            return 400;
        }

        return courseLevel;    
    }    
}
