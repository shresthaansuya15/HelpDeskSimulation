public class Student 
{
    private String name;
    private int course;
    private int workload;
    private int arrivalTime;

    public Student(String name, int course, int workload, int arrivalTime) 
    {
        this.name = name;
        this.course = course;
        this.workload = workload;
        this.arrivalTime = arrivalTime;
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

    public int getArrivalTime() 
    {
        return arrivalTime;
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
