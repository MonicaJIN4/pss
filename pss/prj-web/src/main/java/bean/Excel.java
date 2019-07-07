package bean;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;

public class Excel {
    public  static<T> XSSFWorkbook toWrite(List<T> list, int begin, int end) throws Exception{
        //在内存中创建一个Excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();

        //给Excel文件创建一张 Sheet1表
        Sheet sheet = workbook.createSheet();   //table --》下面的Sheet1名

        //循环创建多行
        for (int i = 0; i < list.size(); i++){
            T t = list.get(i);//每次获取集合中的对象

            if (i==0){
                //t.getClass() 获取到这个对象的class类
                Field field = t.getClass().getDeclaredField("tableName");
                field.setAccessible(true);//创建实体类的私有化属性，才可以获取到私有化的字段
                List<String> tableName =  (List<String>) field.get(t);
                Row row = sheet.createRow(0);
                for(int j = 0; j < tableName.size(); j++){
                    Cell cell = row.createCell(j);
                    cell.setCellValue(tableName.get(j));
                }
            }
            Row row = sheet.createRow(i+1);
            Field[] fields = t.getClass().getDeclaredFields();//获取该次对象所有的字段==》数组
            if ( end>fields.length ) {
                throw new Exception("长度超出对象字段范围");
            }
            for (int j = begin-1; j<end-1; j++) {

                Cell cell = row.createCell(j);
                //遍历这个字段数组
                //要访问私有的字段  就要设置强制访问为true
                fields[j].setAccessible(true);
                //获取该字段对象的字段名
//                fields[i].getName();//字段名
                Object obj= fields[j].get(t);//字段值(该对象的实例)

//                System.out.println("字段名为：" + fields[j].getName() + ",字段类型为：" + fields[j].getType() + ", 字段值为：" + fields[j].get(t));
                if ( obj!=null){
                    cell.setCellValue(obj.toString());
                } else {
                    cell.setCellValue("");
                }
            }
        }

        //文件输出流--》把内存中的excel文件写到磁盘中
        FileOutputStream fos = new FileOutputStream("采购报表" + ".xlsx");
        workbook.write(fos);
        fos.flush();
        fos.close();

        //返回Excel文件
        return workbook;
    }

}
