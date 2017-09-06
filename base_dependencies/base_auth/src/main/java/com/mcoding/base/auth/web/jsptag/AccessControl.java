package com.mcoding.base.auth.web.jsptag;

import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by LiBing on 2014-07-24  13:39
 */
public class AccessControl extends TagSupport {
	private static final long serialVersionUID = 1L;

	private String menuCode;
    private String operCode;

    @SuppressWarnings("unchecked")
	@Override
    public int doStartTag() throws JspException {
        Object obj =  pageContext.getSession().getAttribute("userRight");
        if(obj instanceof Map){
            Map<String, Set<String>> rightMap = (Map<String, Set<String>>) obj;
            if(rightMap.containsKey("menuAll")){
                return EVAL_BODY_INCLUDE;
            } else {
                if(rightMap.containsKey(menuCode)){
                    Set<String> operSet = rightMap.get(menuCode);
                    if(operSet.contains(operCode)){
                        return EVAL_BODY_INCLUDE;
                    }
                }
            }
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return  EVAL_BODY_INCLUDE;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getOperCode() {
        return operCode;
    }

    public void setOperCode(String operCode) {
        this.operCode = operCode;
    }
}
