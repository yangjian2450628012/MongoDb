package com.xiaoyang.util.system;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
/** 
* @ClassName: Tools 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yang
* @date 2017年1月22日 下午2:42:17 
*  
*/
public class Tools {
	 private static List htmlTag_static;

	    static 
	    {
	        htmlTag_static = null;
	        htmlTag_static = new ArrayList(2);
	        htmlTag_static.add("#br#");
	        htmlTag_static.add("<br>");
	    }

    public Tools()
    {
    }

    public static String unicode2utf8(String instr)
    {
        return instr.replaceAll("\272", "\260");
    }

    public static String getNewUUID()
    {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }

    public static boolean StringIsNullOrSpace(String str)
    {
        return str == null || "".equals(str.trim()) || "NULL".equals(str.trim().toUpperCase()) || "<\u65E0>".equals(str.trim());
    }

    public static String getTimeNow(String format)
    {
        try
        {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(d);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static String getPreDaysDate(String todayDate, int days)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(todayDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, -days);
        return sdf.format(cal.getTime());
    }

    public static String[] splitStr(String str, int len)
    {
        if(str != null && !"".equals(str))
        {
            int num = 1;
            num = str.length() % len != 0 ? str.length() / len + 1 : str.length() / len;
            String result[] = new String[num];
            for(int i = 0; i < num; i++)
                if(str.length() > (i + 1) * len)
                    result[i] = str.substring(i * len, (i + 1) * len);
                else
                    result[i] = str.substring(i * len, str.length());

            return result;
        } else
        {
            return null;
        }
    }

    public static String toUTF8(String str)
        throws Exception
    {
        if(str == null)
            return null;
        else
            return new String(str.getBytes("ISO8859_1"), "utf-8");
    }

    public static boolean isDateBefore(String data1, String date2, String format)
    {
        DateFormat df = new SimpleDateFormat(format);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try
        {
            c1.setTime(df.parse(data1));
            c2.setTime(df.parse(date2));
        }
        catch(ParseException e)
        {
            System.err.println("\u683C\u5F0F\u4E0D\u6B63\u786E");
        }
        int result = c1.compareTo(c2);
        return result <= 0;
    }

    public static String getStrByLen(String str, int len)
    {
        if(str != null && !"".equals(str))
        {
            if(str.length() > len)
                return str.substring(0, len);
            else
                return str;
        } else
        {
            return "";
        }
    }

    public static String nullToSpace(String str)
    {
        if("null".equalsIgnoreCase(str) || "<\u65E0>".equals(str))
            return "";
        else
            return str;
    }

    public static String getPYString(String str)
    {
        String tempStr = "";
        for(int i = 0; i < str.length(); i++)
        {
            char c = str.charAt(i);
            if(c >= ' ' && c <= '~')
                tempStr = (new StringBuilder(String.valueOf(tempStr))).append(String.valueOf(c)).toString();
            else
                tempStr = (new StringBuilder(String.valueOf(tempStr))).append(getPYChar(String.valueOf(c))).toString();
        }

        return tempStr;
    }

    public static String getPYChar(String c)
    {
        byte array[] = new byte[2];
        array = String.valueOf(c).getBytes();
        int i = (short)((array[0] - 0) + 256) * 256 + (short)((array[1] - 0) + 256);
        if(i < 45217)
            return "";
        if(i < 45253)
            return "a";
        if(i < 45761)
            return "b";
        if(i < 46318)
            return "c";
        if(i < 46826)
            return "d";
        if(i < 47010)
            return "e";
        if(i < 47297)
            return "f";
        if(i < 47614)
            return "g";
        if(i < 48119)
            return "h";
        if(i < 49062)
            return "j";
        if(i < 49324)
            return "k";
        if(i < 49896)
            return "l";
        if(i < 50371)
            return "m";
        if(i < 50614)
            return "n";
        if(i < 50622)
            return "o";
        if(i < 50906)
            return "p";
        if(i < 51387)
            return "q";
        if(i < 51446)
            return "r";
        if(i < 52218)
            return "s";
        if(i < 52698)
            return "t";
        if(i < 52980)
            return "w";
        if(i < 53689)
            return "x";
        if(i < 54481)
            return "y";
        if(i < 55290)
            return "z";
        else
            return "";
    }

    public static String getDydj(String key)
    {
        if(key == null || "".equals(key))
            return "";
        if("-1".equals(key))
            return "\u65E0\u7B49\u7EA7";
        if("1".equals(key))
            return "500kV";
        if("2".equals(key))
            return "220kV";
        if("3".equals(key))
            return "110kV";
        if("4".equals(key))
            return "35kV";
        if("5".equals(key))
            return "10kV";
        if("6".equals(key))
            return "6.3kV";
        if("7".equals(key))
            return "6kV";
        if("8".equals(key))
            return "0.4kV";
        else
            return "";
    }

    public static String getDydjKey(String value)
    {
        if(value == null || "".equals(value))
            return "-1";
        if("\u65E0\u7B49\u7EA7".equals(value))
            return "-1";
        if("500".equals(value.toLowerCase().replaceAll("kv", "").trim()))
            return "1";
        if("220".equals(value.toLowerCase().replaceAll("kv", "").trim()))
            return "2";
        if("110".equals(value.toLowerCase().replaceAll("kv", "").trim()))
            return "3";
        if("35".equals(value.toLowerCase().replaceAll("kv", "").trim()))
            return "4";
        if("10".equals(value.toLowerCase().replaceAll("kv", "").trim()))
            return "5";
        if("6.3".equals(value.toLowerCase().replaceAll("kv", "").trim()))
            return "6";
        if("6".equals(value.toLowerCase().replaceAll("kv", "").trim()))
            return "7";
        if("0.4".equals(value.toLowerCase().replaceAll("kv", "").trim()))
            return "8";
        else
            return "";
    }

    public static boolean isExistFile(String filePath, String fileName)
    {
        try
        {
            File f = new File(new File(filePath), fileName);
            return f.exists();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static int rateUserPass(String pass)
    {
        int i = 0;
        if(pass == null || pass.length() == 0)
            return 0;
        boolean hasLetter = pass.matches("[a-zA-Z]*");
        boolean hasNumber = pass.matches("[0-9]*");
        boolean hasLN = pass.matches("[a-zA-Z0-9]*");
        int passLen = pass.length();
        if(passLen >= 8)
        {
            if(hasLetter || hasNumber)
            {
                if(passLen < 8)
                    i = 2;
                else
                    i = 3;
            } else
            if(!hasLetter && !hasNumber && hasLN)
            {
                if(passLen >= 10)
                    i = 5;
                else
                if(passLen >= 8)
                    i = 4;
                else
                    i = 3;
            } else
            if(!hasLetter && !hasLetter && !hasLN)
            {
                if(passLen >= 8)
                    i = 5;
                else
                    i = 4;
            } else
            if(hasLetter && !hasLetter || !hasLetter && hasNumber)
                if(!hasLetter && !hasNumber)
                    i = 2;
                else
                if(passLen > 8)
                    i = 5;
                else
                if(passLen == 8)
                    i = 4;
                else
                    i = 3;
        } else
        if(passLen > 0)
            i = 1;
        else
            i = 0;
        return i;
    }

    public static String addrEncode(String text)
    {
        return text.replace(File.separatorChar, '/');
    }

    public static boolean StrIsNumber(String str)
    {
        if(str == null || str.length() == 0)
        {
            return false;
        } else
        {
            boolean hasNumber = str.matches("[0-9]");
            return hasNumber;
        }
    }

    public static String Encode(String code)
    {
        if(code == null)
            return null;
        else
            return code.replaceAll(">", "&gt;").replaceAll("<", "&lt;").replaceAll("\"", "&quot;");
    }

    public static boolean isNumeric(String str)
    {
        if(str == null || "".equals(str.trim()))
        {
            return false;
        } else
        {
            Pattern pattern = Pattern.compile("[0-9]*");
            str = str.replaceFirst("\\.", "");
            return pattern.matcher(str).matches();
        }
    }

    public static double StringBeDouble(String str)
    {
        if(isNumeric(str))
            return Double.parseDouble(str);
        else
            return 0.0D;
    }

    public static String[] getDate(int lx, String date, String language)
        throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = sdf.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        int index = cal.get(7);
        if(language.equalsIgnoreCase("CN") && index == 1)
            index = 8;
        if(lx == 0)
        {
            if(language.equalsIgnoreCase("CN"))
                cal.add(5, -(index - 2));
            else
                cal.set(7, cal.getMinimalDaysInFirstWeek());
        } else
        if(lx == 1)
            cal.set(5, cal.getActualMinimum(5));
        else
        if(lx == 2)
            cal.set(6, cal.getActualMinimum(6));
        String start = sdf.format(cal.getTime());
        if(lx == 0)
        {
            if(language.equalsIgnoreCase("CN"))
                cal.add(5, 6);
            else
                cal.set(7, cal.getActualMaximum(7));
        } else
        if(lx == 1)
            cal.set(5, cal.getActualMaximum(5));
        else
        if(lx == 2)
            cal.set(6, cal.getActualMaximum(6));
        String end = sdf.format(cal.getTime());
        String result[] = {
            start, end
        };
        return result;
    }

    public static Date getDateBySecs(String time, long secs)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(time);
        long time1 = date.getTime();
        time1 += secs * 1000L;
        date = new Date(time1);
        return date;
    }

    public static long getTimeInSecs(String asStartTime, String asEndTime)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse(asStartTime);
        Date date2 = sdf.parse(asEndTime);
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long time = time2 - time1;
        return time / 1000L;
    }

    public static void main(String args1[])
    {
    }

    public static String clearHtmlTag(String content, List htmlTag, String target)
    {
        if(StringIsNullOrSpace(content))
            return "";
        if(htmlTag == null || htmlTag.size() == 0)
            htmlTag = htmlTag_static;
        for(Iterator iterator = htmlTag.iterator(); iterator.hasNext();)
        {
            String html = (String)iterator.next();
            content = content.replace(html, target);
        }

        return content;
    }

    public static String htmEncode(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        int j = s.length();
        for(int i = 0; i < j; i++)
        {
            char c = s.charAt(i);
            switch(c)
            {
            case 60: // '<'
                stringbuffer.append("&lt;");
                break;

            case 62: // '>'
                stringbuffer.append("&gt;");
                break;

            case 38: // '&'
                stringbuffer.append("&amp;");
                break;

            case 34: // '"'
                stringbuffer.append("&quot;");
                break;

            case 169: 
                stringbuffer.append("&copy;");
                break;

            case 174: 
                stringbuffer.append("&reg;");
                break;

            case 165: 
                stringbuffer.append("&yen;");
                break;

            case 8364: 
                stringbuffer.append("&euro;");
                break;

            case 8482: 
                stringbuffer.append("&#153;");
                break;

            case 13: // '\r'
                if(i < j - 1 && s.charAt(i + 1) == '\n')
                {
                    stringbuffer.append("<br>");
                    i++;
                }
                break;

            case 32: // ' '
                if(i < j - 1 && s.charAt(i + 1) == ' ')
                {
                    stringbuffer.append(" &nbsp;");
                    i++;
                    break;
                }
                // fall through

            default:
                stringbuffer.append(c);
                break;
            }
        }

        return new String(stringbuffer.toString());
    }

    public static String formatDate(Date d, String format)
    {
        try
        {
            if(d != null)
            {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.format(d);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "";
        }
        return "";
    }

    public static Date convertToDate(String strDate, String format)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(strDate);
            return date;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatString(String str, String format)
    {
        int num = 0;
        if(StringIsNullOrSpace(str))
            return "";
        if(format.lastIndexOf(".") != -1)
            num = format.length() - format.lastIndexOf(".") - 1;
        if(str.lastIndexOf(".") != -1)
        {
            if(str.length() - str.lastIndexOf(".") > num)
                return str.substring(0, str.lastIndexOf(".") + 1 + num);
            else
                return str;
        } else
        {
            return str;
        }
    }

}
