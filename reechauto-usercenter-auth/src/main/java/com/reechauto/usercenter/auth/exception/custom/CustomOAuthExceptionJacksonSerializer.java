package com.reechauto.usercenter.auth.exception.custom;

import java.io.IOException;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomOAuthExceptionJacksonSerializer extends StdSerializer<CustomOAuth2Exception> {

	private static final long serialVersionUID = 1L;

	protected CustomOAuthExceptionJacksonSerializer() {
        super(CustomOAuth2Exception.class);
    }

    @Override
    public void serialize(CustomOAuth2Exception value, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeObjectField("code", value.getHttpErrorCode());
        String errorMessage = value.getOAuth2ErrorCode();
        if (errorMessage != null) {
            errorMessage = HtmlUtils.htmlEscape(errorMessage);
        }
        jgen.writeStringField("message", errorMessage);
        if (value.getAdditionalInformation()!=null) {
        	jgen.writeObjectField("data", value.getAdditionalInformation());
        }
        jgen.writeEndObject();
    }
}
