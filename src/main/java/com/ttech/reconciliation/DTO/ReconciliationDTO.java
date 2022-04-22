package com.ttech.reconciliation.DTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ReconciliationDTO {

	@NotNull(message = "Mutabakat No Bos Gecilemez")
	@NotEmpty
	private int reconID;

	@Size(min = 2, max = 10)
	private String inBalanceType;

	@Min(0)
	private double inBalance;

	@Size(min = 2, max = 60)
	private String captchaIdentifier;
	@Size(min = 2, max = 8, message = "message size must be between 2 and 12")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "must not contain special characters")
	private String captchaText;

	public int getReconID() {
		return reconID;
	}

	public void setReconID(int reconID) {
		this.reconID = reconID;
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

	public String getCaptchaIdentifier() {
		return captchaIdentifier;
	}

	public void setCaptchaIdentifier(String captchaIdentifier) {
		this.captchaIdentifier = captchaIdentifier;
	}

	public String getCaptchaText() {
		return captchaText;
	}

	public void setCaptchaText(String captchaText) {
		this.captchaText = captchaText;
	}

}
