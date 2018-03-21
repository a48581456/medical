package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import listener.ServletContextListener;
import mybatis.Pagination.Pagination;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import common.Constants;
import parameter.AdvBean;
import parameter.CaseBean;
import parameter.CurriculumBean;
import parameter.ExpertBean;
import parameter.MessageBean;
import bean.CodeBean;
import dao.CommonDao;

public class Util {

	public static final String YYYYMMDD = "yyyy/MM/dd";

	private static CommonDao commonDao;

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		Util.commonDao = commonDao;
	}

	/**
	 * 获取科室下拉菜单
	 * 
	 * */
	public static List<CodeBean> getDepartmentList() {

		return commonDao.getDepartmentList();
	}
	
	/**
	 * 获取科室下拉菜单
	 * 
	 * */
	public static List<CodeBean> getDepartmentPageList(Pagination pagination) {

		return commonDao.getDepartmentPageList(pagination);
	}

	/**
	 * 获取状态下拉菜单
	 * 
	 * */
	public static List<CodeBean> getStatusList() {

		return commonDao.getStatusList();
	}

	/**
	 * 获取病例状态下拉菜单
	 * 
	 * */
	public static List<CodeBean> getCaseStatusList() {

		return commonDao.getCaseStatusList();
	}

	/**
	 * 获取权限下拉菜单
	 * 
	 * */
	public static List<CodeBean> getPermissList() {

		return commonDao.getPermissList();
	}

	/**
	 * 获取上传类型下拉菜单
	 * 
	 * */
	public static List<CodeBean> getUploadTypeList() {

		return commonDao.getUploadTypeList();
	}

	/**
	 * 获取疾病类型下拉菜单
	 * 
	 * */
	public static List<CodeBean> getDiseaseTypeList() {

		return commonDao.getDiseaseTypeList();
	}
	
	/**
	 * 获取疾病类型下拉菜单(带分页)
	 * 
	 * */
	public static List<CodeBean> getDiseaseTypePageList(Pagination pagination) {

		return commonDao.getDiseaseTypePageList(pagination);
	}

	/**
	 * 获取专家下拉菜单
	 * 
	 * */
	public static List<CodeBean> getExpertList() {

		return commonDao.getExpertList();
	}
	
	/**
	 * 获取专家下拉菜单(带分页)
	 * 
	 * */
	public static List<ExpertBean> getExpertPageList(Pagination pagination) {

		return commonDao.getExpertPageList(pagination);
	}
	
	/**
	 * 获取专家名
	 * 
	 * */
	public static String getExpertName(String expert) {

		return commonDao.getExpertName(expert);
	}
	
	/**
	 * 获取医院下拉菜单
	 * 
	 * */
	public static List<CodeBean> getHospitalList() {

		return commonDao.getHospitalList();
	}
	
	/**
	 * 获取医院下拉菜单(带分页)
	 * 
	 * */
	public static List<CodeBean> getHospitalPageList(Pagination pagination) {

		return commonDao.getHospitalPageList(pagination);
	}

	/**
	 * 获取年龄下拉菜单
	 * 
	 * */
	public static List<CodeBean> getAgeList() {

		return commonDao.getAgeList();
	}
	
	/**
	 * 获取职称下拉列表
	 * @return
	 */
	public static List<CodeBean> getTitleList() {
		return commonDao.getTitleList();
	}
	
	/**
	 * 获取微课堂信息
	 * 
	 * */
	public static List<CurriculumBean> getCurriculumListByDate(Timestamp date) {

		return commonDao.getCurriculumListByDate(date);
	}
	
	/**
	 * 根据开始时间获取活动信息
	 * 
	 * */
	public static List<CaseBean> getActivityListByStartDate(Timestamp date) {

		return commonDao.getActivityListByStartDate(date);
	}
	
	/**
	 * 根据结束时间获取活动信息
	 * 
	 * */
	public static List<CaseBean> getActivityListByEndDate(Timestamp date) {

		return commonDao.getActivityListByEndDate(date);
	}
	
	
	/**
	 * 根据结束时间获取预约信息
	 * 
	 * */
	public static List<Integer> getReserveListByEndDate(Timestamp date) {

		return commonDao.getReserveListByEndDate(date);
	}

	/**
	 * 更新活动状态
	 * @return 
	 * 
	 * */
	public static int updActivity(String status, int id) {
		
		return commonDao.updActivity(status,id);
	}
	
	/**
	 * 更新预约状态
	 * @return 
	 * 
	 * */
	public static int updReserve(int id) {
		
		return commonDao.updReserve(id);
	}
	
	/**
	 * 插入message
	 * 
	 * */
	public static int addMessageInfo(MessageBean messageBean) {

		return commonDao.addMessageInfo(messageBean);
	}

	/**
	 * 根据当前日期获得所在周的日期
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getTimeIntervalList(Date date, String format) {

		List<String> resultList = new ArrayList<String>();

		SimpleDateFormat sdf = new SimpleDateFormat(format);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		// 当前周一日期
		String imptimeBegin = sdf.format(cal.getTime());

		// 获取一周日期
		resultList.add(imptimeBegin);
		for (int i = 0; i < 6; i++) {
			cal.add(Calendar.DATE, 1);
			resultList.add(sdf.format(cal.getTime()));
		}

		return resultList;
	}
	
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void wirteToErweima(String htmlUrl, String erweimaUrl,String imgName) throws Exception {
		
		File file = new File(erweimaUrl);
		if (!file.exists()  && !file.isDirectory()) {
			file.mkdir(); 
		}
		
		int width = 200;
		int height = 200;
		// 二维码的图片格式
		String format = "gif";
		Hashtable hints = new Hashtable();
		// 内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(htmlUrl,
				BarcodeFormat.QR_CODE, width, height, hints);
		// 生成二维码
		File outputFile = new File(erweimaUrl+imgName);
		ErweimaBasic.writeToFile(bitMatrix, format, outputFile);

	}
	
	/**
	 * 更新在线状态
	 * @return 
	 * 
	 * */
	public static int updLoginStatus(String userId, String status) {
		
		return commonDao.updLoginStatus(userId, status);
	}
	
	/**
	 * 获取悬浮广告信息
	 * @return 
	 * 
	 * */
	public static List<AdvBean> getAdvSuspenInfo() {
		
		return commonDao.getAdvSuspenInfo();
	}
	
	  /**
	   * 读取某个文件夹下的随机文件名
	   */
	  public static String readfile() {
	 
		String path =ServletContextListener.getWEB_ABSOLUTE_PATH() + "ranImage"; 
		  
		int min = 0;
		Random random = new Random();
		File file = new File(path);
		String[] filelist = file.list();
		int max = filelist.length;
		int s = random.nextInt(max) % (max - min + 1) + min;
		File readfile = new File(path + "\\" + filelist[s]);
		
	    return readfile.getName();
	  }
	  
	  /**
	   * 读取某个文件夹下的随机文件名
	 * @throws IOException 
	   */
	  public static String ffmpeg(String videoName, String imgName) throws IOException {
	 
	    String videoRealPath = ServletContextListener.getWEB_ABSOLUTE_PATH() + Constants.UPLOAD_PATH +"/"+Constants.VIDEO+"/" + videoName;  
        //截图的路径（输出路径）  
        String imageRealPath = ServletContextListener.getWEB_ABSOLUTE_PATH() + Constants.UPLOAD_PATH +"/"+Constants.VIDEO+"/" + imgName + ".jpg";
        
	    String ffmpegPath=ServletContextListener.getWEB_ABSOLUTE_PATH()+"ffmpeg/ffmpeg.bat";
		
//		  String videoRealPath = "d://111.mp4";
//		  String imageRealPath = "d://111.jpg";
//		  String ffmpegPath= "D://Project/J2ee/Medical/WebContent/ffmpeg/ffmpeg.bat";
		  
        //调用批处理文件  
        Runtime.getRuntime().exec("cmd /C start "+ ffmpegPath + " " + videoRealPath + " " + imageRealPath);
        
//        Runtime.getRuntime().exec("cmd /C start wmic process where name='cmd.exe' call terminate");  
        
      	return imgName + ".jpg";
	 }
	  

	  
	  

	public static int getCaseCount(String departmentid) {

		return commonDao.getCaseCount(departmentid);
	}
	public static int getAchievementsCount(String departmentid) {

		return commonDao.getAchievementsCount(departmentid);
	}
	public static int getDrugsCount(String departmentid) {

		return commonDao.getDrugsCount(departmentid);
	}
	public static int getVideoCount(String departmentid) {

		return commonDao.getVideoCount(departmentid);
	}
	public static int getPatentCount(String departmentid) {

		return commonDao.getPatentCount(departmentid);
	}
	public static int getUserCount(String departmentid) {

		return commonDao.getUserCount(departmentid);
	} 

	public static int getNoticeType() {

		AdvBean noticeBean = commonDao.getNoticeType();
		
		if (noticeBean != null) {
			return Integer.parseInt(noticeBean.getType());
		} else{
			return 0;
		}
	} 
	
	public static int getNoticeId() {

		AdvBean noticeBean = commonDao.getNoticeId();
		
		if (noticeBean != null) {
			return noticeBean.getNotice_id();
		} else{
			return 0;
		}
	} 
	
	public static String getNoticeContent() {

		AdvBean noticeBean = commonDao.getNoticeContent();
		
		if (noticeBean != null) {
			return noticeBean.getNotice_content();
		} else{
			return "";
		}
	} 
	/**
	 * 获取固定位数随机数
	 *
	 * @return String
	 */
	public static String genRandomNum(int pwd_len){
        //35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int i; //生成的随机数
        int count = 0; //生成的密码的长度
        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < pwd_len){
         //生成随机数，取绝对值，防止生成负数，
       
         i = Math.abs(r.nextInt(maxNum)); //生成的数最大为36-1
       
         if (i >= 0 && i < str.length) {
          pwd.append(str[i]);
          count ++;
         }
        }
        return pwd.toString();
     }
	  
	public static String getSimpleText(String html){
		String msg = html.replaceAll("<.+?>","");//执行替换成空字符
		
		return msg;
	}
	
	
	public static List<String> getSimpleImg(String html){
		
		Pattern p_img = Pattern.compile("(<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>)");
		Matcher m_img = p_img.matcher(html);
		List<String> imgList = new ArrayList<String>();
		while (m_img.find()) {
			String img = m_img.group(2); //m_img.group(1) 为获得整个img标签  m_img.group(2) 为获得src的值
		    imgList.add(img);
		}
		
		return imgList;
	}

}
