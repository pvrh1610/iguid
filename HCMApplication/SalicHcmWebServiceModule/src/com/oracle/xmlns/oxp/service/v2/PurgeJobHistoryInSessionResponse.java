
package com.oracle.xmlns.oxp.service.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="purgeJobHistoryInSessionReturn" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "purgeJobHistoryInSessionReturn" })
@XmlRootElement(name = "purgeJobHistoryInSessionResponse")
public class PurgeJobHistoryInSessionResponse {

    protected boolean purgeJobHistoryInSessionReturn;

    /**
     * Gets the value of the purgeJobHistoryInSessionReturn property.
     *
     */
    public boolean isPurgeJobHistoryInSessionReturn() {
        return purgeJobHistoryInSessionReturn;
    }

    /**
     * Sets the value of the purgeJobHistoryInSessionReturn property.
     *
     */
    public void setPurgeJobHistoryInSessionReturn(boolean value) {
        this.purgeJobHistoryInSessionReturn = value;
    }

}
