/**
 *
 */
package egovframework.rte.ygpa.gam.popup.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.sym.ccm.zip.service.EgovCcmRdnmadZipManageService;
import egovframework.com.sym.ccm.zip.service.EgovCcmZipManageService;
import egovframework.com.sym.ccm.zip.service.Zip;
import egovframework.com.sym.ccm.zip.service.ZipVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.cmmn.service.GamFileServiceVo;
import egovframework.rte.ygpa.gam.cmmn.service.GamFileUploadUtil;

/**
 *
 * @author Administrator
 * @since 2014. 1. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 1. 29.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamPopupZipManageController {

	@Resource(name = "ZipManageService")
    private EgovCcmZipManageService zipManageService;

	@Resource(name = "RdnmadZipService")
	private EgovCcmRdnmadZipManageService rdnmadZipService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name = "multipartResolver")
	CommonsMultipartResolver mailmultipartResolver;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 화면 호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/cmmn/popup/gamPopupSearchZipView.do", method=RequestMethod.POST)
    String indexMain() throws Exception {

    	return "/ygpa/gam/cmmn/popup/GamPopupSearchZipView";
    }


    /**
     * 우편번호 찾기 목록을 조회한다.
     * @param searchVO
     * @return map
     * @throws Exception
     */
	@SuppressWarnings("rawtypes")
    @RequestMapping(value="/cmmn/popup/gamCcmZipSearchList.do", method=RequestMethod.POST)
	@ResponseBody Map<String, Object> selectZipSearchList (@ModelAttribute("searchVO") ZipVO searchVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		String sList = "";

		if (searchVO.getSearchList() != null && searchVO.getSearchList() != "" ) {
			sList = searchVO.getSearchList().substring(0, 1);
		}

		int totCnt = 0;
		List CmmnCodeList = new ArrayList();
		if (sList.equals("1")) {
			CmmnCodeList = zipManageService.selectZipList(searchVO);
	        totCnt = zipManageService.selectZipListTotCnt(searchVO);

		} else {
			CmmnCodeList = rdnmadZipService.selectZipList(searchVO);
			totCnt = rdnmadZipService.selectZipListTotCnt(searchVO);
		}
		paginationInfo.setTotalRecordCount(totCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultList", CmmnCodeList);
	    map.put("totalCount", totCnt);
	    map.put("searchOption", searchVO);
        return map;
	}


	/**
	 * 우편번호를 삭제한다.
	 * @param loginVO
	 * @param zip
	 * @param searchVO
	 * @param model
	 * @return zip
	 * @throws Exception
	 */
    @RequestMapping(value="/cmmn/popup/gamCcmZipRemove.do")
	@ResponseBody Map<String, Object> deleteZip (@ModelAttribute("loginVO") LoginVO loginVO, Zip zip, ZipVO searchVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("searchList", searchVO.getSearchList());

    	if (searchVO.getSearchList().equals("1")) {
			zipManageService.deleteZip(zip);
		} else {
			rdnmadZipService.deleteZip(zip);
		}
        return map;
	}


	/**
	 * 우편번호를 등록한다.
	 * @param loginVO
	 * @param zip
	 * @param searchVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/cmmn/popup/gamCcmZipRegist.do")
	@ResponseBody Map<String, Object> insertZip (@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("zip") Zip zip, ZipVO searchVO
			, BindingResult bindingResult) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

		map.put("searchList", searchVO.getSearchList());

		if(zip.getZip() == null || zip.getZip().equals("")) {

            return map;
    	}

    	if (searchVO.getSearchList().equals("1")) {
    		beanValidator.validate(zip, bindingResult);

    		if (bindingResult.hasErrors()){
    			return map;
    		}

    		zip.setFrstRegisterId(loginVO.getUniqId());
    		zipManageService.insertZip(zip);
    	} else {

    		zip.setFrstRegisterId(loginVO.getUniqId());
    		rdnmadZipService.insertZip(zip);
    	}
        return map;
    }


	/**
	 * 엑셀파일을 업로드하여 우편번호를 등록한다.
	 * @param loginVO
	 * @param request
	 * @param commandMap
	 * @param searchVO
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmmn/popup/gamCcmExcelZipRegist.do")
	@ResponseBody Map<String, Object> insertExcelZip(@ModelAttribute("loginVO") LoginVO loginVO
			, final HttpServletRequest request, Map<String, Object> commandMap, ZipVO searchVO) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("searchList", searchVO.getSearchList());

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if (sCmd.equals("")) {
    		return map;
    	}

		String uploadPath = EgovProperties.getProperty("global.fileStorePath")+"_temp_zip.xls";
		List<GamFileServiceVo> list = GamFileUploadUtil.uploadFiles(request, uploadPath, null);

		if(list.size()==0) {
			map.put("resultCode", "0");
//			map.put("resultMsg", egovMessageSource.getMessage("fail.common.upload"));
			return map;
		}
//    	final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//		final Map<String, MultipartFile> files = multiRequest.getFileMap();
//		InputStream fis = null; // 2011.11.1 보안점검 후속조치
//
//		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
//		MultipartFile file;
//
//		while (itr.hasNext()) {
//			Entry<String, MultipartFile> entry = itr.next();
//
//			file = entry.getValue();
//			if (!"".equals(file.getOriginalFilename())) {
//				// 2011.10.07 업로드 파일에 대한 확장자를 체크
//				if (file.getOriginalFilename().endsWith(".xls")
//						|| file.getOriginalFilename().endsWith(".xlsx")
//						|| file.getOriginalFilename().endsWith(".XLS")
//						|| file.getOriginalFilename().endsWith(".XLSX")) {
//
//					//zipManageService.deleteAllZip();
//					//excelZipService.uploadExcel("ZipManageDAO.insertExcelZip", file.getInputStream(), 2);
//					// 2011.10.21 보안점검 후속조치
//					try {
//						fis = file.getInputStream();
//						if (searchVO.getSearchList().equals("1")) {
//							zipManageService.insertExcelZip(fis);
//						} else {
//							rdnmadZipService.insertExcelZip(fis);
//						}
//					} catch(Exception e) {
//						throw e;
//					} finally {
//						if (fis != null)	// 2011.11.1 보안점검 후속조치
//							fis.close();
//					}
//
//				}else{
//					//log.info("xls, xlsx 파일 타입만 등록이 가능합니다.");
//					return map;
//				}
//				// *********** 끝 ***********
//
//			}
//		}

        return map;
	}


	/**
	 * 우편번호 상세항목을 조회한다.
	 * @param loginVO
	 * @param zip
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/cmmn/popup/gamCcmZipDetail.do")
 	@ResponseBody Map<String, Object> selectZipDetail (@ModelAttribute("loginVO") LoginVO loginVO, Zip zip, ZipVO searchVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		if (searchVO.getSearchList().equals("1")) {
			Zip vo = zipManageService.selectZipDetail(zip);
			map.put("result", vo);
			map.put("searchList", searchVO.getSearchList());
		} else {
			Zip vo = rdnmadZipService.selectZipDetail(zip);
			map.put("result", vo);
			map.put("searchList", searchVO.getSearchList());
		}

		return map;
	}


	/**
	 * 우편번호 목록을 조회한다.
	 * @param loginVO
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
    @RequestMapping(value="/cmmn/popup/gamCcmZipList.do")
	@ResponseBody Map<String, Object> selectZipList (@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("searchVO") ZipVO searchVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List CmmnCodeList = null;
		int totCnt = 0;
		if (searchVO.getSearchList().equals("1")) {
	        CmmnCodeList = zipManageService.selectZipList(searchVO);
	        totCnt = zipManageService.selectZipListTotCnt(searchVO);
		} else {
			CmmnCodeList = rdnmadZipService.selectZipList(searchVO);
			totCnt = rdnmadZipService.selectZipListTotCnt(searchVO);
		}

		map.put("resultList", CmmnCodeList);
		paginationInfo.setTotalRecordCount(totCnt);
		map.put("paginationInfo", paginationInfo);
        return map;
	}


	/**
	 * 우편번호를 수정한다.
	 * @param loginVO
	 * @param zip
	 * @param searchVO
	 * @param bindingResult
	 * @param commandMap
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping(value="/cmmn/popup/gamCcmZipModify.do")
	@ResponseBody Map<String, Object> updateZip (@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("zip") Zip zip, ZipVO searchVO
			, BindingResult bindingResult, Map<String, Object> commandMap) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		map.put("searchList", searchVO.getSearchList());

		if (sCmd.equals("")) {
    		if (searchVO.getSearchList().equals("1")) {
	    		Zip vo = zipManageService.selectZipDetail(zip);
	    		map.put("zip", vo);
    		} else {
    			Zip vo = rdnmadZipService.selectZipDetail(zip);
    			map.put("zip", vo);
    		}
    	} else if (sCmd.equals("Modify")) {
			if (searchVO.getSearchList().equals("1")) {
				beanValidator.validate(zip, bindingResult);
				if (bindingResult.hasErrors()){
					return map;
				}

				zip.setLastUpdusrId(loginVO.getUniqId());
				zipManageService.updateZip(zip);
			} else {

				zip.setLastUpdusrId(loginVO.getUniqId());
				rdnmadZipService.updateZip(zip);
			}
    	}
    	return map;
    }
}