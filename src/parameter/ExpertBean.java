package parameter;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class ExpertBean {

	// 专家用户ID
	private String expert_id;
	// 专家名
	private String expert_name;
	// 状态
	private String status;
	// 排队人数
	private String line_up_penson;
	// 专家简介
	private String coprofile;
	// 专家详细介绍
	private String comment;
	// 专家排班
	private String expert_scheduling;
	// 所属医院
	private String hospital;
	
	private String hospitalId;
	// 科室
	private String departmentid;
	// 科室名
	private String departmentName;
	// 照片路径
	private String photoPath;
	// 排班list
	private List<String> scheduList;
	
	// 在线状态
	private String onloginStatus;
	
	// 首页展示
	private String show_flag;
	
	//专家所属
	private String flag;
	
	//排序ID 
	private String order_id;
	
	//专家职称
	private String title;
	// 电话
	private String regis_tel;
	
	//文件
    private File file;

    private String fileFileName;  
 
    private String fileContentType;  
	
	//选择专家标识
	private String searchFlg;
	
	private String create_user;
	private Timestamp create_date;
	private String update_user;
	private Timestamp update_date;
	
	private List<ScheduBean> scheduBeanList = new ArrayList<ScheduBean>();
	
	private List<ExpertBean> expertBeanList = new ArrayList<ExpertBean>();
	
	
	public String getExpert_id() {
		return expert_id;
	}
	public void setExpert_id(String expert_id) {
		this.expert_id = expert_id;
	}
	public String getExpert_name() {
		return expert_name;
	}
	public void setExpert_name(String expert_name) {
		this.expert_name = expert_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLine_up_penson() {
		return line_up_penson;
	}
	public void setLine_up_penson(String line_up_penson) {
		this.line_up_penson = line_up_penson;
	}
	public String getCoprofile() {
		return coprofile;
	}
	public void setCoprofile(String coprofile) {
		this.coprofile = coprofile;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getExpert_scheduling() {
		return expert_scheduling;
	}
	public void setExpert_scheduling(String expert_scheduling) {
		this.expert_scheduling = expert_scheduling;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public List<String> getScheduList() {
		return scheduList;
	}
	public void setScheduList(List<String> scheduList) {
		this.scheduList = scheduList;
	}
	public List<ScheduBean> getScheduBeanList() {
		return scheduBeanList;
	}
	public void setScheduBeanList(List<ScheduBean> scheduBeanList) {
		this.scheduBeanList = scheduBeanList;
	}
	public List<ExpertBean> getExpertBeanList() {
		return expertBeanList;
	}
	public void setExpertBeanList(List<ExpertBean> expertBeanList) {
		this.expertBeanList = expertBeanList;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getSearchFlg() {
		return searchFlg;
	}
	public void setSearchFlg(String searchFlg) {
		this.searchFlg = searchFlg;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getOnloginStatus() {
		return onloginStatus;
	}
	public void setOnloginStatus(String onloginStatus) {
		this.onloginStatus = onloginStatus;
	}
	public String getShow_flag() {
		return show_flag;
	}
	public void setShow_flag(String show_flag) {
		this.show_flag = show_flag;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the regis_tel
	 */
	public String getRegis_tel() {
		return regis_tel;
	}
	/**
	 * @param regis_tel the regis_tel to set
	 */
	public void setRegis_tel(String regis_tel) {
		this.regis_tel = regis_tel;
	}
	
}
