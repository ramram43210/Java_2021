public class Employee {

	private Integer employeeId;
	private String name;
	private String company;
	private String emailId;

	public Employee() {}

	public Employee(Integer employeeId, String name,
					String company, String emailId)
	{
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.company = company;
		this.emailId = emailId;
	}

	public Integer getEmployeeId() { return employeeId; }

	public void setEmployeeId(Integer employeeId)
	{
		this.employeeId = employeeId;
	}

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getCompany() { return company; }

	public void setCompany(String company)
	{
		this.company = company;
	}

	public String getEmailId() { return emailId; }

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	@Override public String toString()
	{
		return "Employee ["
			+ "employeeId=" + employeeId + ", name=" + name
			+ ", "
			+ " company=" + company + ", emailId=" + emailId
			+ "]";
	}
}
