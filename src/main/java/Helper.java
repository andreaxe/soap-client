import ch.iec.tc57._2011.schema.message.HeaderType;
import datatypes.iop.rd.edf.tdxassist.*;
import datatypes.iop.rd.edf.tdxassist.MeasurementUnitKindString;
import enumerations.iop.rd.edf.tdxassist.AssetTypeList;
import enumerations.iop.rd.edf.tdxassist.CodingSchemeTypeList;
import enumerations.iop.rd.edf.tdxassist.CurveTypeList;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.GDurationSpecification;
import reactivepowersetpoint_.iop.rd.edf.tdxassist.*;
import reactivepowersetpoint_.iop.rd.edf.tdxassist.EnergyProductKindString;
import reactivepowersetpoint_.iop.rd.edf.tdxassist.MarketRoleKindString;
import reactivepowersetpoint_.iop.rd.edf.tdxassist.PsrTypeString;
import reactivepowersetpointmessage.iop.rd.edf.tdxassist.ReactivePowerSetpointPayloadType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;

class Helper {

    /**
     *  HeaderType
     * @return HeaderType
     */
    static HeaderType  getHeader(){

        HeaderType headerType = HeaderType.Factory.newInstance();
        headerType.setNoun("ReactivePowerSetpoint");
        headerType.setVerb(HeaderType.Verb.CREATE);
        headerType.setSource("SoapUI");
        headerType.setMessageID("151968eb-c239-4081-a59b-00b65fc3ad78");
        headerType.setCorrelationID("3c18a1ba-be5b-46af-8651-d6e158398def");
        return headerType;
    }

    /**
     * ReactivePowerSetpointPayloadType
     * @return ReactivePowerSetpointPayloadType
     */
    static ReactivePowerSetpointPayloadType getPayload() throws InstantiationException, IllegalAccessException {

        ReactivePowerSetpointPayloadType reactivePowerSetpointPayloadType =
                ReactivePowerSetpointPayloadType.Factory.newInstance();
        ReactivePowerSetpointMarketDocumentType setpointMarketDocumentType = getPowerSetpointMarketDocument();
        reactivePowerSetpointPayloadType.setReactivePowerSetpointMarketDocument(setpointMarketDocumentType);

        return reactivePowerSetpointPayloadType;
    }

    /**
     * ReactivePowerSetpointMarketDocumentType
     * @return ReactivePowerSetpointMarketDocumentType
     */
    private static ReactivePowerSetpointMarketDocumentType getPowerSetpointMarketDocument() throws IllegalAccessException, InstantiationException {

        ReactivePowerSetpointMarketDocumentType reactivePowerSetpointMarketDocumentType =
                ReactivePowerSetpointMarketDocumentType.Factory.newInstance();

        // mRID
        reactivePowerSetpointMarketDocumentType.setMRID("");

        // Period
        ESMPDateTimeInterval esmpDateTimeInterval = ESMPDateTimeInterval.Factory.newInstance();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(2019, Calendar.DECEMBER, 25, 4, 15, 20);;
        end.set(2019, Calendar.DECEMBER, 25, 5, 15, 20);;
        esmpDateTimeInterval.setStart(start);
        esmpDateTimeInterval.setEnd(end);
        TimePeriod timePeriod = TimePeriod.Factory.newInstance();
        timePeriod.setTimeInterval(esmpDateTimeInterval);
        reactivePowerSetpointMarketDocumentType.setPeriod(timePeriod);

        // Market Participant
        MarketParticipant marketParticipant = MarketParticipant.Factory.newInstance();
        PartyIDString partyIDString = PartyIDString.Factory.newInstance();
        partyIDString.setCodingScheme(CodingSchemeTypeList.EIC);
        marketParticipant.setMRID(partyIDString);

        MarketRole marketRole = MarketRole.Factory.newInstance();
        // todo

        marketRole.setType(MarketRoleKindString.Factory.newInstance());
        marketParticipant.setMarketRole(marketRole);

        reactivePowerSetpointMarketDocumentType.setSenderMarketParticipant(marketParticipant);
        reactivePowerSetpointMarketDocumentType.setReceiverMarketParticipant(marketParticipant);

        // AssetTimeSeries
        AssetTimeSeries assetTimeSeries = AssetTimeSeries.Factory.newInstance();
        RegisteredResource registeredResource = RegisteredResource.Factory.newInstance();
        ResourceIDString resourceIDString = ResourceIDString.Factory.newInstance();
        resourceIDString.setCodingScheme(CodingSchemeTypeList.EIC);
        registeredResource.setMRID(resourceIDString);
        MktPSRType mktPSRType = MktPSRType.Factory.newInstance();


        // todo
        mktPSRType.setPsrType(PsrTypeString.Factory.newInstance());
        registeredResource.setPSRType(mktPSRType);
        assetTimeSeries.setRegisteredResource(registeredResource);
        reactivePowerSetpointMarketDocumentType.setAssetTimeSeries(assetTimeSeries);

        ReactivePowerTimeSeries reactivePowerTimeSeries = ReactivePowerTimeSeries.Factory.newInstance();
        // todo
        //CurveTypeList.Enum curveTypeList = (CurveTypeList.SEQUENTIAL_FIXED_SIZE_BLOCK;
        reactivePowerTimeSeries.setCurveType(CurveTypeString.Factory.newInstance());
        // todo
        reactivePowerTimeSeries.setProduct(EnergyProductKindString.Factory.newInstance());
        // todo

        MeasureUnit measureUnit = MeasureUnit.Factory.newInstance();
        measureUnit.addNewName();

        reactivePowerTimeSeries.setMeasurementUnit(measureUnit);
        SeriesPeriod seriesPeriod = SeriesPeriod.Factory.newInstance();

        seriesPeriod.setTimeInterval(esmpDateTimeInterval);

        GDuration gDuration = new GDuration();
        seriesPeriod.setResolution(gDuration);

        Point point = Point.Factory.newInstance();
        point.setQuantity(BigDecimal.ONE);
        point.setPosition(BigInteger.ONE);
        seriesPeriod.addNewPoint();
        seriesPeriod.setPointArray(0, point);

        reactivePowerTimeSeries.setPeriod(seriesPeriod);

        reactivePowerSetpointMarketDocumentType.setTimeSeries(reactivePowerTimeSeries);

        return reactivePowerSetpointMarketDocumentType;

    }
}
