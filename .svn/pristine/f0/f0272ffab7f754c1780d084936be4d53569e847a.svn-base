package com.jd.common.utils.file;

import com.jd.common.exception.file.FileNameLengthLimitExceededException;
import com.jd.common.utils.ServletUtils;
import com.jd.common.utils.StringUtils;
import com.jd.framework.config.ProjectPropertiesConfig;
import com.jd.framework.web.domain.Result;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文件上传工具类
 */
public class FileUploadUtils {

    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 52428800;

    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = ProjectPropertiesConfig.getProfile();

    /**
     * 默认的文件名最大长度
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 200;

    /**
     * 默认文件类型jpg
     */
    public static final String IMAGE_JPG_EXTENSION = ".jpg";

    private static int counter = 0;

    public static void setDefaultBaseDir(String defaultBaseDir) {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    public static String getDefaultBaseDir() {
        return defaultBaseDir;
    }

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(MultipartFile file) throws IOException {
        try {
            return upload(getDefaultBaseDir(), file, FileUploadUtils.IMAGE_JPG_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(String baseDir, MultipartFile file) throws IOException {
        try {
            return upload(baseDir, file, FileUploadUtils.IMAGE_JPG_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }


    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final Result uploadFile(String baseDir, MultipartFile file,String dir) throws IOException {
        try {
            String filename =file.getOriginalFilename();
            String filetype = filename.substring(filename.lastIndexOf("."));
            return uploadFile(baseDir, file, filetype,dir);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir                   相对应用的基目录
     * @param file                      上传的文件
     * @param extension                 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException       如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException                          比如读写文件出错时
     */
    public static final String upload(String baseDir, MultipartFile file, String extension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException {

        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(file.getOriginalFilename(), fileNamelength,
                    FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file);

        String fileName = encodingFilename(file.getOriginalFilename(), extension);

        File desc = getAbsoluteFile(baseDir, fileName);
        file.transferTo(desc);
        return fileName;
    }


    /**
     * 文件上传
     *
     * @param baseDir                   相对应用的基目录
     * @param file                      上传的文件
     * @param filetype                 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException       如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException                          比如读写文件出错时
     */
    public static final Result uploadFile(String baseDir, MultipartFile file, String filetype,String dir)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException {
        Result result = new Result();
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(file.getOriginalFilename(), fileNamelength,
                    FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }
        // 验证文件大小
        assertAllowed(file);
        // 生成文件名
        String fileName = encodingFilename(file.getOriginalFilename(), filetype);
        // 根据路径生成文件传输文件
        File desc = getAbsoluteFile(baseDir+dir+File.separator, fileName);
        file.transferTo(desc);

        result.put("fileName",file.getOriginalFilename());
        result.put("fileUrl",dir + File.separator + fileName);
        result.put("fileType",filetype);
        return result;
    }

    /**
     * web文件下载的方法
     * @param url
     * @param upLoadDir,url
     */
    public static void downFile(String upLoadDir,String url){
        String serverUrl = upLoadDir;
        if(StringUtils.isEmpty(serverUrl)){
            serverUrl= "D:";
        }
        File file = new File(serverUrl + url);
        // 后缀名
        if(url.indexOf(".")==-1){return ;}
        String suffixName = url.substring(url.lastIndexOf("."));
        String imgType = "image/" + suffixName;
        //判断文件是否存在如果不存在就返回默认图标
        if (!(file.exists() && file.canRead())) {
            return;
        }
        FileInputStream inputStream = null;
        OutputStream stream =null;
        try {
            inputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            inputStream.read(data);
            HttpServletResponse response=ServletUtils.getResponse();
            response.setHeader ("Content-type", "application/octet-stream");
            response.setCharacterEncoding ("utf-8");//设置编码集,文件名不会发生中文乱码
            response.setHeader ("Content-Disposition", "attachment;filename=" + url);
            stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(inputStream!=null)
                    inputStream.close();
                if(stream!=null)
                    stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static final File getAbsoluteFile(String uploadDir, String filename) throws IOException {
        File desc = new File(File.separator+ uploadDir + filename);

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 编码文件名
     */
    private static final String encodingFilename(String filename, String extension) {
        filename = filename.replace("_", " ");
        filename = new Md5Hash(filename + System.nanoTime() + counter++).toHex().toString() + extension;
        return filename;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     * @throws FileSizeLimitExceededException 如果超出最大大小
     */
    public static final void assertAllowed(MultipartFile file) throws FileSizeLimitExceededException {
        long size = file.getSize();
        if (DEFAULT_MAX_SIZE != -1 && size > DEFAULT_MAX_SIZE) {
            throw new FileSizeLimitExceededException("not allowed upload upload", size, DEFAULT_MAX_SIZE);
        }
    }

}
