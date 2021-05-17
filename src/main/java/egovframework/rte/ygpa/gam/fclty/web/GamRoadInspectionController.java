/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.web;

import java.io.File;
import java.io.IOException;
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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
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
import egovframework.rte.ygpa.gam.cmmn.service.GamFileServiceVo;
import egovframework.rte.ygpa.gam.cmmn.service.GamFileUploadUtil;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadInspectionPrintVO;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadInspectionService;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadInspectionVO;

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
public class GamRoadInspectionController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamRoadInspectionService")
	private GamRoadInspectionService gamRoadInspectionService;

    @Resource(name="gamRoadFileIdGnrService")
    EgovTableIdGnrService gamRoadFileIdGnrService;

	@Resource(name = "multipartResolver")
	CommonsMultipartResolver multipartResolver;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;

    protected static final Log LOG = LogFactory.getLog(GamRoadInspectionController.class);

	@RequestMapping(value="/fclty/gamRoadInspection.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//List fcltsClCdList = gamCvlEngFcltySpecMngService.selectFcltsClCdList();

		//model.addAttribute("fcltsClCdList", fcltsClCdList);
		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/fclty/GamRoadInspection";

	}


	@RequestMapping(value="/fclty/gamRoadInspectionList.do")
	@ResponseBody Map<String, Object> gamRoadInspectionList(GamRoadInspectionVO searchVO) throws Exception {

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
		List mntnRprObjFcltsFList = gamRoadInspectionService.selectRoadInspectionList(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", mntnRprObjFcltsFList);

    	return map;

	}

	/**
	 * 임항도로 시설 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamRoadInspectionDetailList.do")
	@ResponseBody Map<String, Object> gamRoadInspectionDetailList(GamRoadInspectionVO searchVO) throws Exception {

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
		List roadInspectionList = gamRoadInspectionService.selectRoadInspectionDetailList(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", roadInspectionList);

    	return map;

	}

	@RequestMapping(value="/fclty/gamRoadInspectionFileList.do")
	@ResponseBody Map<String, Object> gamRoadInspectionFileList(GamRoadInspectionVO searchVO) throws Exception {

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


	@RequestMapping(value="/fclty/gamInsertRoadInspection.do")
	@ResponseBody Map<String, Object> gamInsertRoadInspection(final HttpServletRequest request, @RequestParam Map param, Model model) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Map insertRoadInspection = new HashMap();
    	List<HashMap<String,String>> insertObjList=null;

    	List<HashMap<String,String>> roadInspectionListOne=null;
    	List<HashMap<String,String>> roadInspectionListTwo=null;
    	List<HashMap<String,String>> roadInspectionListThree=null;

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		insertRoadInspection = mapper.readValue((String)param.get("detailForm"), new TypeReference<HashMap<String,String>>(){});

		roadInspectionListOne = mapper.readValue((String)param.get("roadInspectionListOne"), new TypeReference<List<HashMap<String,String>>>(){});
		roadInspectionListTwo = mapper.readValue((String)param.get("roadInspectionListTwo"), new TypeReference<List<HashMap<String,String>>>(){});
		roadInspectionListThree = mapper.readValue((String)param.get("roadInspectionListThree"), new TypeReference<List<HashMap<String,String>>>(){});

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		// 파일 처리
		Assert.state(request instanceof MultipartHttpServletRequest,
				"request !instanceof MultipartHttpServletRequest");
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;


		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		if(files!=null) {
			String photoOne = insertUpdateFile(files, "photoOneFile", insertRoadInspection.get("photoOne").toString(), null);
			String photoTwo = insertUpdateFile(files, "photoTwoFile", insertRoadInspection.get("photoTwo").toString(), null);
			String photoThree = insertUpdateFile(files, "photoThreeFile", insertRoadInspection.get("photoThree").toString(), null);

			insertRoadInspection.put("photoOne",photoOne);
			insertRoadInspection.put("photoTwo",photoTwo);
			insertRoadInspection.put("photoThree",photoThree);

			String chckTableOne = insertUpdateFile(files, "chckTableOneFile", insertRoadInspection.get("chckTableOne").toString() , null);
			String chckTableTwo = insertUpdateFile(files, "chckTableTwoFile", insertRoadInspection.get("chckTableTwo").toString() , null);
			String chckTableThree = insertUpdateFile(files, "chckTableThreeFile", insertRoadInspection.get("chckTableThree").toString() , null);

			insertRoadInspection.put("chckTableOne",chckTableOne);
			insertRoadInspection.put("chckTableTwo",chckTableTwo);
			insertRoadInspection.put("chckTableThree",chckTableThree);
		}

		try {
			insertRoadInspection.put("register", user.getId());
			gamRoadInspectionService.gamInsertRoadInspection (insertRoadInspection, roadInspectionListOne, roadInspectionListTwo, roadInspectionListThree);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}
		return map;
	}
/*
	@ResponseBody Map<String, Object> gamInsertRoadInspection(final HttpServletRequest request, GamRoadInspectionVO inputVO, BindingResult bindingResult, Model model) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		beanValidator.validate(inputVO, bindingResult);

		if (bindingResult.hasErrors()){
			Object[] error = bindingResult.getAllErrors().get(0).getArguments();
			String msg = error[0].toString()+"가 부정확합니다.";


    		map.put("resultCode", 1);
    		map.put("resultMsg", msg);
    		return map;
		}

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		// 파일 처리
		Assert.state(request instanceof MultipartHttpServletRequest,
				"request !instanceof MultipartHttpServletRequest");
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		if(files!=null) {
			String photoOne = insertUpdateFile(files, "photoOneFile", inputVO.getPhotoOne(), inputVO.getDelPhotoOne());
			String photoTwo = insertUpdateFile(files, "photoTwoFile", inputVO.getPhotoTwo(), inputVO.getDelPhotoTwo());
			String photoThree = insertUpdateFile(files, "photoThreeFile", inputVO.getPhotoThree(), inputVO.getDelPhotoThree());

			inputVO.setPhotoOne(photoOne);
			inputVO.setPhotoTwo(photoTwo);
			inputVO.setPhotoThree(photoThree);

			String chckTableOne = insertUpdateFile(files, "chckTableOneFile", inputVO.getChckTableOne(), inputVO.getDelChckTableOne());
			String chckTableTwo = insertUpdateFile(files, "chckTableTwoFile", inputVO.getChckTableTwo(), inputVO.getDelChckTableTwo());
			String chckTableThree = insertUpdateFile(files, "chckTableThreeFile", inputVO.getChckTableThree(), inputVO.getDelChckTableThree());

			inputVO.setChckTableOne(chckTableOne);
			inputVO.setChckTableTwo(chckTableTwo);
			inputVO.setChckTableThree(chckTableThree);
		}

		try {
			inputVO.setRegister(user.getId());
			gamRoadInspectionService.gamInsertRoadInspection (inputVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}
		return map;
	}
*/
	@RequestMapping(value="/fclty/gamUpdateRoadInspection.do")
	@ResponseBody Map<String, Object> gamUpdateRoadInspection(final HttpServletRequest request, @RequestParam Map param, Model model) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Map insertRoadInspection = new HashMap();
    	List<HashMap<String,String>> insertObjList=null;

    	List<HashMap<String,String>> roadInspectionListOne=null;
    	List<HashMap<String,String>> roadInspectionListTwo=null;
    	List<HashMap<String,String>> roadInspectionListThree=null;


		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		insertRoadInspection = mapper.readValue((String)param.get("detailForm"), new TypeReference<HashMap<String,String>>(){});

		roadInspectionListOne = mapper.readValue((String)param.get("roadInspectionListOne"), new TypeReference<List<HashMap<String,String>>>(){});
		roadInspectionListTwo = mapper.readValue((String)param.get("roadInspectionListTwo"), new TypeReference<List<HashMap<String,String>>>(){});
		roadInspectionListThree = mapper.readValue((String)param.get("roadInspectionListThree"), new TypeReference<List<HashMap<String,String>>>(){});

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
/*
		beanValidator.validate(inputVO, bindingResult);
*/
		// 파일 처리
		Assert.state(request instanceof MultipartHttpServletRequest,
				"request !instanceof MultipartHttpServletRequest");
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		/*
		 * extract files
		 */
		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		if(files!=null) {
			String delPhotoOneStr = (String)insertRoadInspection.get("delPhotoOne");
			String[] delPhotoOne = null;
			if(delPhotoOneStr!=null) {
				Integer[] delPhotoInt = mapper.readValue(delPhotoOneStr,
						new TypeReference<Integer[]>(){});
				delPhotoOne = new String[delPhotoInt.length];
					for(int i=0; i<delPhotoInt.length; i++) {
						delPhotoOne[i]=delPhotoInt[i].toString();
					}
			}
			String delPhotoTwoStr = (String)insertRoadInspection.get("delPhotoTwo");
			String[] delPhotoTwo = null;
			if(delPhotoTwoStr!=null) {
				Integer[] delPhotoInt = mapper.readValue(delPhotoTwoStr,
						new TypeReference<Integer[]>(){});
				delPhotoTwo = new String[delPhotoInt.length];
				for(int i=0; i<delPhotoInt.length; i++) {
					delPhotoTwo[i]=delPhotoInt[i].toString();
				}
			}
			String delPhotoThreeStr = (String)insertRoadInspection.get("delPhotoThree");
			String[] delPhotoThree = null;
			if(delPhotoThreeStr!=null) {
				Integer[] delPhotoInt = mapper.readValue(delPhotoThreeStr,
						new TypeReference<Integer[]>(){});
				delPhotoThree = new String[delPhotoInt.length];
				for(int i=0; i<delPhotoInt.length; i++) {
					delPhotoThree[i]=delPhotoInt[i].toString();
				}
			}

			String photoOne = insertUpdateFile(files, "photoOneFile", insertRoadInspection.get("photoOne").toString(), delPhotoOne);
			String photoTwo = insertUpdateFile(files, "photoTwoFile", insertRoadInspection.get("photoTwo").toString(), delPhotoTwo);
			String photoThree = insertUpdateFile(files, "photoThreeFile", insertRoadInspection.get("photoThree").toString(), delPhotoThree);

			insertRoadInspection.put("photoOne",photoOne);
			insertRoadInspection.put("photoTwo",photoTwo);
			insertRoadInspection.put("photoThree",photoThree);


			String delChckTableOneStr = (String)insertRoadInspection.get("delChckTableOne");
			String[] delChckTableOne = null;
			if(delChckTableOneStr!=null) {
				Integer[] delPhotoInt = mapper.readValue(delChckTableOneStr,
						new TypeReference<Integer[]>(){});
				delChckTableOne = new String[delPhotoInt.length];
				for(int i=0; i<delPhotoInt.length; i++) {
					delChckTableOne[i]=delPhotoInt[i].toString();
				}
			}
			String delChckTableTwoStr = (String)insertRoadInspection.get("delChckTableTwo");
			String[] delChckTableTwo = null;
			if(delChckTableTwoStr!=null) {
				Integer[] delPhotoInt = mapper.readValue(delChckTableTwoStr,
						new TypeReference<Integer[]>(){});
				delChckTableTwo = new String[delPhotoInt.length];
				for(int i=0; i<delPhotoInt.length; i++) {
					delChckTableTwo[i]=delPhotoInt[i].toString();
				}
			}
			String delChckTableThreeStr = (String)insertRoadInspection.get("delChckTableThree");
			String[] delChckTableThree = null;
			if(delChckTableThreeStr!=null) {
				Integer[] delPhotoInt = mapper.readValue(delChckTableThreeStr,
						new TypeReference<Integer[]>(){});
				delChckTableThree = new String[delPhotoInt.length];
				for(int i=0; i<delPhotoInt.length; i++) {
					delChckTableThree[i]=delPhotoInt[i].toString();
				}
			}

			String chckTableOne = insertUpdateFile(files, "chckTableOneFile", insertRoadInspection.get("chckTableOne").toString() , delChckTableOne);
			String chckTableTwo = insertUpdateFile(files, "chckTableTwoFile", insertRoadInspection.get("chckTableTwo").toString() , delChckTableTwo);
			String chckTableThree = insertUpdateFile(files, "chckTableThreeFile", insertRoadInspection.get("chckTableThree").toString() , delChckTableThree);

			insertRoadInspection.put("chckTableOne",chckTableOne);
			insertRoadInspection.put("chckTableTwo",chckTableTwo);
			insertRoadInspection.put("chckTableThree",chckTableThree);
		}

		try {
			insertRoadInspection.put("register", user.getId());
			gamRoadInspectionService.gamUpdateRoadInspection (insertRoadInspection, roadInspectionListOne, roadInspectionListTwo, roadInspectionListThree);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
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
					if(isNewFile && "".equals(fileId)) fileId=gamRoadFileIdGnrService.getNextStringId();
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

	@RequestMapping(value="/fclty/gamDeleteRoadInspection.do")
	@ResponseBody Map<String, Object> gamDeleteCvlEngFcltySpecMng(GamRoadInspectionVO deleteVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamRoadInspectionService.deleteRoadInspection (deleteVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}
		return map;
	}


	/**
	 * 임항도로 정기점검 출력
	 * @param searchOpt
	 * @return String
	 * @throws Exception
	 */

    @RequestMapping(value="/fclty/roadInspectionPrint.do")
    public String gamRoadInspectionPrint(GamRoadInspectionPrintVO printVo, ModelMap model) throws Exception {
    	Map map = new HashMap();

		ObjectMapper mapper = new ObjectMapper();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "ygpa/gam/code/GamAssetCodeListPrint";
    	}
//    	roadMng = mapper.convertValue(searchOpt, GamRoadMngGroupVO.class);
//    	searchVO.setsFcltsMngGroupNo(roadMng.getFcltsMngGroupNo());

    	String fcltsGbn = printVo.getFcltsGbn();

    	if(fcltsGbn=="1") {
    		printVo.setFcltsGbnNm("1종");
    	}else if(fcltsGbn=="2") {
    		printVo.setFcltsGbnNm("2종");
    	}else if(fcltsGbn=="3") {
    		printVo.setFcltsGbnNm("1종/2종");
    	}else if(fcltsGbn=="9") {
    		printVo.setFcltsGbnNm("기타");
    	}else {
    		printVo.setFcltsGbnNm("기타");
    	}

    	GamRoadInspectionVO searchVO = new GamRoadInspectionVO();

    	searchVO.setsFcltsMngGroupNo(printVo.getFcltsMngGroupNo());
    	searchVO.setsYear(printVo.getYear());
    	searchVO.setsSn(printVo.getSn());
    	searchVO.setSearchUseYn("Y");
    	searchVO.setsSe("1");
    	List roadInspectionList1 = gamRoadInspectionService.selectRoadInspectionDetailList(searchVO);

    	searchVO.setsSe("2");
    	List roadInspectionList2 = gamRoadInspectionService.selectRoadInspectionDetailList(searchVO);

    	searchVO.setsSe("3");
    	List roadInspectionList3 = gamRoadInspectionService.selectRoadInspectionDetailList(searchVO);

    	model.addAttribute("resultCode", 0);	// return ok
    	model.addAttribute("printVo", printVo);
    	model.addAttribute("roadInspectionList1", roadInspectionList1);
    	model.addAttribute("roadInspectionList2", roadInspectionList2);
    	model.addAttribute("roadInspectionList3", roadInspectionList3);

    	return "ygpa/gam/fclty/GamRoadInspectionPrint";
    }


    // 파일 처리 (자산 임대 공통 - 리퀘스트 패스만 변경 하여 사용)
    @RequestMapping(value="/fclty/uploadRoadAttachFile.do", method=RequestMethod.POST)
    public @ResponseBody Map uploadFile(HttpServletRequest request) throws Exception {
		Map map = new HashMap();
		String uploadPath = EgovProperties.getProperty("assetsRent.fileStorePath");
		try {
			List<GamFileServiceVo> list = GamFileUploadUtil.uploadFiles(request, uploadPath, gamRoadFileIdGnrService);

			map.put("resultCode", "0");
			map.put("result", list);
		} catch(IOException e){
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}
		catch(Exception e) {
			map.put("resultCode", "-1");
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.upload"));
		}
		return map;
	}



}

