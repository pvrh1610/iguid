package SalicROVO;

import java.math.BigDecimal;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Nov 21 10:43:51 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FilterApproverListROVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        HeaderId {
            protected Object get(FilterApproverListROVORowImpl obj) {
                return obj.getAttributeInternal(index());
            }

            protected void put(FilterApproverListROVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        ApproveLevel {
            protected Object get(FilterApproverListROVORowImpl obj) {
                return obj.getAttributeInternal(index());
            }

            protected void put(FilterApproverListROVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        ApproverId {
            protected Object get(FilterApproverListROVORowImpl obj) {
                return obj.getAttributeInternal(index());
            }

            protected void put(FilterApproverListROVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        ApproverUserName {
            protected Object get(FilterApproverListROVORowImpl obj) {
                return obj.getAttributeInternal(index());
            }

            protected void put(FilterApproverListROVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        ApproverComments {
            protected Object get(FilterApproverListROVORowImpl obj) {
                return obj.getAttributeInternal(index());
            }

            protected void put(FilterApproverListROVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        ApproverFlag {
            protected Object get(FilterApproverListROVORowImpl obj) {
                return obj.getAttributeInternal(index());
            }

            protected void put(FilterApproverListROVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Type {
            protected Object get(FilterApproverListROVORowImpl obj) {
                return obj.getAttributeInternal(index());
            }

            protected void put(FilterApproverListROVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Page {
            protected Object get(FilterApproverListROVORowImpl obj) {
                return obj.getAttributeInternal(index());
            }

            protected void put(FilterApproverListROVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        ReqNumber {
            protected Object get(FilterApproverListROVORowImpl obj) {
                return obj.getAttributeInternal(index());
            }

            protected void put(FilterApproverListROVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        ApprType {
            protected Object get(FilterApproverListROVORowImpl obj) {
                return obj.getAttributeInternal(index());
            }

            protected void put(FilterApproverListROVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        protected abstract Object get(FilterApproverListROVORowImpl object);

        protected abstract void put(FilterApproverListROVORowImpl object, Object value);

        protected int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        protected static final int firstIndex() {
            return firstIndex;
        }

        protected static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        protected static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }
    public static final int HEADERID = AttributesEnum.HeaderId.index();
    public static final int APPROVELEVEL = AttributesEnum.ApproveLevel.index();
    public static final int APPROVERID = AttributesEnum.ApproverId.index();
    public static final int APPROVERUSERNAME = AttributesEnum.ApproverUserName.index();
    public static final int APPROVERCOMMENTS = AttributesEnum.ApproverComments.index();
    public static final int APPROVERFLAG = AttributesEnum.ApproverFlag.index();
    public static final int TYPE = AttributesEnum.Type.index();
    public static final int PAGE = AttributesEnum.Page.index();
    public static final int REQNUMBER = AttributesEnum.ReqNumber.index();
    public static final int APPRTYPE = AttributesEnum.ApprType.index();

    /**
     * This is the default constructor (do not remove).
     */
    public FilterApproverListROVORowImpl() {
    }
}

