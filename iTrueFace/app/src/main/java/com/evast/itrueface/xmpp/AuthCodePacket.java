package com.evast.itrueface.xmpp;


import com.evast.itrueface.util.other.L;

import org.jivesoftware.smack.packet.IQ;

/**
 * Created by 72963 on 2015/12/20.
 */
public class AuthCodePacket extends IQ {
    public static final String ELEMENT_NAME = "iTureFace";
    public static final String NAMESPACE = "iTureFace";

    private String phone;
    private int requestType;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }


    @Override
    public String getChildElementXML() {
        StringBuffer buf = new StringBuffer();
//        buf.append("<" + ELEMENT_NAME + " xmlns=" + NAMESPACE + ">");
//        if (getType() == IQ.Type.GET) {
//            buf.append("<phone>").append(phone).append("</phone>");
//            buf.append("<type>").append(requestType).append("</type>");
//            buf.append(getExtensionsXML());
//        }
//        buf.append("</" + ELEMENT_NAME + ">");
        buf.append("<iq id=\"u0rx1-89\" to=\"xmpp.itrueface.com\" type=\"set\">\n" +
                "\t<query xmlns=\"jabber:iq:checkcode\">\n" +
                "\t\t<x xmlns=\"jabber:x:data\" type=\"submit\">\n" +
                "\t\t\t<methodname>checkcode</methodname>>\n" +
                "\t\t\t<form_type>jabber:iq:checkcode</form_type>\n" +
                "\t\t\t<phone>18270839376</phone>\n" +
                "\t\t\t<checkcode></checkcode>\n" +
                "\t\t\t<type>0</type>\n" +
                "\t\t\t<date>2016-01-13 19:58:30</date>\n" +
                "\t\t</x>\n" +
                "\t</query>\n" +
                "</iq>");
        L.e(buf.toString());
        return buf.toString();
    }
}
