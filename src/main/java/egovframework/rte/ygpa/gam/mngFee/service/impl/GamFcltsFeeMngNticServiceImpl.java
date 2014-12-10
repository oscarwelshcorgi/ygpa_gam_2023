/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngNticService;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngNticVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngNticDetailVo;

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
@Service("gamFcltsFeeMngNticService")
public class GamFcltsFeeMngNticServiceImpl extends AbstractServiceImpl implements GamFcltsFeeMngNticService{

	@Resource(name="gamFcltsFeeMngNticDao")
	private GamFcltsFeeMngNticDao gamFcltsFeeMngNticDao;

	@Override
	public GamFcltsFeeMngNticVo selectFcltsFeeMngNticListTotCnt(GamFcltsFeeMngNticVo searchVO) throws Exception {
		return gamFcltsFeeMngNticDao.selectFcltsFeeMngNticListTotCnt(searchVO);
	}

	@Override
	public List selectFcltsFeeMngNticList(GamFcltsFeeMngNticVo searchVO) throws Exception {
		return gamFcltsFeeMngNticDao.selectFcltsFeeMngNticList(searchVO);
	}

	@Override
	public List selectFcltsFeeMngNticDetailList(GamFcltsFeeMngNticDetailVo searchVO) throws Exception {
		return gamFcltsFeeMngNticDao.selectFcltsFeeMngNticDetailList(searchVO);
	}

	@Override
	public List selectFcltsFeeMngNticPrintNoticeIssueList(Map searchVO) throws Exception {
		return gamFcltsFeeMngNticDao.selectFcltsFeeMngNticPrintNoticeIssueList(searchVO);
	}

	@Override
	public String selectFcltsFeeMngNticMaxReqestSeq(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) throws Exception {
		return gamFcltsFeeMngNticDao.selectFcltsFeeMngNticMaxReqestSeq(gamFcltsFeeMngNticVo);
	}

	@Override
	public void insertFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) throws Exception {
		gamFcltsFeeMngNticDao.insertFcltsFeeMngNtic(gamFcltsFeeMngNticVo);
	}

	@Override
	public void updateFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) throws Exception {
		gamFcltsFeeMngNticDao.updateFcltsFeeMngNtic(gamFcltsFeeMngNticVo);
	}

	@Override
	public void deleteFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) throws Exception {
		gamFcltsFeeMngNticDao.deleteFcltsFeeMngNtic(gamFcltsFeeMngNticVo);
	}

	@Override
	public void processFcltsFeeMngNticIssue(Map<String, Object> vo) throws Exception {
		gamFcltsFeeMngNticDao.updateFcltsFeeMngNticIssue(vo);
		gamFcltsFeeMngNticDao.insertRevCollF(vo);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void processCreateEgiro(Map map) throws Exception{
		Map mapResult = new HashMap();
		Map revCollF = gamFcltsFeeMngNticDao.selectRevCollF(map);
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
			gamFcltsFeeMngNticDao.insertEgiro(mapResult);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void processCancelEgiro(Map map) throws Exception{
		Map mapResult = new HashMap();
		Map revCollF = gamFcltsFeeMngNticDao.selectRevCollF(map);
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
		Map egiroMap = gamFcltsFeeMngNticDao.selectEgiroMaxInfo(mapResult);
		if (egiroMap != null && !egiroMap.isEmpty()) {
			String strEgiroTranid = (String)egiroMap.get("tranid") ;
			String strEgiroWorkDt = (String)egiroMap.get("workDt") ;
			String strEgiroCancelDt = (String)egiroMap.get("cancelDt") ;
			String strEgiroPrintDt = (String)egiroMap.get("printDt") ;

			mapResult.put("cancelDt", strPrintDt);
			mapResult.put("tranid", strEgiroTranid);
			if (strPrintDt.equals(strEgiroWorkDt) && (strEgiroCancelDt == null || "".equals(strEgiroCancelDt))) {
				gamFcltsFeeMngNticDao.updateEgiroCancel(mapResult);
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
				gamFcltsFeeMngNticDao.insertEgiro(mapResult);
			}
		}
	}

	@SuppressWarnings({ "rawtypes"})
	@Override
	public void updateFcltsFeeMngNticIssuePrintYn(Map<String, Object> vo) throws Exception {
		Map map = gamFcltsFeeMngNticDao.selectMngFeeLevRequestFByPk(vo);
		gamFcltsFeeMngNticDao.updateFcltsFeeMngNticNhtPrintYn(vo);
		if (map.get("arrrgNo") != null) {
			gamFcltsFeeMngNticDao.updateUnpaidFDlyBillPrtYn(vo);
		}
		gamFcltsFeeMngNticDao.updateRevCollFBillPrtYn(vo);
		if ("Y".equals(vo.get("nhtPrintYn"))) {
			processCreateEgiro(vo);
		} else {
			processCancelEgiro(vo);
		}
	}

	@SuppressWarnings({ "rawtypes"})
	@Override
	public void cancelFcltsFeeMngNticIssue(Map<String, Object> vo) throws Exception {
		String strNhtPrintYn = (String)vo.get("nhtPrintYn");
		if ("Y".equals(strNhtPrintYn)) {
			Map map = gamFcltsFeeMngNticDao.selectMngFeeLevRequestFByPk(vo);
			gamFcltsFeeMngNticDao.updateFcltsFeeMngNticNhtPrintYn(vo);
			String strArrrgNo = (String)map.get("arrrgNo");
			if (strArrrgNo == null && !"".equals(strArrrgNo) && !"00".equals(strArrrgNo)) {
				gamFcltsFeeMngNticDao.updateUnpaidFDlyBillPrtYn(vo);
			}
			gamFcltsFeeMngNticDao.updateRevCollFBillPrtYn(vo);
			processCancelEgiro(vo);
		}
		gamFcltsFeeMngNticDao.deleteRevCollF(vo);
		gamFcltsFeeMngNticDao.updateFcltsFeeMngNticIssue(vo);
	}

}
