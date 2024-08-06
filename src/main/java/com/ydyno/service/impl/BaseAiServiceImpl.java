package com.ydyno.service.impl;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.ydyno.config.OpenAiConfig;
import com.ydyno.service.BaseAiService;
import com.ydyno.service.dto.BaseAiRequest;
import com.ydyno.utils.AiTypeHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class BaseAiServiceImpl implements BaseAiService {

    @Autowired
    private OpenAiConfig openAiConfig;
    @Autowired
    private AiTypeHelper aiTypeHelper;
    @Override
    public String communicate(BaseAiRequest req) {
        return textArk(req);
    }

    private String textArk(BaseAiRequest request) {
        String apiUrl = aiTypeHelper.getApiUrl(request.getApiType());
        if (StringUtils.hasText(apiUrl)) {
            return HttpRequest.post(apiUrl)
                    .header(Header.CONTENT_TYPE, "application/json")
                    .header(Header.AUTHORIZATION, "Bearer " + aiTypeHelper.getApiKey(request.getApiType()))
                    .body(JSONUtil.toJsonStr(request))
                    .execute().body();
        }
        return "apiType 不存在";
    }
}
