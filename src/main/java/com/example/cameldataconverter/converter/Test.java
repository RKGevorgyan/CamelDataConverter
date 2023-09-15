package com.example.cameldataconverter.converter;

import org.apache.camel.component.xslt.TransformerFactoryConfigurationStrategy;
import org.apache.camel.component.xslt.XsltEndpoint;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;

public class Test implements TransformerFactoryConfigurationStrategy {
    @Override
    public void configure(TransformerFactory factory, XsltEndpoint endpoint) {
        factory.setAttribute(OutputKeys.OMIT_XML_DECLARATION, "yes");
    }
}
