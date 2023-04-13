package lib;

public class TaxFunction {
    
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, WorkingMonth numberOfMonthWorking, 
                                   int deductible, MarriageStatus marriageStatus, NumberOfChildren numberOfChildren) {
        int tax = 0;
        
        if (numberOfMonthWorking.getValue() > 12) {
            System.err.println("More than 12 month working per year");
        }
        
        int nonTaxableIncome = NonTaxableIncomeCalculator.calculate(marriageStatus, numberOfChildren);
        int taxableIncome = calculateTaxableIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking, deductible, nonTaxableIncome);
        tax = (int) Math.round(0.05 * taxableIncome);
        
        if (tax < 0) {
            return 0;
        } else {
            return tax;
        }
    }
    
    private static int calculateTaxableIncome(int monthlySalary, int otherMonthlyIncome, WorkingMonth numberOfMonthWorking, 
                                              int deductible, int nonTaxableIncome) {
        int taxableIncome = ((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking.getValue()) - deductible - nonTaxableIncome;
        
        if (taxableIncome < 0) {
            taxableIncome = 0;
        }
        
        return taxableIncome;
    }
}

class WorkingMonth {
    private int value;

    public WorkingMonth(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

class MarriageStatus {
    private boolean isMarried;

    public MarriageStatus(boolean isMarried) {
        this.isMarried = isMarried;
    }

    public boolean isMarried() {
        return isMarried;
    }
}

class NumberOfChildren {
    private int value;
    private static final int MAX_CHILDREN = 3;

    public NumberOfChildren(int value) {
        this.value = Math.min(value, MAX_CHILDREN);
    }

    public int getValue() {
        return value;
    }
}

class NonTaxableIncomeCalculator {
    private static final int BASE_NON_TAXABLE_INCOME = 54000000;
    private static final int NON_TAXABLE_INCOME_PER_CHILD = 4500000;
    private static final int NON_TAXABLE_INCOME_FOR_MARRIED = 4500000;
    
    public static int calculate(MarriageStatus marriageStatus, NumberOfChildren numberOfChildren) {
        int nonTaxableIncome = BASE_NON_TAXABLE_INCOME;
        
        if (marriageStatus.isMarried()) {
            nonTaxableIncome += NON_TAXABLE_INCOME_FOR_MARRIED;
        }
        
        nonTaxableIncome += numberOfChildren.getValue() * NON_TAXABLE_INCOME_PER_CHILD;
        
        return nonTaxableIncome;
    }
}


