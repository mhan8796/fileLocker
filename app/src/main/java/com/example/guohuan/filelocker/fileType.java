package com.example.guohuan.filelocker;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class fileType {
    public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();
    static {
        //images
        mFileTypes.put("000000","iso");
        mFileTypes.put("3026B2","asf");
        mFileTypes.put("524946","avi");
        mFileTypes.put("415649","avi");
        mFileTypes.put("202020","BAS");
        mFileTypes.put("424D36","bmp");
        mFileTypes.put("424D3E","BMP");
        mFileTypes.put("5B436C","CCD");
        mFileTypes.put("495453","CHM");
        mFileTypes.put("5B5769","CPX");
        mFileTypes.put("4D5A50","DPL");
        mFileTypes.put("414331","dwg");
        mFileTypes.put("2A5052","ECO");
        mFileTypes.put("526563","EML");
        mFileTypes.put("C5D0D3","EPS");
        mFileTypes.put("434841","FNT");
        mFileTypes.put("87F53E","GBC");
        mFileTypes.put("474946","gif");
        mFileTypes.put("7B5072","GTD");
        mFileTypes.put("4D5A90","IME");
        mFileTypes.put("00FFFF","IMG");
        mFileTypes.put("4D5A90","IMM");
        mFileTypes.put("FFD8FF","jpg");
        mFileTypes.put("42494C","LDB");
        mFileTypes.put("3F5F03","LHP");
        mFileTypes.put("2A2420","LIB");
        mFileTypes.put("4C0000","LNK");
        mFileTypes.put("234558","m3u");
        mFileTypes.put("537461","mdb");
        mFileTypes.put("00FFFF","MDF");
        mFileTypes.put("4D4544","MDS");
        mFileTypes.put("4d494d","mht");
        mFileTypes.put("4D5468","mid");
        mFileTypes.put("6D6F6F","mov");
        mFileTypes.put("00000F","MOV");
        mFileTypes.put("494433","mp3");
        mFileTypes.put("FFFB50","MP3");
        mFileTypes.put("3C3F78","MSC");
        mFileTypes.put("4E4553","NES");
        mFileTypes.put("C22020","NLS");
        mFileTypes.put("4D5A90","OCX");
        mFileTypes.put("4D5A90","OLB");
        mFileTypes.put("5B4144","PBK");
        mFileTypes.put("17A150","PCB");
        mFileTypes.put("0A0501","PCS");
        mFileTypes.put("255044","pdf");
        mFileTypes.put("255044","PDF");
        mFileTypes.put("484802","PDG");
        mFileTypes.put("24536F","PLL");
        mFileTypes.put("89504e","png");
        mFileTypes.put("89504E","PNG");
        mFileTypes.put("526563","PPC");
        mFileTypes.put("234445","PRG");
        mFileTypes.put("2E7261","ram");
        mFileTypes.put("526172","rar");
        mFileTypes.put("526172","RAR");
        mFileTypes.put("2E524D","rm");
        mFileTypes.put("7B5C72","rtf");
        mFileTypes.put("2A7665","SCH");
        mFileTypes.put("805343","scm");
        mFileTypes.put("00FFFF","SMD");
        mFileTypes.put("FFFFFF","SUB");
        mFileTypes.put("435753","swf");
        mFileTypes.put("435753","SWF");
        mFileTypes.put("49492A","tif");
        mFileTypes.put("524946","wav");
        mFileTypes.put("574156","wav");
        mFileTypes.put("3026b2","wmv");
        mFileTypes.put("31BE00","WRI");
        mFileTypes.put("584245","XBE");
        mFileTypes.put("3C3F78","XML");
        mFileTypes.put("FFFE3C","XSL");
        mFileTypes.put("504B03","zip");
        mFileTypes.put("255044","PDF");
        mFileTypes.put("526563","EML");
        mFileTypes.put("D0CF11","PPT");
        mFileTypes.put("4D5AEE","COM");
        mFileTypes.put("E93B03","COM");
        mFileTypes.put("4D5A90","EXE");
        mFileTypes.put("424D3E","BMP");
        mFileTypes.put("49492A","TIF");
        mFileTypes.put("384250","PSD");
        mFileTypes.put("C5D0D3","EPS");
        mFileTypes.put("0A0501","PCS");
        mFileTypes.put("89504E","PNG");
        mFileTypes.put("060500","RAW");
        mFileTypes.put("000002","TGA");
        mFileTypes.put("60EA27","ARJ");
        mFileTypes.put("526172","RAR");
        mFileTypes.put("504B03","ZIP");
        mFileTypes.put("495363","CAB");
        mFileTypes.put("1F9D8C","Z");
        mFileTypes.put("524946","WAV");
        mFileTypes.put("435753","SWF");
        mFileTypes.put("3026B2","WMV");
        mFileTypes.put("3026B2","WMA");
        mFileTypes.put("2E524D","RM");
        mFileTypes.put("00000F","MOV");
        mFileTypes.put("000077","MOV");
        mFileTypes.put("000001","MPA");
        mFileTypes.put("FFFB50","MP3");
        mFileTypes.put("234558","m3u");
        mFileTypes.put("3C2144","HTM");
        mFileTypes.put("FFFE3C","XSL");
        mFileTypes.put("3C3F78","XML");
        mFileTypes.put("3C3F78","MSC");
        mFileTypes.put("4C0000","LNK");
        mFileTypes.put("495453","CHM");
        mFileTypes.put("805343","scm");
        mFileTypes.put("D0CF11","XLS");
        mFileTypes.put("31BE00","WRI");
        mFileTypes.put("00FFFF","MDF");
        mFileTypes.put("4D4544","MDS");
        mFileTypes.put("5B436C","CCD");
        mFileTypes.put("00FFFF","IMG");
        mFileTypes.put("FFFFFF","SUB");
        mFileTypes.put("17A150","PCB");
        mFileTypes.put("2A5052","ECO");
        mFileTypes.put("526563","PPC");
        mFileTypes.put("000100","DDB");
        mFileTypes.put("42494C","LDB");
        mFileTypes.put("2A7665","SCH");
        mFileTypes.put("2A2420","LIB");
        mFileTypes.put("434841","FNT");
        mFileTypes.put("7B5C72","RTF");
        mFileTypes.put("7B5072","GTD");
        mFileTypes.put("234445","PRG");
        mFileTypes.put("000007","PJT");
        mFileTypes.put("202020","BAS");
        mFileTypes.put("000002","TAG");
        mFileTypes.put("4D5A90","dll");
        mFileTypes.put("4D5A90","OCX");
        mFileTypes.put("4D5A50","DPL");
        mFileTypes.put("3F5F03","HLP");
        mFileTypes.put("4D5A90","OLB");
        mFileTypes.put("4D5A90","IMM");
        mFileTypes.put("4D5A90","IME");
        mFileTypes.put("3F5F03","LHP");
        mFileTypes.put("C22020","NLS");
        mFileTypes.put("5B5769","CPX");
        mFileTypes.put("4D5A16","DRV");
        mFileTypes.put("5B4144","PBK");
        mFileTypes.put("24536F","PLL");
        mFileTypes.put("4E4553","NES");
        mFileTypes.put("87F53E","GBC");
        mFileTypes.put("00FFFF","SMD");
        mFileTypes.put("584245","XBE");
        mFileTypes.put("005001","XMV");
        mFileTypes.put("000100","TTF");
        mFileTypes.put("484802","PDG");
        mFileTypes.put("000100","TST");
        mFileTypes.put("", "");
    }
    public static String getFileType(String filePath) {
        if(mFileTypes.get(getFileHeader(filePath))==null)
        {
            return "folder";
        }
        return mFileTypes.get(getFileHeader(filePath));
    }
    public static String getFileHeader(String filePath) {
        FileInputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[3];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
        } finally {
            if(null != is) {
                try {
                    is.close();
                } catch (IOException e) {}
            }
        }
        return value;
    }
    private static String bytesToHexString(byte[] src){
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }

}
