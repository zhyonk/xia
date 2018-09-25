package cn.zhyonk.handler;

import java.io.File;
import java.util.Map;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import cn.zhyonk.common.utils.StringUtils;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 
 * @author Binary Wang
 *
 */
@Component
public class LogHandler extends AbstractHandler {
  private static final ObjectMapper JSON = new ObjectMapper();
  static {
    JSON.setSerializationInclusion(Include.NON_NULL);
    JSON.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
  }

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
      Map<String, Object> context, WxMpService wxMpService,
      WxSessionManager sessionManager) {

    String event = wxMessage.getEvent();
    if(StringUtils.equals(event, WxConsts.EventType.CLICK)){
      String openid = wxMessage.getFromUser();
      int time = 60*60*24*7;
      try {
        WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateTmpTicket(openid,time);
        File qrcodeUrl = wxMpService.getQrcodeService().qrCodePicture(ticket);
        WxMpKefuMessage mess = new WxMpKefuMessage();
        WxMediaUploadResult result = wxMpService.getMaterialService().mediaUpload(WxConsts.MediaFileType.IMAGE, qrcodeUrl);
        String mediaId = result.getMediaId();
        mess.setMsgType(WxConsts.KefuMsgType.IMAGE);
        mess.setMediaId(mediaId);
        mess.setToUser(openid);
        wxMpService.getKefuService().sendKefuMessage(mess);
        logger.info("\n为"+openid+"生成二维码");
      } catch (WxErrorException e) {
        e.printStackTrace();
      }
    }

    try {
      this.logger.info("\n接收到请求消息，内容：{}", JSON.writeValueAsString(wxMessage));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return null;
  }

}
