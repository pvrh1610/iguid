package SalicAM;

import SalicAM.common.ManagerDashbordAM;

import common.GenerateEmailTemplate;

import common.pojo.EmailRequestPojo;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import oracle.adf.share.ADFContext;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Aug 09 14:00:00 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ManagerDashbordAMImpl extends ApplicationModuleImpl implements ManagerDashbordAM {
    /**
     * This is the default constructor (do not remove).
     */
    public ManagerDashbordAMImpl() {
    }

    /**
     * Container's getter for XxhcmApprovalDtlsVO1.
     * @return XxhcmApprovalDtlsVO1
     */
    public ViewObjectImpl getXxhcmApprovalDtlsVO1() {
        return (ViewObjectImpl)findViewObject("XxhcmApprovalDtlsVO1");
    }

    /**
     * Container's getter for XxhcmManagerReqDtlsVO1.
     * @return XxhcmManagerReqDtlsVO1
     */
    public ViewObjectImpl getXxhcmManagerReqDtlsVO1() {
        return (ViewObjectImpl)findViewObject("XxhcmManagerReqDtlsVO1");
    }

    /**
     * Container's getter for XxQpActionHistoryTVO1.
     * @return XxQpActionHistoryTVO1
     */
    public ViewObjectImpl getXxQpActionHistoryTVO1() {
        return (ViewObjectImpl)findViewObject("XxQpActionHistoryTVO1");
    }
    public void load(){
//        ADFContext aDFContext = ADFContext.getCurrent();
//        aDFContext.getPageFlowScope().put("mempId",empId);
        ADFContext aDFContext = ADFContext.getCurrent();
        BigDecimal empId = (BigDecimal)aDFContext.getPageFlowScope().get("mempId");
        ViewObject mgrVo1=this.getmanagerDashbaordROVO1();
        mgrVo1.setNamedWhereClauseParam("p_emp_logged_in", empId);
        mgrVo1.executeQuery();
        
        ViewObject mgrVo=this.getmanagerDashbaordROVO2();
        mgrVo.setNamedWhereClauseParam("p_emp_logged_in", empId);
        System.err.println("total request "+mgrVo.getEstimatedRowCount());
        
        aDFContext.getPageFlowScope().put("total", mgrVo.getEstimatedRowCount());
        
        ViewCriteria vc=mgrVo.createViewCriteria();
        ViewCriteriaRow vcr=vc.createViewCriteriaRow();
        vcr.setAttribute("Status", "APPROVE");
        vc.addRow(vcr);
        mgrVo.applyViewCriteria(vc);
        mgrVo.executeQuery();
        System.err.println("Approved request "+mgrVo.getEstimatedRowCount());
        aDFContext.getPageFlowScope().put("approved", mgrVo.getEstimatedRowCount());
        
        ViewCriteria vcc=mgrVo.createViewCriteria();
        ViewCriteriaRow vccr=vcc.createViewCriteriaRow();
        vccr.setAttribute("Status", "REJECT");
        vcc.addRow(vccr);
        mgrVo.applyViewCriteria(vcc);
        mgrVo.executeQuery();
        System.err.println("Rejected request "+mgrVo.getEstimatedRowCount());
        aDFContext.getPageFlowScope().put("rejected", mgrVo.getEstimatedRowCount());
        
        ViewCriteria vccc=mgrVo.createViewCriteria();
        ViewCriteriaRow vcccr=vccc.createViewCriteriaRow();
        vcccr.setAttribute("Status", "Pending Approval");
        vccc.addRow(vcccr);
        mgrVo.applyViewCriteria(vccc);
        mgrVo.executeQuery();
        System.err.println("Pending request "+mgrVo.getEstimatedRowCount());
        aDFContext.getPageFlowScope().put("pending", mgrVo.getEstimatedRowCount());
        
        ViewCriteria vcccc=mgrVo.createViewCriteria();
        ViewCriteriaRow vccccr=vcccc.createViewCriteriaRow();
        vccccr.setAttribute("RequestNumber", "");
        vcccc.addRow(vccccr);
        mgrVo.applyViewCriteria(null);
        mgrVo.executeQuery();
        //        mgrVo.getEstimatedRowCount();
//        AdfFacesContext.getCurrentInstance().addPartialTarget(totaReq);
    }

    /**
     * Container's getter for XxQpActionHistoryTVO2.
     * @return XxQpActionHistoryTVO2
     */
    public ViewObjectImpl getXxQpActionHistoryTVO2() {
        return (ViewObjectImpl)findViewObject("XxQpActionHistoryTVO2");
    }

    /**
     * Container's getter for managerDashbaordROVO1.
     * @return managerDashbaordROVO1
     */
    public ViewObjectImpl getmanagerDashbaordROVO1() {
        return (ViewObjectImpl)findViewObject("managerDashbaordROVO1");
    }

    /**
     * Container's getter for managerDashbaordROVO2.
     * @return managerDashbaordROVO2
     */
    public ViewObjectImpl getmanagerDashbaordROVO2() {
        return (ViewObjectImpl)findViewObject("managerDashbaordROVO2");
    }

    /**
     * Container's getter for managerDashbaordROVO3.
     * @return managerDashbaordROVO3
     */
    public ViewObjectImpl getmanagerDashbaordROVO3() {
        return (ViewObjectImpl)findViewObject("managerDashbaordROVO3");
    }


    /**
     * Container's getter for XxQpActionHistoryTVO4.
     * @return XxQpActionHistoryTVO4
     */
    public ViewObjectImpl getXxQpActionHistoryTVO4() {
        return (ViewObjectImpl)findViewObject("XxQpActionHistoryTVO4");
    }

    /**
     * Container's getter for manager_QpActionHistory_VL2.
     * @return manager_QpActionHistory_VL2
     */
    public ViewLinkImpl getmanager_QpActionHistory_VL2() {
        return (ViewLinkImpl)findViewLink("manager_QpActionHistory_VL2");
    }
    
    public void prepareMailTemplateAndSend(String approveOrReject) {
        EmailRequestPojo emailReq = new EmailRequestPojo();
        
        String reqType =  getStringBasedOnReqType((String) ADFContext.getCurrent().getSessionScope()
                                                                               .get("page"));
        
        ViewObjectImpl otHdrVO = getmanagerDashbaordROVO1();
        emailReq.setRequestId(((oracle.jbo.domain.Number) otHdrVO.getCurrentRow().getAttribute("ReqId")).intValue());
        emailReq.setRequestNo((String) otHdrVO.getCurrentRow().getAttribute("RequestNumber"));
        emailReq.setEmpId(((oracle.jbo.domain.Number) otHdrVO.getCurrentRow().getAttribute("EmpId")).stringValue());
        emailReq.setEmpName((String) otHdrVO.getCurrentRow().getAttribute("EmployeeName"));

        ArrayList<String> toRecepients = new ArrayList<String>();
        
        getXxQpActionHistoryTVO1().setNamedWhereClauseParam("p_req_typ", (String) otHdrVO.getCurrentRow().getAttribute("ReqType"));
        getXxQpActionHistoryTVO1().setNamedWhereClauseParam("p_req_id", ((oracle.jbo.domain.Number) otHdrVO.getCurrentRow().getAttribute("ReqId")).bigDecimalValue());
        getXxQpActionHistoryTVO1().executeQuery();
        
        BigDecimal empId = (BigDecimal) ADFContext.getCurrent().getPageFlowScope().get("mempId");
        
        BigDecimal approveLevel = null;
        String firstLevelApproverName = "";
        String secondLevelApproverName = "";
        
        ArrayList<String> tableContentCols = new ArrayList<String>();
        LinkedHashMap<String, String> tableColumnDatatypes = null;
        String reqPage = (String) ADFContext.getCurrent()
                                            .getSessionScope()
                                            .get("page");
        if (reqPage.equalsIgnoreCase("ot")) {
            tableContentCols.add("Overtime Date");
            tableContentCols.add("Overtime Type");
            tableContentCols.add("Overtime Hours");
            tableContentCols.add("Calculated Hours");
            tableContentCols.add("Description");

            emailReq.setTableContentColumns(tableContentCols);


            emailReq.setDetailsQuery("select OVERTIME_DATE,OVERTIME_TYPE,OVERTIME_HOURS,CALCULATED_HOURS,MISSIONS from XXHCM_OVERTIME_DETAILS_ALL where REQ_ID=" +
                                     emailReq.getRequestId());

            tableColumnDatatypes = new LinkedHashMap<String, String>();
            tableColumnDatatypes.put("OVERTIME_DATE", "DATE");
            tableColumnDatatypes.put("OVERTIME_TYPE", "STRING");
            tableColumnDatatypes.put("OVERTIME_HOURS", "STRING");
            tableColumnDatatypes.put("CALCULATED_HOURS", "STRING");
            tableColumnDatatypes.put("MISSIONS", "STRING");
            emailReq.setTableColumnDatatypes(tableColumnDatatypes);

        }
        if(approveOrReject != null && "A".equalsIgnoreCase(approveOrReject)){
       
        
        Row[] rows = getXxQpActionHistoryTVO1().getFilteredRows("ApproverId", empId.toString());
        if(rows != null && rows.length > 0){
            approveLevel = (BigDecimal)rows[0].getAttribute("ApproveLevel");
            firstLevelApproverName = (String) rows[0].getAttribute("ApproverUserName");
            BigDecimal nextLevel = approveLevel.add(new BigDecimal(1));
            rows = getXxQpActionHistoryTVO1().getFilteredRows("ApproveLevel", nextLevel);
            if(rows != null && rows.length > 0){
                //next level approver is present.
                secondLevelApproverName = (String) rows[0].getAttribute("ApproverUserName");
                
                //sending email to employee about first level approval complete and nexi is pending
                
                String[] to = { "paas.user@salic.com" }; //TODO get logged in user email
                emailReq.setToEmail(to);
                emailReq.setToEmpName(emailReq.getEmpName());
                //                    emailReq.setToEmail((String[]) toRecepients.toArray());

                emailReq.setSubject("Your "+reqType+" request("+emailReq.getRequestNo()+") is approved from "+firstLevelApproverName+"  pending with "+secondLevelApproverName);
                emailReq.setMessage("Your <b> "+reqType+" request </b>is pending for approval from <b>"+secondLevelApproverName+" </b> with hereunder information:");
               
                
                LinkedHashMap<String, String> actionButtons = new LinkedHashMap<String, String>();
                actionButtons.put("More Info", "");
                emailReq.setActionButtons(actionButtons);
                
                Map<String, String> emailHapmap =
                    GenerateEmailTemplate.prepareEmailTemplate(emailReq, getDBTransaction());

                //Code for Sending email to employee about first level approval complete and nexi is pending
                GenerateEmailTemplate.sendFromGMail(emailReq.getToEmail(), emailHapmap.get("subject"), emailHapmap.get("body"));
                
                //To second approver
               String[] managerUsers = { "paas.user@salic.com" }; //TODO get manager email 
               String mgrUserName = secondLevelApproverName;
               emailReq.setToEmpName(mgrUserName);
               emailReq.setToEmail(managerUsers);
               emailReq.setSubject("Action required for "+ reqType +" request ("+emailReq.getRequestNo()+") of "+emailReq.getEmpName());
               emailReq.setMessage("<b> "+ reqType +
                                   " request </b> for <b>"+emailReq.getEmpName()+ "("+emailReq.getEmpId()+") </b> is pending for your approval with hereunder details:");
               actionButtons = new LinkedHashMap<String, String>();
               actionButtons.put("Approve", "");
               actionButtons.put("Reject", "");
               actionButtons.put("More Info", "");
               emailReq.setActionButtons(actionButtons);
               emailHapmap = GenerateEmailTemplate.prepareEmailTemplate(emailReq, getDBTransaction());

               //Code for Sending email for second approver
               GenerateEmailTemplate.sendFromGMail(emailReq.getToEmail(), emailHapmap.get("subject"), emailHapmap.get("body"));
                
            }
            else{
                
                String[] to = { "paas.user@salic.com" }; //TODO get logged in user email
                emailReq.setToEmail(to);
                emailReq.setToEmpName(emailReq.getEmpName());
                emailReq.setSubject("Your "+reqType+" request("+emailReq.getRequestNo()+") is approved.");
                emailReq.setMessage("Your <b> "+reqType+" request </b> is approved with hereunder information:");
                LinkedHashMap<String, String> actionButtons = new LinkedHashMap<String, String>();
                actionButtons = new LinkedHashMap<String, String>();
                actionButtons.put("More Info", "");
                emailReq.setActionButtons(actionButtons);
                Map<String, String> emailHapmap =
                    GenerateEmailTemplate.prepareEmailTemplate(emailReq, getDBTransaction());
                emailHapmap = GenerateEmailTemplate.prepareEmailTemplate(emailReq, getDBTransaction());

                //Code for Sending email for second approver
                GenerateEmailTemplate.sendFromGMail(emailReq.getToEmail(), emailHapmap.get("subject"), emailHapmap.get("body"));
                
                //TODO: fetcch FYI list and send all approvers are over code.
            }
        }
        }
        else if(approveOrReject != null && "R".equalsIgnoreCase(approveOrReject)){
            String rejectReason = ""; //TODO fetch reject reason
            String[] to = { "paas.user@salic.com" }; //TODO get logged in user email
            emailReq.setToEmail(to);
            emailReq.setToEmpName(emailReq.getEmpName());
            emailReq.setSubject("Your "+reqType+" request("+emailReq.getRequestNo()+") is rejected.");
            emailReq.setMessage("Your <b> "+reqType+" request </b> is rejected with hereunder information: <br> Reject Reason : "+rejectReason);
            LinkedHashMap<String, String> actionButtons = new LinkedHashMap<String, String>();
            actionButtons = new LinkedHashMap<String, String>();
            actionButtons.put("More Info", "");
            emailReq.setActionButtons(actionButtons);
            Map<String, String> emailHapmap =
                GenerateEmailTemplate.prepareEmailTemplate(emailReq, getDBTransaction());
            emailHapmap = GenerateEmailTemplate.prepareEmailTemplate(emailReq, getDBTransaction());

            //Code for Sending email for second approver
            GenerateEmailTemplate.sendFromGMail(emailReq.getToEmail(), emailHapmap.get("subject"), emailHapmap.get("body"));
        }
    }
    
    private String getStringBasedOnReqType(String reqType){
        String reqty = null;
        if(reqType.equalsIgnoreCase("ot"))
            return "Over Time";       
        if(reqType.equalsIgnoreCase("salary"))
            return "Salary In Advance";
        if(reqType.equalsIgnoreCase("BusinessTrip"))
            return "Business Trip";
        if(reqType.equalsIgnoreCase("BusinessTripCompletion"))
            return "Business Trip Completion";
        if(reqType.equalsIgnoreCase("edu"))
            return "Education Allowance";
        if(reqType.equalsIgnoreCase("letter"))
            return "HR Letter";
        if(reqType.equalsIgnoreCase("vacation"))
            return "Vacation Allowance";
        if(reqType.equalsIgnoreCase("house"))
            return "Housing Adavce";
        
        //house
        
        return reqty;
    }
}
