package com.biology;


import com.biology.loader.*;
import com.biology.repository.AppRepository;
import com.biology.util.FileUtil;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class SqlGen {

    private TagListLoader tagListLoader = new TagListLoader();
    private UserListLoader userListLoader = new UserListLoader();
    private QuestionCommentLoader questionCommentLoader = new QuestionCommentLoader(userListLoader);
    private AnswerCommentLoader answerCommentLoader = new AnswerCommentLoader(userListLoader);
    private AnswerListLoader answerListLoader = new AnswerListLoader(userListLoader, answerCommentLoader);
    private QuestionListLoader questionListLoader = new QuestionListLoader(userListLoader, tagListLoader, answerListLoader, questionCommentLoader);

    public SqlGen() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        List<String> filePathList = FileUtil.listFilesForFolder(new File(classLoader.getResource("").getFile()));

        for(String filePath: filePathList) {
            File file = new File(filePath);
            InputStream is = new FileInputStream(file);
            String jsonTxt = IOUtils.toString(is);
            JSONObject json = new JSONObject(jsonTxt);
            JSONArray items = json.getJSONArray("items");
            Iterator it = items.iterator();
            while(it.hasNext()) {
                JSONObject item = (JSONObject) it.next();
                questionListLoader.loadTagFromJsonItem(item);
            }
        }
        tagListLoader.printSql();
        userListLoader.printSql();
        questionListLoader.printSql();
        answerListLoader.printSql();
        questionCommentLoader.printSql();
        answerCommentLoader.printSql();

        tagListLoader.report();
        userListLoader.report();
        questionListLoader.report();
        answerListLoader.report();
        questionCommentLoader.report();
        answerCommentLoader.report();
    }



    public static void main(String[] args) throws IOException {
        new SqlGen();
    }

}
