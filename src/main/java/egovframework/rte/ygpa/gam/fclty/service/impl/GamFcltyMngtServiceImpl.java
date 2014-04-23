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
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyMngtService;

/**
 *
 * @author kok
 * @since 2014. 2. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 3.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltyMngtService")
public class GamFcltyMngtServiceImpl extends AbstractServiceImpl implements GamFcltyMngtService{

	@Resource(name="gamFcltyMngtDao")
    private GamFcltyMngtDao gamFcltyMngtDao;

	/**
	 * 시설관리 목록
	 */
	public List selectFcltyMngtList(GamFcltyManageVO vo) throws Exception {
   		return (List)gamFcltyMngtDao.selectFcltyMngtList(vo);
	}


	/**
	 * 시설관리 총 수
	 */
	public int selectFcltyMngtListTotCnt(GamFcltyManageVO vo) throws Exception {
		return gamFcltyMngtDao.selectFcltyMngtListTotCnt(vo);
    }


	/**
	 * 시설관리 파일 목록
	 */
	public List<ComDefaultVO> selectFcltyMngtPhotoList(GamFcltyManageVO vo) throws Exception {
		return (List<ComDefaultVO>)gamFcltyMngtDao.selectFcltyMngtPhotoList(vo);
	}


	/**
	 * 시설관리 파일 총 수
	 */
	public int selectFcltyMngtPhotoListTotCnt(GamFcltyManageVO vo) throws Exception {
		return gamFcltyMngtDao.selectFcltyMngtPhotoListTotCnt(vo);
	}


	/**
	 * 시설관리 시퀀스
	 */
	public String insertFcltyGetSeq() throws Exception {
		return gamFcltyMngtDao.insertFcltyGetSeq();
	}


	// 시설관리 저장
	public String insertFclty(Map form) throws Exception{
		return gamFcltyMngtDao.insertFclty(form);
	}

	// 시설관리 수정
	public void updateFclty(Map form) throws Exception{
		gamFcltyMngtDao.updateFclty(form);
	}

	// 시설 정보 삭제
	public void deleteFclty(Map vo) throws Exception{
		gamFcltyMngtDao.deleteFclty(vo);
	}

	// 시설 파일 삭제
	public List mergeFcltyPhotoMngt(Map mergeMap, String prtFcltySe) throws Exception{
        ArrayList arraylistCU = (ArrayList)mergeMap.get("CU");
        HashMap[] hmCU = (HashMap[])arraylistCU.toArray(new HashMap[arraylistCU.size()]);
        Map result;
        Integer photoSeq=0;

		if(hmCU.length>0) photoSeq=gamFcltyMngtDao.selectFcltyPhotoMaxSeq(hmCU[0]);
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

		return gamFcltyMngtDao.mergeFcltyPhoto(mergeMap);
		}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFcltyMngtService#fcltyMngSelectView(java.util.Map)
	 */
	@Override
	public EgovMap fcltyMngSelectView(Map vo)
			throws Exception {
		return gamFcltyMngtDao.fcltyMngSelectView(vo);
	}

}