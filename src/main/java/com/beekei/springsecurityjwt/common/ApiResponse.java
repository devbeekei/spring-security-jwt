package com.beekei.springsecurityjwt.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponse {

    private int code = ApiResponseType.SUCCESS.getCode();
    private String msg = ApiResponseType.SUCCESS.getMessage();

    public static ApiResponse error(ApiResponseType apiResponseType) {
        return new ApiResponse(apiResponseType.getCode(), apiResponseType.getMessage());
    }

    public static ApiResponse error(ApiResponseType apiResponseType, String Message) {
        return new ApiResponse(apiResponseType.getCode(), Message);
    }

    public static ApiResponse error(ApiResponseType apiResponseType, List<ReplaceString> replaceStringList) {
        String message = apiResponseType.getMessage();
        for (ReplaceString replaceString : replaceStringList) {
            message = message.replace(replaceString.getKey(), replaceString.getValue());
        }
        return new ApiResponse(apiResponseType.getCode(), message);
    }

    public static void error(ServletResponse response, ApiResponseType apiResponseType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(apiResponseType.getCode());
        httpServletResponse.getWriter().write(Objects.requireNonNull(objectMapper.writeValueAsString(ApiResponse.error(apiResponseType))));
    }

    public static void token(ServletResponse response, String token) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(ApiResponseType.SUCCESS.getCode());
        httpServletResponse.getWriter().write(Objects.requireNonNull(objectMapper.writeValueAsString(new DataApiResponse<String>(token))));
    }

    @Getter
    @AllArgsConstructor
    public static class ReplaceString {
        private final String key;
        private final String value;
    }

}
