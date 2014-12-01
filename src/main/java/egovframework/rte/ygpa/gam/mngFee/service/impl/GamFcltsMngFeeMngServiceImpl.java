/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngDetailVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamGasUsageSttusMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamGrHseEmitQyMngService;

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
	public int selectFcltsMngFeeMngListTotCnt(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception {
		return gamFcltsMngFeeMngDao.selectFcltsMngFeeMngListTotCnt(gamFcltsMngFeeMngVo);
	}

	@Override
	public List selectFcltsMngFeeMngList(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception {
		return gamFcltsMngFeeMngDao.selectFcltsMngFeeMngList(gamFcltsMngFeeMngVo);
	}

	@Override
	public void insertFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception {
		gamFcltsMngFeeMngDao.insertFcltsMngFeeMng(gamFcltsMngFeeMngVo);
	}

	@Override
	public void deleteFcltsMngFeeMng(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception {
		gamFcltsMngFeeMngDao.deleteFcltsMngFeeMngDetail(gamFcltsMngFeeMngDetailVo);
		gamFcltsMngFeeMngDao.deleteFcltsMngFeeMng(gamFcltsMngFeeMngDetailVo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngService#mergeSaveFcltsMngFeeMng(java.util.Map)
	 */
	@Override
	public List mergeSaveFcltsMngFeeMng(Map mergeList) throws Exception {
	        ArrayList arraylistCU = (ArrayList)mergeList.get("CU");
	        HashMap[] hmCU = (HashMap[])arraylistCU.toArray(new HashMap[arraylistCU.size()]);
	        Map result;
	        Integer photoSeq=0;

//			if(hmCU.length>0) photoSeq=gamFcltsMngFeeMngDao.mergeSaveFcltsMngFeeMng(hmCU[0]);
	        //수정처리 & 입력처리
	        for (int i=0; i<hmCU.length; i++) {
	        	if ("I".equals(hmCU[i].get("_updtId"))) {
	            	log.debug("#photoeq : "+photoSeq.toString());
	            	hmCU[i].put("photoSeq", photoSeq++);
	            	hmCU[i].put("prtFcltySe", null);
	            }
	        	else if("U".equals(hmCU[i].get("_updtId"))){
	        	}
	            else {
	            	log.debug("unknown RowStatus ["+i+"] : "+hmCU[i].get("_updtId"));
	            }
	        }

			return gamFcltsMngFeeMngDao.mergeSaveFcltsMngFeeMng(mergeList);
			}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngService#insertFcltsMngFeeMngDetail(egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngVo)
	 */
	@Override
	public void insertFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception {

		gamFcltsMngFeeMngDao.insertFcltsMngFeeMngDetail(gamFcltsMngFeeMngDetailVo);
	}

	/*
	 * 관리비 관리 상세 테이블 조회한다.
	 */
	@Override
	public List selectFcltsMngFeeMngDetailList(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception {
		return gamFcltsMngFeeMngDao.selectFcltsMngFeeMngDetailList(gamFcltsMngFeeMngDetailVo);
	}

	/*
	 * 관리비 관리 마스터 테이블 update
	 * @see egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngService#updateFcltsMngFeeMng(egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngVo)
	 */
	@Override
	public void updateFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception {
		gamFcltsMngFeeMngDao.updateFcltsMngFeeMng(gamFcltsMngFeeMngVo);
	}

	/*
	 *  관리비 관리 마스터 테이블 update
	 * @see egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngService#updateFcltsMngFeeMngDetail(egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngDetailVo)
	 */
	@Override
	public void updateFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception {
		gamFcltsMngFeeMngDao.updateFcltsMngFeeMngDetail(gamFcltsMngFeeMngDetailVo);

	}

	/*
	 * 관리비 관리 마스터 테이블 delete
	 * @see egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngService#deleteFcltsMngFeeMngDetail(egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngDetailVo)
	 */
	@Override
	public void deleteFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception {
		gamFcltsMngFeeMngDao.deleteFcltsMngFeeMngDetail(gamFcltsMngFeeMngDetailVo);

	}

	/*
	 * 관리비 관리 요금 코드 select
	 * @see egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngService#selectChargeKndList(egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngVo)
	 */
	@Override
	public List selectChargeKndList() throws Exception {

		return gamFcltsMngFeeMngDao.selectChargeKndList();
	}

}