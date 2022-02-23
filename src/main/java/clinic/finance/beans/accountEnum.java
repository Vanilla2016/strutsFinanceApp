package clinic.finance.beans;

public enum accountEnum {

	//FIRSTDIRECT, CATERALLEN, TANDEM
	VIRGIN(1, 19362.64, accountTypeEnum.ISA.toString(), "Virgin S&S", 0, "virgin.login.", "loginUri", "virginMoneyHTML", new String [] {"user", "pin"},"Error 404", false, false);
	/**
	,CATERALLEN(3, 10.0, accountTypeEnum.BUSINESS.toString(), "CaterAllen", 0, "callen.login.", "loginUri", "callenHTML", new String [] {"user"}, "Error 404", true, true);
	//Welcome to Cater Allen Private Bank
	SHAWBROOK(8, 2250.00, accountTypeEnum.CURRENT.toString(), "Shawbrook", 0, "shawbrook.login.", "loginUri", "shawbrookHTML", new String [] {"user", "pin"},"Error 404", false, false);
	**/
		
	private String loginUriProp; 
	private String propsPrefix; 
	private String summaryScreenTitle; 
	private String PACTitle = "pacUri"; 
	private double balance; 
	private String [] formFields= null;
	
	private boolean multiPartLogin;
	private boolean popups;
	
	accountEnum(int accId, double balance, String type, String name, int interest, String propsPrefix, String loginUriProp, String mockPageName, String [] formFields, String summaryScreenTitle, boolean multiPartLogin, boolean popups){
		this.loginUriProp = loginUriProp;
		this.propsPrefix = propsPrefix;
		this.summaryScreenTitle = summaryScreenTitle;
		this.balance = balance;
		this.formFields = formFields;
		this.multiPartLogin = multiPartLogin;
		this.popups = popups;
		
		new BankAccount(accId, balance, type, name, interest, propsPrefix, loginUriProp, mockPageName, summaryScreenTitle);
	}
	public boolean hasPopups() {
		return popups;
	}
	public boolean isMultiPartLogin() {
		return multiPartLogin;
	}
	public String[] getFormFields() {
		return formFields;
	}
	public String getloginUriProp() {
		return loginUriProp;
	}
	public String getPropsPrefix() {
		return propsPrefix;
	}
	public String getSummaryScreenTitle() {
		return summaryScreenTitle;
	}
	public double getBalance() {
		return balance;
	}
	public String getPACTitle() {
		return PACTitle;
	}
}
