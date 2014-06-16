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
import egovframework.rte.ygpa.gam.fclty.service.GamElctyFcltyMngtService;
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
 *  2014. 6. 16.		sj		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamElctyFcltyMngtService")
public class GamElctyFcltyMngtServiceImpl extends AbstractServiceImpl implements GamElctyFcltyMngtService{

	@Resource(name="gamElctyFcltyMngtDao")
    private GamElctyFcltyMngtDao gamElctyFcltyMngtDao;

	/**
	 * 전기관리 목록
	 */
	public List selectElctyFcltyMngtList(GamFcltyManageVO vo) throws Exception {
   		return (List)gamElctyFcltyMngtDao.selectElctyFcltyMngtList(vo);
	}


	/**
	 * 전기관리 총 수
	 */
	public int selectElctyFcltyMngtListTotCnt(GamFcltyManageVO vo) throws Exception {
		return gamElctyFcltyMngtDao.selectElctyFcltyMngtListTotCnt(vo);
    }


	/**
	 * 전기관리 파일 목록
	 */
	public List<ComDefaultVO> selectElctyFcltyMngtPhotoList(GamFcltyManageVO vo) throws Exception {
		return (List<ComDefaultVO>)gamElctyFcltyMngtDao.selectElctyFcltyMngtPhotoList(vo);
	}


	/**
	 * 전기관리 파일 총 수
	 */
	public int selectElctyFcltyMngtPhotoListTotCnt(GamFcltyManageVO vo) throws Exception {
		return gamElctyFcltyMngtDao.selectElctyFcltyMngtPhotoListTotCnt(vo);
	}


	/**
	 * 전기관리 시퀀스
	 */
	public String insertElctyFcltyGetSeq() throws Exception {
		return gamElctyFcltyMngtDao.insertElctyFcltyGetSeq();
	}


	// 전기관리 저장
	public String insertElctyFclty(Map form) throws Exception{
		return gamElctyFcltyMngtDao.insertElctyFclty(form);
	}

	// 전기관리 수정
	public void updateElctyFclty(Map form) throws Exception{
		gamElctyFcltyMngtDao.updateElctyFclty(form);
	}

	// 전기 정보 삭제
	public void deleteElctyFclty(Map vo) throws Exception{
		gamElctyFcltyMngtDao.deleteFclty(vo);
	}

	// 전기 파일 삭제
	public List mergeElctyFcltyPhotoMngt(Map mergeMap, String prtFcltySe) throws Exception{
        ArrayList arraylistCU = (ArrayList)mergeMap.get("CU");
        HashMap[] hmCU = (HashMap[])arraylistCU.toArray(new HashMap[arraylistCU.size()]);
        Map result;
        Integer photoSeq=0;

		if(hmCU.length>0) photoSeq=gamElctyFcltyMngtDao.selectElctyFcltyPhotoMaxSeq(hmCU[0]);
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

		return gamElctyFcltyMngtDao.mergeFcltyPhoto(mergeMap);
		}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFcltyMngtService#fcltyMngSelectView(java.util.Map)
	 */
	@Override
	public EgovMap ElctyfcltyMngSelectView(Map vo)
			throws Exception {
		return gamElctyFcltyMngtDao.ElctyfcltyMngSelectView(vo);
	}

}