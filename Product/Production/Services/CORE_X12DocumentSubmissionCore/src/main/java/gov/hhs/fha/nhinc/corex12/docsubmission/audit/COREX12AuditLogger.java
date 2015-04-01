/*
 * Copyright (c) 2009-2015, United States Government, as represented by the Secretary of Health and Human Services. 
 * All rights reserved. 
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met: 
 *     * Redistributions of source code must retain the above 
 *       copyright notice, this list of conditions and the following disclaimer. 
 *     * Redistributions in binary form must reproduce the above copyright 
 *       notice, this list of conditions and the following disclaimer in the documentation 
 *       and/or other materials provided with the distribution. 
 *     * Neither the name of the United States Government nor the 
 *       names of its contributors may be used to endorse or promote products 
 *       derived from this software without specific prior written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE UNITED STATES GOVERNMENT BE LIABLE FOR ANY 
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND 
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */
package gov.hhs.fha.nhinc.corex12.docsubmission.audit;

import gov.hhs.fha.nhinc.auditrepository.nhinc.proxy.AuditRepositoryProxy;
import gov.hhs.fha.nhinc.auditrepository.nhinc.proxy.AuditRepositoryProxyObjectFactory;
import gov.hhs.fha.nhinc.common.auditlog.LogEventRequestType;
import gov.hhs.fha.nhinc.common.nhinccommon.AcknowledgementType;
import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;
import gov.hhs.fha.nhinc.common.nhinccommon.NhinTargetSystemType;
import org.apache.log4j.Logger;
import org.caqh.soap.wsdl.corerule2_2_0.COREEnvelopeBatchSubmission;
import org.caqh.soap.wsdl.corerule2_2_0.COREEnvelopeBatchSubmissionResponse;
import org.caqh.soap.wsdl.corerule2_2_0.COREEnvelopeRealTimeRequest;
import org.caqh.soap.wsdl.corerule2_2_0.COREEnvelopeRealTimeResponse;

/**
 *
 * @author svalluripalli
 */
public class COREX12AuditLogger {

    private static final Logger LOG = Logger.getLogger(COREX12AuditLogger.class);
    private COREX12AuditRepositoryLogger auditLogger = new COREX12AuditRepositoryLogger();

    /**
     *
     * @param message
     * @param assertion
     * @param target
     * @param direction
     */
    public void auditNhinCoreX12RealtimeRequest(COREEnvelopeRealTimeRequest message, AssertionType assertion, NhinTargetSystemType target,
        String direction) {
        LOG.trace("---Begin COREX12AuditLogger.auditCoreX12RealtimeRequest()---");
        // Set up the audit logging request message
        LogEventRequestType auditLogMsg = auditLogger.logNhinCoreX12RealtimeRequest(message, assertion, target, direction);
        if (auditLogMsg != null && auditLogMsg.getAuditMessage() != null) {
            audit(auditLogMsg, assertion);
        } else {
            LOG.error("Core X12 Realtime Request auditLogMsg is null");
        }
        LOG.trace("---End COREX12AuditLogger.auditCoreX12RealtimeRequest()---");
    }

    /**
     *
     * @param message
     * @param assertion
     * @param target
     * @param direction
     */
    public void auditNhinCoreX12RealtimeRespponse(COREEnvelopeRealTimeResponse message, AssertionType assertion, NhinTargetSystemType target,
        String direction, boolean isRequesting) {
        LOG.trace("---Begin COREX12AuditLogger.auditCoreX12RealtimeRespponse()---");
        // Set up the audit logging request message        
        LogEventRequestType auditLogMsg = auditLogger.logNhinCoreX12RealtimeResponse(message, assertion, target, direction, direction, isRequesting);
        if (auditLogMsg != null && auditLogMsg.getAuditMessage() != null) {
            audit(auditLogMsg, assertion);
        } else {
            LOG.error("Core X12 Realtime Respponse auditLogMsg is null");
        }
        LOG.trace("---End COREX12AuditLogger.auditCoreX12RealtimeRespponse()---");
    }

    /**
     *
     * @param message
     * @param assertion
     * @param target
     * @param direction
     */
    public void auditAdapterCoreX12RealtimeRequest(COREEnvelopeRealTimeRequest message, AssertionType assertion, NhinTargetSystemType target,
        String direction) {
        LOG.trace("---Begin COREX12AuditLogger.auditAdapterCoreX12RealtimeRequest()---");
        // Set up the audit logging request message        
        LogEventRequestType auditLogMsg = auditLogger.logAdapterCoreX12RealtimeRequest(message, assertion, target, direction);
        if (auditLogMsg != null && auditLogMsg.getAuditMessage() != null) {
            audit(auditLogMsg, assertion);
        } else {
            LOG.error("Core X12 Realtime Request auditLogMsg is null");
        }
        LOG.trace("---End COREX12AuditLogger.auditAdapterCoreX12RealtimeRequest()---");
    }

    /**
     *
     * @param message
     * @param assertion
     * @param target
     * @param direction
     */
    public void auditAdapterCoreX12RealtimeRespponse(COREEnvelopeRealTimeResponse message, AssertionType assertion, NhinTargetSystemType target,
        String direction, boolean isRequesting) {
        LOG.trace("---Begin COREX12AuditLogger.audiAdaptertCoreX12RealtimeRespponse()---");
        // Set up the audit logging request message        
        LogEventRequestType auditLogMsg = auditLogger.logAdapterCoreX12RealtimeResponse(message, assertion, target, direction, direction, isRequesting);
        if (auditLogMsg != null && auditLogMsg.getAuditMessage() != null) {
            audit(auditLogMsg, assertion);
        } else {
            LOG.error("Core X12 Realtime Respponse auditLogMsg is null");
        }
        LOG.trace("---End COREX12AuditLogger.auditAdapterCoreX12RealtimeRespponse()---");
    }

    /**
     *
     * @param message
     * @param assertion
     * @param target
     * @param direction
     */
    public void auditNhinCoreX12BatchRequest(COREEnvelopeBatchSubmission message, AssertionType assertion, NhinTargetSystemType target,
        String direction) {
        LOG.trace("---Begin COREX12AuditLogger.auditNhinCoreX12BatchRequest()---");
        LogEventRequestType auditLogMsg = auditLogger.logNhinCoreX12BatchRequest(message, assertion, target, direction);
        if (auditLogMsg != null && auditLogMsg.getAuditMessage() != null) {
            audit(auditLogMsg, assertion);
        } else {
            LOG.error("Core X12 Nhin Batch Request auditLogMsg is null");
        }
        LOG.trace("---End COREX12AuditLogger.auditNhinCoreX12BatchRequest()---");
    }

    /**
     *
     * @param message
     * @param assertion
     * @param target
     * @param direction
     * @param isRequesting
     */
    public void auditNhinCoreX12BatchRespponse(COREEnvelopeBatchSubmissionResponse message, AssertionType assertion, NhinTargetSystemType target,
        String direction, boolean isRequesting) {
        LOG.trace("---Begin COREX12AuditLogger.auditNhinCoreX12BatchRespponse()---");
        LogEventRequestType auditLogMsg = auditLogger.logNhinCoreX12BatchResponse(message, assertion, target, direction, direction, isRequesting);
        if (auditLogMsg != null && auditLogMsg.getAuditMessage() != null) {
            audit(auditLogMsg, assertion);
        } else {
            LOG.error("Core X12 Nhin Batch Response auditLogMsg is null");
        }
        LOG.trace("---End COREX12AuditLogger.auditNhinCoreX12BatchRespponse()---");
    }

    /**
     *
     * @param message
     * @param assertion
     * @param target
     * @param direction
     */
    public void auditAdapterCoreX12BatchRequest(COREEnvelopeBatchSubmission message, AssertionType assertion, NhinTargetSystemType target,
        String direction) {
        LOG.trace("---Begin COREX12AuditLogger.auditAdapterCoreX12BatchRequest()---");
        LogEventRequestType auditLogMsg = auditLogger.logAdapterCoreX12BatchRequest(message, assertion, target, direction, direction, Boolean.TRUE);
        if (auditLogMsg != null && auditLogMsg.getAuditMessage() != null) {
            audit(auditLogMsg, assertion);
        } else {
            LOG.error("Core X12 Adapter Batch Request auditLogMsg is null");
        }
        LOG.trace("---End COREX12AuditLogger.auditAdapterCoreX12BatchRequest()---");
    }

    /**
     *
     * @param message
     * @param assertion
     * @param target
     * @param direction
     * @param isRequesting
     */
    public void auditAdapterCoreX12BatchRespponse(COREEnvelopeBatchSubmissionResponse message, AssertionType assertion, NhinTargetSystemType target,
        String direction, boolean isRequesting) {
        LOG.trace("---Begin COREX12AuditLogger.auditAdapterCoreX12BatchRespponse()---");
        LogEventRequestType auditLogMsg = auditLogger.logAdapterCoreX12BatchResponse(message, assertion, target, direction, direction, isRequesting);
        if (auditLogMsg != null && auditLogMsg.getAuditMessage() != null) {
            audit(auditLogMsg, assertion);
        } else {
            LOG.error("Core X12 Adapter Batch Response auditLogMsg is null");
        }
        LOG.trace("---End COREX12AuditLogger.auditAdapterCoreX12BatchRespponse()---");
    }

    /**
     * Submits a generic Audit Log message to the Audit Log Repository.
     *
     * @param auditLogMsg The generic audit log to be audited
     * @param assertion The assertion to be audited
     * @return
     */
    private AcknowledgementType audit(LogEventRequestType auditLogMsg, AssertionType assertion) {
        AuditRepositoryProxyObjectFactory auditRepoFactory = new AuditRepositoryProxyObjectFactory();
        AuditRepositoryProxy proxy = auditRepoFactory.getAuditRepositoryProxy();
        return proxy.auditLog(auditLogMsg, assertion);
    }
}