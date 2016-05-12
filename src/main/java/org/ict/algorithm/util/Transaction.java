package org.ict.algorithm.util;

import org.apache.commons.lang3.StringUtils;

public class Transaction implements Comparable<Transaction> {
	
	private String who;
	
	private Date when;
	
	private double amount;
	
	public Transaction(String who, Date when, double amount) {
		if (Double.isNaN(amount) || Double.isInfinite(amount)) {
			throw new IllegalArgumentException("Amount cannot be NaN or infinite");
		}
		if (StringUtils.isBlank(who)) {
			throw new IllegalArgumentException("who cannnot be blank!");
		}
		if (null == when) {
			throw new IllegalArgumentException("when cannot be null!");
		}
		this.who = who;
		this.when = when;
		this.amount = amount;
	}

	public int compareTo(Transaction o) {
		if ((this.amount < o.amount)) return -1;
		else if (this.amount > o.amount) return 1;
		else return 0;
	}
	
	

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public Date getWhen() {
		return when;
	}

	public void setWhen(Date when) {
		this.when = when;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [who=" + who + ", when=" + when + ", amount="
				+ amount + "]";
	}

}
