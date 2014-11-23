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
	public void deleteFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception {
		gamFcltsMngFeeMngDao.deleteFcltsMngFeeMng(gamFcltsMngFeeMngVo);
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

}