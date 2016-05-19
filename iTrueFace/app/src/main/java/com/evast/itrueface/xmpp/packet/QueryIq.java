/**
 * $RCSfile$
 * $Revision$
 * $Date$
 * <p/>
 * Copyright 2003-2007 Jive Software.
 * <p/>
 * All rights reserved. Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.evast.itrueface.xmpp.packet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jivesoftware.smack.packet.IQ;

/**
 * @author cui.li
 */
public class QueryIq extends IQ {
    private String methodName = null;
    private String nameSpace;
    private Map<String, List<Map<String, String>>> items = new HashMap<String, List<Map<String, String>>>();
    private Map<String, String> fields = new HashMap<String, String>();

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    /**
     * Returns the map of String key/value pairs of account attributes.
     *
     * @return the account attributes.
     */
    public Map<String, List<Map<String, String>>> getItems() {
        return items;
    }

    public void addItems(String key, List<Map<String, String>> list) {
        items.put(key, list);
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void addFields(String key, String values) {
        fields.put(key, values);
    }

    public String getChildElementXML() {
        StringBuilder buf = new StringBuilder();
        String methodNames = getMethodName();
        String nameSpace = getNameSpace();
        if (nameSpace == null || nameSpace.equals(""))
            nameSpace = methodNames;
        if (methodNames == null || methodNames.equals(""))
            return "";
        buf.append("<query xmlns=\"jabber:iq:" + nameSpace + "\">");
        buf.append("<x xmlns=\"jabber:x:data\" type=\"submit\">");
        buf.append("<field var=\"methodname\"  type=\"text-single\"><value>"
                + methodNames + "</value></field>");
        buf.append(" <field var=\"form_type\"  type=\"hidden\"><value>jabber:iq:"
                + nameSpace + "</value></field> ");
        if (fields != null) {
            int size = fields.size();
            if (size > 0) {
                for (String name : fields.keySet()) {
                    String value = fields.get(name);
                    buf.append(" <field var=\"" + name
                            + "\" type=\"text-single\"><value>" + value
                            + "</value></field> ");
                }
            }
        }

        buf.append(getExtensionsXML());
        buf.append("</x>");
        buf.append("</query>");
        return buf.toString();
    }
}