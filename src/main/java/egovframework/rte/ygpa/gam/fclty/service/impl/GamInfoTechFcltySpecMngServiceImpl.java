/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamInfoTechFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamInfoTechFcltySpecMngVO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 17.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamInfoTechFcltySpecMngService")
public class GamInfoTechFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamInfoTechFcltySpecMngService{
	@Resource(name="gamInfoTechFcltySpecMngDao")
	private GamInfoTechFcltySpecMngDao gamInfoTechFcltySpecMngDao;
	/**
	 * 정보통신시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	public List<?> selectInfoTechFcltySpecMngList(GamInfoTechFcltySpecMngVO searchVO) throws Exception {
		return gamInfoTechFcltySpecMngDao.selectInfoTechFcltySpecMngList(searchVO);
	}
	
	/**
	 * 정보통신시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectInfoTechFcltySpecMngListTotCnt(GamInfoTechFcltySpecMngVO searchVO) throws Exception {
		return gamInfoTechFcltySpecMngDao.selectInfoTechFcltySpecMngListTotCnt(searchVO);
	}

	/**
	 * 정보통신시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */		
	public EgovMap selectInfoTechFcltySpecMngDetail(Map<?, ?> searchVO) throws Exception {
		return gamInfoTechFcltySpecMngDao.selectInfoTechFcltySpecMngDetail(searchVO);
	}
	
	/**
	 * 정보통신시설재원관리 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertInfoTechFcltySpecMngDetail(Map<?, ?> vo) throws Exception {
		gamInfoTechFcltySpecMngDao.insertInfoTechFcltySpecMngDetail(vo);
	}
	
	/**
	 * 정보통신시설재원관리 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateInfoTechFcltySpecMngDetail(Map<?, ?> vo) throws Exception {
		gamInfoTechFcltySpecMngDao.updateInfoTechFcltySpecMngDetail(vo);
	}
	
	/**
	 * 정보통신시설재원관리 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void deleteInfoTechFcltySpecMngDetail(Map<?, ?> vo) throws Exception {
		gamInfoTechFcltySpecMngDao.deleteInfoTechFcltySpecMngDetail(vo);
	}	
	
	/**
	 * 정보통신시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public List<?> selectInfoTechFcltySpecFileList(GamInfoTechFcltySpecMngVO searchVO) throws Exception {
		return gamInfoTechFcltySpecMngDao.selectInfoTechFcltySpecFileList(searchVO);
	}

	/**
	 * 정보통신시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public int selectInfoTechFcltySpecFileListTotCnt(GamInfoTechFcltySpecMngVO searchVO) throws Exception {
		return gamInfoTechFcltySpecMngDao.selectInfoTechFcltySpecFileListTotCnt(searchVO);
	}

	/**
	 * 정보통신시설재원관리 첨부파일 목록을 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	public void deleteInfoTechFcltySpecFileList(Map<?, ?> vo) throws Exception {
		gamInfoTechFcltySpecMngDao.deleteInfoTechFcltySpecFileList(vo);
	}
	
	/**
	 * 정보통신시설재원관리 첨부파일목록을 병합하여 저장한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	public void mergeFcltyFileMngt(Map<String, Object> mergeMap) throws Exception{
		gamInfoTechFcltySpecMngDao.mergeFcltyFile(mergeMap);
	}	
}
