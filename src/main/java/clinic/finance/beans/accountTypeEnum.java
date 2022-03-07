package clinic.finance.beans;

public enum accountTypeEnum {
	
	BUSINESS, CURRENT, ISA, SAVING, STOCK, FUND, PENSION;
	
	private String type;
	
	String type() {
		return type;
	}
}
