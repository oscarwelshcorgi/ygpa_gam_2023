/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyManageVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltySpecMngService;

/**
 *
 * @author HNJ
 * @since 2014. 11. 4.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 4.	HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltySpecMngService")
public class GamFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamFcltySpecMngService{

	@Resource(name="gamFcltySpecMngDao")
    private GamFcltySpecMngDao gamFcltySpecMngDao;

	/**
	 * 시설관리 목록
	 */
	public List selectFcltySpecMngList(GamFcltyManageVO vo) throws Exception {
   		return (List)gamFcltySpecMngDao.selectFcltySpecMngList(vo);
	}


	/**
	 * 시설관리 총 수
	 */
	public int selectFcltySpecMngListTotCnt(GamFcltyManageVO vo) throws Exception {
		return gamFcltySpecMngDao.selectFcltySpecMngListTotCnt(vo);
    }


	/**
	 * 시설관리 파일 목록
	 */
	public List<ComDefaultVO> selectFcltySpecMngPhotoList(GamFcltyManageVO vo) throws Exception {
		return (List<ComDefaultVO>)gamFcltySpecMngDao.selectFcltySpecMngPhotoList(vo);
	}


	/**
	 * 시설관리 파일 총 수
	 */
	public int selectFcltySpecMngPhotoListTotCnt(GamFcltyManageVO vo) throws Exception {
		return gamFcltySpecMngDao.selectFcltySpecMngPhotoListTotCnt(vo);
	}


	/**
	 * 시설관리 시퀀스
	 */
	public String insertFcltyGetSeq() throws Exception {
		return gamFcltySpecMngDao.insertFcltyGetSeq();
	}


	// 시설관리 저장
	public String insertFclty(Map form) throws Exception{
		return gamFcltySpecMngDao.insertFclty(form);
	}

	// 시설관리 수정
	public void updateFclty(Map form) throws Exception{
		gamFcltySpecMngDao.updateFclty(form);
	}

	// 시설 정보 삭제
	public void deleteFclty(Map vo) throws Exception{
		gamFcltySpecMngDao.deleteFclty(vo);
	}

	// 시설 파일 삭제
	public List mergeFcltyPhotoMngt(Map mergeMap, String prtFcltySe) throws Exception{
        ArrayList arraylistCU = (ArrayList)mergeMap.get("CU");
        HashMap[] hmCU = (HashMap[])arraylistCU.toArray(new HashMap[arraylistCU.size()]);
        Map result;
        Integer photoSeq=0;

		if(hmCU.length>0) photoSeq=gamFcltySpecMngDao.selectFcltyPhotoMaxSeq(hmCU[0]);
        //수정처리 & 입력처리
        for (int i=0; i<hmCU.length; i++) {
        	if ("I".equals(hmCU[i].get("_updtId"))) {
            	log.debug("#photoeq : "+photoSeq.toString());
            	hmCU[i].put("photoSeq", photoSeq++);
            	hmCU[i].put("prtFcltySe", prtFcltySe);
            }
        	else if("U".equals(hmCU[i].get("_updtId"))){
        	}
            else {
            	log.debug("unknown RowStatus ["+i+"] : "+hmCU[i].get("_updtId"));
            }
        }

		return gamFcltySpecMngDao.mergeFcltyPhoto(mergeMap);
		}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFcltySpecMngService#fcltyMngSelectView(java.util.Map)
	 */
	@Override
	public EgovMap fcltyMngSelectView(Map vo)
			throws Exception {
		return gamFcltySpecMngDao.fcltyMngSelectView(vo);
	}

}