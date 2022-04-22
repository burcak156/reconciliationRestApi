package com.ttech.reconciliation.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttech.reconciliation.DTO.ReconciliationDTO;
import com.ttech.reconciliation.captcha.CaptchaClient;
import com.ttech.reconciliation.model.ReconciliationModel;
import com.ttech.reconciliation.repository.ReconciliationRepo;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ReconciliationController {

	@Autowired
	ReconciliationRepo reconciliationRepo;

	@GetMapping("/recon/{id}/{token}")
	public ResponseEntity<ReconciliationModel> getReconById(@PathVariable("id") int id,
			@PathVariable("token") String token) {

		String isValid = reconciliationRepo.isTokenValid(id, token);
		if ("S".equals(isValid)) {
			CaptchaClient captchaClient = new CaptchaClient();
			JSONObject respJson = captchaClient.callGetCaptchaService(
					reconciliationRepo.getCaptchaWsParam().getGetUrl(),
					reconciliationRepo.getCaptchaWsParam().getApplKey(),
					reconciliationRepo.getCaptchaWsParam().getSecretKey(),
					reconciliationRepo.getCaptchaWsParam().getGwToken());

			ReconciliationModel recon = reconciliationRepo.findByReconId(id, token);
			if (recon != null) {
				if (respJson.getInt("resultCode") == 13)  {
					recon.setCaptchaImg(respJson.getJSONObject("message").getString("image"));
					recon.setCaptchaIdentifier(respJson.getJSONObject("message").getString("identifier"));
					// System.out.println(respJson.getJSONObject("message").getString("identifier"));
					return new ResponseEntity<>(recon, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} else

			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

	@PutMapping("/recon/{id}/{token}")
	public ResponseEntity<ReconciliationModel> updateRecon(@PathVariable("id") int id,
			@PathVariable("token") String token, @RequestBody ReconciliationDTO recon) {
		String resultDb = "";
		String isValid = reconciliationRepo.isTokenValid(id, token);
		if ("S".equals(isValid)) {

			CaptchaClient captchaClient = new CaptchaClient();
			JSONObject respJson = captchaClient.callCheckCaptchaService(
					reconciliationRepo.getCaptchaWsParam().getCheckUrl(),
					reconciliationRepo.getCaptchaWsParam().getApplKey(),
					reconciliationRepo.getCaptchaWsParam().getSecretKey(),
					reconciliationRepo.getCaptchaWsParam().getGwToken(), recon.getCaptchaText(),
					recon.getCaptchaIdentifier());
			if (respJson.getInt("resultCode") == 13) {

				ReconciliationModel _reconModel = reconciliationRepo.findByReconId(id, token);

				if (_reconModel != null) {
					_reconModel.setReconID(recon.getReconID());
					_reconModel.setInBalance(recon.getInBalance());
					_reconModel.setInBalanceType(recon.getInBalanceType());
					resultDb = reconciliationRepo.update(_reconModel);
					if ("S".equals(resultDb)) {
						return new ResponseEntity<>(_reconModel, HttpStatus.CREATED);
					} else {
						return new ResponseEntity<>(_reconModel, HttpStatus.NOT_FOUND);
					}
				} else {
					return new ResponseEntity<>(_reconModel, HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

		} else

			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

	}

}
