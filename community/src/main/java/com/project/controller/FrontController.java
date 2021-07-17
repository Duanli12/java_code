package com.project.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.common.config.FileUploadProperties;
import com.project.common.exception.BusinessException;
import com.project.common.utils.CoputerUtils;
import com.project.common.utils.DataResult;
import com.project.common.utils.DateUtils;
import com.project.entity.Clazz;
import com.project.entity.Suggest;
import com.project.entity.FieldEntity;
import com.project.entity.Notice;
import com.project.entity.Record;
import com.project.entity.Sign;
import com.project.entity.User;
import com.project.mapper.TClazzMapper;
import com.project.mapper.TCourseMapper;
import com.project.mapper.TFieldMapper;
import com.project.mapper.TNoticeMapper;
import com.project.mapper.TRecordMapper;
import com.project.mapper.TSignMapper;
import com.project.mapper.TUserMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 视图
 *
 * @author
 * @version V1.0
 * @date
 */
@Api(tags = "视图")
@Controller
public class FrontController {
	@Resource
	private FileUploadProperties fileUploadProperties;

	@Autowired
	TUserMapper userMapper;
	@Autowired
	TFieldMapper tfieldMapper;
	@Autowired
	TClazzMapper clazzMapper;
	@Autowired
	TSignMapper signMapper;
	@Autowired
	TNoticeMapper tNoticeMapper;
	@Autowired
	TCourseMapper courseMapper;
	@Autowired
	TRecordMapper recordMapper;

	@GetMapping("/frontIndex")
	public String index(HttpServletRequest request) {
		setIndexList(request);
		return "index";
	}

	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}

	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

	@GetMapping("/project")
	public String project() {
		return "project";
	}

	@GetMapping("/profile")
	public String userinfo() {
		return "profile";
	}

	@GetMapping("/xuqiu")
	public String xuqiu() {
		return "xuqiu";
	}

	@GetMapping("/tongzhi")
	public String tongzhi(HttpServletRequest request) {
		List<Notice> selectList2 = tNoticeMapper.selectList(null);
		request.setAttribute("ggList", selectList2);
		return "tongzhi";
	}

	@GetMapping("/jianyi")
	public String jianyi() {
		return "jianyi";
	}

	@GetMapping("/aboutUs")
	public String aboutUs() {
		return "aboutUs";
	}

	@GetMapping("/pingtai")
	public String pingtai() {
		return "pingtai";
	}

	@GetMapping("/xuqiuAccpet")
	public String xuqiuAccpet(HttpServletRequest request) {
		request.setAttribute("xuqiuid", request.getParameter("id"));
		FieldEntity selectById = tfieldMapper.selectById(request.getParameter("id"));
		request.setAttribute("xuqiu", selectById);
		return "xuqiuAccpet";
	}

	@ResponseBody
	@PostMapping("/reg")
	public DataResult add(@RequestBody User sysContent) {
		QueryWrapper<User> query1 = new QueryWrapper<>();
		QueryWrapper<User> query2 = new QueryWrapper<>();
		query1.eq("phone", sysContent.getPhone());
		query2.eq("idcard",sysContent.getIdcard());
		User one = userMapper.selectOne(query1);
		User tow = userMapper.selectOne(query2);

		if (one != null) {
			return DataResult.fail("当前账号已被注册");
		}
		if (tow !=null){
			return DataResult.fail("当前身份证号已存在");
		}
		userMapper.insert(sysContent);
		return DataResult.success();
	}

	@ResponseBody
	@PostMapping("/login")
	public DataResult login(@RequestBody User sysContent, HttpSession session) {
		QueryWrapper<User> query1 = new QueryWrapper<>();
		query1.eq("phone", sysContent.getPhone());
		query1.eq("password", sysContent.getPassword());
		User one = userMapper.selectOne(query1);
		if (one == null) {
			return DataResult.fail("账号或密码错误");
		}
		session.setAttribute("userInfo", one);
		return DataResult.success();
	}

	@GetMapping("/loginout")
	public String loginout(HttpServletRequest request, HttpSession session) {
		session.removeAttribute("userInfo");
		setIndexList(request);
		return "index";
	}

	@ResponseBody
	@PostMapping("/editUser")
	public DataResult editUser(@RequestBody User sysContent, HttpSession session) {
		User attribute = (User) session.getAttribute("userInfo");
		if (attribute == null) {
			return DataResult.fail("请先登录");
		}
		attribute.setAge(sysContent.getAge());
		attribute.setPassword(sysContent.getPassword());
		attribute.setUsername(sysContent.getUsername());
		attribute.setSex(sysContent.getSex());
		/*attribute.setDianhua(sysContent.getDianhua());*/
		userMapper.updateById(attribute);
		session.setAttribute("userInfo", attribute);
		return DataResult.success();
	}

	@ResponseBody
	@PostMapping("/pubXuqiu")
	public DataResult pubXuqiu(@RequestBody FieldEntity sysContent, HttpSession session) {
		User attribute = (User) session.getAttribute("userInfo");
		if (attribute == null) {
			return DataResult.fail("请先登录");
		}

		Clazz selectById = clazzMapper.selectById(sysContent.getFenlei());

		double numberTwoPontis = CoputerUtils.getNumberTwoPontis(selectById.getName() * sysContent.getTimenum());
		Double money = attribute.getMoney();
		if((money - numberTwoPontis)<0) {
			return DataResult.fail("当前任务所需金币："+numberTwoPontis+",账户余额不足，无法发布！");
		}
		
		sysContent.setId(UUID.randomUUID().toString());
		sysContent.setCreatetime(new Date());
		sysContent.setUserid(attribute.getId());
		sysContent.setStatus(1);
		sysContent.setMoney(numberTwoPontis);

		
		tfieldMapper.insert(sysContent);

		return DataResult.success();
	}

	@ResponseBody
	@PostMapping("/cz")
	public DataResult cz(@RequestBody FieldEntity sysContent, HttpSession session) {
		User attribute = (User) session.getAttribute("userInfo");
		if (attribute == null) {
			return DataResult.fail("请先登录");
		}
		sysContent.getMoney();
		attribute.setMoney(CoputerUtils.getNumberTwoPontis(attribute.getMoney() + sysContent.getMoney()));
		userMapper.updateById(attribute);
		session.setAttribute("userInfo", attribute);

		return DataResult.success();
	}

	private void setIndexList(HttpServletRequest request) {
		QueryWrapper<FieldEntity> query1 = new QueryWrapper<>();
		query1.orderByDesc("createtime");

		String queryParam = request.getParameter("queryParam");
		if (StringUtils.isNotBlank(queryParam)) {
			query1.eq("fenlei", queryParam);
			request.setAttribute("queryParam", queryParam);
		}

		List<FieldEntity> selectList = tfieldMapper.selectList(query1);
		if (selectList != null && !selectList.isEmpty()) {
			for (FieldEntity tFieldEntity : selectList) {

				User user = userMapper.selectById(tFieldEntity.getUserid());
				if (user != null) {
					tFieldEntity.setFabutaker(user.getUsername());
				}

				Sign selectById = signMapper.getOneByTwo(tFieldEntity.getId());
				if (selectById != null) {
					tFieldEntity.setUndertaker(userMapper.selectById(selectById.getUserid()).getUsername());
				}
			}
		}
		request.setAttribute("list", selectList);

		List<Notice> selectList2 = tNoticeMapper.selectList(null);
		request.setAttribute("ggList", selectList2);

		HttpSession session = request.getSession();
		User attribute = (User) session.getAttribute("userInfo");
		if (attribute != null) {
			QueryWrapper<User> userQuesy = new QueryWrapper<>();
			userQuesy.ne("id", attribute.getId());
			List<User> userList = userMapper.selectList(userQuesy);
			request.setAttribute("userList", userList);
		}

		Integer userCount = userMapper.selectCount(null);
		Integer xqCount = tfieldMapper.selectCount(null);
		request.setAttribute("userCount", userCount);
		request.setAttribute("xqCount", xqCount);
		request.setAttribute("fenleiList", clazzMapper.selectList(null));

		QueryWrapper<FieldEntity> qq = new QueryWrapper<>();
		qq.eq("status", 4);
		Integer xqCountOK = tfieldMapper.selectCount(qq);
		request.setAttribute("xqCountOK", xqCountOK);
	}

	@ResponseBody
	@PostMapping("/shenqingXuqiu")
	public DataResult shenqingXuqiu(@RequestBody Sign sysContent, HttpSession session) {
		User attribute = (User) session.getAttribute("userInfo");
		if (attribute == null) {
			return DataResult.fail("请先登录");
		}

		QueryWrapper<Sign> query1 = new QueryWrapper<>();
		query1.eq("courseid", sysContent.getCourseid());
		query1.eq("userid", attribute.getId());
		Sign selectOne = signMapper.selectOne(query1);
		if (selectOne != null) {
			return DataResult.fail("当前需求已经申请过，无需再次申请");
		}

		FieldEntity selectById = tfieldMapper.selectById(sysContent.getCourseid());
		if (attribute.getId().equals(selectById.getUserid())) {
			return DataResult.fail("不能申请自己发布的需求！");
		}
		
		if(selectById.getStatus() == 4) {
			return DataResult.fail("当前需求已完成，无法申请！");
		}

		sysContent.setSex(attribute.getSex());
		sysContent.setAge(attribute.getAge());
		sysContent.setUsername(attribute.getUsername());
		sysContent.setId(UUID.randomUUID().toString());
		sysContent.setCreatetime(new Date());
		sysContent.setUserid(attribute.getId());
		sysContent.setPhone(attribute.getPhone());
		sysContent.setNum(attribute.getIdcard());
		sysContent.setStatus(1);

		signMapper.insert(sysContent);

		return DataResult.success();
	}

	@GetMapping("/gonggao")
	public String gonggao(HttpServletRequest request) {
		request.setAttribute("xuqiuid", request.getParameter("id"));
		Notice selectById = tNoticeMapper.selectById(request.getParameter("id"));
		request.setAttribute("xuqiu", selectById);
		return "gonggao";
	}

	@ResponseBody
	@PostMapping("/addJianyi")
	public DataResult addJianyi(@RequestBody Suggest sysContent, HttpSession session) {
		User attribute = (User) session.getAttribute("userInfo");
		if (attribute == null) {
			return DataResult.fail("请先登录");
		}

		sysContent.setId(UUID.randomUUID().toString());
		sysContent.setCreatetime(new Date());
		sysContent.setUserid(attribute.getId());
		courseMapper.insert(sysContent);

		return DataResult.success();
	}

	@GetMapping("/myXuqiu")
	public String myXuqiu(HttpServletRequest request, HttpSession session) {
		User attribute = (User) session.getAttribute("userInfo");

		QueryWrapper<FieldEntity> query1 = new QueryWrapper<>();
		query1.eq("userid", attribute.getId());
		List<FieldEntity> selectList = tfieldMapper.selectList(query1);
		if(selectList!=null && !selectList.isEmpty()) {
			for (FieldEntity tFieldEntity : selectList) {
				QueryWrapper<Sign> query12 = new QueryWrapper<>();
				query12.eq("courseid", tFieldEntity.getId());
				query12.eq("status", 4);
				Sign selectById = signMapper.selectOne(query12);
				if(selectById!=null) {
					tFieldEntity.setFabuliuyan(selectById.getFabuliuyan());
					tFieldEntity.setJieshouliuyan(selectById.getJieshouliuyan());
					tFieldEntity.setSignid(selectById.getId());
				}
				
			}
		}
		

		request.setAttribute("list", selectList);

		return "myXuqiu";

	}

	@GetMapping("/myShenqing")
	public String myShenqing(HttpServletRequest request, HttpSession session) {

		User attribute = (User) session.getAttribute("userInfo");

		QueryWrapper<Sign> query1 = new QueryWrapper<>();
		query1.eq("userid", attribute.getId());
		List<Sign> records = signMapper.selectList(query1);
		if (records != null && !records.isEmpty()) {
			for (Sign tSign : records) {
				FieldEntity selectById = tfieldMapper.selectById(tSign.getCourseid());
				tSign.setMoney(selectById.getMoney());
				tSign.setXuqiuName(selectById.getTitle());
				tSign.setXuqiuContent(selectById.getContent());
				User u1 = userMapper.selectById(selectById.getUserid());
				tSign.setFabuUserName(u1.getUsername());

				User u2 = userMapper.selectById(tSign.getUserid());
				tSign.setJieshouUserName(u2.getUsername());
			}
		}
		request.setAttribute("list", records);

		return "myShenqing";

	}

	@ResponseBody
	@PostMapping("/wancheng")
	public DataResult wancheng(@RequestBody Sign sysContent, HttpSession session) {
		// 加钱的用户
		User attribute = (User) session.getAttribute("userInfo");
		if (attribute == null) {
			return DataResult.fail("请先登录");
		}

		// 需求更新为4
		String id = sysContent.getId();
		Sign selectById = signMapper.selectById(id);
		selectById.setStatus(4);
		signMapper.updateById(selectById);

		// 扣钱得用户
		FieldEntity selectById2 = tfieldMapper.selectById(selectById.getCourseid());
		selectById2.setStatus(4);
		tfieldMapper.updateById(selectById2);

		double money = selectById2.getMoney();
		String userid = selectById2.getUserid();
		User kqUser = userMapper.selectById(userid);

		Record tr = new Record();
		tr.setId(UUID.randomUUID().toString());
		tr.setCreatetime(new Date());
		tr.setUserid(userid);
		tr.setMoney(money);
		tr.setFuhao("-");
		tr.setType(1);
		tr.setUsername(kqUser.getUsername());
		tr.setReuserid(attribute.getId());
		tr.setReusername(attribute.getUsername());
		recordMapper.insert(tr);

		kqUser.setMoney(kqUser.getMoney() - money);
		userMapper.updateById(kqUser);

		// 得钱
		attribute.setMoney(attribute.getMoney() + money);
		userMapper.updateById(attribute);
		session.setAttribute("userInfo", attribute);

		Record tr2 = new Record();
		tr2.setId(UUID.randomUUID().toString());
		tr2.setCreatetime(new Date());
		tr2.setUserid(attribute.getId());
		tr2.setMoney(money);
		tr2.setFuhao("+");
		tr2.setType(1);
		tr2.setUsername(attribute.getUsername());
		tr2.setReuserid(kqUser.getId());
		tr2.setReusername(kqUser.getUsername());
		recordMapper.insert(tr2);

		return DataResult.success();
	}

	@ResponseBody
	@PostMapping("/subZs")
	public DataResult subZs(@RequestBody FieldEntity sysContent, HttpSession session) {

		User attribute = (User) session.getAttribute("userInfo");
		if (attribute == null) {
			return DataResult.fail("请先登录");
		}

		double numberTwoPontis = CoputerUtils.getNumberTwoPontis(attribute.getMoney() - sysContent.getMoney());
		if(numberTwoPontis<0) {
			return DataResult.fail("余额不足，无法赠送");
		}
		attribute.setMoney(numberTwoPontis);
		userMapper.updateById(attribute);
		session.setAttribute("userInfo", attribute);

		User selectById = userMapper.selectById(sysContent.getId());

		Record tr = new Record();
		tr.setId(UUID.randomUUID().toString());
		tr.setCreatetime(new Date());
		tr.setUserid(attribute.getId());
		tr.setMoney(sysContent.getMoney());
		tr.setFuhao("-");
		tr.setType(2);
		tr.setUsername(attribute.getUsername());
		tr.setReuserid(selectById.getId());
		tr.setReusername(selectById.getUsername());
		recordMapper.insert(tr);

		Record tr2 = new Record();
		tr2.setId(UUID.randomUUID().toString());
		tr2.setCreatetime(new Date());
		tr2.setUserid(selectById.getId());
		tr2.setMoney(sysContent.getMoney());
		tr2.setFuhao("+");
		tr2.setType(2);
		tr2.setUsername(selectById.getUsername());
		tr2.setReuserid(attribute.getId());
		tr2.setReusername(attribute.getUsername());
		recordMapper.insert(tr2);

		selectById.setMoney(selectById.getMoney() + sysContent.getMoney());
		userMapper.updateById(selectById);
		return DataResult.success();
	}

	@GetMapping("/myJilu")
	public String myJilu(HttpServletRequest request, HttpSession session) {

		User attribute = (User) request.getSession().getAttribute("userInfo");
		QueryWrapper<Record> query1 = new QueryWrapper<>();
		query1.orderByAsc("createtime");
		query1.eq("userid", attribute.getId());
		List<Record> selectList = recordMapper.selectList(query1);
		request.setAttribute("list", selectList);
		return "myJilu";
	}

	@ApiOperation(value = "新增")
	@PostMapping("/file/upload")
	@ResponseBody
	public DataResult add(@RequestParam(value = "file") MultipartFile file, HttpSession session) {
		// 判断文件是否空
		if (file == null || file.getOriginalFilename() == null
				|| "".equalsIgnoreCase(file.getOriginalFilename().trim())) {
			return DataResult.fail("文件为空");
		}
		// 存储文件夹
		String createTime = DateUtils.format(new Date(), DateUtils.DATEPATTERN);
		String newPath = fileUploadProperties.getPath() + createTime + File.separator;
		File uploadDirectory = new File(newPath);
		if (uploadDirectory.exists()) {
			if (!uploadDirectory.isDirectory()) {
				uploadDirectory.delete();
			}
		} else {
			uploadDirectory.mkdir();
		}
		try {
			String fileName = file.getOriginalFilename();
			// id与filename保持一直，删除文件
			String fileNameNew = UUID.randomUUID().toString().replace("-", "") + getFileType(fileName);
			String newFilePathName = newPath + fileNameNew;
			String url = fileUploadProperties.getUrl() + "/" + createTime + "/" + fileNameNew;
			// 创建输出文件对象
			File outFile = new File(newFilePathName);
			// 拷贝文件到输出文件对象
			FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);
			Map<String, String> resultMap = new HashMap<>();
			resultMap.put("src", url);

			User attribute = (User) session.getAttribute("userInfo");
			attribute.setImg(url);
			session.setAttribute("userInfo", attribute);
			return DataResult.success(resultMap);
		} catch (Exception e) {
			throw new BusinessException("上传文件失败");
		}

	}

	private String getFileType(String fileName) {
		if (fileName != null && fileName.contains(".")) {
			return fileName.substring(fileName.lastIndexOf("."));
		}
		return "";
	}

	@ResponseBody
	@PostMapping("/fabuliuyan")
	public DataResult fabuliuyan(@RequestBody Sign sysContent, HttpSession session) {
		// 加钱的用户
		User attribute = (User) session.getAttribute("userInfo");
		if (attribute == null) {
			return DataResult.fail("请先登录");
		}

		String id = sysContent.getId();
		Sign selectById = signMapper.selectById(id);
		selectById.setFabuliuyan(sysContent.getFabuliuyan());
		signMapper.updateById(selectById);

		return DataResult.success();
	}
	
	@ResponseBody
	@PostMapping("/jieshouliuyan")
	public DataResult jieshouliuyan(@RequestBody Sign sysContent, HttpSession session) {
		// 加钱的用户
		User attribute = (User) session.getAttribute("userInfo");
		if (attribute == null) {
			return DataResult.fail("请先登录");
		}

		String id = sysContent.getId();
		Sign selectById = signMapper.selectById(id);
		selectById.setJieshouliuyan(sysContent.getJieshouliuyan());
		signMapper.updateById(selectById);

		return DataResult.success();
	}
}
