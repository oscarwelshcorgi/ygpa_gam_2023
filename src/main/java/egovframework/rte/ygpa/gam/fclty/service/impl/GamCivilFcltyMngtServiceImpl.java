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
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltyMngtService;
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
 *  2014. 6. 257.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamCivilFcltyMngtService")
public class GamCivilFcltyMngtServiceImpl extends AbstractServiceImpl implements GamCivilFcltyMngtService{

	@Resource(name="gamCivilFcltyMngtDao")
    private GamCivilFcltyMngtDao gamCivilFcltyMngtDao;

	/**
	 * 시설관리 목록
	 */
	public List selectCivilFcltyMngtList(GamFcltyManageVO vo) throws Exception {
   		return (List)gamCivilFcltyMngtDao.selectCivilFcltyMngtList(vo);
	}


	/**
	 * 시설관리 총 수
	 */
	public int selectCivilFcltyMngtListTotCnt(GamFcltyManageVO vo) throws Exception {
		return gamCivilFcltyMngtDao.selectCivilFcltyMngtListTotCnt(vo);
    }


	/**
	 * 시설관리 파일 목록
	 */
	public List<ComDefaultVO> selectCivilFcltyMngtPhotoList(GamFcltyManageVO vo) throws Exception {
		return (List<ComDefaultVO>)gamCivilFcltyMngtDao.selectCivilFcltyMngtPhotoList(vo);
	}


	/**
	 * 시설관리 파일 총 수
	 */
	public int selectCivilFcltyMngtPhotoListTotCnt(GamFcltyManageVO vo) throws Exception {
		return gamCivilFcltyMngtDao.selectCivilFcltyMngtPhotoListTotCnt(vo);
	}


	/**
	 * 시설관리 시퀀스
	 */
	public String insertCivilFcltyGetSeq() throws Exception {
		return gamCivilFcltyMngtDao.insertCivilFcltyGetSeq();
	}


	// 시설관리 저장
	public String insertCivilFclty(Map form) throws Exception{
		return gamCivilFcltyMngtDao.insertCivilFclty(form);
	}

	// 시설관리 수정
	public void updateCivilFclty(Map form) throws Exception{
		gamCivilFcltyMngtDao.updateCivilFclty(form);
	}

	// 시설 정보 삭제
	public void deleteCivilFclty(Map vo) throws Exception{
		gamCivilFcltyMngtDao.deleteCivilFclty(vo);
	}

	// 시설 파일 삭제
	public List mergeCivilFcltyPhotoMngt(Map mergeMap, String prtFcltySe) throws Exception{
        ArrayList arraylistCU = (ArrayList)mergeMap.get("CU");
        HashMap[] hmCU = (HashMap[])arraylistCU.toArray(new HashMap[arraylistCU.size()]);
        Map result;
        Integer photoSeq=0;

		if(hmCU.length>0) photoSeq=gamCivilFcltyMngtDao.selectCivilFcltyPhotoMaxSeq(hmCU[0]);
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

		return gamCivilFcltyMngtDao.mergeCivilFcltyPhoto(mergeMap);
		}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFcltyMngtService#fcltyMngSelectView(java.util.Map)
	 */
	@Override
	public EgovMap CivilfcltyMngSelectView(Map vo)
			throws Exception {
		return gamCivilFcltyMngtDao.CivilfcltyMngSelectView(vo);
	}

}