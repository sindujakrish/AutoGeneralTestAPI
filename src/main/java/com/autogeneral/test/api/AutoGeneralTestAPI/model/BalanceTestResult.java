package com.autogeneral.test.api.AutoGeneralTestAPI.model;

/**
 * A data transfer object to transfer the details of the brackets validation API test result
 * as part of the JSON response.
 *
 */
public class BalanceTestResult {
	
	private String input;
	private boolean isBalanced;
	
	public BalanceTestResult() {
		super();
	}

	public BalanceTestResult(String input, boolean isBalanced) {
		super();
		this.input = input;
		this.isBalanced = isBalanced;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public boolean isBalanced() {
		return isBalanced;
	}

	public void setBalanced(boolean isBalanced) {
		this.isBalanced = isBalanced;
	}
	
	

}
