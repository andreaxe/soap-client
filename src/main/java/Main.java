import ch.iec.tc57._2011.schema.message.HeaderType;
import inesctec.pt.ExecuteReactivePowerSetpointStub;
import inesctec.pt.FaultMessage;
import org.apache.axis2.AxisFault;
import reactivepowersetpointmessage.iop.rd.edf.tdxassist.CreateReactivePowerSetpointDocument;
import reactivepowersetpointmessage.iop.rd.edf.tdxassist.ReactivePowerSetpointPayloadType;
import reactivepowersetpointmessage.iop.rd.edf.tdxassist.ReactivePowerSetpointRequestMessageType;
import reactivepowersetpointmessage.iop.rd.edf.tdxassist.ReactivePowerSetpointResponseMessageDocument;

import java.rmi.RemoteException;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) throws RemoteException, FaultMessage {

        // Stub creation
        ExecuteReactivePowerSetpointStub stub = new ExecuteReactivePowerSetpointStub();

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

            System.out.println(response.getReactivePowerSetpointResponseMessage().getReply().getResult());

        } catch (AxisFault e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
