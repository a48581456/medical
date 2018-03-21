package parameter;

import java.io.File;
import java.sql.Timestamp;


public class UserBean {

	// 用户ID
	private String user_id;
	// 昵称
	private String user_name;
	// 密码
	private String user_password;
	// 旧密码
	private String old_password;
	// 验证码
	private String validCode;
	// 邮件
	private String mail;
	// 身份证
	private String card_id;
	// 真实姓名
	private String name;
	// 地址
	private String company_address;
	// 电话
	private String regis_tel;
	// 备注
	private String remarks;
	// 科室
	private String departmentid;
	// 科室名
	private String departmentName;
	// 状态
	private String status;
	// 状态名
	private String statusName;
	// 权限
	private String permiss;
	// 权限名
	private String permissName;
	// 头像
	private String pic_path;
	//性别
	private String sex;
	//年龄
	private String age;
	//职称
	private String title;
	
	private String auditFlag;
	
	// 所属医院
	private String hospital;
	
	// 值班状态
	private boolean scheduFlag = false;
	
	// 在线状态
	private String onloginStatus;
	
	private String create_user;
	private Timestamp create_date;
	private String update_user;
	private Timestamp update_date;
	
	private String token;
	
	//医师资格证书
	private String img1;
	
	//医师职业证书
	private String img2;
	
	//身份证正反面
	private String img3;
	private String img4;
	
	private String oldPic;
	
	//----------文件上传-----------
  	//图片文件
  	private File img;
  	
    private String imgFileName;  
 
    private String imgContentType;
    
    private File imgFile1;
  	
    private String imgFile1FileName;  
 
    private String imgFile1ContentType;
    
    private File imgFile2;
  	
    private String imgFile2FileName;  
 
    private String imgFile2ContentType;
    
    private File imgFile3;
  	
    private String imgFile3FileName;  
 
    private String imgFile3ContentType;
    
    private File imgFile4;
  	
    private String imgFile4FileName;  
 
    private String imgFile4ContentType;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getValidCode() {
		return validCode;
	}
	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}
	public String getRegis_tel() {
		return regis_tel;
	}
	public void setRegis_tel(String regis_tel) {
		this.regis_tel = regis_tel;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPermiss() {
		return permiss;
	}
	public void setPermiss(String permiss) {
		this.permiss = permiss;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getPermissName() {
		return permissName;
	}
	public void setPermissName(String permissName) {
		this.permissName = permissName;
	}
	public String getAuditFlag() {
		return auditFlag;
	}
	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	public String getUpdate_user() {
		return update_user;
	}
	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}
	public Timestamp getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Timestamp update_date) {
		this.update_date = update_date;
	}
	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getImg2() {
		return img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public String getImg3() {
		return img3;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
	}
	public String getImg4() {
		return img4;
	}
	public void setImg4(String img4) {
		this.img4 = img4;
	}
	public String getOld_password() {
		return old_password;
	}
	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}
	public String getPic_path() {
		return pic_path;
	}
	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public File getImg() {
		return img;
	}
	public void setImg(File img) {
		this.img = img;
	}
	public String getImgFileName() {
		return imgFileName;
	}
	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
	public String getImgContentType() {
		return imgContentType;
	}
	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}
	public File getImgFile1() {
		return imgFile1;
	}
	public void setImgFile1(File imgFile1) {
		this.imgFile1 = imgFile1;
	}
	public String getImgFile1FileName() {
		return imgFile1FileName;
	}
	public void setImgFile1FileName(String imgFile1FileName) {
		this.imgFile1FileName = imgFile1FileName;
	}
	public String getImgFile1ContentType() {
		return imgFile1ContentType;
	}
	public void setImgFile1ContentType(String imgFile1ContentType) {
		this.imgFile1ContentType = imgFile1ContentType;
	}
	public File getImgFile2() {
		return imgFile2;
	}
	public void setImgFile2(File imgFile2) {
		this.imgFile2 = imgFile2;
	}
	public String getImgFile2FileName() {
		return imgFile2FileName;
	}
	public void setImgFile2FileName(String imgFile2FileName) {
		this.imgFile2FileName = imgFile2FileName;
	}
	public String getImgFile2ContentType() {
		return imgFile2ContentType;
	}
	public void setImgFile2ContentType(String imgFile2ContentType) {
		this.imgFile2ContentType = imgFile2ContentType;
	}
	public File getImgFile3() {
		return imgFile3;
	}
	public void setImgFile3(File imgFile3) {
		this.imgFile3 = imgFile3;
	}
	public String getImgFile3FileName() {
		return imgFile3FileName;
	}
	public void setImgFile3FileName(String imgFile3FileName) {
		this.imgFile3FileName = imgFile3FileName;
	}
	public String getImgFile3ContentType() {
		return imgFile3ContentType;
	}
	public void setImgFile3ContentType(String imgFile3ContentType) {
		this.imgFile3ContentType = imgFile3ContentType;
	}
	public File getImgFile4() {
		return imgFile4;
	}
	public void setImgFile4(File imgFile4) {
		this.imgFile4 = imgFile4;
	}
	public String getImgFile4FileName() {
		return imgFile4FileName;
	}
	public void setImgFile4FileName(String imgFile4FileName) {
		this.imgFile4FileName = imgFile4FileName;
	}
	public String getImgFile4ContentType() {
		return imgFile4ContentType;
	}
	public void setImgFile4ContentType(String imgFile4ContentType) {
		this.imgFile4ContentType = imgFile4ContentType;
	}
	public boolean isScheduFlag() {
		return scheduFlag;
	}
	public void setScheduFlag(boolean scheduFlag) {
		this.scheduFlag = scheduFlag;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getOnloginStatus() {
		return onloginStatus;
	}
	public void setOnloginStatus(String onloginStatus) {
		this.onloginStatus = onloginStatus;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getOldPic() {
		return oldPic;
	}
	public void setOldPic(String oldPic) {
		this.oldPic = oldPic;
	}
	
}
