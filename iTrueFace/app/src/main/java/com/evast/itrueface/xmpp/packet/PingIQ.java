package com.evast.itrueface.xmpp.packet;

import org.jivesoftware.smack.packet.IQ;

public class PingIQ extends IQ {

    public static final String ELEMENT = "ping";
    public static final String NAMESPACE = "urn:xmpp:ping";

    @Override
    public String getChildElementXML() {
        StringBuffer sb = new StringBuffer();
        sb.append("<").append(ELEMENT).append(" xmlns=\"").append(NAMESPACE)
                .append("\">");

        sb.append("</").append(ELEMENT).append(">");
        return sb.toString();
    }
}
