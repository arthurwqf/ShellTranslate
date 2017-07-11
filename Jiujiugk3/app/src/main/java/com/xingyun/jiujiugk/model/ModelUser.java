package com.xingyun.jiujiugk.model;


public class ModelUser {


    //{"user_id":"2","realname":"\u8d75\u5fd7\u519b","group_title":"\u8d75\u5fd7\u519b","mobile":"13631572202","level":"0","avatar":"http:\/\/static.99.xingyun.net\/images\/avatar_default.png"}
    private String mobile, realname, avatar, group_title, im_token, qr_code, hospital_title,
            hospital_department_title, job_title, speciality, work_experience, signature,
            system_phone_no,//系统呼叫电话
            service_phone_no;//玖玖小秘书服务电话

    private int level, sex, job_id, hospital_id, hospital_department_id, doctor_price, expert_price;
    /**
     * 是否有权限提交或查看关节登记系统
     */
    private int is_joint = -1; //0:无权限，不显示关节登记  1：有权限
    private int doctor_time_length, expert_time_length, user_id;
    /**
     * 患者1  医生2  专家3
     */
    private int group_id = -1;

    /**
     * 是否有权限审核病历，0表示没有，1表示有
     */
    private int is_joint_reviewer = -1;
    /**
     * 用户是否验证成功 0:未认证，1：认证成功，2：认证失败，3：认证中
     */
    private int certi_id;

    public int getIs_joint_reviewer() {
        return is_joint_reviewer;
    }

    public void setIs_joint_reviewer(int is_joint_reviewer) {
        this.is_joint_reviewer = is_joint_reviewer;
    }

    public int getIs_joint() {
        return is_joint;
    }

    public void setIs_joint(int is_joint) {
        this.is_joint = is_joint;
    }

    public String getService_phone_no() {
        return service_phone_no;
    }

    public void setService_phone_no(String service_phone_no) {
        this.service_phone_no = service_phone_no;
    }

    public String getSystem_phone_no() {
        return system_phone_no;
    }

    public void setSystem_phone_no(String system_phone_no) {
        this.system_phone_no = system_phone_no;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getDoctor_price() {
        return doctor_price;
    }

    public void setDoctor_price(int doctor_price) {
        this.doctor_price = doctor_price;
    }

    public int getDoctor_time_length() {
        return doctor_time_length;
    }

    public void setDoctor_time_length(int doctor_time_length) {
        this.doctor_time_length = doctor_time_length;
    }

    public int getExpert_price() {
        return expert_price;
    }

    public void setExpert_price(int expert_price) {
        this.expert_price = expert_price;
    }

    public int getExpert_time_length() {
        return expert_time_length;
    }

    public void setExpert_time_length(int expert_time_length) {
        this.expert_time_length = expert_time_length;
    }

    public String getHospital_title() {
        return hospital_title;
    }

    public void setHospital_title(String hospital_title) {
        this.hospital_title = hospital_title;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public int getHospital_department_id() {
        return hospital_department_id;
    }

    public void setHospital_department_id(int hospital_department_id) {
        this.hospital_department_id = hospital_department_id;
    }

    public String getHospital_department_title() {
        return hospital_department_title;
    }

    public void setHospital_department_title(String hospital_department_title) {
        this.hospital_department_title = hospital_department_title;
    }


    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getWork_experience() {
        return work_experience;
    }

    public void setWork_experience(String work_experience) {
        this.work_experience = work_experience;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public String getIm_token() {
        return im_token;
    }

    public void setIm_token(String im_token) {
        this.im_token = im_token;
    }

    public String getGroup_title() {
        return group_title;
    }

    public void setGroup_title(String group_title) {
        this.group_title = group_title;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCerti_id() {
        return certi_id;
    }

    public void setCerti_id(int certi_id) {
        this.certi_id = certi_id;
    }
}
