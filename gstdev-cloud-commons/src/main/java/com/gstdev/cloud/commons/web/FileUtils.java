// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.commons.web;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import com.gstdev.cloud.commons.exception.BadRequestException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@SuppressWarnings({"unchecked", "all"})
public class FileUtils extends cn.hutool.core.io.FileUtil {

  /**
   * 定义GB的计算常量
   */
  private static final int GB = 1024 * 1024 * 1024;
  /**
   * 定义MB的计算常量
   */
  private static final int MB = 1024 * 1024;
  /**
   * 定义KB的计算常量
   */
  private static final int KB = 1024;

  /**
   * 格式化小数
   */
  private static final DecimalFormat DF = new DecimalFormat("0.00");

  /**
   * 随机数的最大值
   */
  private static final Integer maxNum = 2 << 12;
  /**
   * 文件后缀列表
   */
  public static String[] suffixArr = {"txt", "pdf", "doc", "docx", "ppt", "pps", "pptx", "xls", "xlsx", "mp3", "mp4",
    "mp5", "png", "jpeg", "jpg", "gif", "avi", "3gp", "flv"};

  /**
   * MultipartFile转File
   */
  public static File toFile(MultipartFile multipartFile) {
    // 获取文件名
    String fileName = multipartFile.getOriginalFilename();
    // 获取文件后缀
    String prefix = "." + getExtensionName(fileName);
    File file = null;
    try {
      // 用uuid作为文件名，防止生成的临时文件重复
      file = File.createTempFile(IdUtil.simpleUUID(), prefix);
      // MultipartFile to File
      multipartFile.transferTo(file);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return file;
  }

  public static String replaceExtentionName(File file, String newExtention) {
    String name = file.getName();
    return file.getParent() + File.separator + name.substring(0, name.indexOf(getExtensionName(name))) + newExtention;
  }

  /**
   * 获取文件扩展名，不带 .
   */
  public static String getExtensionName(String filename) {
    if ((filename != null) && (filename.length() > 0)) {
      int dot = filename.lastIndexOf('.');
      if ((dot > -1) && (dot < (filename.length() - 1))) {
        return filename.substring(dot + 1);
      }
    }
    return filename;
  }

  /**
   * Java文件操作 获取不带扩展名的文件名
   */
  public static String getFileNameNoEx(String filename) {
    if ((filename != null) && (filename.length() > 0)) {
      int dot = filename.lastIndexOf('.');
      if ((dot > -1) && (dot < (filename.length()))) {
        return filename.substring(0, dot);
      }
    }

    return filename;
  }

  /**
   * 文件大小转换
   */
  public static String getSize(long size) {
    String resultSize;
    if (size / GB >= 1) {
      // 如果当前Byte的值大于等于1GB
      resultSize = DF.format(size / (float) GB) + "GB   ";
    } else if (size / MB >= 1) {
      // 如果当前Byte的值大于等于1MB
      resultSize = DF.format(size / (float) MB) + "MB   ";
    } else if (size / KB >= 1) {
      // 如果当前Byte的值大于等于1KB
      resultSize = DF.format(size / (float) KB) + "KB   ";
    } else {
      resultSize = size + "B   ";
    }

    return resultSize;
  }

  /**
   * inputStream 转 File
   */
  static File inputStreamToFile(InputStream ins, String name) throws Exception {
    File file = new File(System.getProperty("java.io.tmpdir") + File.separator + name);
    if (file.exists()) {
      return file;
    }

    OutputStream os = new FileOutputStream(file);
    int bytesRead;
    int len = 8192;
    byte[] buffer = new byte[len];
    while ((bytesRead = ins.read(buffer, 0, len)) != -1) {
      os.write(buffer, 0, bytesRead);
    }
    os.close();
    ins.close();

    return file;
  }

  /**
   * 将文件名解析成文件的上传路径
   */
  public static File upload(MultipartFile file, String filePath) {
    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssS");
    String name = getFileNameNoEx(file.getOriginalFilename());
    String suffix = getExtensionName(file.getOriginalFilename());
    String nowStr = "-" + format.format(date);
    try {
      String fileName = name + nowStr + "." + suffix;
      String path = filePath + fileName;
      // getCanonicalFile 可解析正确各种路径
      File dest = new File(path).getCanonicalFile();
      // 检测是否存在目录
      if (!dest.getParentFile().exists()) {
        dest.getParentFile().mkdirs();
      }
      // 文件写入
      file.transferTo(dest);
      return dest;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  public static String fileToBase64(File file) throws Exception {
    FileInputStream inputFile = new FileInputStream(file);
    String base64;
    byte[] buffer = new byte[(int) file.length()];
    inputFile.read(buffer);
    inputFile.close();
    base64 = Base64.encode(buffer);
    return base64.replaceAll("[\\s*\t\n\r]", "");
  }

  /**
   * 导出excel
   */
  public static void downloadExcel(List<Map<String, Object>> list, HttpServletResponse response) throws IOException {
    String tempPath = System.getProperty("java.io.tmpdir") + IdUtil.fastSimpleUUID() + ".xlsx";
    File file = new File(tempPath);
    BigExcelWriter writer = ExcelUtil.getBigWriter(file);
    // 一次性写出内容，使用默认样式，强制输出标题
    writer.write(list, true);
    // response为HttpServletResponse对象
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
    // test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
    response.setHeader("Content-Disposition", "attachment;filename=file.xlsx");
    ServletOutputStream out = response.getOutputStream();
    // 终止后删除临时文件
    file.deleteOnExit();
    writer.flush(out, true);
    // 此处记得关闭输出Servlet流
    IoUtil.close(out);
  }

  public static String getFileType(String type) {
    String documents = "txt doc pdf ppt pps xlsx xls docx";
    String music = "mp3 wav wma mpa ram ra aac aif m4a";
    String video = "avi mpg mpe mpeg asf wmv mov qt rm mp4 flv m4v webm ogv ogg";
    String image = "bmp dib pcp dif wmf gif jpg tif eps psd cdr iff tga pcd mpt png jpeg";
    if (image.contains(type)) {
      return "pictures";
    } else if (documents.contains(type)) {
      return "documents";
    } else if (music.contains(type)) {
      return "music";
    } else if (video.contains(type)) {
      return "videos";
    } else {
      return "other";
    }
  }

  public static void checkSize(long maxSize, long size) {
    // 1M
    int len = 1024 * 1024;
    if (size > (maxSize * len)) {
      throw new BadRequestException("文件超出规定大小");
    }
  }

  /**
   * 判断两个文件是否相同
   */
  public static boolean check(File file1, File file2) {
    String img1Md5 = getMd5(file1);
    String img2Md5 = getMd5(file2);
    return img1Md5.equals(img2Md5);
  }

  /**
   * 判断两个文件是否相同
   */
  public static boolean check(String file1Md5, String file2Md5) {
    return file1Md5.equals(file2Md5);
  }

  private static byte[] getByte(File file) {
    // 得到文件长度
    byte[] b = new byte[(int) file.length()];

    try {
      InputStream in = new FileInputStream(file);
      try {
        in.read(b);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }

    return b;
  }

  /**
   * 下载文件
   *
   * @param request  /
   * @param response /
   * @param file     /
   */
  public static void downloadFile(HttpServletRequest request, HttpServletResponse response, File file,
                                  boolean deleteOnExit) {
    response.setCharacterEncoding(request.getCharacterEncoding());
    response.setContentType("application/octet-stream");
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(file);
      response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
      IOUtils.copy(fis, response.getOutputStream());
      response.flushBuffer();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (fis != null) {
        try {
          fis.close();
          if (deleteOnExit) {
            file.deleteOnExit();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static boolean delFile(File file) {
    if (!file.exists()) {
      return false;
    }

    if (file.isDirectory()) {
      File[] files = file.listFiles();
      for (File f : files) {
        delFile(f);
      }
    }
    return file.delete();
  }

  /**
   * 生成文件的路径名
   *
   * @return 路径规则:yyyyMM(年月)/dd(日)/文件名
   */
  public static String filePathByDate() {
    Date date = new Date(System.currentTimeMillis());
    StringBuffer filePath = new StringBuffer();
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String dateStr = sdf.format(date);
      System.out.println("date: " + dateStr);
      String year = dateStr.substring(0, 4);
      String month = dateStr.substring(5, 7);
      String day = dateStr.substring(8, dateStr.length());
      filePath.append(year);
      filePath.append(month);
      filePath.append("/");
      filePath.append(day);
      filePath.append("/");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return filePath.toString();
  }

  /**
   * 随机生成文件名
   *
   * @param suffix 文件名后缀
   * @return 文件名规则:yyyyMMdd(年月日)-HHmmss(时分秒)-SS(毫秒)-(0~4096的随机数)
   */
  public static String randomFileName(String suffix) {
    if (StringUtils.isEmpty(suffix)) {
      throw new RuntimeException("文件后缀不允许为空");
    }
    // 判断该后缀是否被允许
    Boolean isAllowSuffix = false;
    for (String allowSuffix : suffixArr) {
      if (allowSuffix.equals(suffix)) {
        isAllowSuffix = true;
        break;
      }
    }
    if (!isAllowSuffix) {
      throw new RuntimeException("不允许上传该类型文件");
    }
    StringBuffer fileName = new StringBuffer();
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss-SS");
      fileName.append(sdf.format(new Date(System.currentTimeMillis())));
      fileName.append("-");
    } catch (Exception e) {
      e.printStackTrace();
    }

    Random random = new Random();
    fileName.append(random.nextInt(maxNum));
    fileName.append(".");
    fileName.append(suffix);

    return fileName.toString();
  }

  public static String getMd5(byte[] bytes) {
    // 16进制字符
    char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    try {
      MessageDigest mdTemp = MessageDigest.getInstance("MD5");
      mdTemp.update(bytes);
      byte[] md = mdTemp.digest();
      int j = md.length;
      char[] str = new char[j * 2];
      int k = 0;
      // 移位 输出字符串
      for (byte byte0 : md) {
        str[k++] = hexDigits[byte0 >>> 4 & 0xf];
        str[k++] = hexDigits[byte0 & 0xf];
      }
      return new String(str);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String getMd5(File file) {
    return getMd5(getByte(file));
  }

  public static String getSHA1(byte[] bytes) {
    String value = null;
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
      messageDigest.update(bytes);
      value = SHA1Utils.getFormattedText(bytes);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return value.toUpperCase();
  }

  // 上传文件后先检查数据库中是否存在该文件的SHA1值,如果存在则将改文件删除,避免文件冗余
  // 若不存在则将该SHA1值和文件路径记录到数据库中(相比于MD5碰撞概率要小一些)

  /**
   * 获取文件的SHA1值
   *
   * @param file
   * @return SHA1大写字符串
   */
  public static String getSHA1ByFile(File file) {
    String value = null;
    FileInputStream in = null;
    try {
      in = new FileInputStream(file);
      MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
      MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
      messageDigest.update(byteBuffer);
      value = SHA1Utils.getFormattedText(messageDigest.digest());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != in) {
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return value.toUpperCase();
  }

  public static void copyURLToFile(final URL source, final File destination) throws IOException {
    copyInputStreamToFile(source.openStream(), destination);
  }

  public static void copyURLToFile(final URL source, final File destination, final int connectionTimeout,
                                   final int readTimeout) throws IOException {
    final URLConnection connection = source.openConnection();
    connection.setConnectTimeout(connectionTimeout);
    connection.setReadTimeout(readTimeout);

    copyInputStreamToFile(connection.getInputStream(), destination);
  }

  public static void copyInputStreamToFile(final InputStream source, final File destination) throws IOException {
    try (InputStream in = source) {
      copyToFile(in, destination);
    }
  }

  public static void copyToFile(final InputStream source, final File destination) throws IOException {
    try (OutputStream out = openOutputStream(destination)) {
      IOUtils.copy(source, out);
    }
  }

  public static FileOutputStream openOutputStream(final File file) throws IOException {
    return openOutputStream(file, false);
  }

  public static FileOutputStream openOutputStream(final File file, final boolean append) throws IOException {
    if (file.exists()) {
      if (file.isDirectory()) {
        throw new IOException("File '" + file + "' exists but is a directory");
      }
      if (file.canWrite() == false) {
        throw new IOException("File '" + file + "' cannot be written to");
      }
    } else {
      final File parent = file.getParentFile();
      if (parent != null) {
        if (!parent.mkdirs() && !parent.isDirectory()) {
          throw new IOException("Directory '" + parent + "' could not be created");
        }
      }
    }

    return new FileOutputStream(file, append);
  }

  /**
   * 获取文件对应的魔数
   *
   * @param file
   * @return
   */
  public static String getMagicNum(String file) {
    try (InputStream stream = FileUtils.getInputStream(file)) {

      byte[] b = new byte[28];
      stream.read(b, 0, 28);

      return bytesToHex(b);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 获取流文件对应的魔数
   *
   * @param inputStream
   * @return
   */
  public static String getMagicNum(ByteArrayInputStream inputStream) {
    byte[] bytes = new byte[28];
    inputStream.read(bytes, 0, 28);
    inputStream.reset();
    return bytesToHex(bytes);
  }

  /**
   * 将字节数组转换成16进制字符串
   */
  private static String bytesToHex(byte[] src) {
    StringBuilder stringBuilder = new StringBuilder();
    if (src == null || src.length <= 0) {
      return null;
    }

    for (byte aSrc : src) {
      int v = aSrc & 0xFF;
      String hv = Integer.toHexString(v);
      if (hv.length() < 2) {
        stringBuilder.append(0);
      }
      stringBuilder.append(hv);
    }

    return stringBuilder.toString();
  }
}
