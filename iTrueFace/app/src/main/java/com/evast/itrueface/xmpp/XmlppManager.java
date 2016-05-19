package com.evast.itrueface.xmpp;


import com.evast.itrueface.bean.course.CousesVo;
import com.evast.itrueface.bean.information.TeacherVo;
import com.evast.itrueface.bean.message.CourseVo;
import com.evast.itrueface.bean.message.FriendVo;
import com.evast.itrueface.bean.message.MessageVo;
import com.evast.itrueface.xmpp.packet.QueryIq;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Registration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 72963 on 2015/12/18.
 */
public class XmlppManager {
    private static XmlppManager xmlppManager = null;
    private XMPPConnection connection = null;

    public static synchronized XmlppManager getInstance() {
        if (xmlppManager == null) {
            xmlppManager = new XmlppManager();
        }
        return xmlppManager;
    }

    /**
     * 打开连接
     */
    public void openConnection() {
        /** 未连接时，connection为null，或者未登录isAuthenticated*/
        if (connection == null || !connection.isAuthenticated()) {
            /** 配置连接*/
            ConnectionConfiguration configuration = new ConnectionConfiguration(
                    com.evast.itrueface.xmpp.ConstantsValue.SERVER_HOST, ConstantsValue.SERVER_PORT, ConstantsValue.SERVER_NAME);
            /** 设置运行断线重连*/
            configuration.setReconnectionAllowed(true);
            /** 设置安全加密模式*/
            configuration.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
            configuration.setSendPresence(true);

            configuration.setSASLAuthenticationEnabled(false);
//            configuration .setTruststorePath("/system/etc/security/cacerts.bks");
//            configuration.setTruststorePassword("123456");
//            configuration.setTruststoreType("bks");

            connection = new XMPPConnection(configuration);
            try {
                connection.connect();
            } catch (XMPPException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取连接
     *
     * @return
     */
    public XMPPConnection getConnection() {
        if (connection == null) {
            openConnection();
        }
        return connection;
    }

    /**
     * 关闭连接
     */
    public void closeConnnection() {
        if (connection != null && connection.isConnected()) {
            connection.disconnect();
            connection = null;
        }
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     * @throws XMPPException
     */
    public boolean login(String username, String password) throws XMPPException {
        if (getConnection() == null) {
            return false;
        }
        if (!connection.isAuthenticated() && getConnection().isConnected()) {
            getConnection().login(username, password);
            Presence presence = new Presence(Presence.Type.available);
            presence.setMode(Presence.Mode.available);
            return true;
        }
        return false;
    }

    /**
     * 注册账号
     *
     * @param attributesAttrs
     * @return
     */
    public IQ register(Map<String, String> attributesAttrs) {
        if (getConnection() == null) {
            return null;
        }
        Registration registration = new Registration();
        registration.setType(IQ.Type.SET);
        registration.setTo(getConnection().getServiceName());
//        registration.setUsername("evast");
//        registration.setPassword("123456");
//        registration.setAttributes(attributesAttrs);
        PacketFilter filter = new AndFilter(new PacketIDFilter(registration.getPacketID())
                , new PacketTypeFilter(IQ.class));
        PacketCollector collector = getConnection().createPacketCollector(filter);
        getConnection().sendPacket(registration);
        IQ result = (IQ) collector.nextResult(SmackConfiguration.getPacketReplyTimeout());
        collector.cancel();
        return result;
    }

    /**
     * 退出登录
     */
    public void logout() {
        if (getConnection() == null) {
            return;
        }
        Presence presence = new Presence(Presence.Type.unavailable);
        getConnection().sendPacket(presence);
        closeConnnection();
    }

    /**
     * 获取验证码
     */
    public Packet getAuthCode() {
        AuthCodePacket packet = new AuthCodePacket();
        return packet;
    }

    /**
     * 获取消息页面的数据
     */
    public List<MessageVo> getMessageData() {
        List<MessageVo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MessageVo messageVo = new MessageVo();
            messageVo.setNameStr("阿敏老师");
            messageVo.setContantStr("恩，明天见");
            messageVo.setDateStr("星期四");
            messageVo.setImageUrl("http://image.baidu.com/search/detail?ct=503316480&tn=baiduimagedetail&statnum=head&ipn=d&z=0&s=0&ic=0&lm=-1&itg=1&cg=head&word=%E5%8A%A8%E6%BC%AB%E5%A4%B4%E5%83%8F&ie=utf-8&in=3354&cl=2&st=&pn=15&rn=1&di=196497728240&ln=1000&&fmq=1378374347070_R&se=&sme=0&tab=&face=&&cs=3916835924,1757725161&simid=3327985172,236191123&adpicid=undefined&pi=0&os=771413830,456736769&istype=&ist=&jit=&objurl=http%3A%2F%2Fb.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3D973525c1d0160924dc70aa1fe13719cc%2Ff11f3a292df5e0feb8edcb965c6034a85fdf72ab.jpg&bdtype=0&gsm=6000078");
            list.add(messageVo);
        }
        return list;
    }

    /**
     * 获取联系人中的我的课堂的数据
     */
    public List<CourseVo> getContactsCourseData() {
        List<CourseVo> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CourseVo courseVo = new CourseVo();
            courseVo.setNameStr("吉他教堂（10人）");
            courseVo.setHeadImageUrl("http://down.tutu001.com/d/file/20101129/2f5ca0f1c9b6d02ea87df74fcc_560.jpg");
            list.add(courseVo);
        }
        return list;
    }

    /**
     * 获取联系人中的我的好友的数据
     */
    public List<FriendVo> getContactsFriendData() {
        List<FriendVo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FriendVo friendVo = new FriendVo();
            friendVo.setNameStr("原色空");
            friendVo.setFriendImageUrl("http://down.tutu001.com/d/file/20101129/2f5ca0f1c9b6d02ea87df74fcc_560.jpg");
            list.add(friendVo);
        }
        return list;
    }

    /**
     * 获取老师页面的数据
     *
     * @return
     */
    public List<TeacherVo> getTeacherData() {
        List<TeacherVo> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TeacherVo teacherVo = new TeacherVo();
            teacherVo.setImageUrl("http://down.tutu001.com/d/file/20101129/2f5ca0f1c9b6d02ea87df74fcc_560.jpg");
            teacherVo.setPhoneStr("13681135735");
            teacherVo.setIntroductionStr("擅长教学各个阶段素描会话，有两年以上的教学经验，喜欢零基础教学");
            teacherVo.setIsAuthentication(true);
            teacherVo.setTeacherNameStr("曾海明");
            teacherVo.setKindReward(1);
            list.add(teacherVo);
        }
        return list;
    }

    /**
     * 获取课程页面数据
     *
     * @return
     */
    public List<CousesVo> getCourseData() {
        List<CousesVo> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CousesVo cousesVo = new CousesVo();
            cousesVo.setTeacherNameStr("May");
            cousesVo.setHeadUrl("http://down.tutu001.com/d/file/20101129/2f5ca0f1c9b6d02ea87df74fcc_560.jpg");
            cousesVo.setAgeStr("5-20");
            cousesVo.setPriceStr("100元/小时");
            cousesVo.setCourseNameStr("数学");
            cousesVo.setIsAuth(true);
            cousesVo.setType(1);
            cousesVo.setIsUnsubscribe(true);
            cousesVo.setFraction(4.9f);
            cousesVo.setCount(60);
            cousesVo.setAccout(60);
            list.add(cousesVo);
        }
        return list;
    }

    /**
     * 获取收藏页面的数据
     *
     * @return
     */
    public List<CousesVo> getCollectionData() {
        List<CousesVo> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CousesVo cousesVo = new CousesVo();
            cousesVo.setTeacherNameStr("May");
            cousesVo.setHeadUrl("http://down.tutu001.com/d/file/20101129/2f5ca0f1c9b6d02ea87df74fcc_560.jpg");
            cousesVo.setAgeStr("5-20");
            cousesVo.setPriceStr("100元/小时");
            cousesVo.setCourseNameStr("数学");
            cousesVo.setIsAuth(true);
            cousesVo.setType(1);
            cousesVo.setIsUnsubscribe(true);
            cousesVo.setFraction(4.9f);
            cousesVo.setCount(60);
            cousesVo.setAccout(60);
            list.add(cousesVo);
        }
        return list;
    }

    /**
     * 获取兴趣爱好页面的top 50条
     *
     * @return
     */
    public List<CousesVo> getInterstTop() {
        List<CousesVo> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            CousesVo cousesVo = new CousesVo();
            cousesVo.setTeacherNameStr("May");
            cousesVo.setHeadUrl("http://down.tutu001.com/d/file/20101129/2f5ca0f1c9b6d02ea87df74fcc_560.jpg");
            cousesVo.setAgeStr("5-20");
            cousesVo.setPriceStr("100元/小时");
            cousesVo.setCourseNameStr("数学");
            cousesVo.setIsAuth(true);
            cousesVo.setType(1);
            cousesVo.setIsUnsubscribe(true);
            cousesVo.setFraction(4.9f);
            cousesVo.setCount(60);
            cousesVo.setAccout(60);
            list.add(cousesVo);
        }
        return list;
    }

    /**
     * 获取兴趣爱好页面的最新课程
     *
     * @return
     */
    public List<CousesVo> getInterstNowCourse() {
        List<CousesVo> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            CousesVo cousesVo = new CousesVo();
            cousesVo.setTeacherNameStr("May");
            cousesVo.setHeadUrl("http://down.tutu001.com/d/file/20101129/2f5ca0f1c9b6d02ea87df74fcc_560.jpg");
            cousesVo.setAgeStr("5-20");
            cousesVo.setPriceStr("100元/小时");
            cousesVo.setCourseNameStr("数学");
            cousesVo.setIsAuth(true);
            cousesVo.setType(1);
            cousesVo.setIsUnsubscribe(true);
            cousesVo.setFraction(4.9f);
            cousesVo.setCount(60);
            cousesVo.setAccout(60);
            list.add(cousesVo);
        }
        return list;
    }

    /**
     * Add course info plugin
     *
     * @param course
     * @return
     *
     * @author yue.wang
     */
    public boolean addCourseInfo(CourseVo course) {
        try {
            String menthodName = "addcoursesort";
            QueryIq addCourseInfoIQ = new QueryIq();
            addCourseInfoIQ.setType(IQ.Type.SET);
            addCourseInfoIQ.setTo(menthodName + "." + ConstantsValue.SERVER_NAME);
            addCourseInfoIQ.setMethodName(menthodName);
//            addCourseInfoIQ.addFields("category", course);
//            addCourseInfoIQ.addFields("title", title);
//            addCourseInfoIQ.addFields("cover", "course's photo!");
//            addCourseInfoIQ.addFields("teacherid", "course's photo!");
//            addCourseInfoIQ.addFields("price", "course's photo!");
//            addCourseInfoIQ.addFields("age", "course's photo!");
//            addCourseInfoIQ.addFields("star", "course's photo!");
//            addCourseInfoIQ.addFields("students", "course's photo!");
//            addCourseInfoIQ.addFields("address", "course's photo!");
//            addCourseInfoIQ.addFields("mode", "course's photo!");
//            addCourseInfoIQ.addFields("intro", "course's photo!");
//            addCourseInfoIQ.addFields("city", "course's photo!");
            if (connection != null) {
                connection.sendPacket(addCourseInfoIQ);
            }
//            log.I("addCourseInfo..." + myIQ.toXML());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Add course sort plugin
     *
     * @param category
     * @param title
     * @param photo
     * @return
     *
     * @author yue.wang
     */
    public boolean addCourseSort(String category, String title, String photo) {
        try {
            String menthodName = "addcoursesort";
            QueryIq addCourseSortIQ = new QueryIq();
            addCourseSortIQ.setType(IQ.Type.SET);
            addCourseSortIQ.setTo(menthodName + "." + ConstantsValue.SERVER_NAME);
            addCourseSortIQ.setMethodName(menthodName);
//            addCourseSortIQ.addFields("category", category);
//            addCourseSortIQ.addFields("title", title);
//            addCourseSortIQ.addFields("photo", photo);
            addCourseSortIQ.addFields("category", "1");
            addCourseSortIQ.addFields("title", "course's content!");
            addCourseSortIQ.addFields("photo", "course's photo!");
            if (connection != null) {
                connection.sendPacket(addCourseSortIQ);
            }
//            log.I("addCourseSort..." + myIQ.toXML());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Add city plugin
     *
     * @param pid
     * @param title
     * @param letter
     * @return
     *
     * @author yue.wang
     */
    public boolean addCity(String pid, String title, String letter) {
        try {
            String menthodName = "addcity";
            QueryIq addCityIQ = new QueryIq();
            addCityIQ.setType(IQ.Type.SET);
            addCityIQ.setTo(menthodName + "." + ConstantsValue.SERVER_NAME);
            addCityIQ.setMethodName(menthodName);
//            addCityIQ.addFields("pid", pid);
//            addCityIQ.addFields("title", title);
//            addCityIQ.addFields("letter", letter);
            addCityIQ.addFields("pid", "1");
            addCityIQ.addFields("title", "beijing-test-by-wy");
            addCityIQ.addFields("letter", "B");
            if (connection != null) {
                connection.sendPacket(addCityIQ);
            }
//            log.I("addCity..." + myIQ.toXML());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Add feedback plugin
     *
     * @param content
     * @param who
     * @return
     *
     * @author yue.wang
     */
    public boolean addFeedback(String content, String who) {
        try {
            String menthodName = "addfeedback";
            QueryIq addFeedbackIQ = new QueryIq();
            addFeedbackIQ.setType(IQ.Type.SET);
            addFeedbackIQ.setTo(menthodName + "." + ConstantsValue.SERVER_NAME);
            addFeedbackIQ.setMethodName(menthodName);
//            addFeedbackIQ.addFields("content", content);
//            addFeedbackIQ.addFields("who", who);
            addFeedbackIQ.addFields("content", "feedback content!");
            addFeedbackIQ.addFields("who", "wangyue");
            if (connection != null) {
                connection.sendPacket(addFeedbackIQ);
            }
//            log.I("addFeedback..." + myIQ.toXML());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Add tipoff plugin
     *
     * @param who
     * @param content
     * @param people
     * @return
     *
     * @author yue.wang
     */
    public boolean addTipoff(String who, String content, String people) {
        try {
            String menthodName = "addtipoff";
            QueryIq addTipoffIQ = new QueryIq();
            addTipoffIQ.setType(IQ.Type.SET);
            addTipoffIQ.setTo(menthodName + "." + ConstantsValue.SERVER_NAME);
            addTipoffIQ.setMethodName(menthodName);
//            addTipoffIQ.addFields("who", who);
//            addTipoffIQ.addFields("content", content);
//            addTipoffIQ.addFields("people", people);
            addTipoffIQ.addFields("who", "wangyue");
            addTipoffIQ.addFields("content", "tipoff content!");
            addTipoffIQ.addFields("people", "people who has been tipoff");
            if (connection != null) {
                connection.sendPacket(addTipoffIQ);
            }
//            log.I("addTipoff..." + myIQ.toXML());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
