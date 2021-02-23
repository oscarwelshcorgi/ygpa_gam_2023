/**
 *
 */
package egovframework.rte.ygpa.gam.popup.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.popup.service.GamPopupAtchDirFileService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupAtchDirFileVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 3. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 24.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamPopupAtchDirFileController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamPopupAtchDirFileService")
	private GamPopupAtchDirFileService gamPopupAtchDirFileService;

	@RequestMapping(value="/popup/showAtchDirFile.do")
	String showAtchDirFile(GamPopupAtchDirFileVO searchOpt, ModelMap model) throws Exception {

		model.addAttribute("searchOpt", searchOpt);

		return "/ygpa/gam/popup/GamPopupAtchDirFile";

    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/popup/selectAtchDirFileList.do", method=RequestMethod.POST)
	@ResponseBody Map selectAtchDirFileList(GamPopupAtchDirFileVO searchVO) throws Exception {

		Map map = new HashMap();
		List resultList;
		try{
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		if (searchVO != null && "F".equals(searchVO.getsSearchSe())) {
			resultList = gamPopupAtchDirFileService.selectAtchFileList(searchVO);
		} else {
			resultList = gamPopupAtchDirFileService.selectAtchDirList(searchVO);
		}

		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);
		}catch(Exception e){
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail"));
		}
		return map;
	}

}
