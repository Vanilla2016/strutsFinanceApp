/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.mycompany.mysystem.example;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.opensymphony.xwork2.ActionContext;

import clinic.finance.beans.Account;
import clinic.finance.util.JDBCUtil;

/** 
 ** <code>Set welcome message.</code>
 */
public class AccountAggregatorAction extends ExampleSupport {

	JDBCUtil jdbcUtil;
	List<Account> bankAccounts;
	BigDecimal sumTotal;
	BigDecimal accessibleTotal;

	private final String JDBCPROPERTIESLOCATION = 
			"C:/Users/domin/workspace/strutsFinanceApp/src/main/resources/jdbc.properties";
			
	private static SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public String execute() {
		String exec = SUCCESS;
		
        setMessage(getText(MESSAGE));
        jdbcUtil = new JDBCUtil(JDBCPROPERTIESLOCATION);
    	try {
			jdbcUtil.getConnection();
			bankAccounts = jdbcUtil.runSelectStatement();
			
			if (bankAccounts!=null) {
				setBankAccounts(bankAccounts);
				setSumTotal(bankAccounts);
				setAccessibleTotal(bankAccounts);
				
				ActionContext.getContext().getSession().put("bankAccounts", getBankAccounts());
				ActionContext.getContext().getSession().put("sumTotal", getSumTotal());
				ActionContext.getContext().getSession().put("accessibleTotal", getAccessibleTotal());
			}
		} catch (ClassNotFoundException | SQLException | NoClassDefFoundError e) {//Why getting this NoClassDefFoundError?
			e.printStackTrace();
		}
		 
    	/*
        try {
        	sessionFactory =  new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
        	System.err.println("Failed to create session factory object" +ex);
        	throw new ExceptionInInitializerError(ex);
        }
        
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        
        //System.out.println(""+session.connection());
        
        try {
        	tx = session.beginTransaction();
        	bankAccounts = session.createQuery("FROM account").list();
        	if (bankAccounts!=null) {
				setBankAccounts(bankAccounts);
				//setSumTotal(bankAccounts);
				//setAccessibleTotal(bankAccounts);
				
				ActionContext.getContext().getSession().put("bankAccounts", getBankAccounts());
				//ActionContext.getContext().getSession().put("sumTotal", getSumTotal());
				//ActionContext.getContext().getSession().put("accessibleTotal", getAccessibleTotal());
			}
        	tx.commit();
        } catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			exec = ERROR;
		} finally {
			session.close();
		}
        */
        return exec;
    }

    /**
     * Provide default valuie for Message property.
     */
    public static final String MESSAGE = "AccountAggregatorAction.message";
  
    /**
     * Field for Message property.
     */
    private String message;
  
    /**
     * Return Message property.
     *
     * @return Message property
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set Message property.
     *
     * @param message Text to display on HelloWorld page.
     */
    public void setMessage(String message) {
        this.message = message;
    }
    public List<Account> getBankAccounts() {
		return bankAccounts;
	}
	public void setBankAccounts(List<Account> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
	public BigDecimal getSumTotal() {
		return sumTotal;
	}
	public BigDecimal getAccessibleTotal() {
		return accessibleTotal;
	}
	 public void setSumTotal(List<Account> bankAccounts) {
		sumTotal = jdbcUtil.calculateSumTotal(bankAccounts, true);
	}
	public void setAccessibleTotal(List<Account> bankAccounts) {
		accessibleTotal = jdbcUtil.calculateSumTotal(bankAccounts, false);
	}
}
