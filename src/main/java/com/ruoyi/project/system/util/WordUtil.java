package com.ruoyi.project.system.util;

/**
 * @author Vetch
 * @Description
 * @create 2021-04-09-9:19
 */

import com.ruoyi.framework.config.RuoYiConfig;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 通过word模板生成新的word工具类
 * *
 */
public class WordUtil {


    /**
     * 根据模板生成新word文档
     * 判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
     *
     * @param textMap 需要替换的信息集合
     * @return 成功返回true, 失败返回false
     */
    public static boolean changWord(InputStream inputStream, OutputStream outputStream, Map<String, String> textMap, int height, int width) {

        InputStream in = null;
        try {
            //获取docx解析对象
            XWPFDocument document = new XWPFDocument(inputStream);
            //解析替换文本段落对象
            WordUtil.changeText(document, textMap, height, width);
            //解析替换表格对象
            WordUtil.changeTable(document, textMap, height, width);

            document.write(outputStream);
            document.close();
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 替换段落文本
     *
     * @param document docx解析对象
     * @param textMap  需要替换的信息集合
     */
    public static void changeText(XWPFDocument document, Map<String, String> textMap, int height, int width) {
        //获取段落集合
        List<XWPFParagraph> paragraphs = document.getParagraphs();

        for (XWPFParagraph paragraph : paragraphs) {
            //判断此段落时候需要进行替换
            String text = paragraph.getText();
            changeValue(text, textMap, paragraph, height, width);
        }
    }


    /**
     * 匹配传入信息集合与模板
     *
     * @param newText 模板需要替换的区域
     * @param textMap 传入信息集合
     * @return 模板需要替换区域信息集合对应值
     */
    public static void changeValue(String newText, Map<String, String> textMap, XWPFParagraph paragraph, Integer height, Integer width) {
        StringBuilder str = new StringBuilder();
        List<XWPFRun> runs = paragraph.getRuns();
        FileInputStream in = null;
        try {
            for (int i = runs.size() - 1; i >= 0; i--) {
                paragraph.removeRun(i);
            }
            while (newText.indexOf("$") != -1) {
                int start = newText.indexOf("$");
                int end = newText.indexOf("}");
                str.append(newText.substring(0, start));
                String key = newText.substring(start + 2, end);
                String value = String.valueOf(textMap.get(key));
                if (value != null && !"".equals(value) && !"null".equals(value)) {
                    if ("idCardFront".equals(key) || "idCardBack".equals(key) || "diploma".equals(key) || "degree".equals(key)) {
                        File file = new File(value);
                        if (file.exists()) {
                            paragraph.createRun().setText(str.toString());
                            in = new FileInputStream(file);
                            paragraph.createRun().addPicture(in, XWPFDocument.PICTURE_TYPE_PNG, key, Units.toEMU(width), Units.toEMU(height));
                            str.delete(0, str.length());
                        }
                    } else {
                        str.append(value);
                    }
                } else {
                    String graduationDay = String.valueOf(textMap.get("graduationDay"));
                    if ("entranceDay".equals(key)&& (!"null".equals(graduationDay) && !"".equals(graduationDay))) {
                        int num=4;
                        if ("专科".equals(String.valueOf(textMap.get("eduBackground")))){
                            num=3;
                        }
                        str.append(Integer.parseInt(graduationDay.substring(0,graduationDay.indexOf(".")))-num);
                        str.append(".09");
                    }
                }


                if (end == newText.length() - 1) {
                    newText = "";
                    break;
                }
                newText = newText.substring(end + 1);
            }
            str.append(newText);


            paragraph.createRun().setText(str.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * 替换表格对象方法
     *
     * @param document docx解析对象
     * @param textMap  需要替换的信息集合
     */
    public static void changeTable(XWPFDocument document, Map<String, String> textMap, Integer height, Integer width) {
        //获取表格对象集合
        List<XWPFTable> tables = document.getTables();
        for (int i = 0; i < tables.size(); i++) {
            //只处理行数大于等于2的表格，且不循环表头
            XWPFTable table = tables.get(i);
            //判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
//            System.out.println(table.getText());
            if (checkText(table.getText())) {
                List<XWPFTableRow> rows = table.getRows();
                //遍历表格,并替换模板
                for (XWPFTableRow row : rows) {
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        //判断单元格是否需要替换
                        if (checkText(cell.getText())) {
                            List<XWPFParagraph> paragraphs = cell.getParagraphs();
                            for (XWPFParagraph paragraph : paragraphs) {
                                changeValue(paragraph.getText(), textMap, paragraph, height, width);
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * 为表格插入数据，行数不够添加新行
     *
     * @param table     需要插入数据的表格
     * @param tableList 插入数据集合
     */
    public static void insertTable(XWPFTable table, List<String[]> tableList) {
        //创建行,根据需要插入的数据添加新行，不处理表头
        for (int i = 1; i < tableList.size(); i++) {
            XWPFTableRow row = table.createRow();
        }
        //遍历表格插入数据
        List<XWPFTableRow> rows = table.getRows();
        for (int i = 1; i < rows.size(); i++) {
            XWPFTableRow newRow = table.getRow(i);
            List<XWPFTableCell> cells = newRow.getTableCells();
            for (int j = 0; j < cells.size(); j++) {
                XWPFTableCell cell = cells.get(j);
                cell.setText(tableList.get(i - 1)[j]);
            }
        }

    }


    /**
     * 判断文本中时候包含$
     *
     * @param text 文本
     * @return 包含返回true, 不包含返回false
     */
    public static boolean checkText(String text) {
        boolean check = false;
        if (text.indexOf("$") != -1) {
            check = true;
        }
        return check;

    }

    public static String createZip(List<String> files, String UserName) {
        OutputStream os = null;
        ZipOutputStream zos = null;
        String filename = "";
        try {
            filename = UserName + ".zip";
            String downloadPath = RuoYiConfig.getDownloadPath() + filename;
            File desc = new File(downloadPath);
            if (!desc.getParentFile().exists()) {
                desc.getParentFile().mkdirs();
            }
            os = new FileOutputStream(downloadPath);
            zos = new ZipOutputStream(os);
            byte[] buf = new byte[8192];
            int len;
            for (int i = 0; i < files.size(); i++) {
                File file = new File(files.get(i));
                if (!file.isFile()) continue;
                ZipEntry ze = new ZipEntry(file.getName());
                zos.putNextEntry(ze);
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                while ((len = bis.read(buf)) > 0) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                zos.closeEntry();
                zos.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }
        return filename;

    }

 /*   public static void main(String[] args) throws Exception {
        //从FTP读取文件模板
        InputStream is = new FileInputStream(new File("src/main/java/com/简历模板-初级开发工程师-张三.docx"));

        //填充文本和表格需要替换的数据
        Map<String, String> map = new HashMap();
        map.put("userName", "张三");
        map.put("age", "24");
        map.put("sex", "男");
        map.put("nation", "汉");
        map.put("birthDay", "2020-1-1");
        map.put("idCard", "16");
        map.put("phone", "163333333333");
        map.put("school", "北大");
        map.put("eduBackground", "本科");
        map.put("graduationDay", "2020-7-7");
        map.put("major", "电器");
        map.put("workDay", "7");
        map.put("workLevel", "高级");
        map.put("leavelLow", "初级");
        map.put("idCardFront", "C:/Users/75440/Desktop/upload/profile/2021/04/11/ee299936a0d49b94f601a0eb4687e1f7.jpeg");
        map.put("idCardBack", "C:/Users/75440/Desktop/upload/profile/2021/04/11/ee299936a0d49b94f601a0eb4687e1f7.jpeg");
        map.put("diploma", "C:/Users/75440/Desktop/upload/profile/2021/04/11/ee299936a0d49b94f601a0eb4687e1f7.jpeg");
        map.put("degree", "C:/Users/75440/Desktop/upload/profile/2021/04/11/ee299936a0d49b94f601a0eb4687e1f7.jpeg");
        //调取数据生成Echarts图表

        OutputStream os = new FileOutputStream("src/main/java/com/" + "张三" + ".docx");
        //生成新的word文档
        WordUtil.changWord(is, os, map, 100, 100);

    }*/
}
