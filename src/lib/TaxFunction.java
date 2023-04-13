package lib;

public class TaxFunction {
    
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, WorkingMonth numberOfMonthWorking, 
                                   int deductible, MarriageStatus marriageStatus, NumberOfChildren numberOfChildren) {
        int tax = 0;
        
        if (numberOfMonthWorking.getValue() > 12) {
            System.err.println("More than 12 month working per year");
        }
        
        if (numberOfChildren.getValue() > 3) {
            numberOfChildren = new NumberOfChildren(3);
        }
        
        int nonTaxableIncome = NonTaxableIncomeCalculator.calculate(marriageStatus, numberOfChildren);
        int taxableIncome = ((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking.getValue()) - deductible - nonTaxableIncome;
        tax = (int) Math.round(0.05 * taxableIncome);
        
        if (tax < 0) {
            return 0;
        } else {
            return tax;
        }
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

    public NumberOfChildren(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

class NonTaxableIncomeCalculator {
    public static int calculate(MarriageStatus marriageStatus, NumberOfChildren numberOfChildren) {
        int nonTaxableIncome = 54000000;
        
        if (marriageStatus.isMarried()) {
            nonTaxableIncome += 4500000;
        }
        
        nonTaxableIncome += numberOfChildren.getValue() * 4500000;
        
        return nonTaxableIncome;
    }
}

