package com.xianzhifengshui.api.des;

/**
 * @desc byte数组与十六进制互相转换
 *
 * @author zhangtb
 * @date 2016年10月4日下午8:33:41
 * @since 1.0.0
 * @version 1.0.0
 */
public class DESPlus {
	
    /**
     * @desc byte数组转换成十六进制
     *
     * @author zhangtb
     * @date 2016年10月4日下午8:33:47
     * @since 1.0.0
     * @version 1.0.0
     * @param arrB
     * @return
     * @throws Exception
     */
    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * @desc 十六进制转换成byte数组
     *
     * @author zhangtb
     * @date 2016年10月4日下午8:34:00
     * @since 1.0.0
     * @version 1.0.0
     * @param strIn
     * @return
     * @throws Exception
     */
    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }
    
}
