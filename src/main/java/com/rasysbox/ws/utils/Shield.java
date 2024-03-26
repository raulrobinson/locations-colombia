package com.rasysbox.ws.utils;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

/**
 * The type Shield.
 */
public class Shield {
    private Shield(){}

    /**
     * Blind str string.
     *
     * @param value the value
     * @return the string
     */
    public static String blindStr(String value) {
        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        return policy.sanitize(value)
                .replace("&#34;", "'")
                .replace("&#43;", "+")
                .replace("&#39;", "'")
                .replace("&#61;", "=")
                .replace("&amp;", " & ")
                .replace("&#64;", "@");
    }

}
