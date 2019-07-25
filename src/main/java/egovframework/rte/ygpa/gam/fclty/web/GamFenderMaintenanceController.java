/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceService;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderMngGroupVO;

/**
*
* @author LFIT
* @since 2019. 6. 19.
* @version 1.0
* @see
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일 		 수정자		 수정내용
*  -------		--------	---------------------------
*  2019.6.19.		LFIT		최초 생성
*
* </pre>
*/

@Controller
public class GamFenderMaintenanceController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamFenderMaintenanceService")
	private GamFenderMaintenanceService gamFenderMaintenanceService;

    @Resource(name="gamFenderFileIdGnrService")
    EgovTableIdGnrService gamFenderFileIdGnrService;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;

    protected static final Log LOG = LogFactory.getLog(GamFenderMaintenanceController.class);

	@RequestMapping(value="/fclty/gamFenderMaintenance.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//List fcltsClCdList = gamCvlEngFcltySpecMngService.selectFcltsClCdList();

		//model.addAttribute("fcltsClCdList", fcltsClCdList);
		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/fclty/GamFenderMaintenance";

	}


	/**
	 * 방충재 그룹 시설 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamFenderMaintenanceList.do")
	public @ResponseBody Map gamFenderMaintenanceList(GamFenderMaintenanceVO searchVO)throws Exception {

		Map map = new HashMap();

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
		List gamFenderMngGroupList = gamFenderMaintenanceService.selectFenderMaintenanceList(searchVO);

        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", gamFenderMngGroupList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	@RequestMapping(value="/fclty/gamFenderMaintenanceFileList.do")
	@ResponseBody Map<String, Object> gamFenderMaintenanceFileList(GamFenderInspectionVO searchVO) throws Exception {

		Map map = new HashMap();

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}
		// 내역 조회
		FileVO fileVO = new FileVO();
		List<FileVO> resultPhotoOne=null;
		List<FileVO> resultPhotoTwo=null;
		List<FileVO> resultPhotoThree=null;
		List<FileVO> resultChckTableOne=null;
		List<FileVO> resultChckTableTwo=null;
		List<FileVO> resultChckTableThree=null;
		if(!"".equals(searchVO.getPhotoOne())) {
			fileVO.setAtchFileId(searchVO.getPhotoOne());
			resultPhotoOne = fileService.selectFileInfs(fileVO);
		}
		if(!"".equals(searchVO.getPhotoTwo())) {
			fileVO.setAtchFileId(searchVO.getPhotoTwo());
			resultPhotoTwo = fileService.selectFileInfs(fileVO);
		}
		if(!"".equals(searchVO.getPhotoThree())) {
			fileVO.setAtchFileId(searchVO.getPhotoThree());
			resultPhotoThree = fileService.selectFileInfs(fileVO);
		}
		if(!"".equals(searchVO.getChckTableOne())) {
			fileVO.setAtchFileId(searchVO.getChckTableOne());
			resultChckTableOne = fileService.selectFileInfs(fileVO);
		}
		if(!"".equals(searchVO.getChckTableTwo())) {
			fileVO.setAtchFileId(searchVO.getChckTableTwo());
			resultChckTableTwo = fileService.selectFileInfs(fileVO);
		}
		if(!"".equals(searchVO.getChckTableThree())) {
			fileVO.setAtchFileId(searchVO.getChckTableThree());
			resultChckTableThree = fileService.selectFileInfs(fileVO);
		}

		map.put("resultCode", 0);			// return ok
		map.put("resultPhotoOne", resultPhotoOne);
		map.put("resultPhotoTwo", resultPhotoTwo);
		map.put("resultPhotoThree", resultPhotoThree);
		map.put("resultChckTableOne", resultChckTableOne);
		map.put("resultChckTableTwo", resultChckTableTwo);
		map.put("resultChckTableThree", resultChckTableThree);

		return map;

	}

	/**
	 * 방충재 시설 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamFenderMaintenanceDetailList.do")
	@ResponseBody Map<String, Object> gamFenderMaintenanceDetailList(GamFenderMaintenanceVO searchVO) throws Exception {

		Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회

		/** List Data */
		List FenderMaintenanceList = gamFenderMaintenanceService.selectFenderMaintenanceDetailList(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", FenderMaintenanceList);

    	return map;

	}


	@RequestMapping(value="/fclty/gamInsertFenderMaintenance.do")
	@ResponseBody Map<String, Object> gamInsertFenderMaintenance(final HttpServletRequest request, @RequestParam Map inputVO, Model model) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Map insertFenderMaintenance = new HashMap();
    	List<HashMap<String,String>> insertObjList=null;

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

//		beanValidator.validate(inputVO, bindingResult);

//		if (bindingResult.hasErrors()){
/*			model.addAttribute("inputVO", inputVO);
			return "/ygpa/gam/fclty/GamFenderMaintenance";
*/
//			Object[] error = bindingResult.getAllErrors().get(0).getArguments();
//			String msg = error[0].toString()+"가 부정확합니다.";

//    		map.put("resultCode", 1);
//   		map.put("resultMsg", msg);
//    		return map;
//		}

		insertFenderMaintenance = mapper.readValue((String)inputVO.get("detailForm"),
    		    new TypeReference<HashMap<String,String>>(){});

		insertObjList = mapper.readValue((String)inputVO.get("insertMntnObjList"),
				new TypeReference<List<HashMap<String,String>>>(){});

		// 파일 처리
		Assert.state(request instanceof MultipartHttpServletRequest,
				"request !instanceof MultipartHttpServletRequest");
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		/*
		 * extract files
		 */
		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		if(files!=null) {
			String[] delPhoto = mapper.readValue((String)insertFenderMaintenance.get("delPhoto"),
	    		    new TypeReference<String[]>(){});
			String photo = insertUpdateFile(files, "photo", (String)insertFenderMaintenance.get("photo"), delPhoto);
			insertFenderMaintenance.put("photo", photo);

			String[] delCompetInspctWtnnc = mapper.readValue((String)insertFenderMaintenance.get("delCompetInspctWtnnc"),
					new TypeReference<String[]>(){});
			String competInspctWtnnc = insertUpdateFile(files, "photo", (String)insertFenderMaintenance.get("competInspctWtnnc"), delPhoto);
			insertFenderMaintenance.put("competInspctWtnnc", competInspctWtnnc);
		}


		try {
			gamFenderMaintenanceService.gamInsertFenderMaintenance (insertFenderMaintenance, insertObjList);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}
		return map;
	}

	@RequestMapping(value="/fclty/gamUpdateFenderMaintenance.do")
	@ResponseBody Map<String, Object> gamUpdateFenderMaintenance(final HttpServletRequest request, @RequestParam Map inputVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Map insertFenderMaintenance = new HashMap();
    	List<HashMap<String,String>> insertObjList=null;

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		insertFenderMaintenance = mapper.readValue((String)inputVO.get("detailForm"),
    		    new TypeReference<HashMap<String,String>>(){});

		insertObjList = mapper.readValue((String)inputVO.get("insertMntnObjList"),
				new TypeReference<List<HashMap<String,String>>>(){});

		// 파일 처리
		Assert.state(request instanceof MultipartHttpServletRequest,
				"request !instanceof MultipartHttpServletRequest");
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		/*
		 * extract files
		 */
		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		if(files!=null) {
			String[] delPhoto = mapper.readValue((String)insertFenderMaintenance.get("delPhoto"),
	    		    new TypeReference<String[]>(){});
			String photo = insertUpdateFile(files, "photo", (String)insertFenderMaintenance.get("photo"), delPhoto);
			insertFenderMaintenance.put("photo", photo);

			String[] delCompetInspctWtnnc = mapper.readValue((String)insertFenderMaintenance.get("delCompetInspctWtnnc"),
					new TypeReference<String[]>(){});
			String competInspctWtnnc = insertUpdateFile(files, "photo", (String)insertFenderMaintenance.get("competInspctWtnnc"), delPhoto);
			insertFenderMaintenance.put("competInspctWtnnc", competInspctWtnnc);
		}

		try {
			gamFenderMaintenanceService.gamUpdateFenderMaintenance (insertFenderMaintenance, insertObjList);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}
		return map;
	}


	private String insertUpdateFile(final Map<String, MultipartFile> files, String fileKeyPrefix, String fileId, String[] delFileSn) throws Exception {
		/*
		 * process files
		 */
		String uploadPath = EgovProperties.getProperty("prtfclty.fileStorePath");
		File saveFolder = new File(uploadPath);

		// 디렉토리 생성
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet()
				.iterator();
		MultipartFile file;

		List<FileVO> fileList = null;
		List<FileVO> fileUpList = new ArrayList<FileVO>();

		String filePath;

		FileVO fileVo = null;

		boolean isNewFile=true;

		int maxFileSn=0;

		if(!"".equals(fileId)) {
			FileVO fvo = new FileVO();
			fvo.setAtchFileId(fileId);
			isNewFile=false;

			if(delFileSn!=null && delFileSn.length>0) {
				for(int i=0; i<delFileSn.length; i++) {
					fvo.setFileSn(delFileSn[i]);
					fileService.deleteFileInf(fvo);
				}
			}
			fileList = fileService.selectFileInfs(fvo);

			for(int i=0; i<fileList.size(); i++) {	// get Max sn
				FileVO vo = fileList.get(i);
				String sn_str = vo.getFileSn();
				try {
					int sn = Integer.parseInt(sn_str);
					if(sn>=maxFileSn) {
						maxFileSn=sn+1;
					}
				}
				catch(NumberFormatException e) {
					LOG.error("File SN invalid type.");
				}
			}
		}
		else {
			fileList = new ArrayList<FileVO>();
		}

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			System.out.println("[" + entry.getKey() + "]");

			file = entry.getValue();
			String[] tokens = file.getOriginalFilename().split("\\.(?=[^\\.]+$)");
			if (!"".equals(file.getOriginalFilename())) {
				if(entry.getKey().startsWith(fileKeyPrefix)) {
					fileVo = new FileVO();
					String fileSn="";
					if(isNewFile) fileId=gamFenderFileIdGnrService.getNextStringId();
					fileVo.setAtchFileId(fileId);
					fileSn = StringUtils.leftPad(Integer.toString(maxFileSn++), 2, "0");
					fileVo.setFileSn(fileSn);
					fileVo.setStreFileNm(fileId+fileSn);
					fileVo.setOrignlFileNm(file.getOriginalFilename());
					fileVo.setFileExtsn(tokens[1]);
					fileUpList.add(fileVo);
					filePath = uploadPath + fileVo.getStreFileNm();
					fileVo.setFileStreCours(uploadPath);
					file.transferTo(new File(filePath));
				}
			}
		}

		if(fileUpList.size()>0) {
			if(isNewFile) {
				fileService.insertFileInfs(fileUpList);
			}
			else {
				fileService.updateFileInfs(fileUpList);
			}
		}
		return fileId;
	}

	@RequestMapping(value="/fclty/gamDeleteFenderMaintenance.do")
	@ResponseBody Map<String, Object> gamDeleteFenderMaintenance(GamFenderMaintenanceVO deleteVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFenderMaintenanceService.gamDeleteFenderMaintenance (deleteVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}
		return map;
	}


	/**
	 * 방충재 유지보수 출력
	 * @param searchOpt
	 * @return String
	 * @throws Exception
	 */

    @RequestMapping(value="/fclty/gamFenderMaintenancePrint.do")
    public String gamFenderMaintenancePrint(GamFenderMaintenanceVO printVo, ModelMap model) throws Exception {
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "ygpa/gam/code/GamAssetCodeListPrint";
    	}


    	List fenderMaintenanceDetailList = gamFenderMaintenanceService.selectFenderMaintenanceDetailPrint(printVo);

//    	fenderMng = mapper.convertValue(searchOpt, GamFenderMngGroupVO.class);
//    	searchVO.setsFcltsMngGroupNo(fenderMng.getFcltsMngGroupNo());

//		List FenderMaintenanceList = gamFenderMaintenanceService.selectFenderMaintenanceList(searchVO);

    	model.addAttribute("resultCode", 0);	// return ok
    	model.addAttribute("printVo", printVo);
    	model.addAttribute("fenderMaintenanceDetailList", fenderMaintenanceDetailList);

    	return "ygpa/gam/fclty/GamFenderMaintenancePrint";
    }

}

