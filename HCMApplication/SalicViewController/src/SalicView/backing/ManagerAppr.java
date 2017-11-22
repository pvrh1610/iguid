package SalicView.backing;

import SalicView.backing.Utils.ADFUtils;

import SalicView.backing.Utils.DBUtils;
import SalicView.backing.Utils.JSFUtils;

import java.math.BigDecimal;

import java.sql.SQLException;

import javax.faces.event.ActionEvent;

import oracle.adf.model.OperationBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ApplicationModuleImpl;

import oracle.jbo.server.ViewObjectImpl;

import oracle.jdbc.internal.OracleTypes;

import view.session.LoginBean;
import view.session.UserService;

public class ManagerAppr {
    private RichOutputLabel totaReq;
    private RichOutputLabel penAppr;
    private RichOutputLabel appr;
    private RichOutputLabel reje;
    private RichOutputLabel ol1;
    private RichOutputLabel ol3;
    private RichOutputLabel ol5;
    private RichOutputLabel ol7;
    private RichOutputLabel ol9;
    private RichTable t2;
    static ADFLogger logger = ADFLogger.createADFLogger(ManagerAppr.class);
    public ManagerAppr() {
    }

    public void taskFlowInitializer(){
        LoginBean usersb =
            (LoginBean) ADFUtils.evaluateEL("#{loginBean}");
        logger.log("session bean accessed ==>"+usersb);
        logger.log("person id accessed ==>"+usersb.getPersonId());
        BigDecimal empId = new BigDecimal(usersb.getPersonId());
        
                ADFContext aDFContext = ADFContext.getCurrent();
                aDFContext.getPageFlowScope().put("mempId",empId);
        
    }
    private RichInputText mgrItSearch;
    private RichTable mgrTable;

    public void onClickMgrDashSearchACL(ActionEvent actionEvent) {
        ViewObject mgrVo =
            ADFUtils.findIterator("managerDashbaordROVO1Iterator").getViewObject();
        if (mgrItSearch.getValue() != null) {
            ViewCriteria vc = mgrVo.createViewCriteria();
            ViewCriteriaRow vcr = vc.createViewCriteriaRow();
            vcr.setAttribute("RequestNumber",
                             "like %" + mgrItSearch.getValue() + "%");
            vc.addRow(vcr);
            mgrVo.applyViewCriteria(vc);
            mgrVo.executeQuery();
        } else {
            ViewCriteria vc = mgrVo.createViewCriteria();
            ViewCriteriaRow vcr = vc.createViewCriteriaRow();
            vcr.setAttribute("RequestNumber", "");
            vc.addRow(vcr);
            mgrVo.applyViewCriteria(vc);
            mgrVo.executeQuery();
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(mgrTable);
    }

    public void setMgrItSearch(RichInputText mgrItSearch) {
        this.mgrItSearch = mgrItSearch;
    }

    public RichInputText getMgrItSearch() {
        return mgrItSearch;
    }

    public void setMgrTable(RichTable mgrTable) {
        this.mgrTable = mgrTable;
    }

    public RichTable getMgrTable() {
        return mgrTable;
    }
    Object[][] dobProcArgs = null;

    public void update_approve(Object header_id, String approver_flag,
                               String req_num, String User) {
        String str = null;
        Number hdrId = null;
        String userName = User;
        try {
            hdrId = new Number(header_id);
        } catch (SQLException e) {
        }
        ApplicationModuleImpl am =
            (ApplicationModuleImpl)ADFUtils.getApplicationModuleForDataControl(null);
        dobProcArgs = new Object[][] { { "IN", hdrId, OracleTypes.NUMBER }, //0
                    { "IN", approver_flag, OracleTypes.VARCHAR }, //1
                    { "IN", userName, OracleTypes.VARCHAR }, //2
                    { "IN", "H", OracleTypes.VARCHAR }, //3
                    { "IN", "OT", OracleTypes.VARCHAR }, //4 p_page
                    { "IN", req_num, OracleTypes.VARCHAR }, //5 p_req_number
                    { "OUT", str, OracleTypes.VARCHAR } //6
                    } ;
        try {
            System.err.println("==11====");
            DBUtils.callDBStoredProcedure(am.getDBTransaction(),
                                          "call xx_boq_update_approval(?,?,?,?,?,?,?)",
                                          dobProcArgs);
            System.err.println("==22====");
        } catch (SQLException e) {
            System.err.println("===EXE==" + e.toString());
        }
        str = (String)dobProcArgs[6][1];
        System.err.println("===SEND==" + str);

    }



    public void updateApproveRejection(Object header_id, String approver_flag,
                                String User) {
        String str = null;
        Number hdrId = null;
        String userName = User;
        try {
            hdrId = new Number(header_id);
        } catch (SQLException e) {
        }
        ViewObject mgrVo =
            ADFUtils.findIterator("managerDashbaordROVO1Iterator").getViewObject();
        
        //XXSALIC_HCM_PKG.XXSALIC_APPROVAL_PRC
        //LoginBean  lb = (LoginBean)JSFUtils.getFromSession("loginBean");
        LoginBean usersb =
            (LoginBean) ADFUtils.evaluateEL("#{loginBean}");
        
        
        BigDecimal empId = new BigDecimal(usersb.getPersonId());
        BigDecimal ownerReqId =(BigDecimal)mgrVo.getCurrentRow().getAttribute("EmpId");
        String reqType = (String)mgrVo.getCurrentRow().getAttribute("ReqType");
        
        ApplicationModuleImpl am =
            (ApplicationModuleImpl)ADFUtils.getApplicationModuleForDataControl(null);
        try {
            dobProcArgs = new Object[][] {
                { "IN", approver_flag, OracleTypes.VARCHAR }, //0
                { "IN", reqType, OracleTypes.VARCHAR },
                { "IN", new oracle.jbo.domain.Number(ownerReqId), OracleTypes.NUMBER },
                { "IN", new oracle.jbo.domain.Number(empId), OracleTypes.NUMBER },
                { "IN", hdrId, OracleTypes.NUMBER }, //1 
                { "OUT", str, OracleTypes.VARCHAR }
            };
        } catch (SQLException e) {
        }
        try {
            System.err.println("==11====");
            DBUtils.callDBStoredProcedure(am.getDBTransaction(),
                                          "call XXSALIC_HCM_PKG.XXSALIC_APPROVAL_PRC(?,?,?,?,?,?)",
                                          dobProcArgs);
            System.err.println("==22====");
        } catch (SQLException e) {
            System.err.println("===EXE==" + e.toString());
        }
        
    }

    public void approveACL(ActionEvent actionEvent) {
        ViewObject mgrVO =
            ADFUtils.findIterator("managerDashbaordROVO1Iterator").getViewObject();
        //        mgrVO.getCurrentRow().getAttribute("");
        updateApproveRejection(mgrVO.getCurrentRow().getAttribute("ReqId"), "A",
                       (String)mgrVO.getCurrentRow().getAttribute("RequestNumber")
                       );
        mgrVO.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(t2);
        OperationBinding ob =
            (OperationBinding)ADFUtils.getBindingContainer().getOperationBinding("load");
        ob.execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(ol1);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ol3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ol3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ol7);
        refresh();
        
        //send emails
        oracle.binding.OperationBinding op = ADFUtils.findOperation("prepareMailTemplateAndSend");
        op.getParamsMap().put("approveOrReject", "A");
        op.execute();
        
    }

    public void rejectACL(ActionEvent actionEvent) {
        ViewObject mgrVO =
            ADFUtils.findIterator("managerDashbaordROVO1Iterator").getViewObject();
        //        mgrVO.getCurrentRow().getAttribute("");
        updateApproveRejection(mgrVO.first().getAttribute("ReqId"), "R",
                       (String)mgrVO.first().getAttribute("RequestNumber"));
        mgrVO.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(t2);
        OperationBinding ob =
            (OperationBinding)ADFUtils.getBindingContainer().getOperationBinding("load");
        ob.execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(ol1);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ol3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ol3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ol7);
        refresh();
        
        oracle.binding.OperationBinding op = ADFUtils.findOperation("prepareMailTemplateAndSend");
        op.getParamsMap().put("approveOrReject", "R");
        op.execute();
    }

    public void refresh() {
        ViewObject mgrVO =
            ADFUtils.findIterator("managerDashbaordROVO1Iterator").getViewObject();
        mgrVO.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(t2);

    }

    public void setTotaReq(RichOutputLabel totaReq) {
        System.err.println("hiiiiiiii");
        //      load();
        this.totaReq = totaReq;
    }

    public RichOutputLabel getTotaReq() {
        return totaReq;
    }

    public void load() {
        ViewObject mgrVo =
            ADFUtils.findIterator("XxQpActionHistoryTVO1Iterator1").getViewObject();
        System.err.println("total request " + mgrVo.getEstimatedRowCount());
        totaReq.setValue(mgrVo.getEstimatedRowCount());
        AdfFacesContext.getCurrentInstance().addPartialTarget(totaReq);
    }

    public void filterFromInfoletACL(ActionEvent actionEvent) {
        //        ViewObject mgrVo=ADFUtils.findIterator("XxQpActionHistoryTVO1Iterator1").getViewObject();
        //        ViewCriteria vcccc=mgrVo.createViewCriteria();
        //        ViewCriteriaRow vccccr=vcccc.createViewCriteriaRow();
        //        vccccr.setAttribute("ReqNumber", "");
        //        vcccc.addRow(vccccr);
        //        mgrVo.applyViewCriteria(vcccc);
        //        mgrVo.executeQuery();
        //        ADFContext aDFContext = ADFContext.getCurrent();
        //        aDFContext.getPageFlowScope().put("total", mgrVo.getEstimatedRowCount());
        //        AdfFacesContext.getCurrentInstance().addPartialTarget(mgrTable);
        filterEmployeeDashboardStatus("Delegate");
    }

    public void apprInfoletACL(ActionEvent actionEvent) {
        //        ViewObject mgrVo=ADFUtils.findIterator("XxQpActionHistoryTVO1Iterator1").getViewObject();
        //        ViewCriteria vc=mgrVo.createViewCriteria();
        //        ViewCriteriaRow vcr=vc.createViewCriteriaRow();
        //        vcr.setAttribute("ApproverFlag", "Y");
        //        vc.addRow(vcr);
        //        mgrVo.applyViewCriteria(vc);
        //        mgrVo.executeQuery();
        //        System.err.println("Approved request "+mgrVo.getEstimatedRowCount());
        //        ADFContext.getCurrent().getPageFlowScope().put("approved", mgrVo.getEstimatedRowCount());
        //        AdfFacesContext.getCurrentInstance().addPartialTarget(mgrTable);
        filterEmployeeDashboardStatus("APPROVE");
    }

    public void rejectInfoletACL(ActionEvent actionEvent) {
        //        ViewObject mgrVo=ADFUtils.findIterator("XxQpActionHistoryTVO1Iterator1").getViewObject();
        //        ViewCriteria vcc=mgrVo.createViewCriteria();
        //        ViewCriteriaRow vccr=vcc.createViewCriteriaRow();
        //        vccr.setAttribute("ApproverFlag", "N");
        //        vcc.addRow(vccr);
        //        mgrVo.applyViewCriteria(vcc);
        //        mgrVo.executeQuery();
        //        System.err.println("Rejected request "+mgrVo.getEstimatedRowCount());
        //         ADFContext.getCurrent().getPageFlowScope().put("rejected", mgrVo.getEstimatedRowCount());
        //        AdfFacesContext.getCurrentInstance().addPartialTarget(mgrTable);

        filterEmployeeDashboardStatus("REJECT");
    }

    public void pendingACL(ActionEvent actionEvent) {
        //        ViewObject mgrVo=ADFUtils.findIterator("XxQpActionHistoryTVO1Iterator1").getViewObject();
        //        ViewCriteria vccc=mgrVo.createViewCriteria();
        //        ViewCriteriaRow vcccr=vccc.createViewCriteriaRow();
        //        vcccr.setAttribute("ApproverFlag", "is null");
        //        vccc.addRow(vcccr);
        //        mgrVo.applyViewCriteria(vccc);
        //        mgrVo.executeQuery();
        //        System.err.println("Pending request "+mgrVo.getEstimatedRowCount());
        //        ADFContext.getCurrent().getPageFlowScope().put("pending", mgrVo.getEstimatedRowCount());
        filterEmployeeDashboardStatus("Pending Approval");
    }


    public void filterEmployeeDashboardStatus(String statusVal) {
        ViewObject employeeVO =
            ADFUtils.findIterator("managerDashbaordROVO1Iterator").getViewObject();
        ViewObjectImpl employeeVOImpl =
            (ViewObjectImpl)employeeVO.getViewObject();
        ViewCriteria employeeVC =
            employeeVOImpl.getViewCriteria("filterByStatus");
        employeeVO.applyViewCriteria(employeeVC);
        employeeVO.setNamedWhereClauseParam("p_Status", statusVal);
        employeeVO.executeQuery();
    }

    public void setPenAppr(RichOutputLabel penAppr) {
        this.penAppr = penAppr;
    }

    public RichOutputLabel getPenAppr() {
        return penAppr;
    }

    public void setAppr(RichOutputLabel appr) {
        this.appr = appr;
    }

    public RichOutputLabel getAppr() {
        return appr;
    }

    public void setReje(RichOutputLabel reje) {
        this.reje = reje;
    }

    public RichOutputLabel getReje() {
        return reje;
    }

    public void onClickTotalStatus(ActionEvent actionEvent) {
        filterEmployeeDashboardStatus(null);
    }

    public void setT2(RichTable t2) {
        this.t2 = t2;
    }

    public RichTable getT2() {
        return t2;
    }

    public void setOl1(RichOutputLabel ol1) {
        this.ol1 = ol1;
    }

    public RichOutputLabel getOl1() {
        return ol1;
    }

    public void setOl3(RichOutputLabel ol3) {
        this.ol3 = ol3;
    }

    public RichOutputLabel getOl3() {
        return ol3;
    }

    public void setOl5(RichOutputLabel ol5) {
        this.ol5 = ol5;
    }

    public RichOutputLabel getOl5() {
        return ol5;
    }

    public void setOl7(RichOutputLabel ol7) {
        this.ol7 = ol7;
    }

    public RichOutputLabel getOl7() {
        return ol7;
    }

    public void setOl9(RichOutputLabel ol9) {
        this.ol9 = ol9;
    }

    public RichOutputLabel getOl9() {
        return ol9;
    }
}
