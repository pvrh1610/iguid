package com.oracle.xmlns.oxp.service.v2;
// This source file is generated by Oracle tools.
// Contents may be subject to change.
// For reporting problems, use the following:
// Generated by Oracle JDeveloper 12c 12.2.1.2.0.1224
public class ScheduleServiceClient {
//    public static void main(String[] args) {
//        scheduleReportForHrLetter("300000005807455","EMBASSY OF  QATAR");
//    }

    public void scheduleReportForHrLetter(String empId,String letterTo,String letterToAr,String letterTemplate) {
        ScheduleService_Service scheduleService_Service = new ScheduleService_Service();
        ScheduleService scheduleService = scheduleService_Service.getScheduleService();

        // Testing runReport
        System.out.println("Testing runReport Service");
        try {

            ReportRequest req = new ReportRequest();
            req.setAttributeFormat("pdf"); //type of the report
            req.setAttributeLocale("en-US"); //Language
            req.setAttributeTemplate(letterTemplate); //Template type
            req.setReportAbsolutePath("/Custom/Human Capital Management/Human Resources/PaaS Letter/IDENTIFICATION_LTR_MAIL.xdo"); //Absolute path of the report from BIP Catalog.
            req.setSizeOfDataChunkDownload(-1); //to download all
            ParamNameValues paramList = new ParamNameValues();
            ArrayOfParamNameValue arrayOfParamNameValue = new ArrayOfParamNameValue();
            arrayOfParamNameValue.getItem().add(getArrayofparam("P_EMP_NUMBER",empId));
            arrayOfParamNameValue.getItem().add(getArrayofparam("P_EN_LETTER_TO",letterTo));
            arrayOfParamNameValue.getItem().add(getArrayofparam("P_AR_LETTER_TO",letterToAr));
            arrayOfParamNameValue.getItem().add(getArrayofparam("P_TEMPLATE",letterTemplate));
            
            paramList.setListOfParamNameValues(arrayOfParamNameValue);
            req.setParameterNameValues(paramList);

            ScheduleRequest sreq = new ScheduleRequest();
            sreq.setReportRequest(req); //Set ReportRequest
            sreq.setSaveDataOption(true);
            sreq.setBookBindingOutputOption(false);
            sreq.setJobTZ("UTC+3:00");
            sreq.setStartDate(null);
            sreq.setUseUTF8Option(true);
            sreq.setScheduleBurstringOption(true);
            sreq.setUserJobName("IDENTIFICATION_LTR_MAIL");
            String job_id = null;
            ;
            try {
                job_id = scheduleService.scheduleReport(sreq, "paas.user@salic.com", "Welc@me123");
            } catch (AccessDeniedException_Exception | InvalidParametersException_Exception |
                     OperationFailedException_Exception e) {
                e.printStackTrace();
            }
            System.out.println("job_id:" + job_id);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public ParamNameValue getArrayofparam(String paramName,String paramValue){
            ParamNameValue paramNameValue = new ParamNameValue();
            ArrayOfString arrayOfString = new ArrayOfString();
            paramNameValue.setName(paramName);
            arrayOfString.getItem().add(paramValue);
            paramNameValue.setValues(arrayOfString);        
            return paramNameValue;
        }
}
