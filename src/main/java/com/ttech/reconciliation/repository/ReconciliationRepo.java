package com.ttech.reconciliation.repository;

import com.ttech.reconciliation.model.CaptchaWSParam;
import com.ttech.reconciliation.model.ReconciliationModel;

public interface ReconciliationRepo {

	ReconciliationModel findByReconId(int reconId,String token);
	
	String update(ReconciliationModel recon);
	
	String isTokenValid(int reconId, String token);
	
	CaptchaWSParam getCaptchaWsParam();
}
