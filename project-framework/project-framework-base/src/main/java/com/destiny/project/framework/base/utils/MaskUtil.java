package com.destiny.project.framework.base.utils;

import com.destiny.project.framework.base.enums.MaskTypeEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class MaskUtil {
    private static final char SEPARATOR_CHAR_ASTERISK = '*';
    private static final char EMAIL_CHAR = '@';
    private static final Integer BASE64_MAX_LEN = 100;
    private static final Logger logger = LoggerFactory.getLogger(MaskUtil.class);
    private static final String ALL_ASTERISK = "******";

    public MaskUtil() {
    }

    public static String nameMask(String name) {
        if (StringUtil.isBlank(name)) {
            return "";
        } else {
            try {
                int begin = 1;
                int len = name.length();
                int maskLen;
                if (len == 2) {
                    maskLen = 1;
                } else {
                    maskLen = len - 2;
                }

                return mask(name, begin, begin + maskLen);
            } catch (Exception var4) {
                logger.error("字符掩码异常: {}", var4);
                return name;
            }
        }
    }

    public static String certMask(String cert) {
        if (StringUtil.isBlank(cert)) {
            return "";
        } else {
            try {
                int begin = 3;
                int len = cert.length();
                byte disPlayLen;
                if (len == 15) {
                    disPlayLen = 2;
                } else {
                    disPlayLen = 2;
                }

                int maskLen = len - begin - disPlayLen;
                return mask(cert, begin, begin + maskLen);
            } catch (Exception var5) {
                logger.error("字符掩码异常: {}", var5);
                return cert;
            }
        }
    }

    public static String bankCardNoMask(String bankCardNo) {
        if (StringUtil.isBlank(bankCardNo)) {
            return "";
        } else {
            try {
                int begin = 5;
                int disPlayLen = 4;
                int len = bankCardNo.length();
                int maskLen = len - begin - disPlayLen;
                return mask(bankCardNo, begin, begin + maskLen);
            } catch (Exception var5) {
                logger.error("字符掩码异常: {}", var5);
                return bankCardNo;
            }
        }
    }

    public static String mobileMask(String mobile) {
        if (mobile == null) {
            return "";
        } else {
            try {
                int begin = 3;
                int disPlayLen = 4;
                int len = mobile.length();
                int maskLen = len - begin - disPlayLen;
                return mask(mobile, begin, begin + maskLen);
            } catch (Exception var5) {
                logger.error("字符掩码异常: {}", var5);
                return mobile;
            }
        }
    }

    public static String telPhoneMask(String telPhone) {
        if (StringUtil.isBlank(telPhone)) {
            return null;
        } else {
            try {
                int disPlayLen = 4;
                int len = telPhone.length();
                return mask(telPhone, len - disPlayLen, len);
            } catch (Exception var3) {
                logger.error("字符掩码异常: {}", var3);
                return telPhone;
            }
        }
    }

    public static String emailMask(String email) {
        if (StringUtil.isBlank(email)) {
            return "";
        } else {
            try {
                int index = StringUtils.indexOf(email, '@');
                return index <= 1 ? email : StringUtils.rightPad(StringUtils.left(email, 1), index, '*').concat(StringUtils.mid(email, index, StringUtils.length(email)));
            } catch (Exception var2) {
                logger.error("字符掩码异常: {}", var2);
                return email;
            }
        }
    }

    public static String addrMask(String addr) {
        if (StringUtil.isBlank(addr)) {
            return "";
        } else {
            try {
                int length = StringUtils.length(addr);
                return StringUtils.rightPad(StringUtils.left(addr, 6), length, '*');
            } catch (Exception var2) {
                logger.error("字符掩码异常: {}", var2);
                return addr;
            }
        }
    }

    public static String captchaMask(String captcha) {
        if (StringUtil.isBlank(captcha)) {
            return "";
        } else {
            try {
                int length = StringUtils.length(captcha);
                int lastLength = length - 1;
                return StringUtils.rightPad(StringUtils.left(captcha, 1), lastLength, '*').concat(StringUtils.mid(captcha, lastLength, StringUtils.length(captcha)));
            } catch (Exception var3) {
                logger.error("字符掩码异常: {}", var3);
                return captcha;
            }
        }
    }

    public static String creditCodeMask(String creditCode) {
        if (StringUtil.isBlank(creditCode)) {
            return "";
        } else {
            try {
                int length = StringUtils.length(creditCode);
                int lastLength = length - 2;
                return StringUtils.rightPad(StringUtils.left(creditCode, 3), lastLength, '*').concat(StringUtils.mid(creditCode, lastLength, StringUtils.length(creditCode)));
            } catch (Exception var3) {
                logger.error("字符掩码异常: {}", var3);
                return creditCode;
            }
        }
    }

    public static String splitBase64(String base64) {
        if (StringUtil.isBlank(base64)) {
            return null;
        } else {
            int len = base64.length();
            return len > BASE64_MAX_LEN ? base64.substring(0, BASE64_MAX_LEN) + "..." : base64;
        }
    }

    public static String mask(String str, int beginIndex, int endIndex) {
        if (str != null && str.length() != 0) {
            if (str.length() == 1) {
                return String.valueOf('*');
            } else {
                if (beginIndex < 0) {
                    beginIndex = 0;
                }

                if (endIndex > str.length()) {
                    endIndex = str.length();
                }

                int subLen = endIndex - beginIndex;
                if (subLen < 0) {
                    throw new StringIndexOutOfBoundsException(subLen);
                } else {
                    char[] chars = str.toCharArray();
                    char[] mask = repeatAsterisk(subLen);
                    System.arraycopy(mask, 0, chars, beginIndex, subLen);
                    return new String(chars);
                }
            }
        } else {
            return str;
        }
    }

    private static char[] repeatAsterisk(int len) {
        char[] chars = new char[len];
        Arrays.fill(chars, '*');
        return chars;
    }

    public static String maskAll(String str) {
        return str != null && str.length() != 0 ? "******" : str;
    }

    public static void main(String[] args) {
        System.out.println("姓名掩码：" + nameMask("申屠莺悦"));
        System.out.println("手机号码掩码：" + mobileMask("13567795732"));
        System.out.println("座机号码掩码：" + telPhoneMask("02388888888"));
        System.out.println("身份证号码掩码：" + certMask("361181197009257135"));
        System.out.println("银行卡号掩码：" + bankCardNoMask("6214600180001471999"));
        System.out.println("邮箱掩码：" + emailMask("785297125@qq.com"));
        System.out.println("住址掩码：" + addrMask("贵州省金阳市观山湖区长岭北路中信银行大厦12楼"));
        System.out.println("短信验证码掩码：" + captchaMask("123456"));
        System.out.println("社会统一信用代码掩码：" + creditCodeMask("91520321573329444K"));
        System.out.println("Base64编码截取：" + splitBase64("/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAIBAQEBAQIBAQECAgICAgQDAgICAgUEBAMEBgUGBgYFBgYGBwkIBgcJBwYGCAsICQoKCgoKBggLDAsKDAkKCgr/2wBDAQICAgICAgUDAwUKBwYHCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgr/wAARCACqAIgDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD3iiiigAooor0MuajXd30/yAKwda/5Ccv/AAH/ANBFb1YOtf8AITl/4D/6CK9pSjLZnnZl/AXr+jKtFFFUeIFFFFABRRRQBi6va3UuoSPHbSMpxgqhI6Cq32G9/wCfOX/v2a6OigDnPsN7/wA"));
        MaskTypeEnum maskTypeEnum = MaskTypeEnum.valueOf("CHINESE_NAME");
        System.out.println(maskTypeEnum);
    }
}