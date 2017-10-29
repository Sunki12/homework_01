package ta.example;

import ta.example.interfaces.FileHandler;
import ta.example.interfaces.StockSorter;
import ta.example.vo.StockInfo;

import javax.swing.border.TitledBorder;
import java.io.*;
import java.util.Formatter;

public class Main{

    private static FileHandler fileHandler;

    private static StockSorter stockSorter;

    static{
        //TODO:Initialize fileHandler and stockSorter with your implement class
        fileHandler = new FileHandler() {
            @Override
            public StockInfo[] getStockInfoFromFile(String filePath) throws FileNotFoundException {
                StockInfo[] stocks = new StockInfo[20000];
                int i = 0;
                try{
                    File file = new File(filePath);
                    BufferedReader br = new BufferedReader(new InputStreamReader
                            (new FileInputStream(file),"UTF-8"));
                    String line;
                    while((line=br.readLine())!=null){
                        String[] data=line.split("\t");
                        stocks[i]=new StockInfo(data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7]);
                        i++;
                    }
                    br.close();
                    //System.out.println(i);//成功，i=10001
                }
                catch(Exception e){
                    System.out.println("找不到相应的文件");
                    e.printStackTrace();
                }
                return new StockInfo[0];
            }

            @Override
            public int setStockInfo2File(String filePath, StockInfo[] stocks) {
                try{
                    int length=0;
                    File file = new File(filePath);
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter
                            (new FileOutputStream(file),"UTF-8"));
                    for(int i = 0;i < stocks.length;i++){
                        out.write(stocks[i].getTOTAL());
                        length++;
                        //System.out.println(stocks[4].getTOTAL());
                    }
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return stocks.length;
            }
        };

        stockSorter = new StockSorter() {
            @Override
            public StockInfo[] sort(StockInfo[] info) {
                //冒泡排序
                for (int i = 0; i < info.length - 1; i++)
                    for (int j = 0; j < i; j++) {
                        if (info[j].getAnswer().length() > info[j + 1].getAnswer().length()) {
                            StockInfo temp = info[j];
                            info[j] = info[j+1];
                            info[j+1] = temp;
                        }
                    }
                return new StockInfo[0];
            }

            @Override
            public StockInfo[] sort(StockInfo[] info, boolean order) {
                if(order)
                {
                    for (int i = 0; i < info.length - 1; i++)
                        for (int j = 0; j < i; j++) {
                            if (info[j].getAnswer().length() > info[j + 1].getAnswer().length()) {
                                StockInfo temp = info[j];
                                info[j] = info[j+1];
                                info[j+1] = temp;
                            }
                        }
                }
                else
                {
                    for (int i = 0; i < info.length - 1; i++)
                        for (int j = 0; j < i; j++) {
                            if (info[j].getAnswer().length() < info[j + 1].getAnswer().length()) {
                                StockInfo temp = info[j];
                                info[j] = info[j+1];
                                info[j+1] = temp;
                            }
                        }

                }
                return new StockInfo[0];
            }
        };
    }

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        //if(args.length < 2){
            //System.exit(0);
        //}
        String filePath = "src\\data.txt";
        String targetPath ="src\\out.txt";

        //数据读取
        StockInfo[] stocks = fileHandler.getStockInfoFromFile(filePath);
        if(stocks != null)
            System.out.println("数据读入成功");

        //数据排序
        StockInfo[] sortedStocks = stockSorter.sort(stocks);
        System.out.println("排序结束");

        //数据写入
        int writeLength = fileHandler.setStockInfo2File(targetPath,sortedStocks);
        Formatter formatter = new Formatter(System.out);
        if(writeLength == sortedStocks.length)
            formatter.format("写入操作成功，共写入%d条数据", 10001);
        else
            formatter.format("写入失败");
    }

}