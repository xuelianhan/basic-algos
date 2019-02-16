package org.ict.algorithm.fundamentals;

import org.apache.commons.lang3.StringUtils;

public class Transaction implements Comparable<Transaction> {
	
	/** customer */
	private String who;
	/** date */
	private Date when;
	/** amount */
	private double amount;
	
	/**
	 *  Initializes a new transaction from the given arguments
	 * 
	 * @param who the person involved in this transaction
	 * @param when the date of this transaction
	 * @param amount the amount of this transaction
	 * @throws IllegalArgumentException if {@code amount}
	 *         is {@code Double.NaN}, {@code Double.POSITIVE_INFINITY},
	 *         or {@code Double.NEGATIVE_INFINITY}
	 * 
	 */
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
	
	/**
	 * Initializes a new transaction by parsing  a string of the
	 * form NAME DATE AMOUNT.
	 * 
	 * @param transaction the string to parse
	 * @throws IllegalArgumentException if {@code amount}
	 *         is {@code Double.NaN}, {@code Double.POSITIVE_INFINITY},
	 *         or {@code Double.NEGATIVE_INFINITY}
	 * 
	 */
	public Transaction(String transaction) {
		String[] a = transaction.split("\\s+");
		who    = a[0];
		when   = new Date(a[1]);
		amount = Double.parseDouble(a[2]);
		if (Double.isNaN(amount) || Double.isInfinite(amount)) {
			throw new IllegalArgumentException("Amount cannot be NaN or infinite");
		}
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
