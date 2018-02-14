package cn.sunxingdong.mpam.controller;

import cn.sunxingdong.mpam.biz.intf.file.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "files")
public class FileController {
    
    /**
     * fileUploadDownloadService <br>
     */
    @Autowired
    private FileService fileService;

    /**
     * uploadMaxSize <br>
     */
    @Value("${ucc.fileStorage.uploadMaxSize:2}")
    private Long uploadMaxSize;
    
    /**
     * contentTypeMap <br>
     */
    private static Map<String, String> contentTypeMap = new HashMap<String, String>();
    static {
        contentTypeMap.put("ez" , "application/andrew-inset");
        contentTypeMap.put("hqx" , "application/mac-binhex40");
        contentTypeMap.put("cpt" , "application/mac-compactpro");
        contentTypeMap.put("doc" , "application/msword");
        contentTypeMap.put("bin" , "application/octet-stream");
        contentTypeMap.put("dms" , "application/octet-stream");
        contentTypeMap.put("lha" , "application/octet-stream");
        contentTypeMap.put("lzh" , "application/octet-stream");
        contentTypeMap.put("exe" , "application/octet-stream");
        contentTypeMap.put("class" , "application/octet-stream");
        contentTypeMap.put("so" , "application/octet-stream");
        contentTypeMap.put("dll" , "application/octet-stream");
        contentTypeMap.put("oda" , "application/oda");
        contentTypeMap.put("pdf" , "application/pdf");
        contentTypeMap.put("ai" , "application/postscript");
        contentTypeMap.put("eps" , "application/postscript");
        contentTypeMap.put("ps" , "application/postscript");
        contentTypeMap.put("smi" , "application/smil");
        contentTypeMap.put("smil" , "application/smil");
        contentTypeMap.put("mif" , "application/vnd.mif");
        contentTypeMap.put("xls" , "application/vnd.ms-excel");
        contentTypeMap.put("ppt" , "application/vnd.ms-powerpoint");
        contentTypeMap.put("wbxml" , "application/vnd.wap.wbxml");
        contentTypeMap.put("wmlc" , "application/vnd.wap.wmlc");
        contentTypeMap.put("wmlsc" , "application/vnd.wap.wmlscriptc");
        contentTypeMap.put("bcpio" , "application/x-bcpio");
        contentTypeMap.put("vcd" , "application/x-cdlink");
        contentTypeMap.put("pgn" , "application/x-chess-pgn");
        contentTypeMap.put("cpio" , "application/x-cpio");
        contentTypeMap.put("csh" , "application/x-csh");
        contentTypeMap.put("dcr" , "application/x-director");
        contentTypeMap.put("dir" , "application/x-director");
        contentTypeMap.put("dxr" , "application/x-director");
        contentTypeMap.put("dvi" , "application/x-dvi");
        contentTypeMap.put("spl" , "application/x-futuresplash");
        contentTypeMap.put("gtar" , "application/x-gtar");
        contentTypeMap.put("hdf" , "application/x-hdf");
        contentTypeMap.put("js" , "application/x-javascript");
        contentTypeMap.put("skp" , "application/x-koan");
        contentTypeMap.put("skd" , "application/x-koan");
        contentTypeMap.put("skt" , "application/x-koan");
        contentTypeMap.put("skm" , "application/x-koan");
        contentTypeMap.put("latex" , "application/x-latex");
        contentTypeMap.put("nc" , "application/x-netcdf");
        contentTypeMap.put("cdf" , "application/x-netcdf");
        contentTypeMap.put("sh" , "application/x-sh");
        contentTypeMap.put("shar" , "application/x-shar");
        contentTypeMap.put("swf" , "application/x-shockwave-flash");
        contentTypeMap.put("sit" , "application/x-stuffit");
        contentTypeMap.put("sv4cpio" , "application/x-sv4cpio");
        contentTypeMap.put("sv4crc" , "application/x-sv4crc");
        contentTypeMap.put("tar" , "application/x-tar");
        contentTypeMap.put("tcl" , "application/x-tcl");
        contentTypeMap.put("tex" , "application/x-tex");
        contentTypeMap.put("texinfo" , "application/x-texinfo");
        contentTypeMap.put("texi" , "application/x-texinfo");
        contentTypeMap.put("t" , "application/x-troff");
        contentTypeMap.put("tr" , "application/x-troff");
        contentTypeMap.put("roff" , "application/x-troff");
        contentTypeMap.put("man" , "application/x-troff-man");
        contentTypeMap.put("me" , "application/x-troff-me");
        contentTypeMap.put("ms" , "application/x-troff-ms");
        contentTypeMap.put("ustar" , "application/x-ustar");
        contentTypeMap.put("src" , "application/x-wais-source");
        contentTypeMap.put("xhtml" , "application/xhtml+xml");
        contentTypeMap.put("xht" , "application/xhtml+xml");
        contentTypeMap.put("zip" , "application/zip");
        contentTypeMap.put("au" , "audio/basic");
        contentTypeMap.put("snd" , "audio/basic");
        contentTypeMap.put("mid" , "audio/midi");
        contentTypeMap.put("midi" , "audio/midi");
        contentTypeMap.put("kar" , "audio/midi");
        contentTypeMap.put("mpga" , "audio/mpeg");
        contentTypeMap.put("mp2" , "audio/mpeg");
        contentTypeMap.put("mp3" , "audio/mpeg");
        contentTypeMap.put("aif" , "audio/x-aiff");
        contentTypeMap.put("aiff" , "audio/x-aiff");
        contentTypeMap.put("aifc" , "audio/x-aiff");
        contentTypeMap.put("m3u" , "audio/x-mpegurl");
        contentTypeMap.put("ram" , "audio/x-pn-realaudio");
        contentTypeMap.put("rm" , "audio/x-pn-realaudio");
        contentTypeMap.put("rpm" , "audio/x-pn-realaudio-plugin");
        contentTypeMap.put("ra" , "audio/x-realaudio");
        contentTypeMap.put("wav" , "audio/x-wav");
        contentTypeMap.put("pdb" , "chemical/x-pdb");
        contentTypeMap.put("xyz" , "chemical/x-xyz");
        contentTypeMap.put("bmp" , "image/bmp");
        contentTypeMap.put("gif" , "image/gif");
        contentTypeMap.put("ief" , "image/ief");
        contentTypeMap.put("jpeg" , "image/jpeg");
        contentTypeMap.put("jpg" , "image/jpeg");
        contentTypeMap.put("jpe" , "image/jpeg");
        contentTypeMap.put("png" , "image/png");
        contentTypeMap.put("tiff" , "image/tiff");
        contentTypeMap.put("tif" , "image/tiff");
        contentTypeMap.put("djvu" , "image/vnd.djvu");
        contentTypeMap.put("djv" , "image/vnd.djvu");
        contentTypeMap.put("wbmp" , "image/vnd.wap.wbmp");
        contentTypeMap.put("ras" , "image/x-cmu-raster");
        contentTypeMap.put("pnm" , "image/x-portable-anymap");
        contentTypeMap.put("pbm" , "image/x-portable-bitmap");
        contentTypeMap.put("pgm" , "image/x-portable-graymap");
        contentTypeMap.put("ppm" , "image/x-portable-pixmap");
        contentTypeMap.put("rgb" , "image/x-rgb");
        contentTypeMap.put("xbm" , "image/x-xbitmap");
        contentTypeMap.put("xpm" , "image/x-xpixmap");
        contentTypeMap.put("xwd" , "image/x-xwindowdump");
        contentTypeMap.put("igs" , "model/iges");
        contentTypeMap.put("iges" , "model/iges");
        contentTypeMap.put("msh" , "model/mesh");
        contentTypeMap.put("mesh" , "model/mesh");
        contentTypeMap.put("silo" , "model/mesh");
        contentTypeMap.put("wrl" , "model/vrml");
        contentTypeMap.put("vrml" , "model/vrml");
        contentTypeMap.put("css" , "text/css");
        contentTypeMap.put("html" , "text/html; charset=UTF-8");
        contentTypeMap.put("htm" , "text/html; charset=UTF-8");
        contentTypeMap.put("stm" , "text/html; charset=UTF-8");
        contentTypeMap.put("shtm" , "text/html; charset=UTF-8");
        contentTypeMap.put("shtml" , "text/html; charset=UTF-8");
        contentTypeMap.put("asc" , "text/plain");
        contentTypeMap.put("txt" , "text/plain");
        contentTypeMap.put("rtx" , "text/richtext");
        contentTypeMap.put("rtf" , "text/rtf");
        contentTypeMap.put("sgml" , "text/sgml");
        contentTypeMap.put("sgm" , "text/sgml");
        contentTypeMap.put("tsv" , "text/tab-separated-values");
        contentTypeMap.put("wml" , "text/vnd.wap.wml");
        contentTypeMap.put("wmls" , "text/vnd.wap.wmlscript");
        contentTypeMap.put("etx" , "text/x-setext");
        contentTypeMap.put("xsl" , "text/xml");
        contentTypeMap.put("xml" , "text/xml");
        contentTypeMap.put("mpeg" , "video/mpeg");
        contentTypeMap.put("mpg" , "video/mpeg");
        contentTypeMap.put("mpe" , "video/mpeg");
        contentTypeMap.put("qt" , "video/quicktime");
        contentTypeMap.put("mov" , "video/quicktime");
        contentTypeMap.put("mxu" , "video/vnd.mpegurl");
        contentTypeMap.put("avi" , "video/x-msvideo");
        contentTypeMap.put("movie" , "video/x-sgi-movie");
        contentTypeMap.put("ice" , "x-conference/x-cooltalk");
    }
    
//    @ResponseBody
//    @RequestMapping(method = RequestMethod.POST)
//    public UploadResponseEntity upload(@RequestParam("files[]") MultipartFile file) throws BaseAppException {
//        logger.info("FileController.upload start");
//        String fileName = file.getOriginalFilename();
//        Long fileSize = file.getSize();
//        if (fileSize > uploadMaxSize * 1024 * 1024) {
//
//        }
//        try {
//            String mediaId = fileService.upload(file.getInputStream(), fileName);
//            UploadResponseEntity entity = new UploadResponseEntity();
//            entity.setMediaId(mediaId);
//            return entity;
//        }
//        catch (IOException e) {
//
//        }
//        return null;
//    }
    
    @RequestMapping(value = "**", method = RequestMethod.GET)
    public void download(@RequestParam("path") String filePath, HttpServletResponse response) {
        String fileName = filePath;
        if (filePath.contains("/")) {
            int lastIndex = filePath.lastIndexOf("/");
            fileName = filePath.substring(lastIndex + 1);
        }
        OutputStream os = null;
        try {
            String prefix = filePath.substring(filePath.lastIndexOf(".") + 1);
            String contentType = contentTypeMap.get(prefix);
            if (StringUtils.isEmpty(contentType)) {
                contentType = "application/octet-stream; charset=UTF-8";
            }
            response.setContentType(contentType);
            
            // 对应网页直接打开，不需要下载
            if (!filePath.toLowerCase().endsWith(".htm") &&
                    !filePath.toLowerCase().endsWith(".html") && contentType.indexOf("image") < 0) {
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            }
            os = response.getOutputStream();
            //fileService.download(os, filePath);
        }
        catch (IOException e) {

        }
        
    }
}