package gov.hhs.fha.nhinc.xdr.request.entity;

import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;
import gov.hhs.fha.nhinc.common.nhinccommon.HomeCommunityType;
import gov.hhs.fha.nhinc.common.nhinccommon.NhinTargetCommunitiesType;
import gov.hhs.fha.nhinc.common.nhinccommon.NhinTargetCommunityType;
import gov.hhs.fha.nhinc.common.nhinccommon.UrlInfoType;
import gov.hhs.fha.nhinc.common.nhinccommonentity.RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType;
import gov.hhs.fha.nhinc.nhinclib.NhincConstants;
import gov.hhs.fha.nhinc.xdr.XDRAuditLogger;
import gov.hhs.fha.nhinc.xdr.request.proxy.NhincProxyXDRRequestSecuredImpl;
import gov.hhs.healthit.nhin.XDRAcknowledgementType;
import javax.xml.ws.WebServiceContext;
import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryResponseType;
import org.apache.commons.logging.Log;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Neil Webb
 */
public class EntityXDRRequestSecuredImplTest {

    private Mockery context;

    @Before
    public void setUp() {
        context = new Mockery() {

            {
                setImposteriser(ClassImposteriser.INSTANCE);
            }
        };
    }

    @Test
    public void testProvideAndRegisterDocumentSetBRequest() {
        final Log mockLogger = context.mock(Log.class);
        final XDRAuditLogger mockAuditLogger = context.mock(XDRAuditLogger.class);
        final AssertionType mockAssertion = context.mock(AssertionType.class);
        final WebServiceContext mockWebServiceContext = context.mock(WebServiceContext.class);

        EntityXDRRequestSecuredImpl sut = new EntityXDRRequestSecuredImpl() {

            @Override
            protected XDRAuditLogger createAuditLogger() {
                return mockAuditLogger;
            }

            @Override
            protected Log createLogger() {
                return mockLogger;
            }

            @Override
            protected NhincProxyXDRRequestSecuredImpl createNhinProxy() {
                NhincProxyXDRRequestSecuredImpl mockProxy = new NhincProxyXDRRequestSecuredImpl() {

                    @Override
                    public XDRAcknowledgementType provideAndRegisterDocumentSetBRequest(gov.hhs.fha.nhinc.common.nhinccommonproxy.RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType provideAndRegisterRequestRequest, AssertionType assertion) {
                        XDRAcknowledgementType response = new XDRAcknowledgementType();
                        RegistryResponseType regResp = new RegistryResponseType();
                        regResp.setStatus(NhincConstants.XDR_ACK_STATUS_MSG);
                        response.setMessage(regResp);
                        return response;
                    }
                };
                return mockProxy;
            }

            @Override
            protected AssertionType extractAssertion(WebServiceContext context) {
                return mockAssertion;
            }

            @Override
            protected boolean checkPolicy(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request, AssertionType assertion) {
                return true;
            }

            @Override
            protected String extractMessageId(WebServiceContext context) {
                return "uuid:1111111111.11111.111.11";
            }
        };

        context.checking(new Expectations() {

            {
                allowing(mockLogger).info(with(any(String.class)));
                allowing(mockLogger).debug(with(any(String.class)));
                oneOf(mockAssertion).setAsyncMessageId(with(any(String.class)));
                oneOf(mockAuditLogger).auditEntityXDR(with(any(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType.class)), with(any(AssertionType.class)), with(any(String.class)));
                oneOf(mockAuditLogger).auditEntityAcknowledgement(with(any(XDRAcknowledgementType.class)), with(any(AssertionType.class)), with(any(String.class)), with(any(String.class)));
                will(returnValue(null));
            }
        });

        RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request = new RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType();
        NhinTargetCommunitiesType targets = new NhinTargetCommunitiesType();
        NhinTargetCommunityType target = new NhinTargetCommunityType();
        HomeCommunityType hc = new HomeCommunityType();
        hc.setHomeCommunityId("1.1");
        target.setHomeCommunity(hc);
        targets.getNhinTargetCommunity().add(target);
        request.setNhinTargetCommunities(targets);

        XDRAcknowledgementType ack = sut.provideAndRegisterDocumentSetBRequest(request, mockWebServiceContext);

        assertNotNull("Ack was null", ack);
        assertEquals("Response message incorrect", NhincConstants.XDR_ACK_STATUS_MSG, ack.getMessage().getStatus());
    }

    @Test
    public void testProvideAndRegisterDocumentSetBRequestLiftEnabled() {
        final Log mockLogger = context.mock(Log.class);
        final XDRAuditLogger mockAuditLogger = context.mock(XDRAuditLogger.class);
        final AssertionType mockAssertion = context.mock(AssertionType.class);
        final WebServiceContext mockWebServiceContext = context.mock(WebServiceContext.class);

        EntityXDRRequestSecuredImpl sut = new EntityXDRRequestSecuredImpl() {

            @Override
            protected XDRAuditLogger createAuditLogger() {
                return mockAuditLogger;
            }

            @Override
            protected Log createLogger() {
                return mockLogger;
            }

            @Override
            protected NhincProxyXDRRequestSecuredImpl createNhinProxy() {
                NhincProxyXDRRequestSecuredImpl mockProxy = new NhincProxyXDRRequestSecuredImpl() {

                    @Override
                    public XDRAcknowledgementType provideAndRegisterDocumentSetBRequest(gov.hhs.fha.nhinc.common.nhinccommonproxy.RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType provideAndRegisterRequestRequest, AssertionType assertion) {
                        XDRAcknowledgementType response = new XDRAcknowledgementType();
                        RegistryResponseType regResp = new RegistryResponseType();
                        regResp.setStatus(NhincConstants.XDR_ACK_STATUS_MSG);
                        response.setMessage(regResp);
                        return response;
                    }
                };
                return mockProxy;
            }

            @Override
            protected AssertionType extractAssertion(WebServiceContext context) {
                return mockAssertion;
            }

            @Override
            protected boolean checkPolicy(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request, AssertionType assertion) {
                return true;
            }

            @Override
            protected String extractMessageId(WebServiceContext context) {
                return "uuid:1111111111.11111.111.11";
            }

            @Override
            protected boolean checkLiftProperty() {
                return true;
            }

            @Override
            protected String generateLiFTPayload(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request, AssertionType assertion) {
                return "59139453-9e74-4a33-ba99-4105f2112e54";
            }

            @Override
            protected boolean copyFileToFileServer(String url, String destination) {
                return true;
            }

            @Override
            protected boolean doesTargetSupportLift(String hcid) {
                return true;
            }
        };

        context.checking(new Expectations() {

            {
                allowing(mockLogger).info(with(any(String.class)));
                allowing(mockLogger).debug(with(any(String.class)));
                oneOf(mockAssertion).setAsyncMessageId(with(any(String.class)));
                oneOf(mockAuditLogger).auditEntityXDR(with(any(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType.class)), with(any(AssertionType.class)), with(any(String.class)));
                oneOf(mockAuditLogger).auditEntityAcknowledgement(with(any(XDRAcknowledgementType.class)), with(any(AssertionType.class)), with(any(String.class)), with(any(String.class)));
                will(returnValue(null));
            }
        });

        RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request = new RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType();
        NhinTargetCommunitiesType targets = new NhinTargetCommunitiesType();
        NhinTargetCommunityType target = new NhinTargetCommunityType();
        HomeCommunityType hc = new HomeCommunityType();
        hc.setHomeCommunityId("1.1");
        target.setHomeCommunity(hc);
        targets.getNhinTargetCommunity().add(target);
        request.setNhinTargetCommunities(targets);
        UrlInfoType urlInfo = new UrlInfoType();
        urlInfo.setUrl("file:///C:/Temp/data.pdf");
        urlInfo.setId("Document01");
        request.setUrl(urlInfo);

        XDRAcknowledgementType ack = sut.provideAndRegisterDocumentSetBRequest(request, mockWebServiceContext);

        assertNotNull("Ack was null", ack);
        assertEquals("Response message incorrect", NhincConstants.XDR_ACK_STATUS_MSG, ack.getMessage().getStatus());
    }

    @Test
    public void testProvideAndRegisterDocumentSetBRequestLiftDisabled() {
        final Log mockLogger = context.mock(Log.class);
        final XDRAuditLogger mockAuditLogger = context.mock(XDRAuditLogger.class);
        final AssertionType mockAssertion = context.mock(AssertionType.class);
        final WebServiceContext mockWebServiceContext = context.mock(WebServiceContext.class);

        EntityXDRRequestSecuredImpl sut = new EntityXDRRequestSecuredImpl() {

            @Override
            protected XDRAuditLogger createAuditLogger() {
                return mockAuditLogger;
            }

            @Override
            protected Log createLogger() {
                return mockLogger;
            }

            @Override
            protected NhincProxyXDRRequestSecuredImpl createNhinProxy() {
                NhincProxyXDRRequestSecuredImpl mockProxy = new NhincProxyXDRRequestSecuredImpl() {

                    @Override
                    public XDRAcknowledgementType provideAndRegisterDocumentSetBRequest(gov.hhs.fha.nhinc.common.nhinccommonproxy.RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType provideAndRegisterRequestRequest, AssertionType assertion) {
                        XDRAcknowledgementType response = new XDRAcknowledgementType();
                        RegistryResponseType regResp = new RegistryResponseType();
                        regResp.setStatus(NhincConstants.XDR_ACK_STATUS_MSG);
                        response.setMessage(regResp);
                        return response;
                    }
                };
                return mockProxy;
            }

            @Override
            protected AssertionType extractAssertion(WebServiceContext context) {
                return mockAssertion;
            }

            @Override
            protected boolean checkPolicy(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request, AssertionType assertion) {
                return true;
            }

            @Override
            protected String extractMessageId(WebServiceContext context) {
                return "uuid:1111111111.11111.111.11";
            }

            @Override
            protected boolean checkLiftProperty() {
                return false;
            }

            @Override
            protected String generateLiFTPayload(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request, AssertionType assertion) {
                return "59139453-9e74-4a33-ba99-4105f2112e54";
            }

            @Override
            protected boolean copyFileToFileServer(String url, String destination) {
                return true;
            }

            @Override
            protected boolean doesTargetSupportLift(String hcid) {
                return true;
            }
        };

        context.checking(new Expectations() {

            {
                allowing(mockLogger).info(with(any(String.class)));
                allowing(mockLogger).debug(with(any(String.class)));
                oneOf(mockAssertion).setAsyncMessageId(with(any(String.class)));
                oneOf(mockAuditLogger).auditEntityXDR(with(any(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType.class)), with(any(AssertionType.class)), with(any(String.class)));
                oneOf(mockAuditLogger).auditEntityAcknowledgement(with(any(XDRAcknowledgementType.class)), with(any(AssertionType.class)), with(any(String.class)), with(any(String.class)));
                will(returnValue(null));
            }
        });

        RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request = new RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType();
        NhinTargetCommunitiesType targets = new NhinTargetCommunitiesType();
        NhinTargetCommunityType target = new NhinTargetCommunityType();
        HomeCommunityType hc = new HomeCommunityType();
        hc.setHomeCommunityId("1.1");
        target.setHomeCommunity(hc);
        targets.getNhinTargetCommunity().add(target);
        request.setNhinTargetCommunities(targets);
        UrlInfoType urlInfo = new UrlInfoType();
        urlInfo.setUrl("file://C:/Temp/data.pdf");
        urlInfo.setId("Document01");
        request.setUrl(urlInfo);

        XDRAcknowledgementType ack = sut.provideAndRegisterDocumentSetBRequest(request, mockWebServiceContext);

        assertNotNull("Ack was null", ack);
        assertEquals("Response message incorrect", NhincConstants.XDR_ACK_STATUS_MSG, ack.getMessage().getStatus());
    }

    @Test
    public void testProvideAndRegisterDocumentSetBRequestLiftCopyFailed() {
        final Log mockLogger = context.mock(Log.class);
        final XDRAuditLogger mockAuditLogger = context.mock(XDRAuditLogger.class);
        final AssertionType mockAssertion = context.mock(AssertionType.class);
        final WebServiceContext mockWebServiceContext = context.mock(WebServiceContext.class);

        EntityXDRRequestSecuredImpl sut = new EntityXDRRequestSecuredImpl() {

            @Override
            protected XDRAuditLogger createAuditLogger() {
                return mockAuditLogger;
            }

            @Override
            protected Log createLogger() {
                return mockLogger;
            }

            @Override
            protected NhincProxyXDRRequestSecuredImpl createNhinProxy() {
                NhincProxyXDRRequestSecuredImpl mockProxy = new NhincProxyXDRRequestSecuredImpl() {

                    @Override
                    public XDRAcknowledgementType provideAndRegisterDocumentSetBRequest(gov.hhs.fha.nhinc.common.nhinccommonproxy.RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType provideAndRegisterRequestRequest, AssertionType assertion) {
                        XDRAcknowledgementType response = new XDRAcknowledgementType();
                        RegistryResponseType regResp = new RegistryResponseType();
                        regResp.setStatus(NhincConstants.XDR_ACK_STATUS_MSG);
                        response.setMessage(regResp);
                        return response;
                    }
                };
                return mockProxy;
            }

            @Override
            protected AssertionType extractAssertion(WebServiceContext context) {
                return mockAssertion;
            }

            @Override
            protected boolean checkPolicy(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request, AssertionType assertion) {
                return true;
            }

            @Override
            protected String extractMessageId(WebServiceContext context) {
                return "uuid:1111111111.11111.111.11";
            }

            @Override
            protected boolean checkLiftProperty() {
                return true;
            }

            @Override
            protected String generateLiFTPayload(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request, AssertionType assertion) {
                return "59139453-9e74-4a33-ba99-4105f2112e54";
            }

            @Override
            protected boolean copyFileToFileServer(String url, String destination) {
                return false;
            }

            @Override
            protected boolean doesTargetSupportLift(String hcid) {
                return true;
            }
        };

        context.checking(new Expectations() {

            {
                allowing(mockLogger).info(with(any(String.class)));
                allowing(mockLogger).debug(with(any(String.class)));
                allowing(mockLogger).error(with("Failed to copy file to the file server"));
                oneOf(mockAssertion).setAsyncMessageId(with(any(String.class)));
                oneOf(mockAuditLogger).auditEntityXDR(with(any(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType.class)), with(any(AssertionType.class)), with(any(String.class)));
                oneOf(mockAuditLogger).auditEntityAcknowledgement(with(any(XDRAcknowledgementType.class)), with(any(AssertionType.class)), with(any(String.class)), with(any(String.class)));
                will(returnValue(null));
            }
        });

        RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request = new RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType();
        NhinTargetCommunitiesType targets = new NhinTargetCommunitiesType();
        NhinTargetCommunityType target = new NhinTargetCommunityType();
        HomeCommunityType hc = new HomeCommunityType();
        hc.setHomeCommunityId("1.1");
        target.setHomeCommunity(hc);
        targets.getNhinTargetCommunity().add(target);
        request.setNhinTargetCommunities(targets);
        UrlInfoType urlInfo = new UrlInfoType();
        urlInfo.setUrl("file://C:/Temp/data.pdf");
        urlInfo.setId("Document01");
        request.setUrl(urlInfo);

        XDRAcknowledgementType ack = sut.provideAndRegisterDocumentSetBRequest(request, mockWebServiceContext);

        assertNotNull("Ack was null", ack);
        assertEquals("Response message incorrect", "Failed to copy file to the file server", ack.getMessage().getStatus());
    }

     @Test
    public void testProvideAndRegisterDocumentSetBRequestLiftPayloadFailed() {
        final Log mockLogger = context.mock(Log.class);
        final XDRAuditLogger mockAuditLogger = context.mock(XDRAuditLogger.class);
        final AssertionType mockAssertion = context.mock(AssertionType.class);
        final WebServiceContext mockWebServiceContext = context.mock(WebServiceContext.class);

        EntityXDRRequestSecuredImpl sut = new EntityXDRRequestSecuredImpl() {

            @Override
            protected XDRAuditLogger createAuditLogger() {
                return mockAuditLogger;
            }

            @Override
            protected Log createLogger() {
                return mockLogger;
            }

            @Override
            protected NhincProxyXDRRequestSecuredImpl createNhinProxy() {
                NhincProxyXDRRequestSecuredImpl mockProxy = new NhincProxyXDRRequestSecuredImpl() {

                    @Override
                    public XDRAcknowledgementType provideAndRegisterDocumentSetBRequest(gov.hhs.fha.nhinc.common.nhinccommonproxy.RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType provideAndRegisterRequestRequest, AssertionType assertion) {
                        XDRAcknowledgementType response = new XDRAcknowledgementType();
                        RegistryResponseType regResp = new RegistryResponseType();
                        regResp.setStatus(NhincConstants.XDR_ACK_STATUS_MSG);
                        response.setMessage(regResp);
                        return response;
                    }
                };
                return mockProxy;
            }

            @Override
            protected AssertionType extractAssertion(WebServiceContext context) {
                return mockAssertion;
            }

            @Override
            protected boolean checkPolicy(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request, AssertionType assertion) {
                return true;
            }

            @Override
            protected String extractMessageId(WebServiceContext context) {
                return "uuid:1111111111.11111.111.11";
            }

            @Override
            protected boolean checkLiftProperty() {
                return true;
            }

            @Override
            protected String generateLiFTPayload(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request, AssertionType assertion) {
                return null;
            }

            @Override
            protected boolean copyFileToFileServer(String url, String destination) {
                return true;
            }

            @Override
            protected boolean doesTargetSupportLift(String hcid) {
                return true;
            }
        };

        context.checking(new Expectations() {

            {
                allowing(mockLogger).info(with(any(String.class)));
                allowing(mockLogger).debug(with(any(String.class)));
                allowing(mockLogger).error(with("Failed to generate LiFT Payload"));
                oneOf(mockAssertion).setAsyncMessageId(with(any(String.class)));
                oneOf(mockAuditLogger).auditEntityXDR(with(any(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType.class)), with(any(AssertionType.class)), with(any(String.class)));
                oneOf(mockAuditLogger).auditEntityAcknowledgement(with(any(XDRAcknowledgementType.class)), with(any(AssertionType.class)), with(any(String.class)), with(any(String.class)));
                will(returnValue(null));
            }
        });

        RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request = new RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType();
        NhinTargetCommunitiesType targets = new NhinTargetCommunitiesType();
        NhinTargetCommunityType target = new NhinTargetCommunityType();
        HomeCommunityType hc = new HomeCommunityType();
        hc.setHomeCommunityId("1.1");
        target.setHomeCommunity(hc);
        targets.getNhinTargetCommunity().add(target);
        request.setNhinTargetCommunities(targets);
        UrlInfoType urlInfo = new UrlInfoType();
        urlInfo.setUrl("file://C:/Temp/data.pdf");
        urlInfo.setId("Document01");
        request.setUrl(urlInfo);

        XDRAcknowledgementType ack = sut.provideAndRegisterDocumentSetBRequest(request, mockWebServiceContext);

        assertNotNull("Ack was null", ack);
        assertEquals("Response message incorrect", "Failed to generate LiFT Payload", ack.getMessage().getStatus());
    }

     @Test
    public void testProvideAndRegisterDocumentSetBRequestLiftNotSupportedByTarget() {
        final Log mockLogger = context.mock(Log.class);
        final XDRAuditLogger mockAuditLogger = context.mock(XDRAuditLogger.class);
        final AssertionType mockAssertion = context.mock(AssertionType.class);
        final WebServiceContext mockWebServiceContext = context.mock(WebServiceContext.class);

        EntityXDRRequestSecuredImpl sut = new EntityXDRRequestSecuredImpl() {

            @Override
            protected XDRAuditLogger createAuditLogger() {
                return mockAuditLogger;
            }

            @Override
            protected Log createLogger() {
                return mockLogger;
            }

            @Override
            protected NhincProxyXDRRequestSecuredImpl createNhinProxy() {
                NhincProxyXDRRequestSecuredImpl mockProxy = new NhincProxyXDRRequestSecuredImpl() {

                    @Override
                    public XDRAcknowledgementType provideAndRegisterDocumentSetBRequest(gov.hhs.fha.nhinc.common.nhinccommonproxy.RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType provideAndRegisterRequestRequest, AssertionType assertion) {
                        XDRAcknowledgementType response = new XDRAcknowledgementType();
                        RegistryResponseType regResp = new RegistryResponseType();
                        regResp.setStatus(NhincConstants.XDR_ACK_STATUS_MSG);
                        response.setMessage(regResp);
                        return response;
                    }
                };
                return mockProxy;
            }

            @Override
            protected AssertionType extractAssertion(WebServiceContext context) {
                return mockAssertion;
            }

            @Override
            protected boolean checkPolicy(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request, AssertionType assertion) {
                return true;
            }

            @Override
            protected String extractMessageId(WebServiceContext context) {
                return "uuid:1111111111.11111.111.11";
            }

            @Override
            protected boolean checkLiftProperty() {
                return true;
            }

            @Override
            protected String generateLiFTPayload(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request, AssertionType assertion) {
                return "59139453-9e74-4a33-ba99-4105f2112e54";
            }

            @Override
            protected boolean copyFileToFileServer(String url, String destination) {
                return true;
            }

            @Override
            protected boolean doesTargetSupportLift(String hcid) {
                return false;
            }
        };

        context.checking(new Expectations() {

            {
                allowing(mockLogger).info(with(any(String.class)));
                allowing(mockLogger).debug(with(any(String.class)));
                allowing(mockLogger).error(with("Target does not support LiFT, but a LiFT transfer was specified"));
                oneOf(mockAssertion).setAsyncMessageId(with(any(String.class)));
                oneOf(mockAuditLogger).auditEntityXDR(with(any(RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType.class)), with(any(AssertionType.class)), with(any(String.class)));
                oneOf(mockAuditLogger).auditEntityAcknowledgement(with(any(XDRAcknowledgementType.class)), with(any(AssertionType.class)), with(any(String.class)), with(any(String.class)));
                will(returnValue(null));
            }
        });

        RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType request = new RespondingGatewayProvideAndRegisterDocumentSetSecuredRequestType();
        NhinTargetCommunitiesType targets = new NhinTargetCommunitiesType();
        NhinTargetCommunityType target = new NhinTargetCommunityType();
        HomeCommunityType hc = new HomeCommunityType();
        hc.setHomeCommunityId("1.1");
        target.setHomeCommunity(hc);
        targets.getNhinTargetCommunity().add(target);
        request.setNhinTargetCommunities(targets);
        UrlInfoType urlInfo = new UrlInfoType();
        urlInfo.setUrl("file://C:/Temp/data.pdf");
        urlInfo.setId("Document01");
        request.setUrl(urlInfo);

        XDRAcknowledgementType ack = sut.provideAndRegisterDocumentSetBRequest(request, mockWebServiceContext);

        assertNotNull("Ack was null", ack);
        assertEquals("Response message incorrect", "Target does not support LiFT, but a LiFT transfer was specified", ack.getMessage().getStatus());
    }
}