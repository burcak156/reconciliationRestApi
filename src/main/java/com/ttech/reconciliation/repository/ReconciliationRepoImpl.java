package com.ttech.reconciliation.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.ttech.reconciliation.model.CaptchaWSParam;
import com.ttech.reconciliation.model.ReconciliationModel;

@Repository
public class ReconciliationRepoImpl implements ReconciliationRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ReconciliationModel findByReconId(int reconId, String token) {
		//System.out.println("reconId " + reconId + "token" + token);
		// isTokenValid(reconId,token);
		try {
			ReconciliationModel recon = jdbcTemplate.queryForObject(
					"SELECT RECONID, RECEIVERVENDORNAME, SENDERVENDORNAME, CURRENCYCODE, RECONDATE, BALANCE, INCURRENCYCODE, INBALANCETYPE, INBALANCE FROM apps.xxtg_recon_ws_v WHERE reconid=?",
					BeanPropertyRowMapper.newInstance(ReconciliationModel.class), reconId);

			return recon;
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	@Override
	public String update(ReconciliationModel recon) {
		String result = "";
		List<SqlParameter> declaredParameters = new ArrayList<SqlParameter>();

		declaredParameters.add(new SqlParameter("p_recon_id", Types.INTEGER));
		declaredParameters.add(new SqlParameter("p_in_amount", Types.DOUBLE));
		declaredParameters.add(new SqlParameter("p_in_amount_type", Types.VARCHAR));
		declaredParameters.add(new SqlOutParameter("x_result", Types.VARCHAR));
		//System.out.println(" isTokenValid start");
		Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement cs = con.prepareCall("{call XXTG_RECON_WS_UTIL_PKG.update_ws_result(?,?,?,?)}");
				cs.setInt(1, recon.getReconID());
				cs.setDouble(2, recon.getInBalance());
				cs.setString(3, recon.getInBalanceType());
				cs.registerOutParameter(4, Types.VARCHAR);
				return cs;
			}
		}, declaredParameters);
		//System.out.print("t= " + t.get("x_result"));
		result = t.get("x_result") + "";
		return result;

		/*
		 * return jdbcTemplate.
		 * update("UPDATE XXTG_RECON_WS_CLIENT_TOKENS SET enabled_flag=? WHERE recon_id=?"
		 * , new Object[] { 'N', recon.getReconID() });
		 */
		// return 0;

	}

	@Override
	public String isTokenValid(int reconId, String token) {
		String result = "";
		List<SqlParameter> declaredParameters = new ArrayList<SqlParameter>();

		declaredParameters.add(new SqlParameter("p_recon_id", Types.INTEGER));
		declaredParameters.add(new SqlParameter("p_token", Types.VARCHAR));
		declaredParameters.add(new SqlOutParameter("x_result", Types.VARCHAR));
		//System.out.println(" isTokenValid start");
		Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement cs = con.prepareCall("{call XXTG_RECON_WS_UTIL_PKG.check_token(?,?,?)}");
				cs.setInt(1, reconId);
				cs.setString(2, token);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		}, declaredParameters);
		//System.out.print("t= " + t.get("x_result"));
		result = t.get("x_result") + "";
		return result;
	}
	
	@Override
	public CaptchaWSParam getCaptchaWsParam() {
		try {
			CaptchaWSParam captchaParams = jdbcTemplate.queryForObject(
					"SELECT GETURL, CHECKURL, APPLKEY, SECRETKEY, GWTOKEN FROM APPS.XXTG_RECON_WS_CAPTCHA_PARAM_V",
					BeanPropertyRowMapper.newInstance(CaptchaWSParam.class));

			return captchaParams;
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}
}
