package com.biology.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionTag implements Insertable {

    private Map<Integer, List<Integer>> questionTagMap = new HashMap<Integer, List<Integer>>();

    public Map<Integer, List<Integer>> getQuestionTagMap() {
        return questionTagMap;
    }

    public void add(Integer questionId, List<Integer> tagListId) {
        if(tagListId == null || tagListId.isEmpty() || questionId == null) {
            return;
        }
        if(!questionTagMap.containsKey(questionId)) {
            questionTagMap.put(questionId, tagListId);
        }
    }

    public String genInsert() {
        StringBuilder builder = new StringBuilder();
        for(Integer questionId: questionTagMap.keySet()) {
            for(Integer tagId: questionTagMap.get(questionId)) {
                String sql = String.format("INSERT INTO `question_tag` (`questionId`, `tagId`) " +
                        "VALUES (%d, %d);", questionId, tagId);
                builder.append(sql);
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public String genCreateTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `question_tag` (\n" +
                "  `questionId` bigint(20) NOT NULL,\n" +
                "  `tagId` bigint(20) NOT NULL \n" +
                "); \n" +
                "\n";
        return sql;
    }
}
