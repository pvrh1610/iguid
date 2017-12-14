package SalicView;

import java.math.BigDecimal;
import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Dec 14 08:37:33 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxhcmPurposeOfTrvl_VOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public XxhcmPurposeOfTrvl_VOImpl() {
    }

    /**
     * Returns the variable value for bindReqId.
     * @return variable value for bindReqId
     */
    public BigDecimal getbindReqId() {
        return (BigDecimal) ensureVariableManager().getVariableValue("bindReqId");
    }

    /**
     * Sets <code>value</code> for variable bindReqId.
     * @param value value to bind as bindReqId
     */
    public void setbindReqId(BigDecimal value) {
        ensureVariableManager().setVariableValue("bindReqId", value);
    }

    /**
     * Insert new Rows at the end of RowSet.
     * @param row
     */
    @Override
    public void insertRow(Row row) {
        //go to the end of Rowset if it has rows
        Row lastRow = this.last();
        if (lastRow != null) {
            //insert new row at the end and make it current
            int indx = this.getRangeIndexOf(lastRow) + 1;
            this.insertRowAtRangeIndex(indx, row);
            this.setCurrentRow(row);
        } else { // empty Rowset
            super.insertRow(row);
        }
    }

}


