package com.example.wangfj.common.utils;

import org.springframework.context.support.ResourceBundleMessageSource;
import java.util.Locale;

/**
 * <li>文件名称: 内容</li>
 * <li>文件描述: 获取国际化文件中的内容</li>
 * <li>版权所有: 版权所有©2018/4/13</li>
 * <li>公    司: 勤智数码科技股份有限公司</li>
 * <li>内容摘要: 无</li>
 * <li>其他说明:无</li>
 * <li>完成日期：2018/4/13 12:49</li>
 * <li>修改记录: 无</li>
 *
 * @author lij
 * @version 1.0.1
 * @email lij@chinawiserv.com
 */
public class MessageSourceUtil {
    private static ResourceBundleMessageSource messageSource ;

    private static void init(){
        if(messageSource == null) {
            messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("i18n/messages");
        }
    }

    public static String getMesage(String code){
        init();
//        String msg = messageSource.getMessage(code, null, BaseLang.getDefault());
        //不根据系统环境设置中英文i18n, 修改为中文 @youto
        String msg = messageSource.getMessage(code, null, Locale.CHINESE);
        return msg;
    }

}
