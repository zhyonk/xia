package cn.zhyonk.handler;

import java.util.Map;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import cn.zhyonk.builder.TextBuilder;
import cn.zhyonk.entity.WechatUser;
import cn.zhyonk.rpc.api.LocalUserService;
import cn.zhyonk.wechat.service.WeixinService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author Binary Wang
 */
@Component
public class SubscribeHandler extends AbstractHandler {

    @Autowired
    private LocalUserService localUserService;

    @Autowired
    private WeixinService wxUserService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        WeixinService weixinService = (WeixinService) wxMpService;

        // 获取微信用户基本信息
        WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser(), null);

        if (userWxInfo != null) {

            WechatUser user = new WechatUser().cast(userWxInfo);
            user.setIsDel(2);
            try {
                localUserService.insert(user);
            } catch (Exception e) {
                localUserService.updateById(user);
                this.logger.info("新关注用户 OPENID 存入数据库: " + wxMessage.getFromUser());
            }
        }
        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            return new TextBuilder().build("感谢关注", wxMessage, weixinService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    protected WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage) throws Exception {
        //TODO
        //扫码进来的，带着上一个人得openid
        String[] split = wxMessage.getEventKey().split("_");
        if (split.length == 2) {
            if (wxMessage.getEventKey().split("_")[1] != null && wxMessage.getEventKey().split("_")[1].length() == 28) {
                String ziid = wxMessage.getFromUser();
                String fuid = split[1];
                if(localUserService.isRelation(fuid,ziid)){
                    WxMpKefuMessage mess = new WxMpKefuMessage();
                    mess.setMsgType(WxConsts.KefuMsgType.TEXT);
                    mess.setContent(localUserService.selectUserInfoByOpenid(fuid).getNickname() + "已经是你的上级");
                    mess.setToUser(ziid);
                    wxUserService.getKefuService().sendKefuMessage(mess);
                    return null;
                }
                try {

                    localUserService.insertOrUpdateRelation(fuid,ziid);
                    WxMpKefuMessage mess = new WxMpKefuMessage();
                    mess.setMsgType(WxConsts.KefuMsgType.TEXT);
                    mess.setContent("你通过" + localUserService.selectUserInfoByOpenid(fuid).getNickname() + "的二维码关注本公众号！");
                    mess.setToUser(ziid);
                    wxUserService.getKefuService().sendKefuMessage(mess);
                    mess.setContent(localUserService.selectUserInfoByOpenid(ziid).getNickname() + "通过您的二维码关注本平台");
                    mess.setToUser(fuid);
                    wxUserService.getKefuService().sendKefuMessage(mess);

                } catch (Exception e) {
                    e.printStackTrace();
                    //用户已经存在
                }
            }
        }

        return null;
    }

}
