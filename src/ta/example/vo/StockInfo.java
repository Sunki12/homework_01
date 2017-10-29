package ta.example.vo;

import com.sun.org.apache.bcel.internal.generic.LADD;
import ta.example.interfaces.FileHandler;
import ta.example.interfaces.StockSorter;
import java.io.FileInputStream;
import java.io.*;


public class StockInfo{
    public String ID;
    public String TITLE;
    public String AUTHOR;
    public String DATE;
    public String LASTUPDATE;
    public String CONTENT;
    public String ANSWERAUTHOR;
    public String ANSWER;
    public String TOTAL;

    public StockInfo(String ID, String TITLE, String AUTHOR, String DATE, String LASTUPDATE, String CONTENT, String ANSWERAUTHOR, String ANSWER){
        this.ID=ID;
        this.TITLE=TITLE;
        this.AUTHOR=AUTHOR;
        this.DATE=DATE;
        this.LASTUPDATE= LASTUPDATE;
        this.CONTENT=CONTENT;
        this.ANSWERAUTHOR=ANSWERAUTHOR;
        this.ANSWER=ANSWER;
        TOTAL=this.ID+"\t"+this.TITLE+"\t"+this.AUTHOR+"\t"
                +this.DATE+"\t"+this.LASTUPDATE+"\t"+this.CONTENT+"\t"
                +this.ANSWERAUTHOR+"\t"+this.ANSWER+"\n";
    }
    public String getID(){
        return ID;
    }
    public String getTitle(){
        return TITLE;
    }
    public String getAuthor(){
        return AUTHOR;
    }
    public String getDate(){
        return DATE;
    }
    public String getLastupdate(){
        return LASTUPDATE;
    }
    public String getContent(){
        return CONTENT;
    }
    public String getAnswerAuthor(){
        return ANSWERAUTHOR;
    }
    public String getAnswer(){
        return ANSWER;
    }

    public String getTOTAL() {
        return TOTAL;
    }
}
