package SalicROVO;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Dec 31 21:59:09 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FetchEmailActionLinkVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        LookupValueNameDisp {
            protected Object get(FetchEmailActionLinkVORowImpl obj) {
                return obj.getAttributeInternal(index());
            }

            protected void put(FetchEmailActionLinkVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        protected abstract Object get(FetchEmailActionLinkVORowImpl object);

        protected abstract void put(FetchEmailActionLinkVORowImpl object, Object value);

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
    public static final int LOOKUPVALUENAMEDISP = AttributesEnum.LookupValueNameDisp.index();

    /**
     * This is the default constructor (do not remove).
     */
    public FetchEmailActionLinkVORowImpl() {
    }
}

