package org.egorkazantsev.library;

import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

public class Utils {

    public static String getUuid(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString().replaceAll("\"", "");
    }
}
