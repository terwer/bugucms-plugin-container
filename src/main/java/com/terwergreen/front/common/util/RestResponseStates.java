package com.terwergreen.front.common.util;

/**
 * @author terwergreen Rest响应客户端的状态值
 */
public enum RestResponseStates {

    SUCCESS("1", "请求成功，无任何异常"),

    SERVER_ERROR("2", "系统忙，请稍候再试。如有疑问请拨打客服电话"),
    
    VALID_LOGIN("3333","登录超时，请重新登录!"),
    
    VALID_IDNO("1001","身份证格式错误。"),
    
    H5_VERSION_NOT_EXISTS("1002", "传入的H5版本号不存在"),

    H5_VERSION_IS_ALREADY_NEWEST("1003", "传入的H5版本号已经是最新的，无需更新"),

    IDENTITY_ILLEGAL("4","身份证校验失败，请重新输入"),
    
    H5_VERSION_IS_TOO_OLD_TO_UPDATE("1004", "传入的H5版本号已经太早，不提供更新包"),
    
    VALID_BIND_CARD("1005","您还没有绑定银行卡信息!"),
    
    VALID_IS_NULL("1006","必填参数缺失"),
    
    UPDATE_BIND_BANKCARD_FAILED("1007","更新鉴权卡信息失败"),
    
    APPNO_EXCEPTION("20", "申请号异常,请重新登录！"),
    
    VALID_SMS_VERIFICATION_CODE_IS_NULL("3","短信验证码为空，请重试"),
    

    ELECSIGN_ERROR("005", "I贷迁徙电子签名异常"),
    
    DOCUMENTSINFO_ERROR("090001", "系统异常"),
    
    DOCUMENTSINFO_PARAM_01_ERROR("090101", "list不能为null或者长度为0"),

    DOCUMENTSINFO_PARAM_02_ERROR("090102", "list超过最大长度"),
    
    DOCUMENTSINFO_PARAM_03_ERROR("090103", "documentCode不能为空"),
    
    DOCUMENTSINFO_PARAM_04_ERROR("090104", "documentRule不能为空"),
    
    DOCUMENTSINFO_SUCCESS("000000", "成功"),
    
    DOCUMENTSINFO_PREVIEW_ISNULL("080201", "参数不能为空"),
    
    DOCUMENTSINFO_PREVIEW_PARAM_ISNULL("080202", "参数错误"),
    
    DOCUMENTSINFO_PREVIEW_PARAM_01("080203", "单证编码不存在"),
    
    DOCUMENTSINFO_PREVIEW_PARAM_02("080203", "单证规则不存在"),
    
    DOCUMENTSINFO_PREVIEW_PARAM_03("080203", "分组不能为空"),
    
    DOCUMENTSINFO_POLLING_ERROR("080204", "轮询失败"),

    DOCUMENTSINFO_POLLING_ERROR_01("080205", "系统繁忙"),
    
    DOCUMENTSINFO_GROUP_ERROR("080206", "按组查询失败"),

    DOCUMENTSINFO_COMPSE_FAIL("080101", "请求单证平台失败");
    
    private String value;
    private String msg;

    private RestResponseStates(String value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
