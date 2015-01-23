/**
 *
 */
package egovframework.rte.ygpa.gam.code.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.ccm.zip.service.ZipVO;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.util.fileupload.multi.service.FileInfoVO;
import egovframework.rte.ygpa.gam.cmmn.service.GamFileServiceVo;
import egovframework.rte.ygpa.gam.cmmn.service.GamFileUploadUtil;
import egovframework.rte.ygpa.gam.code.service.GamOlnlpFVO;
import egovframework.rte.ygpa.gam.code.service.GamOlnlpMngtService;
import egovframework.rte.ygpa.gam.code.service.GisAssetsCodeVO;

/**
 *
 * @author kok
 * @since 2014. 3. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 7.		kok		최초 생성
 *  2014.04.11. eunsungj	엑셀파일 등록 기능 추가
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamOlnlpMngtController {

	protected Log log = LogFactory.getLog(this.getClass());

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	@Resource(name = "gamOlnlpMngtService")
	protected GamOlnlpMngtService gamOlnlpMngtService;

	@Resource(name = "excelOlnlpService")
	private EgovExcelService excelOlnlpService;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /**
     * 임시파일 아이디를 생성한다.
     */
    @Resource(name="gamTempFileIdGnrService")
    EgovTableIdGnrService gamTempFileIdGnrService;

	/**
     * 공시지가 관리화면호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/code/gamOlnlpMngt.do")
    String indexConstOlnlpMngt(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/code/GamOlnlpMngt";
    }

	/**
	 * 공시지가 등록 현황 목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/gamOlnlpInsertList.do")
	@ResponseBody Map<String, Object> selectOlnlpInsertList(GisAssetsCodeVO searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}
		// 내역 조회
		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */
		List<GisAssetsCodeVO> OlnlpMngtList = gamOlnlpMngtService.selectOlnlpInsertList(searchVO);
		int totCnt = gamOlnlpMngtService.selectOlnlpInsertListTotCnt(searchVO);

		System.out.print("result ******************************** : " + OlnlpMngtList);

		paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
		map.put("totalCount", totCnt);
		map.put("resultList", OlnlpMngtList);
		map.put("searchOption", searchVO);

		return map;
	}


	/**
	 * 공시지가 목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/gamOlnlpMngtList.do")
	@ResponseBody Map<String, Object> selectOlnlpMngtList(GamOlnlpFVO searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */
		List<GamOlnlpFVO> olnlpMngtList = gamOlnlpMngtService.selectOlnlpMngtList(searchVO);
        int totCnt = gamOlnlpMngtService.selectOlnlpMngtListTotCnt(searchVO);



        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", olnlpMngtList);
    	map.put("searchOption", searchVO);

    	return map;
    }


	/**
	 * 공시지가 목록 관리 등록
	 * @param olnlpManageVO
	 * @param bindingResult
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping("/code/insertOlnlpMngt.do")
    @ResponseBody Map<String, Object> insertOlnlpMngt(GamOlnlpFVO olnlpVO, BindingResult bindingResult)throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

        beanValidator.validate(olnlpVO, bindingResult);
		if (bindingResult.hasErrors()){
	        map.put("resultCode", 1);
			map.put("resultMsg", "입력 값에 오류가 있습니다.");
			map.put("resultObject", bindingResult.getAllErrors());

		}else{

			try {

				olnlpVO.setRegUsr(user.getId());
				gamOlnlpMngtService.insertOlnlpMngt(olnlpVO);
				map.put("resultCode", 0);
				map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
			} catch (Exception e) {
				// TODO: handle exception

				map.put("resultCode", 1);
				map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
			}
		}

        return map;
    }


	/**
	 * 공시지가 목록 관리 수정
	 * @param olnlpManageVO
	 * @param bindingResult
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping("/code/updateOlnlpMngt.do")
    @ResponseBody Map<String, Object> updateOlnlpMngt(GamOlnlpFVO olnlpVO, BindingResult bindingResult)throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

        beanValidator.validate(olnlpVO, bindingResult);
		if (bindingResult.hasErrors()){
	        map.put("resultCode", 1);
			map.put("resultMsg", "입력 값에 오류가 있습니다.");
			map.put("resultObject", bindingResult.getAllErrors());

		}else{

			try {
				olnlpVO.setUpdUsr(user.getId());
				gamOlnlpMngtService.updateOlnlpMngt(olnlpVO);
				map.put("resultCode", 0);
				map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
			} catch (Exception e) {
				// TODO: handle exception

				map.put("resultCode", 1);
				map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
			}
		}

        return map;
    }


    /**
     * 건축 시설관리 삭제
     * @param olnlpManageVO
     * @return map
     * @throws Exception
     */
    @RequestMapping("/code/gamOlnlpDelete.do")
    @ResponseBody Map<String, Object> deleteOlnlpMngt(GamOlnlpFVO olnlpVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		gamOlnlpMngtService.deleteOlnlpMngt(olnlpVO);

	        map.put("resultCode", 0);
	        map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));

		} catch (Exception e) {
			// TODO: handle exception

	        map.put("resultCode", 1);
	        map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

    	return map;
    }

	@RequestMapping(value="/code/mergeGamOlnlpMngt.do")
	@ResponseBody Map<String, Object> mergeGamOlnlpMngt(@RequestParam Map<String, Object> dataList) throws Exception {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		Map<String,Object> map = new HashMap<String,Object>();
		Map<String, String> userMap = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	List<HashMap<String,String>> insertList=null;
    	List<HashMap<String,String>> updateList=null;
    	List<HashMap<String,String>> deleteList=null;
    	List<Map<String,String>> userList=null;

    	int resultCode = -1;
    	String resultMsg = "";

		insertList = mapper.readValue((String)dataList.get("insertList"),
		    new TypeReference<List<HashMap<String,String>>>(){});

		updateList = mapper.readValue((String)dataList.get("updateList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		deleteList = mapper.readValue((String)dataList.get("deleteList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		userList = new ArrayList();
		userMap.put("id",  loginVO.getId());
		userList.add(userMap);

		Map<String,Object> mergeMap = new HashMap<String,Object>();

		insertList.addAll(updateList);	// combine list

		mergeMap.put("CU", insertList);
		mergeMap.put("D", deleteList);
		mergeMap.put("USER", userList);

		gamOlnlpMngtService.mergeOlnlpMngt(mergeMap);

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));

		return map;
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/code/uploadOlnlpXlsFile.do", method=RequestMethod.POST)
    public @ResponseBody Map uploadOlnlpXlsFile(HttpServletRequest request) throws Exception {
    	File file = null;
		InputStream fis = null; // 2011.11.1 보안점검 후속조치

		Map map = new HashMap();
		String uploadPath = EgovProperties.getProperty("Globals.fileStorePath");
		List<GamFileServiceVo> list = GamFileUploadUtil.uploadFiles(request, uploadPath, gamTempFileIdGnrService);

		if(list.size()==0) {
			map.put("resultCode", "-1");
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.upload"));
			return map;
		}
		else if(list.size()!=1) {
			map.put("resultCode", "-2");
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.not_one_upload"));
			return map;
		}

		GamFileServiceVo filevo = list.get(0);

		if (!"".equals(filevo.getPhyscalFileNm())) {
			if (filevo.getPhyscalFileNm().endsWith(".xls")
					|| filevo.getPhyscalFileNm().endsWith(".xlsx")
					|| filevo.getPhyscalFileNm().endsWith(".XLS")
					|| filevo.getPhyscalFileNm().endsWith(".XLSX")) {

				try {
					file = new File(uploadPath+filevo.getPhyscalFileNm());
					fis = new FileInputStream(file);
					gamOlnlpMngtService.deleteOlnlpBJD();
					excelOlnlpService.uploadExcel("gamOlnlpMngtDao.insertGamBupjungdongOlnlp_S", fis, 1);
					gamOlnlpMngtService.createOlnlpFromBJD();
				}
				catch(FileNotFoundException e) {
					map.put("resultCode", "-1");
					map.put("resultMsg", egovMessageSource.getMessage("fail.common.upload"));
					return map;
				}
				catch(OfficeXmlFileException e) {
					map.put("resultCode", "-5");
					map.put("resultMsg", e.getMessage());
					return map;
				}
				catch(NumberFormatException e) {
					map.put("resultCode", "-4");
					map.put("resultMsg", egovMessageSource.getMessage("fail.common.invalid_number_format")+e.getMessage());
					return map;
				}
				catch(IllegalArgumentException e) {
					map.put("resultCode", "-3");
					map.put("resultMsg", egovMessageSource.getMessage("fail.common.invalid_xls_format"));
					return map;
				}
				catch(Exception e) {
					map.put("resultCode", "-3");
					map.put("resultMsg", egovMessageSource.getMessage("fail.common.invalid_xls_format")+" : "+e.getMessage());
					return map;
				} finally {
					if (fis != null)	// 2011.11.1 보안점검 후속조치
						fis.close();
				}

			}else{
				log.info("xls, xlsx 파일 타입만 등록이 가능합니다.");
				map.put("resultCode", "-3");
				map.put("resultMsg", "xls, xlsx 파일 타입만 등록이 가능합니다.");

				return map;
			}
		}
		map.put("resultCode", "0");

		return map;
	}

}