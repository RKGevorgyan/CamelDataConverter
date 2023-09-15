package com.example.cameldataconverter.converter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.camel.builder.ValueBuilder;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jacksonxml.JacksonXMLDataFormat;
import org.apache.camel.component.xslt.TransformerFactoryConfigurationStrategy;
import org.apache.camel.component.xslt.XsltOutput;
import org.apache.camel.model.DataFormatDefinition;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class XmlConverterRoute extends EndpointRouteBuilder {

    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<CdiCreatePersonReq>\n" +
            "    <Party>\n" +
            "        <Origin/>\n" +
            "        <SourceTimestamp>2023-09-07T15:07:17</SourceTimestamp>\n" +
            "        <SrcPartyStatus/>\n" +
            "        <SrcFullName/>\n" +
            "        <SrcFirstName>Ирина</SrcFirstName>\n" +
            "        <SrcMiddleName>Петровна</SrcMiddleName>\n" +
            "        <SrcLastName>Седова</SrcLastName>\n" +
            "        <SrcPrevFirstName/>\n" +
            "        <SrcPrevMiddleName/>\n" +
            "        <SrcPrevLastName/>\n" +
            "        <SrcGender/>\n" +
            "        <SrcBirthDate>1975-08-01</SrcBirthDate>\n" +
            "        <SrcBirthPlace/>\n" +
            "        <SrcInn/>\n" +
            "        <SrcSnils/>\n" +
            "        <SrcMaritalStatus/>\n" +
            "        <SrcChildrenCount/>\n" +
            "        <SrcDependantCount/>\n" +
            "        <SrcFamilyCount/>\n" +
            "        <SrcEducationLevel/>\n" +
            "        <SrcApplicationDate/>\n" +
            "        <SrcStartDate/>\n" +
            "        <SrcApplicationUpdateDate/>\n" +
            "        <SrcPartyType>\n" +
            "            <RecordCode>P</RecordCode>\n" +
            "        </SrcPartyType>\n" +
            "        <SourceId>\n" +
            "            <ObjectId>2426664_2426720</ObjectId>\n" +
            "        </SourceId>\n" +
            "    </Party>\n" +
            "    <ContactList>\n" +
            "        <Contact>\n" +
            "            <Origin/>\n" +
            "            <SourceTimestamp>2023-07-07T15:07:17</SourceTimestamp>\n" +
            "            <SrcContactValue>22vbn@mail.ru</SrcContactValue>\n" +
            "            <SrcMainContactFlag/>\n" +
            "            <SrcActiveFlag/>\n" +
            "            <SrcConfirmedFlag/>\n" +
            "            <SrcContactType>\n" +
            "                <RecordCode>Email</RecordCode>\n" +
            "            </SrcContactType>\n" +
            "            <SrcContactTypeDet>\n" +
            "                <RecordCode>Email</RecordCode>\n" +
            "            </SrcContactTypeDet>\n" +
            "            <ContactSourceId>\n" +
            "                <ObjectId/>\n" +
            "            </ContactSourceId>\n" +
            "            <ContactPartySourceId>\n" +
            "                <ObjectId>2426664_2426720</ObjectId>\n" +
            "            </ContactPartySourceId>\n" +
            "        </Contact>\n" +
            "        <Contact>\n" +
            "            <Origin/>\n" +
            "            <SourceTimestamp>2023-09-07T15:07:17</SourceTimestamp>\n" +
            "            <SrcContactValue>89988802815</SrcContactValue>\n" +
            "            <SrcMainContactFlag/>\n" +
            "            <SrcActiveFlag/>\n" +
            "            <SrcConfirmedFlag/>\n" +
            "            <SrcContactType>\n" +
            "                <RecordCode>Phone</RecordCode>\n" +
            "            </SrcContactType>\n" +
            "            <SrcContactTypeDet>\n" +
            "                <RecordCode>Home</RecordCode>\n" +
            "            </SrcContactTypeDet>\n" +
            "            <ContactSourceId>\n" +
            "                <ObjectId/>\n" +
            "            </ContactSourceId>\n" +
            "            <ContactPartySourceId>\n" +
            "                <ObjectId>2426644_2426677</ObjectId>\n" +
            "            </ContactPartySourceId>\n" +
            "        </Contact>\n" +
            "    </ContactList>\n" +
            "</CdiCreatePersonReq>";

    @Override
    public void configure() throws Exception {
        from("timer://foo?period=10000")
                .process(exchange -> {
                    exchange.getIn().setBody(xml);
                })
                .setHeader("modelVersion", simple("1"))
                .setHeader("modelName", simple("party"))
                .to(xslt("pipelineRequest_1.xslt"))
                .log("${body}")
                .setHeader("TOJSON", simple("true"))
                .choice()
                    .when(header("TOJSON").isEqualTo("true"))
                        .to(direct("test-1"))
                    .otherwise()
                        .to(direct("test-2"))
                .endChoice()
                .end();
    }
}
