package common;

public class Constants {

	// 上传文件类型
	public static String UPLOAD_IMAGE = "image";
	public static String UPLOAD_FILE = "file";
	public static String UPLOAD_MEDIA = "media";
	public static String UPLOAD_PPT = "ppt";

	// 上传文件路径
	public static String UPLOAD_PATH = "uploadFile";
	
	//二维码路径
	public static String CODE_PATH = "codeFile";

	// 服务器根目录
	public static String SERVER_PATH = "http://localhost:8080/medical/";
	
	// 在线咨询
	// APPKEY
	
	// 短信模版
	// 预约通知(专家)
	public static String APPOINTMENT_EXPERT  = "SMS_126464273";
	// 预约咨询(用户)
	public static String APPOINTMENT_USER  = "SMS_126359448";
	// 预约咨询(结果)
	public static String APPOINTMENT_RESULT  = "SMS_126364436";
	
	// 机能ID
	//banner
	public static String BANNER = "banner";
	// 专家
	public static String EXPERT = "expert";
	// 用户
	public static String USER = "user";
	//患者
	public static String PATIENT = "patient";
	// 广告
	public static String ADVERTISING = "advertising";
	// 新闻
	public static String NEWS = "news";
	// 简介
	public static String INTRODUCTION = "introduction";
	// 医患故事
	public static String STORY = "story";
	// 话题
	public static String TOPIC = "topic";
	// 政策法规
	public static String POLICY = "policy";
	// 视频
	public static String VIDEO = "video";
	// 前沿科技
	public static String TECHNOLOGY = "technology";
	// 病例
	public static String CASE = "case";
	// 活动
	public static String ACTIVITY = "activity";
	// 专利
	public static String PATENT = "patent";
	// 会诊
	public static String REMOTE = "remote";
	//成果展示
	public static String ACHIEVEMENTS = "achievements";
	//课程
	public static String CURRICULUM = "curriculum";
	//医学著作展示
	public static String LITERATURE = "literature";
	//药品
	public static String DRUGS = "drugs";
	//器械
	public static String APPARATUS = "apparatus";
	//多中心合作
	public static String MULTICENTER = "multicenter";
	//进修报名
	public static String EDUCATION = "education";
	

	//------------message标题-------------
	// 添加用户
	public static String TITLE_ADD_USER = "恭喜您成为平台用户";
	// 添加专家
	public static String TITLE_ADD_EXPERT = "恭喜您成为专家权限";
	// 删除专家
	public static String TITLE_DEL_EXPERT = "您已失去专家权限";
	// 专家排班
	public static String TITLE_EXPERT_SCHEDU = "最新排班计划";
	// 审核中
	public static String TITLE_AUDITING = "申请成功";
	// 通过
	public static String TITLE_APPROVAL = "审核通过";
	// 拒绝
	public static String TITLE_REJECTION = "审核未通过";
	//微课堂开课通知
	public static String TITLE_START = "开课通知";
	// 预约中
	public static String TITLE_RESERVE = "新的预约申请到达";
	
	// 删除
	public static String TITLE_DELETE = "？已删除";
	//病例参选
	public static String TITLE_START_CASE = "病例参选";
	//取消病例参选
	public static String TITLE_CANCEL_CASE = "取消病例参选";
	//优秀病例评选活动开启
	public static String TITLE_START_ACTIVITY = "优秀病例评选活动开启";
	//课题邀请专家(多中心合作)
	public static String TITLE_MULTICENTER ="课题邀请专家参与";
	
	public static String TITLE_CURRICULUM = "课程开启";
		
	//------------message内容-------------
	// 添加用户
	public static String ADD_USER = "恭喜您成为平台用户！";
	// 添加专家
	public static String ADD_EXPERT = "恭喜您成为专家权限！";
	// 删除专家
	public static String DEL_EXPERT = "抱歉，您已失去专家权限！";
	// 专家排班
	public static String EXPERT_SCHEDU = "您的最新排班计划已完成！";
	// 审核中
	public static String AUDITING = "您上传的？已申请成功，请等待审核！";
	// 通过
	public static String APPROVAL = "恭喜您，您上传的？已审核通过！";
	// 拒绝
	public static String REJECTION = "抱歉，您上传的？审核未通过！请修改后再重新上传！";
	// 删除
	public static String DELETE = "您上传的？已删除！";
	//病例参选
	public static String START_CASE = "您的病例？已参加优秀病例评选活动,请等待活动开启";
	//取消病例参选
	public static String CANCEL_CASE = "您的病例？已取消参加优秀病例评选活动";
	//优秀病例评选活动开启
	public static String START_ACTIVITY = "您的病例？参加的优秀病例评选活动已开启";
	//课题邀请专家
	public static String MULTICENTER_INVITE = "您已经被邀请参与课题？，期待您的参与！";
	//微课堂开课通知
	public static String OPEN_CURRICULUM = "您的课程已发布,请于？！";
	//微课堂开课通知
	public static String START_CURRICULUM = "您预约的课程？已开课，请及时观看。";
	//预约专家通知
	public static String RESERVE_EXPERT = "您有新的预约，请及时确认。";
	
	// 在线状态
	// 在线
	public static String ONLOGIN = "0";
	// 离线
	public static String UNLOGIN = "1";
	
	// 权限
	// 医生
	public static String DOCTOR_PERMISSIONS = "1";
	// 专家
	public static String EXPERT_PERMISSIONS = "2";
	// 科室管理员
	public static String DEPARTMENT_MANAGE = "3";
	// 平台管理员
	public static String ADMIN = "4";
}
