import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private LocalDate dateJoined;
    private boolean isForeigner;
    private boolean isMale;

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<Child> children;

    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
            LocalDate dateJoined, boolean isForeigner, boolean isMale) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.dateJoined = dateJoined;
        this.isForeigner = isForeigner;
        this.isMale = isMale;

        children = new LinkedList<Child>();
    }

    public void setMonthlySalary(int grade) {
        int baseSalary;
        if (grade == 1) {
            baseSalary = 3000000;
        } else if (grade == 2) {
            baseSalary = 5000000;
        } else if (grade == 3) {
            baseSalary = 7000000;
        } else {
            return;
        }

        if (isForeigner) {
            baseSalary = (int) (baseSalary * 1.5);
        }

        setCalculatedMonthlySalary(baseSalary);
    }

    private void setCalculatedMonthlySalary(int salary) {
        this.monthlySalary = salary;
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = idNumber;
    }

    public void addChild(String childName, String childIdNumber) {
        children.add(new Child(childName, childIdNumber));
    }

    public int getAnnualIncomeTax() {
        int monthWorkingInYear = 12;
        if (dateJoined.getYear() == LocalDate.now().getYear()) {
            monthWorkingInYear = LocalDate.now().getMonthValue() - dateJoined.getMonthValue();
        }
        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible,
                spouseIdNumber.equals(""), children.size());
    }

    // Inner class for Child
    private class Child {
        private String name;
        private String idNumber;

        public Child(String name, String idNumber) {
            this.name = name;
            this.idNumber = idNumber;
        }
    }
}
