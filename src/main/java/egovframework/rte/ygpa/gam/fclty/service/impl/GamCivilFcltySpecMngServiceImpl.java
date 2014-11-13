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
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltySpecMngVO;

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
 *  2014. 11. 10.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamCivilFcltySpecMngService")
public class GamCivilFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamCivilFcltySpecMngService{
	@Resource(name="gamCivilFcltySpecMngDao")
	private GamCivilFcltySpecMngDao gamCivilFcltySpecMngDao;
	/**
	 * 토목시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	public List selectCivilFcltySpecMngList(GamCivilFcltySpecMngVO searchVO) throws Exception {
		return gamCivilFcltySpecMngDao.selectCivilFcltySpecMngList(searchVO);
	}
	
	/**
	 * 토목시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectCivilFcltySpecMngListTotCnt(GamCivilFcltySpecMngVO searchVO) throws Exception {
		return gamCivilFcltySpecMngDao.selectCivilFcltySpecMngListTotCnt(searchVO);
	}

	/**
	 * 토목시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */		
	public EgovMap selectCivilFcltySpecMngDetail(Map searchVO) throws Exception {
		return gamCivilFcltySpecMngDao.selectCivilFcltySpecMngDetail(searchVO);
	}
	
	/**
	 * 토목시설재원관리 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertCivilFcltySpecMngDetail(Map<String, Object> vo) throws Exception {
		gamCivilFcltySpecMngDao.insertCivilFcltySpecMngDetail(vo);
	}
	
	/**
	 * 토목시설재원관리 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateCivilFcltySpecMngDetail(Map<String, Object> vo) throws Exception {
		gamCivilFcltySpecMngDao.updateCivilFcltySpecMngDetail(vo);
	}
	
	/**
	 * 토목시설재원관리 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void deleteCivilFcltySpecMngDetail(Map<String, Object> vo) throws Exception {
		gamCivilFcltySpecMngDao.deleteCivilFcltySpecMngDetail(vo);
	}	
	
	/**
	 * 토목시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public List selectCivilFcltySpecFileList(GamCivilFcltySpecMngVO searchVO) throws Exception {
		return gamCivilFcltySpecMngDao.selectCivilFcltySpecFileList(searchVO);
	}

	/**
	 * 토목시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public int selectCivilFcltySpecFileListTotCnt(GamCivilFcltySpecMngVO searchVO) throws Exception {
		return gamCivilFcltySpecMngDao.selectCivilFcltySpecFileListTotCnt(searchVO);
	}


	/**
	 * 토목시설재원관리 첨부파일 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertCivilFcltySpecFileDetail(Map<String, Object> vo) throws Exception {
		gamCivilFcltySpecMngDao.insertCivilFcltySpecFileDetail(vo);
	}
	
	/**
	 * 토목시설재원관리 첨부파일 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateCivilFcltySpecFileDetail(Map<String, Object> vo) throws Exception {
		gamCivilFcltySpecMngDao.updateCivilFcltySpecFileDetail(vo);
	}

	/**
	 * 토목시설재원관리 첨부파일 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	public void deleteCivilFcltySpecFileDetail(Map<String, Object> vo) throws Exception {
		gamCivilFcltySpecMngDao.deleteCivilFcltySpecFileDetail(vo);
	}

	/**
	 * 토목시설재원관리 첨부파일 목록을 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	public void deleteCivilFcltySpecFileList(Map<String, Object> vo) throws Exception {
		gamCivilFcltySpecMngDao.deleteCivilFcltySpecFileList(vo);
	}
	
	/**
	 * 토목시설재원관리 첨부파일목록을 병합하여 저장한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	public List mergeFcltyFileMngt(Map mergeMap) throws Exception{
		return gamCivilFcltySpecMngDao.mergeFcltyFile(mergeMap);
	}
	
}
