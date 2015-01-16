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
import egovframework.rte.ygpa.gam.cmmn.fclty.service.impl.GamGisPrtFcltyCdMngtDao;
import egovframework.rte.ygpa.gam.fclty.service.GamElctyFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamElctyFcltySpecMngVO;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 19.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 19.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamElctyFcltySpecMngService")
public class GamElctyFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamElctyFcltySpecMngService{
	
	@Resource(name="gamElctyFcltySpecMngDao")
	private GamElctyFcltySpecMngDao gamElctyFcltySpecMngDao;
	
	@Resource(name="gamGisPrtFcltyCdMngtDao")
	GamGisPrtFcltyCdMngtDao gamGisPrtFcltyCdMngtDao;
	
	/**
	 * 전기시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	public List<?> selectElctyFcltySpecMngList(GamElctyFcltySpecMngVO searchVO) throws Exception {
		return gamElctyFcltySpecMngDao.selectElctyFcltySpecMngList(searchVO);
	}
	
	/**
	 * 전기시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectElctyFcltySpecMngListTotCnt(GamElctyFcltySpecMngVO searchVO) throws Exception {
		return gamElctyFcltySpecMngDao.selectElctyFcltySpecMngListTotCnt(searchVO);
	}

	/**
	 * 전기시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */		
	public EgovMap selectElctyFcltySpecMngDetail(Map<?,?> searchVO) throws Exception {
		return gamElctyFcltySpecMngDao.selectElctyFcltySpecMngDetail(searchVO);
	}
	
	/**
	 * 전기시설재원관리 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertElctyFcltySpecMngDetail(Map<?, ?> vo) throws Exception {
		gamElctyFcltySpecMngDao.insertElctyFcltySpecMngDetail(vo);
		gamGisPrtFcltyCdMngtDao.insertGisPrtFclty(vo);
	}
	
	/**
	 * 전기시설재원관리 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateElctyFcltySpecMngDetail(Map<?, ?> vo) throws Exception {
		gamElctyFcltySpecMngDao.updateElctyFcltySpecMngDetail(vo);
		gamGisPrtFcltyCdMngtDao.updateGisPrtFclty(vo);
	}
	
	/**
	 * 전기시설재원관리 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void deleteElctyFcltySpecMngDetail(Map<?, ?> vo) throws Exception {
		gamElctyFcltySpecMngDao.deleteElctyFcltySpecMngDetail(vo);
		gamElctyFcltySpecMngDao.deleteElctyFcltySpecFileList(vo);
		gamGisPrtFcltyCdMngtDao.deleteGisPrtFclty(vo);
	}	
	
	/**
	 * 전기시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public List<?> selectElctyFcltySpecFileList(GamElctyFcltySpecMngVO searchVO) throws Exception {
		return gamElctyFcltySpecMngDao.selectElctyFcltySpecFileList(searchVO);
	}

	/**
	 * 전기시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public int selectElctyFcltySpecFileListTotCnt(GamElctyFcltySpecMngVO searchVO) throws Exception {
		return gamElctyFcltySpecMngDao.selectElctyFcltySpecFileListTotCnt(searchVO);
	}


	/**
	 * 전기시설재원관리 첨부파일 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertElctyFcltySpecFileDetail(Map<?, ?> vo) throws Exception {
		gamElctyFcltySpecMngDao.insertElctyFcltySpecFileDetail(vo);
	}
	
	/**
	 * 전기시설재원관리 첨부파일 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateElctyFcltySpecFileDetail(Map<?, ?> vo) throws Exception {
		gamElctyFcltySpecMngDao.updateElctyFcltySpecFileDetail(vo);
	}

	/**
	 * 전기시설재원관리 첨부파일 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	public void deleteElctyFcltySpecFileDetail(Map<?, ?> vo) throws Exception {
		gamElctyFcltySpecMngDao.deleteElctyFcltySpecFileDetail(vo);
	}

	
	/**
	 * 전기시설재원관리 첨부파일목록을 병합하여 저장한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	public List<?> mergeFcltyFileMngt(Map<String,Object> mergeMap) throws Exception{
		return gamElctyFcltySpecMngDao.mergeFcltyFile(mergeMap);
	}
	
}
