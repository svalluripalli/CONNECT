/*
 * Copyright (c) 2014, United States Government, as represented by the Secretary of Health and Human Services.
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
package gov.hhs.fha.nhinc.corex12.docsubmission.genericbatch.response.adapter;

import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;
import gov.hhs.fha.nhinc.largefile.LargeFileUtils;
import gov.hhs.fha.nhinc.util.Base64Coder;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.log4j.Logger;
import org.caqh.soap.wsdl.corerule2_2_0.COREEnvelopeBatchSubmission;
import org.caqh.soap.wsdl.corerule2_2_0.COREEnvelopeBatchSubmissionResponse;

/**
 * @author svalluripalli
 *
 */
public class AdapterCORE_X12DSGenericBatchResponseOrchImpl {

    private static final Logger LOG = Logger.getLogger(AdapterCORE_X12DSGenericBatchResponseOrchImpl.class);

    /**
     *
     * @param msg
     * @param assertion
     * @return
     */
    public COREEnvelopeBatchSubmissionResponse batchSubmitTransaction(COREEnvelopeBatchSubmission msg, AssertionType assertion) {

        COREEnvelopeBatchSubmissionResponse oResponse = null;
        if (msg != null) {
            LOG.trace("Begin AdapterCORE_X12DSGenericBatchResponseOrchImpl.batchSubmitTransaction()");
            //Call to a method which builds response metadata and returns response
            oResponse = buildAdapterCORE_X12DSGenericBatchResponseMetadata();
            //Call for logging inbound
            logAdapterCORE_X12DSGenericBatchRequest(msg);
            LOG.trace("End AdapterCORE_X12DSGenericBatchResponseOrchImpl.batchSubmitTransaction()");
        } else {
            oResponse = new COREEnvelopeBatchSubmissionResponse();
            //TODO: Need to add error handling
        }
        return oResponse;
    }

    private COREEnvelopeBatchSubmissionResponse buildAdapterCORE_X12DSGenericBatchResponseMetadata() {

        String str_payload = "<xop:Include href=\"cid:1.urn:uuid:5117AAE1116EA8B87A1200060184692@apache.org\"\n"
                + "xmlns:xop=\"http://www.w3.org/2004/08/xop/include\"/>";
        byte[] payload = str_payload.getBytes();
        Date currentDate = new Date();

        COREEnvelopeBatchSubmissionResponse oResponse = new COREEnvelopeBatchSubmissionResponse();
        oResponse.setPayloadType("X12_BatchReceiptConfirmation");
        oResponse.setProcessingMode("Batch");
        oResponse.setPayloadID("f81d4fae-7dec-11d0-a765-00a0c91e6bf6");
        oResponse.setPayloadLength(1551254);
        oResponse.setTimeStamp(new Timestamp(currentDate.getTime()).toString());
        oResponse.setSenderID("PayerB");
        oResponse.setReceiverID("HospitalA");
        oResponse.setCORERuleVersion("2.2.0");
        oResponse.setPayload(LargeFileUtils.getInstance().convertToDataHandler(payload));
        oResponse.setErrorCode("Success");
        oResponse.setErrorMessage("None");
        return oResponse;
    }

    private void logAdapterCORE_X12DSGenericBatchRequest(COREEnvelopeBatchSubmission request) {
        LOG.info("Generich Batch Response Paylod Type = " + request.getPayloadType());
        LOG.info("Generich Batch Response Processing Mode = " + request.getProcessingMode());
        LOG.info("Generich Batch Response Payload Id = " + request.getPayloadID());
        LOG.info("Generich Batch Response TimeStamp = " + request.getTimeStamp());
        LOG.info("Generich Batch Response Sender Id = " + request.getSenderID());
        LOG.info("Generich Batch Response Receiver Id = " + request.getReceiverID());
        LOG.info("Generich Batch Response Rule version = " + request.getCORERuleVersion());
        LOG.info("Generich Batch Response Payload = " + request.getPayload());
    }
}
