public interface profile {
    public String  fname=null;
    public String lname=null; 
    public String password=null;
    public String userid=null;
    public String dob=null;
    public int age=0;
    public double height=0;
    public double weight=0;
    public double bmi=0;



	public void Connect();
    public void setID(int i);
    public void getdata(); 
    public void setBMI(double h, double w);
    public void update();
    public void reset();

}