/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngDetailVo;

/**
 *
 * @author Lee
 * @since 2014. 10. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 22.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamFcltsMngFeeMngService")
public class GamFcltsMngFeeMngServiceImpl extends AbstractServiceImpl implements GamFcltsMngFeeMngService{

	@Resource(name="gamFcltsMngFeeMngDao")
	private GamFcltsMngFeeMngDao gamFcltsMngFeeMngDao;

	@Override
	public GamFcltsMngFeeMngVo selectFcltsMngFeeMngListTotCnt(GamFcltsMngFeeMngVo searchVO) throws Exception {
		return gamFcltsMngFeeMngDao.selectFcltsMngFeeMngListTotCnt(searchVO);
	}

	@Override
	public List selectFcltsMngFeeMngList(GamFcltsMngFeeMngVo searchVO) throws Exception {
		return gamFcltsMngFeeMngDao.selectFcltsMngFeeMngList(searchVO);
	}

	@Override
	public EgovMap selectFcltsMngFeeMngPk(GamFcltsMngFeeMngVo searchVO) throws Exception {
		return gamFcltsMngFeeMngDao.selectFcltsMngFeeMngPk(searchVO);
	}

	@Override
	public List selectFcltsMngFeeMngMonthCntList(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception {
		return gamFcltsMngFeeMngDao.selectFcltsMngFeeMngMonthCntList(gamFcltsMngFeeMngVo);
	}

	@Override
	public void insertFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception {
		gamFcltsMngFeeMngDao.insertFcltsMngFeeMng(gamFcltsMngFeeMngVo);
	}

	@Override
	public void updateFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception {
		gamFcltsMngFeeMngDao.updateFcltsMngFeeMng(gamFcltsMngFeeMngVo);
	}

	@Override
	public void deleteFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception {
		gamFcltsMngFeeMngDao.deleteAllMngFeeLevReqestF(gamFcltsMngFeeMngVo);
		gamFcltsMngFeeMngDao.deleteAllFcltsMngFeeMngDetail(gamFcltsMngFeeMngVo);
		gamFcltsMngFeeMngDao.deleteFcltsMngFeeMng(gamFcltsMngFeeMngVo);
	}

	@Override
	public List selectFcltsMngFeeMngDetailList(GamFcltsMngFeeMngDetailVo searchVO) throws Exception {
		return gamFcltsMngFeeMngDao.selectFcltsMngFeeMngDetailList(searchVO);
	}

	@Override
	public String selectFcltsMngFeeMngDetailMaxMngSeq(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception {
		return gamFcltsMngFeeMngDao.selectFcltsMngFeeMngDetailMaxMngSeq(gamFcltsMngFeeMngDetailVo);
	}

	@Override
	public void insertFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception {
		gamFcltsMngFeeMngDao.insertFcltsMngFeeMngDetail(gamFcltsMngFeeMngDetailVo);
		gamFcltsMngFeeMngDao.insertMngFeeLevReqestF(gamFcltsMngFeeMngDetailVo);
	}

	@Override
	public void updateFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception {
		gamFcltsMngFeeMngDao.updateFcltsMngFeeMngDetail(gamFcltsMngFeeMngDetailVo);
		gamFcltsMngFeeMngDao.updateMngFeeLevReqestF(gamFcltsMngFeeMngDetailVo);
	}

	@Override
	public void deleteFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception {
		gamFcltsMngFeeMngDao.deleteMngFeeLevReqestF(gamFcltsMngFeeMngDetailVo);
		gamFcltsMngFeeMngDao.deleteFcltsMngFeeMngDetail(gamFcltsMngFeeMngDetailVo);
	}

	@Override
	public EgovMap selectMngFeeLevRequestFByPk(Map searchVO) throws Exception {
		return gamFcltsMngFeeMngDao.selectMngFeeLevRequestFByPk(searchVO);
	}

	@Override
	public List selectFcltsMngFeeMngPrintNoticeIssueList(Map searchVO) throws Exception {
		return gamFcltsMngFeeMngDao.selectFcltsMngFeeMngPrintNoticeIssueList(searchVO);
	}

	@Override
	public void processFcltsMngFeeMngNticIssue(Map<String, Object> vo) throws Exception {
		gamFcltsMngFeeMngDao.updateMngFeeLevReqestFNticIssue(vo);
		gamFcltsMngFeeMngDao.insertRevCollF(vo);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void processCreateEgiro(Map map) throws Exception{
		Map mapResult = new HashMap();
		Map revCollF = gamFcltsMngFeeMngDao.selectRevCollF(map);
		String strPrtAtCode = (String)revCollF.get("prtAtCode");
		String strFeeTp = (String)revCollF.get("feeTp");
		String strFiscalYr = (String)revCollF.get("fiscalYr");
		String strBillNo = (String)revCollF.get("billNo");
		String strDlySerNo = (String)revCollF.get("dlySerNo");
		String strCustomerNum = (String)revCollF.get("customerNum");
		BigDecimal bdBillAmount = new BigDecimal(revCollF.get("billAmnt").toString());
		BigDecimal bdBillSumAmnt = new BigDecimal(revCollF.get("billSumAmnt").toString());
		String strDueDate = (String)revCollF.get("dueDate");
		String strCloseDate = (String)revCollF.get("closeDate");
		String strGiroNum = (String)revCollF.get("giroNum");
		String strEgiroNum = (String)revCollF.get("egiroNum");
		String strAgentCode = (String)revCollF.get("agentCode");
		String strBillType = (String)revCollF.get("billType");
		String strKorNm = (String)revCollF.get("korNm");
		String strBzRgstId = (String)revCollF.get("bzRgstId");
		String strBillYyyymm = (String)revCollF.get("billYyyymm");
		String strBillDate =  (String)revCollF.get("billDt");
		String strAddr = (String)revCollF.get("addr");
		String strPrintDt = (String)revCollF.get("printDt");
		String strTranid = (String)revCollF.get("tranid");
		if ("".equals(strTranid) || strTranid == null) {
			mapResult.put("prtAtCode", strPrtAtCode);
			mapResult.put("feeTp", strFeeTp);
			mapResult.put("fiscalYr", strFiscalYr);
			mapResult.put("billNo", strBillNo);
			mapResult.put("dlySerNo", strDlySerNo);
			mapResult.put("customerNum", strCustomerNum);
			mapResult.put("amount", bdBillSumAmnt);
			mapResult.put("amouuntAf", bdBillSumAmnt);
			mapResult.put("dueDate", strDueDate);
			mapResult.put("closeDate", strCloseDate);
			mapResult.put("giroNum", strGiroNum);
			mapResult.put("egiroNum", strEgiroNum);
			mapResult.put("agentCode", strAgentCode);
			mapResult.put("billType", strBillType);
			mapResult.put("korNm", strKorNm);
			mapResult.put("bzRgstId", strBzRgstId);
			mapResult.put("billYyyymm", strBillYyyymm);
			mapResult.put("addr", strAddr);
			mapResult.put("printDt", strPrintDt);
			mapResult.put("cancelDt", "");
			mapResult.put("tranid", strTranid);
			gamFcltsMngFeeMngDao.insertEgiro(mapResult);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void processCancelEgiro(Map map) throws Exception{
		Map mapResult = new HashMap();
		Map revCollF = gamFcltsMngFeeMngDao.selectRevCollF(map);
		String strPrtAtCode = (String)revCollF.get("prtAtCode");
		String strFeeTp = (String)revCollF.get("feeTp");
		String strFiscalYr = (String)revCollF.get("fiscalYr");
		String strBillNo = (String)revCollF.get("billNo");
		String strDlySerNo = (String)revCollF.get("dlySerNo");
		String strCustomerNum = (String)revCollF.get("customerNum");
		BigDecimal bdBillAmount = new BigDecimal(revCollF.get("billAmnt").toString());
		BigDecimal bdBillSumAmnt = new BigDecimal(revCollF.get("billSumAmnt").toString());
		String strDueDate = (String)revCollF.get("dueDate");
		String strCloseDate = (String)revCollF.get("closeDate");
		String strGiroNum = (String)revCollF.get("giroNum");
		String strEgiroNum = (String)revCollF.get("egiroNum");
		String strAgentCode = (String)revCollF.get("agentCode");
		String strBillType = (String)revCollF.get("billType");
		String strKorNm = (String)revCollF.get("korNm");
		String strBzRgstId = (String)revCollF.get("bzRgstId");
		String strBillYyyymm = (String)revCollF.get("billYyyymm");
		String strBillDate =  (String)revCollF.get("billDt");
		String strAddr = (String)revCollF.get("addr");
		String strPrintDt = (String)revCollF.get("printDt");
		String strTranid = (String)revCollF.get("tranid");
		mapResult.put("prtAtCode", strPrtAtCode);
		mapResult.put("feeTp", strFeeTp);
		mapResult.put("fiscalYr", strFiscalYr);
		mapResult.put("billNo", strBillNo);
		mapResult.put("dlySerNo", strDlySerNo);
		Map egiroMap = gamFcltsMngFeeMngDao.selectEgiroMaxInfo(mapResult);
		if (egiroMap != null && !egiroMap.isEmpty()) {
			String strEgiroTranid = (String)egiroMap.get("tranid") ;
			String strEgiroWorkDt = (String)egiroMap.get("workDt") ;
			String strEgiroCancelDt = (String)egiroMap.get("cancelDt") ;
			String strEgiroPrintDt = (String)egiroMap.get("printDt") ;

			mapResult.put("cancelDt", strPrintDt);
			mapResult.put("tranid", strEgiroTranid);
			if (strPrintDt.equals(strEgiroWorkDt) && (strEgiroCancelDt == null || "".equals(strEgiroCancelDt))) {
				gamFcltsMngFeeMngDao.updateEgiroCancel(mapResult);
			} else {
				mapResult.put("customerNum", strCustomerNum);
				mapResult.put("amount", bdBillSumAmnt);
				mapResult.put("amouuntAf", bdBillSumAmnt);
				mapResult.put("dueDate", strDueDate);
				mapResult.put("closeDate", strCloseDate);
				mapResult.put("giroNum", strGiroNum);
				mapResult.put("egiroNum", strEgiroNum);
				mapResult.put("agentCode", strAgentCode);
				mapResult.put("billType", strBillType);
				mapResult.put("korNm", strKorNm);
				mapResult.put("bzRgstId", strBzRgstId);
				mapResult.put("billYyyymm", strBillYyyymm);
				mapResult.put("addr", strAddr);
				mapResult.put("printDt", "");
				gamFcltsMngFeeMngDao.insertEgiro(mapResult);
			}
		}
	}

	@SuppressWarnings({ "rawtypes"})
	@Override
	public void updateFcltsMngFeeMngNticIssuePrintYn(Map<String, Object> vo) throws Exception {
		Map map = gamFcltsMngFeeMngDao.selectMngFeeLevRequestFByPk(vo);
		gamFcltsMngFeeMngDao.updateMngFeeLevReqestFNhtPrintYn(vo);
		if (map.get("arrrgNo") != null) {
			gamFcltsMngFeeMngDao.updateUnpaidFDlyBillPrtYn(vo);
		}
		gamFcltsMngFeeMngDao.updateRevCollFBillPrtYn(vo);
		if ("Y".equals(vo.get("nhtPrintYn"))) {
			processCreateEgiro(vo);
		} else {
			processCancelEgiro(vo);
		}
	}

	@Override
	public void cancelFcltsMngFeeMngNticIssue(Map<String, Object> vo) throws Exception {
		String strNhtPrintYn = (String)vo.get("nhtPrintYn");
		if ("Y".equals(strNhtPrintYn)) {
			gamFcltsMngFeeMngDao.updateMngFeeLevReqestFNhtPrintYn(vo);
			gamFcltsMngFeeMngDao.updateRevCollFBillPrtYn(vo);
			processCancelEgiro(vo);
		}
		gamFcltsMngFeeMngDao.deleteRevCollF(vo);
		gamFcltsMngFeeMngDao.updateMngFeeLevReqestFNticIssue(vo);
	}

	@Override
	public void copyFcltsMngFeeMng(Map<String, Object> vo) throws Exception {
		String strMainCnt = (String)vo.get("mainCnt");
		String strDetailCnt = (String)vo.get("detailCnt");
		String strReqestCnt = (String)vo.get("reqestCnt");
		if (!("0".equals(strMainCnt)) && !("".equals(strMainCnt))) {
			gamFcltsMngFeeMngDao.copyFcltsMngFeeF(vo);
		}
		if (!("0".equals(strDetailCnt)) && !("".equals(strDetailCnt))) {
			gamFcltsMngFeeMngDao.copyFcltsMngFeeDetailF(vo);
		}
		if (!("0".equals(strReqestCnt)) && !("".equals(strReqestCnt))) {
			gamFcltsMngFeeMngDao.copyMngFeeLevReqestF(vo);
		}
	}

	@Override
	public List selectFcltsMngFeeMngChartList(Map<String, Object> vo) throws Exception {
		return gamFcltsMngFeeMngDao.selectFcltsMngFeeMngChartList(vo);
	}

}