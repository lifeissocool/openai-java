package com.ydyno.utils;

import com.ydyno.config.OpenAiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class AiTypeHelper {
    @Autowired
    OpenAiConfig openAiConfig;
    private   Map<String,String> apiKeyMap;
    private  Map<String,String> apiUrl;

    private final String BAICHUAN="baichuan";
    private final String BAIDU="baidu";
    private final String ZHIPU="zhipu";
    private final String ALIBABA="alibaba";
    private final String MOONSHOT="moonshot";
    @PostConstruct
    public void init()
    {
        apiKeyMap=new HashMap<>();
        apiKeyMap.put(BAICHUAN, openAiConfig.getBaichuanApiKey());
        apiKeyMap.put(BAIDU, openAiConfig.getBaiduApiKey());
        apiKeyMap.put(ZHIPU, openAiConfig.getZhipuApiKey());
        apiKeyMap.put(ALIBABA, openAiConfig.getAliApiKey());
        apiKeyMap.put(MOONSHOT, openAiConfig.getMoonshotApiKey());
        //map 初始化
        apiUrl=new HashMap<String,String>(){{
            put(BAICHUAN,"https://api.baichuan-ai.com/v1/chat/completions");
            put(BAIDU,"https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions?access_token=");
            put(ZHIPU, "https://open.bigmodel.cn/api/paas/v4/chat/completions");
            put(ALIBABA,"https://dashscope.aliyuncs.com/compatible-mode/v1/chat/completions");
            put(MOONSHOT,"https://api.moonshot.cn/v1/chat/completions");
        }};
    }
    public String getApiKey(String apiType)
    {
        return apiKeyMap.get(apiType);
    }
    public String getApiUrl(String apiType) {
        return apiUrl.get(apiType);
    }
}
