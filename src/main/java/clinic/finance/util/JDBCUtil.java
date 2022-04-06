package clinic.finance.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.apache.commons.dbutils.BeanProcessor;

import clinic.finance.beans.Account;
import clinic.finance.beans.accountTypeEnum;
import clinic.finance.comparators.AccountComparator;

/*
 * Essential to use mysql-connector-java 8.0.11 jar.
 * Otherwise returned unrecognized protocol error or NullPointerException
 * TO-DO No point in duplicating this from FinanceApp - try to export and reference as jar file from there 
 */
public class JDBCUtil extends CommonFinanceUtil{
	
	protected String URLKEY = "url";
	protected String DBMSKEY = "dbms";
	protected String SERVERKEY = "serverName";
	protected String PORTKEY = "portNumber";
	protected String DBNAMEKEY = "account";
	
	private Connection conn = null;
	private boolean connected = false;
	private List<Account> bankAccounts;
	private final String UPDATSTATPRE = "update wds_fin.account ";
	private BigDecimal sumTotal;
	private BigDecimal pensionTotal;
	private String accountType;
	/*
	 * properties Constructor
	 */
	public JDBCUtil (String propertiesPath) {
		propsPrefix = "jdbc.db.";
		loadPropertiesFromResource(propertiesPath);
	}
	
	@Override
	protected Properties getProperties() {
		return prop;
	}
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {

	    Properties connectionProps = new Properties();
	    connectionProps.put("user", prop.getProperty(propsPrefix+USER+"Key"));
	    connectionProps.put("password", prop.getProperty(propsPrefix+PASSKEY));
	    connectionProps.put("useUnicode", "yes");
	    
    try {
	    if (prop.getProperty(propsPrefix+DBMSKEY).equals("mysql")) {
	    	
	    	/*
	    	 * Have to do this bcos - https://www.java67.com/2016/06/
	    	 * 					javasqlsqlexception-no-suitable-driver-found-jdbc-mysql-localhost-3306.html
	    	 */
	    	 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	    	 conn = DriverManager.getConnection("jdbc:" + prop.getProperty(propsPrefix+DBMSKEY) + "://" +
	    			 prop.getProperty(propsPrefix+SERVERKEY) +
	                   ":" + prop.getProperty(propsPrefix+PORTKEY) + "/",
	                   connectionProps);
	    } else if (prop.getProperty(propsPrefix+DBMSKEY).equals("derby")) {
	        conn = DriverManager.getConnection(
	                   "jdbc:" + prop.getProperty(propsPrefix+DBMSKEY) + ":" +
	                		   prop.getProperty(propsPrefix+DBNAMEKEY) +
	                   ";create=true",
	                   connectionProps);
	    }
    } catch (InstantiationException | IllegalAccessException | SQLException e) {
    	e.printStackTrace();
    }
	    
	    if (conn !=  null) connected = true;
	    	
	    return conn;
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	/*If ResultSet was passed back and then another one created from return set, this set and that would close 
	 * and could not be manipulated. Using the BeanProcessor invokes Class.newInstance which requires void BankAccount
	 * constructor and so values aren't populated - research.
	 *  
	 */
	public List<Account> runSelectStatement() {
		
		StringBuilder queryBuff = new StringBuilder("select acc.idaccount, acc.accountname, acc.accounttype, acc_t.accounttypename, "); 
		queryBuff.append("acc.accountbalance, acc.accountinterest ");				
		queryBuff.append("from wds_fin.account acc, wds_fin.accounttype acc_t ");				
		queryBuff.append("WHERE acc.accounttype = acc_t.accounttype ");				
		queryBuff.append("order by acc.accounttype asc");				
						
		ResultSet rs = null;
		if (connected) {
			try {
			  Statement stmt = conn.createStatement();
		      rs = stmt.executeQuery(queryBuff.toString());
		      //rs.beforeFirst();
		      //BeanProcessor bp = new BeanProcessor();
			  bankAccounts = new ArrayList<Account>();
			  while (rs.next()) {
				  
			  //bankAccounts.add((BankAccount)bp.toBean(rs, BankAccount.class));
			  bankAccounts.add(new Account(Integer.parseInt(rs.getString("idaccount")), new BigDecimal(rs.getString("accountBalance")), 
					  Integer.parseInt(rs.getString("accountType")), rs.getString("accountName"), rs.getString("accountTypeName"), Integer.parseInt(rs.getString("accountInterest"))));
			  
			  Collections.sort(bankAccounts, new AccountComparator());
			}
			  rs.close();
		    } catch (SQLException e) {
		    	e.getStackTrace();
		    }
		}
		return bankAccounts;
	}
	
	public BigDecimal calculateSumTotal(List<Account> bankAccounts, boolean withPension) {
		
		sumTotal = new BigDecimal(0.0);
		if (bankAccounts!=null) {
			for (Account bankAccount : bankAccounts) {
				if (bankAccount.getAccountType() != 7|| withPension) {
					sumTotal = sumTotal.add(bankAccount.getAccountBalance());
				}
			}
		}
		return sumTotal;
	}
	
	/*
	 * Update relevant value in DB table
	 */
	public int runAccountUpdateStatement(Account account) {
		
		StringBuilder queryBuild = new StringBuilder(UPDATSTATPRE); 
		List feildNames = account.getUpdateFields();
		
		/*Conditional as cannot update row without id*/
		if(feildNames.contains("accountId")) {
			Stream<?> fieldStream = feildNames.stream();
			
			fieldStream.filter(t -> !t.toString()
					.equalsIgnoreCase("accountId"))
						.forEach(new Consumer() {
							@Override
							public void accept(Object t) {
								queryBuild.append("set ");
								queryBuild.append(t.toString());
								queryBuild.append(" = ");
								
								if (t.toString().equalsIgnoreCase("accountBalance")) {
									queryBuild.append(account.getAccountBalance());
									queryBuild.append(" ");
								}
							}
						});
		}
		queryBuild.append("where idaccount = ");
		queryBuild.append(account.getAccountId());
		
		int returnCode = 0;
		
		if (connected) {
			try (Statement stmt = conn.createStatement()) {
				returnCode = stmt.executeUpdate(queryBuild.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnCode;
	}
}
