package clinic.finance.beans;

import java.math.BigDecimal;

public enum accountEnum {

	//FIRSTDIRECT, CATERALLEN, TANDEM
	VIRGIN(1, new BigDecimal(19362.64), 3, accountTypeEnum.ISA.toString(), "Virgin S&S", 0, "virgin.login.", "loginUri", "virginMoneyHTML", new String [] {"user", "pin"},"Error 404", false, false);
	/**
	,CATERALLEN(3, 10.0, accountTypeEnum.BUSINESS.toString(), "CaterAllen", 0, "callen.login.", "loginUri", "callenHTML", new String [] {"user"}, "Error 404", true, true);
	//Welcome to Cater Allen Private Bank
	SHAWBROOK(8, 2250.00, accountTypeEnum.CURRENT.toString(), "Shawbrook", 0, "shawbrook.login.", "loginUri", "shawbrookHTML", new String [] {"user", "pin"},"Error 404", false, false);
	**/
		
	private String loginUriProp; 
	private String propsPrefix; 
	private String summaryScreenTitle; 
	private String PACTitle = "pacUri"; 
	private BigDecimal balance; 
	private String [] formFields= null;
	
	private boolean multiPartLogin;
	private boolean popups;
	
	accountEnum(int accId, BigDecimal balance, int accType, String name, String accTypeName, 
					int interest, String propsPrefix, String loginUriProp, String mockPageName, 
						String [] formFields, String summaryScreenTitle, boolean multiPartLogin, boolean popups){
		
		this.loginUriProp = loginUriProp;
		this.propsPrefix = propsPrefix;
		this.summaryScreenTitle = summaryScreenTitle;
		this.balance = balance;
		this.formFields = formFields;
		this.multiPartLogin = multiPartLogin;
		this.popups = popups;
		
		new Account(accId, balance, accType, name, accTypeName,
						interest, propsPrefix, loginUriProp, mockPageName, 
							summaryScreenTitle);
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
	public BigDecimal getBalance() {
		return balance;
	}
	public String getPACTitle() {
		return PACTitle;
	}
}
