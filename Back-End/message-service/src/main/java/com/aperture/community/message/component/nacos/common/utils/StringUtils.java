package com.aperture.community.message.component.nacos.common.utils;

import com.aperture.community.message.component.nacos.api.common.Constants;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Locale;

/**
 * @author HALOXIAO
 * @since 2020-10-28 17:26
 **/
public class StringUtils {


    public static final String DOT = ".";

    private static final int INDEX_NOT_FOUND = -1;

    public static final String COMMA = ",";

    public static final String EMPTY = "";

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String newStringForUtf8(byte[] bytes) {
        return new String(bytes, Charset.forName(Constants.ENCODE));
    }


    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }
    /**
     * Judge whether string is blank.
     *
     * @param str string
     * @return true if str is null, empty string or only blanks, otherwise false
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }

    public static String escapeJavaScript(String str) {
        return escapeJavaStyleString(str, true, true);
    }

    private static String escapeJavaStyleString(String str, boolean escapeSingleQuotes, boolean escapeForwardSlash) {
        if (str == null) {
            return null;
        }
        try {
            StringWriter writer = new StringWriter(str.length() * 2);
            escapeJavaStyleString(writer, str, escapeSingleQuotes, escapeForwardSlash);
            return writer.toString();
        } catch (IOException ioe) {
            // this should never ever happen while writing to a StringWriter
            return null;
        }
    }



    /**
     * Join object with input separator.
     *
     * @param collection collection of objects need to join
     * @param separator  separator
     * @return joined string
     */
    public static String join(Collection collection, String separator) {
        if (collection == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object[] objects = collection.toArray();
        for (int i = 0; i < collection.size() - 1; i++) {
            stringBuilder.append(objects[i].toString()).append(separator);
        }
        if (collection.size() > 0) {
            stringBuilder.append(objects[collection.size() - 1]);
        }
        return stringBuilder.toString();
    }


    private static void escapeJavaStyleString(Writer out, String str, boolean escapeSingleQuote,
                                              boolean escapeForwardSlash) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        }
        if (str == null) {
            return;
        }
        int sz;
        sz = str.length();
        for (int i = 0; i < sz; i++) {
            char ch = str.charAt(i);

            // handle unicode
            if (ch > 0xfff) {
                out.write("\\u" + hex(ch));
            } else if (ch > 0xff) {
                out.write("\\u0" + hex(ch));
            } else if (ch > 0x7f) {
                out.write("\\u00" + hex(ch));
            } else if (ch < 32) {
                switch (ch) {
                    case '\b':
                        out.write('\\');
                        out.write('b');
                        break;
                    case '\n':
                        out.write('\\');
                        out.write('n');
                        break;
                    case '\t':
                        out.write('\\');
                        out.write('t');
                        break;
                    case '\f':
                        out.write('\\');
                        out.write('f');
                        break;
                    case '\r':
                        out.write('\\');
                        out.write('r');
                        break;
                    default:
                        if (ch > 0xf) {
                            out.write("\\u00" + hex(ch));
                        } else {
                            out.write("\\u000" + hex(ch));
                        }
                        break;
                }
            } else {
                switch (ch) {
                    case '\'':
                        if (escapeSingleQuote) {
                            out.write('\\');
                        }
                        out.write('\'');
                        break;
                    case '"':
                        out.write('\\');
                        out.write('"');
                        break;
                    case '\\':
                        out.write('\\');
                        out.write('\\');
                        break;
                    case '/':
                        if (escapeForwardSlash) {
                            out.write('\\');
                        }
                        out.write('/');
                        break;
                    default:
                        out.write(ch);
                        break;
                }
            }
        }
    }

    private static String hex(char ch) {
        return Integer.toHexString(ch).toUpperCase(Locale.ENGLISH);
    }
}
