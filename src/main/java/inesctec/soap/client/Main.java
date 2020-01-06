package inesctec.soap.client;

import ch.iec.tc57._2011.schema.message.HeaderType;
import inesctec.pt.ExecuteReactivePowerSetpointStub;
import inesctec.pt.FaultMessage;
import org.apache.axis2.AxisFault;
import reactivepowersetpointmessage.iop.rd.edf.tdxassist.CreateReactivePowerSetpointDocument;
import reactivepowersetpointmessage.iop.rd.edf.tdxassist.ReactivePowerSetpointPayloadType;
import reactivepowersetpointmessage.iop.rd.edf.tdxassist.ReactivePowerSetpointRequestMessageType;
import reactivepowersetpointmessage.iop.rd.edf.tdxassist.ReactivePowerSetpointResponseMessageDocument;

import java.rmi.RemoteException;

public class Main {

    private static final String SUCCESS = "OK";

    public static void main(String[] args) throws RemoteException, FaultMessage {

        // Stub creation
        String endpoint = "http://tdxassist.ardans.fr:9090/axis2-1.6.2/services/ExecuteReactivePowerSetpoint?wsdl";
        ExecuteReactivePowerSetpointStub stub = new ExecuteReactivePowerSetpointStub(endpoint);

        try {
            // creation of the request message
            ReactivePowerSetpointRequestMessageType reactivePowerSetpointRequestMessageType =
                    ReactivePowerSetpointRequestMessageType.Factory.newInstance();
            // instantiation of required classes

            // Header
            HeaderType headerType = Helper.getHeader();
            // Payload
            ReactivePowerSetpointPayloadType payloadType = Helper.getPayload();


            // message header and payload definition
            reactivePowerSetpointRequestMessageType.setHeader(headerType);
            reactivePowerSetpointRequestMessageType.setPayload(payloadType);

            System.out.println(reactivePowerSetpointRequestMessageType);

            CreateReactivePowerSetpointDocument createReactivePowerSetpointDocument =
                    CreateReactivePowerSetpointDocument.Factory.newInstance();
            createReactivePowerSetpointDocument.setCreateReactivePowerSetpoint(reactivePowerSetpointRequestMessageType);
            System.out.println(createReactivePowerSetpointDocument);
            ReactivePowerSetpointResponseMessageDocument response =
                    stub.createReactivePowerSetpoint(createReactivePowerSetpointDocument);

            String message = response.getReactivePowerSetpointResponseMessage().getReply().getResult().toString();

            if(Main.SUCCESS.equals(message)){
                System.out.println("OK!!");
            }

        } catch (AxisFault e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
