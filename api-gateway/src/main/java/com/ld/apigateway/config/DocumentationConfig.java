package com.ld.apigateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
@Primary
@Slf4j
public class DocumentationConfig implements SwaggerResourcesProvider {
    @Value("${rest.api.names}")
    private String[] apiNames;
    @Override
    public List<SwaggerResource> get() {
        ArrayList resources = new ArrayList<>();
        if(apiNames != null){
            Arrays.stream(apiNames).forEach(s->
                    //信息均可配置到配置文件
                    resources.add(swaggerResource(s, "/openapi/" + s + "/v2/api-docs","1.0.0"))
            );
        }
        return resources;
    }

    /**
     * 提取各个服务的信息
     * @param name
     * @param location
     * @param version
     * @return
     */
    private SwaggerResource swaggerResource(String name, String location, String version){
        log.info("名称：{},地址：{},版本：{}",name, location, version);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return  swaggerResource;
    }
}
