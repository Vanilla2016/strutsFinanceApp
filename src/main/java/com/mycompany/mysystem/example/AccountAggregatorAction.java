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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;
import clinic.finance.beans.BankAccount;
import clinic.finance.util.JDBCUtil;

/** 
 ** <code>Set welcome message.</code>
 */
public class AccountAggregatorAction extends ExampleSupport {

	JDBCUtil jdbcUtil;
	List<BankAccount> bankAccounts;
	BigDecimal sumTotal;
	BigDecimal accessibleTotal;

	final String JDBCPROPERTIESLOCATION = 
			"C:/Users/domin/workspace/strutsFinanceApp/src/main/resources/jdbc.properties";
			
    public String execute() {
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
        return SUCCESS;
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
    public List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}
	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
	public BigDecimal getSumTotal() {
		return sumTotal;
	}
	public void setSumTotal(List<BankAccount> bankAccounts) {
		sumTotal = jdbcUtil.calculateSumTotal(bankAccounts, true);
	}
	public BigDecimal getAccessibleTotal() {
		return accessibleTotal;
	}
	public void setAccessibleTotal(List<BankAccount> bankAccounts) {
		accessibleTotal = jdbcUtil.calculateSumTotal(bankAccounts, false);
	}
}
