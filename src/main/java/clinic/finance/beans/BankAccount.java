package clinic.finance.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankAccount {
	
	private int accountId = 0;
	private int accountType = 0;
	private double accountBalance = 0;
	private int accountInterest = 0;
	private String accountName = null;
	private String accountTypeName = null;
	private String accountCol = null;
	private String propsPrefix = null;
	private String loginUri = null;
	private String mockPageName = null;
	private String summaryUri = null;
	private String summaryScreenTitle = null;
	
	/*
	 * Maintain List of updateable fields based on the 
	 * validation of parameter values
	 */
	private List <String> updateFields  =  new ArrayList<String>();
	
	public BankAccount (int accountId, double accountBalance, int accountType,
			String accountName, String accountTypeName, int accountInterest, String propsPrefix, String loginUri, 
											String mockPageName, String summaryScreenTitle) {
		
		this(accountId, accountBalance, accountType,
				accountName, accountTypeName, accountInterest, propsPrefix, loginUri);
		
		if (summaryScreenTitle != null) {
			this.summaryScreenTitle = summaryScreenTitle;
			updateFields.add("summaryScreenTitle");
		}
		if (mockPageName != null) {
			this.mockPageName = mockPageName;
			updateFields.add("mockPageName");
		}
	}

		public BankAccount (int accountId, double accountBalance, int accountType,
			String accountName, String accountTypeName, int accountInterest, String propsPrefix, String loginUri) {
		
		this(accountId, accountBalance, accountType,
				accountName, accountTypeName, accountInterest);
		
		if (propsPrefix != null) {
			this.propsPrefix = propsPrefix;
			updateFields.add("propsPrefix");
		}
		if (loginUri != null) {
			this.loginUri = loginUri;
			updateFields.add("loginUri");
		}
	}
	
	public BankAccount (int accountId, double accountBalance, int accountType,
			String accountName,  String accountTypeName, int accountInterest) {
		
		this(accountId, accountBalance);
		
		if (accountType > 0) {
			this.accountType = accountType;
			updateFields.add("accountType");
		}
		if (accountName != null) {
			this.accountName = accountName;
			updateFields.add("accountName");
		}
		if (accountTypeName != null) {
			this.accountTypeName = accountTypeName;
			updateFields.add("accountTypeName");
		}
		if (accountInterest > 0) {
			this.accountInterest = accountInterest;
			updateFields.add("accountInterest");
		}
	}
	
	public BankAccount (int accountId, double accountBalance) {
		
		if (accountId > 0) {
			this.accountId = accountId;
			updateFields.add("accountId");
		}
		if (accountBalance > 0) {
			this.accountBalance = accountBalance;
			updateFields.add("accountBalance");
		}
	}
	
	public BankAccount() {
		
	}
	
	public List <String> getUpdateFields() {
		return updateFields; 
	}
	
	public int getAccountId() {
		return accountId;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	public String getAccountName() {
		return accountName;
	}
	public int getAccountInterest() {
		return accountInterest;
	}
	public String getAccountCol() {
		return accountCol;
	}
	public void setLoginUri(String loginUri) {
		this.loginUri = loginUri;
	}
	public String getsummaryUrii() {
		return summaryUri;
	}
	public void setsummaryUri(String summaryUri) {
		this.summaryUri = summaryUri;
	}
	public String getMockPageName() {
		return mockPageName;
	}
	public String getSummaryScreenTitle() {
		return summaryScreenTitle;
	}
	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}
}
