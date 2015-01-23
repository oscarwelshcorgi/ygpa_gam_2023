/**
 *
 */
package egovframework.rte.cmmn;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.commons.validator.*;
import org.apache.commons.validator.util.ValidatorUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springmodules.validation.commons.MessageUtils;
import org.springmodules.validation.commons.ValidatorFactory;


import org.springmodules.validation.commons.taglib.JavascriptValidatorTag;

/**
 *
 * @author eunsungj
 * @since 2014. 3. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 29.		eunsungj		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class EmfJavascriptValidatorTag extends JavascriptValidatorTag {

	private String htmlBeginComment = "\n<!-- Begin \n";

    private String htmlEndComment = "//End --> \n";



	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
//		return super.doStartTag();

        StringBuffer results = new StringBuffer();

        Locale locale = RequestContextUtils.getLocale((HttpServletRequest) pageContext.getRequest());

        ValidatorResources resources = getValidatorResources();

        Form form = resources.getForm(locale, formName);
        if (form != null) {
            if ("true".equalsIgnoreCase(dynamicJavascript)) {
                MessageSource messages = getMessageSource();

                List lActions = new ArrayList();
                List lActionMethods = new ArrayList();

                // Get List of actions for this Form
                for (Iterator i = form.getFields().iterator(); i.hasNext();) {
                    Field field = (Field) i.next();

                    for (Iterator x = field.getDependencyList().iterator(); x.hasNext();) {
                        Object o = x.next();

                        if (o != null && !lActionMethods.contains(o)) {
                            lActionMethods.add(o);
                        }
                    }
                }

                // Create list of ValidatorActions based on lActionMethods
                for (Iterator i = lActionMethods.iterator(); i.hasNext();) {
                    String depends = (String) i.next();
                    ValidatorAction va = resources.getValidatorAction(depends);

                    // throw nicer NPE for easier debugging
                    if (va == null) {
                        throw new NullPointerException("Depends string \""
                            + depends
                            + "\" was not found in validator-rules.xml.");
                    }

                    String javascript = va.getJavascript();
                    if (javascript != null && javascript.length() > 0) {
                        lActions.add(va);
                    } else {
                        i.remove();
                    }
                }

                Collections.sort(lActions, new Comparator() {
                    public int compare(Object o1, Object o2) {
                        ValidatorAction va1 = (ValidatorAction) o1;
                        ValidatorAction va2 = (ValidatorAction) o2;

                        if ((va1.getDepends() == null || va1.getDepends().length() == 0)
                            && (va2.getDepends() == null || va2.getDepends().length() == 0)) {
                            return 0;
                        } else if (
                            (va1.getDepends() != null && va1.getDepends().length() > 0)
                                && (va2.getDepends() == null || va2.getDepends().length() == 0)) {
                            return 1;
                        } else if (
                            (va1.getDepends() == null || va1.getDepends().length() == 0)
                                && (va2.getDepends() != null && va2.getDepends().length() > 0)) {
                            return -1;
                        } else {
                            return va1.getDependencyList().size() - va2.getDependencyList().size();
                        }
                    }
                });

                String methods = null;
                for (Iterator i = lActions.iterator(); i.hasNext();) {
                    ValidatorAction va = (ValidatorAction) i.next();
                    String functionName = null;

                    if (va.getJsFunctionName() != null && va.getJsFunctionName().length() > 0) {
                        functionName = va.getJsFunctionName();
                    } else {
                        functionName = va.getName();
                    }

                    if (methods == null) {
                        methods = va.getMethod() + "(form, "+this.getFormName()+"_"+ functionName+")";
                    } else {
                        methods += " && " + va.getMethod() + "(form, "+this.getFormName()+"_"+ functionName+")";
                    }
                }

                results.append(getJavascriptBegin(methods));

                for (Iterator i = lActions.iterator(); i.hasNext();) {
                    ValidatorAction va = (ValidatorAction) i.next();
                    String jscriptVar = null;
                    String functionName = null;

                    if (va.getJsFunctionName() != null && va.getJsFunctionName().length() > 0) {
                        functionName = va.getJsFunctionName();
                    } else {
                        functionName = va.getName();
                    }


                    results.append("    function " + this.getFormName()+"_"+ functionName + " () { \n");
                    for (Iterator x = form.getFields().iterator(); x.hasNext();) {
                        Field field = (Field) x.next();

                        // Skip indexed fields for now until there is a good way to handle
                        // error messages (and the length of the list (could retrieve from scope?))
                        if (field.isIndexed()
                            || field.getPage() != page
                            || !field.isDependency(va.getName())) {

                            continue;
                        }

                        String message = MessageUtils.getMessage(messages, locale, va, field);

                        message = (message != null) ? message : "";

                        jscriptVar = this.getNextVar(jscriptVar);

                        results.append("     this."
                            + jscriptVar
                            + " = new Array(\""
                            + field.getKey()
                            + "\", \""
                            + message
                            + "\", ");

                        results.append("new Function (\"varName\", \"");

                        Map vars = field.getVars();
                        // Loop through the field's variables.
                        Iterator varsIterator = vars.keySet().iterator();
                        while (varsIterator.hasNext()) {
                            String varName = (String) varsIterator.next();
                            Var var = (Var) vars.get(varName);
                            String varValue = var.getValue();
                            String jsType = var.getJsType();

                            // skip requiredif variables field, fieldIndexed, fieldTest, fieldValue
                            if (varName.startsWith("field")) {
                                continue;
                            }

                            if (Var.JSTYPE_INT.equalsIgnoreCase(jsType)) {
                                results.append("this."
                                    + varName
                                    + "="
                                    + ValidatorUtils.replace(varValue,
                                    "\\",
                                    "\\\\")
                                    + "; ");
                            } else if (Var.JSTYPE_REGEXP.equalsIgnoreCase(jsType)) {
                                results.append("this."
                                    + varName
                                    + "=/"
                                    + ValidatorUtils.replace(varValue,
                                    "\\",
                                    "\\\\")
                                    + "/; ");
                            } else if (Var.JSTYPE_STRING.equalsIgnoreCase(jsType)) {
                                results.append("this."
                                    + varName
                                    + "='"
                                    + ValidatorUtils.replace(varValue,
                                    "\\",
                                    "\\\\")
                                    + "'; ");
                                // So everyone using the latest format doesn't need to change their xml files immediately.
                            } else if ("mask".equalsIgnoreCase(varName)) {
                                results.append("this."
                                    + varName
                                    + "=/"
                                    + ValidatorUtils.replace(varValue,
                                    "\\",
                                    "\\\\")
                                    + "/; ");
                            } else {
                                results.append("this."
                                    + varName
                                    + "='"
                                    + ValidatorUtils.replace(varValue,
                                    "\\",
                                    "\\\\")
                                    + "'; ");
                            }
                        }

                        results.append(" return this[varName];\"));\n");
                    }
                    results.append("    } \n\n");
                }
            } else if ("true".equalsIgnoreCase(staticJavascript)) {
                results.append(this.getStartElement());
                if ("true".equalsIgnoreCase(htmlComment)) {
                    results.append(htmlBeginComment);
                }
            }
        }

        if ("true".equalsIgnoreCase(staticJavascript)) {
            results.append(getJavascriptStaticMethods(resources));
        }

        if (form != null
            && ("true".equalsIgnoreCase(dynamicJavascript)
            || "true".equalsIgnoreCase(staticJavascript))) {

            results.append(getJavascriptEnd());
        }


        JspWriter writer = pageContext.getOut();
        try {
            writer.print(results.toString());
        }
        catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return (SKIP_BODY);


	}

    /**
     * Use the application context itself for default message resolution.
     */
    private MessageSource getMessageSource() {
        try {
            this.requestContext =
                new RequestContext((HttpServletRequest) this.pageContext.getRequest());
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex) {
            pageContext.getServletContext().log("Exception in custom tag", ex);
        }
        return requestContext.getWebApplicationContext();
    }


    /**
     * Get the validator resources from a ValidatorFactory defined in the
     * web application context or one of its parent contexts.
     * The bean is resolved by type (org.springmodules.commons.validator.ValidatorFactory).
     *
     * @return ValidatorResources from a ValidatorFactory
     */
    private ValidatorResources getValidatorResources() {
        WebApplicationContext ctx = (WebApplicationContext)
            pageContext.getRequest().getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        if (ctx == null) {
            // look in main application context (i.e. applicationContext.xml)
            ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
        }
        ValidatorFactory factory = (ValidatorFactory)
            BeanFactoryUtils.beanOfTypeIncludingAncestors(ctx, ValidatorFactory.class, true, true);
        return factory.getValidatorResources();
    }

    /**
     * The value <code>null</code> will be returned at the end of the sequence.
     * &nbsp;&nbsp;&nbsp; ex: "zz" will return <code>null</code>
     */
    private String getNextVar(String input) {
        if (input == null) {
            return "aa";
        }

        input = input.toLowerCase();

        for (int i = input.length(); i > 0; i--) {
            int pos = i - 1;

            char c = input.charAt(pos);
            c++;

            if (c <= 'z') {
                if (i == 0) {
                    return c + input.substring(pos, input.length());
                } else if (i == input.length()) {
                    return input.substring(0, pos) + c;
                } else {
                    return input.substring(0, pos) + c + input.substring(pos, input.length() - 1);
                }
            } else {
                input = replaceChar(input, pos, 'a');
            }

        }

        return null;

    }

    /**
     * Constructs the beginning &lt;script&gt; element depending on xhtml status.
     */
    private String getStartElement() {
        StringBuffer start = new StringBuffer("<script type=\"text/javascript\"");

        // there is no language attribute in xhtml
        if (!this.isXhtml()) {
            start.append(" language=\"Javascript1.1\"");
        }

        if (this.src != null) {
            start.append(" src=\"" + src + "\"");
        }

        start.append("> \n");
        return start.toString();
    }

    /**
     * Returns true if this is an xhtml page.
     */
    private boolean isXhtml() {
        return "true".equalsIgnoreCase(xhtml);
    }

    /**
     * Replaces a single character in a <code>String</code>
     */
    private String replaceChar(String input, int pos, char c) {
        if (pos == 0) {
            return c + input.substring(pos, input.length());
        } else if (pos == input.length()) {
            return input.substring(0, pos) + c;
        } else {
            return input.substring(0, pos) + c + input.substring(pos, input.length() - 1);
        }
    }


}
