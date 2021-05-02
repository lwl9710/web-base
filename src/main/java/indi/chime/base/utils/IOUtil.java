package indi.chime.base.utils;

import java.io.*;

/**
 * I/O工具类
 */
public class IOUtil {
    /**
     * 复制文件 自动关闭流 开启缓冲
     * @param from 源文件
     * @param to 目标文件
     * @return 是否复制成功
     */
    public static boolean copyFile(String from, String to) {
        return copyFile(from, to, true);
    }
    /**
     * 复制文件 开启缓冲
     * @param from 源文件
     * @param to 目标文件
     * @param autoClose 是否自动关闭流
     * @return 是否复制成功
     */
    public static boolean copyFile(String from, String to, boolean autoClose) {
        return copyFile(from, to, autoClose, true);
    }

    /**
     * 复制文件
     * @param from 源文件
     * @param to 目标文件
     * @param autoClose 是否自动关闭流
     * @param isBuffer 是否开启缓冲
     * @return 是否复制成功
     */
    public static boolean copyFile(String from, String to, boolean autoClose, boolean isBuffer) {
        return copyFile(new File(from), new File(to), autoClose, isBuffer);
    }

    /**
     * 复制文件 自动关闭流 开启缓冲
     * @param from 源文件
     * @param to 目标文件
     * @return 是否复制成功
     */
    public static boolean copyFile(File from, File to) {
        return copyFile(from, to, true);
    }

    /**
     * 复制文件 开启缓冲
     * @param from 源文件
     * @param to 目标文件
     * @param autoClose 是否自动关闭流
     * @return 是否复制成功
     */
    public static boolean copyFile(File from, File to, boolean autoClose) {
        return copyFile(from, to, autoClose, true);
    }

    /**
     * 复制文件
     * @param from 源文件
     * @param to 目标文件
     * @param autoClose 是否自动关闭流
     * @param isBuffer 是否开启缓冲
     * @return 是否复制成功
     */
    public static boolean copyFile(File from, File to, boolean autoClose, boolean isBuffer) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(from);
            fileOutputStream = new FileOutputStream(to);
            if(isBuffer) {
                return copyFileBuffer(fileInputStream, fileOutputStream, autoClose);
            }else {
                return copyFile(fileInputStream, fileOutputStream, autoClose);
            }
        } catch (Exception ex) {
            try {
                if(fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 缓冲复制文件 自动关闭流 开启缓冲
     * @param from 源文件
     * @param to 目标文件
     * @return 是否复制成功
     */
    public static boolean copyFileBuffer(InputStream from, OutputStream to) {
        return copyFileBuffer(from, to, true);
    }

    /**
     * 复制文件 自动关闭流
     * @param from 源文件
     * @param to 目标文件
     * @return 是否复制成功
     */
    public static boolean copyFile(InputStream from, OutputStream to) {
        return copyFile(from, to, true);
    }

    /**
     * 缓冲复制文件 开启缓冲
     * @param from 源文件
     * @param to 目标文件
     * @param autoClose 是否自动关闭流
     * @return 是否复制成功
     */
    public static boolean copyFileBuffer(InputStream from, OutputStream to, boolean autoClose) {
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                bufferedInputStream = new BufferedInputStream(from);
            }catch (Exception ex) {
                if(from != null && autoClose) {
                    from.close();
                }
                ex.printStackTrace();
                return false;
            }
            bufferedOutputStream = new BufferedOutputStream(to);
        }catch (Exception ex) {
            if(autoClose) {
                try {
                    if(bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(to != null) {
                        try {
                            to.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            ex.printStackTrace();
            return false;
        }
        return copyFile(bufferedInputStream, bufferedOutputStream, autoClose);
    }

    /**
     * 复制文件
     * @param from 源文件
     * @param to 目标文件
     * @param autoClose 是否自动关闭流
     * @return 是否复制成功
     */
    public static boolean copyFile(InputStream from, OutputStream to, boolean autoClose) {
        try {
            byte[] bytes = new byte[1024];
            int len;
            while((len = from.read(bytes)) != -1) {
                to.write(bytes, 0, len);
                to.flush();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if(autoClose) {
                try {
                    if(from != null) {
                        System.out.println("关闭流-1");
                        from.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(to != null) {
                        try {
                            System.out.println("关闭流-2");
                            System.out.println(to);
                            to.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
