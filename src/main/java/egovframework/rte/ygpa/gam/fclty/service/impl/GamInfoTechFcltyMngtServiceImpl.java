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
import egovframework.rte.ygpa.gam.fclty.service.GamInfoTechFcltyMngtService;

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

@Service("gamInfoTechFcltyMngtService")
public class GamInfoTechFcltyMngtServiceImpl extends AbstractServiceImpl implements GamInfoTechFcltyMngtService{

	@Resource(name="gamInfoTechFcltyMngtDao")
    private GamInfoTechFcltyMngtDao gamInfoTechFcltyMngtDao;

	/**
	 * 시설관리 목록
	 */
	public List selectInfoTechFcltyMngtList(GamFcltyManageVO vo) throws Exception {
   		return (List)gamInfoTechFcltyMngtDao.selectInfoTechFcltyMngtList(vo);
	}


	/**
	 * 시설관리 총 수
	 */
	public int selectInfoTechFcltyMngtListTotCnt(GamFcltyManageVO vo) throws Exception {
		return gamInfoTechFcltyMngtDao.selectInfoTechFcltyMngtListTotCnt(vo);
    }


	/**
	 * 시설관리 파일 목록
	 */
	public List<ComDefaultVO> selectInfoTechFcltyMngtPhotoList(GamFcltyManageVO vo) throws Exception {
		return (List<ComDefaultVO>)gamInfoTechFcltyMngtDao.selectInfoTechFcltyMngtPhotoList(vo);
	}


	/**
	 * 시설관리 파일 총 수
	 */
	public int selectInfoTechFcltyMngtPhotoListTotCnt(GamFcltyManageVO vo) throws Exception {
		return gamInfoTechFcltyMngtDao.selectInfoTechFcltyMngtPhotoListTotCnt(vo);
	}


	/**
	 * 시설관리 시퀀스
	 */
	public String insertInfoTechFcltyGetSeq() throws Exception {
		return gamInfoTechFcltyMngtDao.insertInfoTechFcltyGetSeq();
	}


	// 시설관리 저장
	public String insertInfoTechFclty(Map form) throws Exception{
		return gamInfoTechFcltyMngtDao.insertInfoTechFclty(form);
	}

	// 시설관리 수정
	public void updateInfoTechFclty(Map form) throws Exception{
		gamInfoTechFcltyMngtDao.updateInfoTechFclty(form);
	}

	// 시설 정보 삭제
	public void deleteInfoTechFclty(Map vo) throws Exception{
		gamInfoTechFcltyMngtDao.deleteInfoTechFclty(vo);
	}

	// 시설 파일 삭제
	public List mergeInfoTechFcltyPhotoMngt(Map mergeMap, String prtFcltySe) throws Exception{
        ArrayList arraylistCU = (ArrayList)mergeMap.get("CU");
        HashMap[] hmCU = (HashMap[])arraylistCU.toArray(new HashMap[arraylistCU.size()]);
        Map result;
        Integer photoSeq=0;

		if(hmCU.length>0) photoSeq=gamInfoTechFcltyMngtDao.selectInfoTechFcltyPhotoMaxSeq(hmCU[0]);
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

		return gamInfoTechFcltyMngtDao.mergeInfoTechFcltyPhoto(mergeMap);
		}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFcltyMngtService#fcltyMngSelectView(java.util.Map)
	 */
	@Override
	public EgovMap InfoTechfcltyMngSelectView(Map vo)
			throws Exception {
		return gamInfoTechFcltyMngtDao.InfoTechfcltyMngSelectView(vo);
	}

}