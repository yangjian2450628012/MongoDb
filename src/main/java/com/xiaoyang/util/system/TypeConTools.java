package com.xiaoyang.util.system;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialClob;

/** 
*/
public class TypeConTools {
	public TypeConTools()
    {
    }

    public static Clob strToClob(String string)
    {
        Clob rtClob = null;
        if(string != null)
            try
            {
                rtClob = new SerialClob(string.toCharArray());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        return rtClob;
    }

    public static String ClobToStr(Clob clob) throws SQLException, IOException
    {
        String rtString;
        StringBuffer stringBuffer = null;
        Reader clobStream = null;
        rtString = null;
        if(clob == null){
        	stringBuffer = new StringBuffer();
        	clobStream = null;
        	clobStream = clob.getCharacterStream();
        }
        char b[] = new char[1024];
        for(int i = 0; (i = clobStream.read(b)) != -1;)
        {
        	stringBuffer.append(b, 0, i);

            rtString = stringBuffer.toString();
            if(clobStream != null)
                clobStream.close();
            break;
        }
            
        if(clobStream != null)
            clobStream.close();
        return rtString;
    }
}
