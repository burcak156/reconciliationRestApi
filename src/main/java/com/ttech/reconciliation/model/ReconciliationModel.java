package com.ttech.reconciliation.model;

public class ReconciliationModel {

	private String senderVendorName;
	private String receiverVendorName;
	private String reconDate;
	private int reconID;
	private String currencyCode;
	private String balanceType;
	private double balance;
	private String inCurrencyCode;
	private String inBalanceType;
	private double inBalance;
	private String captchaImg;
	private String captchaIdentifier;
	private String captchaText;

	public ReconciliationModel() {

	}
	
	public String getCaptchaText() {
		return captchaText;
	}

	public void setCaptchaText(String captchaText) {
		this.captchaText = captchaText;
	}

	public String getCaptchaIdentifier() {
		return captchaIdentifier;
	}

	public void setCaptchaIdentifier(String captchaIdentifier) {
		this.captchaIdentifier = captchaIdentifier;
	}

	public String getCaptchaImg() {
		return captchaImg;
	}

	public void setCaptchaImg(String captchaImg) {
		this.captchaImg = captchaImg;
	}

	public String getSenderVendorName() {
		return senderVendorName;
	}

	public void setSenderVendorName(String senderVendorName) {
		this.senderVendorName = senderVendorName;
	}

	public String getReceiverVendorName() {
		return receiverVendorName;
	}

	public void setReceiverVendorName(String receiverVendorName) {
		this.receiverVendorName = receiverVendorName;
	}

	public String getReconDate() {
		return reconDate;
	}

	public void setReconDate(String reconDate) {
		this.reconDate = reconDate;
	}

	public int getReconID() {
		return reconID;
	}

	public void setReconID(int reconID) {
		this.reconID = reconID;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getInCurrencyCode() {
		return inCurrencyCode;
	}

	public void setInCurrencyCode(String inCurrencyCode) {
		this.inCurrencyCode = inCurrencyCode;
	}

	public String getInBalanceType() {
		return inBalanceType;
	}

	public void setInBalanceType(String inBalanceType) {
		this.inBalanceType = inBalanceType;
	}

	public double getInBalance() {
		return inBalance;
	}

	public void setInBalance(double inBalance) {
		this.inBalance = inBalance;
	}

	@Override
	public String toString() {
		return "ReconciliationModel [senderVendorName=" + senderVendorName + ", receiverVendorName="
				+ receiverVendorName + ", reconDate=" + reconDate + ", reconID=" + reconID + ", currencyCode="
				+ currencyCode + ", balanceType=" + balanceType + ", balance=" + balance + ", inCurrencyCode="
				+ inCurrencyCode + ", inBalanceType=" + inBalanceType + ", inBalance=" + inBalance + "]";
	}

}
