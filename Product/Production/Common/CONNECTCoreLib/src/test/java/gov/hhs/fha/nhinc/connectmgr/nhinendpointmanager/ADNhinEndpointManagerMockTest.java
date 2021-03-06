/**
 * 
 */
package gov.hhs.fha.nhinc.connectmgr.nhinendpointmanager;

import java.util.ArrayList;
import java.util.List;

import gov.hhs.fha.nhinc.nhinclib.NhincConstants.NHIN_SERVICE_NAMES;
import gov.hhs.fha.nhinc.nhinclib.NhincConstants.UDDI_SPEC_VERSION;

/**
 * @author mweaver
 *
 */
public class ADNhinEndpointManagerMockTest extends AbstractNhinEndpointManagerMockTest {
    
    /*-----------------Setup Methods---------------*/
    
    @Override
    protected NHIN_SERVICE_NAMES getService() {
        return NHIN_SERVICE_NAMES.ADMINISTRATIVE_DISTRIBUTION;
    }
    
    /**
     * Setup expectations for 1.0 specs
     */
    @Override
    protected void set2010Expectations() {
        expectConnectionManagerCache10();        
    }

    /**
     * Setup expectations for 2.0 specs
     */
    @Override
    protected void set2011Expectations() {
        expectConnectionManagerCache20();
    }

    /**
     * Setup for both AD specs
     */
    @Override
    protected void expectConnectionManagerCacheBoth() {
        List<UDDI_SPEC_VERSION> list = new ArrayList<UDDI_SPEC_VERSION>();
        list.add(UDDI_SPEC_VERSION.SPEC_1_0);
        list.add(UDDI_SPEC_VERSION.SPEC_2_0);
        
        expectConnectionManagerCache(list);
    }


}
