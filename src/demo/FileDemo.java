package demo;

import java.io.File;
import java.io.IOException;

/**
 * @author sr
 * * @date Create at 15:10 2024/4/18
 */
public class FileDemo {
    /**
     * 测试File类
     * @param args
     */
    public static void main(String []args){
        File file = new File("D:\\1.txt");
        System.out.println(file.exists());
        if(!file.exists()){
            try{
                System.out.println(file.createNewFile() ? "创建成功":"创建失败");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
